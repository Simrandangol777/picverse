package com.picverse.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.picverse.config.DatabaseConfig;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

/**
 * Service class responsible for handling the business logic
 * of saving posts including image uploads and database insertion.
 */
public class CreateService {

    /**
     * Saves a post along with the uploaded image to the server and database.
     *
     * @param caption   The caption text submitted with the post.
     * @param filePart  The uploaded image file part.
     * @param userId    The ID of the user submitting the post.
     * @param context   The servlet context used to resolve the upload path.
     * @return boolean  True if post was saved successfully, false otherwise.
     * @throws IOException   If file upload fails.
     * @throws SQLException  If a database error occurs.
     * @throws ClassNotFoundException 
     */
    public boolean savePostWithImage(String caption, Part filePart, Integer userId, ServletContext context)
            throws IOException, SQLException, ClassNotFoundException {

        // Extract the original image file name
        String imageName = filePart.getSubmittedFileName();
        
     // To give a abosolute path so that image save in the upload image folder.
     		// Give absolute path like "C:Advance Java/picverse/src/main/webapp/uploads/images/" of the project in the blank area
//     		String uploadDir = "E:/20th/Advance Java/Restart/picverse/src/main/webapp/uploads/images/" + imageName;

        // Define the server path where the image will be saved
        String uploadDir = context.getRealPath("uploads/images/");
        String uploadPath = uploadDir + File.separator + imageName;

        // Ensure the upload directory exists
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Write the image to the server
        try (InputStream inputStream = filePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(uploadPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new IOException("File upload failed: " + e.getMessage(), e);
        }

        // Save post metadata into the database
        try (Connection connection = DatabaseConfig.getDbConnection()) {

            String query = "INSERT INTO post (caption, image, user_id) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, caption);
                stmt.setString(2, imageName);
                stmt.setInt(3, userId);

                int rows = stmt.executeUpdate();
                return rows > 0;
            }

        } catch (SQLException e) {
            throw new SQLException("Database insertion failed: " + e.getMessage(), e);
        }
    }
}
