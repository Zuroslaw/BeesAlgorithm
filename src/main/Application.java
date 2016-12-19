package main;

import java.util.ArrayList;

import algorithm.BeesAlgorithmImpl;
import algorithm.ParcelHeuristicsNew;
import model.Parcel;
import model.Item;
import util.DAO;
import util.Timer;

public class Application {

	public static void main(String[] args) throws Exception {
		DAO dao = DAO.getInstance();
		dao.readParcel();
		ArrayList<Item> fullItemList = dao.readItems();
		
		//		System.out.println(new Parcel());
		//		System.out.println(fullItemList.get(1));

		System.out.println("ALGO:\n");
		BeesAlgorithmImpl algo = new BeesAlgorithmImpl(new Parcel(), fullItemList, 100, 15 , 100, new ParcelHeuristicsNew());

		Timer.tic("FullRun");
		Parcel result = algo.run();
		Timer.toc();

		System.out.println(result);
	}

}
