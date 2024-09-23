package gui;

import database.crud.MedicineCrud;
import entities.Medicine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MedicineDisplayForm extends JFrame {
    public MedicineDisplayForm() {
        setTitle("Medicine Information");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(2, 1));

        JPanel searchPanel = new JPanel();
        searchPanel.setSize(300, 800);
        JLabel enterNameLbl = new JLabel("Enter Medicine Name To Search: ");
        searchPanel.add(enterNameLbl);
        JTextField searchField = new JTextField(8);
        JComboBox<String> medicineDropdown = new JComboBox<>();
        medicineDropdown.setPrototypeDisplayValue("----------------------------------");
        searchPanel.add(searchField);
        searchPanel.add(medicineDropdown);

        JLabel medIdLbl = new JLabel();
        JLabel medIdLabel = new JLabel();
        JLabel lblMedName = new JLabel();
        JTextField txtMedName = new JTextField();
        JLabel jlBrand = new JLabel();
        JTextField txtBrand = new JTextField();
        JLabel jlManufacturedDate = new JLabel();
        JTextField txtManufacturedDate = new JTextField();
        JLabel jlExpiryDate = new JLabel();
        JTextField txtExpiryDate = new JTextField();
        JLabel jlSellingPrice = new JLabel();
        JTextField txtSellingPrice = new JTextField();
        JLabel jlsStockQuantity = new JLabel();
        JTextField txtStockQuantity = new JTextField();

        txtMedName.setEnabled(isReadOAndWriteTextFields());
        txtBrand.setEnabled(isReadOAndWriteTextFields());
        txtManufacturedDate.setEnabled(isReadOAndWriteTextFields());
        txtExpiryDate.setEnabled(isReadOAndWriteTextFields());
        txtSellingPrice.setEnabled(isReadOAndWriteTextFields());
        txtStockQuantity.setEnabled(isReadOAndWriteTextFields());

        setComponentInvisible(txtMedName, medIdLbl, txtBrand, txtManufacturedDate, txtExpiryDate, txtSellingPrice,txtStockQuantity);

        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new GridLayout(10, 2,7,5));
        informationPanel.add(medIdLabel);
        informationPanel.add(medIdLbl);
        informationPanel.add(lblMedName);
        informationPanel.add(txtMedName);
        informationPanel.add(jlBrand);
        informationPanel.add(txtBrand);
        informationPanel.add(jlManufacturedDate);
        informationPanel.add(txtManufacturedDate);
        informationPanel.add(jlExpiryDate);
        informationPanel.add(txtExpiryDate);
        informationPanel.add(jlSellingPrice);
        informationPanel.add(txtSellingPrice);
        informationPanel.add(jlsStockQuantity);
        informationPanel.add(txtStockQuantity);
        informationPanel.setBorder(new EmptyBorder(20, 30, 10, 10));

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, medicineDropdown);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, medicineDropdown);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, medicineDropdown);
            }
        });


        medicineDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (medicineDropdown.getSelectedItem() == null) {
                    medIdLabel.setText("No Employee found");
                    medIdLabel.setVisible(true);
                    return;
                }

                String selectedMedicine = medicineDropdown.getSelectedItem().toString();
                String medId = selectedMedicine.split("#")[1];
                Medicine medicine = MedicineCrud.getMedicineById(Integer.parseInt(medId));
                if (medicine != null) {
                    medIdLabel.setText("Registration Number:");
                    medIdLbl.setText(String.valueOf(medicine.getMedicineId()));
                    medIdLbl.setVisible(true);
                    lblMedName.setText("Medicine Name: ");
                    txtMedName.setText(medicine.getMedicineName());
                    txtMedName.setVisible(true);
                    jlBrand.setText("Brand: ");
                    txtBrand.setText(medicine.getMedicineBrand());
                    txtBrand.setVisible(true);
                    jlManufacturedDate.setText("Manufactured Date:");
                    txtManufacturedDate.setText(medicine.getManufacturedDate());
                    txtManufacturedDate.setVisible(true);
                    jlExpiryDate.setText("Expiry Date: ");
                    txtExpiryDate.setText(medicine.getExpiredDate());
                    txtExpiryDate.setVisible(true);
                    jlSellingPrice.setText("Selling Price: ");
                    txtSellingPrice.setText(String.valueOf(medicine.getSellingPrice()));
                    txtSellingPrice.setVisible(true);
                    jlsStockQuantity.setText("Stock Quantity: ");
                    txtStockQuantity.setText(String.valueOf(medicine.getStockQuantity()));
                    txtStockQuantity.setVisible(true);
                }
            }
        });

        addActionButton(informationPanel,  txtMedName,medIdLbl,txtBrand, txtManufacturedDate, txtExpiryDate, txtSellingPrice,txtStockQuantity);
        add(searchPanel);
        add(informationPanel);
        pack();
    }

    private static void setComponentInvisible(JTextField txtMedName, JLabel medIdjl, JTextField txtBrand, JTextField txtManufacturedDate, JTextField txtExpiryDate, JTextField txtStockQuantity, JTextField stockQuantity) {
        txtMedName.setVisible(false);
        medIdjl.setVisible(false);
        txtBrand.setVisible(false);
        txtManufacturedDate.setVisible(false);
        txtExpiryDate.setVisible(false);
        txtStockQuantity.setVisible(false);
        stockQuantity.setVisible(false);
    }


    private void searchAndUpdateDropdown(JTextField searchField, JComboBox<String> medicineDropdown) {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            List<String> medicineNames = MedicineCrud.searchByName(searchText);
            updateDropdown(medicineDropdown, medicineNames);
        } else {
            medicineDropdown.removeAllItems();
        }
    }

    private void updateDropdown(JComboBox<String> medicineDropdown, List<String> medicineNames) {
        medicineDropdown.removeAllItems();
        for (String name : medicineNames) {
            medicineDropdown.addItem(name);
        }
    }


    public boolean isReadOAndWriteTextFields() {
        return false;
    }

    public void addActionButton(JPanel informationPanel,JTextField txtMedName,JLabel medIdLbl, JTextField txtBrand, JTextField txtManufacturedDate, JTextField txtExpiryDate,JTextField  txtSellingPrice,JTextField txtStockQuantity) {
        // no action button for display
    }
}
