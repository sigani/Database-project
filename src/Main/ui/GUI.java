package ui;
import delegates.TerminalTransactionsDelegate;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLOutput;

public class GUI extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private JFrame addFrame;
    private JFrame updateFrame;
    private JFrame deleteFrame;
    private JFrame showFrame;
    private JFrame testFrame;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton showButton;
    private JButton addButton2;
    private JButton deleteButton2;
    private JButton updateButton2;

    private JPanel leftPanel;
    private JPanel middleTopPanel;
    //private JList middleBottomPanel;

    private JPanel middleBottomPanel;
    private DefaultListModel<StaffModel> listModel;

    private JPanel bottomOfCombo;
    private JLabel blank;
    private JLabel blank2;
    private JLabel blank3;
    private JLabel blank4;
    private JTextField textBox;

    private JTextField staffID;
    private JTextField staffName;
    private JTextField staffAddress;
    private JTextField staffPhone;
    private JTextField staffBranch;
    private JTextField answerHere;
    private JTextField whichStaff;


    private JComboBox<String> cb;
    private JComboBox<String> cb2;
    private JComboBox<String> cb3;

    public int which;

    private JButton allTickets;
    private JButton allStaff;
    private JButton allEvents;
    private JButton allCustomers;
    private JButton selection;
    private JButton selection2;
    private JButton projection;
    private JButton join;

    private JButton join2;
    private JButton groupBy;
    private JButton having;
    private JButton nestedGroupBy;
    private JButton division;

    private JFrame selectFrame;
    private JFrame joinFrame;

    private TerminalTransactionsDelegate delegate;

    private JTable jt;

    private JScrollPane sp;

    private boolean cheap;

    public GUI(TerminalTransactionsDelegate delegate) {
        blank = new JLabel();
        blank2 = new JLabel();
        blank3 = new JLabel();
        blank4 = new JLabel();
        // THIS DELEGATE HAS ALL THE METHODS YOU GOTTA CALL
        this.delegate = delegate;
        createButtons();
        createPanels();
        createMainFrame();
        addPanels();
    }

    public void createButtons() {
        addButton = new JButton("Hire Staff");
        setSizeFontColour(addButton);
        addButton.addActionListener(this);
        updateButton = new JButton("Update Staff Information");
        setSizeFontColour(updateButton);
        updateButton.addActionListener(this);
        deleteButton = new JButton("Fire Staff");
        setSizeFontColour(deleteButton);
        deleteButton.addActionListener(this);
        showButton = new JButton("Show");
        setSizeFontColour(showButton);
        showButton.addActionListener(this);
    }

    public void setSizeFontColour(JButton jb) {
        //jb.setPreferredSize(new Dimension(50, 20));
        jb.setFont(new Font("Arial", Font.BOLD, 16));
        jb.setBackground(new Color(0xF5F5EF));
        jb.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public void createPanels(){
        createLeftPanel();
        createMiddleTopPanel();
        createMiddleBottomPanel();
    }

    private void createLeftPanel() {
        leftPanel = new JPanel(new GridLayout(4, 1));
        leftPanel.setBounds(10, 0, 300, 760);
        leftPanel.setBackground(new Color(0xF5F5EF));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        leftPanel.add(addButton);
        leftPanel.add(deleteButton);
        leftPanel.add(updateButton);
        leftPanel.add(showButton);

    }

    private void createMiddleTopPanel() {
        middleTopPanel = new JPanel(new GridLayout(1, 1));
        middleTopPanel.setBounds(320, 0, 940, 190);
        middleTopPanel.setBackground(new Color(0xF5F5EF));
        middleTopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        ImageIcon image = new ImageIcon("./src/Main/data/images/logo.png");
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setIcon(image);
        middleTopPanel.add(label);
    }

    /*
    private void createMiddleBottomPanel() {
        middleBottomPanel = new JPanel(new GridLayout(1, 1));
        middleBottomPanel.setBounds(320, 200, 940, 560);
        middleBottomPanel.setBackground(new Color(0xF5F5EF));
        middleBottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    }
    */

    /*
    public void createMiddleBottomPanel() {
        listModel = new DefaultListModel();
        middleBottomPanel = new JList(listModel);
        middleBottomPanel.setBounds(320, 200, 940, 560);
        middleBottomPanel.setBackground(new Color(0xF5F5EF));
        middleBottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        middleBottomPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        middleBottomPanel.setSelectedIndex(0);
    }
    */

    public void createMiddleBottomPanel() {
        middleBottomPanel = new JPanel(new GridLayout(1, 1));
        middleBottomPanel.setBounds(320, 200, 940, 560);
        middleBottomPanel.setBackground(new Color(0xF5F5EF));
        middleBottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        JLabel jl = new JLabel();
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setVerticalAlignment(JLabel.CENTER);
        ImageIcon image = new ImageIcon("./src/Main/data/images/cuteImage.png");
        jl.setIcon(image);
        middleBottomPanel.add(jl);
    }

    private void showStaffTable(StaffModel[] results) {
        String column[] = {"ID", "Name", "Home Address", "Telephone Number", "Branch ID"};
        String data[][] = new String[results.length][5]; // CHANGE THIS MAKE IT DYNAMIC
        for(int i = 0; i < results.length; i++) {
            StaffModel m = results[i];
            data[i][0] = m.getStaffID();
            data[i][1] = m.getStaffName();
            data[i][2] = m.getStaffAddress();
            data[i][3] = m.getStaffPhone() + "";
            data[i][4] = m.getBranchID();
            //System.out.println(data[i][0]);
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        //jt.setPreferredScrollableViewportSize(new Dimension(940,560));
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);
        //middleBottomPanel.add(sp);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);


        //mainFrame.getContentPane().add(sp);
        //mainFrame.update();
//        middleBottomPanel = sp;
    }

    private void showEventTable(EventsModel[] results) {
        String column[] = {"ID", "Date", "Venue", "Price"};
        String data[][] = new String[results.length][5]; // CHANGE THIS MAKE IT DYNAMIC
        for(int i = 0; i < results.length; i++) {
            EventsModel m = results[i];
            data[i][0] = m.getEventID();
            data[i][1] = m.getEventDate();
            data[i][2] = m.getEventVenue();
            data[i][3] = m.getEventPrice() + "";
            //System.out.println(data[i][0]);
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);


        //mainFrame.getContentPane().add(sp);
        //mainFrame.update();
//        middleBottomPanel = sp;
    }

    // creates the main frame, sets logo, size, nme
    public void createMainFrame() {
        mainFrame = new JFrame();
        ImageIcon image = new ImageIcon("./src/Main/data/images/logo2.png");
        mainFrame.setIconImage(image.getImage());
        mainFrame.setTitle("PNE 2");
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setSize(1280, 800);
        mainFrame.setVisible(true);
    }

    public void addPanels() {
        mainFrame.add(leftPanel);
        mainFrame.add(middleTopPanel);
        mainFrame.add(middleBottomPanel);
    }

    /*
    public void addFrame() {
        query = "Add";

        addFrame = new JFrame();
        addFrame.setLayout(null);
        addFrame.setTitle("PNE 2");
        addFrame.setSize(600, 400);
        addFrame.setResizable(false);
        addFrame.setVisible(true);
        String[] choices = { "Choose one of the following", "Events","Branches", "Rides","Staff","Food Stands","Customers", "Tickets", "Game Stands"};

        bottomOfCombo = new JPanel();
        bottomOfCombo.setBounds(0, 50, 600, 400);

        JPanel comboPanel = new JPanel(new GridLayout(1, 1));
        comboPanel.setBounds(0, 0, 500, 40);
        cb = new JComboBox<String>(choices);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 14));
        state.setText("Please select a choice above");

        textBox = new JTextField();
        textBox.setFont(new Font("Arial", Font.BOLD, 16));
        textBox.setPreferredSize(new Dimension(550, 20));

        addButton2 = new JButton("Submit");
        setSizeFontColour(addButton2);
        addButton2.addActionListener(this);

        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb.getSelectedItem().toString().equals("Events")) {
                        showTable("Events");
                        state.setText("To add an event, please type in eventID, eventDate, eventVenue, price");
                    } else if (cb.getSelectedItem().toString().equals("Branches")) {
                        showTable("Branches");
                        state.setText("To add a branch, please type in branchID, branchAddress, branchName");
                    } else if (cb.getSelectedItem().toString().equals("Rides")) {
                        showTable("Rides");
                        state.setText("To add a ride, please type in rideID, name, rideType");
                    } else if (cb.getSelectedItem().toString().equals("Staff")) {
                        showTable("Staff");
                        state.setText("To add a staff, please type in staffID, name, homeAddress, telephoneNumber, branchID");
                    } else if (cb.getSelectedItem().toString().equals("Food Stands")) {
                        showTable("Food Stands");
                        state.setText("To add a food stand, please type in foodStandID, foodStandName, foodStandType");
                    } else if (cb.getSelectedItem().toString().equals("Customers")) {
                        showTable("Customers");
                        state.setText("To add a customer, please type in CustomerID, age, email, telephoneNumber");
                    } else if (cb.getSelectedItem().toString().equals("Tickets")) {
                        showTable("Tickets");
                        state.setText("To add a ticket, please type in ticketID, date, customerID, branchID, ticketType");
                    }  else if (cb.getSelectedItem().toString().equals("Game Stands")) {
                        showTable("Game Stands");
                        state.setText("To add a game stand, please type in gameStandID, name, price");
                    }
                }
            }
        });

        bottomOfCombo.add(state);
        bottomOfCombo.add(textBox);
        bottomOfCombo.add(addButton2);
        addFrame.add(bottomOfCombo);
        comboPanel.add(cb);
        addFrame.add(comboPanel);


    }
    */

    public void addFrame() {
        addFrame = new JFrame();
        addFrame.setLayout(null);
        addFrame.setTitle("PNE 2");
        addFrame.setSize(600, 400);
        addFrame.setResizable(false);
        addFrame.setVisible(true);

        String[] choices = { "Choose one of the following", "Staff"};
        JPanel innerPanel = new JPanel(new GridLayout(8, 1));
        innerPanel.setBounds(7, 4, 572, 352);
        cb = new JComboBox<String>(choices);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 14));

        staffID = new JTextField();
        staffID.setFont(new Font("Arial", Font.BOLD, 14));
        staffID.setText("staffID: 'S########'");

        staffName = new JTextField();
        staffName.setFont(new Font("Arial", Font.BOLD, 14));
        staffName.setText("name: 'George'");

        staffAddress = new JTextField();
        staffAddress.setFont(new Font("Arial", Font.BOLD, 14));
        staffAddress.setText("homeAddress: '1234 Unicorn Road'");

        staffPhone = new JTextField();
        staffPhone.setFont(new Font("Arial", Font.BOLD, 14));
        staffPhone.setText("telephoneNumber: 7781231234");

        staffBranch = new JTextField();
        staffBranch.setFont(new Font("Arial", Font.BOLD, 14));
        staffBranch.setText("branchID: 'B########'");

        addButton2 = new JButton("Hire");
        setSizeFontColour(addButton2);
        addButton2.addActionListener(this);


        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb.getSelectedItem().toString().equals("Staff")) {
//                        showTable("Staff");
                        state.setText("Please input the staff details below");
                        innerPanel.add(staffID);
                        innerPanel.add(staffName);
                        innerPanel.add(staffAddress);
                        innerPanel.add(staffPhone);
                        innerPanel.add(staffBranch);
                        innerPanel.add(addButton2);

                    }
                }
            }
        });

        innerPanel.add(cb);
        innerPanel.add(state);
        addFrame.add(innerPanel);
    }

    /*
        public void deleteFrame() {
        query = "Delete";

        deleteFrame = new JFrame();
        deleteFrame.setLayout(null);
        deleteFrame.setTitle("PNE 2");
        deleteFrame.setSize(600, 400);
        deleteFrame.setResizable(false);
        deleteFrame.setVisible(true);

        String[] choices = { "Choose one of the following", "Events","Branches", "Rides","Staff","Food Stands","Customers", "Tickets", "Game Stands"};

        bottomOfCombo = new JPanel(new GridLayout(3, 1));
        bottomOfCombo.setBounds(0, 50, 580, 300);

        JPanel comboPanel = new JPanel(new GridLayout(1, 1));
        comboPanel.setBounds(0, 0, 500, 40);
        cb = new JComboBox<String>(choices);

        deleteButton2 = new JButton("Submit");
        setSizeFontColour(deleteButton2);
        deleteButton2.addActionListener(this);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 14));
        state.setText("Choose a tuple to delete below");

        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb.getSelectedItem().toString().equals("Events")) {
                        showTable("Events");
                    } else if (cb.getSelectedItem().toString().equals("Branches")) {
                        showTable("Branches");
                    } else if (cb.getSelectedItem().toString().equals("Rides")) {
                        showTable("Rides");
                    } else if (cb.getSelectedItem().toString().equals("Staff")) {
                        showTable("Staff");
                    } else if (cb.getSelectedItem().toString().equals("Food Stands")) {
                        showTable("Food Stands");
                    } else if (cb.getSelectedItem().toString().equals("Customers")) {
                        showTable("Customers");
                    } else if (cb.getSelectedItem().toString().equals("Tickets")) {
                        showTable("Tickets");
                    }  else if (cb.getSelectedItem().toString().equals("Game Stands")) {
                        showTable("Game Stands");
                    }
                }
            }
        });
        // TODO: add choices based off of which entity is selected
        String[] choices2 = {"Test", "Test1", "Test2"};
        cb2 = new JComboBox<>(choices2);


        bottomOfCombo.add(state);
        bottomOfCombo.add(cb2);
        bottomOfCombo.add(deleteButton2);
        deleteFrame.add(bottomOfCombo);
        comboPanel.add(cb);
        deleteFrame.add(comboPanel);
    }
    */

    public void deleteFrame() {
        deleteFrame = new JFrame();
        deleteFrame.setLayout(null);
        deleteFrame.setTitle("PNE 2");
        deleteFrame.setSize(600, 400);
        deleteFrame.setResizable(false);
        deleteFrame.setVisible(true);

        JPanel innerPanel = new JPanel(new GridLayout(8, 1));
        innerPanel.setBounds(7, 4, 572, 352);

        String[] choices = { "Choose one of the following", "Staff"};
        cb = new JComboBox<String>(choices);

        deleteButton2 = new JButton("Fire");
        setSizeFontColour(deleteButton2);
        deleteButton2.addActionListener(this);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 14));

        // TODO: add the staff IDs to choices2
        String[] choices2 = {"Test", "Test1", "Test2"};
        cb2 = new JComboBox<>(choices2);

        whichStaff = new JTextField();
        whichStaff.setFont(new Font("Arial", Font.BOLD, 14));

        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb.getSelectedItem().toString().equals("Staff")) {
//                        showTable("Staff");
                        state.setText("Enter the staffID of the staff you would like to fire below");
                        innerPanel.add(state);
                        innerPanel.add(whichStaff);
                        innerPanel.add(blank);
                        innerPanel.add(blank2);
                        innerPanel.add(blank3);
                        innerPanel.add(blank4);
                        innerPanel.add(deleteButton2);
                    }
                }
            }
        });

        innerPanel.add(cb);
        innerPanel.add(state);
        deleteFrame.add(innerPanel);


    }

    public void updateFrame() {
        updateFrame = new JFrame();
        updateFrame.setLayout(null);
        updateFrame.setTitle("PNE 2");
        updateFrame.setSize(600, 400);
        updateFrame.setResizable(false);
        updateFrame.setVisible(true);

        JPanel innerPanel = new JPanel(new GridLayout(8, 1));
        innerPanel.setBounds(7, 4, 572, 352);

        String[] choices = { "Choose one of the following", "Staff"};
        cb = new JComboBox<String>(choices);

        updateButton2 = new JButton("Update Information");
        setSizeFontColour(updateButton2);
        updateButton2.addActionListener(this);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 14));

        JLabel what = new JLabel();
        what.setFont(new Font("Arial", Font.ITALIC, 14));

        staffName = new JTextField();
        staffName.setFont(new Font("Arial", Font.BOLD, 14));

        // TODO: add the staff IDs to choices2
        String[] choices2 = {"Test", "Test1", "Test2"};
        cb2 = new JComboBox<>(choices2);

        whichStaff = new JTextField();
        whichStaff.setFont(new Font("Arial", Font.BOLD, 14));

        String[] choices3 = {"Choose one of the following", "name", "homeAddress", "telephoneNumber"};
        cb3 = new JComboBox<>(choices3);


        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb.getSelectedItem().toString().equals("Staff")) {
//                        showTable("Staff");
                        state.setText("Input the staffID of the staff whose information you'd like to update below");
                        what.setText("Choose which information you would like to update");
                        staffName.setText("");
                        innerPanel.add(state);
                        innerPanel.add(whichStaff);
                        innerPanel.add(what);
                        innerPanel.add(cb3);
                        innerPanel.add(staffName);
                        innerPanel.add(blank);
                        innerPanel.add(updateButton2);
                    }
                }
            }
        });

        cb3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb3.getSelectedItem().toString().equals("staffName")) {
                        staffName.setText("update name here");
                        which = 0;
                    } else if (cb3.getSelectedItem().toString().equals("staffAddress")) {
                        staffName.setText("update address here");
                        which = 1;
                    } else if (cb3.getSelectedItem().toString().equals("staffPhone")) {
                        staffName.setText("update phone number here");
                        which = 2;
                    }
                }
            }
        });


        innerPanel.add(cb);
        innerPanel.add(state);
        updateFrame.add(innerPanel);

    }

    public void showFrame() {
        showFrame = new JFrame();
        showFrame.setLayout(null);
        showFrame.setTitle("PNE 2");
        showFrame.setSize(600, 400);
        showFrame.setResizable(false);
        showFrame.setVisible(true);

        JPanel innerPanel = new JPanel(new GridLayout(6, 2));
        innerPanel.setBounds(7, 4, 562, 352);

        allTickets = new JButton("Show all tickets sold");
        setSizeFontColour(allTickets);
        allTickets.addActionListener(this);

        allStaff = new JButton("Show list of staff");
        setSizeFontColour(allStaff);
        allStaff.addActionListener(this);

        allEvents = new JButton("Show list of Events");
        setSizeFontColour(allEvents);
        allEvents.addActionListener(this);

        allCustomers = new JButton("Show list of Customers");
        setSizeFontColour(allCustomers);
        allCustomers.addActionListener(this);

        selection = new JButton("Cheapest/Most Expensive Event");
        setSizeFontColour(selection);
        selection.addActionListener(this);

        projection = new JButton("Rides with Restrictions");
        setSizeFontColour(projection);
        projection.addActionListener(this);

        join = new JButton("Events at specific Branch");
        setSizeFontColour(join);
        join.addActionListener(this);

        groupBy = new JButton("Find how many staff at each branch");
        setSizeFontColour(groupBy);
        groupBy.addActionListener(this);

        having = new JButton("Find how many staff at low-staffed branches");
        setSizeFontColour(having);
        having.setFont(new Font("Arial", Font.BOLD, 12));
        having.addActionListener(this);

        nestedGroupBy = new JButton("Branch with most number of staff");
        setSizeFontColour(nestedGroupBy);
        nestedGroupBy.setFont(new Font("Arial", Font.BOLD, 11));
        nestedGroupBy.addActionListener(this);

        division = new JButton("Top customer");
        setSizeFontColour(division);
        division.addActionListener(this);

        innerPanel.add(allTickets);
        innerPanel.add(allStaff);
        innerPanel.add(selection);
        innerPanel.add(projection);
        innerPanel.add(join);
        innerPanel.add(groupBy);
        innerPanel.add(having);
        innerPanel.add(nestedGroupBy);
        innerPanel.add(division);
        innerPanel.add(allEvents);
        innerPanel.add(allCustomers);
        showFrame.add(innerPanel);
    }

    private void selectFrame() {
        selectFrame = new JFrame();
        selectFrame.setLayout(null);
        selectFrame.setTitle("PNE 2");
        selectFrame.setSize(400, 200);
        selectFrame.setResizable(false);
        selectFrame.setVisible(true);

        JPanel innerPanel = new JPanel(new GridLayout(3, 1));
        innerPanel.setBounds(7, 4, 372, 152);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 12));
        state.setText("Choose to see either the most expensive or cheapest event");

        String[] choices = { "Choose one of the following", "Most Expensive", "Cheapest"};
        cb = new JComboBox<String>(choices);

        selection2 = new JButton("select");
        setSizeFontColour(selection2);
        selection2.addActionListener(this);

        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cb.getSelectedItem().toString().equals("Most Expensive")) {
                        // TODO: get the most expensive event and set it to a var that we can show on table on button click
                        cheap = false;
                    } else if (cb.getSelectedItem().toString().equals("Cheapest")) {
                        // TODO: get the cheapest event and set it to a var that we can show on table on button click
                        cheap = true;
                    }
                }
            }
        });

        innerPanel.add(state);
        innerPanel.add(cb);
        innerPanel.add(selection2);
        selectFrame.add(innerPanel);
    }

    public void joinFrame() {
        joinFrame = new JFrame();
        joinFrame.setLayout(null);
        joinFrame.setTitle("PNE 2");
        joinFrame.setSize(400, 200);
        joinFrame.setResizable(false);
        joinFrame.setVisible(true);

        JPanel innerPanel = new JPanel(new GridLayout(4, 2));
        innerPanel.setBounds(7, 4, 372, 152);

        JLabel state = new JLabel();
        state.setFont(new Font("Arial", Font.ITALIC, 10));
        state.setText("Choose which tables you'd like to join");

        join2 = new JButton("join");
        setSizeFontColour(join2);
        join2.addActionListener(this);

        String[] choices = { "Choose one of the following", "Events"};
        cb = new JComboBox<String>(choices);

        String[] choices2 = { "Choose one of the following", "Branches"};
        cb2 = new JComboBox<String>(choices2);

        answerHere = new JTextField();
        answerHere.setFont(new Font("Arial", Font.BOLD, 14));
        answerHere.setText("Insert which branch");

        innerPanel.add(state);
        innerPanel.add(blank);
        innerPanel.add(cb);
        innerPanel.add(cb2);
        innerPanel.add(answerHere);
        innerPanel.add(blank2);
        innerPanel.add(join2);
        joinFrame.add(innerPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addFrame();
        } else if (e.getSource() == updateButton) {
            updateFrame();
        } else if (e.getSource() == deleteButton) {
            deleteFrame();
        } else if (e.getSource() == showButton) {
            showFrame();
        } else if (e.getSource() == addButton2) {
            StaffModel model = new StaffModel(staffID.getText(),
                    staffName.getText(),
                    staffAddress.getText(),
                    staffPhone.getText(),
                    staffBranch.getText());
            delegate.insertStaff(model);
            System.out.println(staffID.getText() + " " + staffName.getText() + " " + staffAddress.getText() + " " + staffPhone.getText() + " " + staffBranch.getText());
            StaffModel[] results = delegate.showStaff();
            showStaffTable(results);
        } else if(e.getSource() == deleteButton2) {
            System.out.println("We are firing staff " + whichStaff.getText());
            delegate.deleteStaff(whichStaff.getText());
            deleteFrame.setVisible(false);
            StaffModel[] results = delegate.showStaff();
            showStaffTable(results);
        } else if (e.getSource() == updateButton2) {
            // the number [0,2] is represented by the variable called "what"
            System.out.println("We are updating staff member " + whichStaff.getText() + "'s " +
                    cb3.getSelectedItem().toString() + " to " + staffName.getText());
            delegate.updateStaff(whichStaff.getText(), cb3.getSelectedItem().toString(), staffName.getText());
            updateFrame.setVisible(false);
            StaffModel[] results = delegate.showStaff();
            showStaffTable(results);
        } else if (e.getSource() == selection) {
            selectFrame();
        } else if (e.getSource() == projection) {
            // TODO: add the method that does our projection query here
            // listModel.add() <- add all d the rides with restrictions
            RideModel[] results = delegate.showRidesWithRestrictions();
            showRidesTable(results);
        } else if (e.getSource() == join) {
            joinFrame();
        } else if (e.getSource() == groupBy) {
            BranchModel[] result = delegate.showNumberOfStaffAtEachBranch();
            showBranchTable(result);
        } else if (e.getSource() == having) {
            BranchModel[] result = delegate.showBranchesWithLowAmountOfStaff();
            showBranchTable(result);
        } else if (e.getSource() == nestedGroupBy) {
            BranchModel[] result = delegate.findBranchWithMostStaff();
            showBranchTable(result);
        } else if (e.getSource() == division) {
            CustomerModel[] result = delegate.findSuperCustomers();
            showCustomerTable(result);
        } else if (e.getSource() == selection2) {
            EventsModel[] results;
            if (cheap) {
                results = delegate.showCheapEvents();
            } else {
                results = delegate.showExpensiveEvents();
            }
            showEventTable(results);
            //listModel.clear();
            // listModel.add(CHEAPEST/MOSTEXPENSIVE);
        } else if (e.getSource() == join2) {
            // TODO: add the method that does our join query here
            // first table = cb.getSelectedItem().toString()
            // second table = cb2.getSelectedItem().toString()
            // what to join on = answerHere.getText()
        }
        else if(e.getSource() == allStaff) {
            StaffModel[] results = delegate.showStaff();
            System.out.println(results.length);
            showStaffTable(results);
        }
        else if(e.getSource() == allTickets) {
            CustomerModel[] result = delegate.findCustomers();
            showTicketsTable(result);
        } else if(e.getSource() == allEvents) {
            EventsModel[] result = delegate.showEvents();
            showEvents(result);
        } else if(e.getSource() == allCustomers) {
            CustomerModel[] result = delegate.showCustomers();
            showTheCustomers(result);
        }
    }

    private void showRidesTable(RideModel[] result) {
        String column[] = {"Name", "Weight Restriction", "Height Restriction", "Other Health Restrictions"};
        String data[][] = new String[result.length][4]; // CHANGE THIS MAKE IT DYNAMIC
        for(int i = 0; i < result.length; i++) {
            RideModel c = result[i];
            data[i][0] = c.getRideName();
            data[i][1] = c.getWeight() + "";
            data[i][2] = c.getHeight() + "";
            data[i][3] = c.getHealthIssue();
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);
    }

    private void showCustomerTable(CustomerModel[] result) {
        String column[] = {"ID", "Age", "Email", "Telephone"};
        String data[][] = new String[result.length][4]; // CHANGE THIS MAKE IT DYNAMIC
        for(int i = 0; i < result.length; i++) {
            CustomerModel c = result[i];
            data[i][0] = c.getCustomerID();
            data[i][1] = c.getAge() + "";
            data[i][2] = c.getEmail();
            data[i][3] = c.getTelephoneNumber();
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);
    }

    private void showTicketsTable(CustomerModel[] result) {
        String column[] = {"ID", "Age", "Email", "Telephone", "Branch"};
        String data[][] = new String[result.length][5]; // CHANGE THIS MAKE IT DYNAMIC
        for(int i = 0; i < result.length; i++) {
            CustomerModel c = result[i];
            data[i][0] = c.getCustomerID();
            data[i][1] = c.getAge() + "";
            data[i][2] = c.getEmail();
            data[i][3] = c.getTelephoneNumber();
            data[i][4] = c.getBranchID();
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);
    }

    private void showBranchTable(BranchModel[] result) {
        String column[] = {"ID", "Address", "Name", "Number of staff"};
        String data[][] = new String[result.length][4]; // CHANGE THIS MAKE IT DYNAMIC
        for(int i = 0; i < result.length; i++) {
            BranchModel m = result[i];
            data[i][0] = m.getBranchID();
            data[i][1] = m.getBranchAddress();
            data[i][2] = m.getBranchName();
            data[i][3] = m.getNumOfStaff() + "";
            //System.out.println(data[i][0]);
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);
    }

    private void showEvents(EventsModel[] result) {
        String column[] = {"ID", "Date", "Venue", "Price"};
                String data[][] = new String[result.length][4];
        for(int i = 0; i < result.length; i++) {
            EventsModel c = result[i];
            data[i][0] = c.getEventID();
            data[i][1] = c.getEventDate() + "";
            data[i][2] = c.getEventVenue() + "";
            data[i][3] = String.valueOf(c.getEventPrice());
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);
    }

    private void showTheCustomers(CustomerModel[] result) {
        String column[] = {"ID", "Age", "Email", "Telephone"};
        String data[][] = new String[result.length][4];
        for(int i = 0; i < result.length; i++) {
            CustomerModel c = result[i];
            data[i][0] = c.getCustomerID();
            data[i][1] = c.getAge() + "";
            data[i][2] = c.getEmail() + "";
            data[i][3] = c.getTelephoneNumber();
        }
        jt = new JTable(data, column);
        jt.setBounds(320, 200, 940, 560);
        jt.setBackground(new Color(0xF5F5EF));
        sp = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        jt.setVisible(true);

        testFrame = new JFrame();
        testFrame.add(sp);
        testFrame.setSize(2000, 800);
        testFrame.setVisible(true);
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