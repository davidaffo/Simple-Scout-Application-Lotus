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

import java.util.ResourceBundle;

import ssa.MainController;
import ssa.SSALotus;

public interface SkillHandler{
	MainController c=SSALotus.main;
	ResourceBundle lang=SSALotus.lang;
	//this is what happens for each skill after a button of that sector has been pressed

	public void handlePlayerNumber(int number);

	public void handleVote(int vote);

	public void handleStartZone(int startZone);
	
	public void handleEndZone(int endZone);

	public void handleYesNo(boolean answer);

}
