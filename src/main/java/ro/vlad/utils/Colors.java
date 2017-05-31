package ro.vlad.utils;

public enum Colors {
    RED("rgba(215, 0, 0, 0.9)"),
    GREEN("rgba(34, 187, 69, 1)"),
    BLUE("rgba(17, 94, 150, 0.80)"),
    YELLOW("rgba(255, 235, 59, 1)");

    private final String color;

    Colors(String color) {
        this.color = color;}

    @Override
    public String toString() {
        return color;}
}
