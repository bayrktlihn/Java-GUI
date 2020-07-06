package com.bayrktlihn;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

import com.bayrktlihn.entity.Person;

public class Utils {
	public static String getFileExtension(String fileName) {
		int indexOf = fileName.lastIndexOf(".");

		if (indexOf == -1 || indexOf == fileName.length() - 1)
			return null;

		return fileName.substring(indexOf);

	}

	public static String replaceAndUpperCase(String value, char oldChar, char newChar) {

		return value.replace(oldChar, newChar).toUpperCase();
	}

	public static ImageIcon createIcon(String path) {
		URL resource = Utils.class.getResource(path);

		ImageIcon icon = new ImageIcon(resource);

		return icon;
	}

	public static void savePersonsToFile(List<Person> persons, File file) {
		try {
			OutputStream os = Files.newOutputStream(file.toPath());

			ObjectOutputStream oos = new ObjectOutputStream(os);

			Person[] personArr = persons.toArray(new Person[persons.size()]);

			oos.writeObject(personArr);

			oos.flush();

			oos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void laodPersonsFromFile(List<Person> persons, File file) {
		try {
			InputStream is = Files.newInputStream(file.toPath());

			ObjectInputStream ois = new ObjectInputStream(is);

			Person personArr[] = (Person[]) ois.readObject();

			persons.clear();

			persons.addAll(Arrays.asList(personArr));

			ois.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Font createFont(String path) {

		URL url = System.class.getResource(path);

		if (url == null)
			System.err.println("Unable to load font: " + path);

		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return font;
	}
}
