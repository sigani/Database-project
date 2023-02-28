package model;

public class EventsModel {
    private String eventID;
    private String eventDate;
    private String eventVenue;
    private int eventPrice;
    private String branchID = null;

    public EventsModel(String id, String date, String venue, int price) {
        this.eventID = id;
        this.eventDate = date;
        this.eventVenue = venue;
        this.eventPrice = price;
    }

    public EventsModel(String id, String date, String venue, int price, String branchID) {
        this.eventID = id;
        this.eventDate = date;
        this.eventVenue = venue;
        this.eventPrice = price;
        this.branchID = branchID;
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public int getEventPrice() {
        return eventPrice;
    }

    public String getBranchID() {
        return branchID;
    }
}
