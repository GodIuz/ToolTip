module com.droidgeniuslabs.tooltip {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires org.json;

    opens com.droidgeniuslabs.tooltip to javafx.fxml;
    exports com.droidgeniuslabs.tooltip;
    exports com.droidgeniuslabs.tooltip.Controllers;
    opens com.droidgeniuslabs.tooltip.Controllers to javafx.fxml;
}