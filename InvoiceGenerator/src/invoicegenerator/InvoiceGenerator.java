/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicegenerator;

import appgateway.AppGateway;
import domain.InvoiceRequest;
import domain.TableRow;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Teun
 */
public class InvoiceGenerator extends Application {

    final Button button = new Button("Send");
    final Label notification = new Label();
    final TextField subject = new TextField("0.00");
    final ComboBox cbPaymentMethod = new ComboBox();

    final TableView table = new TableView();
    final AppGateway gateway = new AppGateway("ClientToPSP", "PSPToClient");
    private final ObservableList<TableRow> data = FXCollections.observableArrayList();
    private final Map<String, TableRow> test = new HashMap();

    private InvoiceRequest SendInvoice() {
        InvoiceRequest request = new InvoiceRequest(Double.parseDouble(subject.getText()), "EUR", cbPaymentMethod.getValue().toString().toLowerCase());
        return request;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("InvoiceGenerator");
        Scene scene = new Scene(new Group(), 450, 250);

        table.setEditable(false);
        TableColumn requestCol = new TableColumn("Request");
        requestCol.setMinWidth(490);
        requestCol.setCellValueFactory(
                new PropertyValueFactory<>("request")
        );
        TableColumn replyCol = new TableColumn("Reply");
        replyCol.setMinWidth(490);
        replyCol.setCellValueFactory(
                new PropertyValueFactory<>("reply")
        );

        table.setItems(data);
        table.getColumns().addAll(requestCol, replyCol);

        cbPaymentMethod.getItems().addAll("PayPal", "IDeal", "Google Pay");
        cbPaymentMethod.setValue("PayPal");

        Pattern pattern = Pattern.compile("\\d+\\.\\d{2}");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        subject.setTextFormatter(formatter);
        button.setOnAction(e -> {
            InvoiceRequest request = SendInvoice();
            String messageId = gateway.newRequest(request);
            TableRow temp = new TableRow(request.toString(), "");
            test.put(messageId, temp);
            data.add(temp);
        });

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Payment Method: "), 0, 0);
        grid.add(cbPaymentMethod, 1, 0);
        grid.add(new Label("Price: "), 0, 1);
        grid.add(subject, 1, 1, 3, 1);
        grid.add(button, 4, 1);
        grid.add(notification, 1, 3, 3, 1);
        grid.add(table, 0, 5, 5, 1);

        gateway.onReplyArrived(data, test);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setHeight(550);
        primaryStage.setWidth(1000);
        primaryStage.resizableProperty().set(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
