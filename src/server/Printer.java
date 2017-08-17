package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import security.PasswordHash;

public class Printer extends UnicastRemoteObject implements PrintService {

	private static final long serialVersionUID = -3878749663566559797L;

	private String userLoggedIn;
	private boolean isPrinterStarted = true;

	public Printer() throws RemoteException {
		super();
	}

	@Override
	public void print(String filename, String printer) throws RemoteException {
		System.out.println("PRINTING...");
	}

	@Override
	public void queue() throws RemoteException {
		System.out.println("QUEUE OF PRINT JOBS.");
	}

	@Override
	public void topQueue(int job) throws RemoteException {
		System.out.println("JOB HAS BEEN MOVED TO THE TOP OF THE QUEUE.");
	}

	@Override
	public void start() throws RemoteException {
		System.out.println("PRINTER STARTED.");
	}

	@Override
	public void stop() throws RemoteException {
		System.out.println("PRINTER STOPPED.");
	}

	@Override
	public void restart() throws RemoteException {
		System.out.println("PRINTER RESTARTED.");
	}

	@Override
	public void status() throws RemoteException {
		System.out.println("PRINTER STATUS.");
	}

	@Override
	public void readConfig(String parameter) throws RemoteException {
		System.out.println("READ CONFIG.");
	}

	@Override
	public void setConfig(String parameter, String value) throws RemoteException {
		System.out.println("SET CONFIG.");
	}

	@Override
	public boolean authenticateUser(String username, String password)
			throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException, FileNotFoundException {
		Scanner scanner = new Scanner(new File("login_info.txt"));
		while (scanner.hasNext()) {

			String line = scanner.nextLine();
			String[] tokens = line.split(",");

			if (tokens[0].equals(username)) {
				scanner.close();
				boolean isAuthenticated = PasswordHash.validatePassword(password, tokens[1]);
				if (isAuthenticated) {
					userLoggedIn = username;
				}
				return isAuthenticated;
			}
		}
		scanner.close();
		return false;
	}

	@Override
	public void handleOperationRequest(String operation) throws RemoteException {
		switch (operation) {
		case "print":
			if (isPrinterStarted) {
				print("some file", "some printer");
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "queue":
			if (isPrinterStarted) {
				queue();
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "top queue":
			if (isPrinterStarted) {
				topQueue(2);
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "start":
			if (!isPrinterStarted) {
				start();
			} else {
				System.out.println("ERROR: PRINTER ALREADY STARTED");
			}
			break;
		case "stop":
			if (isPrinterStarted) {
				stop();
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "restart":
			if (isPrinterStarted) {
				restart();
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "status":
			if (isPrinterStarted) {
				status();
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "readconfig":
			if (isPrinterStarted) {
				readConfig("some parameter");
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		case "setconfig":
			if (isPrinterStarted) {
				setConfig("some paramter", "some value");
			} else {
				System.out.println("ERROR: PRINTER NOT STARTED");
			}
			break;
		default:
			System.out.println("NOT AN OPERATION.");
			break;
		}
		System.out.println("INVOKED BY " + userLoggedIn);
	}
}
