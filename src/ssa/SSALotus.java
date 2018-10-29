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
package ssa;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import common.Constants;
import data.Game;

//this class includes main (startup) and every static information shared in the ssa program

public class SSALotus {
	//window controllers
	public static MainController main = new MainController();
	private static SetupController setup;
	//game object
	Game game;
	//file locations
	static final String BACKUP_PATH = Constants.BACKUP_PATH;
	static File backupFile = new File(Constants.BACKUP_PATH);
	//resource bundle (debug) must be accessible from an option
	public static ResourceBundle lang= common.LotusVBE.lang;
	public static File notes = new File(Constants.ROOT + "/" + lang.getString("noteFilename"));
	public static final int MAX_PLAYERS = 13;

	public void pseudoMain() throws IOException, ClassNotFoundException {
		boolean backupActivated=false;
		//main just starts up the program. Windows will do the rest.

		//check if there is a backup file
		if (backupFile.exists() && !backupFile.isDirectory()) {
			//there is a backup file in the root folder
			int response = JOptionPane.showConfirmDialog(null, lang.getString("backupFound"));
			if (response == JOptionPane.YES_OPTION) {
				backupActivated = true;
			} else if (response != JOptionPane.NO_OPTION) {
				System.exit(0);

			}

		}
		
		if (backupActivated && (game = Game.loadFromFile(BACKUP_PATH))!=null) { //i want to load the game from the serilialized backup file
			main.buildWindow(game);
			
		} else {
			game = new Game(MAX_PLAYERS);
			setup = new SetupController();
			setup.buildWindow(game);
			
		}


	}
	
	public Game getGame(){
		return this.game;
	}
	
	

}
