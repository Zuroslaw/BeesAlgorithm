package algorithm;

import java.util.ArrayList;
import java.util.Collections;

import model.Parcel;
import model.Item;

public class BeesAlgorithmImpl extends BeesAlgorithmBase {

	/*@SuppressWarnings("unused")
	private ParcelHeuristics ph;
	@SuppressWarnings("unused")
	private Parcel originalParcel;
	@SuppressWarnings("unused")
	private ArrayList<Item> fullItemList;*/
	
	/*private int maxIter;
    private int numOfScouts;
    private int numOfSources;*/
    private int numOfBees;
    private ArrayList<Double> ParcelsQuality = new ArrayList<>();
    private ArrayList<Parcel> currentSources = new ArrayList<>();
    private ArrayList<Parcel> temp = new ArrayList<>();
    private ArrayList<Parcel> BestForIter= new ArrayList<>();//lista najlepszych wynikow na iteracje
    private ArrayList<Parcel> neighbours= new ArrayList<>();

	public BeesAlgorithmImpl(Parcel parcel, ArrayList<Item> fullItemList, int maxIter, int numOfScouts , int numOfBees, ParcelHeuristics ph) {

		super(parcel, fullItemList, ph, maxIter, numOfScouts);
		this.numOfBees = numOfBees;
	}


// zakladam 10 skaut�w i 100 pszcz�, 50 na 1 , 30 na 2, 10 na 3, 4 na 4 i po 1 na reszte �r�del(paczek)
 public Parcel run(){

     int currentIter;
     System.out.println(maxIter);
     for(currentIter = 0 ; currentIter < maxIter ; currentIter++ )
	{
	    System.out.println(currentIter);
	 for(int i=0 ; i<numOfScouts ; i++)
	 {
	     System.out.print("x" + i);
		 currentSources.add(ph.generateRandomParcel(fullItemList));// paczki skaut�w
		 ParcelsQuality.add(currentSources.get(i).getCurrentQuality()); // wartosci paczek skautow
	 }	 
		 Collections.sort(ParcelsQuality);
		 Collections.reverse(ParcelsQuality);
		for(int i=0 ; i<numOfScouts ; i++) 
		{
		double s =	ParcelsQuality.get(i);		//zmiana s na double??? (bylo int)
		 for(Parcel x : currentSources){
			if( x.getCurrentQuality() == s)
			{
			 temp.add(i , x);
		    }
		 }
		}
		currentSources.clear();
		currentSources.addAll(temp);
	 //chcia�em jakos madrze posortowac paczki wg wartosci ale nic lepszego na szybko nie wymyslilem
		
		 
		 
		 
	// 50 pszcz�, tyle razy szukam s�siad�w lepszych od paczki
		 neighbours.addAll(ph.generateRandomNeighbours(fullItemList,currentSources.get(0), 50));// lista s�siad�w dla 1 paczki
		 for(int j=0; j<50 ; j++){
			 if(currentSources.get(0).getCurrentQuality()<neighbours.get(j).getCurrentQuality() )
				{
				 currentSources.set(0,neighbours.get(j) ); // sprawdzam czy s�siad jest lepszy
				}
			}
		 neighbours.clear();
		// 30 pszcz�, tyle razy szukam s�siad�w lepszych od paczki
		 neighbours.addAll(ph.generateRandomNeighbours(fullItemList,currentSources.get(1), 30));// lista s�siad�w dla 2 paczki
		 for(int k=0; k<30 ; k++){
			 if(currentSources.get(1).getCurrentQuality()<neighbours.get(k).getCurrentQuality() )
				{
				 currentSources.set(1,neighbours.get(k) ); // sprawdzam czy s�siad jest lepszy
				}
			}
		 neighbours.clear();
		// 10 pszcz�, tyle razy szukam s�siad�w lepszych od paczki
		 neighbours.addAll(ph.generateRandomNeighbours(fullItemList,currentSources.get(2), 10));// lista s�siad�w dla 3 paczki
		 for(int m=0; m<10 ; m++){
			 if(currentSources.get(2).getCurrentQuality()<neighbours.get(m).getCurrentQuality() )
				{
				 currentSources.set(2,neighbours.get(m) ); // sprawdzam czy s�siad jest lepszy
				}
			}
		 neighbours.clear();
			// 5 pszcz�, tyle razy szukam s�siad�w lepszych od paczki
		 neighbours.addAll(ph.generateRandomNeighbours(fullItemList,currentSources.get(3), 5));// lista s�siad�w dla 3 paczki
		 for(int n=0; n<5 ; n++){
			 if(currentSources.get(3).getCurrentQuality()<neighbours.get(n).getCurrentQuality() )
				{
				 currentSources.set(3,neighbours.get(n) ); // sprawdzam czy s�siad jest lepszy
				}
			}
		 neighbours.clear();
		// 5 pszcz�, tyle razy szukam s�siad�w lepszych od paczki
		 neighbours.addAll(ph.generateMultipleRandomParcels(fullItemList, 5));// lista s�siad�w dla 5 randomowych pszcz� kt�re zab��dzi�y
		 for(int g=0; g<5 ; g++)
		 {
			 for(int h = 0 ; h<10 ; h++ )
				 {
					 if(currentSources.get(h).getCurrentQuality()<neighbours.get(g).getCurrentQuality() )
					 {
						 currentSources.set(h,neighbours.get(g) ); // sprawdzam czy s�siad jest lepszy od kazdej paczki na liscie
					 }
				 }
		}
		neighbours.clear();
		double max = 0;
		int place = 0;		///zainicjalizowane na 0
		for(int i = 0 ; i < numOfScouts ; i++){
			
		 if(currentSources.get(i).getCurrentQuality() > max)
		 {
			 //max = currentSources.get(i).getcurrentQuality();
			 place = i;
		 }
		}
		
		BestForIter.add(currentSources.get(place));//lista rozwiazan, dla wszyskich iteracji, dla kazdej po 1 paczce najlepszej. get(0) to 1 iteracja
    }

    Parcel result = BestForIter.get(0);

    for (Parcel p : BestForIter) {
         if (p.getCurrentQuality() > result.getCurrentQuality())
             result = p;
    }

    return result;

 }

	@Override
	public void findRandomSolutions() {

	}

	@Override
	public boolean shouldStop() {
		return false;
	}


}
	 
	 
	 