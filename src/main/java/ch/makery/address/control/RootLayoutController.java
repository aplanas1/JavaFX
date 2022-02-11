package ch.makery.address.control;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import ch.makery.address.MainApp;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    Pane pane;
    Scene scene;

    @FXML
    private MenuItem light, dark;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Un constructor con el que le pasamos a la calse sus variables.
     * @param scene es la scene principal.
     * @param pane es el pane principal.
     */
    public void setScene(Scene scene, Pane pane) {
        this.scene = scene;
        this.pane = pane;
    }

    /**
     * Cambiamos el steelsheets del pane y le ponemos encima del que tiene el que nosotros queremos.
     */
    public void dark() {
        pane.getScene().getStylesheets().clear();
        pane.getStylesheets().add("DarkTheme.css");
    }

    /**
     * Borramos el steelsheets que hay actualmente para volver al anterior (en este caso se borra el negro y se queda el blanco que habia antes).
     */
    public void white(){
        pane.getScene().getStylesheets().clear();
        pane.getStylesheets().add("WhiteTheme.css");
    }

    /**
     * Reset de la aplicación.b
     */
    public void resetFile(){
        mainApp.readXML();
    }
    /**
     * Este metodo cierra la aplicación.
     */
    public void closeFile(){
        Platform.exit();}

    /**
     * Abrimos una pagina web externa de ayuda (este javadoc).
     */
    public void helpWeb(){

    }
}
