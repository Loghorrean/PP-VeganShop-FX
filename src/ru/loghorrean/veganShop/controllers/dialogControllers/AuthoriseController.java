package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.controllers.BaseController;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.models.database.managers.UserManager;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthoriseController extends BaseController {
    @FXML
    private DialogPane authoriseDialog;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    private MainData mainData;

    public void initialize() {
        try {
            mainData = MainData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserEntity processAuthorisation() throws SQLException {
        return mainData.getUserManager().authoriseUser(username.getText().trim());
    }

    public boolean checkValidation() throws SQLException {
        if (!checkFields()) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        if (!mainData.getUserManager().checkIfCredentialsAreRight(username.getText().trim(), pass.getText().trim())) {
            setMistake("Хуево");
            return false;
        }
//        if () {
//
//        }
        return true;
    }

    private boolean checkFields() {
        if (username.getText().trim().isEmpty() || pass.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
