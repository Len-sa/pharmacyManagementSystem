package database.crud;

import database.connection.ConnectionManager;
import entities.Address;
import entities.CustomerOrder;
import entities.Employee;
import entities.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderCrud {


    public static boolean addCustomerOrder(CustomerOrder customerOrder) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            String query = "INSERT INTO customerOrder (orderDate, empId, custId)   values(GETDATE(), ?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerOrder.getEmployeeId());
            preparedStatement.setInt(2, customerOrder.getCustomerId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error while registering Customer Order" + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources" + e.getMessage());
            }
        }
        return false;
    }

    public static boolean addOrderDetails(OrderDetail orderDetail) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            String query = "INSERT INTO orderDetail (ordId, medId, quantity, pricePerItem, discount)  values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetail.getOrderId());
            preparedStatement.setInt(2, orderDetail.getMedicineId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setDouble(4, orderDetail.getPricePerItem());
            preparedStatement.setDouble(5, orderDetail.getDiscount());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error while registering Order details" + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources" + e.getMessage());
            }
        }
        return false;
    }

    public static int getTheLastCustomerOrderId() {
        Connection connection;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("SELECT TOP 1 ordId FROM customerOrder ORDER BY ordId DESC");
            resultSet = preparedstatement.executeQuery();

            resultSet.next(); // there is only one row to be returned
            return resultSet.getInt("ordId");

        } catch (SQLException e) {
            System.out.println("Error while selecting the last customer Order Id" + e.getMessage());

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
        return 0;
    }
}
