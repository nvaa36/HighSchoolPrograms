// implements all of the methods in the two interfaces

package adapter;

import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import model.*;
import scale.EditOptions;
import server.BuildCarModelOptions;
import util.ReadSource;

public abstract class ProxyAuto {
	protected static LinkedHashMap<String, Automobile> a1 = new LinkedHashMap<>();
	protected boolean get; 
	
	public ProxyAuto(){
		get = false;
	}
	
	public void buildAuto(String filename, String fileType) {
		ReadSource read = new ReadSource();
		if(fileType.equals("normal"))
		{
			Automobile auto = read.buildAutoObject(filename);
			a1.put(auto.getMake() + " " + auto.getModel(), auto);
		}
		else{
			Properties prop = read.createProps(filename);
			Automobile auto = read.buildAutoFromProp(prop);
			a1.put(auto.getMake() + " " + auto.getModel(), auto);
		}
	}
	//Given a text file name a method called BuildAuto can be written to build an instance of
	//Automobile. This method does not have to return the Auto instance.
	public void printAuto(String modelname) {
		a1.get(modelname).print();
	}
	//This function searches and prints the properties of a given Automodel.
	public void updateOptionSetName(String modelname, String OptionSetName, String newName) {
		a1.get(modelname).updateOptionSetName(OptionSetName, newName);
	}
	//This function searches the Model for a given OptionSet and sets the name of OptionSet to
	//newName.
	public void updateOptionPrice(String modelname, String Optionname, String Option, int newprice) {
		a1.get(modelname).updateOptionPrice(Optionname, Option, newprice);
	}
	//This function searches the Model for a given OptionSet and Option name, and sets the price to
	//newPrice.
	public void updateChoice(String Modelname, String Optionname, String Option){
		a1.get(Modelname).setChoice(Optionname, Option);
	}
	public void updatePrice(String Modelname, int price){
		a1.get(Modelname).setBasePrice(price);
	}
	public void fixAuto() {
		
	}
	//This fixes the errors that can occur when running buildAuto

	// calls an instacne of the EditOptions class to start the cycle of getting and setting
	public void update(String modelname){
		EditOptions edit = new EditOptions(modelname);
		edit.messupPrices();
	}
	
	// creates auto form a properties object
	public void createAutoProp(Properties prop){
        BuildCarModelOptions build = new BuildCarModelOptions();
        Automobile auto = build.buildAuto(prop);
        a1.put(auto.getMake() + " " + auto.getModel(), auto);
	}
	
	// returns the list of the names of the autos
	public Set<String> getAutoList(){
		Set<String> keys = a1.keySet();
		return keys;
	}
	
	// gets the auto that is asked for
	public Automobile getAuto(String autoname){
		Automobile auto = a1.get(autoname);
		return auto;
	}
}
