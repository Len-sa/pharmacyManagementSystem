package gui;

import database.crud.EmployeeCrud;
import entities.Address;
import entities.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeUpdateForm extends EmployeeDisplayForm {

    public EmployeeUpdateForm() {
        setTitle("Update Employee");
    }

    @Override
    public boolean isReadOAndWriteTextFields() {
        return true;
    }

    @Override
    public void addActionButton(JPanel buttonPanel, JLabel empIdLbl, JTextField firstNameField, JTextField lastNameField, JTextField cityField, JTextField subcityField, JTextField kebeleField){
        JButton updateBtn = new JButton("Update");
        buttonPanel.add(updateBtn);
        buttonPanel.setLayout(new GridLayout(7,2));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Address address = new Address(Integer.parseInt(kebeleField.getText()),subcityField.getText(),cityField.getText());
                 Employee employee = new Employee(firstNameField.getText(), null, address);
                 employee.setEmployeeLastName(lastNameField.getName());
                 employee.setEmployeeId(Integer.parseInt(empIdLbl.getText()));
                 boolean isUpdateSuccessful = EmployeeCrud.updateEmployee(employee);
                 if(isUpdateSuccessful) {
                     JOptionPane.showMessageDialog(null, "Employee information updated", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                 } else {
                     JOptionPane.showMessageDialog(null, "Employee information not updated. Please try again!", "Operation failed", JOptionPane.ERROR_MESSAGE);
                 }
            }
        });
    }
}

