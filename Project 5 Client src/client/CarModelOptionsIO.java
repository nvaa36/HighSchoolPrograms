package client;

import java.io.IOException;
import java.util.Properties;

import util.ReadSource;

// reads in a prop file ot object
public class CarModelOptionsIO {
	public Properties readProp(String filename) throws IOException{
		ReadSource read = new ReadSource();
		return read.createProps(filename);
	}
}
