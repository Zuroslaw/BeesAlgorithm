package algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.Paczka;
import model.Przedmiot;

public interface PaczkaHeurystyka {


	public Paczka znajdzLosowe(Paczka paczka, ArrayList<Przedmiot> przedmioty){
		    ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int i=0; i<przedmioty.size(); i++) {
	            list.add(new Integer(i));   // robie liste liczb,ktore potem spermutuje
	        }
	        Collections.shuffle(list);
	
	for( int x : list){
		 paczka.addPrzedmiot(przedmioty.get(x));   //lece po liscie i dodaje przedmioty z listy liczb randomowo ustawionych
		}
		paczka.setListaNumerow(list);    // przekazuje paczce liste liczb wykorzystan� do wy�onienia liczb z listy
		return paczka;
	
	}
	public ArrayList<Paczka> znajdzLosoweSasiedztwa(Paczka paczka, int ilosc){
		ArrayList<Paczka> paczki = new ArrayList<>(); // lista paczek do returna
		Paczka paczusia = new Paczka(); // paczki kt�re trafi� do listy paczek do zwrotu, "s�siednie"
			
		for(int j=0; j<ilosc ;  j++){  // ile s�siedztw szukam
			
		for(int i=0; i<3; i++) { // 3 przedmioty zostawiam, reszte zmieniam
			 paczusia.addPrzedmiot(paczka.getListaPrzedmiotow().get(i)); // "przepisuje przedmioty z paczki oryginalnej do nowej "s�siedniej"
		}
		Collections.shuffle(paczka.getListaNumerow().subList(3,(paczka.getListaNumerow().size())+1)); // mieszam pozostale liczby z listy, tej samej z ktorej korzystam w funkcji znajdzlosowe, trzeba zrobic tak
		//zeby kazda paczka mia�a t� liste jako sk�adow�, �eby potem mo�na j� by�o tutaj wykorzysta�
		for( int x : paczka.getListaNumerow().subList(3,(paczka.getListaNumerow().size())+1)){
			 paczusia.addPrzedmiot(przedmioty.get(x)) // w pliku application jest lista : przedmioty, 
			}
		paczki.add(paczusia);    // dodaje paczusie do listy paczek s�siednich
		}
		return paczki;   // zwracam liste
	
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		
		
	}