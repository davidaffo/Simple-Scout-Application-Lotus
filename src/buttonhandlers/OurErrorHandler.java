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

import java.io.IOException;

public class OurErrorHandler implements SkillHandler {
	
	public OurErrorHandler(){ //i use the constructor to instantly handle the main sector button
		c.game.increaseOurError();
		c.increaseOppScore();
		c.lastAction(lang.getString("ourError"), true);
		try {
			c.saveState(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void handlePlayerNumber(int number) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void handleVote(int vote) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleStartZone(int startZone) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void handleEndZone(int endZone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleYesNo(boolean answer) {
		// TODO Auto-generated method stub

	}

}
