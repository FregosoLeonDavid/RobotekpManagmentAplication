/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author David Fregoso
 */
public class ConnectionDB {
    protected Connection connectionMariaDB;
    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private final String DB_URL = "jdbc:mariadb://localhost:3306/robotekpdb";
    
    private final String USER = "root";
    private final String PASS = "projectsd02";
    
    public void startConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            connectionMariaDB = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexión a la base de datos realizada con éxito.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
            throw new SQLException("No se pudo establecer una conexión a la base de datos.");
        }
    }
    
    public Connection getConnection() {
        return connectionMariaDB;
    }
    
    public void closeConnection() {
        try {
            if (connectionMariaDB != null) {
                connectionMariaDB.close();
                System.out.println("Conexión a la base de datos cerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión a la base de datos: " + ex.getMessage());
        }
    }
}

