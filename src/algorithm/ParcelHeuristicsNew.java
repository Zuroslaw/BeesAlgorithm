package algorithm;

import java.util.*;

import model.Item;
import model.Parcel;

public class ParcelHeuristicsNew implements ParcelHeuristics {


    public Parcel generateRandomParcel(ArrayList<Item> fullItemList) {
        double ItemQuality = 0;
        int j = 0;  //17.12
        ArrayList<Double> tab = new ArrayList<>();
        Parcel temp = new Parcel();    //17.12
        ArrayList<Double> ItemsQuality = new ArrayList<>();
        ArrayList<Integer> shuffleList = new ArrayList<>();
        for (int i = 0; i < fullItemList.size(); i++) {
            shuffleList.add(i);   // robie liste liczb,ktore potem spermutuje
        }

        Collections.shuffle(shuffleList);
        Parcel parcel = new Parcel();

        /*Set<Integer> set = new HashSet<Integer>(shuffleList);
        assert (set.size() == shuffleList.size());*/
        //System.out.println();
        for (int x : shuffleList) {

            //System.out.println(fullItemList.get(x).getName());
            if (!parcel.addItem(fullItemList.get(x)))        //lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
                continue;
            ItemQuality = (parcel.getItemList().get(j).getPrice()); //narazie na cenie bo na jakosci nie dziala, nie wiem czemu :)
            //System.out.println(ItemQuality);
            ItemsQuality.add(ItemQuality); // lista jakosci przedmiotow,tutaj wykorzystuje 'j'
           j++;
        }

        Collections.sort(ItemsQuality);
        Collections.reverse(ItemsQuality);    // od najlepszego do najgorszego pod wzgledem funkcji jakosci rzeczy

       /* for (int m = 0; m < ItemsPrices.size(); m++) {
            System.out.print("N");
            System.out.println(ItemsPrices.get(m));
        }
        */

        for(int k=0 ; k<ItemsQuality.size() ; k++)   // ukladam itemy w kolejnosci ceny od najtanszej(np, 10,10,10,15,30) i tworze temp
        {
            double s = ItemsQuality.get(k);  // sprawdzam czy tej ceny juz nie sprawdzalem na liscie, bo nie chce 3 razy tych samych rzeczy po 10 zeta wrzucac
            if (!tab.contains(s)) {
                tab.add(s);
                for (Item x : parcel.getItemList()) {  // wrzucam wszystkie rzeczy o danej cenie
                    if (x.getPrice() == s) {
                        temp.addItem(x);
                    }
                }
            }
        }
       /* for(int m = 0; m <ItemsPrices.size();m++ ) {
            System.out.print("I");
            System.out.println(temp.getItemList().get(m));
        }
        */
        parcel.removeItems();             //czyszcze plecak i od nowa wstawiam przedmioty pukladane,
       // System.out.println(parcel);

        for(Item h : temp.getItemList())
        {
            parcel.addItem(h);
        }
        //System.out.println("generateRandomParcel" + parcel );

		return parcel;
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
			
			for(int i=0; i<3; i++) { // 3 przedmioty zostawiam, reszte zmieniam
				Item itemToPut = parcel.getItemList().get(new Random().nextInt(parcelListLength));
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
				newParcel.addItem(fullItemList.get(x));		//lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
			}

            for (Parcel p : parcels) {          //SPRAWDZANIE CZY PACZKI SIE NIE DUBLUJA - SZLOBY JAKOS SPRYTNIEJ
			    if (p.equals(newParcel)) {      //TO ZROBIC BO WYDLUZA ITERACJE OSTRO
			        j--;
			        continue outer;
                }
            }
			parcels.add(newParcel);
		}
		return parcels;   // zwracam liste
	
	}
		

}