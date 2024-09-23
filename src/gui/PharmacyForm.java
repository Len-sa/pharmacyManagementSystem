package gui;

import database.crud.PharmacyCrud;
import entities.Address;
import entities.Pharmacy;
import entities.PharmacyPhoneNumber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PharmacyForm extends JFrame {

    public PharmacyForm() {
        setTitle("Pharmacy Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating form fields
        JLabel pharmacyIdLabel = new JLabel("Pharmacy Reg. No:");
        JLabel pharmacyIdNumberLabel = new JLabel();


        JLabel nameLabel = new JLabel("Pharmacy Name:");
        JTextField nameField = new JTextField(20);

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(20);

        JLabel subcityLabel = new JLabel("Subcity:");
        JTextField subcityField = new JTextField(20);

        JLabel kebeleLabel = new JLabel("Kebele:");
        JTextField kebeleField = new JTextField(20);

        JLabel note = new JLabel("NOTE");
        JLabel ruleForPhoneNumber = new JLabel("Add Multiple Phone Numbers");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField(20);

        // fill the text fields if needed
        fillTextFields(pharmacyIdNumberLabel, nameField, cityField, subcityField, kebeleField, phoneNumberField);

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 5, 5));
        panel.setBorder(new EmptyBorder(20, 30, 10, 10));
        panel.add(pharmacyIdLabel);
        panel.add(pharmacyIdNumberLabel);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(subcityLabel);
        panel.add(subcityField);
        panel.add(kebeleLabel);
        panel.add(kebeleField);
        panel.add(note);
        panel.add(ruleForPhoneNumber);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(new JLabel()); // Empty for spacing
        add(panel, BorderLayout.CENTER);

        // adding Phone number


        addActionButton(panel, pharmacyIdNumberLabel, nameField, cityField, subcityField, kebeleField ,phoneNumberField);

        setVisible(true);
    }

    public void addPhoneNumber(TextField phoneNumberField){


    }
    public void addActionButton(JPanel panel, JLabel pharmacyIdNumberLabel, JTextField nameField, JTextField cityField, JTextField subcityField, JTextField kebeleField, JTextField phoneNumberField) {
        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn);
        // Submit button action
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewPharmacy(nameField, cityField, subcityField, kebeleField,phoneNumberField);
            }
        });
    }

    public void fillTextFields(JLabel pharmacyIdNumberLabel, JTextField nameField, JTextField cityField, JTextField subcityField,
                               JTextField kebeleField, JTextField phoneNumberField){
        // by default it does nothing
    }

    private static void addNewPharmacy(JTextField nameField, JTextField cityField, JTextField subcityField, JTextField kebeleField,JTextField phoneNumberField) {
        String name = nameField.getText();
        String city = cityField.getText();
        String subcity = subcityField.getText();
        int kebele = Integer.parseInt(kebeleField.getText());

        String phoneNumberTexts[] = phoneNumberField.getText().split(",");
        List<PharmacyPhoneNumber> pharmacyPhoneNumberList = new ArrayList<>();
        for(String phoneNumber : phoneNumberTexts) {
            if(phoneNumber != null && !phoneNumber.equals("")) {
                pharmacyPhoneNumberList.add(new PharmacyPhoneNumber(phoneNumber));
            }
        }

        Pharmacy pharmacy = new Pharmacy(name, new Address(kebele, city, subcity), pharmacyPhoneNumberList);
        boolean isInsertSuccessful = PharmacyCrud.insertPharmacy(pharmacy);

        if(!isInsertSuccessful) {
            JOptionPane.showMessageDialog(null, "Pharmacy already registered! Please make update if necessary", "Operation cancelled", JOptionPane.WARNING_MESSAGE);
        } else {
            Pharmacy insertedPharmacy = PharmacyCrud.getPharmacyDetail();
            // also insert phone number if any.
            PharmacyCrud.insertPharmacyPhoneNumbers(pharmacyPhoneNumberList, insertedPharmacy.getPharmacyId());
            JOptionPane.showMessageDialog(null, "Pharmacy named " + name + " is Registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}




