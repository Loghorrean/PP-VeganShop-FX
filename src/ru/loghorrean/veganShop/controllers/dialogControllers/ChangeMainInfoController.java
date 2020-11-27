package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.models.database.managers.UserManager;
import ru.loghorrean.veganShop.util.Validator;

import java.io.IOException;
import java.sql.SQLException;

public class ChangeMainInfoController extends DialogController implements IFill {
    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField phone;

    private UserEntity currentUser;

    @Override
    public void fillDialog() {
        username.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        firstname.setText(currentUser.getFirstName());
        lastname.setText(currentUser.getLastName());
        phone.setText(currentUser.getPhone());
    }

    @Override
    public void initialize() {
        currentUser = CurrentUser.getInstance().getUser();
    }

    @Override
    public boolean checkFields() {
        username.setText(username.getText().trim());
        email.setText(email.getText().trim());
        if (!Validator.validateAllFields(username.getText(), email.getText())) {
            setMistake("Никнейм и email обязательны к заполнению");
            return false;
        }
        try {
            if (!username.getText().equals(currentUser.getUsername()) && UserManager.getInstance().checkIfUserExists(username.getText())) {
                setMistake("Пользователь с таким именем уже существует");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            if (!email.getText().equals(currentUser.getEmail()) && UserManager.getInstance().checkIfEmailExists(email.getText())) {
                setMistake("Почта с таким именем уже зарегистрирована");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void changeMainInfo(ActionEvent event) throws IOException, SQLException {
        currentUser.setUsername(username.getText());
        currentUser.setEmail(email.getText());
        currentUser.setFirstName(firstname.getText());
        currentUser.setLastName(lastname.getText());
        currentUser.setPhone(phone.getText());
        UserManager.getInstance().updateUser(currentUser);
        setSuccess("Информация о пользователе обновлена");
        redirect(event, "profile/ProfileWindow");
    }
}
