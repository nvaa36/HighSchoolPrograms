package model;

import java.util.ArrayList;

// subclass of automobile for cars
public class Car extends Automobile{
	
	public Car() { }
	
	public Car(int size, int price, String mak, String mod) {
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
		str.append("Car: " + make + " " + model + ": " + getTotalPrice());
		for(int i = 0; i < opset.size(); i++) {
			str.append(" " + (i+1) + ". " + opset.get(i));
		}
		return str.toString();
	}

}
