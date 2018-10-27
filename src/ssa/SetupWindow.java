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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssa;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.util.Scanner;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.LotusVBE;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Davide
 */
public class SetupWindow extends javax.swing.JFrame {

	private SetupController c;
	private static final long serialVersionUID = 1L;
    static DefaultListModel<String> model;
    static DefaultListModel<String> pmodel;
    static Scanner scan;
    static String reader;
    private static ResourceBundle lang;
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton addPlayer;
    private javax.swing.JButton dbAdd;
    javax.swing.JButton dbDelete;
    javax.swing.JButton dbEdit;
    javax.swing.JTextField dbId;
    javax.swing.JTextField dbLastname;
    javax.swing.JList<String> dbList;
    javax.swing.JTextField dbName;
    javax.swing.JButton deleteTeam;
    javax.swing.JTextField id1;
    javax.swing.JTextField id10;
    javax.swing.JTextField id11;
    javax.swing.JTextField id12;
    javax.swing.JTextField id13;
    javax.swing.JTextField id2;
    javax.swing.JTextField id3;
    javax.swing.JTextField id4;
    javax.swing.JTextField id5;
    javax.swing.JTextField id6;
    javax.swing.JTextField id7;
    javax.swing.JTextField id8;
    javax.swing.JTextField id9;
    javax.swing.JButton integrityButton;
    javax.swing.JTextField integrityStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newTeam;
    javax.swing.JTextField playerName1;
    javax.swing.JTextField playerName10;
    javax.swing.JTextField playerName11;
    javax.swing.JTextField playerName12;
    javax.swing.JTextField playerName13;
    javax.swing.JTextField playerName2;
    javax.swing.JTextField playerName3;
    javax.swing.JTextField playerName4;
    javax.swing.JTextField playerName5;
    javax.swing.JTextField playerName6;
    javax.swing.JTextField playerName7;
    javax.swing.JTextField playerName8;
    javax.swing.JTextField playerName9;
    javax.swing.JTextField playerNumber1;
    javax.swing.JTextField playerNumber10;
    javax.swing.JTextField playerNumber11;
    javax.swing.JTextField playerNumber12;
    javax.swing.JTextField playerNumber13;
    javax.swing.JTextField playerNumber2;
    javax.swing.JTextField playerNumber3;
    javax.swing.JTextField playerNumber4;
    javax.swing.JTextField playerNumber5;
    javax.swing.JTextField playerNumber6;
    javax.swing.JTextField playerNumber7;
    javax.swing.JTextField playerNumber8;
    javax.swing.JTextField playerNumber9;
    javax.swing.JButton remove1;
    javax.swing.JButton remove10;
    javax.swing.JButton remove11;
    javax.swing.JButton remove12;
    javax.swing.JButton remove13;
    javax.swing.JButton remove2;
    javax.swing.JButton remove3;
    javax.swing.JButton remove4;
    javax.swing.JButton remove5;
    javax.swing.JButton remove6;
    javax.swing.JButton remove7;
    javax.swing.JButton remove8;
    javax.swing.JButton remove9;
    javax.swing.JButton renameTeam;
    javax.swing.JButton resetButton;
    private javax.swing.JTextField search;
    javax.swing.JButton startButton;
    javax.swing.JList<String> teamList;
    javax.swing.JLabel versionLabel;
    JButton copyTeam;
    

    
    /**
     * Creates new form SetupWindow
     */
    public SetupWindow(SetupController c) throws FileNotFoundException {
    	this.setIconImage(LotusVBE.img.lotusLogo);
    	this.c = c;
    	lang=SetupController.lang;


        jScrollPane1 = new javax.swing.JScrollPane();
        teamList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        playerName1 = new javax.swing.JTextField();
        playerNumber1 = new javax.swing.JTextField();
        playerName3 = new javax.swing.JTextField();
        playerNumber3 = new javax.swing.JTextField();
        playerName5 = new javax.swing.JTextField();
        playerNumber5 = new javax.swing.JTextField();
        playerName7 = new javax.swing.JTextField();
        playerNumber7 = new javax.swing.JTextField();
        playerName9 = new javax.swing.JTextField();
        playerNumber9 = new javax.swing.JTextField();
        playerName11 = new javax.swing.JTextField();
        playerNumber11 = new javax.swing.JTextField();
        playerName2 = new javax.swing.JTextField();
        playerNumber2 = new javax.swing.JTextField();
        playerName4 = new javax.swing.JTextField();
        playerNumber4 = new javax.swing.JTextField();
        playerName6 = new javax.swing.JTextField();
        playerNumber6 = new javax.swing.JTextField();
        playerName8 = new javax.swing.JTextField();
        playerNumber8 = new javax.swing.JTextField();
        playerName10 = new javax.swing.JTextField();
        playerNumber10 = new javax.swing.JTextField();
        newTeam = new javax.swing.JButton();
        deleteTeam = new javax.swing.JButton();
        renameTeam = new javax.swing.JButton();
        id1 = new javax.swing.JTextField();
        id2 = new javax.swing.JTextField();
        id3 = new javax.swing.JTextField();
        id4 = new javax.swing.JTextField();
        id5 = new javax.swing.JTextField();
        id6 = new javax.swing.JTextField();
        id7 = new javax.swing.JTextField();
        id8 = new javax.swing.JTextField();
        id9 = new javax.swing.JTextField();
        id10 = new javax.swing.JTextField();
        id11 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        remove1 = new javax.swing.JButton();
        remove1.setContentAreaFilled(false);
        remove1.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove1.setAlignmentY(0.0f);
        remove1.setIconTextGap(0);
        remove1.setMargin(new Insets(0, 0, 0, 0));
        remove2 = new javax.swing.JButton();
        remove2.setContentAreaFilled(false);
        remove2.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove2.setAlignmentY(0.0f);
        remove2.setIconTextGap(0);
        remove2.setMargin(new Insets(0, 0, 0, 0));
        remove3 = new javax.swing.JButton();
        remove3.setContentAreaFilled(false);
        remove3.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove3.setAlignmentY(0.0f);
        remove3.setIconTextGap(0);
        remove3.setMargin(new Insets(0, 0, 0, 0));
        remove4 = new javax.swing.JButton();
        remove4.setContentAreaFilled(false);
        remove4.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove4.setAlignmentY(0.0f);
        remove4.setIconTextGap(0);
        remove4.setMargin(new Insets(0, 0, 0, 0));
        remove5 = new javax.swing.JButton();
        remove5.setContentAreaFilled(false);
        remove5.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove5.setAlignmentY(0.0f);
        remove5.setIconTextGap(0);
        remove5.setMargin(new Insets(0, 0, 0, 0));
        remove6 = new javax.swing.JButton();
        remove6.setContentAreaFilled(false);
        remove6.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove6.setAlignmentY(0.0f);
        remove6.setIconTextGap(0);
        remove6.setMargin(new Insets(0, 0, 0, 0));
        remove7 = new javax.swing.JButton();
        remove7.setContentAreaFilled(false);
        remove7.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove7.setAlignmentY(0.0f);
        remove7.setIconTextGap(0);
        remove7.setMargin(new Insets(0, 0, 0, 0));
        remove8 = new javax.swing.JButton();
        remove8.setContentAreaFilled(false);
        remove8.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove8.setAlignmentY(0.0f);
        remove8.setIconTextGap(0);
        remove8.setMargin(new Insets(0, 0, 0, 0));
        remove9 = new javax.swing.JButton();
        remove9.setContentAreaFilled(false);
        remove9.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove9.setAlignmentY(0.0f);
        remove9.setIconTextGap(0);
        remove9.setMargin(new Insets(0, 0, 0, 0));
        remove10 = new javax.swing.JButton();
        remove10.setContentAreaFilled(false);
        remove10.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove10.setAlignmentY(0.0f);
        remove10.setIconTextGap(0);
        remove10.setMargin(new Insets(0, 0, 0, 0));
        remove11 = new javax.swing.JButton();
        remove11.setContentAreaFilled(false);
        remove11.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove11.setAlignmentY(0.0f);
        remove11.setIconTextGap(0);
        remove11.setMargin(new Insets(0, 0, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(lang.getString("setupTitle"));

        jScrollPane1.setFocusable(false);

        teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teamList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                teamListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(teamList);

        jLabel1.setText(lang.getString("selectTeam"));
        jLabel1.setFocusable(false);

        jLabel3.setText(lang.getString("id"));
        jLabel3.setFocusable(false);

        playerName1.setEditable(false);

        playerNumber1.setEditable(false);

        playerName3.setEditable(false);

        playerNumber3.setEditable(false);

        playerName5.setEditable(false);

        playerNumber5.setEditable(false);

        playerName7.setEditable(false);

        playerNumber7.setEditable(false);

        playerName9.setEditable(false);

        playerNumber9.setEditable(false);

        playerName11.setEditable(false);

        playerNumber11.setEditable(false);

        playerName2.setEditable(false);

        playerNumber2.setEditable(false);

        playerName4.setEditable(false);

        playerNumber4.setEditable(false);

        playerName6.setEditable(false);

        playerNumber6.setEditable(false);

        playerName8.setEditable(false);

        playerNumber8.setEditable(false);

        playerName10.setEditable(false);

        playerNumber10.setEditable(false);

        newTeam.setText(lang.getString("new"));
        newTeam.setFocusable(false);
        newTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTeamActionPerformed(evt);
            }
        });

        deleteTeam.setText(lang.getString("delete"));
        deleteTeam.setEnabled(false);
        deleteTeam.setFocusable(false);
        deleteTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeamActionPerformed(evt);
            }
        });

        renameTeam.setText(lang.getString("renameTeam"));
        renameTeam.setEnabled(false);
        renameTeam.setFocusable(false);
        renameTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameTeamActionPerformed(evt);
            }
        });

        id1.setEditable(false);
        id1.setFocusable(false);

        id2.setEditable(false);
        id2.setFocusable(false);

        id3.setEditable(false);
        id3.setFocusable(false);

        id4.setEditable(false);
        id4.setFocusable(false);

        id5.setEditable(false);
        id5.setFocusable(false);

        id6.setEditable(false);
        id6.setFocusable(false);

        id7.setEditable(false);
        id7.setFocusable(false);

        id8.setEditable(false);
        id8.setFocusable(false);

        id9.setEditable(false);
        id9.setFocusable(false);

        id10.setEditable(false);
        id10.setFocusable(false);

        id11.setEditable(false);
        id11.setFocusable(false);

        jLabel4.setText("Nickname:");
        jLabel4.setFocusable(false);

        jLabel6.setText("N°:");
        jLabel6.setFocusable(false);

        //remove1.setText(lang.getString("removePlayer"));
        remove1.setFocusable(false);
        remove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove1ActionPerformed(evt);
            }
        });
        remove2.setFocusable(false);
        remove2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove2ActionPerformed(evt);
            }
        });
        remove3.setFocusable(false);
        remove3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove3ActionPerformed(evt);
            }
        });
        remove4.setFocusable(false);
        remove4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove4ActionPerformed(evt);
            }
        });
        remove5.setFocusable(false);
        remove5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove5ActionPerformed(evt);
            }
        });
        remove6.setFocusable(false);
        remove6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove6ActionPerformed(evt);
            }
        });
        remove7.setFocusable(false);
        remove7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove7ActionPerformed(evt);
            }
        });
        remove8.setFocusable(false);
        remove8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove8ActionPerformed(evt);
            }
        });
        remove9.setFocusable(false);
        remove9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove9ActionPerformed(evt);
            }
        });
        remove10.setFocusable(false);
        remove10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove10ActionPerformed(evt);
            }
        });
        remove11.setFocusable(false);
        remove11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove11ActionPerformed(evt);
            }
        });
        
        copyTeam = new JButton();
        copyTeam.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.copyTeam();
        	}
        });
        copyTeam.setText(lang.getString("copy"));
        copyTeam.setFocusable(false);
        copyTeam.setEnabled(false);
        getContentPane().setLayout(new MigLayout("", "[39.00,grow,fill][231.00,grow,fill][207px,grow,fill][180px,grow,fill][95px,grow,fill][34.00px,grow,fill][25,fill]", "[25,grow,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][24,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,grow,fill][25,grow,fill][25,grow][25,grow]"));
        getContentPane().add(jScrollPane1, "cell 0 1 2 6,grow");
        getContentPane().add(jLabel1, "cell 0 0 2 1,growx,aligny top");
        jScrollPane2 = new javax.swing.JScrollPane();
        dbList = new javax.swing.JList<>();
        dbList.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		switch(e.getKeyCode()){
        		case KeyEvent.VK_UP:
        			dbList.setSelectedIndex(dbList.getSelectedIndex()-1);
        			break;
        		case KeyEvent.VK_DOWN:
        			dbList.setSelectedIndex(dbList.getSelectedIndex()+1);
        			break;
        		}
        	}
        });
        dbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
                jScrollPane2.setFocusable(false);
                
                        dbList.setFocusable(false);
                        dbList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                                dbListValueChanged(evt);
                            }
                        });
                                jLabel8 = new javax.swing.JLabel();
                                
                                        jLabel8.setText(lang.getString("playerDB"));
                                        jLabel8.setFocusable(false);
                                        getContentPane().add(jLabel8, "cell 0 7 2 1,alignx left,aligny bottom");
                                jLabel7 = new javax.swing.JLabel();
                                
                                        jLabel7.setText(lang.getString("search"));
                                        jLabel7.setFocusable(false);
                                        getContentPane().add(jLabel7, "cell 0 8,alignx left,aligny bottom");
                        setSearch(new javax.swing.JTextField());
                        
                                getSearch().addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        searchKeyReleased(evt);
                                    }
                                	@Override
                                	public void keyPressed(KeyEvent e) {
                                		switch(e.getKeyCode()){
                                		case KeyEvent.VK_UP:
                                			dbList.setSelectedIndex(dbList.getSelectedIndex()-1);
                                			break;
                                		case KeyEvent.VK_DOWN:
                                			dbList.setSelectedIndex(dbList.getSelectedIndex()+1);
                                			break;
                                		}
                                	}
                                });
                                getContentPane().add(search, "cell 1 8,growx,aligny bottom");
                        addPlayer = new javax.swing.JButton();
                        
                                addPlayer.setText(lang.getString("addPlayer"));
                                addPlayer.setEnabled(false);
                                addPlayer.setFocusable(false);
                                addPlayer.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        addPlayerActionPerformed(evt);
                                    }
                                });
                                getContentPane().add(addPlayer, "cell 2 8,growx,aligny bottom");
                        jScrollPane2.setViewportView(dbList);
                        getContentPane().add(jScrollPane2, "cell 0 9 2 9,grow");
        jLabel9 = new javax.swing.JLabel();
        
                jLabel9.setText("ID:");
                jLabel9.setFocusable(false);
                getContentPane().add(jLabel9, "cell 2 9,alignx left,aligny center");
        dbId = new javax.swing.JTextField();
        
                dbId.setEditable(false);
                dbId.setFocusable(false);
                getContentPane().add(dbId, "cell 2 10,growx,aligny top");
        jLabel10 = new javax.swing.JLabel();
        
                jLabel10.setText(lang.getString("lastname"));
                jLabel10.setFocusable(false);
                getContentPane().add(jLabel10, "cell 2 11,alignx left,aligny top");
        dbLastname = new javax.swing.JTextField();
        
                dbLastname.setEditable(false);
                dbLastname.setFocusable(false);
                getContentPane().add(dbLastname, "cell 2 12,growx,aligny top");
        id12 = new javax.swing.JTextField();
        
                id12.setEditable(false);
                id12.setFocusable(false);
                getContentPane().add(id12, "cell 3 12,grow");
        playerName12 = new javax.swing.JTextField();
        
                playerName12.setEditable(false);
                getContentPane().add(playerName12, "cell 4 12,grow");
        playerNumber12 = new javax.swing.JTextField();
        
                playerNumber12.setEditable(false);
                getContentPane().add(playerNumber12, "cell 5 12,grow");
        remove12 = new javax.swing.JButton();
        remove12.setContentAreaFilled(false);
        remove12.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove12.setAlignmentY(0.0f);
        remove12.setIconTextGap(0);
        remove12.setMargin(new Insets(0, 0, 0, 0));
                remove12.setFocusable(false);
                remove12.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        remove12ActionPerformed(evt);
                    }
                });
                getContentPane().add(remove12, "cell 6 12,alignx left,aligny top");
        jLabel11 = new javax.swing.JLabel();
        
                jLabel11.setText(lang.getString("name"));
                jLabel11.setFocusable(false);
                getContentPane().add(jLabel11, "cell 2 13,alignx left,aligny top");
        id13 = new javax.swing.JTextField();
        
                id13.setEditable(false);
                id13.setFocusable(false);
                getContentPane().add(id13, "cell 3 13,grow");
        playerName13 = new javax.swing.JTextField();
        
                playerName13.setEditable(false);
                getContentPane().add(playerName13, "cell 4 13,grow");
        playerNumber13 = new javax.swing.JTextField();
        
                playerNumber13.setEditable(false);
                getContentPane().add(playerNumber13, "cell 5 13,grow");
        remove13 = new javax.swing.JButton();
        remove13.setContentAreaFilled(false);
        remove13.setBorder(new EmptyBorder(0, 0, 0, 0));
        remove13.setAlignmentY(0.0f);
        remove13.setIconTextGap(0);
        remove13.setMargin(new Insets(0, 0, 0, 0));
                remove13.setFocusable(false);
                remove13.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        remove13ActionPerformed(evt);
                    }
                });
                getContentPane().add(remove13, "cell 6 13,alignx left,aligny top");
                dbName = new javax.swing.JTextField();
                
                        dbName.setEditable(false);
                        dbName.setFocusable(false);
                        getContentPane().add(dbName, "cell 2 14,growx,aligny top");
                jLabel5 = new javax.swing.JLabel();
                
                        jLabel5.setText("Author: Daffonchio Davide");
                        jLabel5.setFocusable(false);
                        getContentPane().add(jLabel5, "cell 3 14,alignx left,aligny top");
                dbAdd = new javax.swing.JButton();
                
                        dbAdd.setText(lang.getString("createPlayer"));
                        dbAdd.setFocusable(false);
                        dbAdd.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                dbAddActionPerformed(evt);
                            }
                        });
                        integrityButton = new javax.swing.JButton();
                        
                                integrityButton.setText("Check & Save");
                                integrityButton.setEnabled(false);
                                integrityButton.setFocusable(false);
                                integrityButton.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        try {
					integrityButtonActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
                                    }
                                });
                                getContentPane().add(integrityButton, "cell 4 14 3 2,growx,aligny bottom");
                        getContentPane().add(dbAdd, "cell 2 15,growx,aligny top");
                dbEdit = new javax.swing.JButton();
                
                        dbEdit.setText(lang.getString("modify"));
                        dbEdit.setEnabled(false);
                        dbEdit.setFocusable(false);
                        dbEdit.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                dbEditActionPerformed(evt);
                            }
                        });
                                versionLabel = new javax.swing.JLabel(lang.getString("version")+LotusVBE.version);
                                versionLabel.setFocusable(false);
                                getContentPane().add(versionLabel, "cell 3 15,alignx left,aligny top");
                        getContentPane().add(dbEdit, "cell 2 16,growx,aligny top");
        getContentPane().add(renameTeam, "cell 2 3,alignx left,aligny top");
        getContentPane().add(deleteTeam, "cell 2 2,alignx left,aligny top");
        getContentPane().add(newTeam, "cell 2 1,alignx left,aligny top");
        getContentPane().add(copyTeam, "cell 2 4,alignx left,aligny top");
        getContentPane().add(id10, "cell 3 10,grow");
        getContentPane().add(id6, "cell 3 6,grow");
        getContentPane().add(id4, "cell 3 4,grow");
        getContentPane().add(id5, "cell 3 5,grow");
        getContentPane().add(id3, "cell 3 3,grow");
        getContentPane().add(id2, "cell 3 2,grow");
        getContentPane().add(id9, "cell 3 9,growx,aligny top");
        getContentPane().add(id8, "cell 3 8,growx,aligny top");
        getContentPane().add(id11, "cell 3 11,growx,aligny bottom");
        getContentPane().add(id1, "cell 3 1,grow");
        getContentPane().add(id7, "cell 3 7,growx,aligny top");
        getContentPane().add(jLabel3, "cell 3 0,alignx left,aligny top");
        getContentPane().add(jLabel4, "cell 4 0,alignx left,aligny top");
        getContentPane().add(playerName1, "cell 4 1,grow");
        getContentPane().add(playerName7, "cell 4 7,growx,aligny top");
        getContentPane().add(playerName9, "cell 4 9,growx,aligny top");
        getContentPane().add(playerName11, "cell 4 11,growx,aligny bottom");
        getContentPane().add(playerName3, "cell 4 3,grow");
        getContentPane().add(playerName2, "cell 4 2,grow");
        getContentPane().add(playerName6, "cell 4 6,grow");
        getContentPane().add(playerName8, "cell 4 8,growx,aligny top");
        getContentPane().add(playerName10, "cell 4 10,grow");
        getContentPane().add(playerName4, "cell 4 4,grow");
        getContentPane().add(playerName5, "cell 4 5,grow");
        getContentPane().add(jLabel6, "cell 5 0,growx,aligny top");
        getContentPane().add(playerNumber2, "cell 5 2,grow");
        getContentPane().add(playerNumber1, "cell 5 1,grow");
        getContentPane().add(playerNumber3, "cell 5 3,grow");
        getContentPane().add(playerNumber4, "cell 5 4,grow");
        getContentPane().add(playerNumber5, "cell 5 5,grow");
        getContentPane().add(playerNumber6, "cell 5 6,grow");
        getContentPane().add(playerNumber7, "cell 5 7,growx,aligny top");
        getContentPane().add(playerNumber8, "cell 5 8,growx,aligny top");
        getContentPane().add(playerNumber9, "cell 5 9,growx,aligny top");
        getContentPane().add(playerNumber10, "cell 5 10,grow");
        getContentPane().add(playerNumber11, "cell 5 11,growx,aligny bottom");
        getContentPane().add(remove10, "cell 6 10,alignx left,aligny top");
        getContentPane().add(remove9, "cell 6 9,alignx left,aligny top");
        getContentPane().add(remove8, "cell 6 8,alignx left,aligny top");
        getContentPane().add(remove7, "cell 6 7,alignx left,aligny top");
        getContentPane().add(remove6, "cell 6 6,alignx left,aligny top");
        getContentPane().add(remove5, "cell 6 5,alignx left,aligny top");
        getContentPane().add(remove4, "cell 6 4,alignx left,aligny top");
        getContentPane().add(remove3, "cell 6 3,alignx left,aligny top");
        getContentPane().add(remove1, "cell 6 1,alignx left,aligny top");
        getContentPane().add(remove2, "cell 6 2,alignx left,aligny top");
        getContentPane().add(remove11, "cell 6 11,alignx left,aligny bottom");
        dbDelete = new javax.swing.JButton();
        
                dbDelete.setText(lang.getString("delete"));
                dbDelete.setEnabled(false);
                dbDelete.setFocusable(false);
                dbDelete.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        dbDeleteActionPerformed(evt);
                    }
                });
                resetButton = new javax.swing.JButton();
                
                        resetButton.setText("Reset");
                        resetButton.setEnabled(false);
                        resetButton.setFocusable(false);
                        resetButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                resetButtonActionPerformed(evt);
                            }
                        });
                        getContentPane().add(resetButton, "cell 3 16 1 2,growx,aligny bottom");
                startButton = new javax.swing.JButton();
                
                        startButton.setText("Start");
                        startButton.setEnabled(false);
                        startButton.setFocusable(false);
                        startButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                startButtonActionPerformed(evt);
                            }
                        });
                        getContentPane().add(startButton, "cell 4 16 3 2,growx,aligny bottom");
                getContentPane().add(dbDelete, "cell 2 17,growx,aligny top");
                integrityStatus = new javax.swing.JTextField();
                
                        integrityStatus.setEditable(false);
                        integrityStatus.setText(SetupController.lang.getString("welcome"));
                        integrityStatus.setFocusable(false);
                        getContentPane().add(integrityStatus, "cell 0 18 7 1,growx,aligny top");

        pack();
        setLocationRelativeTo(null); //window to center
    
    	this.setPreferredSize(new Dimension(906,579));
    	getSearch().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                c.dynamicSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                c.dynamicSearch();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                c.dynamicSearch();
            }
        }
        );

        /*Image audax = imageIcon.getImage(); // transform it 
        Image newimg = audax.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        imageLabel.setIcon(common.Icons.lotusLogo);*/
    	
    	JButton[] removeButton = new JButton[]{remove1, remove2, remove3, remove4, remove5, remove6, remove7, remove8, remove9, remove10, remove11, remove12, remove13};

    	//setting icons
        this.setIconImage(LotusVBE.img.lotusLogo);
        for(JButton b:removeButton){
        	b.setIcon(new ImageIcon(LotusVBE.img.removeButton.getImage().getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH)));
            b.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseEntered(MouseEvent e) {
            		b.setIcon(new ImageIcon(LotusVBE.img.removeButtonHover.getImage().getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH)));
            	}
            	@Override
            	public void mouseExited(MouseEvent e) {
            		b.setIcon(new ImageIcon(LotusVBE.img.removeButton.getImage().getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH)));
            	}
            	@Override
            	public void mousePressed(MouseEvent e) {
            		b.setIcon(new ImageIcon(LotusVBE.img.removeButtonClicked.getImage().getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH)));
            	}
            	@Override
            	public void mouseReleased(MouseEvent e) {
            		b.setIcon(new ImageIcon(LotusVBE.img.removeButton.getImage().getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH)));
            	}
            	
            });
        }
        
        newTeam.setIcon(new ImageIcon(LotusVBE.img.newTeam.getImage().getScaledInstance(newTeam.getHeight()-10, newTeam.getHeight()-10, Image.SCALE_SMOOTH)));
        deleteTeam.setIcon(new ImageIcon(LotusVBE.img.removeButton.getImage().getScaledInstance(deleteTeam.getHeight()-10, deleteTeam.getHeight()-10, Image.SCALE_SMOOTH)));
        renameTeam.setIcon(new ImageIcon(LotusVBE.img.rename.getImage().getScaledInstance(renameTeam.getHeight()-10, renameTeam.getHeight()-10, Image.SCALE_SMOOTH)));
        copyTeam.setIcon(new ImageIcon(LotusVBE.img.copy.getImage().getScaledInstance(copyTeam.getHeight()-10, copyTeam.getHeight()-10, Image.SCALE_SMOOTH)));
        addPlayer.setIcon(new ImageIcon(LotusVBE.img.addToFormation.getImage().getScaledInstance(addPlayer.getHeight()-10, addPlayer.getHeight()-10, Image.SCALE_SMOOTH)));
        startButton.setIcon(new ImageIcon(LotusVBE.img.start.getImage().getScaledInstance(startButton.getHeight()-10, startButton.getHeight()-10, Image.SCALE_SMOOTH)));
        dbEdit.setIcon(new ImageIcon(LotusVBE.img.rename.getImage().getScaledInstance(dbEdit.getHeight()-10, dbEdit.getHeight()-10, Image.SCALE_SMOOTH)));
        dbDelete.setIcon(new ImageIcon(LotusVBE.img.removeButton.getImage().getScaledInstance(dbDelete.getHeight()-10, dbDelete.getHeight()-10, Image.SCALE_SMOOTH)));
        dbAdd.setIcon(new ImageIcon(LotusVBE.img.newTeam.getImage().getScaledInstance(dbAdd.getHeight()-10, dbAdd.getHeight()-10, Image.SCALE_SMOOTH)));
        resetButton.setIcon(new ImageIcon(LotusVBE.img.reset.getImage().getScaledInstance(resetButton.getHeight()-10, resetButton.getHeight()-10, Image.SCALE_SMOOTH)));
        integrityButton.setIcon(new ImageIcon(LotusVBE.img.checked.getImage().getScaledInstance(integrityButton.getHeight()-10, integrityButton.getHeight()-10, Image.SCALE_SMOOTH)));
        
        


    }

    private void integrityButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_integrityButtonActionPerformed
        c.integrityButton(teamList.getSelectedIndex());

    }//GEN-LAST:event_integrityButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        c.startButton();
    }//GEN-LAST:event_startButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        c.resetButton();
        


    }//GEN-LAST:event_resetButtonActionPerformed


    private void teamListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_teamListValueChanged

		if (!evt.getValueIsAdjusting()) {
    	c.teamSelected();
		}
        
    }//GEN-LAST:event_teamListValueChanged

    private void newTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTeamActionPerformed
        c.newTeam();

    }//GEN-LAST:event_newTeamActionPerformed

    private void deleteTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTeamActionPerformed
        c.deleteTeam();
        }
//GEN-LAST:event_deleteTeamActionPerformed

    private void renameTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameTeamActionPerformed
        c.renameTeam();
    }//GEN-LAST:event_renameTeamActionPerformed


    private void addPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerActionPerformed
        c.addPlayer();
        this.search.setText("");
    }//GEN-LAST:event_dbAddActionPerformed

    private void dbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbDeleteActionPerformed
        try {
			c.deletePlayer();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }//GEN-LAST:event_dbDeleteActionPerformed

    private void dbEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbEditActionPerformed
        c.editPlayer();
    }//GEN-LAST:event_dbEditActionPerformed

    private void remove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove1ActionPerformed
        c.removePlayer(0);
        
    }//GEN-LAST:event_remove1ActionPerformed

    private void remove2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove2ActionPerformed
        c.removePlayer(1);
    }//GEN-LAST:event_remove2ActionPerformed

    private void remove3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove3ActionPerformed
        c.removePlayer(2);
    }//GEN-LAST:event_remove3ActionPerformed

    private void remove4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove4ActionPerformed
        c.removePlayer(3);
    }//GEN-LAST:event_remove4ActionPerformed

    private void remove5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove5ActionPerformed
        c.removePlayer(4);
    }//GEN-LAST:event_remove5ActionPerformed

    private void remove6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove6ActionPerformed
        c.removePlayer(5);
    }//GEN-LAST:event_remove6ActionPerformed

    private void remove7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove7ActionPerformed
        c.removePlayer(6);
    }//GEN-LAST:event_remove7ActionPerformed

    private void remove8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove8ActionPerformed
        c.removePlayer(7);
    }//GEN-LAST:event_remove8ActionPerformed

    private void remove9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove9ActionPerformed
        c.removePlayer(8);
    }//GEN-LAST:event_remove9ActionPerformed

    private void remove10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove10ActionPerformed
        c.removePlayer(9);
    }//GEN-LAST:event_remove10ActionPerformed

    private void remove11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove11ActionPerformed
        c.removePlayer(10);
    }//GEN-LAST:event_remove11ActionPerformed

    private void remove12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove12ActionPerformed
        c.removePlayer(11);
    }//GEN-LAST:event_remove12ActionPerformed

    private void remove13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove13ActionPerformed
        c.removePlayer(12);
    }//GEN-LAST:event_remove13ActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            addPlayer.doClick();
        }
    }//GEN-LAST:event_searchKeyReleased
    
    private void dbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbAddActionPerformed
        c.newPlayer();
		
	}

    private void dbListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_dbListValueChanged
        c.dbListValueChanged(evt);
    }//GEN-LAST:event_dbListValueChanged

    public javax.swing.JTextField getSearch() {
		return search;
	}


	public void setSearch(javax.swing.JTextField search) {
		this.search = search;
	}

	
}

