package gui;

import database.crud.MedicineCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicineDeleteForm extends MedicineDisplayForm {
    public MedicineDeleteForm() {
        setTitle("Delete Medicine");
    }

    @Override
    public void addActionButton(JPanel informationPanel,JTextField txtMedName,JLabel medIdLbl, JTextField txtBrand, JTextField txtManufacturedDate, JTextField txtExpiryDate,JTextField  txtSellingPrice,JTextField txtStockQuantity){
        JButton updateBtn = new JButton("Delete");
        informationPanel.add(updateBtn);
        informationPanel.setLayout(new GridLayout(10,2, 10, 5));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isUpdateSuccessful = MedicineCrud.deleteMedicine(Integer.parseInt(medIdLbl.getText()));
                if(isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(null, "Medicine information delete", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Medicine information not delete. Please try again!", "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

