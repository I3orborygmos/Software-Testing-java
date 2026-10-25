package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Test
    void addContactStoresContact() {
        Contact c = new Contact("1", "John", "Doe", "5551234567", "123 Main St");
        service.addContact(c);
        assertEquals(c, service.getContact("1"));
    }

    @Test
    void addContactRejectsDuplicateId() {
        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));
        assertThrows(IllegalArgumentException.class,
            () -> service.addContact(
                new Contact("1", "Jane", "Smith", "5559876543", "456 Oak Ave")));
    }

    @Test
    void addContactRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void deleteContactRemovesContact() {
        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));
        service.deleteContact("1");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("1"));
    }

    @Test
    void deleteContactRejectsUnknownId() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999"));
    }

    @Test
    void updateContactUpdatesAllUpdatableFields() {
        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));
        service.updateContact("1", "Jane", "Smith", "5559876543", "456 Oak Ave");

        Contact c = service.getContact("1");
        assertEquals("Jane", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("5559876543", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());
    }

    @Test
    void updateContactLeavesNullFieldsUnchanged() {
        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));
        service.updateContact("1", null, null, "5559876543", null);

        Contact c = service.getContact("1");
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("5559876543", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void updateContactRejectsUnknownId() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateContact("999", "Jane", null, null, null));
    }

    @Test
    void updateContactRejectsInvalidValues() {
        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updateContact("1", null, null, "123", null));
    }
}