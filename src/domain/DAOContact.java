package domain;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.*;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAOContact {

//	private final static String RESOURCE_JDBC = "java:comp/env/jdbc/gestion_contact";

    private SessionFactory sessionFactory;

    public DAOContact() {
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
        } catch (NoClassDefFoundError e) {
            System.err.println(e.getMessage());
            sessionFactory = null;
        }
    }

    /**
     * @param contact
     * @return null if contact was correctly being add or string exception if failure
     */
    public String addContact(Contact contact) {

        System.out.println("contact : " + contact.toString());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.persist(contact);

        session.getTransaction().commit();
        session.close();

        System.out.println(String.format("Add contact to database : %s", contact.toString()));

        return null;
    }

    /**
     * @param contact
     * @return return null or string exception
     */
    public Object searchContact(final Contact contact) {
        System.out.println(String.format("Searching contact : %s", contact.toString()));
        return null;
    }

    /**
     * Note : It is actually not possible to delete orphan of a one to one (or many to one) relation.
     * It has to be done manually.
     * See : https://hibernate.atlassian.net/browse/HHH-2608
     *
     * @param contact Contact
     * @return return null or string exception
     */
    public Object updateContact(final Contact contact) {

        if (!contact.getAddress().isValid()) {
            if (contact.getAddress().getId() != 0) {
                deleteAddress(contact.getAddress().getId());
            }
            contact.setAddress(null);
        }

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.saveOrUpdate(contact);

        session.getTransaction().commit();
        session.close();

        System.out.println(String.format("Update contact to database : %s", contact.toString()));
        return null;
    }

    /**
     * @param contact
     * @return return null or string exception
     */
    public Object deleteContact(final Contact contact) {
        System.out.println(String.format("Deleting contact : %s", contact.toString()));
        return null;
    }

    /**
     * @param id Delete contact based on his id
     * @return return null or string exception
     */
    public Object deleteContact(final Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Contact contact = session.get(Contact.class, id);

        session.delete(contact);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    /**
     * @param address
     * @return null if address was correctly being add or string exception if failure
     */
    public Object addAddress(final Address address) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        long id = (long) session.save(address);
        address.setId(id);

        session.getTransaction().commit();
        session.close();

        System.out.println(String.format("Add address to database : %s", address.toString()));
        return null;
    }

    /**
     * @param address
     * @return address being search or string exception if failure
     */
    public Object searchAddress(Address address) {
        System.out.println(String.format("Searching address : %s", address.toString()));
        return null;
    }

    /**
     * @param address
     * @return return null or string exception
     */
    public Object updateAddress(Address address) {
        System.out.println(String.format("Updating address : %s", address.toString()));
        return null;
    }

    /**
     * @param address Address
     * @return null
     */
    public Object deleteAddress(Address address) {
        return null;
    }

    /**
     * @param id Long
     * @return null
     */
    public Object deleteAddress(long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Address address = session.get(Address.class, id);

        session.delete(address);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    /**
     * @param phoneNumber
     * @return null if phoneNumber was correctly being add or string exception if failure
     */
    public Object addPhoneNumber(final PhoneNumber phoneNumber) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        long id = (long) session.save(phoneNumber);
        phoneNumber.setId(id);

        session.getTransaction().commit();
        session.close();

        System.out.println(String.format("Add phoneNumber to database : %s", phoneNumber.toString()));
        return null;
    }

    /**
     * @param phoneNumber
     * @return phoneNumber being search or string exception if failure
     */
    public Object searchPhoneNumber(PhoneNumber phoneNumber) {
        System.out.println(String.format("Searching phoneNumber : %s", phoneNumber.toString()));
        return null;
    }

    /**
     * @param phoneNumber
     * @return return null or string exception
     */
    public Object updatePhoneNumber(PhoneNumber phoneNumber) {
        System.out.println(String.format("Updating phoneNumber : %s", phoneNumber.toString()));
        return null;
    }

    /**
     * @param phoneNumber
     * @return return null or string exception
     */
    public Object deletePhoneNumber(PhoneNumber phoneNumber) {
        System.out.println(String.format("Deleting phoneNumber : %s", phoneNumber.toString()));
        return null;
    }

    public Object addContactGroup(ContactGroup contactGroup) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        long id = (long) session.save(contactGroup);
        contactGroup.setGroupId(id);

        session.getTransaction().commit();
        session.close();

        System.out.println(String.format("Add contactGroup : %s", contactGroup.toString()));
        return null;
    }

    public Object addContactToGroup(ContactGroup group, Contact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        // TODO Comment faire pour mettre à jour l'association sans récupérer le group en entier sur la BDD ?
        ContactGroup groupTmp = session.get(ContactGroup.class, group.getGroupId());
        groupTmp.addContact(contact);

        session.saveOrUpdate(groupTmp);

        session.getTransaction().commit();
        session.close();

        System.out.println(String.format("Add contact %s to group %s",
                contact.toString(), group.toString()));
        return null;
    }

    public List<Contact> loadContacts() {
        if (sessionFactory != null) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Contact> contacts = session.createQuery(
                    "from Contact contact ORDER BY lastName", Contact.class).list();
            session.close();

            return new ArrayList<>(contacts);
        }
        return null;
    }

    public Object loadContact(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
        Root<Contact> root = criteria.from(Contact.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        Contact contact = session.createQuery(criteria).getSingleResult();

        // Used to force load lazy collections
        Hibernate.initialize(contact.getAddress());
        Hibernate.initialize(contact.getPhones());

        session.close();
        return contact;
    }

    public Object loadGroups() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object groups = session.createQuery(
                "from ContactGroup contactGroup ORDER BY groupName").list();
        System.out.println(groups);
        session.close();

        return groups;
    }

    public Object loadGroups(String groupName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object groups = session.createQuery(
                "from ContactGroup contactGroup WHERE groupName like :name ORDER BY groupName")
                .setParameter("name", String.format("%s%%", groupName))
                .list();
        session.close();
        return groups;
    }
}
