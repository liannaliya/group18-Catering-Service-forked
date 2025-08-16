module com.oop.groupeighteen.group18cateringservice {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.base;

    exports com.oop.groupeighteen.group18cateringservice;
    exports com.oop.groupeighteen.group18cateringservice.Lianna;
    exports com.oop.groupeighteen.group18cateringservice.Maliha;
    exports com.oop.groupeighteen.group18cateringservice.rahul;
    exports com.oop.groupeighteen.group18cateringservice.Tanvir;

    opens com.oop.groupeighteen.group18cateringservice to javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.Lianna to javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.Lianna.models to javafx.base, javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.Maliha to javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.rahul to javafx.fxml;
    opens com.oop.groupeighteen.group18cateringservice.Tanvir to javafx.fxml;
}
