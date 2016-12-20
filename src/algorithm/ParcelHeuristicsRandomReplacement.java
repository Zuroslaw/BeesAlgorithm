package algorithm;

import model.Item;
import model.Parcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by mateu on 20.12.2016.
 */
public class ParcelHeuristicsRandomReplacement implements ParcelHeuristics {
    @Override
    public Parcel generateRandomParcel(ArrayList<Item> fullItemList) {
        ArrayList<Integer> shuffleList = new ArrayList<>();
        for (int i = 0; i < fullItemList.size(); i++) {
            shuffleList.add(i);
        }
        Collections.shuffle(shuffleList);

        int modulo = new Random().nextInt(5) + 5;
        Parcel result = new Parcel();
        for (int i : shuffleList) {
            if (i%modulo == 0) {
                if (!result.getItemList().isEmpty()) {
                    result.removeItem(result.getItemList().get(result.getItemList().size()-1));
                }
            }
            Item itemToPut = fullItemList.get(shuffleList.get(i));
            if (!result.hasItem(itemToPut)) {
                result.addItem(itemToPut);
            }
        }

        return result;
    }

    @Override
    public ArrayList<Parcel> generateMultipleRandomParcels(ArrayList<Item> fullItemList, int numOfParcels) {

        ArrayList<Parcel> result = new ArrayList<>();

        outer : while (result.size() != numOfParcels) {
            Parcel newParcel = generateRandomParcel(fullItemList);
            for (Parcel par : result) {
                if (newParcel.equals(par)) {
                    continue outer;
                }
            }
            result.add(newParcel);
        }
        return result;
    }

    @Override
    public ArrayList<Parcel> generateRandomNeighbours(ArrayList<Item> fullItemList, Parcel parcel, int numOfParcels) {

        ArrayList<Parcel> neighbours = new ArrayList<>();

        for (int j = 0; j < numOfParcels; j++) {

            Parcel result = new Parcel();
            for (int i = 0; i < parcel.getItemList().size(); i++) {
                result.addItem(parcel.getItemList().get(i));
            }
            for (int i = 0; i < result.getItemList().size(); i++) {
                if (new Random().nextInt(100) + 1 <= 30) {
                    result.removeItem(result.getItemList().get(i));
                    Item itemToPut = fullItemList.get(new Random().nextInt(fullItemList.size()));
                    if (!result.hasItem(itemToPut)) {
                        result.addItem(itemToPut);
                    }
                }
            }
            neighbours.add(result);
        }

        return neighbours;
    }
}
