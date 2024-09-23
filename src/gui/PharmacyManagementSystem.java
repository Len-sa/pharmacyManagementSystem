package gui;

import database.crud.PharmacyCrud;
import entities.Pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PharmacyManagementSystem extends JFrame {

    public PharmacyManagementSystem() {
        setTitle("Pharmacy Management System");
        setVisible(true);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Get pharmacy detail and Write welcome message with the pharmacy name
        Pharmacy pharmacy = PharmacyCrud.getPharmacyDetail();
        String welcomeMessage = "No Pharmacy Registered!";
        if(pharmacy != null) {
            welcomeMessage = pharmacy.getPharmacyName();
        }
        JLabel welcomeLabel = new JLabel(welcomeMessage, JLabel.CENTER);
        welcomeLabel.setForeground(Color.blue);
        welcomeLabel.setFont(new Font("ITALIC", Font.PLAIN, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Menu options
        JPanel btnPanel = new JPanel();
        btnPanel.setSize(500,500);
        btnPanel.setLayout(new GridLayout(2,2,10,10));
        JButton pharmacyBtn = new JButton("PHARMACY");
        JButton employeeBtn = new JButton("EMPLOYEE");
        JButton medicineBtn = new JButton("MEDICINE");
        JButton customerBtn = new JButton("CUSTOMER");
        JButton reportBtn = new JButton("REPORT");

        // Customizing button size and centering them
        pharmacyBtn.setPreferredSize(new Dimension(400, 50));
        pharmacyBtn.setBackground(Color.orange);
        pharmacyBtn.setOpaque(true);
        customerBtn.setPreferredSize(new Dimension(400, 100));
        customerBtn.setBackground(Color.cyan);
        customerBtn.setOpaque(true);
        medicineBtn.setPreferredSize(new Dimension(400, 100));
        medicineBtn.setBackground(Color.green);
        medicineBtn.setOpaque(true);
        reportBtn.setPreferredSize(new Dimension(400, 100));
        reportBtn.setBackground(Color.pink);
        reportBtn.setOpaque(true);

        btnPanel.add(pharmacyBtn);
        btnPanel.add(employeeBtn);
        btnPanel.add(customerBtn);
        btnPanel.add(medicineBtn);
        btnPanel.add(reportBtn);

        add(btnPanel, BorderLayout.AFTER_LINE_ENDS);
        pack();

        // The pack() method automatically sizes the window to fit the preferred sizes of its contained components.


        // Action listeners for each button
        pharmacyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManagePharmacy();
            }
        });

        customerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageCustomer();
            }
        });

        employeeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageEmployee();
            }
        });

        medicineBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageMedicine();
            }
        });

        reportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReportGenerator();
            }
        });
        pack();
    }

    public static void main(String[] args) {
        new PharmacyManagementSystem();
    }
}
