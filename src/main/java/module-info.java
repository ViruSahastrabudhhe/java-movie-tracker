module org.litterboxed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens org.litterboxed to javafx.fxml, javafx.base;
    opens org.litterboxed.domain.model to javafx.fxml, javafx.base;
    exports org.litterboxed;
    exports org.litterboxed.domain.model;
}