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

import ssa.SSALotus;


public class Skill implements Serializable{
	private static final long serialVersionUID = 1;
	private int[] marks;
	public String[] tags;
	private String name;

	public final static int EFFICIENCY=0;
	public final static int POSITIVITY=1;
	public final static int PERFECTION=2;

	public Skill(int size,String name, String[] tags){//the constructor need the size as range of possible marks
		marks=new int[size];
		this.name=name;
		this.tags=tags;
	}

	public int getMark(int mark){//returns how many times that mark has taken, for example the number of mark 3.
		return marks[mark];
	}

	public void increaseMark(int mark){//add a mark
		marks[mark]++;
	}

	public void decreaseMark(int mark){//remove a mark
		marks[mark]--;
	}

	public void setMark(int mark, int value){
		if(value>=0)
			marks[mark]=value;
	}

	public float getMarkPerc(int mark){//returns the mark as a percentage of the total
		float sum = getTotal();
		if(sum == 0)
			return 0;

		return (marks[mark]/sum) * 100;

	}

	public int getTotal(){//returns the summation of all marks ("how many time i used that skill")
		int sum=0;
		for(int mark:marks){
			sum+=mark;
		}
		return sum;
	}

	public float getWeightSum(){ //used for average calculation
		float weightSum=0;
		for(int i=0;i<marks.length;i++){
			weightSum+=marks[i]*i;
		}
		return weightSum;
	}

	public float getAvg(){//returns the average of marks
		float sum = getTotal();
		if (sum == 0) 
			return 0;

		return getWeightSum() / sum;
	}

	public int size(){
		return marks.length;
	}

	//output operations

	public String printVote(int vote){
		return printVoteOnly(vote)+"-"+printVotePercOnly(vote);
	}

	public String printVoteOnly(int vote){
		return Integer.toString(getMark(vote));
	}

	public String printVotePercOnly(int vote){
		return String.format("%.0f", getMarkPerc(vote))+"%";
	}

	public String printTotal(){
		return Integer.toString(getTotal());
	}

	public String printAvg(){
		return String.format("%.1f", getAvg());
	}

	public String printSkill(){
		String s="";
		for(int i=0;i<marks.length;i++)
			s+="<td>"+printVote(i)+"</td>";
		s+="<td>"+printEfficiency()+"</td>"+"<td>"+printPositivity()+"</td>"+"<td>"+printPerfection()+"</td>"+"<td>="+printTotal()+"</td>"+"<td>"+printAvg()+"</td>";
		return s;
	}

	public String getName(){
		return SSALotus.lang.getString(this.name);
	}

	//statistic indexes
	public float getStatisticIndex(int index){
		switch(index){
		case EFFICIENCY:
			return getEfficiency();
		case POSITIVITY:
			return getPositivity();
		case PERFECTION:
			return getPerfection();
		}
		return 99;
	}
	public float getPerfection(){
		if(getTotal()==0)
			return 0;
		return ((float)getMark(marks.length-1)/(float)getTotal())*100;
	}

	public float getPositivity(){
		if(getTotal()==0)
			return 0;
		return ((float)(getMark(marks.length-1)+getMark(marks.length-2))/(float)getTotal())*100;
	}

	public float getEfficiency(){
		if(getTotal()==0)
			return 0;
		if(this.name.equals("dig"))
			return ((float)(getMark(marks.length-1)-getMark(0)-getMark(1))/(float)getTotal())*100;
		return ((float)(getMark(marks.length-1)-getMark(0))/(float)getTotal())*100;
	}

	public String printPerfection(){
		return String.format("%.0f", getPerfection())+"%";
	}

	public String printPositivity(){
		return String.format("%.0f", getPositivity())+"%";
	}

	public String printEfficiency(){
		return String.format("%.0f", getEfficiency())+"%";
	}

	public int getPerfect(){
		return marks[marks.length-1];
	}
	
	public int getError(){
		if(this.name.equals("dig"))
			return marks[0]+marks[1];
		return marks[0];
	}
	
	public String printError(){
		return Integer.toString(getError());
	}
}
