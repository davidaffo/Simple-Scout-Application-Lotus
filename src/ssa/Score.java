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

import java.io.Serializable;

public class Score implements Serializable{
	private static final long serialVersionUID = 300;
	private static MainController c=SSALotus.main;
	private int ourScore=0;
	private int oppScore=0;
	
	public Score(){
		
	}
	
	public void refresh(){
		c.w.ourPane.setText(Integer.toString(ourScore));
		c.w.oppPane.setText(Integer.toString(oppScore));
	}
	
	public void set(int ourScore,int oppScore){
		this.ourScore=ourScore;
		this.oppScore=oppScore;
		refresh();
	}
	
	public void increaseOurScore(){
		ourScore++;
		refresh();
	}
	
	public void decreaseOurScore(){
		if(ourScore<1)
			return;
		ourScore--;
		refresh();
	}
	
	public void increaseOppScore(){
		oppScore++;
		refresh();
	}
	
	public void decreaseOppScore(){
		if(oppScore<1)
			return;
		oppScore--;
		refresh();
	}
	
	
}
