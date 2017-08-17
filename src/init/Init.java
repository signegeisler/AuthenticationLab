package init;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import security.PasswordHash;

/**
 * This class is only used for creating a file with users and passwords to use
 * for authentication purposes. It is only supposed to be run once.
 */
public class Init {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, FileNotFoundException {

		PrintWriter pw = new PrintWriter("login_info.txt");

		pw.println("signe," + PasswordHash.createHash("passw0rd"));
		pw.println("henrik," + PasswordHash.createHash("trustno1"));
		pw.println("jens," + PasswordHash.createHash("trustno1"));

		pw.close();
	}
}
