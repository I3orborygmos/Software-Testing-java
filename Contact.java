package contact;

public class Contact {

    // Required, unique, non-null, max 10 chars, NOT updatable
    private final String contactId;

    private String firstName;   // required, non-null, max 10 chars
    private String lastName;    // required, non-null, max 10 chars
    private String phone;       // required, non-null, exactly 10 digits
    private String address;     // required, non-null, max 30 chars

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {
        validateContactId(contactId);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // ---- Validators ----------------------------------------------------

    private void validateContactId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Contact ID must not be null.");
        }
        if (value.length() > 10) {
            throw new IllegalArgumentException("Contact ID must not exceed 10 characters.");
        }
    }

    private void validateFirstName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("First name must not be null.");
        }
        if (value.length() > 10) {
            throw new IllegalArgumentException("First name must not exceed 10 characters.");
        }
    }

    private void validateLastName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Last name must not be null.");
        }
        if (value.length() > 10) {
            throw new IllegalArgumentException("Last name must not exceed 10 characters.");
        }
    }

    private void validatePhone(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Phone must not be null.");
        }
        if (!value.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits.");
        }
    }

    private void validateAddress(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Address must not be null.");
        }
        if (value.length() > 30) {
            throw new IllegalArgumentException("Address must not exceed 30 characters.");
        }
    }

    // ---- Getters -------------------------------------------------------
    // Note: no setter for contactId — the field is final and immutable.

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getPhone()     { return phone; }
    public String getAddress()   { return address; }

    // ---- Setters (updatable fields only) -------------------------------

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }
}