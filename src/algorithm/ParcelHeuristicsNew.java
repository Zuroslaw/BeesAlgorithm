package algorithm;

import java.util.*;

import model.Item;
import model.Parcel;
import model.Item_Extended;
import model.Parcel_Extended;
public class ParcelHeuristicsNew implements ParcelHeuristics {


    public Parcel generateRandomParcel(ArrayList<Item> fullItemList) {


        Parcel newParcel = new Parcel();    //17.12
        ArrayList<Double> ItemsQuality = new ArrayList<>();
        ArrayList<Integer> shuffleList = new ArrayList<>();

        for (int i=0; i<fullItemList.size(); i++) {
            shuffleList.add(i);   // robie liste liczb,ktore potem spermutuje
        }

        Collections.shuffle(shuffleList);

        for ( int x : shuffleList) {
            Item itemToPut = fullItemList.get(x);
            if (newParcel.hasItem(itemToPut)) continue;
            newParcel.addItem(fullItemList.get(x));		//lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
        }

		return newParcel;
    }


	public ArrayList<Parcel> generateMultipleRandomParcels(ArrayList<Item> fullItemList, int numOfParcels) {

		ArrayList<Parcel> result = new ArrayList<>();

		outer : while (result.size() != numOfParcels) {
		    Parcel newParcel = generateRandomParcel(fullItemList);
		    for (Parcel par : result) {
		        if (newParcel.equals(par)) {
		            continue outer;
                }
            }
            result.add(newParcel);
        }
        return result;
	}

	public ArrayList<Parcel> generateRandomNeighbours(ArrayList<Item> fullItemList, Parcel parcel, int numOfParcels){
		ArrayList<Parcel> parcels = new ArrayList<>(); // lista paczek do returna
		int parcelListLength = parcel.getItemList().size();

        outer : for(int j=0; j < numOfParcels ;  j++){  // ile s�siedztw szukam

			Parcel newParcel = new Parcel(); // paczki kt�re trafi� do listy paczek do zwrotu, "s�siednie"

            if (parcelListLength > 6) {
                for(int i=0; i<6; i++) { // x przedmioty zostawiam, reszte zmieniam
                    Item itemToPut = parcel.getItemList().get(new Random().nextInt(parcelListLength));
                    if (newParcel.hasItem(itemToPut)) {
                        --i;
                        continue;
                    }
                    newParcel.addItem(itemToPut); // "przepisuje przedmioty z paczki oryginalnej do nowej "s�siedniej"
                }
            }

			ArrayList<Integer> shuffleList = new ArrayList<>();
			for (int i=0; i<fullItemList.size(); i++) {
				shuffleList.add(i);   // robie liste liczb,ktore potem spermutuje
			}
			Collections.shuffle(shuffleList);
			for ( int x : shuffleList) {
				Item itemToPut = fullItemList.get(x);
				if (newParcel.hasItem(itemToPut)) continue;
				newParcel.addItem(fullItemList.get(x));		//lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
			}

       /*     for (Parcel p : parcels) {          //SPRAWDZANIE CZY PACZKI SIE NIE DUBLUJA - SZLOBY JAKOS SPRYTNIEJ
			    if (p.equals(newParcel)) {      //TO ZROBIC BO WYDLUZA ITERACJE OSTRO
			        j--;
			        continue outer;
                }
            }
        */
			parcels.add(newParcel);
		}
		return parcels;   // zwracam liste
	
	}


}