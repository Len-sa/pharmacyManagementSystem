package gui;


import database.crud.CustomerCrud;
import database.crud.EmployeeCrud;
import database.crud.MedicineCrud;
import database.crud.OrderCrud;
import entities.CustomerOrder;
import entities.Medicine;
import entities.OrderDetail;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicineSellForm extends JFrame {
    public MedicineSellForm() {
        setTitle("Sell Medicine");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel searchPanel = new JPanel();
        searchPanel.setSize(300, 300);
        searchPanel.setLayout(new GridLayout(3, 3));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        // medicine to select
        JLabel enterMedNameLbl = new JLabel("Med to sell: ");
        JTextField searchMedNameField = new JTextField(8);
        JComboBox<String> medicineDropdown = new JComboBox<>();
        medicineDropdown.setPrototypeDisplayValue("----------------------------------");
        searchPanel.add(enterMedNameLbl);
        searchPanel.add(searchMedNameField);
        searchPanel.add(medicineDropdown);

        searchMedNameField.getDocument().addDocumentListener(getDocumentListener(searchMedNameField, medicineDropdown, "medicine"));


        // employee to select
        JLabel enterEmpNameLbl = new JLabel("Employee: ");
        JTextField searchEmpNameField = new JTextField(8);
        JComboBox<String> employeeDropdown = new JComboBox<>();
        employeeDropdown.setPrototypeDisplayValue("----------------------------------");
        searchPanel.add(enterEmpNameLbl);
        searchPanel.add(searchEmpNameField);
        searchPanel.add(employeeDropdown);

        searchEmpNameField.getDocument().addDocumentListener(getDocumentListener(searchEmpNameField, employeeDropdown, "employee"));

        // customer to select
        JLabel enterCustomerNameLbl = new JLabel("Customer: ");
        JTextField searchCustomerNameField = new JTextField(8);
        JComboBox<String> customerDropdown = new JComboBox<>();
        customerDropdown.setPrototypeDisplayValue("----------------------------------");
        searchPanel.add(enterCustomerNameLbl);
        searchPanel.add(searchCustomerNameField);
        searchPanel.add(customerDropdown);

        searchCustomerNameField.getDocument().addDocumentListener(getDocumentListener(searchCustomerNameField, customerDropdown, "customer"));

        add(searchPanel, BorderLayout.NORTH);

        // add buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setSize(400, 500);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        actionPanel.setLayout(new GridLayout(6, 2, 10, 10));

        //med price
        JLabel priceLbl = new JLabel("Price Per Item");
        JTextField priceTxt = new JTextField();
        actionPanel.add(priceLbl);
        actionPanel.add(priceTxt);

        //discount
        JLabel discountLbl = new JLabel("Discount");
        JTextField discountTxt = new JTextField();
        actionPanel.add(discountLbl);
        actionPanel.add(discountTxt);

        // med quantity
        JLabel quantityLbl = new JLabel("Quantity");
        JTextField quantityTxt = new JTextField();
        actionPanel.add(quantityLbl);
        actionPanel.add(quantityTxt);


        // payment collection
        JLabel totalAmountToBePaidLbl = new JLabel();
        JLabel isPaidLbl = new JLabel("Is amount fully paid?");
        JCheckBox fullAmountPaidChkBox = new JCheckBox();

        actionPanel.add(totalAmountToBePaidLbl);
        actionPanel.add(new JLabel()); // to fill the 2 column requirement
        actionPanel.add(isPaidLbl);
        actionPanel.add(fullAmountPaidChkBox);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setEnabled(false);
        actionPanel.add(submitBtn);

        priceTxt.getDocument().addDocumentListener(getTotalAmtDueDocumentListener(priceTxt, totalAmountToBePaidLbl, quantityTxt, discountTxt));
        quantityTxt.getDocument().addDocumentListener(getTotalAmtDueDocumentListener(priceTxt, totalAmountToBePaidLbl, quantityTxt, discountTxt));
        discountTxt.getDocument().addDocumentListener(getTotalAmtDueDocumentListener(priceTxt, totalAmountToBePaidLbl, quantityTxt, discountTxt));

        fullAmountPaidChkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fullAmountPaidChkBox.isSelected()) {
                    submitBtn.setEnabled(true);
                } else {
                    submitBtn.setEnabled(false);
                }
            }
        });

        submitBtn.addActionListener(getSubmitBtnActionListener(fullAmountPaidChkBox, medicineDropdown, employeeDropdown,
                customerDropdown, quantityTxt, priceTxt, discountTxt));


        add(actionPanel, BorderLayout.SOUTH);

    }

    private static DocumentListener getTotalAmtDueDocumentListener(JTextField priceTxt, JLabel totalAmountToBePaidLbl, JTextField quantityTxt, JTextField discountTxt) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalAmountDue(priceTxt, totalAmountToBePaidLbl, quantityTxt, discountTxt);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalAmountDue(priceTxt, totalAmountToBePaidLbl, quantityTxt, discountTxt);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalAmountDue(priceTxt, totalAmountToBePaidLbl, quantityTxt, discountTxt);
            }
        };
    }

    private static void updateTotalAmountDue(JTextField priceTxt, JLabel totalAmountToBePaidLbl, JTextField quantityTxt, JTextField discountTxt) {
        double price, discount;
        int quantity;
        if (priceTxt.getText().isEmpty()) {
            totalAmountToBePaidLbl.setText("Please enter medicine price");
            return;
        } else {
            price = Double.parseDouble(priceTxt.getText());
        }
        if (quantityTxt.getText().isEmpty()) {
            totalAmountToBePaidLbl.setText("Please enter quantity");
            return;
        } else {
            quantity = Integer.parseInt(quantityTxt.getText());
        }

        if (!discountTxt.getText().isEmpty()) {
            discount = Double.parseDouble(discountTxt.getText());
        } else {
            discount = 0;
        }

        double totalToBePaid = (price * quantity) - discount;
        totalAmountToBePaidLbl.setText("Total amount due: " + totalToBePaid);
    }

    private DocumentListener getDocumentListener(JTextField searchField, JComboBox<String> comboBox, String type) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, comboBox, type);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, comboBox, type);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, comboBox, type);
            }
        };
    }

    private static ActionListener getSubmitBtnActionListener(JCheckBox fullAmountPaidChkBox, JComboBox<String> medicineDropdown,
                                                             JComboBox<String> employeeDropdown, JComboBox<String> customerDropdown, JTextField quantityTxt,
                                                             JTextField priceTxt, JTextField discountTxt) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fullAmountPaidChkBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please make sure the amount is fully paid", "Amount not paid yet", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (medicineDropdown.getSelectedItem() == null || employeeDropdown.getSelectedItem() == null
                        || customerDropdown.getSelectedItem() == null || quantityTxt.getText().isEmpty()
                        || priceTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields before submitting", "Required fields not filled", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String medIdSelected = medicineDropdown.getSelectedItem().toString().split("#")[1];
                int medId = Integer.parseInt(medIdSelected);
                String empIdSelected = employeeDropdown.getSelectedItem().toString().split("-")[1];
                int empId = Integer.parseInt(empIdSelected);
                String custIddSelected = customerDropdown.getSelectedItem().toString().split("-")[1];
                int custId = Integer.parseInt(custIddSelected);

                String quantity = quantityTxt.getText();
                String price = priceTxt.getText();
                String discount = discountTxt.getText();

                // check if there is enough medicines to be sold.
                Medicine medicine = MedicineCrud.getMedicineById(medId);

                if (Integer.parseInt(quantity) > medicine.getStockQuantity()) {
                    JOptionPane.showMessageDialog(null, "There is no enough medicine for requested quantity", "No Enough Medicine in the Stock!", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (isMedicineExpired(medicine.getExpiredDate())) {
                    JOptionPane.showMessageDialog(null, "This medicine can not be sold as it has expired", "Medicine Expired!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // add details to Customer Order
                CustomerOrder customer = new CustomerOrder(empId, custId);
                if (!OrderCrud.addCustomerOrder(customer)) {
                    JOptionPane.showMessageDialog(null, "Can not add customer Order. Aborting the action", "Customer Order creation failed", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // add details to OrderDetails table
                int lastCustomerOrderId = OrderCrud.getTheLastCustomerOrderId();
                OrderDetail orderDetail = new OrderDetail(lastCustomerOrderId, medId, Integer.parseInt(quantity), Double.parseDouble(price));
                if (!discount.isEmpty()) {
                    orderDetail.setDiscount(Double.parseDouble(discount));
                }

                if (!OrderCrud.addOrderDetails(orderDetail)) {
                    JOptionPane.showMessageDialog(null, "Can not add Order details. Aborting the action", "Order Detail Creation failed", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // update Medicine stock as the sold quantity
                boolean isUpdateSuccessful = MedicineCrud.updateMedicineQuantity(medId, Integer.parseInt(quantity));
                if (isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(null, "Medicine successfully sold", "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
    }

    private static boolean isMedicineExpired(String expiredDate) {
        // Convert the string to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expiryDate = LocalDate.parse(expiredDate, formatter);
        // Get today's date
        LocalDate currentDate = LocalDate.now();
        return expiryDate.isBefore(currentDate);
    }

    private void searchAndUpdateDropdown(JTextField searchField, JComboBox<String> comboBox, String type) {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            List<String> searchContents = new ArrayList<>();
            if (type.equals("customer")) {
                searchContents = CustomerCrud.searchByFirstName(searchText);
            } else if (type.equals("employee")) {
                searchContents = EmployeeCrud.searchByFirstName(searchText);
            } else if (type.equals("medicine")) {
                searchContents = MedicineCrud.searchByName(searchText);
            }
            updateDropdown(comboBox, searchContents);
        } else {
            comboBox.removeAllItems();
        }
    }

    private void updateDropdown(JComboBox<String> comboBox, List<String> contents) {
        comboBox.removeAllItems();
        for (String content : contents) {
            comboBox.addItem(content);
        }
    }

}
