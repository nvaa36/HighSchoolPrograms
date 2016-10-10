package model;

import java.io.Serializable;

//holds the name of the option as well as the price
public class Option implements Serializable{ // type of option (ex. blue)
	private String name;
	private int price;
	private static final long serialVersionUID = 1L;
	
	protected Option() {
		name = "null";
		price = 0;
	}
	
	public Option(String n) {
		name = n;
		price = 0;
	}
	
	public Option(String n, int p) {
		name = n;
		price = p;
	}
	
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected int getPrice() {
		return price;
	}
	protected void setPrice(int price) {
		this.price = price;
	}
	public String toString() {
		return name + " = " + price;
	}
}