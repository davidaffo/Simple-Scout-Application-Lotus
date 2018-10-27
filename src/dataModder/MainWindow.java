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
import net.miginfocom.swing.MigLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import common.DatePicker;
import common.Images;
import common.LotusVBE;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.event.PopupMenuListener;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.event.PopupMenuEvent;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

public class MainWindow extends javax.swing.JFrame{

	MainController c;
	ResourceBundle lang = LotusVBE.lang;

	private static final long serialVersionUID = 1L;
	
	String[] fromZones = new String[]{"0","1","2","3","4","5","6"};
	String[] toZones = new String[]{"1","2","3","4","5","6","7"};
	String[] normalZones = new String[]{"1","2","3","4","5","6"};
	String[] skills = new String[]{lang.getString("serve"),lang.getString("receive"),lang.getString("attackSO"), lang.getString("attackBP"),lang.getString("block"),lang.getString("dig")};
	
	
	
	JTextField scoutName;
	JTextField assistName;
	JTextField homeTeam;
	JTextField guestTeam;
	JTextField mvp;
	JComboBox<String> homeResult;
	JComboBox<String> guestResult;
	JButton saveButton;
	JButton updateAllButton;
	JComboBox<String> dataSelector;
	JComboBox<String> playerSelector;
	JComboBox<String> skillSelector;
	JTextField vote0;
	JTextField vote1;
	JTextField vote2;
	JTextField vote3;
	JTextField vote4;
	JLabel lblNewLabel_6;
	JLabel lblNewLabel_7;
	JLabel lblNewLabel_8;
	JLabel lblNewLabel_9;
	JLabel lblNewLabel_10;
	JLabel lblNewLabel_11;
	JLabel lblNewLabel_12;
	JLabel lblNewLabel_13;
	JLabel lblNewLabel_14;
	JComboBox<String> toZoneScore;
	JComboBox<String> fromZoneScore;
	JTextField scoreAttackField;
	JLabel lblNewLabel_15;
	JLabel lblNewLabel_16;
	JComboBox<String> receiveZone;
	JTextField receiveField;
	JLabel lblNewLabel_17;
	JComboBox<String> fromZoneSubAttack;
	JLabel lblNewLabel_18;
	JLabel lblNewLabel_19;
	JComboBox<String> toZoneSubAttack;
	JTextField subAttackField;
	JLabel lblNewLabel_20;
	JLabel lblNewLabel_21;
	JLabel lblNewLabel_22;
	JTextField ourError;
	JLabel lblNewLabel_23;
	JTextField oppError;
	JLabel lblNewLabel_24;
	JComboBox<String> rotationSetSelector;
	JLabel lblNewLabel_25;
	JTextField blocks;
	JProgressBar progress;
	JTextField notify;
	private JButton idModderButton;
	private JTextField pathField;
	private JLabel fileArea;
	JDatePickerImpl datePicker = DatePicker.getDatePicker();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainWindow(MainController c) {
		this.setIconImage(LotusVBE.img.lotusLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.c=c;
		setTitle("SSA Lotus - Data Modder");
		getContentPane().setLayout(new MigLayout("", "[77,grow][77,grow][grow][grow][grow][grow][grow]", "[][][][][][][][25.00][fill][fill][fill][fill][fill][fill][fill][fill]"));
		
		fileArea = new JLabel(lang.getString("dragDrop"));
		fileArea.setHorizontalTextPosition(SwingConstants.CENTER);
		fileArea.setVerticalTextPosition(SwingConstants.TOP);
		getContentPane().add(fileArea, "cell 0 0 2 3,grow");
		fileArea.setDropTarget(new DropTarget() {
			private static final long serialVersionUID = 1L;

			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt
					.getTransferable().getTransferData(
							DataFlavor.javaFileListFlavor);
					File f = droppedFiles.get(0);
					c.filePath = f.getAbsolutePath();
					pathField.setText(c.filePath);
					if(f.isDirectory()){
						updateAllButton.setEnabled(true);
						idModderButton.setEnabled(true);
					}
					else
						c.load();
				} catch (Exception ex) {
					LotusVBE.printError(c.w, lang.getString("notValidFile"));
					ex.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel = new JLabel(lang.getString("scoutName"));
		getContentPane().add(lblNewLabel, "cell 2 0 2 1,alignx trailing");

		scoutName = new JTextField();
		getContentPane().add(scoutName, "cell 4 0 3 1,growx");
		scoutName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(lang.getString("assistName"));
		getContentPane().add(lblNewLabel_1, "cell 2 1 2 1,alignx trailing");

		assistName = new JTextField();
		getContentPane().add(assistName, "cell 4 1 3 1,growx");
		assistName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel(lang.getString("date"));
		getContentPane().add(lblNewLabel_2, "cell 2 2 2 1,alignx trailing");

		updateAllButton = new JButton(lang.getString("updateAll"));
		updateAllButton.setFocusable(false);
		updateAllButton.setEnabled(false);
		updateAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.updateAll();
			}
		});
		getContentPane().add(updateAllButton, "cell 0 3 2 1,grow");

		JLabel lblNewLabel_3 = new JLabel(lang.getString("homeTeam"));
		getContentPane().add(lblNewLabel_3, "cell 2 3 2 1,alignx trailing");

		homeTeam = new JTextField();
		getContentPane().add(homeTeam, "cell 4 3 2 1,growx");
		homeTeam.setColumns(10);

		homeResult = new JComboBox();
		homeResult.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
		getContentPane().add(homeResult, "cell 6 3,growx");
		
		idModderButton = new JButton(lang.getString("idModder"));
		idModderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.w.setEnabled(false);
				new IdModder(c);
			}
		});
		idModderButton.setFocusable(false);
		idModderButton.setEnabled(false);
		getContentPane().add(idModderButton, "cell 0 4 2 1,grow");

		JLabel lblNewLabel_4 = new JLabel(lang.getString("guestTeam"));
		getContentPane().add(lblNewLabel_4, "cell 2 4 2 1,alignx trailing");

		guestTeam = new JTextField();
		getContentPane().add(guestTeam, "cell 4 4 2 1,growx");
		guestTeam.setColumns(10);

		guestResult = new JComboBox();
		guestResult.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
		getContentPane().add(guestResult, "cell 6 4,growx");
		
				saveButton = new JButton(lang.getString("save"));
				saveButton.setFocusable(false);
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						class Task extends SwingWorker<Void, Void> {

							@Override
							protected Void doInBackground() throws Exception {
								c.save();
								return null;
							}
						}

						Task task = new Task();
						//task.addPropertyChangeListener(this);
						task.execute();
					}
				});
				saveButton.setEnabled(false);
				getContentPane().add(saveButton, "cell 0 5 2 1,grow");

		JLabel lblNewLabel_5 = new JLabel("MVP");
		getContentPane().add(lblNewLabel_5, "cell 2 5 2 1,alignx trailing");

		mvp = new JTextField();
		getContentPane().add(mvp, "cell 4 5 3 1,growx");
		mvp.setColumns(10);
		
		pathField = new JTextField();
		pathField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pathField.setEditable(false);
		pathField.setColumns(10);
		getContentPane().add(pathField, "cell 0 6 4 1,grow");
		
		notify = new JTextField();
		notify.setEditable(false);
		getContentPane().add(notify, "cell 4 6 3 1,grow");
		notify.setColumns(10);
		
		progress = new JProgressBar();
		progress.setForeground(new Color(0, 204, 0));
		getContentPane().add(progress, "cell 0 7 7 1,grow");
		
		lblNewLabel_20 = new JLabel(lang.getString("gameSector"));
		getContentPane().add(lblNewLabel_20, "cell 0 8 2 1,alignx center,growy");
		
		dataSelector = new JComboBox(new String[]{"Total","Rotation","Set"});
		dataSelector.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		dataSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(true);
			}
		});
		getContentPane().add(dataSelector, "cell 2 8 2 1,grow");
		
		rotationSetSelector = new JComboBox();
		rotationSetSelector.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		rotationSetSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(rotationSetSelector, "cell 4 8,growx");
		
		lblNewLabel_11 = new JLabel(lang.getString("player"));
		getContentPane().add(lblNewLabel_11, "cell 0 9 2 1,alignx center,growy");
		
		playerSelector = new JComboBox();
		playerSelector.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		playerSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(playerSelector, "cell 2 9 2 1,grow");
		
		lblNewLabel_24 = new JLabel(lang.getString("skill"));
		getContentPane().add(lblNewLabel_24, "cell 4 9,alignx center,growy");
		
		skillSelector = new JComboBox(skills);
		skillSelector.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		skillSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(skillSelector, "cell 5 9 2 1,grow");
		
		lblNewLabel_6 = new JLabel("0 =");
		getContentPane().add(lblNewLabel_6, "cell 0 10 2 1,alignx center,growy");
		
		lblNewLabel_7 = new JLabel("1 -");
		getContentPane().add(lblNewLabel_7, "cell 2 10 2 1,alignx center,growy");
		
		lblNewLabel_8 = new JLabel("2 /");
		getContentPane().add(lblNewLabel_8, "cell 4 10,alignx center,growy");
		
		lblNewLabel_9 = new JLabel("3 +");
		getContentPane().add(lblNewLabel_9, "cell 5 10,alignx center,growy");
		
		lblNewLabel_10 = new JLabel("4 #");
		getContentPane().add(lblNewLabel_10, "cell 6 10,alignx center,growy");
		
		vote0 = new JTextField();
		getContentPane().add(vote0, "cell 0 11 2 1,grow");
		vote0.setColumns(10);
		
		vote1 = new JTextField();
		vote1.setColumns(10);
		getContentPane().add(vote1, "cell 2 11 2 1,grow");
		
		vote2 = new JTextField();
		vote2.setColumns(10);
		getContentPane().add(vote2, "cell 4 11,grow");
		
		vote3 = new JTextField();
		vote3.setColumns(10);
		getContentPane().add(vote3, "cell 5 11,grow");
		
		vote4 = new JTextField();
		vote4.setColumns(10);
		getContentPane().add(vote4, "cell 6 11,grow");
		
		lblNewLabel_12 = new JLabel(lang.getString("scoreAttack"));
		getContentPane().add(lblNewLabel_12, "cell 0 12,alignx center");
		
		lblNewLabel_13 = new JLabel(lang.getString("fromZone"));
		getContentPane().add(lblNewLabel_13, "cell 1 12,alignx trailing");
		
		getContentPane().add(datePicker, "cell 4 2 3 1,growx");
		
		fromZoneScore = new JComboBox(fromZones);
		fromZoneScore.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		fromZoneScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(fromZoneScore, "cell 2 12 2 1,grow");
		
		lblNewLabel_14 = new JLabel(lang.getString("toZone"));
		getContentPane().add(lblNewLabel_14, "cell 4 12,alignx trailing");
		
		toZoneScore = new JComboBox(toZones);
		toZoneScore.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		toZoneScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(toZoneScore, "cell 5 12,growx");
		
		scoreAttackField = new JTextField();
		getContentPane().add(scoreAttackField, "cell 6 12,grow");
		scoreAttackField.setColumns(10);
		
		lblNewLabel_21 = new JLabel(lang.getString("team"));
		getContentPane().add(lblNewLabel_21, "cell 0 13,alignx center");
		
		lblNewLabel_22 = new JLabel(lang.getString("ourError"));
		getContentPane().add(lblNewLabel_22, "cell 1 13,alignx trailing");
		
		ourError = new JTextField();
		getContentPane().add(ourError, "cell 2 13,grow");
		ourError.setColumns(10);
		
		lblNewLabel_23 = new JLabel(lang.getString("oppError"));
		getContentPane().add(lblNewLabel_23, "cell 3 13,alignx trailing");
		
		oppError = new JTextField();
		getContentPane().add(oppError, "cell 4 13,grow");
		oppError.setColumns(10);
		
		lblNewLabel_25 = new JLabel(lang.getString("subBlock"));
		getContentPane().add(lblNewLabel_25, "cell 5 13,alignx trailing");
		
		blocks = new JTextField();
		getContentPane().add(blocks, "cell 6 13,growx");
		blocks.setColumns(10);
		
		lblNewLabel_15 = new JLabel(lang.getString("receiveError"));
		getContentPane().add(lblNewLabel_15, "cell 0 14 2 1,alignx center");
		
		lblNewLabel_16 = new JLabel(lang.getString("zone"));
		getContentPane().add(lblNewLabel_16, "cell 2 14 2 1,alignx right");
		
		receiveZone = new JComboBox(normalZones);
		receiveZone.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		receiveZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(receiveZone, "cell 4 14,grow");
		
		receiveField = new JTextField();
		getContentPane().add(receiveField, "cell 5 14,grow");
		receiveField.setColumns(10);
		
		lblNewLabel_18 = new JLabel(lang.getString("digError"));
		getContentPane().add(lblNewLabel_18, "cell 0 15,alignx center");
		
		lblNewLabel_17 = new JLabel(lang.getString("fromZone"));
		getContentPane().add(lblNewLabel_17, "cell 1 15,alignx trailing");
		
		fromZoneSubAttack = new JComboBox(fromZones);
		fromZoneSubAttack.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		fromZoneSubAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(fromZoneSubAttack, "cell 2 15 2 1,grow");
		
		lblNewLabel_19 = new JLabel(lang.getString("toZone"));
		getContentPane().add(lblNewLabel_19, "cell 4 15,alignx trailing");
		
		toZoneSubAttack = new JComboBox(toZones);
		toZoneSubAttack.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				c.saveInMemoryGame();
			}
		});
		toZoneSubAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.selectorChanged(false);
			}
		});
		getContentPane().add(toZoneSubAttack, "cell 5 15,grow");
		
		subAttackField = new JTextField();
		getContentPane().add(subAttackField, "cell 6 15,grow");
		subAttackField.setColumns(10);
		pack();
		setLocationRelativeTo(null); //window to center
		setSize(new Dimension(669, 464));
		

		fileArea.setIcon(new ImageIcon(LotusVBE.img.dragDrop.getImage().getScaledInstance(fileArea.getHeight()-30, fileArea.getHeight()-30, Image.SCALE_SMOOTH)));

	}

}
