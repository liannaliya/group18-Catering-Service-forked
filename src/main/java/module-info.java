module com.oop.groupeighteen.group18cateringservice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.oop.groupeighteen.group18cateringservice to javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.Maliha to javafx.fxml,javafx.base;
    exports com.oop.groupeighteen.group18cateringservice;
}