package algorithm;

import java.util.ArrayList;

import model.Parcel;
import model.Item;

public abstract class BeesAlgorithmBase {

	public ArrayList<Double> bestOfRun = new ArrayList<>();
	public ArrayList<Double> meanOfRun = new ArrayList<>();

	protected ParcelHeuristics ph;
	protected Parcel originalParcel;
	protected ArrayList<Item> fullItemList;
	
	int maxIter;
	int[] beesDistribution;
	int sourcesToDump;

	public BeesAlgorithmBase(Parcel parcel, ArrayList<Item> fullItemList, ParcelHeuristics ph, int maxIter, int[] beesDistribution, int sourcesToDump) {
		this.originalParcel = parcel;
		this.fullItemList = fullItemList;
		this.ph = ph;
		this.maxIter = maxIter;
		this.beesDistribution = beesDistribution;
		this.sourcesToDump = sourcesToDump;
	}

	public abstract Parcel run();
	
	public abstract boolean shouldStop();
}
