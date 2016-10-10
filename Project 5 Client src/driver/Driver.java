package driver;
import client.RunClient;

//Driver for testing the code for making a car
public class Driver {
	public static void main(String [] args) {
		/*Build Automobile Object from a file.
		ReadSource read = new ReadSource();
		Automobile FordZTW = read.buildAutoObject("FordInfo");
		//Print attributes before serialization
		FordZTW.print();
		//Serialize the object
		//read.serializeAuto(FordZTW);
		//Deserialize the object and read it into memory.
		//Automobile newFordZTW = read.deserializeAuto("auto.ser");
		//Print new attributes.
		//newFordZTW.print();
		
		BuildAuto build2 = new BuildAuto();
		build2.buildAuto("BadFordInfo", "normal");
		
		BuildAuto build = new BuildAuto();
		build.buildAuto("FordInfo", "normal");
		build.printAuto("Focus Wagon ZTW");
		build.updateOptionPrice("Focus Wagon ZTW", "Transmission", "Manual", 345);
		build.updateOptionSetName("Focus Wagon ZTW", "Color", "Ridiculous Colors");
		build.printAuto("Focus Wagon ZTW");
		
		
		build.buildAuto("Toyota4Runner", "normal");
		build.printAuto("Toyota 4Runner");
		build.updateChoice("Toyota 4Runner", "Color", "Gray");
		build.updateChoice("Toyota 4Runner", "Transmission", "Manual");
		build.printAuto("Toyota 4Runner");
		
		
		LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();
		autos.put(FordZTW.getMake() + " " + FordZTW.getModel(), FordZTW);
		Automobile fourRunner = read.buildAutoObject("Toyota4Runner");
		autos.put(fourRunner.getMake() + " " + fourRunner.getModel(), fourRunner);
		AutoGroup group = new AutoGroup();
		int cost = group.getCost(autos);
		System.out.println("The Cost of the group of Automobiles is: " + cost);
		LinkedHashMap<String, Car> cars = new LinkedHashMap<String, Car>();
		cars.put(FordZTW.getMake() + " " + FordZTW.getModel(), (Car)FordZTW);
		cost = group.getCost(cars);
		System.out.println("The Cost of the group of Cars is: " + cost);
		build.update("Toyota 4Runner");*/
		
		RunClient run = new RunClient("10.41.103.189", 4444, 5555);
		run.run();
	}
}
