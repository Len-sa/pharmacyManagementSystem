package gui;

import database.crud.EmployeeCrud;
import entities.Address;
import entities.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeRegistrationForm extends JFrame{

    public EmployeeRegistrationForm() {
        setTitle("Employee Registration");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setting padding using a Border
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Padding around the form
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Header Label
        JLabel headerLabel = new JLabel("Employee Registration Form", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Form Fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(20);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        JLabel middleNameLabel = new JLabel("Middle Name:");
        JTextField middleNameField = new JTextField(20);
        formPanel.add(middleNameLabel);
        formPanel.add(middleNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(20);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);


        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(20);
        formPanel.add(cityLabel);
        formPanel.add(cityField);

        JLabel subcityLabel = new JLabel("Subcity:");
        JTextField subcityField = new JTextField(20);
        formPanel.add(subcityLabel);
        formPanel.add(subcityField);

        JLabel kebeleLabel = new JLabel("Kebele:");
        JTextField kebeleField = new JTextField(20);
        formPanel.add(kebeleLabel);
        formPanel.add(kebeleField);

        JLabel salaryLabel = new JLabel("Salary");
        JTextField salaryField = new JTextField(10);
        formPanel.add(salaryLabel);
        formPanel.add(salaryField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons panel with color, spacing, and design
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton backBtn = new JButton("Back to Menu");
        backBtn.setPreferredSize(new Dimension(120, 30));
        backBtn.setBackground(new Color(255, 69, 0));  // Red-Orange background
        //backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(backBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        addActionButton(buttonPanel, firstNameField, middleNameField, lastNameField, cityField, subcityField, salaryField, kebeleField);

        // Button action listener for going back to the menu
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the current window
            }
        });

        setVisible(true);
    }

    public void addActionButton(JPanel buttonPanel, JTextField firstNameField, JTextField middleNameField,
                                JTextField lastNameField, JTextField cityField, JTextField subcityField,
                                JTextField salaryField, JTextField kebeleField) {
        JButton submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(120, 30));
        submitBtn.setBackground(new Color(70, 130, 180));  // Steel blue background
        //submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));


        buttonPanel.add(submitBtn);
        // Button action listener for submit
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                String city = cityField.getText();
                String subcity = subcityField.getText();
                double salary = Double.parseDouble(salaryField.getText());

                // add kebele validation before converting to int
                int kebele = Integer.parseInt(kebeleField.getText());
                Address address = new Address(kebele, city, subcity);
                Employee employee = new Employee(firstName,middleName,address,salary);

                if(EmployeeCrud.addEmployee(employee)) {
                    JOptionPane.showMessageDialog(null, "Employee Registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null, "Employee Registration failed", "Dailed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}

