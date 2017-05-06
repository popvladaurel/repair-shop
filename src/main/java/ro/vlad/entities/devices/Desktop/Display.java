package ro.vlad.entities.devices.Desktop;

import ro.vlad.entities.Item;

public class Display extends Item {
    private String resolution;

    public Display(String manufacturer, String model, String resolution) {
        super(manufacturer, model);
        this.resolution = resolution;
    }
}
