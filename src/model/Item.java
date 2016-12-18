package model;

import java.util.Objects;

public class Item {
	
	private String name;
	private double weight;
    private double price;


    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getQuality() {
        return quality;
    }

    private double quality;
	
	public Item(String name, double weight, double price)
	{
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.quality = price + ((-2)*weight);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Item)) return false;
		if (obj == this) return true;

		Item castedObject = (Item) obj;
		return (castedObject.name.equals(name));
	}

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price);
    }

    @Override
	public String toString() {
		return "Item [name=" + name + ", weight=" + weight + ", price=" + price + " ]";
	}
	
}
