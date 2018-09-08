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

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class LotusVBE {
	public static ResourceBundle lang = Language.en_US; //initialized for development with window builder pro
	public static String TABLE_PATH = Constants.ROOT+"/Table/";
	public static Images img;

	//other
	public final static String version = "v1.1.0"; //always respect this format to check updates correctly
	
	static void startSSA(){
		ssa.SSALotus instance = new ssa.SSALotus();
		try {
			instance.pseudoMain();
		} catch (ClassNotFoundException e) {
				printError(null, lang.getString("classNotFound"));
			e.printStackTrace();
		} catch (IOException e) {
				printError(null, lang.getString("gameNotFound"));
			e.printStackTrace();
		}
	}
	
	static void startAnalyzer(){
		analyzer.MainController c = new analyzer.MainController();
		c.buildWindow();
	}
	

	public static void createEnvironment(){ //creates necessary directories if they are not found
		new File(Constants.ROOT+"Games").mkdirs();
		new File(Constants.ROOT+"LotusData").mkdirs();
		new File(Constants.ROOT+"LotusData"+File.separator+"Teams").mkdirs();
	}
	

	public static String readFile(String path) throws IOException { //salvataggio file in una stringa
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}
	
	public static String tableHead(){
				return "<head>"
							+"<style>"
							+"table {width: 100%; border-collapse: collapse; font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;}"
							+"table, td, th {text-align: center; border: 1px solid black;}"
							+"th {background-color: #66a3ff;}"
							+".thError {background-color: #ff3333;}"
							+".thAvg {background-color: #ff8533;}"
							+".thGood {background-color: #66ff66;}"
							+"</style>"
					+ "</head>"
					;
	}
	
	public static String outputHeader(){
		return "<head>"
				+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
				+"<style>"
				+"table{border:0; cellspacing:0; cellpadding:0;}"
				+"table,h2 {width:100%; border-collapse: collapse; font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif; margin-right: auto;}"
				+"h2 {background-color: #8cff72;}"
				+"table, td, th {text-align: center; border: 1px solid black;  word-break: keep-all;}"
				+"th {background-color: #66a3ff;}"
				+".thError {background-color: #ff3333;}"
				+".thAvg {background-color: #ff8533;}"
				+".thGood {background-color: #66ff66;}"
				+"tr:nth-child(even){background-color: #c3d4f7}"
				+"tr:hover {background-color:#8cff72;}"
				+"p {font-size: 20px}"
				//sidenav style
				+".sidenav {height: 100%; width: 0; position: fixed; z-index: 1; top: 0; left: 0; background-color: #111; overflow-x: hidden;  padding-top: 60px;}"
				+ ".sidenav a {padding: 8px 8px 8px 32px; text-decoration: none; font-size: 25px; color: #818181; display: block;}"
				+ ".sidenav a:hover {color: #f1f1f1;}"
				+ ".sidenav .closebtn {position: -webkit-sticky; position: -moz-sticky;  position: -ms-sticky; position: -o-sticky; position: sticky; top:0; right:0; bottom:0; left:150px; font-size: 36px; color: #ed1212;}"
				+ "#main {padding: 16px;}"
				+ "@media screen and (max-height: 450px) {.sidenav {padding-top: 15px;}"
				+ ".sidenav a {font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif; font-size: 25px;}}"
				//navbar style
				+".navbar {background-color: rgba(150,0,0,.25); position: -webkit-sticky; position: -moz-sticky;  position: -ms-sticky; position: -o-sticky; position: sticky; top: 0; left: 0; float: left;}"
				+ ".navbar a {float: left; display: block; color: #f2f2f2; text-align: center; padding: 14px 16px; text-decoration: none; font-size: 17px;}"
				+"</style>"
		+ "</head>"
		;
	}
	
	public static String outputFooter(){
		return "<script>"
				+ "function openNav() {document.getElementById(\"mySidenav\").style.width = \"250px\"; document.getElementById(\"main\").style.marginLeft = \"250px\"; document.getElementById(\"header\").style.marginLeft = \"250px\";}"
				+ "function closeNav() {document.getElementById(\"mySidenav\").style.width = \"0\";document.getElementById(\"main\").style.marginLeft= \"0\"; document.getElementById(\"header\").style.marginLeft= \"0\";}"
				+ "</script>";
	}
	
	public static boolean isOpen(File f){
		try {
		    org.apache.commons.io.FileUtils.touch(new File(Constants.BACKUP_PATH));
		    return false;
		} catch (IOException e) {
		    return true;
		}
	}

	public static void startDataModder() {
		dataModder.MainController c = new dataModder.MainController();
		c.buildWindow();
		
	}
	
	public static void printError(Component parentComponent,String s){
		JOptionPane.showMessageDialog(parentComponent, s, "Unexpected Error", JOptionPane.ERROR_MESSAGE);
	}

}
