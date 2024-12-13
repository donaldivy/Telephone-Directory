module com.telephone_directory.e_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.telephone_directory.e_project to javafx.fxml;
    exports com.telephone_directory.e_project;
}