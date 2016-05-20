package Métier;

import java.sql.*;
import java.util.logging.Logger;

public class Connexion {

	/**
	 * Pour les messages
	 */
	protected static Logger logger = Logger.getLogger("crs471");

	private Connection myConnexion;

	/**
	 *  Construction de l'accesseur de donnees.
	 *
	 */
	public Connexion() {

		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://127.0.0.1/calendrierfarm";
		String user = "root";
		String pass = "";
		
		
		try {
			Class.forName(driverName);

			myConnexion = DriverManager.getConnection(dbUrl, user, pass);
			logger.info("Connexion reussie");
		} catch (Exception exc) {
			logger.severe(exc.toString());
		}
	}

	public Connection getConnexion(){
		return this.myConnexion;
		
	}	

	public Logger getLogger(){
		return logger;
	}
}