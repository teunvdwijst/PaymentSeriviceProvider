/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicegenerator;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Teun
 */
public class InvoiceGenerator extends Application {

    final Button button = new Button("Send");
    final Label notification = new Label();
    final TextField subject = new TextField("");

    private void SendInvoice() {
        // TO DO implement JMS sending of an invoice
    }

    @Override
    public void start(Stage primaryStage) {
        Pattern pattern = Pattern.compile("\\d+|\\d+\\,\\d{1,2}");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        subject.setTextFormatter(formatter);
        button.setOnAction(e -> {
            SendInvoice();
        });
        primaryStage.setTitle("InvoiceGenerator");
        Scene scene = new Scene(new Group(), 450, 250);

        final ComboBox cbPaymentMethod = new ComboBox();
        cbPaymentMethod.getItems().addAll(
                "IDeal",
                "PayPal",
                "Google Pay"
        );

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Payment Method: "), 0, 0);
        grid.add(cbPaymentMethod, 1, 0);
        grid.add(new Label("Price: "), 0, 1);
        grid.add(subject, 1, 1, 3, 1);
        grid.add(button, 0, 3);
        grid.add(notification, 1, 3, 3, 1);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setHeight(150);
        primaryStage.setWidth(350);
        primaryStage.resizableProperty().set(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
