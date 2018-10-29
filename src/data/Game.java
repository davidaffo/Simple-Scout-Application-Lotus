/*******************************************************************************
 * Copyright (C) 2018 Davide Daffonchio
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import common.Constants;
import common.LotusVBE;
import ssa.SSALotus;
import ssa.Score;

public class Game implements Serializable{ //includes all stored data of a game
	public static String nl = Constants.NL;
	private static final long serialVersionUID = 0;
	public static ResourceBundle lang = SSALotus.lang;

	public final long releaseNumber = 1; //number of the release for eventual retro-compatibility

	private Team team; //team data of the game
	private Player[] players; //array of players that contains individual data

	//data i need to backup correctly
	public int actualRotation;
	public int actualSet;
	public int actualPhase;
	public Score score;

	//output data
	public String scoutName="";
	public String assistName="";
	public String homeTeam="";
	public String guestTeam="";
	public String mvp="";
	public String homeResult="";
	public String guestResult="";
	
	public Calendar date = Calendar.getInstance();



	public Game(int playerNumber){
		team = new Team(this,true);
		players = new Player[playerNumber];
		actualRotation=1;
		actualSet=1;
		actualPhase=0;
		score = new Score();
	}

	/***************************************************************************************************************************************************/

	//getters and setters

	/***************************************************************************************************************************************************/

	public Team getTeam(){
		return this.team;
	}

	public Player[] getPlayers(){
		return this.players;
	}
	
	public List<String> getPlayerIds(){
		List<String> ids = new ArrayList<String>();
		for(Player p:players)
			if(p.exists())
				ids.add(p.getId());
		return ids;
	}
	
	public int playersSize(){
		return players.length;
	}
	
	public Player getPlayer(String id){
		for(Player p:players){
			if(id!=null && p.getId()!=null && p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	public String getFilename(){
		return date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH)+1) + "-" + date.get(Calendar.DAY_OF_MONTH) + "_" + homeTeam + "-" + guestTeam;
	}
	
	public String printDate(){
		return date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH);
	}
	

	/***************************************************************************************************************************************************/

	//data related options

	/***************************************************************************************************************************************************/

	public int playerNumberExists(int number){
		for(int i=0;i<players.length;i++)
			if(players[i]!=null && players[i].getNumber()==number)
				return i;
		return -1;
	}

	public void playerAddVote(){
		players[ssa.MainController.playerNumber].addVote();
	}

	public void addSet(){
		for(Player p:players)
			p.addSet();
		actualSet++;
	}

	public void sufferedReceive(){
		team.sufferedReceive();
	}

	public void sufferedAttack(){
		team.sufferedAttack();
	}

	public void sufferedBlock(){
		team.sufferedBlock();
	}

	public void scoreSpike(){
		players[ssa.MainController.playerNumber].scoreSpike();
	}

	public void saveState() throws InterruptedException {

		while(true){
			if(!LotusVBE.isOpen(new File(Constants.BACKUP_PATH)))
				break;
			System.out.println("File busy, waiting for save");
			Thread.sleep(100);
		}
		saveToFile(Constants.BACKUP_PATH);
	}
	
	public static Game loadFromFile(String path){
		Game game;
		try {
			if(!path.endsWith(".lotusgame"))
				return null;
			FileInputStream backupIn = new FileInputStream(path);
			ObjectInputStream objIn = new ObjectInputStream(backupIn);
			game = (Game) objIn.readObject();
			objIn.close();
			backupIn.close();
			return game;
		} catch (IOException e) {
			LotusVBE.printError(null, lang.getString("gameNotFound"));
		} catch (ClassNotFoundException e) {
			LotusVBE.printError(null, lang.getString("gameDataCorrupted"));
		}
		return null;
	}
	
	public void saveToFile(String path){
		FileOutputStream backupOut;
		try {
			backupOut = new FileOutputStream(path);
			ObjectOutputStream objOut = new ObjectOutputStream(backupOut);
			objOut.writeObject(this);
			objOut.close();
			backupOut.close();
		} catch (IOException e) {
			LotusVBE.printError(null, lang.getString("gameNotFound"));
		}
	}





	/***************************************************************************************************************************************************/

	//player related operations

	/***************************************************************************************************************************************************/

	//operations to get total data (all set all rotations)
	int getTeamMark(int skillIndex,int mark, int key, int value){ //summation of all marks of a skill of a specific mark (for example all 0 marks)
		int sum=0;
		for(Player p:players)
			sum+=p.getData(key, value).getSkill(skillIndex).getMark(mark);
		return sum;
	}

	private int getTeamTotal(int skillIndex, int key, int value){ //summation of all marks of a skill
		int sum=0;
		for(Player p:players)
			sum+=p.getData(key, value).getSkill(skillIndex).getTotal();
		return sum;
	}

	private float getTeamPerc(int skillIndex,int mark, int key, int value){ //team perc of this mark
		float sum = getTeamTotal(skillIndex,key,value);
		if(sum == 0)
			return 0;
		return (getTeamMark(skillIndex,mark,key,value)/sum) * 100;
	}

	/***************************************************************************************************************************************************/

	//team related operations

	/***************************************************************************************************************************************************/

	public void increaseOppError(){
		team.increaseOppError();
	}

	public void increaseOurError(){
		team.increaseOurError();
	}

	//output operations, i use key and value to decide what to print (total data, specific set data, specific rotation data)

	public String printSkillHeader(String header,int skillIndex){
		String s="";
		int voteNumber=players[0].skillRef[skillIndex].size();
		s+="<th>"+header+"</th>";
		for(int i=0;i<voteNumber;i++)
			s+="<th>"+"tot"+i+" <font color=\"red\">"+players[0].skillRef[skillIndex].tags[i]+"<sb></b></font>"+"</th>";
		s+="<th>"+lang.getString("efficiency")+"</th>"+"<th>"+lang.getString("positivity")+"</th>"+"<th>"+lang.getString("perfection")+"</th>"+"<th>TOT</th><th>AVG</th>";
		return s;
	}

	public String printPlayerHeader(Player p,int size){
		String s="";
		s+="<th>"+p.printName()+"</th>";
		for(int i=0;i<size;i++){
			s+="<th>"+"tot"+i+" <font color=\"red\">"+players[0].skillRef[Constants.SERVE].tags[i]+"<sb></b></font>"+"</th>";
		}
		s+="<th>"+lang.getString("efficiency")+"</th>"+"<th>"+lang.getString("positivity")+"</th>"+"<th>"+lang.getString("perfection")+"</th>"+"<th>TOT</th><th>AVG</th>";
		return s;
	}

	public String printZoneHeader(String header,int start, int end){
		String s = "<th>"+header+"</th>";
		for(int i=start;i<end+1;i++)
			s+="<th>"+SSALotus.lang.getString("zone")+i+"</th>";
		s+="<th>TOT</th>";
		return s;
	}

	public String printSkillTotal(int skillIndex,int key,int value){
		return "<td>Total:</td>"+getTotalSkill(skillIndex,key,value).printSkill();
	}

	public String printSkillTable(String header,int skillIndex, int key, int value){
		String s= "<tr>"+printSkillHeader(header,skillIndex)+"</tr>";
		for(Player p:players){
			if(!p.exists())
				break;
			s+="<tr>"+p.getData(key, value).printPlayerSkill(skillIndex,Player.EXNAME)+"</tr>";
		}
		s+="<tr>"+printSkillTotal(skillIndex,key,value)+"</tr>";
		if(skillIndex==Constants.DIG) {
			Skill sk = getTotalSkill(skillIndex, key, value);
			s+="<tr><th>"+lang.getString("counterPot")+"</th><td>"+String.format("%.0f", sk.getMarkPerc(3)+sk.getMarkPerc(4))+"%"+"</td></tr>";
		}
		return "<table>"+s+"</table>";
	}

	public String printTeamVote(int skillIndex,int vote,int key,int value){
		return getTeamMark(skillIndex,vote,key,value)+"-"+String.format("%.0f",getTeamPerc(skillIndex,vote,key,value))+"%";
	}

	public String printPlayerReport(Player p){
		String s="<tr>"+printPlayerHeader(p,Constants.BASIC_MARK_SIZE)+"</tr>";
		for(int i=0;i<p.skillRef.length;i++){
			if(i!=Constants.ATTACKBP && i!=Constants.ATTACKSO)
				s+="<tr>"+p.getData(Player.TOTAL, 0).printPlayerSkill(i,Player.SKILL)+"</tr>";
		}
		s = "<table>"+s+"</table>"+"<br>";

		String r=p.printPlayerSpikeScore(Player.TOTAL);
		r+="<br>";

		String q = "<tr>"+"<th class=\"thGood\">"+lang.getString("donePoints")+"</th>"+"<th class=\"thGood\">"+"Ace"+"</th>"+"<th class=\"thGood\">"+lang.getString("scoreAttack")+"</th>"+"<th class=\"thGood\">"+lang.getString("block")+"</th>"+"</tr>";
		q+= "<tr>"+"<td>"+p.getDone()+"</td>"+"<td>"+p.getSkill(Constants.SERVE).printVoteOnly(4)+"</td>"+"<td>"+p.getSkill(Constants.BOTH_ATTACKS).printVoteOnly(4)+"</td>"+"<td>"+p.getSkill(Constants.BLOCK).printVoteOnly(4)+"</td>"+"</tr>";
		q = "<table>"+q+"</table>"+"<br>";

		String m = "<tr>"+"<th class=\"thError\">"+lang.getString("sufferedPoints")+"</th>"+"<th class=\"thError\">"+lang.getString("serve")+"</th>"+"<th class=\"thError\">"+lang.getString("receive")+"</th>"+"<th class=\"thError\">"+lang.getString("attack")+"</th>"+"<th class=\"thError\">"+lang.getString("block")+"</th>"+"</th>"+"<th class=\"thError\">"+lang.getString("dig")+"</tr>";
		m+= "<tr>"+"<td>"+p.getSub()+"</td>"+"<td>"+p.getSkill(Constants.SERVE).printVoteOnly(0)+"</td>"+"<td>"+p.getSkill(Constants.RECEIVE).printVoteOnly(0)+"</td>"+"<td>"+p.getSkill(Constants.BOTH_ATTACKS).printVoteOnly(0)+"</td>"+"<td>"+p.getSkill(Constants.BLOCK).printVoteOnly(0)+"</td>"+"<td>"+p.getSkill(Constants.DIG).printError()+"</td>"+"</tr>";
		m = "<table>"+m+"</table>"+"<br>";

		return s+r+q+m;
	}

	public String printSuffered(int key,int value){
		return team.getData(key, value).printSuffered(key,value);
	}

	public String printStatisticIndexes(int key,int value){
		String s = printStatisticIndexesHeader("efficiency");
		for(Player p:players){
			s+="<tr>"+"<td>"+p.printName()+"</td>";
			for(int i=0;i<p.skillRef.length;i++){
				if(i!=Constants.ATTACKBP && i!=Constants.ATTACKSO)
					s+="<td>"+p.getData(key, value).skillRef[i].printEfficiency();
			}
		}

		String r = printStatisticIndexesHeader("positivity");
		for(Player p:players){
			r+="<tr>"+"<td>"+p.printName()+"</td>";
			for(int i=0;i<p.skillRef.length;i++){
				if(i!=Constants.ATTACKBP && i!=Constants.ATTACKSO)
					r+="<td>"+p.getData(key, value).skillRef[i].printPositivity();
			}
		}

		String m = printStatisticIndexesHeader("perfection");
		for(Player p:players){
			m+="<tr>"+"<td>"+p.printName()+"</td>";
			for(int i=0;i<p.skillRef.length;i++){
				if(i!=Constants.ATTACKBP && i!=Constants.ATTACKSO)
					m+="<td>"+p.getData(key, value).skillRef[i].printPerfection();
			}
		}

		return "<table>"+s+"</table>"+"<br>"+"<table>"+r+"</table>"+"<br>"+"<table>"+m+"</table>";
	}

	public String printStatisticIndexesHeader(String header){
		String s = "<tr><th>"+lang.getString(header)+"</th>";
		int size = players[0].skillRef.length;
		for(int i=0;i<size;i++){
			if(i!=Constants.ATTACKBP && i!=Constants.ATTACKSO)
				s+="<th>"+players[0].skillRef[i].getName()+"</th>";
		}
		s+="</tr>";
		return s;
	}

	public Skill getTotalSkill(int skillIndex, int key, int value){ //use this to simplify every total operation, by emulate a single skill that has all total data
		int size = players[0].getSkill(skillIndex).size();
		String name = players[0].getSkill(skillIndex).getName();
		Skill s = new Skill(size,name,players[0].getSkill(skillIndex).tags);
		for(int i=0;i<size;i++)
			s.setMark(i, getTeamMark(skillIndex, i, key, value));
		return s;
	}
	
	public Trajectory getTotalScoreSpike(){
		Trajectory t = new Trajectory("scoreAttack");
		for(Player p:players){
			for(int i=0;i<Constants.ZONE_NUMBER;i++){
				for (int j=0;j<Constants.ZONE_NUMBER;j++){
					t.setTrajectory(t.getTrajectory(i, j)+p.getScoreSpike().getTrajectory(i, j), i, j);
				}
			}
		}
		return t;
	}

	public int getTotalErrors(int key, int value) {
		int playersSub=0;
		for(Player p:players){
			playersSub+=p.getData(key, value).getSub()-p.getData(key, value).skillRef[Constants.DIG].getMark(0)-p.getData(key, value).skillRef[Constants.DIG].getMark(1); //dig error isn't considered as an error
		}
		return team.getData(key, value).getSub().getOurErrors()+playersSub;
	}

	public int getTotalPoints(int key,int value){
		int playersPoints=0;
		for(Player p:players){
			playersPoints+=p.getData(key, value).getDone();
		}
		return playersPoints+team.getData(key, value).oppError;
	}

	public String printSection(String text, String id,StringBuilder nav){ //this works with navigation bar
		String s = "<h2 id=\""+id+"\">"+text+"</h2>";
		nav.append("<a href=\"#"+id+"\">"+text+"</a>");
		return "<br>"+s+"<br>";
	}

	public String printBestScore(){
		Player[] array = this.getPlayers().clone();
		Arrays.sort(array, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2)
			{

				if (o1.getDone() < o2.getDone())
				{
					return 1;
				}
				else if (o1.getDone() > o2.getDone())
				{
					return -1;
				}
				return 0;    
			}
		});
		String s = "<tr><th>Top Score</th>"+"<th>"+lang.getString("scoreAttack")+"</th>"+"<th>"+"Ace"+"</th>"+"<th>"+lang.getString("block")+"</th>"+"<th>"+"TOT"+"</th></tr>";
		int i=1;
		for(Player p:array){
			if(p.exists())
				s+="<tr><td>"+"<font color=\"red\">"+i+"</font>"+" "+p.printName()+"</td>"+"<td>"+p.getSkill(Constants.BOTH_ATTACKS).getPerfect()+"</td>"+"<td>"+p.getSkill(Constants.SERVE).getPerfect()+"</td>"+"<td>"+p.getSkill(Constants.BLOCK).getPerfect()+"</td>"+"<td>"+p.getDone()+"</td></tr>";
			i++;
			if(i>3)
				break;
		}
		return "<table>"+s+"</table>";
	}

	public String printStrongWeakRotation(){
		String s = "<tr><th>"+lang.getString("strongWeak")+"</th><th>"+lang.getString("donePoints")+"</th><th>"+lang.getString("sufferedPoints")+"</th><th>Delta</th>";
		for(int i=0;i<6;i++){
			s+="<tr><td>"+lang.getString("serviceRotation")+(i+1)+"</td>"+"<td>"+getTotalPoints(Player.ROTATION, i)+"</td>"+"<td>"+getTotalErrors(Player.ROTATION, i)+"</td>"+"<td>"+(getTotalPoints(Player.ROTATION, i)-getTotalErrors(Player.ROTATION, i))+"</td></tr>";
		}
		return "<table>"+s+"</table>";
	}

	public String printGame(String header, String topResults, boolean analyzing){ //print the entire game data (what you read on the output file, (the full html file))
		//analyzing = this method has been called from Lotus Analyzer
		String s = "";
		Skill[] skillRef = players[0].skillRef;
		StringBuilder nav= new StringBuilder(); //i can't pass a string as reference
		String topMenu="<div class=\"navbar\"><span style=\"font-size:30px;cursor:pointer\" onclick=\"openNav()\">&#9776 Menu</span></div>";
		nav.append("<div id=\"mySidenav\" class=\"sidenav\"><a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times;</a>");
		String resultsHeader = printSection(topResults, "top", nav);
		//printing skills of total data
		s+=printSection(lang.getString("totalSkill"),"totalSkill",nav);
		for(int i=0; i<skillRef.length;i++){
			s+=printSkillTable(skillRef[i].getName(), i, Player.TOTAL, 0)+"<br>";
		}
		
		s+=printSection("topScore","topScore",nav);
		s+=printBestScore();
		
		s+=printSection(lang.getString("scoreAttack"),"scoreAttack",nav);
		s+=getTotalScoreSpike().printTrajectory("");
		
		s+=printSection(lang.getString("sufferedPoints"),"sufferedPoints",nav);
		s+=printSuffered(Team.TOTAL, 0);

		s+=printSection(lang.getString("strongWeak"),"strongWeak",nav);
		s+=printStrongWeakRotation();

		//from here we print set data (only skills)
		//TODO sometime set doesn't print the last inserted data if not ran 2 times
		if(!analyzing){
		for(int j=0;j<players[0].sets.size();j++){
			s+=printSection(lang.getString("skills")+" Set "+(j+1),"totalSkillSet"+(j+1),nav);
			for(int i=0; i<skillRef.length;i++){
				s+=printSkillTable(skillRef[i].getName(), i, Player.SET, j)+"<br>";
			}
		}
		}

		//now we print the rotation data
		//skill rotation data
		for(int i=0;i<6;i++) {
			s+=printSection(lang.getString("skills")+" "+lang.getString("serviceRotation")+(i+1),"totalSkillRotation"+(i+1),nav);
			for(int j=0; j<skillRef.length;j++){
				s+=printSkillTable(skillRef[j].getName(), j, Player.ROTATION, i)+"<br>";
			}
			//suffered points
			s+=printSection(lang.getString("sufferedPoints")+" "+lang.getString("serviceRotation")+(i+1),"sufferedPointsRotation"+(i+1),nav);
			s+=printSuffered(Team.ROTATION,i);
		}

		//players sheets
		for (Player p:players) {
			if(p.exists()){
				s+=printSection(p.printName(),p.printName(),nav);
				s+=printPlayerReport(p);
			}
		}
		nav.append("</div>");
		return "<html>"+LotusVBE.outputHeader()+"<body>"+"<div id=\"header\">"+resultsHeader+header+"</div>"+topMenu+nav+"<div id=\"main\">"+s+"</div>"+LotusVBE.outputFooter()+"</body>"+"</html>";
	}


}
