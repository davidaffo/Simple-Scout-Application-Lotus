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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import common.Constants;
import common.LotusVBE;
import data.Game;
import data.Player;

public class Output {
	OutputWindow w;
	MainController c = SSALotus.main;
	boolean testmode = true;
	ResourceBundle lang = LotusVBE.lang;
	static String htmlData;
	Game game;

	public Output(OutputWindow w){
		this.w=w;
		this.game=c.game;
	}

	public void check(){
		//controlla la correttezza dei dati

		if (!"testmode".equals(w.scoutNameField.getText())) { //comando sviluppatore
			testmode = false;
			//empty fields
			if ("".equals(w.scoutNameField.getText()) || "".equals(w.casaField.getText()) || "".equals(w.ospiteField.getText())) {
				w.notify.setText(lang.getString("emptyFields"));
				return;
			}

		}
		//check went ok, starting output
		c.w.dispose();
		startOutput();
	}

	public void startOutput(){
		progress(10,"Assigning game variables");
		game.scoutName = w.scoutNameField.getText();
		game.assistName = w.assistNameField.getText();
		game.homeTeam = w.casaField.getText();
		game.guestTeam = w.ospiteField.getText();
		game.mvp = w.mvpField.getText();
		game.homeResult = Integer.toString(w.homeResult.getSelectedIndex());
		game.guestResult = Integer.toString(w.guestResult.getSelectedIndex());
		if(w.datePicker.getJFormattedTextField().getText().equals("")){
			game.date = Calendar.getInstance();
		}
		else{
			game.date = (Calendar) w.datePicker.getModel().getValue();
		}
		w.setEnabled(false); //comment for develop
		progress(25,"Creating files");

		//files creation index and main directory
		String fileNameNo = game.getFilename();
		new File(Constants.ROOT + "/" + fileNameNo).mkdir();
		String fileName = fileNameNo + ".html";
		File output = new File(Constants.ROOT + "/" + fileNameNo + "/" + fileName);
		try {
			output.createNewFile();
		} catch (IOException ex) {
			LotusVBE.printError(w, LotusVBE.lang.getString("fileNotFoundModder"));
		}
		
		progress(50,"Moving files");
		game.saveToFile(Constants.BACKUP_PATH);
		//transfering game file
		if (!testmode) {
			File backupFile = new File(Constants.BACKUP_PATH);
			try {
				Files.move(backupFile.toPath(), new File(Constants.ROOT+fileNameNo + File.separator + "game.lotusgame").toPath(), REPLACE_EXISTING);
			} catch (IOException e) {
				LotusVBE.printError(w, LotusVBE.lang.getString("gameNotFound"));
			}
		}
		
		//everything will be printed on a single html file to facilitate cloud use, "output" is this file
		progress(75,"Printing game data");
		PrintWriter writer=null;
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			LotusVBE.printError(w, LotusVBE.lang.getString("fileNotFoundModder"));
			e.printStackTrace();
		}
		writer.println(game.printGame(
				"<p>"+"Date: "+game.date.get(Calendar.YEAR)+"/"+(game.date.get(Calendar.MONTH)+1)+"/"+game.date.get(Calendar.DAY_OF_MONTH)+"</p>"
				+"<p>"+"MVP: "+game.mvp+"</p>"
				+"<p>Scout-Man: "+game.scoutName+"</p><p>"+" Assistants: "+game.assistName+"</p>"
				+"<p>Performed by SSA Lotus v"+LotusVBE.version+" &#9400; 2018 Davide Daffonchio All Rights Reserved"+"</p>"
				,game.homeTeam+" - "+game.guestTeam+" "+game.homeResult+" - "+game.guestResult,false));
		writer.close();
		//printing email list
		output = new File(Constants.ROOT + "/" + fileNameNo + "/" + "e-MailList.txt");
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		progress(90,"Exporting e-mails");
		for(Player p:game.getPlayers()){
			if(Constants.validateMail(p.getId()))
				writer.println(p.getId()+";");
		}
		writer.close();
		progress(100,"Done!");
		JOptionPane.showMessageDialog(w, lang.getString("finalAdvice"));
		System.exit(0);
		
	}
	
	public void progress(int value, String text){
		w.pb.setValue(value);
		w.pb.setString(text);
	}

}
