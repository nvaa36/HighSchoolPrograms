package model;

import java.io.Serializable;
import java.util.ArrayList;

// creates an automobile object that holds multiple optionsets which have
// within them multiple options
public class Automobile implements Serializable{ // represents the model
	protected String make;
	protected String model;
	protected int basePrice;
	protected ArrayList<OptionSet> opset;
	protected ArrayList<Option> choice;
	private static final long serialVersionUID = 1L;
	
	public Automobile() { }
	
	public Automobile(int size, int price, String mak, String mod) {
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
	
	public Automobile(int price, String mak, String mod) {
		opset = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>();
		setBasePrice(price);
		setMake(mak);
		setModel(mod);
	}
	
	public void addOptionSet(OptionSet op) {
		opset.add(op);
	}
	
	public void addChoice(Option op) {
		choice.add(op);
	}
	
	public void setOptionSet(int n, OptionSet op) {
		opset.set(n, op);
	}
	public OptionSet getOptionSet(int n) {
		return opset.get(n);
	}
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	public String getChoiceName(String op) {
		return find(op).getChoice().getName();
	}
	public int getChoicePrice(String op) {
		return find(op).getChoice().getPrice();
	}
	public void setChoice(String ops, String op) {
		Option choic = find(ops).setChoice(op);
		choice.set(findInd(ops), choic);
	}
	public int getTotalPrice(){
		int total = basePrice;
		for(OptionSet op: opset){
			if(op.getChoice() != null)
				total += op.getChoice().getPrice();
		}
		return total;
	}
	public OptionSet find(String n) {
		int ind = 0;
		for(int i = 0;i < opset.size();i++){
			if(opset.get(i).getName().equals(n))
				ind = i;
		}
		return opset.get(ind);
	}
	public int findInd(String n) {
		int ind = 0;
		for(int i = 0;i < opset.size();i++){
			if(opset.get(i).getName().equals(n))
				ind = i;
		}
		return ind;
	}
	public OptionSet delete(int ind) {
		if(ind >= opset.size())
			return null;
		return opset.remove(ind);
	}
	public OptionSet delete(String n) {
		int ind = findInd(n);
		return delete(ind);
	}
	public void update(int ind, String n, int s) {
		opset.set(ind, new OptionSet(n, s));
	}
	public void update(int ind, int indo, String n, int price) {
		opset.get(ind).update(indo, n, price);
	}
	public void updateOptionSetName(String optionSetName, String newName) {
		find(optionSetName).setName(newName);
	}
	public void updateOptionPrice(String optionname, String option, int newprice) {
		find(optionname).updateOptionPrice(option, newprice);
	}
	public String toString() {
		StringBuffer str = new StringBuffer ();
		str.append(make + " " + model + ": " + getTotalPrice());
		for(int i = 0; i < opset.size(); i++) {
			str.append(" " + (i+1) + ". " + opset.get(i));
		}
		return str.toString();
	}
	public void print() {
		System.out.println(toString());
	}
}
