package client;

// runs all the functionality of the client class
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import model.Automobile;
import model.OptionSet;

import java.io.*;

public class RunClient extends Thread implements SocketClientInterface, SocketClientConstants{

	private BufferedReader reader;
	private ObjectInputStream instream;
	private ObjectOutputStream out;
	private BufferedReader readeru;
	private Socket sock;
	private Socket sockob;
	private String strHost;
	private int iPort;
	private int iPortob;
	
	public RunClient(String strHost, int iPort, int iPortob) {
		this.iPort = iPort;
		this.strHost = strHost;
		this.iPortob = iPortob;
	}//constructor

	public void run(){
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}
	
	// opens the connection to the server and the readers, writers, input and output streams
	public boolean openConnection(){
		try {
			sock = new Socket(strHost, iPort);
			sockob = new Socket(strHost, iPortob);
			System.out.println("connection successful");
		}
		catch(IOException socketError){
			if (DEBUG) System.err.println
			("Unable to connect to " + strHost);
			return false;
		}
		try {
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new ObjectOutputStream(sock.getOutputStream());
			instream = new ObjectInputStream(sockob.getInputStream());
		}
		catch (Exception e){
			if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}
	
	// asks the user what it wants to do then calls the helper method to do it
	public void handleSession(){
		readeru = new BufferedReader(new InputStreamReader(System.in));
			boolean exit = false;
			do{
				int op = getOption();
				switch(op){
				case 1: doProps(); break;
				case 2: doConfig(); break;
				case 3: exit = true;
				}
			} while(!exit);
	}
	
	// closes the session and all sockets
	public void closeSession(){
		try{
			out.close();
			reader.close();
			sock.close();
		} catch(IOException e){
			System.out.println("Error closing socket to " + strHost);
		}
	}
	
	// gets what the reader wants to do
	public int getOption(){
		int op = 1;
		System.out.println("What would you like to do?");
		System.out.println("1) upload properties file");
		System.out.println("2) configure a car");
		System.out.println("3) Exit");
		try {
			boolean good = false;
			while(!good){
				String in = readeru.readLine();
				try{
					op = Integer.parseInt(in);
					if(op == 1 || op == 2 || op == 3)
						good = true;
					else
						throw new NumberFormatException();
				}catch(NumberFormatException e){
					System.out.println("Please enter a valid number (1, 2, or 3)");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}
	
	// handles if user wants to upload a props file to server
	//  makes prop object from file then sends to server
	public void doProps(){
		System.out.println("Please enter the name of the Properties file to be uploaded.");
		Properties prop = new Properties();
		try {
			boolean good = false;
			while(!good){
				String in = readeru.readLine();
				try{
					CarModelOptionsIO car = new CarModelOptionsIO();
					prop = car.readProp(in);
					good = true;
				}catch(IOException e){
					System.out.println("That Properties file was not found. Please try another.");
				}
			}
			out.writeObject(prop);
			String serverin = reader.readLine();
			System.out.println("Server: " + serverin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// handles if user wants to configure a car
	//  gets list of cars from server, asks for which car to configure, then asks for choices
	public void doConfig(){
		try {
			System.out.println("Getting Car List.");
			out.writeObject("send car list");
			String serverin;
			while (!((serverin = reader.readLine()).equals("done"))) {
				System.out.print(serverin + " ");
			}
			System.out.println("\nPlease pick a car to configure.");
			boolean good = false;
			while(!good){
				String in = readeru.readLine();
				int carnum = Integer.parseInt(in);
				out.writeObject(carnum);
				if(reader.readLine().equals("good"))
					good = true;
				else{
					System.out.println("Please enter a valid number");
				}
			}
			Automobile auto = new Automobile();
			try {
				auto = (Automobile)instream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			auto.print();
			for(int i = 0; i < auto.getSize(); i++){
				System.out.println("\nPlease choose an option.");
				OptionSet opset = auto.getOptionSet(i);
				System.out.println(opset.toString());
				int opnum = 0;
				good = false;
				while(!good){
					String in = readeru.readLine();
					char ch = in.charAt(0);
					opnum = (int)ch - 97;
					if(opnum < opset.getSize())
						good = true;
					else{
						System.out.println("Please enter a valid letter");
					}
				}
				opset.setChoice(opnum);
				auto.setChoice(i, opset.getChoice());
			}
			System.out.println("Here is your configured car");
			System.out.print(auto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}