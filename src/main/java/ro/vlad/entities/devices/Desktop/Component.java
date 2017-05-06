package ro.vlad.entities.devices.Desktop;

import ro.vlad.entities.Item;

public class Component extends Item {

    private ComponentType type;

    Component(ComponentType type, String manufacturer, String model) {
        super(manufacturer, model);
        this.type = type;
    }

    public ComponentType getType() {
        return type;
    }


}
