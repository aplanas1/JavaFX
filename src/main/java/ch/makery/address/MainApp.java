package ch.makery.address;

import java.io.*;

import ch.makery.address.control.BirthdayStatisticsController;
import ch.makery.address.control.PersonEditDialogController;
import ch.makery.address.control.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import ch.makery.address.control.PersonOverviewController;
import ch.makery.address.model.Person;

import java.io.IOException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    public String root = "root-layout.fxml";

    public static String theme= "css/DarkTheme.css";

    /**
     * Constructor
     */
    public MainApp() {
        readXML();
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("root-layout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController rootLayoutController = loader.getController();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            rootLayoutController.setScene(scene, rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("person-overview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to show birthday statistics.
     */

    public void showGraph() {
        try {
            // Load the fxml file and create a new stage for the popup.

            /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("graph.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("LoL stats");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.show();*/

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("graph.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Graph");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("person-editDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

    public void readXML(){
        try {
            // internet URL
            URL url = new URL("https://analisi.transparenciacatalunya.cat/api/views/uqk7-bf9s/rows.xml");

            // download and save image
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("deads.xml");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            //close writers
            fos.close();
            rbc.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String fileName = "deads.xml";
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(fileName));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("row");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                try {
                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        String comarcadescripcio = element.getElementsByTagName("comarcadescripcio").item(0).getTextContent();
                        int comarcacodi = Integer.parseInt(element.getElementsByTagName("comarcacodi").item(0).getTextContent());
                        String sexedescripcio = element.getElementsByTagName("sexedescripcio").item(0).getTextContent();
                        int sexecodi = 0;
                        if (sexedescripcio.equals("Dona")) {
                            sexecodi = 1;
                        }

                        String data = element.getElementsByTagName("exitusdata").item(0).getTextContent();

                        String month = "00/00/00";
                        try {

                            LocalDate localDate = LocalDate.parse(data, formatter);
                            Month monthREAL = localDate.getMonth();
                            month = monthREAL.toString();

                        } catch (Exception e) {
                        }

                        System.out.println("-----------------------------------------");
                        System.out.println("Comarca : " + comarcadescripcio);
                        System.out.println("Comarca Codi : " + comarcacodi);
                        System.out.println("Sexe : " + sexedescripcio);
                        System.out.println("Sexe Codi : " + sexecodi);
                        System.out.println("Data : " + data);

                        personData.add(new Person(comarcadescripcio, comarcacodi, sexedescripcio, sexecodi, data));
                    }
                } catch (DOMException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
