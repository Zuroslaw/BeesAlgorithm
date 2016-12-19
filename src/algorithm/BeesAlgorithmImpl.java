package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import model.Parcel;
import model.Item;

public class BeesAlgorithmImpl extends BeesAlgorithmBase {

    private int numOfBees;
    private ArrayList<Parcel> currentSources = new ArrayList<>();
    private ArrayList<Parcel> BestForIter= new ArrayList<>();//lista najlepszych wynikow na iteracje
    private ArrayList<Parcel> neighbours= new ArrayList<>();

	public BeesAlgorithmImpl(Parcel parcel, ArrayList<Item> fullItemList, int maxIter, int numOfScouts , int numOfBees, ParcelHeuristics ph) {

		super(parcel, fullItemList, ph, maxIter, numOfScouts);
		this.numOfBees = numOfBees;
	}


// zakladam 10 skaut�w i 100 pszcz�, 50 na 1 , 30 na 2, 10 na 3, 4 na 4 i po 1 na reszte �r�del(paczek)
    public Parcel run(){

         int currentIter;
         currentSources = ph.generateMultipleRandomParcels(fullItemList, numOfScouts);  //paczki beda rozne
         for(currentIter = 0 ; currentIter < maxIter ; currentIter++ )
         {
             System.out.println(currentIter+1);

             Collections.sort(currentSources);
             currentSources.set(0, sendBees(currentSources.get(0), 50));
             currentSources.set(1, sendBees(currentSources.get(1), 30));
             currentSources.set(2, sendBees(currentSources.get(2), 20));
             currentSources.set(3, sendBees(currentSources.get(3), 10));
             currentSources.set(4, sendBees(currentSources.get(4), 5));
             currentSources.set(5, sendBees(currentSources.get(5), 5));
             currentSources.set(6, sendBees(currentSources.get(6), 5));
             currentSources.set(7, sendBees(currentSources.get(7), 5));
             currentSources.set(8, sendBees(currentSources.get(8), 5));
             currentSources.set(9, sendBees(currentSources.get(9), 5));

             Collections.sort(currentSources);

             for (int i = 5; i < 10; i++) {
                 currentSources.set(i, ph.generateRandomParcel(fullItemList));
             }
             Collections.sort(currentSources);
             currentSources.forEach(parcel -> System.out.print("" + parcel.getCurrentQuality() + " "));     //TO TEZ
             System.out.println();  //I TO TEZZZZZZ

         }

         Collections.sort(currentSources);
         return currentSources.get(0);

    }

	private Parcel sendBees(Parcel source, int numOfBeesToSend) {
        /*
         * Pytanie: czy zostawic tak jak tu, ze neighbours jest tworzone w tej metodzie
         *          czy raczej zrobic tak zeby neighbours bylo przekazywane do tej metody a tworzone przed jej
         *          wywoalniem (i neigbours.clear() przed kazdym kolejnym wywolaniem).
         *          Z tym przekazywaniem chyba moze byc czytelniej. Do sprawdzenia potem co jest wydajniejsze.
         */
        ArrayList<Parcel> neighbours = new ArrayList<>(ph.generateRandomNeighbours(fullItemList,source, numOfBeesToSend));

        Parcel best = neighbours.stream().max(Parcel::compareTo).get();

        return best.compareTo(source) < 0 ? best : source;
	}


	@Override
	public boolean shouldStop() {
		return false;
	}


}
	 
	 
	 