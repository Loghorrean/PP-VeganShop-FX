package ru.loghorrean.veganShop.controllers.mainScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.loghorrean.veganShop.controllers.BaseController;

import java.io.IOException;

public class LogoController extends BaseController {
    @FXML
    private Button button;

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        redirect(event, "mainScreens/Main");
    }

    @Override
    public void initialize() {

    }
}
