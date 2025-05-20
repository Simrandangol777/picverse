package com.picverse.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.ContactModel;

public class ContactService {

    public void saveContact(ContactModel contact) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String sql = "INSERT INTO contact (name, phone_number, email, subject, message) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, contact.getName());
                stmt.setLong(2, contact.getPhoneNumber());
                stmt.setString(3, contact.getEmail());
                stmt.setString(4, contact.getSubject());
                stmt.setString(5, contact.getMessage());
                stmt.executeUpdate();
            }
        }
    }
}
