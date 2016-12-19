package algorithm;

import java.util.ArrayList;

import model.Parcel;
import model.Item;

public abstract class BeesAlgorithmBase {

	@SuppressWarnings("unused")
	protected ParcelHeuristics ph;
	@SuppressWarnings("unused")
	protected Parcel originalParcel;
	@SuppressWarnings("unused")
	protected ArrayList<Item> fullItemList;
	
	int maxIter;
	int currentIter;
	int numOfScouts;
	int numOfSources;
	
	ArrayList<Parcel> currentSources;

	public BeesAlgorithmBase() {};

	public BeesAlgorithmBase(Parcel parcel, ArrayList<Item> fullItemList, ParcelHeuristics ph, int maxIter, int numOfScouts) {
		this.originalParcel = parcel;
		this.fullItemList = fullItemList;
		this.ph = ph;
		this.maxIter = maxIter;
		this.numOfScouts = numOfScouts;
	}

	public abstract Parcel run();
	
	public abstract boolean shouldStop();
}
