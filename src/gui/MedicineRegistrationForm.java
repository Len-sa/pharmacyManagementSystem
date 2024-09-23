package gui;


import database.crud.MedicineCrud;
import entities.Medicine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicineRegistrationForm extends JFrame {
    public MedicineRegistrationForm() {
        setTitle("Medicine Registration");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(400,500);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new GridLayout(10, 2, 10, 10));


        JLabel medNameJl = new JLabel("Medicine Name: ");
        JTextField medNameJtxt = new JTextField(10);

        JLabel brandJl = new JLabel("Brand: ");
        JTextField brandJtxt = new JTextField(10);

        JLabel manufacturedDateJl = new JLabel("Manufactured Date (YYYY/mm/dd): ");
        JTextField manufacturedDateJtxt = new JTextField(10);

        JLabel expiryDateJl = new JLabel("Expiry Date (YYYY/mm/dd): ");
        JTextField expiryDateJtxt = new JTextField(10);

        JLabel sellingPriceJl = new JLabel("Selling Price: ");
        JTextField sellingPriceJtxt = new JTextField(10);

        JLabel stockQuantityJl = new JLabel("Stock quantity: ");
        JTextField stockQuantityJtxt = new JTextField(10);


        mainPanel.add(medNameJl);
        mainPanel.add(medNameJtxt);
        mainPanel.add(brandJl);
        mainPanel.add(brandJtxt);
        mainPanel.add(manufacturedDateJl);
        mainPanel.add(manufacturedDateJtxt);
        mainPanel.add(expiryDateJl);
        mainPanel.add(expiryDateJtxt);
        mainPanel.add(sellingPriceJl);
        mainPanel.add(sellingPriceJtxt);
        mainPanel.add(stockQuantityJl);
        mainPanel.add(stockQuantityJtxt);



        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton backBtn = new JButton("Back to Menu");
        backBtn.setPreferredSize(new Dimension(120, 30));
        backBtn.setBackground(new Color(255, 69, 0));  // Red-Orange background
        //backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));

        //buttonPanel.add(backBtn);
        mainPanel.add(backBtn);
        add(mainPanel,BorderLayout.CENTER);


        addActionButton(mainPanel,medNameJtxt, brandJtxt, manufacturedDateJtxt, expiryDateJtxt, sellingPriceJtxt, stockQuantityJtxt);

        // Button action listener for going back to the menu
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the current window
            }
        });

        setVisible(true);
    }

    public void addActionButton(JPanel mainPanel, JTextField medNameJtxt, JTextField brandJtxt,
                                JTextField manufacturedDateJtxt, JTextField expiryDateJtxt, JTextField sellingPriceJtxt,
                                JTextField stockQuantityJtxt) {
        JButton submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(120, 30));
        submitBtn.setBackground(new Color(70, 130, 180));  // Steel blue background
        //submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));


        mainPanel.add(submitBtn);
        // Button action listener for submit
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String medName = medNameJtxt.getText();
                String brand = brandJtxt.getText();
                String manufacturedDate = manufacturedDateJtxt.getText();
                String expiryDate = expiryDateJtxt.getText();
                Double sellingPrice = Double.parseDouble(sellingPriceJtxt.getText());
                int stockQuantity = Integer.parseInt(stockQuantityJtxt.getText());

                // TODO add a check to see if manufactured Date is before expiry Date

                // TODO do not allow registration of expired medicine. 

                Medicine medicine = new Medicine(medName,brand,manufacturedDate,expiryDate,sellingPrice,stockQuantity);
                if(MedicineCrud.addMedicine(medicine)) {
                    JOptionPane.showMessageDialog(null, "Medicine Registered!", "success", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null, "Medicine Registration Failed. Please check your input and try again!", "Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    }
