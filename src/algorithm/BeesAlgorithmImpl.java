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
         for(currentIter = 0 ; currentIter < maxIter ; currentIter++ )
         {
             System.out.println(currentIter+1);
             currentSources = ph.generateMultipleRandomParcels(fullItemList, numOfScouts);  //paczki beda rozne

             currentSources.sort((p1, p2) -> (int) (p1.getCurrentQuality() - p2.getCurrentQuality()));

             currentSources.set(0, sendBees(currentSources.get(0), 50));
             currentSources.set(0, sendBees(currentSources.get(1), 30));
             currentSources.set(0, sendBees(currentSources.get(2), 20));
             currentSources.set(0, sendBees(currentSources.get(3), 10));

             currentSources.sort((p1, p2) -> (int) (p1.getCurrentQuality() - p2.getCurrentQuality()));      //TO DO SPRAWDZANIA TYLKO
             currentSources.forEach(parcel -> System.out.print("" + parcel.getCurrentQuality() + " "));     //TO TEZ
             System.out.println();  //I TO TEZZZZZZ

             neighbours.clear();
            // 5 pszcz�, tyle razy szukam s�siad�w lepszych od paczki
             neighbours.addAll(ph.generateMultipleRandomParcels(fullItemList, 5));// lista s�siad�w dla 5 randomowych pszcz� kt�re zab��dzi�y
             for(int g=0; g<5 ; g++)
             {
                 for(int h = 0 ; h<10 ; h++ )
                     {
                         if(currentSources.get(h).getCurrentQuality()<neighbours.get(g).getCurrentQuality() )
                         {
                             //System.out.println("przed zamiana na sasiada "+ (h+1)  +" "+   currentSources.get(h));
                             currentSources.set(h,neighbours.get(g) ); // sprawdzam czy s�siad jest lepszy od kazdej paczki na liscie
                             //System.out.println("Lepszy sasiad " + (h+1) + " paczki w random: " + currentSources.get(h));
                             break ; //wychodze z petli, nie chce podmieniac wszystkich gorszych paczek, tylko 1 napotkana
                         }
                     }
            }

            double max = currentSources.get(0).getCurrentQuality();
            int place = 0;		///zainicjalizowane na 0
            for(int i = 0 ; i < numOfScouts ; i++){

                if(currentSources.get(i).getCurrentQuality() > max)
                {
                    //max = currentSources.get(i).getcurrentQuality();
                    place = i;
                }
            }

            BestForIter.add(currentSources.get(place));//lista rozwiazan, dla wszyskich iteracji, dla kazdej po 1 paczce najlepszej. get(0) to 1 iteracja
            currentSources.clear();
            neighbours.clear();
         }

        Parcel result = BestForIter.get(0);

        for (Parcel p : BestForIter) {
             if (p.getCurrentQuality() > result.getCurrentQuality())
                 result = p;
        }

        return result;

    }

	private Parcel sendBees(Parcel source, int numOfBeesToSend) {
        /*
         * Pytanie: czy zostawic tak jak tu, ze neighbours jest tworzone w tej metodzie
         *          czy raczej zrobic tak zeby neighbours bylo przekazywane do tej metody a tworzone przed jej
         *          wywoalniem (i neigbours.clear() przed kazdym kolejnym wywolaniem).
         *          Z tym przekazywaniem chyba moze byc czytelniej. Do sprawdzenia potem co jest wydajniejsze.
         */
        ArrayList<Parcel> neighbours = new ArrayList<>(ph.generateRandomNeighbours(fullItemList,source, numOfBeesToSend));
        for(int n=0; n < numOfBeesToSend ; n++){

            if (neighbours.get(n).getCurrentQuality() < source.getCurrentQuality())
                return neighbours.get(n);
        }
        return source;
	}

	@Override
	public boolean shouldStop() {
		return false;
	}


}
	 
	 
	 