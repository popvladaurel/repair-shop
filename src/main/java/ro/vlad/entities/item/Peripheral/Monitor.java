package ro.vlad.entities.item.Peripheral;

import ro.vlad.entities.Item;

public class Monitor extends Item {
    private String diagonal;

    public Monitor(String manufacturer, String model, String diagonal) {
        super(manufacturer, model);
        this.diagonal = diagonal;
    }
}
