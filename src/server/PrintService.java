package server;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface PrintService extends Remote {
	
	public void print(String filename, String printer) throws RemoteException;   // prints file filename on the specified printer
	
	public void queue() throws RemoteException;   // lists the print queue on the user's display in lines of the form <job number>   <file name>
	
	public void topQueue(int job) throws RemoteException;   // moves job to the top of the queue
	
	public void start() throws RemoteException;   // starts the print server
	
	public void stop() throws RemoteException;   // stops the print server
	
	public void restart() throws RemoteException;   // stops the print server, clears the print queue and starts the print server again
	
	public void status() throws RemoteException;  // prints status of printer on the user's display
	
	public void readConfig(String parameter) throws RemoteException;   // prints the value of the parameter on the user's display
	
	public void setConfig(String parameter, String value) throws RemoteException;   // sets the parameter to value

	public boolean authenticateUser(String username, String password) throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException, FileNotFoundException; // authenticates the user

	public void handleOperationRequest(String operation) throws RemoteException;
}
