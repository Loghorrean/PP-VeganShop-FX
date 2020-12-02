package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.exceptions.UserException;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.util.HashCompiler;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;

public class RegistrationController extends DialogController {
    @FXML
    private DialogPane registerDialog;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField passRepeat;

    private MainData mainData;

    private User userToRegister;

    public void initialize() {
        try {
            mainData = MainData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processRegistration() throws SQLException {
        String randSalt = HashCompiler.getRandomSalt();
        userToRegister = new User.UserBuilder()
                .withUsername(username.getText())
                .withEmail(email.getText())
                .withSalt(randSalt)
                .withPassword(HashCompiler.hashPassword(pass.getText(), randSalt))
                .withRole(mainData.getRoleByTitle("Customer"))
                .build();
        mainData.registerUser(userToRegister);
    }

    public boolean checkFields() {
        username.setText(username.getText().trim());
        email.setText(email.getText().trim());
        pass.setText(pass.getText().trim());
        passRepeat.setText(passRepeat.getText().trim());
        if (!Validator.validateAllFields(username.getText(), email.getText(), pass.getText(), passRepeat.getText())) {
            setMistake("Все поля должны быть заполнены.");
            return false;
        }
        if (!pass.getText().equals(passRepeat.getText())) {
            setMistake("Пароли не совпадают.");
            return false;
        }
        if (checkIfUserExists()) {
            setMistake("Пользователь с таким именем существует. Пожалуйста, выберите другое имя.");
            return false;
        }
        if (checkIfEmailExists()) {
            setMistake("Такой почтовый адрес уже используется. Пожалуйста, используйте другой");
            return false;
        }
        return true;
    }

    private boolean checkIfUserExists() {
        try {
            return mainData.getUserManager().checkIfUserExists(username.getText());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkIfEmailExists() {
        try {
            return mainData.getUserManager().checkIfEmailExists(email.getText());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
