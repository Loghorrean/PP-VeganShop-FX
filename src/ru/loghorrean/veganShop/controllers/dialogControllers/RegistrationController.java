package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.BaseController;
import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.util.HashCompiler;
import ru.loghorrean.veganShop.util.RegexCompiler;
import ru.loghorrean.veganShop.util.Regexes;
import ru.loghorrean.veganShop.util.Roles;

import java.sql.SQLException;

public class RegistrationController extends BaseController {
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

    public void initialize() {
        try {
            mainData = MainData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processRegistration() throws SQLException {
        String newUsername = username.getText().trim();
        String newEmail = email.getText().trim();
        String randSalt = HashCompiler.getRandomSalt();
        String newPassword = HashCompiler.hashPassword(pass.getText().trim(), randSalt);
        UserEntity user = new UserEntity.UserBuilder()
                .withUsername(newUsername)
                .withEmail(newEmail)
                .withPassword(newPassword)
                .withSalt(randSalt)
                .withRole(Roles.Customer)
                .build();
        MainData.getInstance().getUserManager().registerUser(user);
    }

    public boolean checkValidation() throws SQLException {
        if (!checkFields()) {
            setMistake("Все поля должны быть заполнены.");
            return false;
        }
        if (!pass.getText().trim().equals(passRepeat.getText().trim())) {
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
        // TODO: does not work
//        if (!RegexCompiler.compileRegEx(email.getText().trim(), Regexes.MAIL)) {
//            setMistake("Почтовый адрес не сооветствует шаблону.");
//            return false;
//        }
//        if (!RegexCompiler.compileRegEx(pass.getText().trim(), Regexes.PASSWORD)) {
//            setMistake("Пароль не соответствует шаблону");
//            return false;
//        }
        return true;
    }

    private boolean checkFields() {
        return !username.getText().trim().isEmpty()
                && !email.getText().trim().isEmpty()
                && !pass.getText().trim().isEmpty()
                && !passRepeat.getText().trim().isEmpty();
    }

    private boolean checkIfUserExists() throws SQLException {
        return mainData.getUserManager().checkIfUserExists(username.getText().trim());
    }

    private boolean checkIfEmailExists() throws SQLException {
        return mainData.getUserManager().checkIfEmailExists(email.getText().trim());
    }
}
