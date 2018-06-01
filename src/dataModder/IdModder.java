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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.LotusVBE;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class IdModder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField oldId;
	private JTextField newId;
	IdModder w = this;
	MainController c;

	/**
	 * Create the frame.
	 */
	public IdModder(MainController c) {
		this.addWindowListener(new WindowAdapter(){
            @Override
        public void windowClosing(WindowEvent e){
            c.w.setEnabled(true);
        }
		});
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[][][][][]"));
		
		JLabel lblNewLabel = new JLabel(LotusVBE.lang.getString("oldId"));
		contentPane.add(lblNewLabel, "cell 0 0 2 1");
		
		oldId = new JTextField();
		contentPane.add(oldId, "cell 0 1 2 1,grow");
		oldId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(LotusVBE.lang.getString("newId"));
		contentPane.add(lblNewLabel_1, "cell 0 2 2 1");
		
		newId = new JTextField();
		contentPane.add(newId, "cell 0 3 2 1,grow");
		newId.setColumns(10);
		
		JButton cancelButton = new JButton(LotusVBE.lang.getString("cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.w.setEnabled(true);
				w.dispose();
			}
		});
		contentPane.add(cancelButton, "flowx,cell 0 4,grow");
		
		JButton applyButton = new JButton(LotusVBE.lang.getString("apply"));
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(w.oldId.getText().equals("") || w.newId.getText().equals(""))
					return;
				w.dispose();
				c.idMod(w.oldId.getText(),w.newId.getText());
				
			}
		});
		contentPane.add(applyButton, "cell 1 4,grow");
		this.setVisible(true);
	}

}
