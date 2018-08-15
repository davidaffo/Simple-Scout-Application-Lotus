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

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {
	
	public ImageIcon lotusIcon;
	public ImageIcon removeButton;
	public Image lotusLogo;
	public ImageIcon removeButtonHover;
	public ImageIcon removeButtonClicked;
	public ImageIcon newTeam;
	public ImageIcon rename;
	public ImageIcon copy;
	public ImageIcon start;
	public ImageIcon addToFormation;
	public ImageIcon reset;
	public ImageIcon checked;
	public ImageIcon serve;
	public ImageIcon receive;
	public ImageIcon attack;
	public ImageIcon dig;
	public ImageIcon block;
	public ImageIcon undo;
	public ImageIcon notes;
	public ImageIcon quit;
	public ImageIcon rotate;
	public ImageIcon resetRotation;
	public ImageIcon ourError;
	public ImageIcon oppError;
	public ImageIcon dragDrop;
	public ImageIcon analyzer;
	public ImageIcon dataModder;
	public ImageIcon upload;
	public ImageIcon export;
	public ImageIcon sheet;
	public ImageIcon preview;
	public ImageIcon save;
	public ImageIcon update;
	
	public Images (){
		lotusIcon  = new ImageIcon(getClass().getResource("/images/lotus.png"));
		lotusLogo = lotusIcon.getImage();
		removeButton = new ImageIcon(getClass().getResource("/images/x-button.png"));
		removeButtonHover = new ImageIcon(getClass().getResource("/images/x-button-hover.png"));
		removeButtonClicked = new ImageIcon(getClass().getResource("/images/x-button-hover-clicked.png"));
		newTeam = new ImageIcon(getClass().getResource("/images/plus.png"));
		rename = new ImageIcon(getClass().getResource("/images/price-tag.png"));
		copy = new ImageIcon(getClass().getResource("/images/copy.png"));
		start = new ImageIcon(getClass().getResource("/images/volleyball.png"));
		addToFormation = new ImageIcon(getClass().getResource("/images/volleyball-silhouette.png"));
		reset = new ImageIcon(getClass().getResource("/images/reset.png"));
		checked = new ImageIcon(getClass().getResource("/images/checked.png"));
		serve = new ImageIcon(getClass().getResource("/images/serve.png"));
		receive = new ImageIcon(getClass().getResource("/images/receive.png"));
		attack  = new ImageIcon(getClass().getResource("/images/attack.png"));
		dig = new ImageIcon(getClass().getResource("/images/dig.png"));
		block = new ImageIcon(getClass().getResource("/images/block.png"));
		undo = new ImageIcon(getClass().getResource("/images/undo.png"));
		notes = new ImageIcon(getClass().getResource("/images/writing.png"));
		quit = new ImageIcon(getClass().getResource("/images/logout.png"));
		rotate = new ImageIcon(getClass().getResource("/images/repeat.png"));
		resetRotation = new ImageIcon(getClass().getResource("/images/resetRotation.png"));
		ourError = new ImageIcon(getClass().getResource("/images/ourError.png"));
		oppError = new ImageIcon(getClass().getResource("/images/oppError.png"));
		dragDrop = new ImageIcon(getClass().getResource("/images/drag.png"));
		analyzer = new ImageIcon(getClass().getResource("/images/analyzer.png"));
		dataModder = new ImageIcon(getClass().getResource("/images/coding.png"));
		upload = new ImageIcon(getClass().getResource("/images/upload.png"));
		export = new ImageIcon(getClass().getResource("/images/export.png"));
		sheet = new ImageIcon(getClass().getResource("/images/sheet.png"));
		preview = new ImageIcon(getClass().getResource("/images/preview.png"));
		save = new ImageIcon(getClass().getResource("/images/save.png"));
		update = new ImageIcon(getClass().getResource("/images/update.png"));
		
		
		
	}

}
