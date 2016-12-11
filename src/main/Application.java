package main;

import java.util.ArrayList;

import model.Parcel;
import model.Item;
import util.DAO;

public class Application {

	public static void main(String[] args) throws Exception {
		DAO dao = DAO.getInstance();
		dao.readParcel();
		ArrayList<Item> fullItemList = dao.readItems();
		
		System.out.println(new Parcel());
		System.out.println(fullItemList.get(1));
	}

}
