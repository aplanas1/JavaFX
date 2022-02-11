package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

    private final StringProperty comarca;
    private final IntegerProperty comarcacodi;
    private final StringProperty sexe;
    private final IntegerProperty sexecodi;
    private final StringProperty data;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, 0, null, 0, null);
    }

    public Person(String comarca, int comarcacodi, String sexe, int sexecodi, String data) {
        this.comarca = new SimpleStringProperty(comarca);
        this.comarcacodi = new SimpleIntegerProperty(comarcacodi);
        this.sexe = new SimpleStringProperty(sexe);
        this.sexecodi = new SimpleIntegerProperty(sexecodi);
        this.data = new SimpleStringProperty(data);
    }

    public String getComarca() {
        return comarca.get();
    }

    public StringProperty comarcaProperty() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca.set(comarca);
    }

    public int getComarcacodi() {
        return comarcacodi.get();
    }

    public IntegerProperty comarcacodiProperty() {
        return comarcacodi;
    }

    public void setComarcacodi(int comarcacodi) {
        this.comarcacodi.set(comarcacodi);
    }

    public String getSexe() {
        return sexe.get();
    }

    public StringProperty sexeProperty() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe.set(sexe);
    }

    public int getSexecodi() {
        return sexecodi.get();
    }

    public IntegerProperty sexecodiProperty() {
        return sexecodi;
    }

    public void setSexecodi(int sexecodi) {
        this.sexecodi.set(sexecodi);
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }
}