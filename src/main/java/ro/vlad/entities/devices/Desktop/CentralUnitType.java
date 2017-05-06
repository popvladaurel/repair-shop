package ro.vlad.entities.devices.Desktop;

public enum CentralUnitType {
    SUPER("Super Tower"),
    FULL("Full Tower"),
    MID("Mid Tower"),
    MINI("Mini Tower"),
    MICRO("Micro"),
    SLIM("Slim Line Tower"),
    SFF("Small Form Factor"),
    AIO("All-In-One"),
    ODD("Optical Drive"),
    OS("Operating System");

    private final String type;

    CentralUnitType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
