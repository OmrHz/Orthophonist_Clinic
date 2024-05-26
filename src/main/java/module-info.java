module org.example.demo {
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
    requires annotations;
    opens ESI.TP.Clinic.Modules.RDV to javafx.base;
    opens ESI.TP.Clinic.Modules.Other to javafx.base;
    opens ESI.TP.Clinic.Modules.orthophoniste to javafx.base;
    opens ESI.TP.Clinic.Modules.patient to javafx.base;
    opens ESI.TP.Clinic.Modules.BO to javafx.base;
    opens ESI.TP.Clinic.Modules.test to javafx.base;
    opens ESI.TP.Clinic.Modules.Fiche to javafx.base;
    opens ESI.TP.Clinic to javafx.fxml;
    exports ESI.TP.Clinic;
    opens ESI.TP.Clinic.Controllers to javafx.fxml;
    exports ESI.TP.Clinic.Controllers ;


}