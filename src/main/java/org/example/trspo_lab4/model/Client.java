package org.example.trspo_lab4.model;

import javafx.beans.property.*;

public class Client {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final StringProperty aboutYourself = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty sex = new SimpleStringProperty();
    private final StringProperty birthdate = new SimpleStringProperty();
    private final StringProperty hobby = new SimpleStringProperty();
    private final StringProperty requirement = new SimpleStringProperty();

    public Client() {
    }

    public Client(int id, String name, String surname, String aboutYourself, int age, String sex, String birthdate, String hobby, String requirement) {
        this.id.set(id);
        this.name.set(name);
        this.surname.set(surname);
        this.aboutYourself.set(aboutYourself);
        this.age.set(age);
        this.sex.set(sex);
        this.birthdate.set(birthdate);
        this.hobby.set(hobby);
        this.requirement.set(requirement);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public String getAboutYourself() {
        return aboutYourself.get();
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself.set(aboutYourself);
    }

    public StringProperty aboutYourselfProperty() {
        return aboutYourself;
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getSex() {
        return sex.get();
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

    public String getHobby() {
        return hobby.get();
    }

    public void setHobby(String hobby) {
        this.hobby.set(hobby);
    }

    public StringProperty hobbyProperty() {
        return hobby;
    }

    public String getRequirement() {
        return requirement.get();
    }

    public void setRequirement(String requirement) {
        this.requirement.set(requirement);
    }

    public StringProperty requirementProperty() {
        return requirement;
    }
}


