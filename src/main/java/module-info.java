module org.example {
    opens domain to com.google.gson;
    opens menu to com.google.gson;
    requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;
    exports domain;
    exports menu;
}