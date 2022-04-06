module taquin.qaud {
    requires javafx.controls;
    requires javafx.fxml;


    opens Model to javafx.fxml;
    exports Model;
}