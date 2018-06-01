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

import common.Constants;
import common.LotusVBE;
import ssa.SSALotus;


public class Suffered implements Serializable{ //how i've suffered the points
	private static final long serialVersionUID = 200;

	public int ourErrors=0; //every suffered point that is not registered automatically from the program (some kind of errors)
	public int blocks=0; //every time someone got a 0 attack because of an opponents block
	private Trajectory dig; //where i suffered the spike
	private Zone receive; //where i suffered the serve
	
	public static int ERRORE_RICOSTRUZIONE = 99; //TODO how do i translate this in english??

	public Suffered(){
		dig = new Trajectory(SSALotus.lang.getString("dig"));
		receive = new Zone(SSALotus.lang.getString("receive"));
	}

	void sufferedReceive(){
		receive.increaseZone(ssa.MainController.startZone);
	}

	void sufferedAttack(){
		dig.increaseTrajectory(ssa.MainController.startZone, ssa.MainController.endZone);
	}

	void increaseOurError(){
		ourErrors++;
	}

	void increaseBlock(){
		blocks++;
	}

	public int getOurErrors() {
		return ourErrors;
	}

	public int getBlocks() {
		return blocks;
	}
	
	public Trajectory getDig(){
		return dig;
	}
	
	public Zone getReceive(){
		return receive;
	}

	public String printSuffered(Game game,int key, int value){
		//receive
		String s = "<tr><th>"+SSALotus.lang.getString("skill")+"</th>";
		for(int i=1; i<Constants.ZONE_NUMBER-1;i++){
			s+="<th>"+SSALotus.lang.getString("zone")+i+"</th>";
		}
		s+= "<th>TOT</th>";
		s+="</tr>";
		s+="<tr>"+receive.printZone(1,6)+"</tr>";
		s="<table>"+s+"</table>";
		//dig
		String r = dig.printTrajectory("");
		//all errors
		String m = "<tr>"+"<th class=\"thError\">"+LotusVBE.lang.getString("errors")+"</th>"+"<th class=\"thError\">"+LotusVBE.lang.getString("serve")+" - "+printPercentage(getErrorPercentage(game,Constants.SERVE,key,value))+"</th>"+"<th class=\"thError\">"+LotusVBE.lang.getString("receive")+" - "+printPercentage(getErrorPercentage(game,Constants.RECEIVE,key,value))+"</th>"+"<th class=\"thError\">"+LotusVBE.lang.getString("attack")+" - "+printPercentage(getErrorPercentage(game,Constants.BOTH_ATTACKS,key,value))+"<br>"+"("+LotusVBE.lang.getString("blocked")+" "+blocks+")"+"</th>"+"<th class=\"thError\">"+LotusVBE.lang.getString("block")+" - "+printPercentage(getErrorPercentage(game,Constants.BLOCK,key,value))+"</th>"+"<th class=\"thError\">"+LotusVBE.lang.getString("ourError")+" - "+printPercentage(getErrorPercentage(game,ERRORE_RICOSTRUZIONE,key,value))+"</th>"+"</tr>";
		m+= "<tr>"+"<td>"+game.getTotalErrors(key,value)+"</td>"+"<td>"+game.printTeamVote(Constants.SERVE, 0, key, value)+"</td>"+"<td>"+game.printTeamVote(Constants.RECEIVE, 0, key, value)+"</td>"+"<td>"+game.printTeamVote(Constants.BOTH_ATTACKS, 0, key, value)+"</td>"+"<td>"+game.printTeamVote(Constants.BLOCK, 0, key, value)+"</td>"+"<td>"+ourErrors+"</td>"+"</tr>";
		m = "<table>"+m+"</table>";
		
		return s+"<br>"+r+"<br>"+m;

	}
	
	public float getErrorPercentage(Game game,int skillIndex,int key,int value){
		float sum = game.getTotalErrors(key, value);
		if(sum == 0)
			return 0;
		if(skillIndex == ERRORE_RICOSTRUZIONE)
			return (game.getTeam().getData(key, value).getSub().getOurErrors()/sum) * 100;

		return (game.getTotalSkill(skillIndex, key, value).getError()/sum) * 100;
		
	}
	
	public String printPercentage(float value){
		return String.format("%.0f", value)+"%";
	}

}
