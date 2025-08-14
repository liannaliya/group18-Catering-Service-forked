module com.oop.groupeighteen.group18cateringservice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.groupeighteen.group18cateringservice to javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.rahul to javafx.fxml, java.base ;
    exports com.oop.groupeighteen.group18cateringservice;
}