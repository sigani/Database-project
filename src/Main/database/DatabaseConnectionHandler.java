package database;

import model.*;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// REFERENCED FROM THE
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertStaff(StaffModel model) {
        try{
            String query = "INSERT INTO Staff VALUES(?,?,?,?,?)";
            String sid = model.getStaffID();
            String name = model.getStaffName();
            String addr = model.getStaffAddress();
            String pn = model.getStaffPhone();
            String branch = model.getBranchID();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,sid);
            ps.setString(2,name);
            ps.setString(3,addr);
            ps.setString(4,pn);
            ps.setString(5,branch);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateStaff(String staffID, String option, String value) {
        try {
            System.out.println(staffID);
            System.out.println(option);
            System.out.println(value);
            String query;
            switch (option) {
                case "name":
                    query = "UPDATE Staff SET NAME = ? WHERE staffID = ?";
                    break;
                case "homeAddress":
                    query = "UPDATE Staff SET HOMEADDRESS = ? WHERE staffID = ?";
                    break;
                case "telephoneNumber":
                    query = "UPDATE Staff SET TELEPHONENUMBER = ? WHERE staffID = ?";
                    break;
                default:
                    query = "UPDATE Staff SET NAME = ? WHERE staffID = ?";
                    return;
            }
//            ps.setString(1, option);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, value);
            ps.setString(2, staffID);
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteStaff(String staffID) {
        try{
            String query = "DELETE FROM Staff WHERE STAFFID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,staffID);
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public StaffModel[] showStaff() {
        ArrayList<StaffModel> result = new ArrayList<>();
        String query = "SELECT * FROM Staff";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                StaffModel model = new StaffModel(rs.getString("staffID"),
                        rs.getString("name"),
                        rs.getString("homeAddress"),
                        rs.getString("telephoneNumber"),
                        rs.getString("branchID"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new StaffModel[result.size()]);
    }

    public EventsModel[] showCheapEvents() {
        ArrayList<EventsModel> result = new ArrayList<>();
        String query = "SELECT * FROM Events WHERE PRICE < 20";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                EventsModel model = new EventsModel(rs.getString("eventID"),
                        rs.getString("eventDate"),
                        rs.getString("eventVenue"),
                        rs.getInt("price"));
                result.add(model);
                System.out.println(rs.getString("eventID"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new EventsModel[result.size()]);
    }

    public EventsModel[] showExpensiveEvents() {
        ArrayList<EventsModel> result = new ArrayList<>();
        String query = "SELECT * FROM Events WHERE PRICE > 20";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                EventsModel model = new EventsModel(rs.getString("eventID"),
                        rs.getString("eventDate"),
                        rs.getString("eventVenue"),
                        rs.getInt("price"));
                result.add(model);
                System.out.println(rs.getString("price"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new EventsModel[result.size()]);
    }

    public EventsModel[] showWhereEventsAre() {
        ArrayList<EventsModel> result = new ArrayList<>();
        String query = "SELECT * FROM Events JOIN EVENTLOCATION E on EVENTS.EVENTVENUE = E.EVENTVENUE and EVENTS.EVENTDATE = E.EVENTDATE";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                EventsModel model = new EventsModel(rs.getString("eventID"),
                        rs.getString("eventDate"),
                        rs.getString("eventVenue"),
                        rs.getInt("price"),
                        rs.getString("branchID"));
                result.add(model);
                System.out.println(rs.getString("branchID"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new EventsModel[result.size()]);
    }

    public BranchModel[] showNumberOfStaffAtEachBranch() {
        ArrayList<BranchModel> result = new ArrayList<>();
        String query = "SELECT BRANCHES.BRANCHID, BRANCHADDRESS, BRANCHNAME, COUNT(STAFFID) as STAFFS " +
                "FROM BRANCHES join STAFF S2 on BRANCHES.BRANCHID = S2.BRANCHID " +
                "GROUP BY BRANCHES.BRANCHID, BRANCHNAME, BRANCHADDRESS";

        return getBranchModels(result, query);
    }

    public BranchModel[] showBranchesWithLowAmountOfStaff() {
        ArrayList<BranchModel> result = new ArrayList<>();
        String query = "SELECT BRANCHES.BRANCHID, BRANCHADDRESS, BRANCHNAME, COUNT(STAFFID) as STAFFS " +
                "FROM BRANCHES join STAFF S2 on BRANCHES.BRANCHID = S2.BRANCHID " +
                "HAVING COUNT(STAFFID) < 3 " +
                "GROUP BY BRANCHES.BRANCHID, BRANCHNAME, BRANCHADDRESS";

        return getBranchModels(result, query);
    }

    private BranchModel[] getBranchModels(ArrayList<BranchModel> result, String query) {
        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                BranchModel model = new BranchModel(rs.getString("branchID"),
                        rs.getString("branchAddress"),
                        rs.getString("branchName"),
                        rs.getInt("STAFFS"));
                result.add(model);
                System.out.println(rs.getString("branchID"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new BranchModel[result.size()]);
    }

    public BranchModel[] findBranchWithMostStaff() {
        ArrayList<BranchModel> result = new ArrayList<>();
        String query = "SELECT BRANCHES.BRANCHID, BRANCHADDRESS, BRANCHNAME, COUNT(STAFFID) AS STAFFS " +
                "FROM BRANCHES join STAFF S2 on BRANCHES.BRANCHID = S2.BRANCHID " +
                "GROUP BY BRANCHES.BRANCHID, BRANCHADDRESS, BRANCHNAME " +
                "HAVING COUNT(STAFFID) = " +
                "(SELECT MAX(COUNT(STAFFID)) " +
                "FROM BRANCHES join STAFF S2 on BRANCHES.BRANCHID = S2.BRANCHID " +
                "GROUP BY BRANCHES.BRANCHID, BRANCHNAME)";

        return getBranchModels(result, query);
    }

    public CustomerModel[] findSuperCustomers() {
        /*
        SELECT c.customerID, c.age, c.email, c.telephoneNumber
        FROM Customers c
        WHERE NOT EXISTS
            ((SELECT b.branchID
              FROM Branches b)
             MINUS
             (SELECT tt.branchID
              FROM Tickets tt
              WHERE tt.customerID = c.customerID))
         */
        ArrayList<CustomerModel> result = new ArrayList<>();
        String query = "SELECT c.customerID, c.age, c.email, c.telephoneNumber " +
                "FROM Customers c " +
                "WHERE NOT EXISTS " +
                "    ((SELECT b.branchID" +
                "      FROM Branches b)" +
                "     MINUS" +
                "     (SELECT tt.branchID" +
                "      FROM Tickets tt" +
                "      WHERE tt.customerID = c.customerID))";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CustomerModel model = new CustomerModel(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                result.add(model);
                System.out.println(rs.getString("c.customerID"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }


        return result.toArray(new CustomerModel[result.size()]);
    }

    public RideModel[] showRidesWithRestrictions() {
        ArrayList<RideModel> result = new ArrayList<>();
        String query = "SELECT Rides.NAME, Weight, Height, HealthIssues FROM RIDES JOIN RESTRICTIONS R on RIDES.RIDEID = R.RIDEID";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                RideModel model = new RideModel(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4)
                );
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new RideModel[result.size()]);
    }

    public CustomerModel[] findCustomers() {
        ArrayList<CustomerModel> result = new ArrayList<>();
        String query = "SELECT CUSTOMERS.CUSTOMERID, AGE, CUSTOMERS.EMAIL, TELEPHONENUMBER, BRANCHID from CUSTOMERS join POINTS P on P.EMAIL = CUSTOMERS.EMAIL join TICKETS T on CUSTOMERS.CUSTOMERID = T.CUSTOMERID";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CustomerModel model = new CustomerModel(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }


        return result.toArray(new CustomerModel[result.size()]);
    }

    public EventsModel[] showEvents() {
        ArrayList<EventsModel> result = new ArrayList<>();
        String query = "SELECT * FROM Events";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                EventsModel model = new EventsModel(rs.getString("eventID"),
                        rs.getString("eventDate"),
                        rs.getString("eventVenue"),
                        rs.getInt("price"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new EventsModel[result.size()]);
    }

    public CustomerModel[] showCustomers() {
        ArrayList<CustomerModel> result = new ArrayList<>();
        String query = "SELECT * FROM Customers";

        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CustomerModel model = new CustomerModel(rs.getString("customerID"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("telephoneNumber"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new CustomerModel[result.size()]);
    }
    /*CREATE TABLE Customers(
    customerID varchar(30) PRIMARY KEY,
    age INT,
    email varchar(30),
    telephoneNumber varchar(30),
    FOREIGN KEY(email) REFERENCES Points(email)
        ON DELETE CASCADE
                     );*/

}
