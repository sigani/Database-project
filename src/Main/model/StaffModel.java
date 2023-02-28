package model;

public class StaffModel {
    private String staffID, staffName, staffAddress, branchID;
    private String staffPhone;

    public StaffModel(String staffID, String staffName, String staffAddress, String staffPhone, String branchID) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffAddress = staffAddress;
        this.staffPhone = staffPhone;
        this.branchID = branchID;
    }

    public String getStaffID() {
        return this.staffID;
    }
    public String getStaffName() {
        return this.staffName;
    }
    public String getStaffAddress() {
        return this.staffAddress;
    }
    public String getStaffPhone() {
        return this.staffPhone;
    }
    public String getBranchID() {
        return this.branchID;
    }
}
