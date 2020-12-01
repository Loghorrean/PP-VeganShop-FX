package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;
import ru.loghorrean.veganShop.util.validators.Validator;

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

    private UsersManager manager;

    private User currentUser;

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
        try {
            manager = new UsersManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            if (!username.getText().equals(currentUser.getUsername()) && manager.checkIfUserExists(username.getText())) {
                setMistake("Пользователь с таким именем уже существует");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            if (!email.getText().equals(currentUser.getEmail()) && UsersManager.getInstance().checkIfEmailExists(email.getText())) {
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
        UsersManager.getInstance().updateUser(currentUser);
        setSuccess("Информация о пользователе обновлена");
        redirect(event, "profile/ProfileWindow");
    }
}
