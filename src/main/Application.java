package main;

import java.util.ArrayList;

import algorithm.*;
import model.Parcel;
import model.Item;
import model.Parcel_Extended;
import model.Item_Extended;
import util.DAO;
import util.Timer;

public class Application {

	public static void main(String[] args) throws Exception {
		DAO dao = DAO.getInstance();
		dao.setFileName("input3.txt");
		dao.readParcel();
		ArrayList<Item> fullItemList = dao.readItems();
		
		//		System.out.println(new Parcel());
		//		System.out.println(fullItemList.get(1));

		int[] beesDistribution = {70, 50, 30, 20, 15, 10, 8, 7, 6, 5, 4, 3, 2};
		System.out.println("ALGO:\n");
		BeesAlgorithmBase algo1 = new BeesAlgorithmImpl(new Parcel_Extended(), fullItemList, 500, beesDistribution, 5, new ParcelHeuristicsNew());
		BeesAlgorithmBase algo2 = new BeesAlgorithmRandomDump(new Parcel_Extended(), fullItemList, 500, beesDistribution, 5, 3, new ParcelHeuristicsNew());
        BeesAlgorithmBase algo3 = new BeesAlgorithmImpl(new Parcel_Extended(), fullItemList, 500, beesDistribution, 5, new ParcelHeuristicsRandomReplacement());
        BeesAlgorithmBase algo4 = new BeesAlgorithmRandomDump(new Parcel_Extended(), fullItemList, 500, beesDistribution, 5, 3, new ParcelHeuristicsRandomReplacement());

        Timer.tic("Impl");
        Parcel p1 = algo1.run();
        Timer.toc();

        Timer.tic("RandDump");
        Parcel p2 = algo2.run();
        Timer.toc();

        Timer.tic("Impl");
        Parcel p3 = algo3.run();
        Timer.toc();

        Timer.tic("RandDump");
        Parcel p4 = algo4.run();
        Timer.toc();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);

        //		ArrayList<Double> qualityListAlgo1 = new ArrayList<>();
//		ArrayList<Double> qualityListAlgo2 = new ArrayList<>();
//
//		for (int i = 0; i < 10; i++) {
//			Timer.tic("Standard implementation " + i);
//			Parcel result = algo1.run();
//			qualityListAlgo1.add(result.getCurrentQuality());
//			Timer.toc();
//		}
//
//		for (int i = 0; i < 10; i++) {
//			Timer.tic("Random dump " + i);
//			Parcel result = algo2.run();
//			qualityListAlgo2.add(result.getCurrentQuality());
//			Timer.toc();
//		}
//
//		System.out.println("\nRESULTS:\n\nStandard implementation:");
//		System.out.println(qualityListAlgo1);
//		System.out.println("\nRandom dump:");
//		System.out.println(qualityListAlgo2);


	}

}
