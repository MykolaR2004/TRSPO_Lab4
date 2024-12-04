package org.example.trspo_lab4.model;

import javafx.beans.property.*;

public class MeetingView {
    private final IntegerProperty id;
    private final StringProperty client1;
    private final IntegerProperty client1Id;
    private final StringProperty client2;
    private final IntegerProperty client2Id;
    private final StringProperty date;
    private final StringProperty country;

    public MeetingView(int id, String client1, int client1Id, String client2, int client2Id, String date, String country) {
        this.id = new SimpleIntegerProperty(id);
        this.client1 = new SimpleStringProperty(client1);
        this.client1Id = new SimpleIntegerProperty(client1Id);
        this.client2 = new SimpleStringProperty(client2);
        this.client2Id = new SimpleIntegerProperty(client2Id);
        this.date = new SimpleStringProperty(date);
        this.country = new SimpleStringProperty(country);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getClient1() {
        return client1.get();
    }

    public StringProperty client1Property() {
        return client1;
    }

    public int getClient1Id() {
        return client1Id.get();
    }

    public IntegerProperty client1IdProperty() {
        return client1Id;
    }

    public String getClient2() {
        return client2.get();
    }

    public StringProperty client2Property() {
        return client2;
    }

    public int getClient2Id() {
        return client2Id.get();
    }

    public IntegerProperty client2IdProperty() {
        return client2Id;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }


    public StringProperty dateProperty() {
        return date;
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }
}
