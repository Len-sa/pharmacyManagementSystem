package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageCustomer extends JFrame {
    public ManageCustomer(){
        setTitle("OUR CUSTOMERS ");
        setVisible(true);
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3,2,10,10));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(200,250);
        mainPanel.setLayout(new GridLayout(2,2,10,10));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JButton addCustomer = new JButton("ADD ");
        JButton deleteCustomer = new JButton("DELETE ");
        JButton updateCustomer = new JButton("UPDATE");
        JButton displayCustomer = new JButton("DISPLAY");

        addCustomer.setBackground(Color.orange);
        addCustomer.setOpaque(true);
        deleteCustomer.setBackground(Color.orange);
        deleteCustomer.setOpaque(true);
        updateCustomer.setBackground(Color.orange);
        updateCustomer.setOpaque(true);
        displayCustomer.setBackground(Color.orange);
        displayCustomer.setOpaque(true);

        mainPanel.add(addCustomer);
        mainPanel.add(deleteCustomer);
        mainPanel.add(updateCustomer);
        mainPanel.add(displayCustomer);

        add(mainPanel);

        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerRegistrationForm();
            }
        });
        deleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerDeleteForm();

            }
        });
        updateCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new CustomerUpdateForm();
            }
        });
        displayCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerDisplayForm();

            }
        });


    }
}
