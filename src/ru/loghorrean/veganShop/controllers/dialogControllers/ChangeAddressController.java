package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.exceptions.UserException;
import ru.loghorrean.veganShop.models.ProfileData;
import ru.loghorrean.veganShop.models.database.entities.City;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.io.IOException;
import java.sql.SQLException;

public class ChangeAddressController extends DialogController implements IFill {
    @FXML
    private ComboBox<City> city;

    @FXML
    private TextField street;

    @FXML
    private TextField house;

    @FXML
    private TextField flat;

    private User currentUser;

    private ProfileData data;

    private ObservableList<City> cities;

    @Override
    public void initialize() {
        currentUser = CurrentUser.getInstance().getUser();
        try {
            data = ProfileData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        city.setItems(FXCollections.observableArrayList(data.getCities()));
    }

    @Override
    public void fillDialog() {
        city.setValue(currentUser.getCity());
        street.setText(currentUser.getStreet());
        house.setText(Integer.toString(currentUser.getHouse()));
        flat.setText(Integer.toString(currentUser.getFlat()));
    }

    @Override
    public boolean checkFields() {
        if (!Validator.checkForInt(house.getText())) {
            setMistake("Номер дома должен быть цифрой");
            return false;
        }
        if (!Validator.checkForInt(flat.getText())) {
            setMistake("Номер квартиры должен быть цифрой");
            return false;
        }
        return true;
    }

    public void changeAddress(ActionEvent event) throws IOException, SQLException {
        currentUser.setCity(city.getValue());
        currentUser.setStreet(street.getText());
        currentUser.setHouse(Integer.parseInt(house.getText()));
        currentUser.setFlat(Integer.parseInt(flat.getText()));
        data.getUsersManager().update(currentUser);
        setSuccess("Адрес обновлен");
        redirect(event, "profile/ProfileWindow");
    }
}
