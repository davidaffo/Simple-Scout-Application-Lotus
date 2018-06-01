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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
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
import database.DB;


public class PlayerController {

	PlayerWindow w;
	MainController main;
	List<Game> games = new ArrayList<Game>();
	List<String> playerIds = new ArrayList<String>();
	Game finalGame;
	ResourceBundle lang = LotusVBE.lang;
	DB database;

	PlayerController(MainController main){
		this.main=main;
		this.games=main.games;
		this.playerIds=main.playerIds;
		this.finalGame=main.finalGame;
		this.database=main.database;
		buildWindow();
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void buildWindow() {
		w = new PlayerWindow(this);
		//playerSelector
		List<String> playerList = new ArrayList<String>();
		for(Player p:finalGame.getPlayers())
			playerList.add(p.printName());
		w.playerSelector.setModel(new DefaultComboBoxModel(playerList.toArray()));
		loadData();
		w.setVisible(true);
	}
	
	public void loadData(){
		//level bars
		int[] skills ={Constants.SERVE,Constants.RECEIVE,Constants.BOTH_ATTACKS,Constants.DIG,Constants.BLOCK};
		int[] indexes={Skill.EFFICIENCY,Skill.POSITIVITY,Skill.PERFECTION};
		for(int s:skills){
			for(int i:indexes){
				setProgressValue((int) finalGame.getPlayers()[w.playerSelector.getSelectedIndex()].getSkill(s).getStatisticIndex(i),i,w.levelBars.get(new Pair(s,i)));
			}
		}
		//pies
		String[] pieLabel = new String[]{lang.getString("attack"),"Ace",lang.getString("block")};
		PieChart pie = new PieChart("",pieLabel,new int[]{finalGame.getPlayers()[w.playerSelector.getSelectedIndex()].getSkill(Constants.BOTH_ATTACKS).getPerfect(),finalGame.getPlayers()[w.playerSelector.getSelectedIndex()].getSkill(Constants.SERVE).getPerfect(),finalGame.getPlayers()[w.playerSelector.getSelectedIndex()].getSkill(Constants.BLOCK).getPerfect()});
		w.pieDone.setContentPane(pie.panel);
	}

	public void setProgressValue(int value, int type, JProgressBar bar){
		if (type== Skill.EFFICIENCY){
			bar.setValue(100+value);
			bar.setString(Integer.toString(value)+"%");
			return;
		}
		bar.setValue(value);

	}

	void showDataGraph(int skillIndex) {

		XYSeries series = new XYSeries(finalGame.getPlayers()[0].getSkill(skillIndex).getName());

		for(int i=0; i<games.size();i++){
			series.add(i+1,games.get(i).getPlayers()[w.playerSelector.getSelectedIndex()].getSkill(skillIndex).getAvg());
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


