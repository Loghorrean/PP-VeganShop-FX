package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.models.database.managers.UserManager;
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

    private UserEntity currentUser;

    public void initialize() {
        currentUser = CurrentUser.getInstance().getUser();
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
        if (!oldHashedPass.equals(currentUser.getPassword())) {
            return false;
        }
        return true;
    }

    public void changePassword() throws SQLException {
        String hashedPass = HashCompiler.hashPassword(newPassword.getText(), currentUser.getSalt());
        currentUser.setPassword(hashedPass);
        UserManager.getInstance().updateUser(currentUser);
        setSuccess("Пароль изменен");
    }
}
