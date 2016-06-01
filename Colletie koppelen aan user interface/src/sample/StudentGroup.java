package sample;

import javafx.collections.*;
import javafx.scene.control.TreeItem;

/**
 * Created by sander on 1-4-2016.
 */
public class StudentGroup {

    private String name;
    private ObservableList<Student> students;

    private TreeItem<StudentGroup> treeNode;

    public StudentGroup(String name) {
        this.name = name;
        this.students = FXCollections.observableArrayList();
        this.treeNode = new TreeItem(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    public void addTreeItem(TreeItem<StudentGroup> studentGroup) {
        this.treeNode.getChildren().add(studentGroup);
    }

    public TreeItem<StudentGroup> getTreeItem() {
        return this.treeNode;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
