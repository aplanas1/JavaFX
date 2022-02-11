module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ch.makery.address to javafx.fxml;
    exports ch.makery.address;
    exports ch.makery.address.control;
    opens ch.makery.address.control to javafx.fxml;
}