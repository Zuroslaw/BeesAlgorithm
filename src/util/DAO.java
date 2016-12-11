package util;

import model.Przedmiot;
import model.Paczka;

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
	
	public Paczka readPaczka() throws Exception {
		
		BufferedReader br = null;
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath.concat("\\input.txt")));

			sCurrentLine = br.readLine();
			return new Paczka(Double.parseDouble(sCurrentLine.split(" ")[0]));
			
		} finally {
			if (br != null)br.close();
		}
	}
	
	public ArrayList<Przedmiot> readPrzedmioty() throws Exception {
		
		BufferedReader br = null;
		String filePath = new File("").getAbsolutePath();

		try {
			
			ArrayList<Przedmiot> result = new ArrayList<>();
			
			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath.concat("\\input.txt")));

			ArrayList<String> lines = new ArrayList<>();
			
			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}
			
			for (int i = 1; i < lines.size(); i++) {
				
				String[] item = lines.get(i).split(" ");
				
				String nazwa = item[0];
				double waga = Double.parseDouble(item[1]);
				double cena = Double.parseDouble(item[2]);
				
				result.add(new Przedmiot(nazwa, waga, cena));
			}
			
			return result;
			
		} finally {
			if (br != null)br.close();
		}
	}
}
