module gtm {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens it.unipv.ingsfw to javafx.graphics, javafx.fxml;

    exports it.unipv.ingsfw;

}