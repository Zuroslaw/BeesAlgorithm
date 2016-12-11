package util;

import model.Item;
import model.Parcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DAO {
	
	private static DAO instance;
	
	public static DAO getInstance() {
		if (instance == null) {
			instance = new DAO();
		}
		return instance;
	}
	
	private DAO() {	}
	
	public void readParcel() throws Exception {
		
		BufferedReader br = null;
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath.concat("\\input.txt")));

			sCurrentLine = br.readLine();

			Parcel.setMaxWeight((Double.parseDouble(sCurrentLine.split(" ")[0])));
			
		} finally {
			if (br != null)br.close();
		}
	}
	
	public ArrayList<Item> readItems() throws Exception {
		
		BufferedReader br = null;
		String filePath = new File("").getAbsolutePath();

		try {
			
			ArrayList<Item> result = new ArrayList<>();
			
			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath.concat("\\input.txt")));

			ArrayList<String> lines = new ArrayList<>();
			
			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}
			
			for (int i = 1; i < lines.size(); i++) {
				
				String[] item = lines.get(i).split(" ");
				
				String name = item[0];
				double weight = Double.parseDouble(item[1]);
				double price = Double.parseDouble(item[2]);
				
				result.add(new Item(name, weight, price));
			}
			
			return result;
			
		} finally {
			if (br != null)br.close();
		}
	}
}
