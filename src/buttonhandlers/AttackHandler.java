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

public class AttackHandler implements SkillHandler {

	public AttackHandler(){ //i use the constructor to instantly handle the main sector button
		if(c.game.actualPhase==MainController.BREAK_POINT)
			MainController.skill=Constants.ATTACKBP;
		else
			MainController.skill=Constants.ATTACKSO;
		c.activateSector(MainController.PLAYERNUMBER_SECTOR, 0);
		c.lastAction(lang.getString("attack"), true);
	}

	@Override
	public void handlePlayerNumber(int number) {
		MainController.playerNumber=c.game.playerNumberExists(number);

		if(MainController.playerNumber==-1){
			c.notify(lang.getString("playerNumberNotExists")+" "+c.getNumDisplay());
			c.cleanNumDisplay();
		}
		else{
			c.activateSector(MainController.VOTE_SECTOR, 5);
			c.help(lang.getString("numSectorVoteAttack"));
			c.lastAction("playerNumber", false);
		}
	}

	@Override
	public void handleVote(int vote) {
		MainController.vote=vote;
		c.lastAction("vote", false);

		switch(vote){
		case 0:
			c.activateSector(MainController.YESNO_SECTOR, 0);
			break;
		case 4:
			c.activateSector(MainController.TRAJECTORY_SECTOR_START, 0);
			break;
		default:
			c.game.playerAddVote();
			c.setPhase(MainController.BREAK_POINT);
			c.activateSector(MainController.MAIN_SECTOR,MainController.SAVE);
			break;
		}

	}

	@Override
	public void handleStartZone(int startZone) {
		MainController.startZone=startZone;
		c.lastAction("fromZone", false);
		c.activateSector(MainController.TRAJECTORY_SECTOR_END, 1);
		c.activateZone7();


	}


	@Override
	public void handleEndZone(int endZone) {
		MainController.endZone=endZone;
		c.game.playerAddVote();
		c.game.scoreSpike();
		c.increaseOurScore();
		c.lastAction("toZone", false);
		c.activateSector(MainController.MAIN_SECTOR, MainController.SAVE);

	}

	@Override
	public void handleYesNo(boolean answer) {
		if(answer){//yes
			c.game.sufferedBlock();
			c.lastAction(lang.getString("blocked"), false);
		}
		else
			c.lastAction(lang.getString("notBlocked"), false);
		c.increaseOppScore();
		c.game.playerAddVote();
		c.activateSector(MainController.MAIN_SECTOR, MainController.SAVE);


	}

}
