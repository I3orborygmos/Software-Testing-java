package contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

    // In-memory store keyed by contactId
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a contact with a unique ID.
     * @throws IllegalArgumentException if a contact with that ID already exists.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException(
                "A contact with ID '" + contact.getContactId() + "' already exists.");
        }
        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Deletes a contact by ID.
     * @throws IllegalArgumentException if no contact with that ID exists.
     */
    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException(
                "No contact found with ID '" + contactId + "'.");
        }
        contacts.remove(contactId);
    }

    /**
     * Updates the updatable fields of a contact by ID.
     * Passing null for a field leaves it unchanged.
     * @throws IllegalArgumentException if no contact with that ID exists.
     */
    public void updateContact(String contactId, String firstName, String lastName,
                              String phone, String address) {
        Contact contact = getContact(contactId);

        if (firstName != null) contact.setFirstName(firstName);
        if (lastName  != null) contact.setLastName(lastName);
        if (phone     != null) contact.setPhone(phone);
        if (address   != null) contact.setAddress(address);
    }

    /** Convenience getter so tests can inspect stored state. */
    public Contact getContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException(
                "No contact found with ID '" + contactId + "'.");
        }
        return contact;
    }
}