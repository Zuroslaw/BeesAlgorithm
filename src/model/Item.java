package model;

import java.util.Objects;

public class Item {
	
	protected String name;
	protected double weight;
    protected double price;
	
	public Item(String name, double weight, double price)
	{
		this.name = name;
		this.weight = weight;
		this.price = price;
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
		return (castedObject.name.equals(name) && castedObject.getWeight() == weight && castedObject.getPrice() == price);
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
