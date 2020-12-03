package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.TemplatesData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;

public class TemplateController extends DialogController implements IFill, IInit {
    @FXML
    private TextField templateName;

    @FXML
    private TextArea templateDesc;

    private DishTemplate template;

    private TemplatesData data;

    @Override
    public void initialize() {
        data = TemplatesData.getInstance();
    }

    @Override
    public boolean checkFields() {
        if (!Validator.validateAllFields(templateName.getText(), templateDesc.getText())) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        boolean templateExists = data.checkIfTemplateExists(templateName.getText());
        if (template != null) {
            if (templateExists && !templateName.getText().equals(template.getName())) {
                setMistake("Шаблон с таким именем уже существует");
                return false;
            }
        } else {
            if (templateExists) {
                setMistake("Шаблон с таким именем уже существует");
                return false;
            }
        }
        return true;
    }

    @Override
    public void fillDialog() {
        templateName.setText(template.getName());
        templateDesc.setText(template.getDescription());
    }

    @Override
    public void initData(DatabaseEntity entity) {
        template = (DishTemplate) entity;
    }

    public void addTemplate() {
        try {
            data.addTemplateToModel(new DishTemplate(templateName.getText(), templateDesc.getText()));
            setSuccess("Шаблон успешно добавлен");
        } catch (SQLException e) {
            System.out.println("ERROR WHILE ADDING A TEMPLATE");
            e.printStackTrace();
        }
    }

    public void updateTemplate() {
        try {
            template.setName(templateName.getText());
            template.setDescription(templateDesc.getText());
            data.updateTemplateInModel(template);
            setSuccess("Шаблон успешно изменен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
