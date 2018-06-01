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
package buttonhandlers;

import common.Constants;
import ssa.MainController;

public class BlockHandler implements SkillHandler {

	public BlockHandler(){ //i use the constructor to instantly handle the main sector button
		MainController.skill=Constants.BLOCK;
		
		c.activateSector(MainController.PLAYERNUMBER_SECTOR, 0);
		c.lastAction(lang.getString("block"), true);
	}
	
	@Override
	public void handlePlayerNumber(int number){
		MainController.playerNumber=c.game.playerNumberExists(number);
		
		if(MainController.playerNumber==-1){
			c.notify(lang.getString("playerNumberNotExists")+" "+c.getNumDisplay());
			c.cleanNumDisplay();
		}
		else{
			c.activateSector(MainController.VOTE_SECTOR, 5);
			c.help(lang.getString("numSectorVoteBlock"));
			c.lastAction("playerNumber", false);
		}
	}


	@Override
	public void handleVote(int vote) {
		MainController.vote=vote;
		c.lastAction("vote", false);
		
		if(vote!=0){
		c.game.playerAddVote();
		c.activateSector(MainController.MAIN_SECTOR,MainController.SAVE);
		if(vote==4)
			c.increaseOurScore();
		return;
		}
		c.activateSector(MainController.TRAJECTORY_SECTOR_START, 0);

	}

	@Override
	public void handleStartZone(int startZone) {
		MainController.startZone=startZone;
		//block-out will directly set end zone 7
		MainController.endZone=7;
		
		c.game.playerAddVote();
		c.game.sufferedAttack();
		c.increaseOppScore();
		c.lastAction("fromZone", false);
		c.activateSector(MainController.MAIN_SECTOR, MainController.SAVE);

	}
	

	@Override
	public void handleEndZone(int endZone) {
		
	}

	@Override
	public void handleYesNo(boolean answer) {
		// TODO Auto-generated method stub

	}

}
