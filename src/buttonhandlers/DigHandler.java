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

public class DigHandler implements SkillHandler {

	public DigHandler(){ //i use the constructor to instantly handle the main sector button
		MainController.skill=Constants.DIG;
		
		c.activateSector(MainController.PLAYERNUMBER_SECTOR, 0);
		c.lastAction(lang.getString("dig"), true);
	}

	@Override
	public void handlePlayerNumber(int number) {
		MainController.playerNumber=c.game.playerNumberExists(number);
		
		if(MainController.playerNumber==-1){
			c.notify(lang.getString("playerNumberNotExists")+" "+c.getNumDisplay());
			c.cleanNumDisplay();
		}
		else{
			c.cleanNumDisplay();
			c.activateSector(MainController.VOTE_SECTOR, 5);
			c.help(lang.getString("numSectorVoteDig"));
			c.lastAction("playerNumber", false);
		}

	}

	@Override
	public void handleVote(int vote) {
		MainController.vote=vote;
		
		if(vote!=0 && vote!=1){
		c.game.playerAddVote();
		c.setPhase(MainController.BREAK_POINT);
		c.lastAction("vote", false);
		c.activateSector(MainController.MAIN_SECTOR,MainController.SAVE);
		return;
		}
		c.activateSector(MainController.TRAJECTORY_SECTOR_START, 0);
		c.lastAction("vote", false);

	}

	@Override
	public void handleStartZone(int startZone) {
		MainController.startZone=startZone;
		
		c.activateSector(MainController.TRAJECTORY_SECTOR_END, 1);
		c.lastAction("fromZone", false);

	}
	

	@Override
	public void handleEndZone(int endZone) {
		MainController.endZone=endZone;
		
		c.game.playerAddVote();
		c.game.sufferedAttack();
		c.increaseOppScore();
		c.lastAction("toZone", false);
		c.activateSector(MainController.MAIN_SECTOR, MainController.SAVE);
		
	}

	@Override
	public void handleYesNo(boolean answer) {
		// TODO Auto-generated method stub

	}

}
