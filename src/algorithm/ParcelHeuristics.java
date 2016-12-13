package algorithm;

import java.util.ArrayList;

import model.Item;
import model.Parcel;

public interface ParcelHeuristics {
	Parcel generateRandomParcel (ArrayList<Item> fullItemList);
	ArrayList<Parcel> generateMultipleRandomParcels(ArrayList<Item> fullItemList, int numOfParcels);
	ArrayList<Parcel> generateRandomNeighbours(ArrayList<Item> fullItemList, Parcel parcel, int numOfParcels);


	//public Parcel generateRandomParcel(Parcel paczka, ArrayList<Item> przedmioty){
}