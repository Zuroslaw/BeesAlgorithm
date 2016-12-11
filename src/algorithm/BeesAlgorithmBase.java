package algorithm;

import java.util.ArrayList;

import model.Paczka;
import model.Przedmiot;

public abstract class BeesAlgorithmBase {

	@SuppressWarnings("unused")
	private PaczkaHeurystyka ph;
	@SuppressWarnings("unused")
	private Paczka orginalnaPaczka;
	@SuppressWarnings("unused")
	private ArrayList<Przedmiot> przedmioty;
	
	int maxIter;
	int currentIter;
	int liczbaSkautow;
	int liczbaZrodel;
	
	ArrayList<Paczka> aktualneZrodla;
	
	public BeesAlgorithmBase(Paczka paczka, ArrayList<Przedmiot> przedmioty, int maxIter, int liczbaSkautow) {
		this.orginalnaPaczka = paczka;
		this.przedmioty = przedmioty;
		this.maxIter = maxIter;
		this.liczbaSkautow = liczbaSkautow;
	}

	public abstract Paczka run();
	
	public abstract void znajdzLosoweRozwiazania();
	
	public abstract boolean shouldStop();
}
