package ro.vlad.entities.devices.Desktop;

import ro.vlad.entities.Item;

import java.util.List;

public class CentralUnit extends Item {
    private List<Component> componentList;
    private CentralUnitType type;

    CentralUnit(String manufacturer, String model, CentralUnitType type) {
        super(manufacturer, model);
        this.type = type;
    }

    public void addComponentToCentralUnit(Component component) {
        componentList.add(component);
    }
}
