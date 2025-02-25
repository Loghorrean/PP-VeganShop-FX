package ru.loghorrean.veganShop.controllers.mainScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ru.loghorrean.veganShop.controllers.BaseController;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.sql.SQLException;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void shutdownNow() {
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
        dialog.showAndWait();
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
        dialog.showAndWait();
    }

    @FXML
    public void openAboutDialog() {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("AboutUsDialog")
                        .createDialog("О нашей компании", mainVBox)
                        .addButtons(ButtonType.OK)
                        .build();
        dialog.showAndWait();
    }
}
