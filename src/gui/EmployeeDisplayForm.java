package gui;

import database.crud.EmployeeCrud;
import entities.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDisplayForm extends JFrame {
    public EmployeeDisplayForm() {
        setTitle("Employee Information");
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
        JComboBox<String> employeeDropdown = new JComboBox<>();
        employeeDropdown.setPrototypeDisplayValue("------------------");
        searchPanel.add(searchField);
        searchPanel.add(employeeDropdown);

        JLabel empIdLbl = new JLabel();
        JLabel empIdLabel = new JLabel();
        JLabel lblEmpName = new JLabel();
        JTextField txtEmpName = new JTextField();
        JLabel jlEmpLastName = new JLabel();
        JTextField txtEmpLastName = new JTextField();
        JLabel jlCity = new JLabel();
        JTextField txtCity = new JTextField();
        JLabel jlSubCity = new JLabel();
        JTextField txtSubCity = new JTextField();
        JLabel jlKebele = new JLabel();
        JTextField txtKebele = new JTextField();

        txtEmpName.setEnabled(isReadOAndWriteTextFields());
        txtEmpLastName.setEnabled(isReadOAndWriteTextFields());
        txtCity.setEnabled(isReadOAndWriteTextFields());
        txtSubCity.setEnabled(isReadOAndWriteTextFields());
        txtKebele.setEnabled(isReadOAndWriteTextFields());

        setComponentInvisible(txtKebele, empIdLbl, txtEmpName, txtEmpLastName, txtCity, txtSubCity);

        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new GridLayout(10, 2,5,5));
        informationPanel.add(empIdLabel);
        informationPanel.add(empIdLbl);
        informationPanel.add(lblEmpName);
        informationPanel.add(txtEmpName);
        informationPanel.add(jlEmpLastName);
        informationPanel.add(txtEmpLastName);
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
                searchAndUpdateDropdown(searchField, employeeDropdown);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, employeeDropdown);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdateDropdown(searchField, employeeDropdown);
            }
        });


        employeeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeDropdown.getSelectedItem() == null) {
                    empIdLabel.setText("No Employee found");
                    empIdLabel.setVisible(true);
                    return;
                }

                String selectedEmployee = employeeDropdown.getSelectedItem().toString();
                String empId = selectedEmployee.split("-")[1];
                Employee employee = EmployeeCrud.getEmployee(Integer.parseInt(empId));

                if (employee != null) {
                    empIdLabel.setText("Registration Number:");
                    empIdLbl.setText(String.valueOf(employee.getEmployeeId()));
                    empIdLbl.setVisible(true);
                    lblEmpName.setText("Name: ");
                    txtEmpName.setText(employee.getEmployeeFirstName());
                    txtEmpName.setVisible(true);
                    jlEmpLastName.setText("Last Name: ");
                    txtEmpLastName.setText(employee.getEmployeeLastName());
                    txtEmpLastName.setVisible(true);
                    jlCity.setText("City:");
                    txtCity.setText(employee.getEmployeAddress().getCity());
                    txtCity.setVisible(true);
                    jlSubCity.setText("Subcity: ");
                    txtSubCity.setText(employee.getEmployeAddress().getSubcity());
                    txtSubCity.setVisible(true);
                    jlKebele.setText("Kebele: ");
                    txtKebele.setText(String.valueOf(employee.getEmployeAddress().getKebele()));
                    txtKebele.setVisible(true);
                }
            }
        });


        addActionButton(informationPanel, empIdLbl, txtEmpName, txtEmpLastName, txtCity, txtSubCity, txtKebele);
        add(searchPanel);
        add(informationPanel);
    }

    private static void setComponentInvisible(JTextField jlKebele, JLabel empIdLbl, JTextField jlEmpName, JTextField jlEmpLastName, JTextField jlCity, JTextField jlSubCity) {
        jlKebele.setVisible(false);
        empIdLbl.setVisible(false);
        jlEmpName.setVisible(false);
        jlEmpLastName.setVisible(false);
        jlCity.setVisible(false);
        jlSubCity.setVisible(false);
    }


    private void searchAndUpdateDropdown(JTextField searchField, JComboBox<String> employeeDropdown) {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            List<String> employeeNames = EmployeeCrud.searchByFirstName(searchText);
            updateDropdown(employeeDropdown, employeeNames);
        } else {
            employeeDropdown.removeAllItems();
        }
    }

    private void updateDropdown(JComboBox<String> employeeDropdown, List<String> employeeNames) {
        employeeDropdown.removeAllItems();
        for (String name : employeeNames) {
            employeeDropdown.addItem(name);
        }
    }


    public boolean isReadOAndWriteTextFields() {
        return false;
    }

    public void addActionButton(JPanel informationPanel, JLabel empIdLbl, JTextField jlEmpName, JTextField jlEmpLastName, JTextField jlCity, JTextField jlSubCity, JTextField jlKebele) {
        // no action button for display
    }

}
