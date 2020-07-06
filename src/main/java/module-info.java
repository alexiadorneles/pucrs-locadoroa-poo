module org.example {
    opens domain.cliente to com.google.gson;
    opens domain.automovel to com.google.gson;
    opens domain.locacao to com.google.gson;
    opens menu to com.google.gson;
    requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;
    exports domain;
    exports menu;
}