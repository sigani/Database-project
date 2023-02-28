package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.TerminalTransactionsDelegate;
import model.*;
import ui.GUI;
import ui.LoginWindow;

/*
    REFERENCED
    https://canvas.ubc.ca/courses/101874/files/23715087?module_item_id=5104258 (THE JAVA/ORACLE TUTORIAL)
    https://www.students.cs.ubc.ca/~cs-304/resources/jdbc-oracle-resources/jdbc-java-looking-through-code.html
 */
public class PNETwo implements LoginWindowDelegate, TerminalTransactionsDelegate {
    private DatabaseConnectionHandler dbh;
    private LoginWindow loginWindow;

    public PNETwo() {
        dbh = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }
    @Override
    public void login(String username, String password) {
        boolean didConnect = dbh.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();

            new GUI(this);
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }

    //TODO: IMPLEMENT THESE
    @Override
    public void databaseSetup() {
//        dbh.databaseSetup();
    }

    @Override
    public void deleteStaff(String staffID) {
        dbh.deleteStaff(staffID);
    }

    @Override
    public void insertStaff(StaffModel model) {
        dbh.insertStaff(model);
    }

    @Override
    public StaffModel[] showStaff() {
        return dbh.showStaff();
    }

    @Override
    public void updateStaff(String staffID, String option, String value) {
        dbh.updateStaff(staffID, option, value);
    }

    @Override
    public EventsModel[] showCheapEvents() {
        EventsModel[] res= dbh.showCheapEvents();
        return res;
    }

    @Override
    public EventsModel[] showExpensiveEvents() {
        EventsModel[] res= dbh.showExpensiveEvents();
        return res;
    }

    @Override
    public RideModel[] showRidesWithRestrictions() {
        return dbh.showRidesWithRestrictions();
    }

    @Override
    public EventsModel[] showWhereEventsAre() {
        EventsModel[] res = dbh.showWhereEventsAre();
        return res;
    }

    @Override
    public BranchModel[] showNumberOfStaffAtEachBranch() {
        BranchModel[] res = dbh.showNumberOfStaffAtEachBranch();
        return res;
    }

    @Override
    public BranchModel[] showBranchesWithLowAmountOfStaff() {
        BranchModel[] res = dbh.showBranchesWithLowAmountOfStaff();
        return res;
    }

    @Override
    public BranchModel[] findBranchWithMostStaff() {
        BranchModel[] res = dbh.findBranchWithMostStaff();
        return res;
    }

    @Override
    public CustomerModel[] findSuperCustomers() {
        CustomerModel[] res = dbh.findSuperCustomers();
        return res;
    }

    @Override
    public CustomerModel[] findCustomers() {
        return dbh.findCustomers();
    }

    @Override
    public EventsModel[] showEvents() {
        return dbh.showEvents();
    }

    @Override
    public CustomerModel[] showCustomers() {
        return dbh.showCustomers();
    }

    public static void main(String args[]) {
        PNETwo pneTwo = new PNETwo();
        pneTwo.start();
    }
}
