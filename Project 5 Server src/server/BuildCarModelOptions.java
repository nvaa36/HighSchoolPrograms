package server;

import java.util.Properties;

import model.Automobile;
import util.ReadSource;

// builds a car from the properties object
public class BuildCarModelOptions {
	
	public Automobile buildAuto(Properties prop){
		Automobile auto;
		ReadSource read = new ReadSource();
		auto = read.buildAutoFromProp(prop);
        return auto;
	}
}
