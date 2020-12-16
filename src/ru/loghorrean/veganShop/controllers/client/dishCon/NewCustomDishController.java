package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.loghorrean.veganShop.CurrentCustomDish;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.CustomDishesData;
import ru.loghorrean.veganShop.models.ProductsInCustomDishData;
import ru.loghorrean.veganShop.models.TemplatesData;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.entities.ProductInCustomDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class NewCustomDishController extends ClientController implements IInit {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField dishName;

    @FXML
    private ComboBox<DishTemplate> templateBox;

    @FXML
    private TextArea dishRecipe;

    @FXML
    private Button addProduct;

    @FXML
    private Button endButton;

    @FXML
    private ListView<CurrentCustomDish.CompositionRow> prodList;

    private CurrentCustomDish customDish;

    @Override
    public void initData(Object object) {
        customDish = (CurrentCustomDish) object;
    }

    @Override
    public void initialize() {
        templateBox.setItems(FXCollections.observableArrayList(TemplatesData.getInstance().getTemplates()));
        templateBox.getSelectionModel().selectFirst();
        templateBox.valueProperty().addListener((observableValue, oldValue, newValue) -> customDish.setTemplate(newValue));
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(dishName.textProperty(), dishRecipe.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (dishName.getText().isEmpty() || dishRecipe.getText().isEmpty());
            }
        };
        endButton.disableProperty().bind(bb);
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        mainAnchorPane.getChildren().add(userMenu);
    }

    @FXML
    protected void addNewProduct(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("ProductsToCustomDialog")
                        .createDialog("Добавьте продукт", mainAnchorPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .passObject(customDish)
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("addProductToDish")
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            prodList.setItems(FXCollections.observableArrayList(customDish.getComposition()));
        }
    }

    @FXML
    protected void makeCustomDish(ActionEvent event) {
        try {
            customDish.setDishName(dishName.getText());
            customDish.setRecipe(dishRecipe.getText());
            CustomDish newDish = new CustomDish (
                    customDish.getTemplate(),
                    customDish.getDishName(),
                    customDish.getRecipe(),
                    CurrentUser.getInstance().getUser()
            );
            CustomDishesData.getInstance().addDish(newDish);
            for (CurrentCustomDish.CompositionRow row: customDish.getComposition()) {
                ProductInCustomDish link = new ProductInCustomDish(
                        row.getProduct(),
                        newDish,
                        row.getAmount(),
                        row.getRecipe()
                );
                ProductsInCustomDishData.getInstance().addLinkToModel(link);
            }
            setSuccess("Блюдо создано и добавлено в 'Ваши блюда'");
            redirect(event, "dishesScreens/AddCustomToOrder");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void backToPrevScene(ActionEvent event) {
        try {
            redirect(event, "dishesScreens/AddCustomToOrder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
