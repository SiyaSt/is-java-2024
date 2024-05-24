package itmo.anastasiya.user.entity;

public enum Role {
    USER("User"),
    ADMIN("Admin");
    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static Role fromString(String value) {
        if (value != null) {
            for (Role pt : Role.values()) {
                if (value.equalsIgnoreCase(pt.value)) {
                    return pt;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }
}
