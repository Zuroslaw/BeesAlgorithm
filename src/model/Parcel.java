package model;

import Exceptions.ItemAlreadyInParcelException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parcel {
	
	private static double MAX_WEIGHT;
	private double currentWeight = 0;
	private double currentQuality = 0;

	private List<Item> itemList;

	/**
	 * Constructs a Parcel object with empty list of items.
	 */
	public Parcel() {
	    itemList = new ArrayList<>();
	}

    /**
     * Constructs a Parcel object with items given as a list.
     *
     * @param itemList a list of items that the parcel will contain.
     */
	public Parcel(List<Item> itemList) {
	    this.itemList = itemList;
    }

    /**
     * Sets a maximum weight of items that the parcel can contain.
     *
     * @param maxWeight maximum weight of items.
     */
	public static void setMaxWeight(double maxWeight) {
	    MAX_WEIGHT = maxWeight;
    }

    /**
     * @return weight of all items in parcel.
     */
	public double getCurrentWeight() {
		return currentWeight;
	}

    /**
     * @return quality of parcel based on a proper function.
     */
	public double getCurrentQuality() {
		return currentQuality;
	}

    /**
     * @return list of all items in parcel.
     */
	public List<Item> getItemList() {
		return itemList;
	}

    /**
     * Replaces current items with new list of items.
     *
     * @param itemList a list of items that the parcel will contain.
     */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * Checks if a given item is in the parcel.
	 *
	 * @param item an item whose presence is to be checked.
	 *
	 * @return true if the item is in the parcel.
	 */
    public boolean hasItem(Item item) {
	    return itemList.contains(item);
    }

    /**
	 * Adds an item to the parcel if it is possible.
	 * If the item is already in the parcel, method will throw an exception, but will continue to run.
	 * The weight of the item should be less or equal than remaining weight in the parcel.
	 *
	 * @param item an item to be added.
	 *
	 * @return true if the item has been added, false if there is no space to fit it.
	 */
	public boolean addItem(Item item) {
		//System.out.println(item);
	    if (itemList.contains(item)) throw new ItemAlreadyInParcelException();

		if (currentWeight + item.getWeight() > MAX_WEIGHT) return false;
		
		itemList.add(item);
		currentWeight += item.getWeight();
		currentQuality += item.getPrice();
		return true;
	}
    /**
     * Removes the item from the parcel if it is in there.
     *
     * @param item an item to be removed.
     *
     * @return true if the item has been removed, false if there was no such item in the parcel.
     */
	public boolean removeItem(Item item) {

        return itemList.remove(item);
    }

    /**
     * @return string representation of the object.
     */
	@Override
	public String toString() {
		return "Parcel [MAX_WEIGHT=" + MAX_WEIGHT + ", currentWeight=" + currentWeight + ", currentQuality=" + currentQuality
				+ ", itemList=" + itemList + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Parcel parcel = (Parcel) o;

		if (parcel.itemList.size() != itemList.size()) return false;

		for (int i = 0; i < parcel.itemList.size(); i++) {
			boolean matches = false;
			for (int j = 0; j < parcel.itemList.size(); j++) {
				if (parcel.itemList.get(j).equals(itemList.get(i)));
					matches = true;
			}
			if (!matches) return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return itemList != null ? itemList.hashCode() : 0;
	}

}

//min f(x) = x1.cena +x2.cena+ x3.cena + x4.cena ...../
 // min f(x) = x1.f(jakosci) + x2.F(jakosci) + x3.f(jakosci) + ......
//f(jakosci)=priorytet*cena + priorytet*jakosc - kara za brak
