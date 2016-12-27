package model;


import java.util.Objects;

/**
 * Created by Mirek on 2016-12-27.
 */
public class Item_Extended extends Item {


    protected double quality;


        public Item_Extended(String name, double weight, double price ) {
            super(name, weight, price);

            this.quality=(price/weight);
        }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }


    public String toString() {
        return "Item [name=" + name + ", weight=" + weight + ", price=" + price + ", quality="+ quality + " ]";
    }

    public int hashCode() {
        return Objects.hash(name, weight, price, quality);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Item_Extended)) return false;
        if (obj == this) return true;

        Item_Extended castedObject = (Item_Extended) obj;
        return (castedObject.name.equals(name) && castedObject.getWeight() == weight && castedObject.getPrice() == price && castedObject.getQuality() == quality);
    }



}
