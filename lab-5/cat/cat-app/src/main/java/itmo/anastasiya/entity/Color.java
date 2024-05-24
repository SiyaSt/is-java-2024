package itmo.anastasiya.entity;


public enum Color {
    BLACK("Black"),
    WHITE("White"),
    RED("Red"),
    GREY("Grey"),
    TURTLE("turtle"),
    ;

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public static Color fromString(String value) {
        if (value != null) {
            for (Color pt : Color.values()) {
                if (value.equalsIgnoreCase(pt.value)) {
                    return pt;
                }
            }
        }
        return null;
    }
}
