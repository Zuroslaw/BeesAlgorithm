package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Item;
import model.Parcel;

public class ParcelHeuristicsNew implements ParcelHeuristics {


	public Parcel generateRandomParcel(ArrayList<Item> fullItemList){

		ArrayList<Integer> shuffleList = new ArrayList<>();
		for (int i=0; i<fullItemList.size(); i++) {
			shuffleList.add(i);   // robie liste liczb,ktore potem spermutuje
		}

		Collections.shuffle(shuffleList);
		Parcel parcel = new Parcel();

		for ( int x : shuffleList) {
			if ( !parcel.addItem(fullItemList.get(x)) )		//lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
				break;
		}

		return parcel;
	
	}

	public ArrayList<Parcel> generateRandomNeighbours(ArrayList<Item> fullItemList, Parcel parcel, int numOfParcels){
		ArrayList<Parcel> parcels = new ArrayList<>(); // lista paczek do returna
		int parcelListlength = parcel.getItemList().size();
			
		for(int j=0; j < numOfParcels ;  j++){  // ile s�siedztw szukam

			Parcel newParcel = new Parcel(); // paczki kt�re trafi� do listy paczek do zwrotu, "s�siednie"
			
			for(int i=0; i<3; i++) { // 3 przedmioty zostawiam, reszte zmieniam
				Item itemToPut = parcel.getItemList().get(new Random().nextInt(parcelListlength));
				if (newParcel.hasItem(itemToPut)) {
					--i;
					continue;
				}
				newParcel.addItem(itemToPut); // "przepisuje przedmioty z paczki oryginalnej do nowej "s�siedniej"
			}

			ArrayList<Integer> shuffleList = new ArrayList<>();
			for (int i=0; i<fullItemList.size(); i++) {
				shuffleList.add(i);   // robie liste liczb,ktore potem spermutuje
			}

			for ( int x : shuffleList) {
				Item itemToPut = fullItemList.get(x);
				if (newParcel.hasItem(itemToPut)) continue;
				if ( !newParcel.addItem(fullItemList.get(x)) )		//lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
					break;
			}

			parcels.add(newParcel);
		}
		return parcels;   // zwracam liste
	
	}
		

}