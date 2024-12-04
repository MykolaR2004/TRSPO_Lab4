package org.example.trspo_lab4.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.trspo_lab4.DAO.ClientDAO;
import org.example.trspo_lab4.model.Client;

import java.io.IOException;
import java.net.URL;

public class ClientsController {



    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, Number> idColumn;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> surnameColumn;

    private final ObservableList<Client> clients = FXCollections.observableArrayList();

    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        clientsTable.setItems(clients);
        loadClients();
        addButtonToTable();
    }

    private void loadClients() {
        try {
            ClientDAO clientDAO = new ClientDAO(DatabaseConnection.getConnection());
            clients.setAll(clientDAO.getAllClients());
        } catch (Exception e) {
            throw new RuntimeException("Error loading Clients", e);
        }
    }

    @FXML
    private TableColumn<Client, Void> actionsColumn;

    private void addButtonToTable() {
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("View Meetings");

            {
                btn.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    URL resource = getClass().getResource("/org/example/trspo_lab4/meetings_view.fxml");
                    if (resource == null) {
                        System.err.println("meetings_view.fxml not found");
                    } else {
                        System.out.println(resource);
                    }

                    showMeetings(client);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        actionsColumn.setCellFactory(cellFactory);
    }

    private void showMeetings(Client client) {
        try {
            URL resource = getClass().getResource("/org/example/trspo_lab4/meetings_view.fxml");
            if (resource == null) {
                throw new IOException("Resource meetings_view.fxml not found!");
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();


            MeetingsController controller = loader.getController();
            controller.setCurrentClient(client);

            Stage stage = new Stage();
            stage.setTitle("Meetings for " + client.getName());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load meetings view", e);
        }
    }


}





