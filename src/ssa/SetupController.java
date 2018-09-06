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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import common.Constants;
import common.LotusVBE;
import data.Game;
import data.Player;
import database.DB;
import database.PlayerRecord;

public class SetupController{
	Game game;
	//constants
	static final String ROOT = common.Constants.ROOT;
	static final String LOTUSDATA = common.Constants.LOTUSDATA;
	static ResourceBundle lang= common.LotusVBE.lang;
	static File teamFile;
	
	//window
	SetupWindow w;
	
	//lists and dynamic search variables
	static List<String> truePathTeam = new ArrayList<String>();
    static List<String> truePathPlayer = new ArrayList<String>();
	static DefaultListModel<String> model;
    static DefaultListModel<String> pmodel;
    static Scanner scan;
    static String reader;
    static JButton[] removeButton;
    static JTextField[] formNames,formNumbers,formId;
    static PlayerRecord selectedPlayer;
    
    //list used for show players from database
    static List<PlayerRecord>playersList = new ArrayList<PlayerRecord>();
    
    //database
    private DB database;
    
    public void buildWindow(Game game) throws ClassNotFoundException, IOException{
    	w = new SetupWindow(this);
    	this.game=game;
    	removeButton = new JButton[]{w.remove1, w.remove2, w.remove3, w.remove4, w.remove5, w.remove6, w.remove7, w.remove8, w.remove9, w.remove10, w.remove11, w.remove12, w.remove13};
    	formNames = new JTextField[]{w.playerName1, w.playerName2, w.playerName3, w.playerName4, w.playerName5, w.playerName6, w.playerName7, w.playerName8, w.playerName9, w.playerName10, w.playerName11, w.playerName12, w.playerName13};
		formNumbers = new JTextField[]{w.playerNumber1, w.playerNumber2, w.playerNumber3, w.playerNumber4, w.playerNumber5, w.playerNumber6, w.playerNumber7, w.playerNumber8, w.playerNumber9, w.playerNumber10, w.playerNumber11, w.playerNumber12, w.playerNumber13};
		formId = new JTextField[]{w.id1, w.id2, w.id3, w.id4, w.id5, w.id6, w.id7, w.id8, w.id9, w.id10, w.id11, w.id12, w.id13};

    	w.versionLabel.setText(lang.getString("version") + LotusVBE.version);
    	
    	//inizialize a db instance
    	database = DB.importDb();
    	
    	 w.setVisible(true);
         updateTeamList();
         updatePlayerList();
    }

    
    void dynamicSearch(){
    	playersList.clear();
    	pmodel.clear();
    	for(PlayerRecord r:database.getPlayers()){
    		if((r.getLastname().toLowerCase()).contains(w.getSearch().getText().toLowerCase()) || (r.getName().toLowerCase()).contains(w.getSearch().getText().toLowerCase()))
    			playersList.add(r);
    	}
    	for(PlayerRecord r:playersList){
			pmodel.addElement(r.getLastname()+" "+r.getName());
		}
		w.dbList.setModel(pmodel);
		w.dbList.setSelectedIndex(0);
    }
	
	void updateTeamList() {

        truePathTeam.clear();
        model = new DefaultListModel<String>();
        File o = new File(LOTUSDATA + "Teams");
        File[] files = o.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".lotus");
            }
        });
        for (File f : files) {
            model.addElement(f.getName());
            truePathTeam.add(f.getPath());
        }

        w.teamList.setModel(model);
    }
	
	@SuppressWarnings("unchecked")
	void updatePlayerList(){
		w.getSearch().setText("");
		try {
			playersList = (List<PlayerRecord>) Constants.deepCopy(database.getPlayers());
		} catch (IOException e) {
			e.printStackTrace();
		}
		pmodel = new DefaultListModel<String>();
		for(PlayerRecord r:playersList){
			pmodel.addElement(r.getLastname()+" "+r.getName());
		}
		w.dbList.setModel(pmodel);
		
	}
	
	void teamSelected(){

            if (!w.teamList.isSelectionEmpty()) {
                try {
                	File teamFile = new File(truePathTeam.get(w.teamList.getSelectedIndex()));
            		scan = new Scanner(teamFile);

            		if (scan.hasNext()) {
            			for (int i = 0; i < 13; i++) {
            				if (!scan.hasNext()) {
            					for (int j=i; j < 13; j++) {
            						formNames[j].setText("");
            						formNumbers[j].setText("");
            						formId[j].setText("");
            						formNames[j].setEditable(false);
            						formNumbers[j].setEditable(false);
            					}
            					scan.close();
            					break;
            				}

            				if (scan.hasNext()) {
            					reader = scan.useDelimiter(" ").next();
            					reader = reader.replaceAll("\\s+", "");
            					formId[i].setText(reader);
            				}
            				//aggiunta nick e number
            				if (database.exists(formId[i].getText())) {
            					formNames[i].setText(database.getRecord(formId[i].getText()).getNickname());
            					formNumbers[i].setText(Integer.toString(database.getRecord(formId[i].getText()).getNumber()));
            				}
            			}
            		} else { //file vuoto
            			for (int i = 0; i < 13; i++) {
            				formNames[i].setText("");
            				formNumbers[i].setText("");
            				formId[i].setText("");
            				formNames[i].setEditable(false);
            				formNumbers[i].setEditable(false);
            			}
            			scan.close();
            		}

            		for (int i = 0; i < 13; i++) {
            			if (!formId[i].getText().equals("")) {
            				formNames[i].setEditable(true);
            				formNumbers[i].setEditable(true);
            			} else {
            				formNames[i].setEditable(false);
            				formNumbers[i].setEditable(false);
            				formNames[i].setText("");
            				formNumbers[i].setText("");
            			}
            		}

            		scan.close();
                } catch (FileNotFoundException ex) {
                	LotusVBE.printError(w, LotusVBE.lang.getString("dbNotFound"));
                }
                w.integrityButton.setEnabled(true);
                w.deleteTeam.setEnabled(true);
                w.renameTeam.setEnabled(true);
                w.copyTeam.setEnabled(true);
                if (!w.dbList.isSelectionEmpty()) {
                    w.addPlayer.setEnabled(true);
                }

            } else {
                w.deleteTeam.setEnabled(false);
                w.renameTeam.setEnabled(false);
                w.copyTeam.setEnabled(false);
                w.integrityButton.setEnabled(false);
                for (int i = 0; i < 13; i++) {
                    formNames[i].setEditable(false);
                    formNames[i].setText("");
                    formNumbers[i].setEditable(false);
                    formNumbers[i].setText("");
                }
            }
	}


	public void integrityButton(int selectedIndex) throws IOException {
		int number=0;
		boolean integrityViolation = false;
		int playersNumber = 0; //how many players
		for (int i = 0; i < 13; i++) {
			if (!formNames[i].getText().equals("") && !formNumbers[i].getText().equals("")) {
				playersNumber++;//se i campi non sono vuoti
				try {
					number = Integer.parseInt(formNumbers[i].getText());
				} catch (NumberFormatException e) {
					integrityViolation = true;
				}
				this.game.getPlayers()[i] = new Player(formId[i].getText(), formNames[i].getText(), number,game,true,false,database);
				if (Integer.toString(number).length() > 2 || number < 0) { //se uso piu di due cifre o un numero negativo
					integrityViolation = true;
				}

			} else {
				this.game.getPlayers()[i] = new Player(null, "Vuoto", 999,game,true,false,database);
			}

			if ((!formId[i].getText().equals("")) && (formNames[i].getText().equals("") || formNumbers[i].getText().equals(""))) { //se c'è una mail senza nome e numero
				integrityViolation = true;
			}
			
			
			
			
		}
		
		if(playersNumber==0) //min 1 player
			integrityViolation = true;
		
		if (!integrityViolation) {//nessuna violazione
			//metti la lista in ordine
			Arrays.sort(this.game.getPlayers(), new Comparator<Player>() {
		        @Override
		        public int compare(Player o1, Player o2)
		        {

		        	if (o1.getNumber() > o2.getNumber())
		            {
		                return 1;
		            }
		           else if (o1.getNumber() < o2.getNumber())
		           {
		               return -1;
		           }
		           return 0;    
		        }
		    });
			//riordina i campi con la nuova lista
			for(int i=0;i<SSALotus.MAX_PLAYERS;i++){
				if(this.game.getPlayers()[i].getId()!=null){
				formId[i].setText(this.game.getPlayers()[i].getId());
				formNames[i].setText(this.game.getPlayers()[i].getNickname());
				formNumbers[i].setText(Integer.toString(this.game.getPlayers()[i].getNumber()));
				}
				else{
					formId[i].setText("");
					formNames[i].setText("");
					formNumbers[i].setText("");
				}
			}
			w.integrityStatus.setText(lang.getString("validFormation"));
			w.resetButton.setEnabled(true);
			w.startButton.setEnabled(true);
			w.deleteTeam.setEnabled(false);
			w.integrityButton.setEnabled(false);
			w.teamList.setEnabled(false);
			w.addPlayer.setEnabled(false);
			w.dbList.setEnabled(false);
			for (JButton removeButton1 : removeButton) {
				removeButton1.setEnabled(false);
			}
			for(int i=0; i<formNames.length;i++){
				//if(!formId[i].getText().equals("")){
					formNames[i].setEditable(false);
					formNumbers[i].setEditable(false);
				
			}

			//save new formation on file
			teamFile = new File(truePathTeam.get(selectedIndex));
			Files.write(Paths.get(truePathTeam.get(selectedIndex)), "".getBytes());
			for (int i = 0; i < SSALotus.MAX_PLAYERS; i++) {
				try {
					Files.write(Paths.get(truePathTeam.get(selectedIndex)), (" " + formId[i].getText()).getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					LotusVBE.printError(w, LotusVBE.lang.getString("teamNotFound"));
				}
				//update data on database
				if (database.exists(formId[i].getText())) {
					database.updateRecord(formId[i].getText(), formNames[i].getText(), Integer.parseInt(formNumbers[i].getText()));
				}

			}

		} else {//found violation
			w.integrityStatus.setText(lang.getString("notValidFormation"));
			w.resetButton.setEnabled(true);
			w.integrityButton.setEnabled(false);
		}
		
	}


	public void startButton() {
		w.dispose();
		SSALotus.main.buildWindow(game);
		
	}


	public void resetButton() {
		w.resetButton.setEnabled(false);
        w.startButton.setEnabled(false);
        w.integrityButton.setEnabled(true);
        w.deleteTeam.setEnabled(true);
        w.teamList.setEnabled(true);
        w.addPlayer.setEnabled(true);
        w.dbList.setEnabled(true);
        for (JButton removeButton1 : removeButton) {
            removeButton1.setEnabled(true);
        }
        for (int i = 0; i < formNames.length; i++) {
            if (!formId[i].getText().equals("")) {
                formNames[i].setEditable(true);
                formNumbers[i].setEditable(true);
            }
        }
		w.integrityStatus.setText(lang.getString("welcome"));
		//the array doesn't have to be cleaned
		
	}


	public void newTeam() {
		String team = JOptionPane.showInputDialog(lang.getString("newTeam"));
        if (team != null) {
            File newTeam = new File(Constants.LOTUSDATA+"Teams"+File.separator + team + ".lotus");
            try {
                newTeam.createNewFile();
            } catch (IOException ex) {
            	LotusVBE.printError(w, LotusVBE.lang.getString("teamNotFound"));
            }

            updateTeamList();
        }
		
	}


	public void deleteTeam() {
		int selection = JOptionPane.showConfirmDialog(w.getRootPane(), lang.getObject("deleteTeamConfirm") +" "+ w.teamList.getSelectedValue() + "?");
        if (selection == 0) { //yes
            try {
                Files.delete(Paths.get(truePathTeam.get(w.teamList.getSelectedIndex())));
            } catch (IOException ex) {
            	LotusVBE.printError(w, LotusVBE.lang.getString("teamNotFound"));
            }
            w.teamList.clearSelection();
            updateTeamList();
		
	}
	}


	public void renameTeam() {
		File oldTeam = new File(Constants.LOTUSDATA+"Teams"+File.separator + w.teamList.getSelectedValue());
        String newName = JOptionPane.showInputDialog(lang.getString("renameMessage"));
        if (newName != null) { //se ho inserito qualcosa e dato ok
            File newTeam = new File(Constants.LOTUSDATA+"Teams"+File.separator + newName + ".lotus");

            if (newTeam.exists()) {//se c'era già un file con quel nome
                JOptionPane.showMessageDialog(w, lang.getString("teamAlreadyExists"));
                return;
            } //else

            oldTeam.renameTo(newTeam);

            updateTeamList();
        }
		
	}
	

	public void copyTeam() {
		File srcFile = new File(Constants.LOTUSDATA+"Teams"+File.separator + w.teamList.getSelectedValue());
        File outFile = new File(Constants.LOTUSDATA+"Teams"+File.separator + w.teamList.getSelectedValue());
        int i=0;
		while(outFile.exists()){
			i++;
			outFile = new File(Constants.LOTUSDATA+"Teams"+File.separator+"copy_"+i+"_"+ w.teamList.getSelectedValue());
		}
            try {
				Files.copy(srcFile.toPath(), outFile.toPath());
			} catch (IOException e) {
				LotusVBE.printError(w, LotusVBE.lang.getString("teamNotFound"));
			}

            updateTeamList();
        }
	


	public void addPlayer() {
		//cerca il primo campo della formazione vuoto, se non c'è fai return e ciao
        int count = 0;
        while (!formId[count].getText().equals("")) {
            count++;
            if (count == 13) {
                return;
            }
        } //ora count=spazio vuoto

        for (int i = 0; i < 13; i++) { //controllo doppioni
            if (selectedPlayer.getId().equals(formId[i].getText())) {
                return;
            }
        }

        //se sono arrivato qui pare sia tutto valido
        formId[count].setText(selectedPlayer.getId());
        formNames[count].setText(selectedPlayer.getNickname());
        if(selectedPlayer.getNumber()!=0)
        	formNumbers[count].setText(Integer.toString(selectedPlayer.getNumber()));
        else
        	formNumbers[count].setText("");
        formNames[count].setEditable(true);
        formNumbers[count].setEditable(true);
    }//GEN-LAST:event_addPlayerActionPerformed


	public void newPlayer() {
		NewPlayer newPlayer = new NewPlayer(true,this);
        newPlayer.setVisible(true);
		
	}


	public DB getDatabase() {
		return database;
	}


	public void setDatabase(DB database) {
		this.database = database;
	}


	public void deletePlayer() throws FileNotFoundException {
		int answer = JOptionPane.showConfirmDialog(w, lang.getString("deletePlayer")+" " + selectedPlayer.getId() + "?");
        if (answer == JOptionPane.YES_OPTION) {
            database.deleteRecord(selectedPlayer.getId());
            updatePlayerList();
        }
		
	}


	public void editPlayer() {
		NewPlayer newPlayer = new NewPlayer(false,this);
        newPlayer.setVisible(true);
	}
	
	public void removePlayer(int player){
		formId[player].setText("");
        formNames[player].setText("");
        formNumbers[player].setText("");
        formNames[player].setEditable(false);
        formNumbers[player].setEditable(false);
	}


	public void dbListValueChanged(ListSelectionEvent evt) {
		if (!evt.getValueIsAdjusting()) {

            if (!w.dbList.isSelectionEmpty()) {
                try {
                    playerSelected(w.dbList.getSelectedIndex()); //riempie playerData
                } catch (FileNotFoundException ex) {
                	LotusVBE.printError(w, LotusVBE.lang.getString("dbNotFound"));
                }
                if (!w.teamList.isSelectionEmpty()) {
                    w.addPlayer.setEnabled(true);
                }
                w.dbDelete.setEnabled(true);
                w.dbEdit.setEnabled(true);

            } else {
                w.addPlayer.setEnabled(false);
                w.dbDelete.setEnabled(false);
                w.dbEdit.setEnabled(false);
                w.dbId.setText("");
                w.dbLastname.setText("");
                w.dbName.setText("");

            }
        }
		
	}
	
	 void playerSelected(int index) throws FileNotFoundException {
		selectedPlayer = playersList.get(index);

		w.dbId.setText(selectedPlayer.getId());
		w.dbLastname.setText(selectedPlayer.getLastname());
		w.dbName.setText(selectedPlayer.getName());

	}


}
