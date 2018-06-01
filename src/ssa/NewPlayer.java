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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

import common.LotusVBE;
import database.DB;

/**
 *
 * @author Davide
 */
public class NewPlayer extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private boolean b;
	private SetupController c;

    /**
     * Creates new form NewPlayer
     */
    public NewPlayer(boolean b,SetupController c) { //false-modifica true-nuovo
    	this.setIconImage(LotusVBE.img.lotusLogo);
    	this.c=c;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                c.w.setEnabled(true);
            }
        });
        c.w.setEnabled(false);
        NewPlayer.b = b;
        initComponents();
        if (!b) { // modifica
            id.setText(SetupController.selectedPlayer.getId());
            lastname.setText(SetupController.selectedPlayer.getLastname());
            name.setText(SetupController.selectedPlayer.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lastname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel9.setText("ID:");

        jLabel10.setText(SetupController.lang.getString("lastname"));

        jLabel11.setText(SetupController.lang.getString("name"));

        save.setText(SetupController.lang.getString("save"));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					saveActionPerformed(evt);
				} catch (FileNotFoundException e) {
					LotusVBE.printError(null, LotusVBE.lang.getString("dbNotFound"));
					e.printStackTrace();
				}
            }
        });

        cancel.setText(SetupController.lang.getString("return"));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(name)
                    .addComponent(jLabel10)
                    .addComponent(lastname, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(id)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.c.w.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {
    	String id=this.id.getText();
    	String name=this.name.getText();
    	String lastname=this.lastname.getText();
        if (!id.equals("") && !lastname.equals("") && !name.equals("")) {
            DB database = c.getDatabase(); //this should modify the real database and not making a copy

            if (b) { //adding
                if (!database.addRecord(id, name, lastname)) {//if add record fails
                    JOptionPane.showMessageDialog(this, SetupController.lang.getObject("playerAlreadyExists"));
                    return;
                }

            } else { //editing
                if (!database.editRecord(SetupController.selectedPlayer.getId(), id ,name,lastname)) {
                    JOptionPane.showMessageDialog(this,SetupController.lang.getObject("idAlreadyUsed"));
                    return;
                }

            }

        }
        c.w.setEnabled(true);
        c.updatePlayerList();
        this.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    javax.swing.JTextField id;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    javax.swing.JTextField lastname;
    javax.swing.JTextField name;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
