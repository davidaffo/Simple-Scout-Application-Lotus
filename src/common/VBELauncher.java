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
package common;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import javax.swing.JLabel;

public class VBELauncher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VBELauncher thisWindow = this;
	private JMenuItem languageMenu;
	private JLabel version;
	private JButton update;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LotusVBE.createEnvironment();
		Language.setupActualLanguage();
		LotusVBE.img = new Images();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VBELauncher frame = new VBELauncher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void changeLanguage(ResourceBundle language){
		LotusVBE.lang = language;
		this.languageMenu.setText(LotusVBE.lang.getString("language")+": "+LotusVBE.lang.getString("thisLanguage"));
		this.version.setText(LotusVBE.lang.getString("version")+LotusVBE.version);
		this.update.setText(LotusVBE.lang.getString("checkVersion"));
		Language.rememberLanguage(LotusVBE.lang.getBaseBundleName());
	}

	/**
	 * Create the frame.
	 */
	public VBELauncher() {
		this.setIconImage(new ImageIcon(getClass().getResource("/images/lotus.png")).getImage());
		setTitle("Lotus Volleyball Environment - Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem us = new JMenuItem("English (US)");
		us.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeLanguage(Language.en_US);
			}
		});
		JMenuItem it = new JMenuItem("Italiano (IT)");
		it.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeLanguage(Language.it_IT);				
			}
		});
		addPopup(this, popupMenu);
		popupMenu.add(us);
		popupMenu.add(it);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		languageMenu = new JMenuItem(LotusVBE.lang.getString("language")+": "+LotusVBE.lang.getString("thisLanguage"));
		languageMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(menuBar, 0, 0);
			}
		});
		menuBar.add(languageMenu);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s;
				try {
					s = LotusVBE.readFile(Constants.ROOT+"NOTICE.TXT");
				} catch (IOException e1) {
					s = "File not found";
				}
				MessageFrame f = new MessageFrame(s);
				f.setVisible(true);
			}
		});
		menuBar.add(btnCredits);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton ssa = new JButton("SSA Lotus");
		ssa.setHorizontalTextPosition(SwingConstants.LEFT);
		ssa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LotusVBE.startSSA();
				thisWindow.dispose();
				
			}
		});
		
		JButton analyzer = new JButton("Lotus Scout Analyzer");
		analyzer.setHorizontalTextPosition(SwingConstants.LEFT);
		analyzer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LotusVBE.startAnalyzer();
				thisWindow.dispose();
			}
		});
		contentPane.setLayout(new MigLayout("", "[212px,grow,fill][212px,grow,fill]", "[100px,grow,fill][100.00px,grow,fill][100px,grow,fill][44.00,fill]"));
		contentPane.add(ssa, "cell 0 0 2 1,grow");
		contentPane.add(analyzer, "cell 0 1 2 1,grow");
		
		JButton dataModder = new JButton("Lotus Data Modder");
		dataModder.setHorizontalTextPosition(SwingConstants.LEFT);
		dataModder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LotusVBE.startDataModder();
				thisWindow.dispose();
			}
		});
		contentPane.add(dataModder, "cell 0 2 2 1,grow");
		
		update = new JButton(LotusVBE.lang.getString("checkVersion"));
		update.setHorizontalTextPosition(SwingConstants.LEFT);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
				    try {
						Desktop.getDesktop().browse(new URI("https://github.com/davidaffo/Simple-Scout-Application-Lotus/releases"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(update, "cell 1 3,grow");
		
		pack();
		int side = ssa.getHeight();
		ssa.setIcon(new ImageIcon(LotusVBE.img.lotusIcon.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		analyzer.setIcon(new ImageIcon(LotusVBE.img.analyzer.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));
		dataModder.setIcon(new ImageIcon(LotusVBE.img.dataModder.getImage().getScaledInstance(side-10, side-10, Image.SCALE_SMOOTH)));

		update.setIcon(new ImageIcon(LotusVBE.img.update.getImage().getScaledInstance(update.getHeight()-10, update.getHeight()-10, Image.SCALE_SMOOTH)));
		
		version = new JLabel(LotusVBE.lang.getString("version")+LotusVBE.version);
		contentPane.add(version, "flowy,cell 0 3,grow");
		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
