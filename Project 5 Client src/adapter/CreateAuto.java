// interface with the methods for creating and printing an auto object

package adapter;

public interface CreateAuto {
	public void buildAuto(String filename, String fileType);
	//Given a text file name a method called BuildAuto can be written to build an instance of
	//Automobile. This method does not have to return the Auto instance.
	public void printAuto(String Modelname);
	//This function searches and prints the properties of a given Automodel.
}