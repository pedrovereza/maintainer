package conference.model;

public enum TopicosPesquisa {

    SOFTWARE_PRODUCT_LINES("Software Product Lines"),
    SOFTWARE_REUSE("Software Reuse"),
    SOFTWARE_ARCHITECTURE("Software Architecture"),
    SOFTWARE_QUALITY("Software Quality"),
    SOFTWARE_TESTING("Software Testing"),
    ASPECT_ORIENTED_PROGRAMMING("Aspect Oriented Programming"),
    MODULATIRY("Modularity");


    private String description;

    TopicosPesquisa(String description) {

        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
