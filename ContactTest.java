package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void contactIsCreatedWithValidFields() {
        Contact c = new Contact("12345", "John", "Doe", "5551234567", "123 Main St");
        assertEquals("12345", c.getContactId());
        assertEquals("John",  c.getFirstName());
        assertEquals("Doe",   c.getLastName());
        assertEquals("5551234567", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void contactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact(null, "John", "Doe", "5551234567", "123 Main St"));
    }

    @Test
    void contactIdCannotExceed10Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("12345678901", "John", "Doe", "5551234567", "123 Main St"));
    }

    @Test
    void firstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", null, "Doe", "5551234567", "123 Main St"));
    }

    @Test
    void firstNameCannotExceed10Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "JohnnyJohnny", "Doe", "5551234567", "123 Main St"));
    }

    @Test
    void lastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", null, "5551234567", "123 Main St"));
    }

    @Test
    void lastNameCannotExceed10Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "DoeDoeDoeDoe", "5551234567", "123 Main St"));
    }

    @Test
    void phoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "Doe", null, "123 Main St"));
    }

    @Test
    void phoneMustBeExactly10Digits() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "Doe", "555123", "123 Main St"));
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "Doe", "55512345678", "123 Main St"));
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "Doe", "55512345a7", "123 Main St"));
    }

    @Test
    void addressCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "Doe", "5551234567", null));
    }

    @Test
    void addressCannotExceed30Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("1", "John", "Doe", "5551234567",
                "1234567890123456789012345678901"));
    }

    @Test
    void settersUpdateUpdatableFields() {
        Contact c = new Contact("1", "John", "Doe", "5551234567", "123 Main St");
        c.setFirstName("Jane");
        c.setLastName("Smith");
        c.setPhone("5559876543");
        c.setAddress("456 Oak Ave");

        assertEquals("Jane", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("5559876543", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());
    }

    @Test
    void settersRejectInvalidValues() {
        Contact c = new Contact("1", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("TooLongLastName"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
}