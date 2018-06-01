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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import database.DB;
import database.PlayerRecord;
import ssa.SSALotus;


public class Player implements Serializable{ //contains the single player related data
	private static final long serialVersionUID = 100;
	Game game;
	//keys to get data in a more abstract and compact way
	public static final int TOTAL=0;
	public static final int ROTATION=1;
	public static final int SET=2;

	//keys to say what i want to print before the vote array
	public static final int EXNAME=0;
	public static final int ID=1;
	public static final int SKILL=2;

	//player personal data
	private String id; //the identifier of the player
	private String nickname; //player used name in this game
	private int number; //the number of shirt of the player int this game
	//player mark/zone data total (all set and all rotations)
	private Skill serve;
	private Skill receive;
	private Skill attackSO; //side-out attack
	private Skill attackBP; //break-point attack
	private Skill block;
	private Skill dig;
	private Skill bothAttacks;
	private Trajectory scoreSpike;
	//quick reference array
	public Skill[] skillRef;//i use this to avoid a lot of conditional switches or if
	
	private boolean analyzing; //for print the id or name+lastname in analyzer instead of name+number that doesn't make any sense
	private DB db;


	//player data for the single set
	List<Player> sets;

	//player data for the single rotation
	Player[] rotations;

	public Player(String id, String nickname, int number,Game game, boolean real, boolean analyzing, DB db){
		this.game=game;
		this.id=id;
		this.nickname=nickname;
		this.number=number;
		this.analyzing=analyzing;
		this.db=db;

		serve = new Skill(Constants.BASIC_MARK_SIZE,"serve",new String[]{"=","-","/","+","#"});
		receive = new Skill(Constants.BASIC_MARK_SIZE,"receive",new String[]{"=","-","/","+","#"});
		attackSO = new Skill(Constants.BASIC_MARK_SIZE,"attackSO",new String[]{"=","-","/","+","#"});
		attackBP = new Skill(Constants.BASIC_MARK_SIZE,"attackBP",new String[]{"=","-","/","+","#"});
		block = new Skill(Constants.BASIC_MARK_SIZE,"block",new String[]{"=","-","/","+","#"});
		dig =new Skill(Constants.BASIC_MARK_SIZE,"dig",new String[]{"=","-","/","+","#"});
		bothAttacks= new Skill(Constants.BASIC_MARK_SIZE,"attack",new String[]{"=","-","/","+","#"});
		scoreSpike = new Trajectory(SSALotus.lang.getString("scoreAttack"));

		skillRef = new Skill[]{serve,receive,bothAttacks,attackSO,attackBP,block,dig};

		sets = new ArrayList<Player>();

		if(real) //to avoid chain constructor bug
			rotations = new Player[]{new Player(id,nickname,number,game,false,analyzing,db),new Player(id,nickname,number,game,false,analyzing,db),new Player(id,nickname,number,game,false,analyzing,db),new Player(id,nickname,number,game,false,analyzing,db),new Player(id,nickname,number,game,false,analyzing,db),new Player(id,nickname,number,game,false,analyzing,db)};
	}
	
	public Player[] getRotations(){
		return rotations;
	}
	
	public List<Player> getSets(){
		return sets;
	}
	
	public int getSetNumber(){
		return sets.size();
	}

	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		if(!id.equals("")){
			this.id=id;
		}
	}
	public String getNickname(){
		return this.nickname;
	}
	public int getNumber(){
		return this.number;
	}
	
	public String printName(){
		if(!analyzing)
		return this.nickname+" "+this.number;
		PlayerRecord record = db.getRecord(this.id);
		if(record==null)
			return this.id;
		return record.getLastname()+" "+record.getName();
	}
	
	public Trajectory getScoreSpike(){
		return this.scoreSpike;
	}
	
	public int getSetsSize(){
		return sets.size();
	}

	public Player getData(int key, int value){
		switch(key){
		case TOTAL:
			return this;
		case SET:
			return this.getSet(value);	
		case ROTATION:
			return this.getRotation(value);
		}
		return null;
	}

	private Player getSet(int set){
		return this.sets.get(set);
	}
	private Player getRotation(int rotation){
		return this.rotations[rotation];
	}

	void addVote(){
		skillRef[ssa.MainController.skill].increaseMark(ssa.MainController.vote);
		sets.get(game.actualSet).skillRef[ssa.MainController.skill].increaseMark(ssa.MainController.vote);
		rotations[game.actualRotation-1].skillRef[ssa.MainController.skill].increaseMark(ssa.MainController.vote);
	}

	public Skill getSkill(int skillIndex) {
		if(skillIndex == Constants.BOTH_ATTACKS)
			return getBothAttacks();
		return skillRef[skillIndex];
	}

	private Skill getBothAttacks(){
		for(int i=0;i<Constants.BASIC_MARK_SIZE;i++)
			bothAttacks.setMark(i, attackSO.getMark(i)+attackBP.getMark(i));
		return bothAttacks;
	}

	public void addSet(){
		sets.add(new Player(id,nickname,number,game,false,false,db));
	}

	public void scoreSpike(){
		scoreSpike.increaseTrajectory(ssa.MainController.startZone,ssa.MainController.endZone);
		rotations[game.actualRotation-1].scoreSpike.increaseTrajectory(ssa.MainController.startZone,ssa.MainController.endZone);
	}

	public int getDone(){ //summation of all points done by the player
		return getSkill(Constants.SERVE).getMark(4)+getBothAttacks().getMark(4)+getSkill(Constants.BLOCK).getMark(4);
	}

	public int getSub(){ //summation of all points suffered by the player
		return getSkill(Constants.SERVE).getMark(0)+getBothAttacks().getMark(0)+getSkill(Constants.BLOCK).getMark(0)+getSkill(Constants.RECEIVE).getMark(0)+getSkill(Constants.DIG).getMark(0)+getSkill(Constants.DIG).getMark(1);
	}

	public float getPerformanceRatio(){
		int done=getDone();
		int sub=getSub();
		if (done == 0) {
			return  sub * (-1);
		}
		if(sub == 0){
			return done;
		}
		return (float)done/(float)sub;
	}

	public boolean exists(){
		return !(this.id==null);
	}

	//output operations

	public String printPlayerSkill(int skillIndex, int key){
		switch(key){
		case EXNAME:
			return "<td>"+printName()+"</td>"+skillRef[skillIndex].printSkill();
		case ID:
			return "<td>"+getId()+"</td>"+skillRef[skillIndex].printSkill();
		case SKILL:
			return "<td>"+skillRef[skillIndex].getName()+"</td>"+skillRef[skillIndex].printSkill();
		default:
			return null;
		}
	}
	
	public String printPlayerSpikeScore(int key){
		switch(key){
		case EXNAME:
			return scoreSpike.printTrajectory(printName());
		case ID:
			return scoreSpike.printTrajectory(getId());
		default:
			return null;
		}
	}




}
