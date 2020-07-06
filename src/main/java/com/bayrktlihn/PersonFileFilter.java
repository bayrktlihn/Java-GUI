package com.bayrktlihn;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {

		String extension = Utils.getFileExtension(f.getName());
		
		if ( extension != null && extension.equals(".per"))
			return true;

		return false;
	}

	@Override
	public String getDescription() {
		return "Person databse files(*.per)";
	}

}
