package gui;

import database.crud.PharmacyCrud;
import entities.Address;
import entities.Pharmacy;
import entities.PharmacyPhoneNumber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PharmacyUpdateForm extends PharmacyForm {

    public PharmacyUpdateForm() {
        super();
        setTitle("Update Pharmacy");
    }

    @Override
    public void addActionButton(JPanel panel, JLabel pharmacyIdNumberLabel, JTextField nameField, JTextField cityField, JTextField subcityField, JTextField kebeleField, JTextField phoneNumberField) {
        JButton updateBtn = new JButton("Update");
        panel.add(updateBtn);
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pharmacyId = Integer.parseInt(pharmacyIdNumberLabel.getText());
                Address address = new Address(Integer.parseInt(kebeleField.getText()),subcityField.getText(),cityField.getText());

                List<PharmacyPhoneNumber> phoneNumberList = new ArrayList<>();
                String[] inputPhoneNumbers = phoneNumberField.getText().split(",");
                for(String inputPhoneNumber: inputPhoneNumbers) {
                    phoneNumberList.add(new PharmacyPhoneNumber(pharmacyId, inputPhoneNumber));
                }
                Pharmacy pharmacy = new Pharmacy(nameField.getText(), address, phoneNumberList);
                pharmacy.setPharmacyId(pharmacyId);
                boolean isUpdateSuccessful = PharmacyCrud.updatePharmacy(pharmacy);
                if(isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(null, "Pharmacy details updated successfully", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    @Override
    public void fillTextFields(JLabel pharmacyIdNumberLabel, JTextField nameField, JTextField cityField, JTextField subcityField,
                               JTextField kebeleField, JTextField phoneNumberField){
        // get the current pharmacy from DB
        Pharmacy pharmacy = PharmacyCrud.getPharmacyDetail();

        // display it in the textfields
        pharmacyIdNumberLabel.setText(String.valueOf(pharmacy.getPharmacyId()));
        nameField.setText(pharmacy.getPharmacyName());
        cityField.setText(pharmacy.getAddress().getCity());
        subcityField.setText(pharmacy.getAddress().getSubcity());
        kebeleField.setText(Integer.toString(pharmacy.getAddress().getKebele()));

        // set all phone numbers to phoneNumberField
        StringBuilder sb = new StringBuilder();
        for(PharmacyPhoneNumber pharmacyPhoneNumber: pharmacy.getPhoneNumbers()){
            sb.append(pharmacyPhoneNumber.getPhoneNumber()).append(",");
        }
        phoneNumberField.setText(sb.toString());
    }


    }


