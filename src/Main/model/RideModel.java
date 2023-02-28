package model;

public class RideModel {
    private String rideName;
    private int weight;
    private int height;
    private String healthIssue;

    public RideModel(String name, int w, int h, String issue) {
        this.rideName = name;
        this.weight = w;
        this.height = h;
        this.healthIssue = issue;
    }


    public String getRideName() {
        return rideName;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getHealthIssue() {
        return healthIssue;
    }
}
