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
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;

import common.DateLabelFormatter;
import common.DatePicker;
import common.LotusVBE;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Davide
 */
public class OutputWindow extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private ResourceBundle lang = LotusVBE.lang;
	private MainController c = SSALotus.main;
	JDatePickerImpl datePicker = DatePicker.getDatePicker();
	
	/**
	 * Creates new form OutputWindow
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OutputWindow() {
		this.setIconImage(LotusVBE.img.lotusLogo);


		UIManager.put("ProgressBar.foreground", Color.green);
		UIManager.put("ProgressBar.selectionForeground", Color.black);



		this.setPreferredSize(new Dimension(560,331));


		jLabel1 = new javax.swing.JLabel();
		scoutNameField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		assistNameField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		casaField = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		ospiteField = new javax.swing.JTextField();
		notify = new javax.swing.JTextField();
		endButton = new javax.swing.JButton();
		backButton = new javax.swing.JButton();
		pb = new javax.swing.JProgressBar();
		pb.setString("");
		pb.setStringPainted(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle(lang.getString("insertData"));

		jLabel1.setText(lang.getString("scoutName"));

		scoutNameField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				scoutNameFieldKeyPressed(evt);
			}
		});

		jLabel2.setText(lang.getString("assistName"));

		jLabel3.setText(lang.getString("date"));

		jLabel6.setText(lang.getString("homeTeam"));

		jLabel7.setText(lang.getString("guestTeam"));

		notify.setEditable(false);

		endButton.setText(lang.getString("end"));
		endButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				endButtonActionPerformed(evt);
			}
		});

		backButton.setText(lang.getString("back"));
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		mvpField = new JTextField();

		lblMvp = new JLabel();
		lblMvp.setText("MVP");

		previewButton = new JButton("Preview");
		previewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSheet w = new StatusSheet(99);
				w.statusArea.setText(c.game.printGame("","",false));
				w.statusArea.setCaretPosition(0);
				w.setExtendedState(w.getExtendedState());
				w.setVisible(true);
			}
		});

		lblResults = new JLabel(lang.getString("result"));

		homeResult = new JComboBox();
		homeResult.setFocusable(false);
		homeResult.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
		homeResult.setMaximumRowCount(4);

		guestResult = new JComboBox();
		guestResult.setFocusable(false);
		guestResult.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
		guestResult.setMaximumRowCount(4);


		this.setIconImage(LotusVBE.img.lotusLogo);
		getContentPane().setLayout(new MigLayout("", "[182.00,grow][121.00,grow][84.00,grow][76.00,grow]", "[20px,grow][20px,grow][20px,grow][20px,grow][20px,grow][20px,grow][20px,grow][50px,grow][56px,grow]"));
		getContentPane().add(datePicker, "cell 1 2 3 1,growx");
		getContentPane().add(notify, "cell 0 6 4 1,growx,aligny top");
		getContentPane().add(pb, "cell 0 8 4 1,grow");
		getContentPane().add(previewButton, "cell 1 7,grow");
		getContentPane().add(jLabel1, "cell 0 0,alignx center,aligny center");
		getContentPane().add(scoutNameField, "cell 1 0 3 1,growx,aligny top");
		getContentPane().add(jLabel2, "cell 0 1,alignx center,aligny center");
		getContentPane().add(assistNameField, "cell 1 1 3 1,growx,aligny top");
		getContentPane().add(jLabel3, "cell 0 2,alignx center,aligny center");
		getContentPane().add(lblMvp, "cell 0 5,alignx center,aligny center");
		getContentPane().add(mvpField, "cell 1 5 3 1,growx,aligny top");
		getContentPane().add(backButton, "cell 0 7,grow");
		getContentPane().add(endButton, "cell 2 7 2 1,grow");
		getContentPane().add(jLabel7, "cell 0 4,alignx center,aligny center");
		getContentPane().add(ospiteField, "cell 1 4,growx,aligny top");
		getContentPane().add(jLabel6, "cell 0 3,alignx center,aligny center");
		getContentPane().add(casaField, "cell 1 3,growx,aligny top");
		getContentPane().add(lblResults, "cell 2 3,alignx center,aligny center");
		getContentPane().add(guestResult, "cell 3 4,growx,aligny top");
		getContentPane().add(homeResult, "cell 3 3,growx,aligny top");
		
		pack();
		setLocationRelativeTo(null); //window to center
		int side = backButton.getHeight()-10;
		
		backButton.setIcon(new ImageIcon(LotusVBE.img.undo.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
		previewButton.setIcon(new ImageIcon(LotusVBE.img.preview.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));
		endButton.setIcon(new ImageIcon(LotusVBE.img.save.getImage().getScaledInstance(side, side, Image.SCALE_SMOOTH)));

	}

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
		this.dispose();
		c.w.setEnabled(true);
		c.w.helpWindow.requestFocus();
	}//GEN-LAST:event_backButtonActionPerformed

	private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtonActionPerformed
		OutputWindow window = this;
		class Task extends SwingWorker<Void, Void> {

			@Override
			protected Void doInBackground() throws Exception {
				Output out = new Output(window);
				out.check();
				return null;
			}
		}

		Task task = new Task();
		//task.addPropertyChangeListener(this);
		task.execute();
	}

	private void scoutNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scoutNameFieldKeyPressed
		// TODO add your handling code here:
		if(evt.getKeyCode()== KeyEvent.VK_ENTER){
			if(scoutNameField.getText().equals("testmode")){
				endButton.doClick();
			}
		}
	}//GEN-LAST:event_scoutNameFieldKeyPressed



	// Variables declaration - do not modify//GEN-BEGIN:variables
	javax.swing.JTextField assistNameField;
	javax.swing.JButton backButton;
	javax.swing.JTextField casaField;
	javax.swing.JButton endButton;
	javax.swing.JLabel jLabel1;
	javax.swing.JLabel jLabel2;
	javax.swing.JLabel jLabel3;
	javax.swing.JLabel jLabel6;
	javax.swing.JLabel jLabel7;
	javax.swing.JTextField notify;
	javax.swing.JTextField ospiteField;
	javax.swing.JProgressBar pb;
	javax.swing.JTextField scoutNameField;
	JTextField mvpField;
	JLabel lblMvp;
	private JLabel lblResults;
	@SuppressWarnings("rawtypes")
	public JComboBox homeResult;
	@SuppressWarnings("rawtypes")
	public JComboBox guestResult;
	private JButton previewButton;
}
