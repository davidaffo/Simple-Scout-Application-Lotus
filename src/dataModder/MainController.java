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
package dataModder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import org.apache.commons.io.FileUtils;

import common.Constants;
import common.LotusVBE;
import data.Game;
import data.Player;

public class MainController {

	MainWindow w;
	String filePath;
	Game game;
	ResourceBundle lang = LotusVBE.lang;

	HashMap<Integer, Integer> dataSelector = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> skillSelector = new HashMap<Integer, Integer>();

	public void buildWindow(){

		w = new MainWindow(this);
		w.setVisible(true);

		dataSelector.put(0, Player.TOTAL);
		dataSelector.put(1, Player.ROTATION);
		dataSelector.put(2, Player.SET);

		skillSelector.put(0, Constants.SERVE);
		skillSelector.put(1, Constants.RECEIVE);
		skillSelector.put(2, Constants.ATTACKSO);
		skillSelector.put(3, Constants.ATTACKBP);
		skillSelector.put(4, Constants.BLOCK);
		skillSelector.put(5, Constants.DIG);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean load(){
		game = Game.loadFromFile(filePath);
		if(game == null)
			return false;
		w.scoutName.setText(game.scoutName);
		w.assistName.setText(game.assistName);
		if(game.date!=null){
			w.datePicker.getModel().setDate(game.date.get(Calendar.YEAR), game.date.get(Calendar.MONTH), game.date.get(Calendar.DAY_OF_MONTH));
		}
		w.homeTeam.setText(game.homeTeam);
		w.guestTeam.setText(game.guestTeam);
		try {
			w.homeResult.setSelectedIndex(Integer.parseInt(game.homeResult));
		} catch (NumberFormatException e) {
			w.homeResult.setSelectedIndex(0);
		}
		try {
			w.guestResult.setSelectedIndex(Integer.parseInt(game.guestResult));
		} catch (NumberFormatException e) {
			w.guestResult.setSelectedIndex(0);
		}

		w.mvp.setText(game.mvp);

		List<String> playerList = new ArrayList<String>();
		for(Player p:game.getPlayers())
			playerList.add(p.printName());
		w.playerSelector.setModel(new DefaultComboBoxModel(playerList.toArray()));
		w.playerSelector.setSelectedIndex(0);

		w.saveButton.setEnabled(true);
		selectorChanged(true);
		return true;


	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void selectorChanged(boolean changedData){
		if(game==null)
			return;
		if(changedData){
			switch(dataSelector.get(w.dataSelector.getSelectedIndex())){
			case Player.TOTAL:
				w.rotationSetSelector.setModel(new DefaultComboBoxModel<String>(new String[]{"0"}));
				break;
			case Player.ROTATION:
				w.rotationSetSelector.setModel(new DefaultComboBoxModel<String>(new String[]{"1","2","3","4","5","6"}));
				break;
			case Player.SET:
				List<String> sets = new ArrayList<String>();
				for(int i=1;i<game.getPlayers()[0].getSetNumber();i++)
					sets.add(Integer.toString(i));
				w.rotationSetSelector.setModel(new DefaultComboBoxModel(sets.toArray()));
				break;

			}
		}
		w.vote0.setText(game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).printVoteOnly(0));
		w.vote1.setText(game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).printVoteOnly(1));
		w.vote2.setText(game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).printVoteOnly(2));
		w.vote3.setText(game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).printVoteOnly(3));
		w.vote4.setText(game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).printVoteOnly(4));

		w.scoreAttackField.setText(Integer.toString(game.getPlayers()[w.playerSelector.getSelectedIndex()].getScoreSpike().getTrajectory(w.fromZoneScore.getSelectedIndex(), w.toZoneScore.getSelectedIndex()+1)));

		if(w.dataSelector.getSelectedIndex() != Player.SET){
			w.ourError.setText(Integer.toString(game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().getOurErrors()));
			w.blocks.setText(Integer.toString(game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().getBlocks()));
			w.oppError.setText(Integer.toString(game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).oppError));
			w.receiveField.setText(Integer.toString(game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().getReceive().getZone(w.receiveZone.getSelectedIndex()+1)));
			w.subAttackField.setText(Integer.toString(game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().getDig().getTrajectory(w.fromZoneSubAttack.getSelectedIndex(), w.toZoneSubAttack.getSelectedIndex()+1)));
		}


	}

	public void saveInMemoryGame(){
		if(game==null)
			return;
		game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).setMark(0, Integer.parseInt(w.vote0.getText()));
		game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).setMark(1, Integer.parseInt(w.vote1.getText()));
		game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).setMark(2, Integer.parseInt(w.vote2.getText()));
		game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).setMark(3, Integer.parseInt(w.vote3.getText()));
		game.getPlayers()[w.playerSelector.getSelectedIndex()].getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSkill(skillSelector.get(w.skillSelector.getSelectedIndex())).setMark(4, Integer.parseInt(w.vote4.getText()));

		game.getPlayers()[w.playerSelector.getSelectedIndex()].getScoreSpike().setTrajectory(Integer.parseInt(w.scoreAttackField.getText()),w.fromZoneScore.getSelectedIndex(), w.toZoneScore.getSelectedIndex()+1);

		if(w.dataSelector.getSelectedIndex() != Player.SET){
			game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().ourErrors=Integer.parseInt(w.ourError.getText());
			game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().blocks=Integer.parseInt(w.blocks.getText());
			game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).oppError=Integer.parseInt(w.oppError.getText());
			game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().getReceive().setZone(Integer.parseInt(w.receiveField.getText()),w.receiveZone.getSelectedIndex()+1);
			game.getTeam().getData(dataSelector.get(w.dataSelector.getSelectedIndex()), w.rotationSetSelector.getSelectedIndex()).getSub().getDig().setTrajectory(Integer.parseInt(w.subAttackField.getText()),w.fromZoneSubAttack.getSelectedIndex(), w.toZoneSubAttack.getSelectedIndex()+1);
		}
	}

	public void save(){
		//empty fields
		if ("".equals(w.scoutName.getText()) || "".equals(w.homeTeam.getText()) || "".equals(w.guestTeam.getText())) {
			w.notify.setText(lang.getString("emptyFields"));
			return;
		}

		w.progress.setValue(10);
		game.scoutName = w.scoutName.getText();
		game.assistName = w.assistName.getText();
		game.date = Calendar.getInstance();
		if(w.datePicker.getJFormattedTextField().getText().equals("")){
			game.date = Calendar.getInstance();
		}
		else{
			game.date = (Calendar) w.datePicker.getModel().getValue();
		}
		game.homeTeam = w.homeTeam.getText();
		game.guestTeam = w.guestTeam.getText();
		game.homeResult = Integer.toString(w.homeResult.getSelectedIndex());
		game.guestResult = Integer.toString(w.guestResult.getSelectedIndex());
		game.mvp = w.mvp.getText();
		w.progress.setValue(20);

		game.saveToFile(filePath);
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] subPath = filePath.split(pattern);
		String htmlPath = "";
		subPath[subPath.length-1]=game.getFilename()+".html";
		for(String line:subPath){
			htmlPath+=line+System.getProperty("file.separator");
		}
		w.progress.setValue(30);
		PrintWriter writer=null;
		File output = new File(htmlPath);
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			LotusVBE.printError(w, lang.getString("fileNotFoundModder"));
			e.printStackTrace();
		}
		w.progress.setValue(40);
		saveInMemoryGame();
		w.progress.setValue(70);
		writer.println(game.printGame(
				"<p>"+"Date: "+game.printDate()+"</p>"
						+"<p>"+"MVP: "+game.mvp+"</p>"
						+"<p>Scout-Man: "+game.scoutName+"</p><p>"+" Assistants: "+game.assistName+"</p>"
						+"<p>Performed by SSA Lotus v"+LotusVBE.version+" &#9400; 2018 Daffonchio Davide All Rights Reserved"+"</p>"
						,game.homeTeam+" - "+game.guestTeam+" "+game.homeResult+" - "+game.guestResult,false));
		w.progress.setValue(90);
		writer.close();
		w.progress.setValue(100);
		w.notify.setText(lang.getString("end"));

	}

	public void updateAll(){
		w.setEnabled(false);
		File f = new File(filePath);
		Collection<File> files = FileUtils.listFiles(f, null, true);
		List<File> gameFiles = new ArrayList<File>();


		for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			if (file.getName().endsWith(".lotusgame"))
				gameFiles.add(file); //adding all game files in a list
		}
		for(File gameFile:gameFiles){
			filePath = gameFile.getPath();
			if(load())
				save();
		}
		w.setEnabled(true);
	}

	public void idMod(String oldId,String newId) {
		File f = new File(filePath);
		Collection<File> files = FileUtils.listFiles(f, null, true);
		List<File> gameFiles = new ArrayList<File>();


		for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			if (file.getName().endsWith(".lotusgame"))
				gameFiles.add(file); //adding all game files in a list
		}
		for(File gameFile:gameFiles){
			filePath = gameFile.getPath();
			if(load()){
				for(Player p:game.getPlayers()){
					if(p.getId().equals(oldId)){
						p.setId(newId);
					}
				}
				save();
			}
		}
		w.setEnabled(true);
	}


}
