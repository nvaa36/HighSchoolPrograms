package util;

import java.io.*;
import java.util.Properties;

import exception.AutoException;
import model.Automobile;
import model.Car;
import model.Truck;

public class ReadSource implements Serializable {
	
	// builds an auto object from a file
	public Automobile buildAutoObject(String filename) {
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			String type = buff.readLine();
			String make = buff.readLine();
			String model = buff.readLine();
			int price = 0;
			try{
				price = getPrice(buff);
			} catch(AutoException exc) {
				exc.fix();
			}
			int size = Integer.parseInt(buff.readLine());
			Automobile auto;
			if(type.equals("truck"))
				auto = new Truck(size, price, make, model);
			else
				auto = new Car(size, price, make, model);
			int ind = 0;
			String line = buff.readLine();
			while (line != null) {
				String name = line;
				size = Integer.parseInt(buff.readLine());
				auto.update(ind, name, size);
				int indo = 0;
				line = buff.readLine();
				while (line != null && !line.equals("/")) {
					int eq = line.indexOf('=');
					if(eq > 0) {
						name = line.substring(0, eq);
						price = Integer.parseInt(line.substring(eq+1));
					}
					else {
						name = line;
						price = 0;
					}
					auto.update(ind, indo, name, price);
					indo++;
					line = buff.readLine();
				}
				ind++;
				line = buff.readLine();
			}
			
			buff.close();
			return auto;
		} catch (IOException e) {
			System.out.println("Error нн " + e.toString());
		}
		return null;
	}
	
	public void serializeAuto(Automobile auto) {
		try {
			FileOutputStream stream = new FileOutputStream("auto.ser");
			ObjectOutputStream ostream = new ObjectOutputStream(stream);
			ostream.writeObject(auto);
			ostream.close();
			stream.close();
		} catch (IOException e) {
			System.out.println("Error нн " + e.toString());
		}
	}
	
	public Automobile deserializeAuto(String file) {
		Automobile auto = null;
		try {
			FileInputStream stream = new FileInputStream("auto.ser");
			ObjectInputStream ostream = new ObjectInputStream(stream);
			auto = (Automobile)ostream.readObject();
			ostream.close();
			stream.close();
		} catch (IOException e) {
			System.out.println("Error нн " + e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return auto;
	}
	
	public int getPrice(BufferedReader buff) throws AutoException{
		int price = 0;
		try {
			String str = buff.readLine();
			if(str.isEmpty())
				throw new AutoException(3);
			price = Integer.parseInt(str);
		} catch(IOException i){} // if error occurs, price remains the default value of 0
		return price;
	}
	
	// create properties object from a text file and returns
	public Properties createProps(String file) throws IOException{
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		return props;
	}
	
	// bulds an auto form prop file and retruns
	public Automobile buildAutoFromProp(Properties prop) {
		String make = prop.getProperty("CarMake");
		if(make != null){
			String model = prop.getProperty("CarModel");
		}
		return null;
	}
}
