package model;

import Exceptions.ItemAlreadyInParcelException;

/**
 * Created by Mirek on 2016-12-27.
 */
public class Parcel_Extended extends Parcel {

    public Parcel_Extended(){
        super();
    }

    public boolean hasItem(Item_Extended item) {
        return itemList.contains(item);
    }  //tego chyba nie trzeba przeciazac

    public boolean addItem(Item_Extended item) {

        if (itemList.contains(item)) throw new ItemAlreadyInParcelException();

        if (getCurrentWeight() + item.getWeight() > getMaxWeight()) return false;

        getItemList().add(item);
        currentWeight+= item.getWeight();
        currentQuality+= item.getQuality();
        return true;
    }


    public boolean removeItem(Item_Extended item) {
        if (itemList.remove(item)) {
            currentWeight -= item.getWeight();
            currentQuality -= item.getQuality();
            return true;
        }
        return false;
    }

    public void removeItems() {
        currentWeight=0;
        currentQuality=0;
        itemList.clear();
    }

    public int compareTo(Parcel_Extended o) {
        return (int) (currentQuality - o.getCurrentQuality());
    }

    public int reversedCompareTo(Parcel_Extended o) {
        return (int) (o.getCurrentQuality() - currentQuality);
    }












}
