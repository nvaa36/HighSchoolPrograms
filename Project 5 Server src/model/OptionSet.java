package model;

import java.io.Serializable;
import java.util.ArrayList;

// holds the name of the optionset along with appropriate getters and setters
// and holds an arraylist of options
public class OptionSet implements Serializable{ // set of all options (ex. color)
	private ArrayList<Option> opt;
	private String name;
	private Option choice;
	private static final long serialVersionUID = 1L;

	public OptionSet() {
		opt = new ArrayList<Option>();
		name = "null";
	}
	
	public OptionSet(String n) {
		opt = new ArrayList<Option>();
		name = n;
		choice = null;
	}
	
	protected OptionSet(String n, int size) {
		opt = new ArrayList<Option>(size);
		name = n;
		choice = null;
		for(int i=0;i<size;i++)
			opt.add(new Option());
		choice = opt.get(0);
	}
	
	protected OptionSet(String n, int size, String choic) {
		opt = new ArrayList<Option>(size);
		name = n;
		choice = null;
		for(int i=0;i<size;i++)
			opt.add(new Option());
		choice = find(choic);
	}
	
	public void add(String op){
		opt.add(new Option(op));
	}
	
	public void add(String op, int price){
		opt.add(new Option(op, price));
	}

	protected ArrayList<Option> getOpt() {
		return opt;
	}
	protected void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected Option getChoice() {
		return choice;
	}
	protected Option setChoice(String opname) {
		choice = find(opname);
		return choice;
	}
	protected Option find(String op) {
		int ind = 0;
		for(int i = 0;i < opt.size();i++){
			if(opt.get(i).getName().equals(op))
				ind = i;
		}
		return opt.get(ind);
	}
	protected int findInd(String op) {
		int ind = 0;
		for(int i = 0;i < opt.size();i++){
			if(opt.get(i).getName().equals(op))
				ind = i;
		}
		return ind;
	}
	protected void updateOptionPrice(String option, int newprice) {
		find(option).setPrice(newprice);
	}
	protected Option delete(int ind) {
		if(ind >= opt.size())
			return null;
	    return opt.remove(ind);
	}
	protected Option delete(Option op) {
		int ind = findInd(op.getName());
		return delete(ind);
	}
	protected void update(int ind, String n, int p) {
		opt.set(ind, new Option(n, p));
	}
	public String toString() {
		StringBuffer str = new StringBuffer ();
		str.append(name);
		if(opt != null) {
			for(int i = 0; i < opt.size(); i++)
				str.append(" " + (char)(i+97) + ". " + opt.get(i));
		}
		return str.toString();
	}
}