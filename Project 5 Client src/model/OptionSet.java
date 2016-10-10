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

	protected OptionSet() {
		name = "null";
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

	protected ArrayList<Option> getOpt() {
		return opt;
	}
	
	public int getSize() {
		return opt.size();
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
	public Option getChoice() {
		return choice;
	}
	public Option setChoice(String opname) {
		choice = find(opname);
		return choice;
	}
	
	public Option setChoice(int opnum) {
		choice = opt.get(opnum);
		System.out.println(choice);
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
			for(int i = 0; i < opt.size(); i++){
				str.append(" " + (char)(i+97) + ". " + opt.get(i));
				if(opt.get(i).equals(choice))
					str.append(" *chosen option*");
			}
		}
		return str.toString();
	}
}