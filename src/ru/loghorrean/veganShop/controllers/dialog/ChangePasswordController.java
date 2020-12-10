package ru.loghorrean.veganShop.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.models.ProfileData;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;
import ru.loghorrean.veganShop.util.HashCompiler;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;

public class ChangePasswordController extends DialogController {
    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField newPasswordConfirm;

    private User currentUser;

    private UsersManager usersManager;

    public void initialize() {
        currentUser = CurrentUser.getInstance().getUser();
        try {
            usersManager = ProfileData.getInstance().getUsersManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFields() {
        oldPassword.setText(oldPassword.getText().trim());
        newPassword.setText(newPassword.getText().trim());
        newPasswordConfirm.setText(newPasswordConfirm.getText().trim());
        if (!Validator.validateAllFields(oldPassword.getText(), newPassword.getText(), newPasswordConfirm.getText())) {
            setMistake("Поля должны быть заполнены");
            return false;
        }
        if (!this.checkOldPassword()) {
            setMistake("Текущий пароль введен неверно");
            return false;
        }
        if (!this.newPassword.getText().equals(newPasswordConfirm.getText())) {
            setMistake("Новые пароли не совпадают");
            return false;
        }
        return true;
    }

    private boolean checkOldPassword() {
        String oldHashedPass = HashCompiler.hashPassword(oldPassword.getText(), currentUser.getSalt());
        assert oldHashedPass != null;
        return oldHashedPass.equals(currentUser.getPassword());
    }

    public void changePassword() throws SQLException {
        String hashedPass = HashCompiler.hashPassword(newPassword.getText(), currentUser.getSalt());
        currentUser.setPassword(hashedPass);
        usersManager.update(currentUser);
        setSuccess("Пароль изменен");
    }
}
