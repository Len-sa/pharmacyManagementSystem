package gui;

import database.crud.MedicineCrud;
import entities.Medicine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicineUpdateForm extends MedicineDisplayForm {
    public MedicineUpdateForm() {
        setTitle("Update Medicine");
    }

    @Override
    public boolean isReadOAndWriteTextFields() {
        return true;
    }

    @Override
    public void addActionButton(JPanel informationPanel,JTextField txtMedName,JLabel medIdLbl, JTextField txtBrand, JTextField txtManufacturedDate, JTextField txtExpiryDate,JTextField  txtSellingPrice,JTextField txtStockQuantity){
        JButton updateBtn = new JButton("Update");
        informationPanel.add(updateBtn);
        informationPanel.setLayout(new GridLayout(10,2, 10, 5));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Medicine medicine = new Medicine(txtMedName.getText(), txtBrand.getText(),txtManufacturedDate.getText(),txtExpiryDate.getText(),Double.parseDouble(txtSellingPrice.getText()),Integer.parseInt(txtStockQuantity.getText()));
                medicine.setMedicineId(Integer.parseInt(medIdLbl.getText()));
                boolean isUpdateSuccessful = MedicineCrud.updateMedicine(medicine);
                if(isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(null, "Medicine information updated", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Medicine information not updated. Please try again!", "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

