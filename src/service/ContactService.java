package service;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import domain.DAOContact;

public class ContactService {
	
	@SuppressWarnings("unused")
	private DAOContact cdao;
	
	public ContactService() {
		
	}
	
	/**
	 * 
	 * @param contact
	 * @return null if contact was correctly being add or string exception if failure
	 */
	public Object addContact(Contact contact) {
		return (cdao = new DAOContact()).addContact(contact);
	}
	
	/**
	 * 
	 * @param contact
	 * @return contact being search or string exception if failure
	 */
	public Object searchContact(Contact contact) {
		return (cdao = new DAOContact()).searchContact(contact);
	}
	
	/**
	 * 
	 * @param contact
	 * @return return null or string exception
	 */
	public Object updateContact(Contact contact) {
		return (cdao = new DAOContact()).updateContact(contact);
	}
	
	/**
	 * 
	 * @param contact
	 * @return return null or string exception
	 */
	public Object deleteContact(Contact contact) {
		return (cdao = new DAOContact()).deleteContact(contact);
	}
	
	/**
	 * 
	 * @param address
	 * @return null if address was correctly being add or string exception if failure
	 */
	public Object addAddress(Address address) {
		return (cdao = new DAOContact()).addAddress(address);
	}
	
	/**
	 * 
	 * @param address
	 * @return address being search or string exception if failure
	 */
	public Object searchAddress(Address address) {
		return (cdao = new DAOContact()).searchAddress(address);
	}
	
	/**
	 * 
	 * @param address
	 * @return return null or string exception
	 */
	public Object updateAddress(Address address) {
		return (cdao = new DAOContact()).updateAddress(address);
	}
	
	/**
	 * 
	 * @param address
	 * @return return null or string exception
	 */
	public Object deleteAddress(Address address) {
		return (cdao = new DAOContact()).deleteAddress(address);
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return null if phoneNumber was correctly being add or string exception if failure
	 */
	public Object addPhoneNumber(PhoneNumber phoneNumber) {
		return (cdao = new DAOContact()).addPhoneNumber(phoneNumber);
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return phoneNumber being search or string exception if failure
	 */
	public Object searchPhoneNumber(PhoneNumber phoneNumber) {
		return (cdao = new DAOContact()).searchPhoneNumber(phoneNumber);
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return return null or string exception
	 */
	public Object updatePhoneNumber(PhoneNumber phoneNumber) {
		return (cdao = new DAOContact()).updatePhoneNumber(phoneNumber);
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return return null or string exception
	 */
	public Object deletePhoneNumber(PhoneNumber phoneNumber) {
		return (cdao = new DAOContact()).deletePhoneNumber(phoneNumber);
	}
}
