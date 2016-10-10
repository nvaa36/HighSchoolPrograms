package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Set;

import adapter.BuildAuto;

// runs the functionality of the server
public class RunServer {
	
	// runs the server based on the input from the client
	public void run() throws IOException{
		ServerSocket serverSocket = null;
		ServerSocket serverSocketob = null;
		BuildAuto build = new BuildAuto();
        try {
            serverSocket = new ServerSocket(4444);
            serverSocketob = new ServerSocket(5555);
        } catch (IOException e) {
        	System.err.println("Could not listen on port: 4444 or 5555.");
        	System.exit(1);
        }

        Socket clientSocket = null;
        Socket clientSocketob = null;
        try {
        	clientSocket = serverSocket.accept();
        	clientSocketob = serverSocketob.accept();
        } catch (IOException e) {
        	System.err.println("Accept failed.");
        	System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream outstream = new ObjectOutputStream(clientSocketob.getOutputStream());
        Properties prop = new Properties();
        Object ob;
        try {
        	ob = in.readObject();
        	while(ob != null){
        		if(ob.getClass().equals(prop.getClass())){
        			prop = (Properties)ob;
        			build.createAutoProp(prop);
        			out.println("Auto creation was successful.");
        		}
        		else{
        			Set<String> cars = build.getAutoList();
        			int i = 1;
        			for(String str: cars){
        				out.println(i + ". " + str);
        				i++;
        			}
        			out.println("done");
        			int carnum = 0;
        			boolean good = false;
        			do{
        				carnum = (int)in.readObject();
        				if(carnum <= cars.size()){
        					good = true;
        					out.println("good");
        				}
        				else
        					out.println("bad");
        			}while(!good);
        			i = 1;
        			for(String str: cars){
        				if(i == carnum)
        					outstream.writeObject(build.getAuto(str));
        				i++;
        			}
        		}
        		ob = in.readObject();
        	}
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
	}
}
