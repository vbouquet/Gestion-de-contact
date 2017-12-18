package dao;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface ContactDAO {
    String addContact(Contact contact);
    Object updateContact(final Contact contact);
    Object deleteContact(final Long id);
    Object addAddress(final Address address);
    Object addPhoneNumber(final PhoneNumber phoneNumber);
    Set<Contact> loadContacts();
    Object loadContacts(String search);
    Object loadContact(Long id);
}