// interface for the updating of an automobile. Can update name of an optionset or an option price

package adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName);
	//This function searches the Model for a given OptionSet and sets the name of OptionSet to
	//newName.
	public void updateOptionPrice(String Modelname, String Optionname, String Option, int newprice);
	//This function searches the Model for a given OptionSet and Option name, and sets the price to
	//newPrice.
	public void updateChoice(String Modelname, String Optionname, String Option);
	//This function searches the Model for a given OptionSet and Option name, and makes that the choice
	public void updatePrice(String Modelname, int price);
}
