package ru.loghorrean.veganShop.util;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.exceptions.DatabaseException;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DialogCreator {
    public static class DialogBuilder {
        private final Dialog<ButtonType> dialog;
        private final FXMLLoader loader;
        private DialogController controller = null;
        private String methodOnSuccess = "";
        private ActionEvent event = null;

        public DialogBuilder(String path) {
            dialog = new Dialog<>();
            loader = new FXMLLoader(getClass().getResource("/ru/loghorrean/veganShop/views/dialogs/" + path + ".fxml"));
        }

        public DialogBuilder createDialog(String title, Pane pane) {
            dialog.setTitle(title);
            dialog.initOwner(pane.getScene().getWindow());
            try {
                dialog.getDialogPane().setContent(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public DialogBuilder addButtons(ButtonType ...buttons) {
            ObservableList<ButtonType> dialogButtons = dialog.getDialogPane().getButtonTypes();
            dialogButtons.addAll(Arrays.asList(buttons));
            return this;
        }

        public DialogBuilder addController() {
            controller = loader.getController();
            return this;
        }

        public DialogBuilder passObject(Object entity) {
            ((IInit) controller).initData(entity);
            return this;
        }

        public DialogBuilder fillDialog() {
            ((IFill) controller).fillDialog();
            return this;
        }

        public DialogBuilder addValidationToButton(ButtonType buttonType) {
            final Button button = (Button) dialog.getDialogPane().lookupButton(buttonType);
            button.addEventFilter(ActionEvent.ACTION, actionEvent -> {
                if (!controller.checkFields()) {
                    actionEvent.consume();
                }
                else {
                    dialogSuccess();
                }
            });
            return this;
        }

        public DialogBuilder onSuccess(String methodName) {
            this.methodOnSuccess = methodName;
            return this;
        }

        private void dialogSuccess() {
            try {
                if (event == null) {
                    Method method = controller.getClass().getMethod(this.methodOnSuccess);
                    method.invoke(controller);
                }
                else {
                    Method method = controller.getClass().getMethod(this.methodOnSuccess, ActionEvent.class);
                    method.invoke(controller, event);
                }
            } catch (NoSuchMethodException e) {
                System.out.println("NO SUCH METHOD IN " + controller.getClass() + ": " + methodOnSuccess);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("TRUING TO INVOKE PRIVATE METHOD " + methodOnSuccess);
                e.printStackTrace();
            }
        }

        public DialogBuilder redirectsFrom(ActionEvent event) {
            this.event = event;
            return this;
        }

        public Dialog<ButtonType> build() {
            return dialog;
        }
    }
}
