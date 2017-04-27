package ro.vlad.entities;

public abstract class Item {
    private String manufacturer;
    private String model;

    public Item(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }
}
