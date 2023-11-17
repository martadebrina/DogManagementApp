package ui;

public enum ButtonNames {
    ADD("Add Dog"),
    VIEW("View My Dogs"),
    REMOVE("Remove Dog"),
    ADDHR("Add Health Records"),
    VIEWHR("View My Dog's Health Record"),
    REMOVEHR("Remove Health Record"),
    SAVE("Save"),
    LOAD("Load"),
    EXIT("Exit");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
