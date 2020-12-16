package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.CustomDishesData;
import ru.loghorrean.veganShop.models.ProductsInCustomDishData;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.util.Optional;

public class CustomDishListController extends ClientController {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ListView<CustomDish> mainListView;

    @FXML
    private AnchorPane addInfoPane;

    @FXML
    private Label dishName;

    @FXML
    private Button addInfoButton;

    @FXML
    private Button cartButton;

    @Override
    public void initialize() {
        CustomDishesData.getInstance();
        ProductsInCustomDishData.getInstance();
        addInfoPane.setVisible(false);
        Accordion userMenu = getUserMenu();
        AnchorPane.setRightAnchor(userMenu, 10.0);
        AnchorPane.setTopAnchor(userMenu, 10.0);
        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> {
            try {
                redirect(event, "dishesScreens/AddCustomToOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setBottomAnchor(backButton, 10.0);
        mainAnchorPane.getChildren().addAll(userMenu, backButton);

        ContextMenu dishMenu = new ContextMenu();

        MenuItem openInfo = new MenuItem("Информация");
        openInfo.setOnAction(event -> {
            CustomDish dish = mainListView.getSelectionModel().getSelectedItem();
            openInfoDialog(event, dish);
        });

        dishMenu.getItems().add(openInfo);

        mainListView.setItems(FXCollections.observableArrayList(CurrentUser.getInstance().getUser().getCustomDishesByUser()));

        mainListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<CustomDish> call(ListView<CustomDish> customDishListView) {
                ListCell<CustomDish> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(CustomDish dish, boolean empty) {
                        super.updateItem(dish, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(dish.getName());
                        }
                    }
                };
                return cell;
            }
        });
        mainListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                addInfoPane.setVisible(true);
                dishName.setText(newValue.getName());
                addInfoButton.setOnAction(event -> {
                    try {
                        redirectWithSmth(event, "dishesScreens/CustomDishComposition", newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                cartButton.setOnAction(event -> {
                    addCustomToCart(event, newValue);
                });
            }
        });
    }

    private void openInfoDialog(ActionEvent event, CustomDish dish) {
        try {
            redirectWithSmth(event, "dishesScreens/CustomDishComposition", dish);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCustomToCart(ActionEvent event, CustomDish dish) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("CustomToOrderDialog")
                            .createDialog("Добавьте блюдо в заказ", mainAnchorPane)
                            .addButtons(ButtonType.OK, ButtonType.CANCEL)
                            .addController()
                            .passObject(dish)
                            .fillDialog()
                            .addValidationToButton(ButtonType.OK)
                            .onSuccess("addCustomToCart")
                            .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                redirect(event, "dishesScreens/AddCustomToOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
