package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ru.loghorrean.veganShop.controllers.dialogControllers.RegistrationController;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.util.HashCompiler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MainController {
    @FXML
    private VBox mainVBox;

    @FXML
    private Label test;

    @FXML
    private Button registerButton;

    @FXML
    private Button authorizeButton;

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
    public void shutdownApplication() {
        System.exit(0);
    }

    @FXML
    public void openRegisterDialog() {
        Dialog<ButtonType> registerDialog = new Dialog<>();
        registerDialog.initOwner(mainVBox.getScene().getWindow());
        registerDialog.setTitle("Регистрация");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/loghorrean/veganShop/views/dialogs/RegisterDialog.fxml"));
        try {
            registerDialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialogue");
            e.printStackTrace();
        }
        registerDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        RegistrationController controller = loader.getController();
        final Button buttonOk = (Button) registerDialog.getDialogPane().lookupButton(ButtonType.OK);
        buttonOk.addEventFilter(ActionEvent.ACTION, actionEvent-> {
            try {
                if (!controller.checkValidation()) {
                    actionEvent.consume();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Optional<ButtonType> result = registerDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                controller.processRegistration();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void openAuthorizeDialog() {
        Dialog<ButtonType> registerDialog = new Dialog<>();
        registerDialog.initOwner(mainVBox.getScene().getWindow());
        registerDialog.setTitle("Авторизация");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/loghorrean/veganShop/views/dialogs/AuthorizeDialog.fxml"));
        try {
            registerDialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialogue");
            e.printStackTrace();
        }
        registerDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = registerDialog.showAndWait();
    }

    @FXML
    public void openAboutDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainVBox.getScene().getWindow());
        dialog.setTitle("О нашей компании");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/loghorrean/veganShop/views/dialogs/AboutUsDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialogue");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> result = dialog.showAndWait();
    }
}
