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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class Language {
	
	public final static ResourceBundle it_IT= ResourceBundle.getBundle("languages/Language_it_IT");
	public final static ResourceBundle en_US= ResourceBundle.getBundle("languages/Language_en_US");
		
		public static void setupActualLanguage(){
			String l="languages/Language_en_US"; //default language
			try {
				l = LotusVBE.readFile(Constants.ROOT+"/actualLanguage.ini");
			} catch (IOException e) {
				//reset file and load default language
				rememberLanguage(l);
			}
			
			LotusVBE.lang = ResourceBundle.getBundle(l);
			
			
		}
		
		public static void rememberLanguage(String l){
			File output = new File(Constants.ROOT+"/actualLanguage.ini");
			try {
				output.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				PrintWriter writer = new PrintWriter(output, "UTF-8");
				writer.print(l);
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
}
