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
package database;

import java.io.Serializable;

public class PlayerRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1001;
	private String id;
	private String name;
	private String lastname;
	
	//nickname and number used last game
	private String nickname;
	private int number;
	
	public PlayerRecord(String id, String name, String lastname){
		setId(id); //so i do controls once
		this.name=name;
		this.lastname=lastname;
		this.nickname="";
		this.number=0;
		
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		//i must check if the id already exists and remove any space(db will control this), also the id must be lowcase (automatically converted)
		this.id = id.toLowerCase().replaceAll("\\s+","");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

}
