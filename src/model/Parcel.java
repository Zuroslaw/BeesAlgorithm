package model;

import Exceptions.ItemAlreadyInParcelException;

import java.util.ArrayList;
import java.util.List;

public class Parcel {
	
	public static double MAX_WEIGHT;
	private double currentWeight = 0;
	private double currentQuality = 0;
	
	private List<Item> itemList;
	
	public Parcel() {
	    itemList = new ArrayList<>();
	}

	public Parcel(List<Item> itemList) {
	    this.itemList = itemList;
    }

	public static void setMaxWeight(double maxWeight) {
	    MAX_WEIGHT = maxWeight;
    }

	public double getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}
	
	public double getCurrentQuality() {
		return currentQuality;
	}

    public void setCurrentQuality(double currentQuality) {
        this.currentQuality = currentQuality;
    }

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

    public boolean hasItem(Item item) {
	    return itemList.contains(item);
    }

	public boolean addItem(Item item) {

	    if (itemList.contains(item)) throw new ItemAlreadyInParcelException();

		if (currentWeight + item.getWeight() > MAX_WEIGHT) return false;
		
		itemList.add(item);
		currentWeight += item.getWeight();
		currentQuality += item.getPrice();
		return true;
	}

	public boolean removeItem(Item item) {

        return itemList.remove(item);
    }

	@Override
	public String toString() {
		return "Parcel [MAX_WEIGHT=" + MAX_WEIGHT + ", currentWeight=" + currentWeight + ", currentQuality=" + currentQuality
				+ ", itemList=" + itemList + "]";
	}

}

//min f(x) = x1.cena +x2.cena+ x3.cena + x4.cena ...../
 // min f(x) = x1.f(jakosci) + x2.F(jakosci) + x3.f(jakosci) + ......
//f(jakosci)=priorytet*cena + priorytet*jakosc - kara za brak
