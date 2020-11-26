package ru.loghorrean.veganShop.controllers.profileControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.controllers.dialogControllers.ChangePasswordController;
import ru.loghorrean.veganShop.models.database.entities.CityEntity;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.models.database.managers.CitiesManager;
import ru.loghorrean.veganShop.models.database.managers.UserManager;

import java.io.IOException;
import java.util.Optional;

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

    private UserManager userManager;

    private UserEntity currentUser;

    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        mainAnchorPane.getChildren().add(userMenu);
        citiesManager = CitiesManager.getInstance();
        userManager = UserManager.getInstance();
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
        CityEntity userCity = currentUser.getCity();
        city.setText(userCity == null ? "Не указан" : userCity.getName());
        street.setText(currentUser.getStreet());
        house.setText(Integer.toString(currentUser.getHouse()));
        flat.setText(Integer.toString(currentUser.getFlat()));
    }

    public void backToMenu(ActionEvent event) throws IOException {
        String role = CurrentUser.getInstance().getUser().getRole().getTitle();
        if (role.equals("Customer")) {
            redirect(event, "MenuWindow");
        }
        else if (role.equals("Admin")) {
            redirect(event, "admin/AdminMenuWindow");
        }
    }

    public void openConfirmPasswordDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());
        dialog.setTitle("Подвердите пароль");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/loghorrean/veganShop/views/dialogs/ChangePasswordConfirmDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChangePasswordController controller = loader.getController();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        final Button buttonOK = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        buttonOK.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            if (!controller.checkFields()) {
                actionEvent.consume();
            }
        });
        Optional<ButtonType> result = dialog.showAndWait();
    }

    private void openChangePasswordDialog() {

    }
}
