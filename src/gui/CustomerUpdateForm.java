package gui;

import database.crud.CustomerCrud;
import entities.Address;
import entities.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUpdateForm extends CustomerDisplayForm {
    public CustomerUpdateForm() {
        setTitle("Update Customer");
    }

    @Override
    public boolean isReadOAndWriteTextFields() {
        return true;
    }

    @Override
    public void addActionButton(JPanel buttonPanel, JLabel custIdLbl, JTextField firstNameField, JTextField lastNameField,JTextField txtDOB,JTextField cityField, JTextField subcityField, JTextField kebeleField){
        JButton updateBtn = new JButton("Update");
        buttonPanel.add(updateBtn);
        buttonPanel.setLayout(new GridLayout(9,2));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Address address = new Address(Integer.parseInt(kebeleField.getText()),subcityField.getText(),cityField.getText());
                Customer customer = new Customer(firstNameField.getText(), null,txtDOB.getText(), address);
                customer.setCustomerLastName (lastNameField.getName());
                customer.setCustomerId(Integer.parseInt(custIdLbl.getText()));
                boolean isUpdateSuccessful = CustomerCrud.updateCustomer(customer);
                if(isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(null, "customer information updated", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "customer information not updated. Please try again!", "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
