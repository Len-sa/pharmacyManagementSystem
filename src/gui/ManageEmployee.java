package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageEmployee extends JFrame {
    public ManageEmployee() {
        setTitle("OUR EMPLOYEE ");
        setVisible(true);
        setSize(400, 450);
        setLayout(new GridLayout(3,2,10,10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(200, 250);
        mainPanel.setLayout(new GridLayout(2, 2, 10, 10));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JButton addEmployee = new JButton("ADD ");
        JButton deleteEmployee = new JButton("DELETE ");
        JButton updateEmployee = new JButton("UPDATE");
        JButton displayEmployee = new JButton("DISPLAY");

        addEmployee.setBackground(Color.orange);
        addEmployee.setOpaque(true);
        deleteEmployee.setBackground(Color.orange);
        deleteEmployee.setOpaque(true);
        updateEmployee.setBackground(Color.orange);
        updateEmployee.setOpaque(true);
        displayEmployee.setBackground(Color.orange);
        displayEmployee.setOpaque(true);

        mainPanel.add(addEmployee);
        mainPanel.add(deleteEmployee);
        mainPanel.add(updateEmployee);
        mainPanel.add(displayEmployee);

        add(mainPanel);

        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeRegistrationForm();
            }
        });
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new EmployeeDeleteForm();

            }
        });
        updateEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeUpdateForm();
            }
        });
       displayEmployee.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new EmployeeDisplayForm();
           }
       });

    }
}
