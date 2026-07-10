module gtm {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens it.unipv.ingsfw to javafx.graphics, javafx.fxml;
    opens it.unipv.ingsfw.controller to javafx.fxml;
    opens it.unipv.ingsfw.model to javafx.base;

    exports it.unipv.ingsfw;

}