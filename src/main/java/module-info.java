module org.litterboxed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens org.litterboxed to javafx.fxml;
    exports org.litterboxed;
}