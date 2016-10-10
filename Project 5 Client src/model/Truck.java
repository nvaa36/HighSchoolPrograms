package model;

import java.util.ArrayList;

// a subclass of automobile for trucks
public class Truck extends Automobile{
	
	public Truck() { }
	
	public Truck(int size, int price, String mak, String mod) {
		opset = new ArrayList<OptionSet>(size);
		choice = new ArrayList<Option>(size);
		setBasePrice(price);
		setMake(mak);
		setModel(mod);
		for(int i=0;i<size;i++)
			opset.add(new OptionSet());
		for(int i = 0; i < size; i++)
			choice.add(new Option("blank", 0));
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer ();
		str.append("Truck: " + make + " " + model + ": " + getTotalPrice());
		for(int i = 0; i < opset.size(); i++) {
			str.append(" " + (i+1) + ". " + opset.get(i));
		}
		return str.toString();
	}
}
