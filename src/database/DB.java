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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import common.Constants;
import common.LotusVBE;

public class DB implements Serializable{
	public static final String DB_PATH = Constants.LOTUSDATA+"db.lotus";
	private static final long serialVersionUID = 1000;
	//where all records are stored
	private ArrayList<PlayerRecord>records;
	
	public DB(){
		 records = new ArrayList<PlayerRecord>();
	}
	
	public boolean exists(String id){
		//exists
		for(PlayerRecord r:records)
			if(r.getId().equals(id))
				return true;
		//else
		return false;
	}
	
	public boolean addRecord(String id, String name, String lastname){
		id=id.toLowerCase().replaceAll("\\s+","");
		if(exists(id) || id.equals("") || name.equals("") || lastname.equals(""))
			return false; //record creation failed
		records.add(new PlayerRecord(id,name,lastname));
		try {
			exportDb();
		} catch (IOException e) {
			LotusVBE.printError(null, LotusVBE.lang.getString("dbNotFound"));
		}
		return true;
	}
	
	public boolean editRecord(String oldId, String newId, String name, String lastname){
		assert exists(oldId);
		if(exists(newId) && !oldId.equals(newId)) return false; //if the id is already used
		oldId=oldId.toLowerCase().replaceAll("\\s+","");
		newId=newId.toLowerCase().replaceAll("\\s+","");
		PlayerRecord r = getRecord(oldId);
		r.setId(newId);
		r.setName(name);
		r.setLastname(lastname);
		try {
			exportDb();
		} catch (IOException e) {
			LotusVBE.printError(null, LotusVBE.lang.getString("dbNotFound"));
		}
		return true;
	}
	
	public void updateRecord(String id, String nickname, int number){
		assert exists(id);
		PlayerRecord r = getRecord(id);
		r.setNickname(nickname);
		r.setNumber(number);
		try {
			exportDb();
		} catch (IOException e) {
			LotusVBE.printError(null, LotusVBE.lang.getString("dbNotFound"));
			e.printStackTrace();
		}
	}
	
	public void deleteRecord(String id){
		records.remove(getRecordIndex(id));
		try {
			exportDb();
		} catch (IOException e) {
			LotusVBE.printError(null, LotusVBE.lang.getString("dbNotFound"));
			e.printStackTrace();
		}
	}
	
	public static DB importDb() throws IOException, ClassNotFoundException{ //import the db instance from a serialized file
		if(!new File(DB_PATH).exists()) //if there is no db i create a new empty db
			return new DB();
		FileInputStream in = new FileInputStream(DB_PATH);
		ObjectInputStream objIn = new ObjectInputStream(in);
		DB db = (DB) objIn.readObject();
		objIn.close();
		in.close();
		db.orderByLastname();
		return db;
	}
	
	private void exportDb() throws IOException{
		this.orderByLastname();
		FileOutputStream out = new FileOutputStream(DB_PATH);
		ObjectOutputStream objOut = new ObjectOutputStream(out);
		objOut.writeObject(this);
		objOut.close();
		out.close();
	}
	
	public ArrayList<PlayerRecord> getPlayers(){
		return this.records;
	}
	
	public PlayerRecord getRecord(String id){
		for(PlayerRecord p:records){
			if(p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	public int getRecordIndex(String id){
		for(int i=0;i<records.size();i++){
			if(records.get(i).getId()==id)
				return i;
		}
		return -1;
	}
	
	public void orderByLastname(){
		records.sort(new Comparator<PlayerRecord>() {
	        @Override
	        public int compare(PlayerRecord o1, PlayerRecord o2)
	        {

	        	if (o1.getLastname().compareTo(o2.getLastname()) > 0)
	            {
	                return 1;
	            }
	           else if (o1.getLastname().compareTo(o2.getLastname()) < 0)
	           {
	               return -1;
	           }
	           return 0;    
	        }
	    });
	}
	
	

}
