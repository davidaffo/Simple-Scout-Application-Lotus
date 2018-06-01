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


public class Team implements Serializable{ //contains all the team related data
	private static final long serialVersionUID = 200;
	Game game;

	//keys to get data in a more abstract and compact way
	public static final int TOTAL=Player.TOTAL;
	public static final int ROTATION=Player.ROTATION;

	//team mark/zone data total (all rotations)
	private Suffered sub;
	public int oppError=0;

	//team data for the single rotation
	private Team[] rotations;

	//score data should be useless here

	public Team(Game game, boolean real){
		this.game=game;
		sub = new Suffered();
		if(real) //to avoid chain constructor bug
			rotations = new Team[]{new Team(game,false),new Team(game,false),new Team(game,false),new Team(game,false),new Team(game,false),new Team(game,false)};
	}
	
	public Team getData(int key, int value){
		switch(key){
		case TOTAL:
			return this;
		case ROTATION:
			return this.getRotation(value);
		}
		return null;
	}

	public Suffered getSub(){
		return this.sub;
	}
	public Team getRotation(int rotation){
		return this.rotations[rotation];
	}

	void sufferedReceive(){
		sub.sufferedReceive();
		rotations[game.actualRotation-1].getSub().sufferedReceive();
	}

	void sufferedAttack(){
		sub.sufferedAttack();
		rotations[game.actualRotation-1].getSub().sufferedAttack();
	}

	void sufferedBlock(){
		sub.increaseBlock();
		rotations[game.actualRotation-1].getSub().increaseBlock();
	}

	void increaseOppError(){
		oppError++;
		rotations[game.actualRotation-1].oppError++;
	}

	void increaseOurError(){
		sub.increaseOurError();
		rotations[game.actualRotation-1].getSub().increaseOurError();
	}

	public String printSuffered(int key, int value){
		return sub.printSuffered(game,key,value);
	}



}
