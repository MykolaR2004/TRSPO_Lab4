package org.example.trspo_lab4.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.example.trspo_lab4.DAO.MeetingDAO;
import org.example.trspo_lab4.model.Client;
import org.example.trspo_lab4.model.Meeting;
import org.example.trspo_lab4.model.MeetingView;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MeetingsController {

    @FXML
    public TableColumn deleteColumn;
    @FXML
    public TableColumn editDateColumn;
    @FXML
    private TableView<MeetingView> meetingsTable;

    @FXML
    private TableColumn<MeetingView, Integer> idColumn;
    @FXML
    private TableColumn<MeetingView, String> client1Column;
    @FXML
    private TableColumn<MeetingView, String> client2Column;
    @FXML
    private TableColumn<MeetingView, String> dateColumn;
    @FXML
    private TableColumn<MeetingView, String> countryColumn;

    @FXML
    private TextField client1Field;
    @FXML
    private TextField client2Field;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField countryField;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private final ObservableList<MeetingView> meetingOutputs = FXCollections.observableArrayList();


    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        client1Column.setCellValueFactory(cellData -> cellData.getValue().client1Property());
        client2Column.setCellValueFactory(cellData -> cellData.getValue().client2Property());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        meetingsTable.setItems(meetingOutputs);
        addDeleteButtonToTable();
        addEditButtonToTable();
    }

    private Client currentClient;

    public void setCurrentClient(Client client) {
        this.currentClient = client;
        loadMeetingsForClient(client);
    }

    public void loadMeetingsForClient(Client client) {
        meetingOutputs.clear();
        try {
            MeetingDAO meetingDAO = new MeetingDAO(DatabaseConnection.getConnection());
            meetingOutputs.addAll(meetingDAO.getMeetingsWithClientNames(client.getId()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load meetings for client: " + client.getId(), e);
        }
    }

    @FXML
    private void addMeeting() {
        try {
            if (currentClient == null) {
                System.err.println("Current client is not set.");
                return;
            }

            int client1Id = currentClient.getId();
            int client2Id = Integer.parseInt(client2Field.getText());
            LocalDate meetingDate = dateField.getValue();
            String country = countryField.getText();

            Meeting newMeeting = new Meeting(0, client1Id, client2Id, meetingDate.toString(), country);

            MeetingDAO meetingDAO = new MeetingDAO(DatabaseConnection.getConnection());
            meetingDAO.addMeeting(newMeeting);

            loadMeetingsForClient(currentClient);

            client2Field.clear();
            dateField.setValue(null);
            countryField.clear();

            System.out.println("Meeting added successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to add meeting: " + currentClient.getId(), e);
        }
    }

    private void addEditButtonToTable() {
        Callback<TableColumn<MeetingView, Void>, TableCell<MeetingView, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("Edit Date");

            {
                btn.setOnAction(event -> {
                    MeetingView meeting = getTableView().getItems().get(getIndex());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    DatePicker datePicker = new DatePicker();
                    try {
                        datePicker.setValue(LocalDate.parse(meeting.getDate(), formatter));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse date.");
                    }
                    datePicker.setOnAction(dpEvent -> {
                        LocalDate newDate = datePicker.getValue();
                        if (newDate != null) {
                            String formattedDate = newDate.atStartOfDay().format(formatter);
                            updateMeetingDate(meeting, formattedDate);
                            getTableView().refresh();
                        }
                    });
                    setGraphic(datePicker);
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

        editDateColumn.setCellFactory(cellFactory);
    }


    private void updateMeetingDate(MeetingView meeting, String newDate) {
        try {
            MeetingDAO meetingDAO = new MeetingDAO(DatabaseConnection.getConnection());
            meetingDAO.updateMeetingDate(meeting.getId(), newDate);
            meeting.setDate(newDate);

            System.out.println("Meeting date updated successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to update meeting date.");
        }
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<MeetingView, Void>, TableCell<MeetingView, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("Delete");

            {
                btn.setOnAction(event -> {
                    MeetingView selectedMeeting = getTableView().getItems().get(getIndex());
                    deleteMeetingFromTable(selectedMeeting);
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
        deleteColumn.setCellFactory(cellFactory);
    }

    private void deleteMeetingFromTable(MeetingView meetingView) {
        if (meetingView == null) return;

        try {
            MeetingDAO meetingDAO = new MeetingDAO(DatabaseConnection.getConnection());
            meetingDAO.deleteMeetingById(meetingView.getId());
            meetingOutputs.remove(meetingView);
            System.out.println("Meeting deleted successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete meeting.");
        }
    }

}
