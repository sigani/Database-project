package model;

public class CustomerModel {
    private String customerID;
    private int age;
    private String email;
    private String telephoneNumber;

    private String branchID;

    public CustomerModel(String id, int age, String email, String tele) {
        this.customerID = id;
        this.age = age;
        this.email = email;
        this.telephoneNumber = tele;
    }

    public CustomerModel(String id, int age, String email, String tele, String b) {
        this.customerID = id;
        this.age = age;
        this.email = email;
        this.telephoneNumber = tele;
        this.branchID = b;
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getBranchID() {
        return branchID;
    }
}
