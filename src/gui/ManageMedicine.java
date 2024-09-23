package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageMedicine extends JFrame {
    public ManageMedicine() {
        setTitle("MANAGE MEDICINE ");
        setVisible(true);
        setLayout(new GridLayout(3, 2, 10, 10));
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setSize(400, 250);
        jPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton infoBtn = new JButton("Med Info");
        infoBtn.setSize(40, 40);
        infoBtn.setOpaque(true);

        JButton addMedicine = new JButton("Add New");
        addMedicine.setSize(40, 40);
        addMedicine.setOpaque(true);

        JButton updateMedicine = new JButton("Update");
        updateMedicine.setSize(40, 40);
        addMedicine.setOpaque(true);

        JButton delete = new JButton("Delete");
        infoBtn.setSize(40, 40);
        infoBtn.setOpaque(true);

        JButton sellMedicine = new JButton("Sell");
        infoBtn.setSize(40, 40);
        infoBtn.setOpaque(true);

        JButton stockQuantity = new JButton("Stock Quantity");
        infoBtn.setSize(40, 40);
        infoBtn.setOpaque(true);

        //adding buttons to panel
        jPanel.add(addMedicine);
        jPanel.add(updateMedicine);
        jPanel.add(delete);
        jPanel.add(sellMedicine);
        jPanel.add(stockQuantity);
        jPanel.add(infoBtn);


        add(jPanel, BorderLayout.CENTER);

        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicineDisplayForm();

            }
        });
        addMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicineRegistrationForm();

            }
        });

        updateMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicineUpdateForm();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicineDeleteForm();
            }
        });

        sellMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicineSellForm();
            }
        });

        stockQuantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
