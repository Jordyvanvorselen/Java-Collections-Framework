package sample.GUI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Student;
import sample.StudentGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TreeView<StudentGroup> tree;
    @FXML
    private Button btnSaveStudent;
    @FXML
    private Button btnSaveStudentGroup;
    @FXML
    private Button btnEditStudent;
    @FXML
    private Button btnEditStudentGroup;
    @FXML
    private TextField txtStudent;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtStudentGroup;
    @FXML
    private Label lbStudent;

    @FXML
    private TableView<Student> table = new TableView<>();

    private ObservableList<Student> data;

    private TreeItem<StudentGroup> lastSelectedItem = null;
    private Student lastSelectedStudent = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initTreeView();
        initButtons();
    }

    /**
     * Initialize tree and events
     */
    private void initTreeView() {

        // Data
        StudentGroup root = new StudentGroup("Student groups");
        StudentGroup scrubs = new StudentGroup("Scrubs");
        StudentGroup normal = new StudentGroup("Normal");

        StudentGroup uberScrubs = new StudentGroup("Uber Scrubs");
        StudentGroup delta = new StudentGroup("Delta");
        Student willem = new Student("Willem", "123");
        Student jordy = new Student("Jordy", "234");
        Student bob = new Student("Bob", "345");
        Student colin = new Student("Colin", "456");

        scrubs.addStudents(willem);
        normal.addStudents(jordy);
        uberScrubs.addStudents(bob);
        delta.addStudents(colin);

        // Create tree
        TreeItem<StudentGroup> rootItem = new TreeItem<>(root);
        rootItem.setExpanded(true);

        rootItem.getChildren().add(scrubs.getTreeItem());
        rootItem.getChildren().add(normal.getTreeItem());

        scrubs.addTreeItem(new TreeItem<>(uberScrubs));
        normal.addTreeItem(new TreeItem<>(delta));

        tree.setRoot(rootItem);

        // Selection change event
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            lastSelectedItem = newVal;

            // Update table if selected item is a StudentGroup
            if(lastSelectedItem != null) {
                StudentGroup group = null;

                if (lastSelectedItem.getValue().getClass() == StudentGroup.class) {
                    group = lastSelectedItem.getValue();
                }
                if(group!=null) {
                    data = group.getStudents();
                    table.setItems(data);
                }
            }
        });
    }

    /**
     * Initialize table rows and events
     */
    private void initTable() {
        table.getColumns().clear();

        table.setEditable(true);

        TableColumn<Student, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(t -> (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue()));

        TableColumn<Student, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(t -> (t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue()));

        table.getColumns().addAll(name, id);

        // Selection change event
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lastSelectedStudent = newValue;
            }
        });
    }

    /**
     * Initialize button events
     */
    private void initButtons() {
        // New student
        btnSaveStudent.setOnAction((event) -> {
            String name = getStudentName();
            String id = getStudentId();

            if(!name.equals("") && !id.equals("")) {
                lastSelectedItem.getValue().addStudents(new Student(name, id));
            }

            clearTextboxes();
        });

        // New student group
        btnSaveStudentGroup.setOnAction((event) -> {

            String groupName = getStudentGroupName();
            if(!groupName.equals("")) {
                StudentGroup newGroup = new StudentGroup(groupName);
                newGroup.getStudents();

                if (lastSelectedItem != null)
                    lastSelectedItem.getChildren().add(newGroup.getTreeItem());
            }

            clearTextboxes();
        });

        // Edit student
        btnEditStudent.setOnAction((event) -> {
            if (lastSelectedStudent != null) {
                String name = getStudentName();
                String id = getStudentId();

                if (!name.equals("")) {
                    lastSelectedStudent.setName(name);
                }
                if (!id.equals("")) {
                    lastSelectedStudent.setId(id);
                }

                table.refresh();

                lastSelectedStudent = null;

                clearTextboxes();
            }
        });

        // Edit studentgroup
        btnEditStudentGroup.setOnAction((event) -> {
            if (lastSelectedItem != null) {
                String groupName = getStudentGroupName();
                if (!groupName.equals("")) {
                    StudentGroup group = lastSelectedItem.getValue();
                    group.setName(groupName);

                    lastSelectedItem.setExpanded(false);
                    lastSelectedItem.setExpanded(true);
                }

                clearTextboxes();
            }
        });
    }

    /**
     * Get the data from txtStudent
     * @return the text from txtStudent
     */
    private String getStudentName() {
        return txtStudent.getText();
    }

    /**
     * Get the data from txtStudentId
     * @return the text from txtStudentId
     */
    private String getStudentId() {
        return txtStudentId.getText();
    }

    /**
     * Get the data from txtStudentGroup
     * @return the text form txtStudentId
     */
    private String getStudentGroupName() {
        return txtStudentGroup.getText();
    }

    /**
     * Clear all textboxes
     */
    private void clearTextboxes() {
        txtStudent.clear();
        txtStudentGroup.clear();
        txtStudentId.clear();
    }
}
