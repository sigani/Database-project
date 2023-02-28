package model;

public class BranchModel {
    private String branchID;
    private String branchAddress;
    private String branchName;
    private int numOfStaff;

    public BranchModel(String id, String addr, String bName, int n) {
        this.branchID = id;
        this.branchAddress = addr;
        this.branchName = bName;
        this.numOfStaff = n;
    }

    public BranchModel(String id, String addr, String bName) {
        this.branchID = id;
        this.branchAddress = addr;
        this.branchName = bName;
    }

    public BranchModel(String id, String name, int n) {
        this.branchID = id;
        this.branchName = name;
        this.numOfStaff = n;
    }

    public String getBranchID() {
        return branchID;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public String getBranchName() {
        return branchName;
    }

    public int getNumOfStaff() {
        return numOfStaff;
    }
}
