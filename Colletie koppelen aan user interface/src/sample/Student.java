package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by sander on 1-4-2016.
 */
public class Student {

    private SimpleStringProperty name;
    private SimpleStringProperty id;

    public Student(String name, String id) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
