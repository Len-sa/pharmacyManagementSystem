package gui;

import database.crud.CustomerCrud;
import entities.Address;
import entities.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRegistrationForm extends JFrame {

    private JTextField firstNameField, middleNameField, lastNameField, dobField, cityField, subcityField, kebeleField;
    private JButton submitBtn, backBtn;

    public CustomerRegistrationForm() {
        setTitle("Customer Registration");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setting padding using a Border
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Padding around the form
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Header Label
        JLabel headerLabel = new JLabel("Customer Registration Form", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Form Fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        JLabel middleNameLabel = new JLabel("Middle Name:");
        middleNameField = new JTextField(20);
        formPanel.add(middleNameLabel);
        formPanel.add(middleNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        dobField = new JTextField(20);
        formPanel.add(dobLabel);
        formPanel.add(dobField);

        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField(20);
        formPanel.add(cityLabel);
        formPanel.add(cityField);

        JLabel subcityLabel = new JLabel("Subcity:");
        subcityField = new JTextField(20);
        formPanel.add(subcityLabel);
        formPanel.add(subcityField);

        JLabel kebeleLabel = new JLabel("Kebele:");
        kebeleField = new JTextField(20);
        formPanel.add(kebeleLabel);
        formPanel.add(kebeleField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons panel with color, spacing, and design
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(120, 30));
        submitBtn.setBackground(new Color(70, 130, 180));  // Steel blue background
        //submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));

        backBtn = new JButton("Back to Menu");
        backBtn.setPreferredSize(new Dimension(120, 30));
        backBtn.setBackground(new Color(255, 69, 0));  // Red-Orange background
        //backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(submitBtn);
        buttonPanel.add(backBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        // Button action listener for submit
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                String dob = dobField.getText();
                String city = cityField.getText();
                String subcity = subcityField.getText();

                // add kebele validation before converting to int
                int kebele = Integer.parseInt(kebeleField.getText());
                Address address = new Address(kebele, city, subcity);
                Customer customer = new Customer(firstName,middleName,dob,address);

                CustomerCrud.addCustomer(customer);

                // Business logic for saving customer registration data goes here
                JOptionPane.showMessageDialog(null, "Customer Registered!");
            }
        });

        // Button action listener for going back to the menu
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the current window
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerRegistrationForm();
    }
}
