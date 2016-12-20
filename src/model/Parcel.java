package model;

import Exceptions.ItemAlreadyInParcelException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Parcel implements Comparable<Parcel> {
	
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
	}  //dokladam static

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

	    if (itemList.contains(item)) throw new ItemAlreadyInParcelException();

		if (currentWeight + item.getWeight() > MAX_WEIGHT) return false;
		
		itemList.add(item);
		currentWeight += item.getWeight();
		currentQuality += item.getPrice();  // koszt wysylki 2zl per kilo
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
	    if (itemList.remove(item)) {
            currentWeight -= item.getWeight();
            currentQuality -= item.getPrice();
            return true;
        }
        return false;
    }

    /**
     * Removes the item from the parcel if it is in there.
     *
     * @return void
     */
    public void removeItems() {              //17.122
        currentWeight=0;
        currentQuality=0;
        itemList.clear();
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

		if (parcel.hashCode() != hashCode()) return false;      //dziala troche szybciej

        HashSet<Item> hs = new HashSet<>(parcel.getItemList());
        HashSet<Item> hs2 = new HashSet<>(getItemList());
        return hs.equals(hs2);
	}

	@Override
	public int hashCode() {
		return itemList != null ? itemList.hashCode() : 0;
	}

	@Override
	public int compareTo(Parcel o) {
		return (int) (currentQuality - o.getCurrentQuality());
	}

	public int reversedCompareTo(Parcel o) {
        return (int) (o.getCurrentQuality() - currentQuality);
    }
}

//min f(x) = x1.cena +x2.cena+ x3.cena + x4.cena ...../
 // min f(x) = x1.f(jakosci) + x2.F(jakosci) + x3.f(jakosci) + ......
//f(jakosci)=priorytet*cena + priorytet*jakosc - kara za brak
