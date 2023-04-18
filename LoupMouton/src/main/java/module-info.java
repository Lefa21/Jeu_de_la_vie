module com.application.loupmouton {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.application.loupmouton to javafx.fxml;
    exports com.application.loupmouton;
}