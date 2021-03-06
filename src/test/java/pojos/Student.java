package pojos;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private int batch;

    public Student() {

    }

    public Student(String firstName, String lastName, String email, int batch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.batch = batch;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "Student{" +
                "\n firstName='" + firstName + '\'' +
                "\n lastName='" + lastName + '\'' +
                "\n email='" + email + '\'' +
                "\n batch=" + batch +
                '}';
    }
}
