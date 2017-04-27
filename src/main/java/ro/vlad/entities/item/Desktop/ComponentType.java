package ro.vlad.entities.item.Desktop;

public enum ComponentType {
    CPU("Processor"),
    MB("MotherBoard"),
    GPU("Graphics Unit"),
    RAM("Memory"),
    HDD("Hard-Disk Drive"),
    SSD("Solid-State Drive"),
    ODD("Optical Drive"),
    OS("Operating System");

    private final String type;

    ComponentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
