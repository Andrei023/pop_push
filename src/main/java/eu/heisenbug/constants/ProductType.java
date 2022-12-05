package eu.heisenbug.constants;

public enum ProductType {
    QUICK_POP("quick_pop"),
    QUICK_PUSH("quick_push");

    public final String label;

    ProductType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
