package main;

import java.util.ArrayList;

import algorithm.BeesAlgorithmImpl;
import algorithm.ParcelHeuristicsNew;
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

		System.out.println("ALGO:\n");
		BeesAlgorithmImpl algo = new BeesAlgorithmImpl(new Parcel(), fullItemList, 7, 10 , 5, new ParcelHeuristicsNew());
		Parcel result = algo.run();

		System.out.println(result);
	}

}
