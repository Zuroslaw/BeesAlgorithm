package algorithm;

import java.util.ArrayList;

import model.Parcel;
import model.Item;

public abstract class BeesAlgorithmBase {

	@SuppressWarnings("unused")
	private ParcelHeuristics ph;
	@SuppressWarnings("unused")
	private Parcel originalParcel;
	@SuppressWarnings("unused")
	private ArrayList<Item> fullItemList;
	
	int maxIter;
	int currentIter;
	int numOfScouts;
	int numOfSources;
	
	ArrayList<Parcel> currentSources;
	
	public BeesAlgorithmBase(Parcel parcel, ArrayList<Item> fullItemList, int maxIter, int numOfScouts) {
		this.originalParcel = parcel;
		this.fullItemList = fullItemList;
		this.maxIter = maxIter;
		this.numOfScouts = numOfScouts;
	}

	public abstract Parcel run();
	
	public abstract void findRandomSolutions();
	
	public abstract boolean shouldStop();
}
