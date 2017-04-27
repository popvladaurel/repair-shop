package ro.vlad.entities;

import java.util.Date;

public class Case {
    private Client client;
    private Item item;
    private Date inDate;
    private Date deadline;
    private String defect;
    private String notes;
    private Date outDate;
    private Double cost;
    private Double estimate;

    public Case(Client client, Item item, Date inDate, Date deadline, String defect, String notes) {
        this.client = client;
        this.item = item;
        this.inDate = inDate;
        this.deadline = deadline;
        this.defect = defect;
        this.notes = notes;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setEstimate(Double estimate) {
        this.estimate = estimate;
    }
}
