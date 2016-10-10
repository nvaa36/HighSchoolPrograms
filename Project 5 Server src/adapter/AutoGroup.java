package adapter;

import java.util.Collection;
import java.util.LinkedHashMap;

import model.Automobile;

// calculates the total cost of a group of automobiles, cars, or trucks
public class AutoGroup {
	public int getCost(LinkedHashMap<String, ? extends Automobile> autos){
		int cost = 0;
		Collection<Automobile> values = (Collection<Automobile>) autos.values();
		for(Automobile auto: values)
			cost += auto.getTotalPrice();
		return cost;
	}
}
