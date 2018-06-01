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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import common.Constants;
import common.LotusVBE;
import common.Pair;
import data.Skill;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JComboBox;

public class PlayerWindow extends JFrame {

	/**
	 * 
	 */
	private PlayerController c;
	private static final long serialVersionUID = 1L;
	ResourceBundle lang = LotusVBE.lang;
	JProgressBar serveEffBar;
	JProgressBar receiveEffBar;
	JProgressBar servePosBar;
	JProgressBar receivePosBar;
	JProgressBar servePerfBar;
	JProgressBar attackEffBar;
	JProgressBar digEffBar;
	JProgressBar receivePerfBar;
	JProgressBar attackPerfBar;
	JProgressBar attackPosBar;
	JProgressBar digPosBar;
	JProgressBar digPerfBar;
	JProgressBar blockPerfBar;
	JProgressBar blockPosBar;
	JProgressBar blockEffBar;

	HashMap<Pair, JProgressBar> levelBars = new HashMap<Pair, JProgressBar>();
	JInternalFrame pieDone;
	JButton serveButton;
	JButton receiveButton;
	JButton attackButton;
	JButton digButton;
	JButton blockButton;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblNewLabel_4;
	@SuppressWarnings("rawtypes")
	JComboBox playerSelector;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public PlayerWindow(PlayerController c) {
		this.setIconImage(LotusVBE.img.lotusLogo);
		UIManager.put("ProgressBar.selectionForeground", Color.black);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		setTitle(lang.getString("analyzerTitle"));
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 766, 508);
		JPanel contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(552, 104));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[112.00,grow][220.00,grow,fill][220.00,grow,fill][220,grow,center]", "[30,grow,fill][50,grow,fill][50,grow,fill][50,grow,fill][50,grow,fill][50,grow,fill][50,grow,fill][50,grow,fill][50,grow,fill]"));

		lblNewLabel_4 = new JLabel(lang.getString("player"));
		contentPane.add(lblNewLabel_4, "cell 0 0,alignx center");

		playerSelector = new JComboBox();
		playerSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.loadData();
			}
		});
		contentPane.add(playerSelector, "cell 1 0,growx");

		JLabel lblNewLabel = new JLabel(lang.getString("skill"));
		contentPane.add(lblNewLabel, "cell 0 1,alignx center,growy");

		serveButton = new JButton(lang.getString("serve"));
		serveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.SERVE);
			}
		});
		contentPane.add(serveButton, "cell 1 1,grow");

		receiveButton = new JButton(lang.getString("receive"));
		receiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.RECEIVE);
			}
		});
		contentPane.add(receiveButton, "cell 2 1,grow");

		attackButton = new JButton(lang.getString("attack"));
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.BOTH_ATTACKS);
			}
		});
		contentPane.add(attackButton, "cell 3 1,grow");

		JLabel lblNewLabel_1 = new JLabel(lang.getString("efficiency"));
		contentPane.add(lblNewLabel_1, "cell 0 2,alignx center,growy");

		serveEffBar = new JProgressBar();
		serveEffBar.setForeground(new Color(0, 153, 51));
		serveEffBar.setMaximum(200);
		serveEffBar.setValue(-100);
		serveEffBar.setStringPainted(true);
		contentPane.add(serveEffBar, "cell 1 2,grow");

		receiveEffBar = new JProgressBar();
		receiveEffBar.setForeground(new Color(0, 153, 51));
		receiveEffBar.setMaximum(200);
		receiveEffBar.setValue(-100);
		receiveEffBar.setStringPainted(true);
		contentPane.add(receiveEffBar, "cell 2 2,grow");

		attackEffBar = new JProgressBar();
		attackEffBar.setForeground(new Color(0, 153, 51));
		attackEffBar.setMaximum(200);
		attackEffBar.setValue(-100);
		attackEffBar.setStringPainted(true);
		contentPane.add(attackEffBar, "cell 3 2,grow");

		JLabel lblNewLabel_2 = new JLabel(lang.getString("positivity"));
		contentPane.add(lblNewLabel_2, "cell 0 3,alignx center,growy");

		servePosBar = new JProgressBar();
		servePosBar.setForeground(new Color(0, 153, 51));
		servePosBar.setStringPainted(true);
		contentPane.add(servePosBar, "cell 1 3,grow");

		receivePosBar = new JProgressBar();
		receivePosBar.setForeground(new Color(0, 153, 51));
		receivePosBar.setStringPainted(true);
		contentPane.add(receivePosBar, "cell 2 3,grow");

		attackPosBar = new JProgressBar();
		attackPosBar.setForeground(new Color(0, 153, 51));
		attackPosBar.setStringPainted(true);
		contentPane.add(attackPosBar, "cell 3 3,grow");

		JLabel lblNewLabel_3 = new JLabel(lang.getString("perfection"));
		contentPane.add(lblNewLabel_3, "cell 0 4,alignx center,growy");

		servePerfBar = new JProgressBar();
		servePerfBar.setForeground(new Color(0, 153, 51));
		servePerfBar.setStringPainted(true);
		contentPane.add(servePerfBar, "cell 1 4,grow");

		receivePerfBar = new JProgressBar();
		receivePerfBar.setForeground(new Color(0, 153, 51));
		receivePerfBar.setStringPainted(true);
		contentPane.add(receivePerfBar, "cell 2 4,grow");

		attackPerfBar = new JProgressBar();
		attackPerfBar.setForeground(new Color(0, 153, 51));
		attackPerfBar.setStringPainted(true);
		contentPane.add(attackPerfBar, "cell 3 4,grow");

		//hash creation
		levelBars.put(new Pair(Constants.SERVE,Skill.EFFICIENCY), serveEffBar);
		levelBars.put(new Pair(Constants.SERVE,Skill.POSITIVITY), servePosBar);
		levelBars.put(new Pair(Constants.SERVE,Skill.PERFECTION), servePerfBar);

		levelBars.put(new Pair(Constants.RECEIVE,Skill.EFFICIENCY), receiveEffBar);
		levelBars.put(new Pair(Constants.RECEIVE,Skill.POSITIVITY), receivePosBar);
		levelBars.put(new Pair(Constants.RECEIVE,Skill.PERFECTION), receivePerfBar);

		levelBars.put(new Pair(Constants.BOTH_ATTACKS,Skill.EFFICIENCY), attackEffBar);
		levelBars.put(new Pair(Constants.BOTH_ATTACKS,Skill.POSITIVITY), attackPosBar);
		levelBars.put(new Pair(Constants.BOTH_ATTACKS,Skill.PERFECTION), attackPerfBar);

		label = new JLabel("Fondamentale");
		contentPane.add(label, "cell 0 5,alignx center");

		digButton = new JButton(lang.getString("dig"));
		digButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.DIG);
			}
		});
		contentPane.add(digButton, "cell 1 5,grow");

		blockButton = new JButton(lang.getString("block"));
		blockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.BLOCK);
			}
		});
		contentPane.add(blockButton, "cell 2 5,grow");

		pieDone = new JInternalFrame(lang.getString("donePoints"));
		pieDone.setMaximumSize(new Dimension(200, 200));
		contentPane.add(pieDone, "cell 3 5 1 4,grow");
		pieDone.setVisible(true);

		label_1 = new JLabel("Efficienza");
		contentPane.add(label_1, "cell 0 6,alignx center");

		digEffBar = new JProgressBar();
		digEffBar.setForeground(new Color(0, 153, 51));
		digEffBar.setMaximum(200);
		digEffBar.setValue(-100);
		digEffBar.setStringPainted(true);
		contentPane.add(digEffBar, "cell 1 6,grow");

		levelBars.put(new Pair(Constants.DIG,Skill.EFFICIENCY), digEffBar);

		blockEffBar = new JProgressBar();
		blockEffBar.setForeground(new Color(0, 153, 51));
		blockEffBar.setMaximum(200);
		blockEffBar.setValue(-100);
		blockEffBar.setStringPainted(true);
		contentPane.add(blockEffBar, "cell 2 6,grow");

		levelBars.put(new Pair(Constants.BLOCK,Skill.EFFICIENCY), blockEffBar);

		label_2 = new JLabel("Positivita'");
		contentPane.add(label_2, "cell 0 7,alignx center");

		digPosBar = new JProgressBar();
		digPosBar.setForeground(new Color(0, 153, 51));
		digPosBar.setStringPainted(true);
		contentPane.add(digPosBar, "cell 1 7,grow");
		levelBars.put(new Pair(Constants.DIG,Skill.POSITIVITY), digPosBar);

		blockPosBar = new JProgressBar();
		blockPosBar.setForeground(new Color(0, 153, 51));
		blockPosBar.setStringPainted(true);
		contentPane.add(blockPosBar, "cell 2 7,grow");
		levelBars.put(new Pair(Constants.BLOCK,Skill.POSITIVITY), blockPosBar);

		label_3 = new JLabel("Perfezione");
		contentPane.add(label_3, "cell 0 8,alignx center");

		digPerfBar = new JProgressBar();
		digPerfBar.setForeground(new Color(0, 153, 51));
		digPerfBar.setStringPainted(true);
		contentPane.add(digPerfBar, "cell 1 8,grow");
		levelBars.put(new Pair(Constants.DIG,Skill.PERFECTION), digPerfBar);

		blockPerfBar = new JProgressBar();
		blockPerfBar.setForeground(new Color(0, 153, 51));
		blockPerfBar.setStringPainted(true);
		contentPane.add(blockPerfBar, "cell 2 8,grow");
		levelBars.put(new Pair(Constants.BLOCK,Skill.PERFECTION), blockPerfBar);
		pack();
		int side=serveButton.getHeight();
		serveButton.setIcon(new ImageIcon(LotusVBE.img.serve.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		receiveButton.setIcon(new ImageIcon(LotusVBE.img.receive.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		attackButton.setIcon(new ImageIcon(LotusVBE.img.attack.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		digButton.setIcon(new ImageIcon(LotusVBE.img.dig.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		blockButton.setIcon(new ImageIcon(LotusVBE.img.block.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
	}


}
