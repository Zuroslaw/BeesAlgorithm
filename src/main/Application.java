package main;

import java.util.ArrayList;

import model.Paczka;
import model.Przedmiot;
import util.DAO;

public class Application {

	public static void main(String[] args) throws Exception {
		DAO dao = DAO.getInstance();
		Paczka paczka = dao.readPaczka();
		ArrayList<Przedmiot> przedmioty = dao.readPrzedmioty();
		
		System.out.println(paczka);
		System.out.println(przedmioty.get(1));
	}

}
