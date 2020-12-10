module VeganShopFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires mysql.connector.java;
    requires java.sql;
    requires java.naming;

    opens ru.loghorrean.veganShop;
    opens ru.loghorrean.veganShop.controllers to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.dialog to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.admin to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.profile to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.mainScreen to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.client.dishCon to javafx.fxml;
    opens ru.loghorrean.veganShop.controllers.client.orderCon to javafx.fxml;
}