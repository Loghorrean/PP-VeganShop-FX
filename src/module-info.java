module VeganShopFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires mysql.connector.java;
    requires java.sql;
    requires java.naming;

    opens ru.loghorrean.veganShop;
    opens ru.loghorrean.veganShop.controllers to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.dialogControllers to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.adminControllers to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.profileControllers to javafx.fxml;
}