package server;

import java.util.Properties;
import java.util.Set;

import model.Automobile;

// functionality of server for build auto
public interface AutoServer {
	// creates auto form prop object
	public void createAutoProp(Properties prop);
	// returns the names of the autos in the list
	public Set<String> getAutoList();
	// returns the suto when given the name
	public Automobile getAuto(String autoname);
}
