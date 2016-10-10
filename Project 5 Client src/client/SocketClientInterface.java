package client;

//interface for client socket
public interface SocketClientInterface {
	boolean openConnection();

	void handleSession();

	void closeSession();
}
