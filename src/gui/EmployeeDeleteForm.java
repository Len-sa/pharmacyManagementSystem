package gui;

import database.crud.EmployeeCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDeleteForm extends EmployeeDisplayForm {

    public EmployeeDeleteForm() {
        setTitle("Delete Employee");
    }

    @Override
    public void addActionButton(JPanel buttonPanel, JLabel empIdLbl, JTextField firstNameField, JTextField lastNameField, JTextField cityField, JTextField subcityField, JTextField kebeleField){
        JButton deleteBtn = new JButton("Delete");
        buttonPanel.add(deleteBtn);
        buttonPanel.setLayout(new GridLayout(7,2));
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isDeleteSuccessful = EmployeeCrud.deleteEmployee(Integer.parseInt(empIdLbl.getText()));
                if(isDeleteSuccessful) {
                    JOptionPane.showMessageDialog(null, "Employee information deleted!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Employee information not deleted. Please try again!", "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
