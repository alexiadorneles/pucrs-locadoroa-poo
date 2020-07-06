module org.example {
    requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;
    opens domain to com.google.gson;
    exports domain;
    exports menu;
}