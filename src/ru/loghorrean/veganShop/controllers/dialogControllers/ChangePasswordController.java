package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.BaseController;
import ru.loghorrean.veganShop.controllers.IValidate;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.util.HashCompiler;

public class ChangePasswordController extends BaseController implements IValidate {
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
//        if (!this.validator.validateAllFields(oldPassword.getText().trim())) {
//            setMistake("Поле должно быть заполнено");
//            return false;
//        }
        if (!this.checkOldPassword()) {
            setMistake("Пароль не совпадает с текущим");
            return false;
        }
        return true;
    }

    public boolean checkOldPassword() {
        String hashedPass = HashCompiler.hashPassword(oldPassword.getText().trim(), currentUser.getSalt());
        if (!hashedPass.equals(currentUser.getPassword())) {
            setMistake("Введенный пароль неправилен");
            return false;
        }
        return true;
    }
}
