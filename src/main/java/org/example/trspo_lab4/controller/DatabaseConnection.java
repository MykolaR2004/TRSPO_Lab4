package org.example.trspo_lab4.controller;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/dating_agency_new";
    private static final String USER = "student";
    private static final String PASSWORD = "1234";

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

