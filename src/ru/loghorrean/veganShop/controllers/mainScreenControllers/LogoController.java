package ru.loghorrean.veganShop.controllers.mainScreenControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.loghorrean.veganShop.controllers.BaseController;

import java.io.IOException;

public class LogoController extends BaseController {
    @FXML
    private Button button;

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        redirect(event, "mainScreens/MainWindow");
    }

    @Override
    public void initialize() {

    }
}
