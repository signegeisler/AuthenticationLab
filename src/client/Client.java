package client;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import server.PrintService;


public class Client {
	
	private static String username;
	private static String password;
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, NoSuchAlgorithmException, InvalidKeySpecException, FileNotFoundException {
		PrintService service = (PrintService) Naming.lookup("rmi://localhost:5099/printer");
		Scanner scanner = new Scanner(System.in);
		doAuthentication(scanner, service);
		scanner.close();
	}
	
	public static void doAuthentication(Scanner scanner, PrintService service) throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException, FileNotFoundException{
		getUserInput(scanner);
		boolean isAuthenticated = service.authenticateUser(username, password);
		
		if(isAuthenticated){
			System.out.println("AUTHENTICATION SUCCESSFUL! WELCOME TO THE SYSTEM!");
			handleOperationRequest(scanner, service);
		} else {
			System.out.println("AUTHENTICATION FAILED!");
			doAuthentication(scanner, service);
		}
	}
	
	private static void handleOperationRequest(Scanner scanner, PrintService service) throws RemoteException{
		System.out.print("PLEASE ENTER AN OPERATION: ");
		String operation = scanner.next();
		service.handleOperationRequest(operation);
		handleOperationRequest(scanner, service);
	}
	
	private static void getUserInput(Scanner scanner){
		System.out.print("USERNAME: ");
		username = scanner.next();
		System.out.print("PASSWORD: ");
		password = scanner.next();
	}
}