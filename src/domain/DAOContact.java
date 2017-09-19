package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOContact {
	
	private final static String RESOURCE_JDBC = "java:comp/env/jdbc/gestion_contact";

	public String addContact(final long id, final String firstName, final String lastName, final String email) {
		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			final Connection lConnection  = lDataSource.getConnection();
			
			// adding a new contact
			final PreparedStatement lPreparedStatementCreation = 
					
			lConnection.prepareStatement("INSERT INTO CONTACT(ID, FIRSTNAME, LASTNAME, EMAIL) VALUES(?, ?, ?, ?)");
			
			lPreparedStatementCreation.setLong(1, id);
			lPreparedStatementCreation.setString(2, firstName);
			lPreparedStatementCreation.setString(3, lastName);
			lPreparedStatementCreation.setString(4, email);
			lPreparedStatementCreation.executeUpdate();
			
			return null;
		} catch (NamingException e) {
		
			return "NamingException : " + e.getMessage();
		
		} catch (SQLException e) {

			return "SQLException : " + e.getMessage();
			
		}
	}
	
	/**
	 * 
	 * @param address
	 * @return null if address was correctly being add or string exception if failure
	 */
	public Object addAddress(final Address address) {
		System.out.println(String.format("Add address to database : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return address being search or string exception if failure
	 */
	public Object searchAddress(Address address) {
		// Searching address by id or country or/and city, or/and street, or/and zip
		System.out.println(String.format("Searching address : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return return null or string exception
	 */
	public Object updateAddress(Address address) {
		// Updating address by id
		System.out.println(String.format("Updating address address : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return return null or string exception
	 */
	public Object deleteAddress(Address address) {
		// Deleting address by id
		System.out.println(String.format("Deleting address : %s", address.toString()));
		return null;
	}
}
