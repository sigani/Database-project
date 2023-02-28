package delegates;

import model.*;

/**
 * PNETwo INITALIZES THESE METHODS
 * ParkConsoleUI CALLS THEM!!!!!!!!!!!!!!!!!!
 * Why??  its a design principle apparently
 * So the GUI class will handle ui
 * pnetwo will handle the logic
 */
public interface TerminalTransactionsDelegate {
    public void databaseSetup();

    public void deleteStaff(String staffID);
    public void insertStaff(StaffModel model);
    public StaffModel[] showStaff();
    public void updateStaff(String staffID, String option, String value);
    public EventsModel[] showCheapEvents();
    public EventsModel[] showExpensiveEvents();
    public RideModel[] showRidesWithRestrictions();
    public EventsModel[] showWhereEventsAre();
    public BranchModel[] showNumberOfStaffAtEachBranch();
    public BranchModel[] showBranchesWithLowAmountOfStaff();
    public BranchModel[] findBranchWithMostStaff();
    public CustomerModel[] findSuperCustomers();
    public CustomerModel[] findCustomers();
    public EventsModel[] showEvents();
    public CustomerModel[] showCustomers();

}
