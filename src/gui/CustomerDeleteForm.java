package gui;

import database.crud.CustomerCrud;
import database.crud.EmployeeCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDeleteForm extends CustomerDisplayForm {
    public CustomerDeleteForm() {
        setTitle("Delete Customer");
    }

    @Override
    public void addActionButton(JPanel buttonPanel, JLabel custIdLbl, JTextField firstNameField, JTextField lastNameField,JTextField jlDOB, JTextField cityField, JTextField subcityField, JTextField kebeleField){
        JButton deleteBtn = new JButton("Delete");
        buttonPanel.add(deleteBtn);
        buttonPanel.setLayout(new GridLayout(9,2));
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isDeleteSuccessful = CustomerCrud.deleteCustomer (Integer.parseInt(custIdLbl.getText()));
                if(isDeleteSuccessful) {
                    JOptionPane.showMessageDialog(null, "Customer information deleted!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Customer information not deleted. Please try again!", "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}


