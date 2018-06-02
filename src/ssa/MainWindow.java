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
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import buttonhandlers.*;
import common.LotusVBE;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

/**
 *
 * @author Davide
 */
public class MainWindow extends javax.swing.JFrame {
	private MainController c;
    //convert to j button when editing with window builder pro (just comment/uncomment) otherwise it wont work
    //comment this for develop, uncomment for release
	
    JGradientButton mainSectorServe = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorReceive = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorBlock = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorError = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorAttack = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorDelete = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorPlus = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorReset = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorRotate = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton mainSectorDig = JGradientButton.newInstance(new Color(200,200,255));
    JGradientButton yButton = JGradientButton.newInstance(new Color(255,140,140));
    JGradientButton nButton = JGradientButton.newInstance(new Color(255,140,140));
    JGradientButton num4 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num5 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num6 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num0 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num1 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num2 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num3 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num7 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num8 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton num9 = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton numenter = JGradientButton.newInstance(new Color(200,255,200));
    JGradientButton numbs = JGradientButton.newInstance(new Color(200,255,200));
    
    
    //uncomment this for develop, comment for release
    /*JButton mainSectorServe = new javax.swing.JButton();
    JButton mainSectorReceive = new javax.swing.JButton();
    JButton mainSectorBlock = new javax.swing.JButton();
    JButton mainSectorError = new javax.swing.JButton();
    JButton mainSectorAttack = new javax.swing.JButton();
    JButton mainSectorDelete = new javax.swing.JButton();
    JButton mainSectorPlus = new javax.swing.JButton();
    JButton mainSectorReset = new javax.swing.JButton();
    JButton mainSectorRotate = new javax.swing.JButton();
    JButton mainSectorDig = new javax.swing.JButton();
    JButton yButton = new javax.swing.JButton();
    JButton nButton = new javax.swing.JButton();
    JButton num4 = new javax.swing.JButton();
    JButton num5 = new javax.swing.JButton();
    JButton num6 = new javax.swing.JButton();
    JButton num0 = new javax.swing.JButton();
    JButton num1 = new javax.swing.JButton();
    JButton num2 = new javax.swing.JButton();
    JButton num3 = new javax.swing.JButton();
    JButton num7 = new javax.swing.JButton();
    JButton num8 = new javax.swing.JButton();
    JButton num9 = new javax.swing.JButton();
    JButton numenter = new javax.swing.JButton();
    JButton numbs = new javax.swing.JButton();*/

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean keyPressed = false;
    private static int key;
    private static final Color MENU_COLOR = Color.YELLOW;
    private static final Color DEFAULT_COLOR = new Color(240,240,240);
    private static ResourceBundle lang;

    /**
     * Creates new form MainWindow
     */
    public MainWindow(MainController c) {
    	addWindowFocusListener(new WindowFocusListener() {
    		public void windowGainedFocus(WindowEvent e) {
    			helpWindow.requestFocus();
    		}
    		public void windowLostFocus(WindowEvent e) {
    		}
    	});
    	this.setIconImage(LotusVBE.img.lotusLogo);
    	this.setPreferredSize(new Dimension(1350, 751));
    	lang= common.LotusVBE.lang;
    	this.c=c;
        playerButton1 = new javax.swing.JButton();
        playerButton3 = new javax.swing.JButton();
        playerButton5 = new javax.swing.JButton();
        playerButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Scout Application Lotus");

        playerButton1.setText("Player1");
        playerButton1.setFocusable(false);
        playerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerButton1ActionPerformed(evt);
            }
        });

        playerButton3.setText("jButton3");
        playerButton3.setFocusable(false);
        playerButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerButton3ActionPerformed(evt);
            }
        });

        playerButton5.setText("jButton5");
        playerButton5.setFocusable(false);
        playerButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerButton5ActionPerformed(evt);
            }
        });

        playerButton7.setText("jButton7");
        playerButton7.setFocusable(false);
        playerButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerButton7ActionPerformed(evt);
            }
        });

        jLabel2.setText(lang.getString("playerStatus"));
        jLabel2.setFocusable(false);
    
        getContentPane().setLayout(new MigLayout("", "[150,grow,fill][150,grow,fill][75,grow,fill][75,grow,fill][75,grow,fill][75,grow,fill][130,grow,fill][100,grow,fill][100,grow,fill][100,grow,fill][100,grow,fill]", "[grow,fill][24,grow,fill][24,grow,fill][24,grow,fill][24,grow,fill][24,grow,fill][24,grow,fill][24,grow,fill][24,grow,fill][33,grow,fill][100px,grow,fill][100.00,grow,fill][100px,grow,fill][50.00px,grow,fill][50.00px,grow,fill]"));
        playerButton9 = new javax.swing.JButton();
        
                playerButton9.setText("jButton9");
                playerButton9.setFocusable(false);
                playerButton9.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        playerButton9ActionPerformed(evt);
                    }
                });
                playerButton2 = new javax.swing.JButton();
                
                        playerButton2.setText("jButton2");
                        playerButton2.setFocusable(false);
                        playerButton2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                playerButton2ActionPerformed(evt);
                            }
                        });
                        
                        increaseOppButton = new JButton("+");
                        increaseOppButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		c.increaseOppScore();
                        	}
                        });
                        
                        decreaseOppButton = new JButton("-");
                        decreaseOppButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		c.decreaseOppScore();
                        	}
                        });
                        
                        decreaseOurButton = new JButton("-");
                        decreaseOurButton.setFocusable(false);
                        decreaseOurButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                        decreaseOurButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		c.decreaseOurScore();
                        	}
                        });
                        decreaseOurButton.setForeground(new Color(0, 128, 0));
                        getContentPane().add(decreaseOurButton, "cell 2 0,grow");
                        
                        increaseOurButton = new JButton("+");
                        increaseOurButton.setFocusable(false);
                        increaseOurButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                        increaseOurButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		c.increaseOurScore();
                        	}
                        });
                        increaseOurButton.setForeground(new Color(0,128,0));
                        getContentPane().add(increaseOurButton, "cell 3 0,grow");
                        decreaseOppButton.setFocusable(false);
                        decreaseOppButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                        decreaseOppButton.setForeground(Color.RED);
                        getContentPane().add(decreaseOppButton, "cell 4 0,grow");
                        increaseOppButton.setFocusable(false);
                        increaseOppButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                        increaseOppButton.setForeground(Color.RED);
                        getContentPane().add(increaseOppButton, "cell 5 0,grow");
                        jLabel1 = new javax.swing.JLabel();
                        
                                jLabel1.setText(lang.getString("help"));
                                jLabel1.setFocusable(false);
                                getContentPane().add(jLabel1, "cell 6 0,alignx left,aligny top");
                        getContentPane().add(playerButton2, "cell 1 1,growx,aligny top");
                playerButton4 = new javax.swing.JButton();
                
                        playerButton4.setText("jButton4");
                        playerButton4.setFocusable(false);
                        playerButton4.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                playerButton4ActionPerformed(evt);
                            }
                        });
                        
                        scrollPane_1 = new JScrollPane();
                        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        getContentPane().add(scrollPane_1, "cell 2 1 2 4,grow");
                        ourPane = new javax.swing.JTextPane();
                        scrollPane_1.setViewportView(ourPane);
                        
                                ourPane.setEditable(false);
                                ourPane.setFont(new Font("Tahoma", Font.PLAIN, 70));
                                ourPane.setForeground(new java.awt.Color(0, 255, 0));
                                ourPane.setFocusable(false);
                                jLabel3 = new javax.swing.JLabel();
                                scrollPane_1.setColumnHeaderView(jLabel3);
                                
                                        jLabel3.setText(lang.getString("ourScore"));
                                        jLabel3.setFocusable(false);
                        
                        scrollPane_2 = new JScrollPane();
                        scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        getContentPane().add(scrollPane_2, "cell 4 1 2 4,grow");
                        oppPane = new javax.swing.JTextPane();
                        scrollPane_2.setViewportView(oppPane);
                        
                                oppPane.setEditable(false);
                                oppPane.setFont(new Font("Tahoma", Font.PLAIN, 70));
                                oppPane.setForeground(new java.awt.Color(255, 0, 0));
                                jLabel4 = new javax.swing.JLabel();
                                scrollPane_2.setColumnHeaderView(jLabel4);
                                
                                        jLabel4.setText(lang.getString("oppScore"));
                                        jLabel4.setFocusable(false);
                        
                        scrollPane = new JScrollPane();
                        getContentPane().add(scrollPane, "cell 6 1 5 8,grow");
                        helpWindow = new javax.swing.JTextArea();
                        helpWindow.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        scrollPane.setViewportView(helpWindow);
                        
                                helpWindow.setEditable(false);
                                helpWindow.setColumns(20);
                                helpWindow.setRows(5);
                                helpWindow.setMargin(new java.awt.Insets(0, 3, 2, 2));
                                helpWindow.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        nextActionKeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        nextActionKeyReleased(evt);
                                    }
                                });
                                helpWindow.requestFocus();
                        getContentPane().add(playerButton4, "cell 1 2,growx,aligny top");
                playerButton6 = new javax.swing.JButton();
                
                        playerButton6.setText("jButton6");
                        playerButton6.setFocusable(false);
                        playerButton6.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                playerButton6ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(playerButton6, "cell 1 3,growx,aligny top");
                playerButton8 = new javax.swing.JButton();
                
                        playerButton8.setText("jButton8");
                        playerButton8.setFocusable(false);
                        playerButton8.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                playerButton8ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(playerButton8, "cell 1 4,growx,aligny top");
                getContentPane().add(playerButton9, "cell 0 5,growx,aligny top");
        playerButton10 = new javax.swing.JButton();
        
                playerButton10.setText("jButton10");
                playerButton10.setFocusable(false);
                playerButton10.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        playerButton10ActionPerformed(evt);
                    }
                });
                getContentPane().add(playerButton10, "cell 1 5,growx,aligny top");
        playerButton11 = new javax.swing.JButton();
        
                playerButton11.setText("jButton11");
                playerButton11.setFocusable(false);
                playerButton11.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        playerButton11ActionPerformed(evt);
                    }
                });
                
                scrollPane_4 = new JScrollPane();
                scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(scrollPane_4, "cell 2 5 2 4,grow");
                
                setPane = new JTextPane();
                scrollPane_4.setViewportView(setPane);
                setPane.setFont(new Font("Tahoma", Font.PLAIN, 70));
                setPane.setFocusable(false);
                setPane.setEditable(false);
                
                lblSet = new JLabel(lang.getString("set"));
                scrollPane_4.setColumnHeaderView(lblSet);
                
                scrollPane_3 = new JScrollPane();
                scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(scrollPane_3, "cell 4 5 2 4,grow");
                rotationPane = new javax.swing.JTextPane();
                scrollPane_3.setViewportView(rotationPane);
                
                        rotationPane.setEditable(false);
                        rotationPane.setFont(new Font("Tahoma", Font.PLAIN, 70)); // NOI18N
                        rotationPane.setFocusable(false);
                        jLabel5 = new javax.swing.JLabel();
                        scrollPane_3.setColumnHeaderView(jLabel5);
                        
                                jLabel5.setText(lang.getString("rotation"));
                                jLabel5.setFocusable(false);
                getContentPane().add(playerButton11, "cell 0 6,growx,aligny top");
        playerButton12 = new javax.swing.JButton();
        
                playerButton12.setText("jButton12");
                playerButton12.setFocusable(false);
                playerButton12.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        playerButton12ActionPerformed(evt);
                    }
                });
                getContentPane().add(playerButton12, "cell 1 6,growx,aligny top");
        playerButton13 = new javax.swing.JButton();
        
                playerButton13.setText("jButton13");
                playerButton13.setFocusable(false);
                playerButton13.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        playerButton13ActionPerformed(evt);
                    }
                });
                getContentPane().add(playerButton13, "cell 0 7,growx,aligny top");
        endSet = new javax.swing.JButton();
        endSet.setForeground(Color.BLUE);
        
                endSet.setText(lang.getString("newSet"));
                endSet.setFocusable(false);
                endSet.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        endSetActionPerformed(evt);
                    }
                });
                getContentPane().add(endSet, "cell 1 7 1 2,growx,aligny top");
        mainSectorServe.setHorizontalTextPosition(SwingConstants.CENTER);
        mainSectorServe.setVerticalTextPosition(SwingConstants.BOTTOM);
        mainSectorServe.setForeground(new Color(0, 128, 0));
        
                mainSectorServe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                mainSectorServe.setText(lang.getString("serveButton"));
                mainSectorServe.setEnabled(false);
                mainSectorServe.setFocusable(false);
                mainSectorServe.setMargin(new java.awt.Insets(2, 0, 2, 0));
                mainSectorServe.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mainSectorbActionPerformed(evt);
                    }
                });
                notify = new javax.swing.JTextField();
                notify.setFont(new Font("Tahoma", Font.PLAIN, 13));
                
                        notify.setEditable(false);
                        notify.setForeground(new java.awt.Color(255, 0, 0));
                        notify.setFocusable(false);
                        getContentPane().add(notify, "cell 0 9 11 1,grow");
                getContentPane().add(mainSectorServe, "cell 0 10,grow");
        mainSectorBlock.setVerticalTextPosition(SwingConstants.BOTTOM);
        mainSectorBlock.setHorizontalTextPosition(SwingConstants.CENTER);
        mainSectorBlock.setForeground(new Color(0, 128, 0));
        
                mainSectorBlock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                mainSectorBlock.setText(lang.getString("blockButton"));
                mainSectorBlock.setEnabled(false);
                mainSectorBlock.setFocusable(false);
                mainSectorBlock.setMargin(new java.awt.Insets(2, 0, 2, 0));
                mainSectorBlock.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mainSectormActionPerformed(evt);
                    }
                });
                mainSectorReceive.setVerticalTextPosition(SwingConstants.BOTTOM);
                mainSectorReceive.setHorizontalTextPosition(SwingConstants.CENTER);
                mainSectorReceive.setForeground(new Color(0, 128, 0));
                
                        mainSectorReceive.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        mainSectorReceive.setText(lang.getString("receiveButton"));
                        mainSectorReceive.setEnabled(false);
                        mainSectorReceive.setFocusable(false);
                        mainSectorReceive.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        mainSectorReceive.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mainSectorrActionPerformed(evt);
                            }
                        });
                        getContentPane().add(mainSectorReceive, "cell 1 10,grow");
                mainSectorAttack.setVerticalTextPosition(SwingConstants.BOTTOM);
                mainSectorAttack.setHorizontalTextPosition(SwingConstants.CENTER);
                mainSectorAttack.setForeground(new Color(0, 128, 0));
                
                        mainSectorAttack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        mainSectorAttack.setText(lang.getString("attackButton"));
                        mainSectorAttack.setEnabled(false);
                        mainSectorAttack.setFocusable(false);
                        mainSectorAttack.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        mainSectorAttack.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mainSectoraActionPerformed(evt);
                            }
                        });
                        getContentPane().add(mainSectorAttack, "cell 2 10 2 1,grow");
                mainSectorDig.setVerticalTextPosition(SwingConstants.BOTTOM);
                mainSectorDig.setHorizontalTextPosition(SwingConstants.CENTER);
                mainSectorDig.setForeground(new Color(0, 128, 0));
                
                        mainSectorDig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        mainSectorDig.setText(lang.getString("digButton"));
                        mainSectorDig.setEnabled(false);
                        mainSectorDig.setFocusable(false);
                        mainSectorDig.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        mainSectorDig.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mainSectordActionPerformed(evt);
                            }
                        });
                        getContentPane().add(mainSectorDig, "cell 4 10 2 1,grow");
                jScrollPane8 = new javax.swing.JScrollPane();
                jScrollPane8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                jScrollPane8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                lastActionDisplay = new javax.swing.JTextArea();
                
                        lastActionDisplay.setEditable(false);
                        lastActionDisplay.setColumns(20);
                        lastActionDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20)); // NOI18N
                        lastActionDisplay.setLineWrap(true);
                        lastActionDisplay.setRows(5);
                        lastActionDisplay.setWrapStyleWord(true);
                        lastActionDisplay.setAutoscrolls(false);
                        lastActionDisplay.setFocusable(false);
                        jScrollPane8.setViewportView(lastActionDisplay);
                        getContentPane().add(jScrollPane8, "cell 6 10 1 2,grow");
                num1.setForeground(new Color(0, 128, 0));
                
                        num1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num1.setText("<html>1 <font color=\"red\"><sb>-</b></font></html>");
                        num1.setEnabled(false);
                        num1.setFocusable(false);
                        num1.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num1ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num1, "cell 7 10,grow");
                num2.setForeground(new Color(0, 128, 0));
                
                        num2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num2.setText("<html>2 <font color=\"red\"><sb>/</b></font></html>");
                        num2.setEnabled(false);
                        num2.setFocusable(false);
                        num2.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num2ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num2, "cell 8 10,grow");
                num3.setForeground(new Color(0, 128, 0));
                
                        num3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num3.setText("<html>3 <font color=\"red\"><sb>+</b></font></html>");
                        num3.setEnabled(false);
                        num3.setFocusable(false);
                        num3.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num3ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num3, "cell 9 10,grow");
                
                scrollPane_5 = new JScrollPane();
                scrollPane_5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                scrollPane_5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(scrollPane_5, "cell 10 10,grow");
                numDisplay = new javax.swing.JTextPane();
                scrollPane_5.setViewportView(numDisplay);
                
                        numDisplay.setEditable(false);
                        numDisplay.setFont(new Font("Tahoma", Font.BOLD, 70)); // NOI18N
                        numDisplay.setFocusable(false);
                getContentPane().add(mainSectorBlock, "cell 0 11,grow");
        mainSectorRotate.setVerticalTextPosition(SwingConstants.BOTTOM);
        mainSectorRotate.setHorizontalTextPosition(SwingConstants.CENTER);
        mainSectorRotate.setForeground(new Color(0, 128, 0));
        
                mainSectorRotate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                mainSectorRotate.setText(lang.getString("rotateButton"));
                mainSectorRotate.setEnabled(false);
                mainSectorRotate.setFocusable(false);
                mainSectorRotate.setMargin(new java.awt.Insets(2, 0, 2, 0));
                mainSectorRotate.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mainSectorgActionPerformed(evt);
                    }
                });
                mainSectorError.setHorizontalTextPosition(SwingConstants.CENTER);
                mainSectorError.setVerticalTextPosition(SwingConstants.BOTTOM);
                mainSectorError.setForeground(new Color(0, 128, 0));
                
                        mainSectorError.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        mainSectorError.setText(lang.getString("errorButton"));
                        mainSectorError.setEnabled(false);
                        mainSectorError.setFocusable(false);
                        mainSectorError.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        mainSectorError.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mainSectoreActionPerformed(evt);
                            }
                        });
                        getContentPane().add(mainSectorError, "cell 1 11,grow");
                mainSectorPlus.setHorizontalTextPosition(SwingConstants.CENTER);
                mainSectorPlus.setVerticalTextPosition(SwingConstants.BOTTOM);
                mainSectorPlus.setForeground(new Color(0, 128, 0));
                
                        mainSectorPlus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        mainSectorPlus.setText(lang.getString("oppErrorButton"));
                        mainSectorPlus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        mainSectorPlus.setEnabled(false);
                        mainSectorPlus.setFocusable(false);
                        mainSectorPlus.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        mainSectorPlus.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mainSectorplusActionPerformed(evt);
                            }
                        });
                        getContentPane().add(mainSectorPlus, "cell 2 11 2 1,grow");
                jScrollPane6 = new javax.swing.JScrollPane();
                jScrollPane6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                jScrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane6.setEnabled(false);
                phasePane = new javax.swing.JTextPane();
                
                        jScrollPane6.setFocusable(false);
                        
                                phasePane.setEditable(false);
                                phasePane.setFont(new Font("Tahoma", Font.PLAIN, 56)); // NOI18N
                                phasePane.setFocusable(false);
                                jScrollPane6.setViewportView(phasePane);
                                jLabel6 = new javax.swing.JLabel();
                                jScrollPane6.setColumnHeaderView(jLabel6);
                                
                                        jLabel6.setText(lang.getString("phase"));
                                        jLabel6.setFocusable(false);
                                        getContentPane().add(jScrollPane6, "cell 4 11 2 1,growx,aligny bottom");
                num4.setForeground(new Color(0, 128, 0));
                
                        num4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num4.setText("<html>4 <font color=\"red\"><sb>#</b></font></html>");
                        num4.setEnabled(false);
                        num4.setFocusable(false);
                        num4.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num4.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num4ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num4, "cell 7 11,grow");
                num5.setForeground(new Color(0, 128, 0));
                
                        num5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num5.setText("5");
                        num5.setEnabled(false);
                        num5.setFocusable(false);
                        num5.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num5.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num5ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num5, "cell 8 11,grow");
                num6.setForeground(new Color(0, 128, 0));
                
                        num6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num6.setText("6");
                        num6.setEnabled(false);
                        num6.setFocusable(false);
                        num6.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num6.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num6ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num6, "cell 9 11,grow");
                jLabel7 = new javax.swing.JLabel();
                
                        jLabel7.setText("<html>Author: Daffonchio Davide"+"<br>"+lang.getString("version") + LotusVBE.version+"</html>");
                        getContentPane().add(jLabel7, "cell 10 11,alignx center,aligny center");
                getContentPane().add(mainSectorRotate, "cell 0 12,grow");
        mainSectorDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
        mainSectorDelete.setHorizontalTextPosition(SwingConstants.CENTER);
        mainSectorDelete.setForeground(new Color(0, 128, 0));
        
                mainSectorDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                mainSectorDelete.setText(lang.getString("undoButton"));
                mainSectorDelete.setEnabled(false);
                mainSectorDelete.setFocusable(false);
                mainSectorDelete.setMargin(new java.awt.Insets(2, 0, 2, 0));
                mainSectorDelete.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mainSectorcActionPerformed(evt);
                    }
                });
                mainSectorReset.setVerticalTextPosition(SwingConstants.BOTTOM);
                mainSectorReset.setHorizontalTextPosition(SwingConstants.CENTER);
                mainSectorReset.setForeground(new Color(0, 128, 0));
                
                        mainSectorReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        mainSectorReset.setText(lang.getString("resetRotationButton"));
                        mainSectorReset.setEnabled(false);
                        mainSectorReset.setFocusable(false);
                        mainSectorReset.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        mainSectorReset.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mainSectorkActionPerformed(evt);
                            }
                        });
                        getContentPane().add(mainSectorReset, "cell 1 12,grow");
                jScrollPane9 = new javax.swing.JScrollPane();
                jScrollPane9.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                deleteArea = new javax.swing.JTextArea();
                
                        deleteArea.setEditable(false);
                        deleteArea.setColumns(20);
                        deleteArea.setLineWrap(true);
                        deleteArea.setRows(5);
                        deleteArea.setWrapStyleWord(true);
                        deleteArea.setFocusable(false);
                        jScrollPane9.setViewportView(deleteArea);
                        getContentPane().add(jScrollPane9, "cell 2 12 5 3,grow");
                num9.setForeground(new Color(0, 128, 0));
                
                        num9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num9.setText("9");
                        num9.setEnabled(false);
                        num9.setFocusable(false);
                        num9.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num9.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num9ActionPerformed(evt);
                            }
                        });
                        num8.setForeground(new Color(0, 128, 0));
                        
                                num8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                num8.setText("8");
                                num8.setEnabled(false);
                                num8.setFocusable(false);
                                num8.setMargin(new java.awt.Insets(2, 0, 2, 0));
                                num8.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        num8ActionPerformed(evt);
                                    }
                                });
                                num7.setForeground(new Color(0, 128, 0));
                                
                                        num7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                        num7.setText("7");
                                        num7.setEnabled(false);
                                        num7.setFocusable(false);
                                        num7.setMargin(new java.awt.Insets(2, 0, 2, 0));
                                        num7.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                num7ActionPerformed(evt);
                                            }
                                        });
                                        getContentPane().add(num7, "cell 7 12,grow");
                                getContentPane().add(num8, "cell 8 12,grow");
                        getContentPane().add(num9, "cell 9 12,grow");
                specialSectorCancel = new javax.swing.JButton();
                specialSectorCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
                specialSectorCancel.setHorizontalTextPosition(SwingConstants.CENTER);
                
                        specialSectorCancel.setText(lang.getString("cancelButton"));
                        specialSectorCancel.setFocusable(false);
                        specialSectorCancel.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                specialSectoruActionPerformed(evt);
                            }
                        });
                        getContentPane().add(specialSectorCancel, "cell 10 12,grow");
                getContentPane().add(mainSectorDelete, "cell 0 13 1 2,grow");
        yButton.setForeground(new Color(0, 128, 0));
        
                yButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                yButton.setText(lang.getString("yesButton"));
                yButton.setEnabled(false);
                yButton.setFocusable(false);
                yButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
                yButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        yButtonActionPerformed(evt);
                    }
                });
                getContentPane().add(yButton, "cell 1 13,grow");
        nButton.setForeground(new Color(0, 128, 0));
        
                nButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                nButton.setText(lang.getString("noButton"));
                nButton.setEnabled(false);
                nButton.setFocusable(false);
                nButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
                nButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        nButtonActionPerformed(evt);
                    }
                });
                numbs.setForeground(new Color(0, 128, 0));
                
                        numbs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        numbs.setText("Backspace");
                        numbs.setEnabled(false);
                        numbs.setFocusable(false);
                        numbs.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        numbs.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                numbsActionPerformed(evt);
                            }
                        });
                        getContentPane().add(numbs, "cell 7 13 1 2,grow");
                num0.setForeground(new Color(0, 128, 0));
                
                        num0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        num0.setText("<html>0 <font color=\"red\"><sb>=</b></font></html>");
                        num0.setEnabled(false);
                        num0.setFocusable(false);
                        num0.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        num0.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                num0ActionPerformed(evt);
                            }
                        });
                        getContentPane().add(num0, "cell 8 13 1 2,grow");
                numenter.setForeground(new Color(0, 128, 0));
                
                        numenter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        numenter.setText("Enter");
                        numenter.setEnabled(false);
                        numenter.setFocusable(false);
                        numenter.setMargin(new java.awt.Insets(2, 0, 2, 0));
                        numenter.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                numenterActionPerformed(evt);
                            }
                        });
                        getContentPane().add(numenter, "cell 9 13 1 2,grow");
                specialSectorTakeNote = new javax.swing.JButton();
                
                        specialSectorTakeNote.setText(lang.getString("notes"));
                        specialSectorTakeNote.setFocusable(false);
                        specialSectorTakeNote.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                specialSectortActionPerformed(evt);
                            }
                        });
                        getContentPane().add(specialSectorTakeNote, "cell 10 13,grow");
                getContentPane().add(nButton, "cell 1 14,grow");
        getContentPane().add(playerButton1, "cell 0 1,grow");
        getContentPane().add(playerButton3, "cell 0 2,growx,aligny top");
        getContentPane().add(playerButton5, "cell 0 3,growx,aligny top");
        getContentPane().add(playerButton7, "cell 0 4,grow");
        getContentPane().add(jLabel2, "cell 0 0 2 1,alignx left,aligny top");
        specialSectorQuit = new javax.swing.JButton();
        
                specialSectorQuit.setText(lang.getString("endButton"));
                specialSectorQuit.setFocusable(false);
                specialSectorQuit.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        specialSectorqActionPerformed(evt);
                    }
                });
                getContentPane().add(specialSectorQuit, "cell 10 14,grow");
        this.setIconImage(LotusVBE.img.lotusLogo);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        serveMenu = new JMenuItem(lang.getString("serve"));
        serveMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		serveMenu.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		serveMenu.setBackground(DEFAULT_COLOR);
        	}
        });
        serveMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        serveMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.livePrintTeam(MainController.SERVE_STATUS);
        	}
        });
        menuBar.add(serveMenu);
        
        receiveMenu = new JMenuItem(lang.getString("receive"));
        receiveMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				c.livePrintTeam(MainController.RECEIVE_STATUS);
        	}
        });
        receiveMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		receiveMenu.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		receiveMenu.setBackground(DEFAULT_COLOR);
        	}
        });
        receiveMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.add(receiveMenu);
        
        attackMenu = new JMenuItem(lang.getString("attack"));
        attackMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.livePrintTeam(MainController.ATTACK_STATUS);
        	}
        });
        attackMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		attackMenu.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		attackMenu.setBackground(DEFAULT_COLOR);
        	}
        });
        attackMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.add(attackMenu);
        
        digMenu = new JMenuItem(lang.getString("dig"));
        digMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.livePrintTeam(MainController.DIG_STATUS);
        	}
        });
        digMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		digMenu.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		digMenu.setBackground(DEFAULT_COLOR);
        	}
        });
        
        blockMenu = new JMenuItem(lang.getString("block"));
        blockMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.livePrintTeam(MainController.BLOCK_STATUS);
        	}
        });
        blockMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		blockMenu.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		blockMenu.setBackground(DEFAULT_COLOR);
        	}
        });
        blockMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.add(blockMenu);
        digMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.add(digMenu);
        
        
        menuSub = new JMenuItem(lang.getString("sufferedPoints"));
        menuSub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
					c.livePrintSuffered();
        	}
        });
        menuSub.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		menuSub.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		menuSub.setBackground(DEFAULT_COLOR);
        	}
        });
        menuSub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.add(menuSub);
        
        menuDone = new JMenuItem(lang.getString("statisticIndexes"));
        menuDone.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
					c.livePrintStatisticIndexes();
        	}
        });
        menuDone.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		menuDone.setBackground(MENU_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		menuDone.setBackground(DEFAULT_COLOR);
        	}
        });
        menuDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.add(menuDone);
        pack();
        
        //images
        int side = mainSectorServe.getHeight()-30;
        mainSectorServe.setIcon(new ImageIcon(LotusVBE.img.serve.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorReceive.setIcon(new ImageIcon(LotusVBE.img.receive.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorAttack.setIcon(new ImageIcon(LotusVBE.img.attack.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorDig.setIcon(new ImageIcon(LotusVBE.img.dig.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorBlock.setIcon(new ImageIcon(LotusVBE.img.block.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        
        mainSectorDelete.setIcon(new ImageIcon(LotusVBE.img.removeButton.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorRotate.setIcon(new ImageIcon(LotusVBE.img.rotate.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        
        side = mainSectorReset.getHeight()-70;
        mainSectorReset.setIcon(new ImageIcon(LotusVBE.img.resetRotation.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorError.setIcon(new ImageIcon(LotusVBE.img.ourError.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        mainSectorPlus.setIcon(new ImageIcon(LotusVBE.img.oppError.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
        
        specialSectorCancel.setIcon(new ImageIcon(LotusVBE.img.undo.getImage().getScaledInstance(specialSectorQuit.getHeight()-10, specialSectorQuit.getHeight()-10, Image.SCALE_SMOOTH)));
        specialSectorQuit.setIcon(new ImageIcon(LotusVBE.img.quit.getImage().getScaledInstance(specialSectorQuit.getHeight()-10, specialSectorQuit.getHeight()-10, Image.SCALE_SMOOTH)));
        specialSectorTakeNote.setIcon(new ImageIcon(LotusVBE.img.notes.getImage().getScaledInstance(specialSectorTakeNote.getHeight()-10, specialSectorTakeNote.getHeight()-10, Image.SCALE_SMOOTH)));
        
        
        helpWindow.requestFocus();
    }

    private void playerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton1ActionPerformed
        
        c.livePrintPlayer(0);
    }//GEN-LAST:event_playerButton1ActionPerformed


    private void mainSectorbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorbActionPerformed
       c.handler = new ServeHandler();
    }//GEN-LAST:event_mainSectorbActionPerformed

    private void mainSectorrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorrActionPerformed
    	c.handler = new ReceiveHandler();
    }//GEN-LAST:event_mainSectorrActionPerformed

    private void mainSectoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectoraActionPerformed
    	c.handler = new AttackHandler();
    }//GEN-LAST:event_mainSectoraActionPerformed

    private void mainSectordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectordActionPerformed
    	c.handler = new DigHandler();
    }//GEN-LAST:event_mainSectordActionPerformed

    private void mainSectormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectormActionPerformed
    	c.handler = new BlockHandler();
    }//GEN-LAST:event_mainSectormActionPerformed

    private void mainSectoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorsActionPerformed
    	c.handler = new OurErrorHandler();
    }//GEN-LAST:event_mainSectorsActionPerformed

    private void mainSectorplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorplusActionPerformed
    	c.handler = new OppErrorHandler();
    }//GEN-LAST:event_mainSectorplusActionPerformed

    private void specialSectoruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialSectoruActionPerformed
        c.cancel();
    }//GEN-LAST:event_specialSectoruActionPerformed

    private void mainSectorgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorgActionPerformed
        c.rotate();
    }//GEN-LAST:event_mainSectorgActionPerformed

    private void mainSectorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorkActionPerformed
        c.handler = new SetRotationHandler(c);
    }//GEN-LAST:event_mainSectorkActionPerformed

    private void mainSectorcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSectorcActionPerformed
        c.undo();
    }//GEN-LAST:event_mainSectorcActionPerformed
    
    private void num0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num0ActionPerformed
    	c.numPadInput(0);
    }//GEN-LAST:event_num0ActionPerformed

    private void num1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num1ActionPerformed
        
    	c.numPadInput(1);
    }//GEN-LAST:event_num1ActionPerformed
    
    private void num2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num2ActionPerformed
    	c.numPadInput(2);
    }//GEN-LAST:event_num2ActionPerformed
    
    private void num3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num3ActionPerformed
    	c.numPadInput(3);
    }//GEN-LAST:event_num3ActionPerformed

    private void num4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num4ActionPerformed
    	c.numPadInput(4);
    }//GEN-LAST:event_num4ActionPerformed

    private void num5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num5ActionPerformed
    	c.numPadInput(5);
    }//GEN-LAST:event_num5ActionPerformed

    private void num6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num6ActionPerformed
    	c.numPadInput(6);
    }//GEN-LAST:event_num6ActionPerformed
    
    private void num7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num7ActionPerformed
    	c.numPadInput(7);
    }//GEN-LAST:event_num7ActionPerformed

    private void num8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num8ActionPerformed
    	c.numPadInput(8);
    }//GEN-LAST:event_num8ActionPerformed

    private void num9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num9ActionPerformed
    	c.numPadInput(9);
    }//GEN-LAST:event_num9ActionPerformed

    private void numenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numenterActionPerformed
        
        if (!"".equals(numDisplay.getText())) {
                c.handler.handlePlayerNumber(Integer.parseInt(numDisplay.getText()));
        }
    }//GEN-LAST:event_numenterActionPerformed

    private void numbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numbsActionPerformed
        
        String str = numDisplay.getText();
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        numDisplay.setText(str);
    }//GEN-LAST:event_numbsActionPerformed

    private void endSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endSetActionPerformed
        
        c.newSet();
    }//GEN-LAST:event_endSetActionPerformed

    private void yButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yButtonActionPerformed
        
        c.handler.handleYesNo(true);
    }//GEN-LAST:event_yButtonActionPerformed

    private void nButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nButtonActionPerformed
        
        c.handler.handleYesNo(false);
    }//GEN-LAST:event_nButtonActionPerformed

    private void specialSectortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialSectortActionPerformed
        c.takeNote();
    }//GEN-LAST:event_specialSectortActionPerformed

    private void playerButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton2ActionPerformed
        
        c.livePrintPlayer(1);
    }//GEN-LAST:event_playerButton2ActionPerformed

    private void playerButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton3ActionPerformed
        
        c.livePrintPlayer(2);
    }//GEN-LAST:event_playerButton3ActionPerformed

    private void playerButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton4ActionPerformed
        
        c.livePrintPlayer(3);
    }//GEN-LAST:event_playerButton4ActionPerformed

    private void playerButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton5ActionPerformed
        
        c.livePrintPlayer(4);
    }//GEN-LAST:event_playerButton5ActionPerformed

    private void playerButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton6ActionPerformed
        
        c.livePrintPlayer(5);
    }//GEN-LAST:event_playerButton6ActionPerformed

    private void playerButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton7ActionPerformed
        
        c.livePrintPlayer(6);
    }//GEN-LAST:event_playerButton7ActionPerformed

    private void playerButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton8ActionPerformed
        
        c.livePrintPlayer(7);
    }//GEN-LAST:event_playerButton8ActionPerformed

    private void playerButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton9ActionPerformed
        
        c.livePrintPlayer(8);
    }//GEN-LAST:event_playerButton9ActionPerformed

    private void playerButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton10ActionPerformed
        
        c.livePrintPlayer(9);
    }//GEN-LAST:event_playerButton10ActionPerformed

    private void playerButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton11ActionPerformed
        
        c.livePrintPlayer(10);
    }//GEN-LAST:event_playerButton11ActionPerformed

    private void playerButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton12ActionPerformed
        
        c.livePrintPlayer(11);
    }//GEN-LAST:event_playerButton12ActionPerformed

    private void playerButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerButton13ActionPerformed
        
        c.livePrintPlayer(12);
    }//GEN-LAST:event_playerButton13ActionPerformed

    private void specialSectorqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialSectorqActionPerformed
        
        c.endOfGame();
    }//GEN-LAST:event_specialSectorqActionPerformed

    private void nextActionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nextActionKeyReleased
        keyPressed = false;
    }//GEN-LAST:event_nextActionKeyReleased

    private void nextActionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nextActionKeyPressed
        if(!keyPressed || key!=evt.getKeyCode()){
        
        key = evt.getKeyCode();

        //JOptionPane.showInputDialog(key); //key code debug
        switch (key) {
            case KeyEvent.VK_BACK_SPACE:
                numbs.doClick();
                break;

            case KeyEvent.VK_B:
                mainSectorBlock.doClick();
                break;
            case KeyEvent.VK_R:
                mainSectorReceive.doClick();
                break;
            case KeyEvent.VK_A:
                mainSectorAttack.doClick();
                break;
            case KeyEvent.VK_D:
                mainSectorDig.doClick();
                break;
            case KeyEvent.VK_S:
                mainSectorServe.doClick();      
                break;
            case KeyEvent.VK_E:
                mainSectorError.doClick();
                break;
            case 521:
            case KeyEvent.VK_ADD:
                mainSectorPlus.doClick();
                break;
            case KeyEvent.VK_G:
            	mainSectorRotate.doClick();
            	break;
            case KeyEvent.VK_K:
                mainSectorReset.doClick();
                break;
            case KeyEvent.VK_C:
                mainSectorDelete.doClick();
                break;
            case KeyEvent.VK_U:
                specialSectorCancel.doClick();
                break;
            case KeyEvent.VK_Q:
                specialSectorQuit.doClick();
                break;
            case KeyEvent.VK_T:
                specialSectorTakeNote.doClick();
                break;
            case KeyEvent.VK_0:
            case KeyEvent.VK_NUMPAD0:
                num0.doClick();
                break;
            case KeyEvent.VK_1:
            case KeyEvent.VK_NUMPAD1:
                num1.doClick();
                break;
            case KeyEvent.VK_2:
            case KeyEvent.VK_NUMPAD2:
                num2.doClick();
                break;
            case KeyEvent.VK_3:
            case KeyEvent.VK_NUMPAD3:
                num3.doClick();
                break;
            case KeyEvent.VK_4:
            case KeyEvent.VK_NUMPAD4:
                num4.doClick();
                break;
            case KeyEvent.VK_5:
            case KeyEvent.VK_NUMPAD5:
                num5.doClick();
                break;
            case KeyEvent.VK_6:
            case KeyEvent.VK_NUMPAD6:
                num6.doClick();
                break;
            case KeyEvent.VK_7:
            case KeyEvent.VK_NUMPAD7:
                num7.doClick();
                break;
            case KeyEvent.VK_8:
            case KeyEvent.VK_NUMPAD8:
                num8.doClick();
                break;
            case KeyEvent.VK_9:
            case KeyEvent.VK_NUMPAD9:
                num9.doClick();
                break;
            case KeyEvent.VK_ENTER:
                numenter.doClick();
                break;
            case KeyEvent.VK_Y:
                yButton.doClick();
                break;
            case KeyEvent.VK_N:
                nButton.doClick();
                break;

        }
        
        keyPressed=true;
        }
    }//GEN-LAST:event_nextActionKeyPressed
    javax.swing.JTextPane oppPane;
    javax.swing.JTextArea deleteArea;
    javax.swing.JButton endSet;
    javax.swing.JTextPane phasePane;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    javax.swing.JTextArea lastActionDisplay;
    javax.swing.JTextArea helpWindow;
    javax.swing.JTextPane ourPane;
    javax.swing.JTextField notify;
    javax.swing.JTextPane numDisplay;
    javax.swing.JButton playerButton1;
    javax.swing.JButton playerButton10;
    javax.swing.JButton playerButton11;
    javax.swing.JButton playerButton12;
    javax.swing.JButton playerButton13;
    javax.swing.JButton playerButton2;
    javax.swing.JButton playerButton3;
    javax.swing.JButton playerButton4;
    javax.swing.JButton playerButton5;
    javax.swing.JButton playerButton6;
    javax.swing.JButton playerButton7;
    javax.swing.JButton playerButton8;
    javax.swing.JButton playerButton9;
    javax.swing.JTextPane rotationPane;
    javax.swing.JButton specialSectorQuit;
    javax.swing.JButton specialSectorTakeNote;
    javax.swing.JButton specialSectorCancel;
    JTextPane setPane;
    private JMenuBar menuBar;
    private JMenuItem serveMenu;
    private JMenuItem receiveMenu;
    private JMenuItem attackMenu;
    private JMenuItem menuSub;
    private JMenuItem menuDone;
    private JButton decreaseOurButton;
    private JButton decreaseOppButton;
    private JButton increaseOurButton;
    private JButton increaseOppButton;
    private JMenuItem digMenu;
    private JMenuItem blockMenu;
    private JLabel lblSet;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_4;
    private JScrollPane scrollPane_5;
    // End of variables declaration//GEN-END:variables
    static class JGradientButton extends JButton {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Color color;

        private JGradientButton(Color color) {
            super("Gradient Button");
            this.color = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            final Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0),
                    Color.WHITE,
                    new Point(0, getHeight()),
                    this.color));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();

            super.paintComponent(g);
        }

        public static JGradientButton newInstance(Color color) {
            return new JGradientButton(color);
        }
    }
}
