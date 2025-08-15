module com.oop.groupeighteen.group18cateringservice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.groupeighteen.group18cateringservice to javafx.fxml;
    exports com.oop.groupeighteen.group18cateringservice;
}