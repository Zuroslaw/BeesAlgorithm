package util;

import model.Item;
import model.Item_Extended;
import model.Parcel;
import model.Parcel_Extended;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DAO {
	
	private static DAO instance;
	private static String filename = "input3.txt";

	/**
	 * Returns a DAO singleton
	 *
	 * @return instance of DAO
	 */
	public static DAO getInstance() {
		if (instance == null) {
			instance = new DAO();
		}
		return instance;
	}

	public static void setFileName(String filename_) {
		filename = filename_;
	}
	
	private DAO() {	}

	/**
	 * Reads parcel parameters from file. The file must be in 'src' folder.
	 * The method modifies static field MAX_WEIGHT of class Parcel.
	 *
	 * @throws IOException if something wrong happened during file read.
	 */
	public void readParcel() throws IOException {
		
		BufferedReader br = null;
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath.concat("\\" + filename)));

			sCurrentLine = br.readLine();

			Parcel_Extended.setMaxWeight((Double.parseDouble(sCurrentLine.split(" ")[0])));
			
		} finally {
			if (br != null)br.close();
		}
	}

    /**
     * Reads item list from file. The file must be in 'src' folder.
     *
     * @return an ArrayList of Items with parameters specified in file.
     *
     * @throws IOException if something wrong happened during file read.
     */
	public ArrayList<Item> readItems() throws IOException {
		
		BufferedReader br = null;
		String filePath = new File("").getAbsolutePath();

		try {
			
			ArrayList<Item> result = new ArrayList<>();
			
			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath.concat("\\" + filename)));

			ArrayList<String> lines = new ArrayList<>();
			
			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}
			
			for (int i = 1; i < lines.size(); i++) {
				
				String[] item = lines.get(i).split(" ");
				
				String name = item[0];
				double weight = Double.parseDouble(item[1]);
				double price = Double.parseDouble(item[2]);
				
				result.add(new Item_Extended(name, weight, price));
			}
			
			return result;
			
		} finally {
			if (br != null)br.close();
		}
	}
}
