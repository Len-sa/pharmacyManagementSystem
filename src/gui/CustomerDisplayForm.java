package gui;

import database.crud.CustomerCrud;
import entities.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerDisplayForm extends JFrame{
        public CustomerDisplayForm() {
            setTitle("Customer Information");
            setSize(500, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
            setLayout(new GridLayout(2, 1));

            JPanel searchPanel = new JPanel();
            searchPanel.setSize(400, 300);
            JLabel enterNameLbl = new JLabel("Enter name to search: ");
            searchPanel.add(enterNameLbl);
            JTextField searchField = new JTextField(8);
            JComboBox<String> customerDropdown = new JComboBox<>();
            customerDropdown.setPrototypeDisplayValue("------------------");
            searchPanel.add(searchField);
            searchPanel.add(customerDropdown);

            JLabel custIdLbl = new JLabel();
            JLabel custIdLabel = new JLabel();
            JLabel lblCustName = new JLabel();
            JTextField txtCustName = new JTextField();
            JLabel jlCustLastName = new JLabel();
            JTextField txtCustLastName = new JTextField();
            JLabel jlCity = new JLabel();
            JTextField txtCity = new JTextField();
            JLabel jlDOB = new JLabel();
            JTextField txtDOB = new JTextField();
            JLabel jlSubCity = new JLabel();
            JTextField txtSubCity = new JTextField();
            JLabel jlKebele = new JLabel();
            JTextField txtKebele = new JTextField();

            txtCustName.setEnabled(isReadOAndWriteTextFields());
            txtCustLastName.setEnabled(isReadOAndWriteTextFields());
            txtCity.setEnabled(isReadOAndWriteTextFields());
            txtSubCity.setEnabled(isReadOAndWriteTextFields());
            txtKebele.setEnabled(isReadOAndWriteTextFields());

            setComponentInvisible(txtKebele, custIdLbl, txtCustName, txtCustLastName,txtDOB, txtCity, txtSubCity);

            JPanel informationPanel = new JPanel();
            informationPanel.setLayout(new GridLayout(9, 2));
            informationPanel.add(custIdLabel);
            informationPanel.add(custIdLbl);
            informationPanel.add(lblCustName);
            informationPanel.add(txtCustName);
            informationPanel.add(jlCustLastName);
            informationPanel.add(txtCustLastName);
            informationPanel.add(jlDOB);
            informationPanel.add(txtDOB);
            informationPanel.add(jlCity);
            informationPanel.add(txtCity);
            informationPanel.add(jlSubCity);
            informationPanel.add(txtSubCity);
            informationPanel.add(jlKebele);
            informationPanel.add(txtKebele);
            informationPanel.setBorder(new EmptyBorder(20, 30, 10, 10));

            searchField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    searchAndUpdateDropdown(searchField, customerDropdown);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    searchAndUpdateDropdown(searchField, customerDropdown);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    searchAndUpdateDropdown(searchField, customerDropdown);
                }
            });


            customerDropdown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (customerDropdown.getSelectedItem() == null) {
                        custIdLabel.setText("No Customer found");
                        custIdLabel.setVisible(true);
                        return;
                    }

                    String selectedCustomer = customerDropdown.getSelectedItem().toString();
                    String custId = selectedCustomer.split("-")[1];
                    Customer customer = CustomerCrud.getCustomer(Integer.parseInt(custId));

                    if (customer != null) {
                        custIdLabel.setText("Registration Number:");
                        custIdLbl.setText(String.valueOf(customer.getCustomerId()));
                        custIdLbl.setVisible(true);
                        lblCustName.setText("Name: ");
                        txtCustName.setText(customer.getCustomerFirstName());
                        txtCustName.setVisible(true);
                        jlCustLastName.setText("Last Name: ");
                        txtCustLastName.setText(customer.getCustomerLastName());
                        txtCustLastName.setVisible(true);
                        jlDOB.setText("Date of Birth: ");
                        txtDOB.setText(customer.getCustomerDOB());
                        txtDOB.setVisible(true);
                        jlCity.setText("City:");
                        txtCity.setText(customer.getCustomerAddress().getCity());
                        txtCity.setVisible(true);
                        jlSubCity.setText("Subcity: ");
                        txtSubCity.setText(customer.getCustomerAddress().getSubcity());
                        txtSubCity.setVisible(true);
                        jlKebele.setText("Kebele: ");
                        txtKebele.setText(String.valueOf(customer.getCustomerAddress().getKebele()));
                        txtKebele.setVisible(true);
                    }
                }
            });
            addActionButton(informationPanel, custIdLbl, txtCustName, txtCustLastName, txtDOB,txtCity, txtSubCity, txtKebele);
            add(searchPanel);
            add(informationPanel);
        }

        private static void setComponentInvisible(JTextField jlKebele, JLabel custIdLbl, JTextField jlCustName, JTextField jlCustLastName, JTextField jlDOB, JTextField jlCity, JTextField jlSubCity) {
            jlKebele.setVisible(false);
            custIdLbl.setVisible(false);
            jlCustName.setVisible(false);
            jlCustLastName.setVisible(false);
            jlDOB.setVisible(false);
            jlCity.setVisible(false);
            jlSubCity.setVisible(false);
        }


        private void searchAndUpdateDropdown(JTextField searchField, JComboBox<String> customerDropdown) {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                List<String> customerNames = CustomerCrud.searchByFirstName(searchText);
                updateDropdown(customerDropdown, customerNames);
            } else {
                customerDropdown.removeAllItems();
            }
        }

        private void updateDropdown(JComboBox<String> customerDropdown, List<String> customerNames) {
            customerDropdown.removeAllItems();
            for (String name : customerNames) {
                customerDropdown.addItem(name);
            }
        }


        public boolean isReadOAndWriteTextFields() {
            return false;
        }

        public void addActionButton(JPanel informationPanel, JLabel custIdLbl, JTextField jlCustName, JTextField jlCustLastName, JTextField jlDOB, JTextField jlCity, JTextField jlSubCity, JTextField jlKebele) {
            // no action button for display
        }

    }

