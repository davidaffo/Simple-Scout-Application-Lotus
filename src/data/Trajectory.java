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

public class Trajectory implements Serializable{
	private static final long serialVersionUID = 2;
	private int[][] zones; //from-to
	private String name;

	public Trajectory(String name){//the constructor need the size as range of possible zones
		zones=new int[Constants.ZONE_NUMBER][Constants.ZONE_NUMBER];
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public int getTrajectory(int startZone,int endZone){//returns how many time i had something on that trajectory
		return zones[startZone][endZone];
	}

	public void setTrajectory(int value,int startZone,int endZone){
		if(value>=0){
			zones[startZone][endZone]=value;
		}
	}

	public void increaseTrajectory(int startZone,int endZone){//add 
		zones[startZone][endZone]++;
	}

	public void decreaseTrajectory(int startZone,int endZone){//remove 
		zones[startZone][endZone]--;
	}

	public float getTrajectoryPerc(int startZone,int endZone){//returns the trajectory as a percentage of the total
		float sum = getTotal();
		if(sum == 0)
			return 0;

		return (zones[startZone][endZone]/sum) * 100;

	}

	public int getTotal(){//returns the summation of all trajectories
		int sum=0;
		for(int i=0;i<Constants.ZONE_NUMBER;i++){
			for(int j=0;j<Constants.ZONE_NUMBER;j++){
				sum+=zones[i][j];
			}
		}
		return sum;
	}

	public int getTotalFrom(int fromZone){
		int sum=0;
		for(int i=0;i<Constants.ZONE_NUMBER;i++){
			sum+=zones[fromZone][i];
		}
		return sum;

	}

	public float getTrajectoryPercFrom(int fromZone){
		float sum = getTotal();
		if(sum == 0)
			return 0;

		return (getTotalFrom(fromZone)/sum) * 100;
	}

	public int getTotalTo(int toZone){
		int sum=0;
		for(int i=0;i<Constants.ZONE_NUMBER;i++){
			sum+=zones[i][toZone];
		}
		return sum;

	}

	public float getTrajectoryPercTo(int toZone){
		float sum = getTotal();
		if(sum == 0)
			return 0;

		return (getTotalTo(toZone)/sum) * 100;
	}

	public String printSingleTrajectory(int start, int end){
		return getTrajectory(start,end)+"-"+String.format("%.0f",getTrajectoryPerc(start,end))+"%";
	}

	public String printTotalTrajectoryFrom(int fromZone){
		return getTotalFrom(fromZone)+"-"+String.format("%.0f",getTrajectoryPercFrom(fromZone))+"%";
	}

	public String printTotalTrajectoryTo(int toZone){
		return getTotalTo(toZone)+"-"+String.format("%.0f",getTrajectoryPercTo(toZone))+"%";
	}


	public String printTrajectory(String optional){
		String s="<tr><th>"+optional+"<br>"+name+"</th>";
		for(int i=1; i<Constants.ZONE_NUMBER;i++){
			if(i==7)
				s+="<th>"+LotusVBE.lang.getString("block")+"</th>";
			else
				s+="<th>"+SSALotus.lang.getString("toZone")+i+"</th>";
		}
		s+="<th>TOT</th></tr>";
		//matrix
		for(int i=0; i<Constants.ZONE_NUMBER-1;i++){
			//vertical header
			if(i==0)
				s+="<tr><th>"+LotusVBE.lang.getString("secondTouch")+"</th>";
			else
				s+="<tr><th>"+SSALotus.lang.getString("fromZone")+i+"</th>";
			for(int j=1;j<Constants.ZONE_NUMBER;j++){
				s+="<td>"+printSingleTrajectory(i,j)+"</td>";
			}
			s+="<td>"+printTotalTrajectoryFrom(i)+"</td>";
			s+="</tr>";
		}
		s+="<tr><th>TOT</th>";
		for(int i=1; i<Constants.ZONE_NUMBER;i++){
			s+="<td>"+printTotalTrajectoryTo(i)+"</td>";
		}
		s+="<td>= "+getTotal()+"</td>"+"</tr>";
		s="<table>"+s+"</table>";
		return s;
	}
	
	public String printTrajectoryEspl() {
		String s="<tr><th>"+LotusVBE.lang.getString("trajectory")+"</th>";
		return "<table>"+s+"</table>";
	}

}
