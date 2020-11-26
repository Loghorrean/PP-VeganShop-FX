package ru.loghorrean.veganShop.controllers.mainScreenControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.BaseController;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.dialogControllers.AuthoriseController;
import ru.loghorrean.veganShop.controllers.dialogControllers.RegistrationController;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MainController extends BaseController {
    @FXML
    private VBox mainVBox;

    @FXML
    private Label test;

    @FXML
    private Button registerButton;

    @FXML
    private Button authoriseButton;

    @FXML
    private Button shutdownButton;

    @FXML
    private Button aboutUsButton;

    private MainData mainModel;

    public void initialize() {
        try {
            mainModel = MainData.getInstance();
            mainModel.setRoles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void shutdownApplication() {
        System.exit(0);
    }

    @FXML
    public void openRegisterDialog() {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("RegisterDialog")
                        .createDialog("Регистрация", mainVBox)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("processRegistration")
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
    }

    @FXML
    public void openAuthoriseDialog(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("AuthoriseDialog")
                        .createDialog("Авторизация", mainVBox)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .addValidationToButton(ButtonType.OK)
                        .redirectsFrom(event)
                        .onSuccess("processAuthorisation")
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
    }

    @FXML
    public void openAboutDialog() {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("AboutUsDialog")
                        .createDialog("О нашей компании", mainVBox)
                        .addButtons(ButtonType.OK)
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
    }
}
