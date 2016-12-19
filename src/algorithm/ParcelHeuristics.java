package algorithm;

import java.util.ArrayList;

import model.Item;
import model.Parcel;

public interface ParcelHeuristics {

    /**
     * Generates random parcel from items included in an item list.
     * @param fullItemList list of items from which an algorithm will pick some to include in parcel.
     * @return parcel with randomly picked items.
     */
	Parcel generateRandomParcel (ArrayList<Item> fullItemList);

    /**
     * Generates multiple random parcels, each with random items from an item list. Implementation should make sure that
     * each of generated parcels is different from the others.
     * @param fullItemList list of items from which an algorithm will pick some to include in parcel.
     * @param numOfParcels number of parcels to generate.
     * @return an ArrayList of randomly generated parcels.
     */
	ArrayList<Parcel> generateMultipleRandomParcels(ArrayList<Item> fullItemList, int numOfParcels);

    /**
     * Generates multiple random neighbours of a given parcel. Neighbours should be chosen with a certain heuristics.
     * Implementation should make sure that each neighbour is different from the others (and from the original parcel).
     * @param fullItemList list of items from which an algorithm will pick some to include in neighbour parcels.
     * @param parcel original parcel which will be used to find new neighbours.
     * @param numOfParcels number of neighbours to find.
     * @return an ArrayList of randomly generated neighbours.
     */
	ArrayList<Parcel> generateRandomNeighbours(ArrayList<Item> fullItemList, Parcel parcel, int numOfParcels);

}