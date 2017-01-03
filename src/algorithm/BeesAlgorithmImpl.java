package algorithm;

import java.util.ArrayList;
import java.util.Optional;

import Exceptions.MoreDumpedSourcesThanScoutsException;
import model.Parcel;
import model.Item;

public class BeesAlgorithmImpl extends BeesAlgorithmBase {

	public BeesAlgorithmImpl(Parcel parcel, ArrayList<Item> fullItemList, int maxIter, int[] beesDistribution, int sourcesToDump, ParcelHeuristics ph) {

		super(parcel, fullItemList, ph, maxIter, beesDistribution, sourcesToDump);
	}


    public Parcel run(){

         if (sourcesToDump > beesDistribution.length) throw new MoreDumpedSourcesThanScoutsException();

         bestOfRun.clear();
         meanOfRun.clear();

         int currentIter;
         int numOfScouts = beesDistribution.length;
         ArrayList<Parcel> currentSources = ph.generateMultipleRandomParcels(fullItemList, numOfScouts);  //paczki beda rozne

         for(currentIter = 0 ; currentIter < maxIter ; currentIter++ )
         {
             System.out.println("" + (currentIter+1) + ": ");

             currentSources.sort(Parcel::reversedCompareTo);

             for (int i = 0; i < numOfScouts; ++i) {
                 currentSources.set(i, sendBees(currentSources.get(i), beesDistribution[i]));
             }

             currentSources.sort(Parcel::compareTo);

             for (int i = 0; i < sourcesToDump; i++) {
                 currentSources.set(i, ph.generateRandomParcel(fullItemList));
             }
             currentSources.sort(Parcel::reversedCompareTo);
             currentSources.forEach(parcel -> System.out.print("" + parcel.getCurrentQuality() + " "));     //TO TEZ
             System.out.println();  //I TO TEZZZZZZ

             bestOfRun.add(currentSources.get(0).getCurrentQuality());
             meanOfRun.add(currentSources.stream()
                                    .mapToDouble(Parcel::getCurrentQuality)
                                    .average()
                                    .getAsDouble());
         }

         currentSources.sort(Parcel::reversedCompareTo);
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
        return best.compareTo(source) > 0 ? best : source;
	}


	@Override
	public boolean shouldStop() {
		return false;
	}


}
	 
	 
	 