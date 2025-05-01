module com.example.safe {
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
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    opens com.example.safe to javafx.fxml;
    opens model to org.hibernate.orm.core;
    exports com.example.safe;
    exports com.example.safe.Controllers;
    opens com.example.safe.Controllers to javafx.fxml;
}