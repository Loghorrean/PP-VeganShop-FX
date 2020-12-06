package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.util.HashCompiler;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.io.IOException;
import java.sql.SQLException;

public class AuthoriseController extends DialogController {
    @FXML
    private DialogPane authoriseDialog;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    private MainData mainData;

    private User user = null;

    public void initialize() {
        try {
            mainData = MainData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processAuthorisation(ActionEvent event) throws IOException {
        CurrentUser.getInstance().setUser(user);
        String currentRole = user.getRole().getTitle();
        if (currentRole.equals("Admin")) {
            redirect(event, "admin/AdminMenuWindow");
        } else if (currentRole.equals("Customer")) {
            redirect(event, "mainScreens/MenuWindow");
        }
    }

    public boolean checkFields() {
        username.setText(username.getText().trim());
        pass.setText(pass.getText().trim());
        if (!Validator.validateAllFields(username.getText(), pass.getText())) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        try {
            user = mainData.getUserManager().getUserByUsername(username.getText());
            if (user == null || !checkIfPasswordIsRight(pass.getText())) {
                setMistake("Неверный юзернейм или пароль");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean checkIfPasswordIsRight(String password) {
        String hashedPass = HashCompiler.hashPassword(password, user.getSalt());
        assert hashedPass != null;
        return hashedPass.equals(user.getPassword());
    }
}
