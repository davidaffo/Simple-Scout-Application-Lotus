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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTree;

import common.Constants;
import common.LotusVBE;
import common.Pair;
import data.Skill;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.border.LineBorder;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private MainController c;
	private static final long serialVersionUID = 1L;
	ResourceBundle lang = LotusVBE.lang;
	JTree tree;
	JTextArea gameListArea;
	JTextArea statusArea;
	JProgressBar serveEffBar;
	JProgressBar receiveEffBar;
	JProgressBar servePosBar;
	JProgressBar receivePosBar;
	JProgressBar servePerfBar;
	JProgressBar attackEffBar;
	JProgressBar digEffBar;
	JProgressBar receivePerfBar;
	JButton exportButton;
	JProgressBar attackPerfBar;
	JProgressBar attackPosBar;
	JProgressBar digPosBar;
	JProgressBar digPerfBar;
	JProgressBar blockPerfBar;
	JProgressBar blockPosBar;
	JProgressBar blockEffBar;

	HashMap<Pair, JProgressBar> levelBars = new HashMap<Pair, JProgressBar>();
	JInternalFrame pieDone;
	JInternalFrame pieSub;
	JButton serveButton;
	JButton receiveButton;
	JButton attackButton;
	JButton digButton;
	JButton blockButton;
	JButton oneOutButton;

	/**
	 * Create the frame.
	 */
	public MainWindow(MainController c) {
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				pack();
				setLocationRelativeTo(null); //window to center
			}
		});
		this.setIconImage(LotusVBE.img.lotusLogo);
		UIManager.put("ProgressBar.selectionForeground", Color.black);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		setTitle(lang.getString("analyzerTitle"));
		this.c=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1277, 603);
		JPanel contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(552, 104));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[112.00,grow][220.00,fill][220.00,fill][110,center][110,center][220.00,fill][110,fill][110,fill]", "[26,grow][26,grow][26.00,grow][26,grow][grow][25px,grow,center][25,grow,center][25,grow,center][312.00]"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMaximumSize(new Dimension(512, 104));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
			}
		});
		contentPane.add(scrollPane, "cell 0 0 3 4,grow");
		
		
				JButton loadButton = new JButton(lang.getString("loadDataButton"));
				loadButton.setEnabled(false);
				loadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.loadButton();
					}
				});
				contentPane.add(loadButton, "cell 3 0 2 1,growx");

		gameListArea = new JTextArea();
		gameListArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		gameListArea.setEditable(false);
		gameListArea.setLineWrap(true);
		contentPane.add(gameListArea, "cell 5 0 2 4,grow");



		statusArea = new JTextArea();
		statusArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		statusArea.setEditable(false);
		contentPane.add(statusArea, "cell 7 0 1 4,grow");
		
				exportButton = new JButton(lang.getString("exportDataButton"));
				exportButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.exportData();
					}
				});
				exportButton.setEnabled(false);
				contentPane.add(exportButton, "cell 3 1 2 1,growx");
		
				oneOutButton = new JButton(lang.getString("singlePlayerButton"));
				oneOutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new PlayerController(c);
					}
				});
				oneOutButton.setEnabled(false);
				contentPane.add(oneOutButton, "cell 3 2 2 1,growx");

		JLabel lblNewLabel = new JLabel(lang.getString("skill"));
		contentPane.add(lblNewLabel, "cell 0 4,alignx center,growy");

		serveButton = new JButton(lang.getString("serve"));
		serveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.SERVE);
			}
		});
		serveButton.setEnabled(false);
		contentPane.add(serveButton, "cell 1 4,grow");

		receiveButton = new JButton(lang.getString("receive"));
		receiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.RECEIVE);
			}
		});
		receiveButton.setEnabled(false);
		contentPane.add(receiveButton, "cell 2 4,grow");

		attackButton = new JButton(lang.getString("attack"));
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.BOTH_ATTACKS);
			}
		});
		attackButton.setEnabled(false);
		contentPane.add(attackButton, "cell 3 4 2 1,grow");

		digButton = new JButton(lang.getString("dig"));
		digButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.DIG);
			}
		});
		digButton.setEnabled(false);
		contentPane.add(digButton, "cell 5 4,grow");

		blockButton = new JButton(lang.getString("block"));
		blockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.showDataGraph(Constants.BLOCK);
			}
		});
		blockButton.setEnabled(false);
		contentPane.add(blockButton, "cell 6 4 2 1,grow");

		JLabel lblNewLabel_1 = new JLabel(lang.getString("efficiency"));
		contentPane.add(lblNewLabel_1, "cell 0 5,alignx center,growy");

		serveEffBar = new JProgressBar();
		serveEffBar.setForeground(new Color(0, 153, 51));
		serveEffBar.setMaximum(200);
		serveEffBar.setValue(-100);
		serveEffBar.setStringPainted(true);
		contentPane.add(serveEffBar, "cell 1 5,grow");

		receiveEffBar = new JProgressBar();
		receiveEffBar.setForeground(new Color(0, 153, 51));
		receiveEffBar.setMaximum(200);
		receiveEffBar.setValue(-100);
		receiveEffBar.setStringPainted(true);
		contentPane.add(receiveEffBar, "cell 2 5,grow");

		attackEffBar = new JProgressBar();
		attackEffBar.setForeground(new Color(0, 153, 51));
		attackEffBar.setMaximum(200);
		attackEffBar.setValue(-100);
		attackEffBar.setStringPainted(true);
		contentPane.add(attackEffBar, "cell 3 5 2 1,grow");

		digEffBar = new JProgressBar();
		digEffBar.setForeground(new Color(0, 153, 51));
		digEffBar.setMaximum(200);
		digEffBar.setValue(-100);
		digEffBar.setStringPainted(true);
		contentPane.add(digEffBar, "cell 5 5,grow");

		blockEffBar = new JProgressBar();
		blockEffBar.setForeground(new Color(0, 153, 51));
		blockEffBar.setMaximum(200);
		blockEffBar.setValue(-100);
		blockEffBar.setStringPainted(true);
		contentPane.add(blockEffBar, "cell 6 5 2 1,grow");

		JLabel lblNewLabel_2 = new JLabel(lang.getString("positivity"));
		contentPane.add(lblNewLabel_2, "cell 0 6,alignx center,growy");

		servePosBar = new JProgressBar();
		servePosBar.setForeground(new Color(0, 153, 51));
		servePosBar.setStringPainted(true);
		contentPane.add(servePosBar, "cell 1 6,grow");

		receivePosBar = new JProgressBar();
		receivePosBar.setForeground(new Color(0, 153, 51));
		receivePosBar.setStringPainted(true);
		contentPane.add(receivePosBar, "cell 2 6,grow");

		attackPosBar = new JProgressBar();
		attackPosBar.setForeground(new Color(0, 153, 51));
		attackPosBar.setStringPainted(true);
		contentPane.add(attackPosBar, "cell 3 6 2 1,grow");

		digPosBar = new JProgressBar();
		digPosBar.setForeground(new Color(0, 153, 51));
		digPosBar.setStringPainted(true);
		contentPane.add(digPosBar, "cell 5 6,grow");

		blockPosBar = new JProgressBar();
		blockPosBar.setForeground(new Color(0, 153, 51));
		blockPosBar.setStringPainted(true);
		contentPane.add(blockPosBar, "cell 6 6 2 1,grow");

		JLabel lblNewLabel_3 = new JLabel(lang.getString("perfection"));
		contentPane.add(lblNewLabel_3, "cell 0 7,alignx center,growy");

		servePerfBar = new JProgressBar();
		servePerfBar.setForeground(new Color(0, 153, 51));
		servePerfBar.setStringPainted(true);
		contentPane.add(servePerfBar, "cell 1 7,grow");

		receivePerfBar = new JProgressBar();
		receivePerfBar.setForeground(new Color(0, 153, 51));
		receivePerfBar.setStringPainted(true);
		contentPane.add(receivePerfBar, "cell 2 7,grow");

		attackPerfBar = new JProgressBar();
		attackPerfBar.setForeground(new Color(0, 153, 51));
		attackPerfBar.setStringPainted(true);
		contentPane.add(attackPerfBar, "cell 3 7 2 1,grow");

		digPerfBar = new JProgressBar();
		digPerfBar.setForeground(new Color(0, 153, 51));
		digPerfBar.setStringPainted(true);
		contentPane.add(digPerfBar, "cell 5 7,grow");

		File fileRoot = new File(Constants.ROOT + "Games");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (tree.isSelectionEmpty()){
					loadButton.setEnabled(false);
				}
				else{
					loadButton.setEnabled(true);
					
				}
			}
		});
		tree.setShowsRootHandles(true);
		CreateChildNodes ccn = 
				new CreateChildNodes(fileRoot, root);
		new Thread(ccn).start();
		scrollPane.setViewportView(tree);

		blockPerfBar = new JProgressBar();
		blockPerfBar.setForeground(new Color(0, 153, 51));
		blockPerfBar.setStringPainted(true);
		contentPane.add(blockPerfBar, "cell 6 7 2 1,grow");

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

		levelBars.put(new Pair(Constants.DIG,Skill.EFFICIENCY), digEffBar);
		levelBars.put(new Pair(Constants.DIG,Skill.POSITIVITY), digPosBar);
		levelBars.put(new Pair(Constants.DIG,Skill.PERFECTION), digPerfBar);

		levelBars.put(new Pair(Constants.BLOCK,Skill.EFFICIENCY), blockEffBar);
		levelBars.put(new Pair(Constants.BLOCK,Skill.POSITIVITY), blockPosBar);
		levelBars.put(new Pair(Constants.BLOCK,Skill.PERFECTION), blockPerfBar);
		
		pieDone = new JInternalFrame(lang.getString("donePoints"));
		pieDone.setPreferredSize(new Dimension(550, 312));
		contentPane.add(pieDone, "cell 1 8 3 1,grow");
		pieDone.pack();
		pieDone.setVisible(true);
		
		pieSub = new JInternalFrame(lang.getString("sufferedPoints"));
		pieSub.setPreferredSize(new Dimension(550, 312));
		contentPane.add(pieSub, "cell 4 8 4 1,grow");
		pieSub.pack();
		pieSub.setVisible(true);
		pack();
		int side=serveButton.getHeight();
		serveButton.setIcon(new ImageIcon(LotusVBE.img.serve.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		receiveButton.setIcon(new ImageIcon(LotusVBE.img.receive.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		attackButton.setIcon(new ImageIcon(LotusVBE.img.attack.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		digButton.setIcon(new ImageIcon(LotusVBE.img.dig.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		blockButton.setIcon(new ImageIcon(LotusVBE.img.block.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		side = loadButton.getHeight();
		loadButton.setIcon(new ImageIcon(LotusVBE.img.upload.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		exportButton.setIcon(new ImageIcon(LotusVBE.img.export.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		oneOutButton.setIcon(new ImageIcon(LotusVBE.img.sheet.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
	}

	public class CreateChildNodes implements Runnable {

		private DefaultMutableTreeNode root;

		private File fileRoot;

		public CreateChildNodes(File fileRoot, 
				DefaultMutableTreeNode root) {
			this.fileRoot = fileRoot;
			this.root = root;
		}

		@Override
		public void run() {
			createChildren(fileRoot, root);
		}

		private void createChildren(File fileRoot, 
				DefaultMutableTreeNode node) {
			File[] files = fileRoot.listFiles();
			if (files == null) return;

			for (File file : files) {
				DefaultMutableTreeNode childNode = 
						new DefaultMutableTreeNode(new FileNode(file));
				node.add(childNode);
				if (file.isDirectory()) {
					createChildren(file, childNode);
				}
			}
		}

	}

	public class FileNode {

		private File file;

		public FileNode(File file) {
			this.file = file;
		}

		@Override
		public String toString() {
			String name = file.getName();
			if (name.equals("")) {
				return file.getAbsolutePath();
			} else {
				return name;
			}
		}
	}

}
