package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.picverse.model.ContactModel;
import com.picverse.service.ContactService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/contact.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	String name = request.getParameter("name");
            Long phoneNumber = Long.parseLong(request.getParameter("phone"));
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");

            ContactModel contact = new ContactModel();
            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contact.setSubject(subject);
            contact.setMessage(message);

            ContactService service = new ContactService();
            service.saveContact(contact);
            System.out.println("Contact information saved successfully.");
            response.sendRedirect("contact");
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
        }
    }
}
