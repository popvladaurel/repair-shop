package ro.vlad.entities.item.Desktop;

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
