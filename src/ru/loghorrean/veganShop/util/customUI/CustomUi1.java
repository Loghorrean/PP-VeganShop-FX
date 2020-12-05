package ru.loghorrean.veganShop.util.customUI;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;

public class CustomUi1 {
    private Label templateName;
    private CheckBox isEnabled;

    public CustomUi1(DishTemplate template, boolean isEnabled) {
        this.templateName = new Label(template.getName());
        this.isEnabled = new CheckBox();
        this.isEnabled.setSelected(isEnabled);
    }

    public Label getTemplateName() {
        return templateName;
    }

    public CheckBox getIsEnabled() {
        return isEnabled;
    }

    public void setTemplateName(String templateName) {
        this.templateName.setText(templateName);
    }

    public void setEnabled() {

    }

    @Override
    public String toString() {
        return "CustomUi1{" +
                "templateName=" + templateName +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
