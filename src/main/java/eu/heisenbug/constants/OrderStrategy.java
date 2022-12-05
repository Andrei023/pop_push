package eu.heisenbug.constants;

public enum OrderStrategy {
    ASCENDING("ascending"),
    DESCENDING("descending");

    public final String label;

    OrderStrategy(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
