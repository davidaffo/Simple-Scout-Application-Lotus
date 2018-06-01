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

import java.awt.Color;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import buttonhandlers.SkillHandler;
import common.Constants;
import common.LotusVBE;
import data.Game;
import data.Player;
import data.Team;

public class MainController{
	public Game game;
	SkillHandler handler;
	//constants
	static final String ROOT = common.Constants.ROOT;
	static final String LOTUSDATA = common.Constants.LOTUSDATA;
	static final String BACKUP_PATH = common.Constants.BACKUP_PATH;
	static ResourceBundle lang= common.LotusVBE.lang;
	public static final int SAVE=1;
	public static final int NO_SAVE=0;

	public static final int SIDE_OUT=0;
	public static final int BREAK_POINT=1;
	//micro sectors
	static int activeSector=0;
	public static final int MAIN_SECTOR=0;
	public static final int PLAYERNUMBER_SECTOR=1;
	public static final int VOTE_SECTOR=2;
	public static final int ZONE_SECTOR=3;
	public static final int TRAJECTORY_SECTOR_START=4;
	public static final int TRAJECTORY_SECTOR_END=5;
	public static final int YESNO_SECTOR=6;

	//special players commands
	public static final int SERVE_STATUS=13;
	public static final int RECEIVE_STATUS=14;
	public static final int ATTACK_STATUS=15;
	public static final int DIG_STATUS=16;
	public static final int DONE_STATUS=17;
	public static final int SUB_STATUS=18;
	public static final int BLOCK_STATUS = 19;

	//macro sector button array
	static JButton[] mainSectorButtons;
	static JButton[] numberSectorButtons;
	static JButton[] yesNoSectorButtons;
	static JButton[] playerButtons;
	//volatile variables
	public static int skill;
	public static int playerNumber;
	public static int vote;
	public static int startZone;
	public static int endZone;
	//actual set and zone are inside game, to backup them

	//list of games i use for the delete option
	private UndoList undoList = new UndoList();



	//window
	MainWindow w;

	public void buildWindow(Game game){
		w = new MainWindow(this);
		this.game=game;
		w.setVisible(true);
		mainSectorButtons = new JButton[]{w.mainSectorServe,w.mainSectorReceive,w.mainSectorAttack,w.mainSectorDig,w.mainSectorError,w.mainSectorBlock,w.mainSectorPlus,w.mainSectorRotate,w.mainSectorReset,w.mainSectorDelete};
		numberSectorButtons = new JButton[]{w.num0,w.num1,w.num2,w.num3,w.num4,w.num5,w.num6,w.num7,w.num8,w.num9,w.numbs,w.numenter};
		yesNoSectorButtons = new JButton[]{w.yButton,w.nButton};
		playerButtons = new JButton[]{w.playerButton1,w.playerButton2,w.playerButton3,w.playerButton4,w.playerButton5,w.playerButton6,w.playerButton7,w.playerButton8,w.playerButton9,w.playerButton10,w.playerButton11,w.playerButton12,w.playerButton13};
		//this.game.actualRotation=1;
		if(this.game.getPlayers()[0].getSetsSize()==0){
		this.game.actualSet=-1; //because add set increase this counter
		this.game.addSet();
		}
		activateSector(MAIN_SECTOR,NO_SAVE);
		w.setPane.setText(Integer.toString(game.actualSet+1));
		undoList.firstPush(game);
		refresh();
	}

	public void activateZone7(){
		w.num7.setEnabled(true);
	}

	public void activateSector(int sector, int parameter){ //disable all current active sectors and enable another
		for(JButton b:mainSectorButtons)
			b.setEnabled(false);
		for(JButton b:numberSectorButtons)
			b.setEnabled(false);
		for(JButton b:yesNoSectorButtons)
			b.setEnabled(false);

		if(sector!=VOTE_SECTOR)
			numberTagSet(0);

		switch(sector){
		case MAIN_SECTOR:
			for(JButton b:mainSectorButtons)
				b.setEnabled(true);
			if(undoList.isEmpty()) //it is not truly empty but filled with the start game on index 0
				w.mainSectorDelete.setEnabled(false);
			if(parameter==SAVE)
				try {
					saveState(true);
				} catch (IOException e) {
					LotusVBE.printError(w, LotusVBE.lang.getString("gameNotFound"));
					e.printStackTrace();
				}
			help(lang.getString("mainSector"));
			break;

		case PLAYERNUMBER_SECTOR:
			for(JButton b:numberSectorButtons)
				b.setEnabled(true);
			help(lang.getString("numSectorPlayer"));
			break;

		case VOTE_SECTOR: //parameter stays for the highest vote of the skill +1
			for(int i=0;i<parameter;i++)
				numberSectorButtons[i].setEnabled(true);
			numberTagSet(parameter);
			break;

		case ZONE_SECTOR: //parameter says if 0 is included or no (1 for not include, 0 for include)
			for(int i=parameter;i<=6;i++)
				numberSectorButtons[i].setEnabled(true);
			help(lang.getString("numSectorZone"));
			break;

		case TRAJECTORY_SECTOR_START:
			for(int i=0;i<=6;i++)
				numberSectorButtons[i].setEnabled(true);
			help(lang.getString("numSectorTrajectoryStart"));
			break;

		case TRAJECTORY_SECTOR_END:
			for(int i=1;i<=6;i++) //i dont need zone 7 for block-out because its automatic
				numberSectorButtons[i].setEnabled(true);
			help(lang.getString("numSectorTrajectoryEnd"));
			break;	

		case YESNO_SECTOR:
			for(JButton b:yesNoSectorButtons)
				b.setEnabled(true);
			help(lang.getString("yesNoSector"));

		}
		if(activeSector==PLAYERNUMBER_SECTOR)
			cleanNumDisplay();
		activeSector=sector;
	}

	public void numPadInput(int number){
		switch (activeSector) {
		case PLAYERNUMBER_SECTOR:
			if (w.numDisplay.getText().length() < 2) {
				w.numDisplay.setText(w.numDisplay.getText() + number);
			}
			break;
		case ZONE_SECTOR:
			handler.handleStartZone(number);
			break;
		case TRAJECTORY_SECTOR_START:
			handler.handleStartZone(number);
			break;
		case TRAJECTORY_SECTOR_END:
			handler.handleEndZone(number);
			break;	
		case VOTE_SECTOR:
			handler.handleVote(number);
			break;

		}  
	}

	public void cleanNumDisplay(){
		w.numDisplay.setText("");
	}

	public String getNumDisplay(){
		return w.numDisplay.getText();
	}

	public void rotate(){
		if (game.actualRotation == 1) {
			game.actualRotation = 6;
		} else {
			game.actualRotation--;
		}
		refresh();
		try {
			saveState(false);
		} catch (IOException e) {
			LotusVBE.printError(w, LotusVBE.lang.getString("gameNotFound"));
		}
	}

	public void setRotation(int rotation){
		game.actualRotation=rotation;
		refresh();
	}

	public void setPhase(int phase){
		game.actualPhase=phase;
		refresh();
	}

	public void undo(){
		game = undoList.pop();
		try {
			saveState(false);
		} catch (IOException e) {
			LotusVBE.printError(w, LotusVBE.lang.getString("gameNotFound"));
		}
		if(undoList.isEmpty()) //it is not truly empty but filled with the start game on index 0
			w.mainSectorDelete.setEnabled(false);
		String s = w.deleteArea.getText();
		String[] lines = s.split("\\*");
		String notify = new String(lines[lines.length-1]);
		lines[lines.length-1]="";
		s="";
		for(String line:lines){
			s+="*"+line;
		}
		s=s.substring(1, s.length()-1);
		w.deleteArea.setText(s);
		w.notify.setText(lang.getString("notifyDelete")+notify);
		refresh();
	}

	public void cancel(){
		activateSector(MAIN_SECTOR, NO_SAVE);
		w.lastActionDisplay.setText("");
		w.numDisplay.setText("");
		w.notify.setText("");
	}

	public void takeNote(){
		AnnotationPad annPad = new AnnotationPad();
		try {
			SSALotus.notes.createNewFile();
			annPad.textArea.setText(LotusVBE.readFile(Constants.ROOT + lang.getString("noteFilename")));
		} catch (IOException e) {
			return;
		}
		w.specialSectorTakeNote.setEnabled(false);
		annPad.setVisible(true);

	}

	public void livePrintSuffered(){
		StatusSheet status = new StatusSheet(99);
		String s = game.printSuffered(Team.TOTAL, 0);
		status.setText(s);
		status.setTitle(lang.getString("sufferedPoints"));
		status.statusArea.setCaretPosition(0);
		status.setVisible(true);
	}

	public void livePrintStatisticIndexes(){
		StatusSheet status = new StatusSheet(99);
		String s = game.printStatisticIndexes(Team.TOTAL, 0);
		status.setText(s);
		status.setTitle(lang.getString("statisticIndexes"));
		status.statusArea.setCaretPosition(0);
		status.setVisible(true);
	}

	public void livePrintPlayer(int player){
		StatusSheet status = new StatusSheet(player);
		String s = game.printPlayerReport(game.getPlayers()[player]);
		status.setText(s);
		status.setTitle(game.getPlayers()[player].printName());
		status.statusArea.setCaretPosition(0);
		status.setVisible(true);
	}

	public void livePrintTeam(int index){
		String s="";
		int skill;
		StatusSheet status = new StatusSheet(index);
		switch(index){
		case SERVE_STATUS:
			status.setTitle(lang.getString("serve"));
			skill=Constants.SERVE;
			break;
		case RECEIVE_STATUS:
			status.setTitle(lang.getString("receive"));
			skill=Constants.RECEIVE;
			break;
		case ATTACK_STATUS:
			status.setTitle(lang.getString("attack"));
			skill=Constants.BOTH_ATTACKS;
			break;
		case BLOCK_STATUS:
			status.setTitle(lang.getString("block"));
			skill=Constants.BLOCK;
			break;
		case DIG_STATUS:
			status.setTitle(lang.getString("dig"));
			skill=Constants.DIG;
			break;
		default:
			return;
		}
		s=game.printSkillTable(lang.getString("totalHeader"), skill, Player.TOTAL, 0);
		s+="<br>";
		s+=game.printSkillTable(lang.getString("setHeader"), skill, Player.SET, game.actualSet);
		status.setText(s);
		status.statusArea.setCaretPosition(0);
		status.setVisible(true);
	}

	public void updatePlayerData(){
		//performance ratios
		for(int i=0;i<SSALotus.MAX_PLAYERS;i++){
			Player player = game.getPlayers()[i];
			if(!player.exists()){
				for(int j=i;j<SSALotus.MAX_PLAYERS;j++){
					playerButtons[j].setText(lang.getString("empty"));
					playerButtons[j].setEnabled(false);
				}
				break;
			}
			int done = player.getDone();
			int sub = player.getSub();
			float ratio = player.getPerformanceRatio();
			String s = done + "/" + sub + " => " + String.format("%.2f", ratio);
			Color color = null;

			if (ratio == 1) {
				color = Color.black;
				if (sub == 0) {
					color = new Color(0, 150, 0);
				}
			} else if (ratio == 0) {
				color = Color.black;
			} else if (ratio > 1) {
				color = new Color(0, 150, 0);
			} else if (ratio < 1) {
				color = Color.red;
			}

			playerButtons[i].setText(player.printName() + " (" + s + ")");
			playerButtons[i].setForeground(color);
		}
	}

	public void saveState(boolean pushUndo) throws IOException{
		try {
			game.saveState();
		} catch (InterruptedException e) {
			LotusVBE.printError(w, LotusVBE.lang.getString("gameNotFound"));
		}
		if(pushUndo){
			undoList.push_back(game);
			w.mainSectorDelete.setEnabled(true);
			//put data on delete window
			String s=w.lastActionDisplay.getText().replaceAll("\n", " - ");
			s+=" - "+lang.getString("rotation")+" "+game.actualRotation+" - "+lang.getString("set")+" "+(game.actualSet+1);
			if(w.deleteArea.getText().equals(""))
				w.deleteArea.append("*"+s);
			else
				w.deleteArea.append("*\n"+s);
			refresh();
		}
	}

	public void refresh(){
		//in america P is called SR (service rotation)
		w.rotationPane.setText(lang.getString("serviceRotation")+Integer.toString(game.actualRotation));//refresh rotation
		if(game.actualPhase==BREAK_POINT)
			w.phasePane.setText(lang.getString("breakpointPhase"));
		else
			w.phasePane.setText(lang.getString("sideoutPhase"));
		game.score.refresh();

		if (undoList.size() > 1) {
			Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
			int start;
			try {
				start = w.deleteArea.getLineStartOffset(undoList.size()-2);
				int end = w.deleteArea.getLineEndOffset(undoList.size()-2);
				Highlighter h = w.deleteArea.getHighlighter();
				h.removeAllHighlights();
				h.addHighlight(start, end, painter);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		updatePlayerData();
	}

	public void newSet() {
		int response = JOptionPane.showConfirmDialog(w, lang.getString("newSet?"));
		if (response == JOptionPane.YES_OPTION) {
			game.score.set(0, 0);
			game.addSet();
			w.setPane.setText(Integer.toString(game.actualSet+1));
			setRotation(1);
			activateSector(MAIN_SECTOR,NO_SAVE);
		}
	}

	public void endOfGame() {
		int response = JOptionPane.showConfirmDialog(w, lang.getString("isGameEnd"));
		if (response != JOptionPane.YES_OPTION) {
			return;
		}
		OutputWindow ow = new OutputWindow();
		ow.setVisible(true);
		w.setEnabled(false);
	}

	public void increaseOurScore(){
		game.score.increaseOurScore();
	}

	public void decreaseOurScore(){
		game.score.decreaseOurScore();
	}

	public void increaseOppScore(){
		game.score.increaseOppScore();
	}

	public void decreaseOppScore(){
		game.score.decreaseOppScore();
	}

	public void notify(String s){
		w.notify.setText(s);
	}

	public void help(String s){
		w.helpWindow.setText(s);
	}

	public void lastAction(String s, boolean first){
		String tag="";
		if(first){//clean
			w.lastActionDisplay.setText("");
			w.lastActionDisplay.append(s);
			return;
		}
		if(s.equals("playerNumber"))
			s=(game.getPlayers()[playerNumber].getNickname()+" "+Integer.toString((game.getPlayers()[playerNumber].getNumber())));
		else if(s.equals("vote")){
			tag = game.getPlayers()[0].getSkill(skill).tags[vote];
			s=lang.getString("vote")+" "+vote+" "+tag;
		}
		else if(s.equals("fromZone"))
			s=lang.getString("fromZone")+" "+startZone;
		else if(s.equals("toZone"))
			s=lang.getString("toZone")+" "+endZone;
		w.lastActionDisplay.append("\n"+s);
	}

	public void numberTagSet(int system){
		for(int i=0;i<numberSectorButtons.length-2;i++){
			numberSectorButtons[i].setText(Integer.toString(i));
		}
		switch(system){
		case 3:
			numberSectorButtons[0].setText("<html>0 <font color=\"red\"><sb>=</b></font></html>");
			numberSectorButtons[1].setText("<html>1 <font color=\"red\"><sb>/</b></font></html>");
			numberSectorButtons[2].setText("<html>2 <font color=\"red\"><sb>#</b></font></html>");
			break;
		case 5:
			numberSectorButtons[0].setText("<html>0 <font color=\"red\"><sb>=</b></font></html>");
			numberSectorButtons[1].setText("<html>1 <font color=\"red\"><sb>-</b></font></html>");
			numberSectorButtons[2].setText("<html>2 <font color=\"red\"><sb>/</b></font></html>");
			numberSectorButtons[3].setText("<html>3 <font color=\"red\"><sb>+</b></font></html>");
			numberSectorButtons[4].setText("<html>4 <font color=\"red\"><sb>#</b></font></html>");
		}
	}

}
