package ru.loghorrean.veganShop.controllers.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.models.database.entities.City;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.models.database.managers.CitiesManager;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;

public class ProfileController extends UserController {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label username;

    @FXML
    private Label email;

    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label phone;

    @FXML
    private Label city;

    @FXML
    private Label street;

    @FXML
    private Label house;

    @FXML
    private Label flat;

    @FXML
    private Button changeMainInfo;

    @FXML
    private Button changeAddress;

    @FXML
    private Button back;

    @FXML
    private Button changePassword;

    private CitiesManager citiesManager;

    private UsersManager userManager;

    private User currentUser;

    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        mainAnchorPane.getChildren().add(userMenu);
        currentUser = CurrentUser.getInstance().getUser();
        fillProfileInfo();
        fillAddressInfo();
    }

    private void fillProfileInfo() {
        username.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        firstname.setText(currentUser.getFirstName());
        lastname.setText(currentUser.getLastName());
        phone.setText(currentUser.getPhone());
    }

    private void fillAddressInfo() {
        City userCity = currentUser.getCity();
        city.setText(userCity == null ? "Не указан" : userCity.getName());
        street.setText(currentUser.getStreet());
        house.setText(Integer.toString(currentUser.getHouse()));
        flat.setText(Integer.toString(currentUser.getFlat()));
    }

    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        String role = CurrentUser.getInstance().getUser().getRole().getTitle();
        if (role.equals("Customer")) {
            redirect(event, "client/orderScreens/Menu");
        }
        else if (role.equals("Admin")) {
            redirect(event, "admin/AdminMenu");
        }
    }

    @FXML
    public void openMainInfoDialog(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("ChangeMainInfoDialog")
                        .createDialog("Информация о себе", mainAnchorPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .fillDialog()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("changeMainInfo")
                        .redirectsFrom(event)
                        .build();
        dialog.showAndWait();
    }

    @FXML
    public void openAddressDialog(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("ChangeAddressDialog")
                        .createDialog("Адрес доставки", mainAnchorPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .fillDialog()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("changeAddress")
                        .redirectsFrom(event)
                        .build();
        dialog.showAndWait();
    }

    @FXML
    public void openConfirmPasswordDialog() {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("ChangePasswordDialog")
                        .createDialog("Подвердите пароль", mainAnchorPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("changePassword")
                        .build();
        dialog.showAndWait();
    }
}
