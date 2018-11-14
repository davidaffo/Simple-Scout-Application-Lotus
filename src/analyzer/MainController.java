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
package analyzer;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import common.Constants;
import common.LotusVBE;
import common.Pair;
import data.Game;
import data.Player;
import data.Skill;
import data.Team;
import database.DB;


public class MainController {

	MainWindow w;
	List<Game> games = new ArrayList<Game>();
	List<String> playerIds = new ArrayList<String>();
	Game finalGame;
	ResourceBundle lang = LotusVBE.lang;
	DB database;


	public void buildWindow() {
		w = new MainWindow(this);
		w.setVisible(true);
		try {
			database = DB.importDb();
		} catch (ClassNotFoundException e) {
			LotusVBE.printError(w, lang.getString("classNotFound"));
		} catch (IOException e) {
			LotusVBE.printError(w, lang.getString("dbNotFound"));
		}
	}

	public void loadButton(){

		int voidCount=0,corruptedCount=0,counter=1;
		int noGames;
		try {
			List<File> gameFiles = new ArrayList<File>();
			TreePath[] roots = w.tree.getSelectionPaths();
			File f;

			for(TreePath tp:roots){
				TreePath root = tp;
				String path = root.toString().replaceAll("\\]","");
				path=path.replaceAll("\\[", "");
				if(File.separator.equals("\\"))
					path=path.replaceAll(", ", "\\"+File.separator);
				else
					path=path.replaceAll(",", File.separator);

				f = new File(Constants.ROOT+path);


				if(f.isDirectory()){
					Collection<File> files = FileUtils.listFiles(f, null, true);


					for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
						File file = (File) iterator.next();
						if (file.getName().endsWith(".lotusgame"))
							gameFiles.add(file); //adding all game files in a list
					}



				}
				else{ //selezionato un singolo file
					if (f.getName().endsWith(".lotusgame")){
						gameFiles.add(f);
					}
				}
			}

			//ora devo caricare gli oggetti game in una lista
			games.clear();
			FileInputStream in;
			ObjectInputStream objIn;
			String text="";
			Game g;
			for(File gf:gameFiles){
				if(gf.length() != 0){
					in = new FileInputStream(gf);
					objIn = new ObjectInputStream(in);
					g = (Game)objIn.readObject();
					in.close();
					objIn.close();
					if(g.scoutName != null){
						games.add(g);
						counter++;
					}
					else
						corruptedCount++;
				}
				else
					voidCount++;
			}

			if(counter==1){
				return;
			}

			Collections.sort(games, new Comparator<Game>() {
				public int compare(Game game1, Game game2) {
					if (game1.date.before(game2.date)) {
						return -1;
					} else {
						return 1;
					}
				}
			});

			for(int i=0;i<games.size();i++){
				text+=(i+1)+": "+games.get(i).getFilename()+"\n";
			}

			noGames=games.size();
			w.gameListArea.setText(text);
			w.gameListArea.setCaretPosition(0);

			//now i have a list with all the games that i want to process

			//now we create a list of all ids
			playerIds.clear();
			for(Game game:games) {
				for(String id:game.getPlayerIds()) { //getPlayerids() guarantee that id exists and its not null
					if(!playerIds.contains(id)){
						playerIds.add(id);
					}
				}
			}

			//lets create a game object with all the data i need
			finalGame = new Game(playerIds.size());
			//initialize players data
			for(int i=0;i<finalGame.playersSize();i++){
				finalGame.getPlayers()[i] = new Player(playerIds.get(i),null,0,finalGame,true,true,database); //i don't care about nickname and number
			}

			//sum all games in finalGame
			for(Game game:games){
				//player data
				for(Player p:finalGame.getPlayers()){
					Player playerData = game.getPlayer(p.getId());
					if(playerData!=null){
						//skills
						for(int skill=0;skill<p.skillRef.length;skill++){
							if(skill!=Constants.BOTH_ATTACKS){
								for(int vote=0;vote<Constants.BASIC_MARK_SIZE;vote++){
									//total data
									p.getSkill(skill).setMark(vote, p.getSkill(skill).getMark(vote)+playerData.getSkill(skill).getMark(vote));
									//rotation data
									for(int rotation=0;rotation<6;rotation++){
										p.getData(Player.ROTATION, rotation).getSkill(skill).setMark(vote, p.getData(Player.ROTATION, rotation).getSkill(skill).getMark(vote)+playerData.getData(Player.ROTATION, rotation).getSkill(skill).getMark(vote));
									}
								}
							}
						}
						//scoreSpike
						for(int startZone=0;startZone<Constants.ZONE_NUMBER;startZone++){
							for(int endZone=0;endZone<Constants.ZONE_NUMBER;endZone++){
								//total data
								p.getScoreSpike().setTrajectory(p.getScoreSpike().getTrajectory(startZone, endZone)+playerData.getScoreSpike().getTrajectory(startZone, endZone), startZone, endZone);
								//rotation data
								for(int rotation=0;rotation<6;rotation++){
									p.getData(Player.ROTATION, rotation).getScoreSpike().setTrajectory(p.getData(Player.ROTATION, rotation).getScoreSpike().getTrajectory(startZone, endZone)+playerData.getData(Player.ROTATION, rotation).getScoreSpike().getTrajectory(startZone, endZone), startZone, endZone);
								}
							}
						}
					}
				}
				//team data
				finalGame.getTeam().oppError+=game.getTeam().oppError;
				//sub
				//ourErrors and blocks (simple int data)
				finalGame.getTeam().getSub().ourErrors+=game.getTeam().getSub().ourErrors;
				finalGame.getTeam().getSub().blocks+=game.getTeam().getSub().blocks;
				//rotation data
				for(int rotation=0;rotation<6;rotation++){
					finalGame.getTeam().getData(Team.ROTATION, rotation).getSub().ourErrors+=game.getTeam().getData(Team.ROTATION, rotation).getSub().ourErrors;
					finalGame.getTeam().getData(Team.ROTATION, rotation).getSub().blocks+=game.getTeam().getData(Team.ROTATION, rotation).getSub().blocks;
				}
				//dig
				for(int startZone=0;startZone<Constants.ZONE_NUMBER;startZone++){
					for(int endZone=0;endZone<Constants.ZONE_NUMBER;endZone++){
						//total data
						finalGame.getTeam().getSub().getDig().setTrajectory(finalGame.getTeam().getSub().getDig().getTrajectory(startZone, endZone)+game.getTeam().getSub().getDig().getTrajectory(startZone, endZone), startZone, endZone);
						//rotation data
						for(int rotation=0;rotation<6;rotation++){
							finalGame.getTeam().getData(Player.ROTATION, rotation).getSub().getDig().setTrajectory(finalGame.getTeam().getData(Player.ROTATION, rotation).getSub().getDig().getTrajectory(startZone, endZone)+game.getTeam().getData(Player.ROTATION, rotation).getSub().getDig().getTrajectory(startZone, endZone), startZone, endZone);
						}
					}
				}
				//receive
				for(int zone=0;zone<Constants.ZONE_NUMBER-2;zone++){
					//total data
					finalGame.getTeam().getSub().getReceive().setZone(finalGame.getTeam().getSub().getReceive().getZone(zone)+game.getTeam().getSub().getReceive().getZone(zone), zone);
					//rotation data
					for(int rotation=0;rotation<6;rotation++){
						finalGame.getTeam().getData(Team.ROTATION, rotation).getSub().getReceive().setZone(finalGame.getTeam().getData(Team.ROTATION, rotation).getSub().getReceive().getZone(zone)+game.getTeam().getData(Team.ROTATION, rotation).getSub().getReceive().getZone(zone), zone);
					}
				}

				Arrays.sort(finalGame.getPlayers(), new Comparator<Player>() {
					public int compare(Player p1, Player p2) {
						return p1.printName().compareTo(p2.printName());
					}
				});

			}
			//end of summation

			//pies 
			String[] pieLabel = new String[]{lang.getString("attack"),"Ace",lang.getString("block"),lang.getString("oppError")};
			PieChart pie = new PieChart("",pieLabel,new int[]{finalGame.getTotalSkill(Constants.BOTH_ATTACKS, Player.TOTAL, 0).getPerfect(),finalGame.getTotalSkill(Constants.SERVE, Player.TOTAL, 0).getPerfect(),finalGame.getTotalSkill(Constants.BLOCK, Player.TOTAL, 0).getPerfect(),finalGame.getTeam().oppError});
			w.pieDone.setContentPane(pie.panel);

			pieLabel = new String[]{lang.getString("attack"),"Ace",lang.getString("attackError"),lang.getString("serveError"),lang.getString("ourError")};
			pie= new PieChart("",pieLabel,new int[]{finalGame.getTotalSkill(Constants.DIG, Player.TOTAL, 0).getError()+finalGame.getTotalSkill(Constants.BLOCK, Player.TOTAL, 0).getError(),finalGame.getTotalSkill(Constants.RECEIVE, Player.TOTAL, 0).getError(),finalGame.getTotalSkill(Constants.BOTH_ATTACKS, Player.TOTAL, 0).getError(),finalGame.getTotalSkill(Constants.SERVE, Player.TOTAL, 0).getError(),finalGame.getTeam().getSub().ourErrors});
			w.pieSub.setContentPane(pie.panel);



			w.statusArea.setText(
					lang.getString("found")+":\n\n"
							+noGames+" "+lang.getString("validFiles")+"\n"
							+corruptedCount+" "+lang.getString("corruptedFiles")+"\n"
							+voidCount+" "+lang.getString("voidFiles")+"\n");


			w.exportButton.setEnabled(true);
			w.oneOutButton.setEnabled(true);
			w.serveButton.setEnabled(true);
			w.receiveButton.setEnabled(true);
			w.attackButton.setEnabled(true);
			w.blockButton.setEnabled(true);
			w.digButton.setEnabled(true);
			//graphics
			//level bars
			int[] skills ={Constants.SERVE,Constants.RECEIVE,Constants.BOTH_ATTACKS,Constants.DIG,Constants.BLOCK};
			int[] indexes={Skill.EFFICIENCY,Skill.POSITIVITY,Skill.PERFECTION};
			for(int s:skills){
				for(int i:indexes){
					setProgressValue((int) finalGame.getTotalSkill(s, Player.TOTAL, 0).getStatisticIndex(i),i,w.levelBars.get(new Pair(s,i)));
				}
			}

		} catch (Exception ex) {
			LotusVBE.printError(w, lang.getString("gameDataCorrupted"));
		}

	}

	public void setProgressValue(int value, int type, JProgressBar bar){
		if (type== Skill.EFFICIENCY){
			bar.setValue(100+value);
			bar.setString(Integer.toString(value)+"%");
			return;
		}
		bar.setValue(value);

	}

	public void exportData(){

		//files creation index and main directory
		Calendar c = Calendar.getInstance();
		String fileName =c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+"-"+c.get(Calendar.HOUR_OF_DAY)+"."+c.get(Calendar.MINUTE)+".html";
		File output = new File(Constants.ROOT + fileName);
		try {
			output.createNewFile();
		} catch (IOException ex) {
			lang.getString("gameNotFound");
		}

		//everything will be printed on a single html file to facilitate cloud use, "output" is this file
		PrintWriter writer=null;
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			lang.getString("fileNotFoundModder");
			e.printStackTrace();
		}
		writer.println(finalGame.printGame("<p>Performed by SSA Lotus Analyzer "+LotusVBE.version+" &#9400; 2018 Davide Daffonchio All Rights Reserved"+"</p>","",true));
		writer.close();

		JOptionPane.showMessageDialog(w, lang.getString("dataExported")+fileName);

	}

	void showDataGraph(int skillIndex) {

		XYSeries series = new XYSeries(finalGame.getPlayers()[0].getSkill(skillIndex).getName());

		for(int i=0; i<games.size();i++){
			series.add(i+1,games.get(i).getTotalSkill(skillIndex, Player.TOTAL, 0).getAvg());
		}
		XYSeriesCollection data = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				finalGame.getPlayers()[0].getSkill(skillIndex).getName(),
				lang.getString("game"), 
				lang.getString("average"), 
				data,
				PlotOrientation.VERTICAL,
				false,
				true,
				false
				);
		// Create an NumberAxis
		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(new NumberTickUnit(1));
		xAxis.setLabel(lang.getString("game"));

		NumberAxis yAxis = new NumberAxis();
		yAxis.setTickUnit(new NumberTickUnit(0.1));
		yAxis.setLabel(lang.getString("average")); 
		yAxis.setUpperBound(4);



		// Assign it to the chart
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDomainAxis(xAxis);
		plot.setRangeAxis(yAxis);
		plot.setBackgroundPaint(Color.white);




		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000,600));

		JFrame demo = new JFrame(finalGame.getPlayers()[0].getSkill(skillIndex).getName());
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setContentPane(chartPanel);
		demo.setSize(1000,600);
		demo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		demo.setVisible(true);


	}
}


