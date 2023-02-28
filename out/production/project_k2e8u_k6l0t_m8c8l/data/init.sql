drop table STAFF cascade constraints;
drop table GAMESOPERATOR cascade constraints;
drop table GAMESTANDAT cascade constraints;
drop table FOODSTANDPERSON cascade constraints;
drop table FOODSTANDAT cascade constraints;
drop table FOODBELONGSTO cascade constraints;
drop table RIDEOPERATOR cascade constraints;
drop table RIDEAT cascade constraints;
drop table RESTRICTIONS cascade constraints;
drop table CUSTOMERS cascade constraints;
drop table TICKETS cascade constraints;
drop table BRANCHES cascade constraints;
drop table GAMESTAND cascade constraints;
drop table FOODSTANDS cascade constraints;
drop table RIDES cascade constraints;
drop table EVENTLOCATION cascade constraints;
drop table EVENTS cascade constraints;
drop table POINTS cascade constraints;
drop table TICKETPRICE cascade constraints;


CREATE TABLE Branches
    (branchID varchar(30) PRIMARY KEY,
    branchAddress varchar(30) UNIQUE,
    branchName varchar(30));
CREATE TABLE Staff
    (staffID varchar(30) PRIMARY KEY,
    name varchar(30),
    homeAddress varchar(30),
    telephoneNumber varchar(30) UNIQUE,
    branchID varchar(30),
    FOREIGN KEY(branchID) REFERENCES Branches(branchID)
        ON DELETE CASCADE);

CREATE TABLE GameStand
    (gameStandID varchar(30) PRIMARY KEY,
    name varchar(30),
    price INT);

CREATE TABLE GamesOperator
    (gameStandID varchar(30),
    staffID varchar(30),
    PRIMARY KEY(gameStandID, staffID),
    FOREIGN KEY(gameStandID) REFERENCES GameStand(gameStandID)
        ON DELETE CASCADE,
    FOREIGN KEY(staffID) REFERENCES Staff(staffID)
        ON DELETE CASCADE);
CREATE TABLE GameStandAt
(
    gameStandID varchar(30),
    branchID    varchar(30),
    PRIMARY KEY (gameStandID, branchID),
    FOREIGN KEY (gameStandID) REFERENCES GameStand (gameStandID)
        ON DELETE CASCADE,
    FOREIGN KEY (branchID) REFERENCES Branches (branchID)
        ON DELETE CASCADE
);

CREATE TABLE FoodStands(
    foodStandID varchar(30) PRIMARY KEY,
    foodStandName varchar(30),
    foodStandType varchar(30)
                       );

CREATE TABLE FoodStandPerson(
    foodStandID varchar(30),
    staffID varchar(30),
    PRIMARY KEY(foodStandID, staffID),
    FOREIGN KEY(foodStandID) REFERENCES FoodStands(foodStandID)
        ON DELETE CASCADE,
    FOREIGN KEY(staffID) REFERENCES Staff(staffID)
        ON DELETE CASCADE
                            );

CREATE TABLE FoodStandAt(
    foodStandID varchar(30),
    branchID varchar(30),
    PRIMARY KEY(foodStandID, branchID),
    FOREIGN KEY(foodStandID) REFERENCES FoodStands(foodStandID)
        ON DELETE CASCADE,
    FOREIGN KEY(branchID) REFERENCES Branches(branchID)
        ON DELETE CASCADE
                        );
CREATE TABLE FoodBelongsTo(
    name varchar(30),
    foodStandID varchar(30),
    price varchar(30),
    PRIMARY KEY(name, foodStandID),
    FOREIGN KEY(foodStandID) REFERENCES FoodStands(foodStandID)
        ON DELETE CASCADE
                          );
CREATE TABLE Rides(
    rideID varchar(30),
    name varchar(30),
    rideType varchar( 30),
    PRIMARY KEY(rideID)
                  );

CREATE TABLE RideOperator(
    rideID varchar(30),
    staffID varchar( 30),
    PRIMARY KEY(rideID, staffID),
    FOREIGN KEY(rideID) REFERENCES Rides(rideID)
        ON DELETE CASCADE,
    FOREIGN KEY(staffID) REFERENCES Staff(staffID)
        ON DELETE CASCADE
                         );

CREATE TABLE RideAt(
    rideID varchar(30),
    branchID varchar(30),
    PRIMARY KEY(rideID, branchID),
    FOREIGN KEY(rideID) REFERENCES Rides(rideID)
        ON DELETE CASCADE,
    FOREIGN KEY(branchID) REFERENCES Branches(branchID)
        ON DELETE CASCADE
                   );
CREATE TABLE Restrictions(
    weight INT,
    height INT,
    healthIssues varchar(30),
    rideID varchar(30),
    PRIMARY KEY(rideID),
    FOREIGN KEY(rideID) REFERENCES Rides(rideID)
        ON DELETE CASCADE
                         );
CREATE TABLE EventLocation(
    eventVenue varchar(30),
    eventDate varchar(30),
    eventName varchar(30),
    branchID varchar(30),
    PRIMARY KEY(eventDate, eventName),
    FOREIGN KEY(branchID) REFERENCES Branches(branchID)
                          );
CREATE TABLE Events(
    eventID varchar(30) PRIMARY KEY,
    eventDate varchar(30),
    eventVenue varchar(30),
    price INT
                   );

CREATE TABLE POINTS(
    email varchar(30) PRIMARY KEY,
    points INT,
    name varchar(30)
                   );
CREATE TABLE Customers(
    customerID varchar(30) PRIMARY KEY,
    age INT,
    email varchar(30),
    telephoneNumber varchar(30),
    FOREIGN KEY(email) REFERENCES Points(email)
        ON DELETE CASCADE
                     );
CREATE TABLE TicketPrice(
    ticketType varchar(30) PRIMARY KEY,
    ticketPrice INT
                        );
CREATE TABLE Tickets(
    ticketID varchar(30) PRIMARY KEY,
    dateOfTicket varchar(30),
    customerID varchar(30),
    branchID varchar(30),
    ticketType varchar(30),
    FOREIGN KEY(customerID) REFERENCES Customers(customerID)
        ON DELETE CASCADE,
    FOREIGN KEY(branchID) REFERENCES Branches(branchID)
        ON DELETE SET NULL,
    FOREIGN KEY(ticketType) REFERENCES TicketPrice(ticketType)
        ON DELETE SET NULL
                    );

INSERT
INTO Branches(branchID, branchAddress, branchName)
VALUES ('B00000001', '1234 Unicorn Road', 'PNE #2');
INSERT
INTO Branches(branchID, branchAddress, branchName)
VALUES ('B00000002', '1234 Unicorn Street', 'PNE #3');
INSERT
INTO Branches(branchID, branchAddress, branchName)
VALUES ('B00000003', '1234 Unicorn Drive', 'PNE #4');
INSERT
INTO Branches(branchID, branchAddress, branchName)
VALUES ('B00000004', '1234 Uni Crescent', 'PNE #5');
INSERT
INTO Branches(branchID, branchAddress, branchName)
VALUES ('B00000005', '1234 Unicorn Lane', 'PNE #6');

INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000001', 'Joe', '123 Dogwood Drive', '7782221234', 'B00000001');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000002', 'Jess', '123 Dogwood Street', '7783331234', 'B00000002');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000003', 'Darcy', '123 Dogwood Crescent', '7784441234', 'B00000003');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000004', 'Sabrina', '123 Dogwood Way', '7785551234', 'B00000004');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000005', 'Jack', '123 Dogwood Lane', '7786661234', 'B00000005');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000006', 'Yeji', '124 Dogwood Drive', '7783333333', 'B00000001');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000007', 'Ryujin', '123 Dogwood Street', '7784444444', 'B00000002');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000008', 'Lia', '124 Dogwood Crescent', '7785555555', 'B00000003');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000009', 'Chaeryeong', '124 Dogwood Way', '7786666666', 'B00000004');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000010', 'Yuna', '69 Dogwood Lane', '7787777777', 'B00000005');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000011', 'John', '333 Dogwood Drive', '7788888888', 'B00000001');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000012', 'Blyke', '333 Dogwood Street', '7789999999', 'B00000002');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000013', 'Sera', '333 Dogwood Crescent', '7781234567', 'B00000003');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000014', 'Arlo', '333 Dogwood Way', '7781111111', 'B00000004');
INSERT
INTO Staff(staffID, name, homeAddress, telephoneNumber, branchID)
VALUES ('S00000015', 'Isen', '333 Dogwood Lane', '7782222222', 'B00000005');

INSERT
INTO GameStand(gameStandID, name, price)
VALUES ('G00000001', 'Bowling', 5);
INSERT
INTO GameStand(gameStandID, name, price)
VALUES ('G00000002', 'Shooting Range', 5);
INSERT
INTO GameStand(gameStandID, name, price)
VALUES ('G00000003', 'Rigged Ladder Game', 10);
INSERT
INTO GameStand(gameStandID, name, price)
VALUES ('G00000004', 'Baseball', 5);
INSERT
INTO GameStand(gameStandID, name, price)
VALUES ('G00000005', 'Dead Hang', 10);

INSERT
INTO GamesOperator(staffID, gameStandID)
VALUES('S00000001', 'G00000001');
INSERT
INTO GamesOperator(staffID, gameStandID)
VALUES('S00000002', 'G00000002');
INSERT
INTO GamesOperator(staffID, gameStandID)
VALUES('S00000003', 'G00000003');
INSERT
INTO GamesOperator(staffID, gameStandID)
VALUES('S00000004', 'G00000004');
INSERT
INTO GamesOperator(staffID, gameStandID)
VALUES('S00000005', 'G00000005');

INSERT
INTO GameStandAt(gameStandID, branchID)
VALUES('G00000001', 'B00000001');
INSERT
INTO GameStandAt(gameStandID, branchID)
VALUES('G00000002', 'B00000002');
INSERT
INTO GameStandAt(gameStandID, branchID)
VALUES('G00000003', 'B00000003');
INSERT
INTO GameStandAt(gameStandID, branchID)
VALUES('G00000004', 'B00000004');
INSERT
INTO GameStandAt(gameStandID, branchID)
VALUES('G00000005', 'B00000005');

INSERT
INTO FoodStands(foodStandID, foodStandName, foodStandType)
VALUES('F00000001', 'Tim Jortons', 'Fast Food');
INSERT
INTO FoodStands(foodStandID, foodStandName, foodStandType)
VALUES('F00000002', 'Dunkin Cronuts', 'Fast Food');
INSERT
INTO FoodStands(foodStandID, foodStandName, foodStandType)
VALUES('F00000003', 'Lemonade R Us', 'Drinks');
INSERT
INTO FoodStands(foodStandID, foodStandName, foodStandType)
VALUES('F00000004', 'Beaver Nails', 'Dessert');
INSERT
INTO FoodStands(foodStandID, foodStandName, foodStandType)
VALUES('F00000005', 'Triple Dohs', 'Fast Food');

INSERT
INTO FoodStandPerson(staffID, foodStandID)
VALUES('S00000006', 'F00000001');
INSERT
INTO FoodStandPerson(staffID, foodStandID)
VALUES('S00000007', 'F00000002');
INSERT
INTO FoodStandPerson(staffID, foodStandID)
VALUES('S00000008', 'F00000003');
INSERT
INTO FoodStandPerson(staffID, foodStandID)
VALUES('S00000009', 'F00000004');
INSERT
INTO FoodStandPerson(staffID, foodStandID)
VALUES('S00000010', 'F00000005');

INSERT
INTO FoodStandAt(foodStandID, branchID)
VALUES('F00000001', 'B00000001');
INSERT
INTO FoodStandAt(foodStandID, branchID)
VALUES('F00000002', 'B00000002');
INSERT
INTO FoodStandAt(foodStandID, branchID)
VALUES('F00000003', 'B00000003');
INSERT
INTO FoodStandAt(foodStandID, branchID)
VALUES('F00000004', 'B00000004');
INSERT
INTO FoodStandAt(foodStandID, branchID)
VALUES('F00000005', 'B00000005');

INSERT
INTO FoodBelongsTo(name, foodStandID, price)
VALUES('Double Double', 'F00000001', 2);
INSERT
INTO FoodBelongsTo(name, foodStandID, price)
VALUES('Donut', 'F00000002', 2);
INSERT
INTO FoodBelongsTo(name, foodStandID, price)
VALUES('Lemonade', 'F00000003', 5);
INSERT
INTO FoodBelongsTo(name, foodStandID, price)
VALUES('Nutella', 'F00000004', 9);
INSERT
INTO FoodBelongsTo(name, foodStandID, price)
VALUES('Milkshake', 'F00000005', 7);

INSERT
INTO Rides(rideID, name, rideType)
VALUES('R00000001', 'Elevator', 'Spooky');
INSERT
INTO Rides(rideID, name, rideType)
VALUES('R00000002', 'Atmosphere', 'Spooky');
INSERT
INTO Rides(rideID, name, rideType)
VALUES('R00000003', 'Easy Train', 'Kids');
INSERT
INTO Rides(rideID, name, rideType)
VALUES('R00000004', 'Butterfly', 'Kids');
INSERT
INTO Rides(rideID, name, rideType)
VALUES('R00000005', 'Screwdriver', 'Spooky');

INSERT
INTO RideOperator(staffID, rideID)
VALUES('S00000011', 'R00000001');
INSERT
INTO RideOperator(staffID, rideID)
VALUES('S00000012', 'R00000002');
INSERT
INTO RideOperator(staffID, rideID)
VALUES('S00000013', 'R00000003');
INSERT
INTO RideOperator(staffID, rideID)
VALUES('S00000014', 'R00000004');
INSERT
INTO RideOperator(staffID, rideID)
VALUES('S00000015', 'R00000005');

INSERT
INTO RideAt(rideID, branchID)
VALUES('R00000001', 'B00000001');
INSERT
INTO RideAt(rideID, branchID)
VALUES('R00000002', 'B00000002');
INSERT
INTO RideAt(rideID, branchID)
VALUES('R00000003', 'B00000003');
INSERT
INTO RideAt(rideID, branchID)
VALUES('R00000004', 'B00000004');
INSERT
INTO RideAt(rideID, branchID)
VALUES('R00000005', 'B00000005');

INSERT
INTO Restrictions(weight, height, healthIssues, rideID)
VALUES(200, 200, 'pregnant', 'R00000001');
INSERT
INTO Restrictions(weight, height, healthIssues, rideID)
VALUES(200, 200, 'pregnant', 'R00000002');
INSERT
INTO Restrictions(weight, height, healthIssues, rideID)
VALUES(30, 70, 'none', 'R00000003');
INSERT
INTO Restrictions(weight, height, healthIssues, rideID)
VALUES(30, 70, 'none', 'R00000004');
INSERT
INTO Restrictions(weight, height, healthIssues, rideID)
VALUES(200, 200, 'pregnant', 'R00000005');

INSERT
INTO EventLocation(eventVenue, eventDate, eventName, branchID)
VALUES('PNE #2', 'January 5th 2023', 'Rock n Roll', 'B00000001');
INSERT
INTO EventLocation(eventVenue, eventDate, eventName, branchID)
VALUES('PNE #3', 'March 5th 2024', 'Circus', 'B00000002');
INSERT
INTO EventLocation(eventVenue, eventDate, eventName, branchID)
VALUES('PNE #4', 'December 10th 2022', 'Beer Garden', 'B00000003');
INSERT
INTO EventLocation(eventVenue, eventDate, eventName, branchID)
VALUES('PNE #5', 'January 22nd 2023', 'Beer Garden', 'B00000004');
INSERT
INTO EventLocation(eventVenue, eventDate, eventName, branchID)
VALUES('PNE #6', 'May 18th 2023', 'Rock n Roll', 'B00000005');

INSERT
INTO Events(eventID, eventDate, eventVenue, price)
VALUES('E00000001', 'January 5th 2023', 'PNE #2', 40);
INSERT
INTO Events(eventID, eventDate, eventVenue, price)
VALUES('E00000002', 'March 5th 2024', 'PNE #3', 20);
INSERT
INTO Events(eventID, eventDate, eventVenue, price)
VALUES('E00000003', 'December 10th 2022', 'PNE #4', 10);
INSERT
INTO Events(eventID, eventDate, eventVenue, price)
VALUES('E00000004', 'January 22nd 2023', 'PNE #5', 10);
INSERT
INTO Events(eventID, eventDate, eventVenue, price)
VALUES('E00000005', 'May 18th 2023', 'PNE #6', 40);

INSERT
INTO Points(email, points, name)
VALUES('boogers@gmail.com', 69, 'Jaren');
INSERT
INTO Points(email, points, name)
VALUES('katisbest@hotmail.com', 50, 'Katrina');
INSERT
INTO Points(email, points, name)
VALUES('georgie2012@icloud.com', 25, 'Georgie');
INSERT
INTO Points(email, points, name)
VALUES('cassidy11@icloud.com', 40, 'Cassidy');
INSERT
INTO Points(email, points, name)
VALUES('shubhGreaterkat@ethereum.org', 50, 'Shubh');

INSERT
INTO Customers(customerID, age, email, telephoneNumber)
VALUES ('C00000001', 20, 'shubhGreaterkat@ethereum.org', '7781231234');
INSERT
INTO Customers(customerID, age, email, telephoneNumber)
VALUES ('C00000002', 20, 'boogers@gmail.com', '6044206969');
INSERT
INTO Customers(customerID, age, email, telephoneNumber)
VALUES ('C00000003', 65, 'katisbest@hotmail.com', '7781231235');
INSERT
INTO Customers(customerID, age, email, telephoneNumber)
VALUES ('C00000004', 10, 'georgie2012@icloud.com', '7781231236');
INSERT
INTO Customers(customerID, age, email, telephoneNumber)
VALUES ('C00000005', 11, 'cassidy11@icloud.com', '7781231237');

INSERT
INTO TicketPrice(ticketType, ticketPrice)
VALUES('General', 35);
INSERT
INTO TicketPrice(ticketType, ticketPrice)
VALUES('Senior', 10);
INSERT
INTO TicketPrice(ticketType, ticketPrice)
VALUES('Child', 25);

INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000001', 'October 15th 2022', 'C00000001', 'B00000001', 'General');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000002', 'October 16th 2022', 'C00000002', 'B00000002', 'General');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000003', 'October 18th 2022', 'C00000003', 'B00000003', 'Senior');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000004', 'October 18th 2022', 'C00000004', 'B00000002', 'Child');

INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000005', 'October 18th 2022', 'C00000005', 'B00000003', 'Child');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000006', 'October 19th 2022', 'C00000005', 'B00000002', 'Child');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000007', 'October 20th 2022', 'C00000005', 'B00000001', 'Child');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000008', 'October 21st 2022', 'C00000005', 'B00000004', 'Child');
INSERT
INTO Tickets(ticketID, dateOfTicket, customerID, branchID, ticketType)
VALUES ('T00000009', 'October 22nd 2022', 'C00000005', 'B00000005', 'Child');















