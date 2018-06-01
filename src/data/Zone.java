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

public class Zone implements Serializable{
	private static final long serialVersionUID = 2;
	private int[] zones;
	private String name;

	public Zone(String name){//the constructor need the size as range of possible zones
		zones=new int[Constants.ZONE_NUMBER];
		this.name=name;
	}
	
	
	public String getName(){
		return this.name;
	}

	public int getZone(int zone){//returns how many time i had something on that zone
		return zones[zone];
	}
	
	public void setZone(int value,int zone){
		if(value>=0)
			zones[zone]=value;
	}
	
	public void increaseZone(int zone){//add 
		zones[zone]++;
	}
	
	public void decreaseZone(int zone){//remove 
		zones[zone]--;
	}
	
	public float getZonePerc(int zone){//returns the zone as a percentage of the total
		float sum = getTotal();
		if(sum == 0)
			return 0;
		
		return (zones[zone]/sum) * 100;

	}

	public int getTotal(){//returns the summation of all zones
		int sum=0;
		for(int zone:zones)
			sum+=zone;
		return sum;
	}
	
	public String printZone(int zone){
		return printZoneOnly(zone)+"-"+printZonePercOnly(zone);
	}
	
	public String printZoneOnly(int zone){
		return Integer.toString(getZone(zone));
	}
	
	public String printZonePercOnly(int zone){
		return String.format("%.0f", getZonePerc(zone))+"%";
	}
	
	public String printTotal(){
		return Integer.toString(getTotal());
	}
	
	public String printZone(int start,int end){
		String s="";
		s+="<td>"+getName()+"</td>";
		for(int i=start;i<end+1;i++)
			s+="<td>"+printZone(i)+"</td>";
		s+="<td>="+printTotal()+"</td>";
		return s;
	}
	
}
