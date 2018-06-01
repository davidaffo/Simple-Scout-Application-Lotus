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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import common.LotusVBE;
import data.Game;

//making deep copy everytime resolves all prblems with pointers
public class UndoList {
	private List<Game> gameList;
	private int highliterFix=0;
	
	public UndoList(){
		gameList = new ArrayList<Game>();
	}
	
	public void push_back(Game g) throws IOException{
		gameList.add((Game)Constants.deepCopy(g));
		if(gameList.size()>20){
			gameList.remove(0); //once reach the max remove the older element to save memory
			highliterFix++; //to prevent highliter issues with this option without complicated code
		}
	}
	
	public Game pop(){
		Game g;
		try {
			g = (Game) Constants.deepCopy(gameList.get(gameList.size()-2));
			gameList.remove(gameList.size()-1); //remove it
			return g;
		} catch (IOException e) {
			LotusVBE.printError(null, LotusVBE.lang.getString("gameNotFound"));
		} //get the last image of game
		return null;
	}
	
	public boolean isEmpty(){
		//the list is initialized with the start game, and it is never deleted
		if(gameList.size()<=1)
			return true;
		return false;
	}
	
	public void firstPush(Game g){
		if(gameList.size()==0)
			try {
				gameList.add((Game) Constants.deepCopy(g));
			} catch (IOException e) {
				LotusVBE.printError(null, LotusVBE.lang.getString("gameNotFound"));
			} //first push
	}
	
	public int size(){
		return gameList.size()+highliterFix;
	}

}
