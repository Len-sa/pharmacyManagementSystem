package database.crud;

import database.connection.ConnectionManager;
import entities.Address;
import entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCrud {

    public static void addCustomer(Customer customer) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionManager.getConnection();
            statement = connection.createStatement();
            String query = "Insert into customer (firstName, middelName, lastName, city, subCity, kebele, DOB) values('" + customer.getCustomerFirstName() + "','" + customer.getCustomerMiddleName() + "','" + customer.getCustomerLastName() + "','" + customer.getCustomerAddress().getCity() + "','" + customer.getCustomerAddress().getSubcity() + "','" + customer.getCustomerAddress().getKebele() + "','" + customer.getCustomerDOB() + "')";
            System.out.println(query);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Customer getCustomer(int custId) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("Select * From customer where custId = ?");
            preparedstatement.setInt(1, custId);
            resultSet = preparedstatement.executeQuery();
            resultSet.next(); // there is only 1 customer with a specific ID.
            int id = resultSet.getInt("custId");
            String firstName = resultSet.getString("firstName");
            String middelName = resultSet.getString("middelName");
            String lastName = resultSet.getString("lastName");
            String city = resultSet.getString("city");
            String subCity = resultSet.getString("subCity");
            int kebele = resultSet.getInt("kebele");
            String DOB = resultSet.getString("DOB");
            Customer customer =  new Customer(firstName, middelName, lastName, new Address(kebele, city, subCity));
            customer.setCustomerId(id);
            customer.setCustomerDOB(DOB);
            return customer;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedstatement != null) {
                    preparedstatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources");
            }
        }
        return null;
    }


    public static boolean deleteCustomer(int custId) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;

        try {
            connection = ConnectionManager.getConnection();

            String sql = "DELETE FROM customer WHERE custId = ?";

            // Step 3: Prepare the statement
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setInt(1, custId);
            int rowsAffected = preparedstatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer with ID " + custId + " was deleted successfully.");
            } else {
                System.out.println("No customer found with ID " + custId);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (preparedstatement != null) {
                    preparedstatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
        return false;
    }

    public static boolean updateCustomer(Customer customer) {
        PreparedStatement updatePreparedStatement = null;
        //PreparedStatement updatePhoneNumberStatement = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("Update customer SET firstName = ?,lastName = ?,city = ?, subCity = ?, kebele = ? where custId = ?");
            updatePreparedStatement.setString(1, customer.getCustomerFirstName());
            updatePreparedStatement.setString(2, customer.getCustomerLastName());
            updatePreparedStatement.setString(3, customer.getCustomerAddress().getCity());
            updatePreparedStatement.setString(4, customer.getCustomerAddress().getSubcity());
            updatePreparedStatement.setInt(5, customer.getCustomerAddress().getKebele());
            updatePreparedStatement.setInt(6, customer.getCustomerId());

            int rowAffected = updatePreparedStatement.executeUpdate();
            return rowAffected == 1;

        } catch (SQLException e) {
            System.out.println("Error while trying update a Customer " + e);
        } finally {
            try {
                if (updatePreparedStatement != null) {
                    updatePreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources");
            }
        }
        return false;
    }

    public static List<String> searchByFirstName(String searchText) {
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<String> result = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("Select * From customer where firstName like ?");
            preparedstatement.setString(1, searchText + "%");
            resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("custId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                result.add(firstName + " " + lastName + "-" + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedstatement != null) {
                    preparedstatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources");
            }
        }
        return result;
    }

}
