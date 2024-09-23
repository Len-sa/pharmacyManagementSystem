package database.crud;

import database.connection.ConnectionManager;

import entities.Address;
import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EmployeeCrud {
    public static boolean addEmployee(Employee employee){
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            String query = "Insert into employee (firstName, middelName, lastName, city, subCity, kebele,salary) values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getEmployeeFirstName());
            preparedStatement.setString(2,employee.getEmployeeMiddleName());
            preparedStatement.setString(3,employee.getEmployeeLastName());
            preparedStatement.setString(4,employee.getEmployeAddress().getCity());
            preparedStatement.setString(5,employee.getEmployeAddress().getSubcity());
            preparedStatement.setInt(6, employee.getEmployeAddress().getKebele());
            preparedStatement.setDouble(7,employee.getSalary());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error while registering employee" +ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                System.out.println("Error while closing resources" +e.getMessage());
            }
        }
        return false;
    }
    public static Employee getEmployee(int empId) {
        Connection connection;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        Employee employee = null;
        try{
            connection = ConnectionManager.getConnection();
            preparedstatement= connection.prepareStatement("Select * From employee where empId = ?");
            preparedstatement.setInt(1, empId);
            resultSet = preparedstatement.executeQuery();

            while(resultSet.next()){
                int id  = resultSet.getInt("empId");
                String firstName = resultSet.getString("firstName");
                String middelName = resultSet.getString("middelName");
                String lastName = resultSet.getString("lastName");
                String city = resultSet.getString("city");
                String subCity = resultSet.getString("subCity");
                int kebele  = resultSet.getInt("kebele");
                double salary = Double.parseDouble(resultSet.getString("salary"));
                Address address = new Address(kebele,city,subCity);
                employee = new Employee(firstName,middelName,address,salary);
                employee.setEmployeeId(id);
                employee.setEmployeeLastName(lastName);
                return employee;
            }
        }catch(SQLException  e){
            System.out.println(e.getMessage());

        }
        finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(preparedstatement != null) {
                    preparedstatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources");
            }
        }
        return employee ;
    }
    public static boolean deleteEmployee(int empId) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("DELETE FROM employee WHERE empId = ?");
            preparedstatement.setInt(1, empId);
            int rowsAffected = preparedstatement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (preparedstatement != null) {
                    preparedstatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
        return false;
    }


    public static boolean updateEmployee(Employee employee){
        PreparedStatement updatePreparedStatement = null;
        //PreparedStatement updatePhoneNumberStatement = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("Update employee SET firstName = ?,lastName = ?,city = ?, subCity = ?, kebele = ? where empId = ?");
            updatePreparedStatement.setString(1, employee.getEmployeeFirstName());
            updatePreparedStatement.setString(2, employee.getEmployeeLastName());
            updatePreparedStatement.setString(3, employee.getEmployeAddress().getCity());
            updatePreparedStatement.setString(4,employee.getEmployeAddress().getSubcity());
            updatePreparedStatement.setInt(5, employee.getEmployeAddress().getKebele());
            updatePreparedStatement.setInt(6, employee.getEmployeeId());

            int rowAffected =  updatePreparedStatement.executeUpdate();
            return rowAffected == 1;

        } catch (SQLException e) {
            System.out.println("Error while trying update a Employee " + e);
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
            preparedstatement= connection.prepareStatement("Select * From employee where firstName like ?");
            preparedstatement.setString(1, searchText + "%");
            resultSet = preparedstatement.executeQuery();

            while(resultSet.next()){
                int id  = resultSet.getInt("empId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                result.add(firstName + " " + lastName + "-" + id);
            }
        } catch(SQLException  e){
            System.out.println(e.getMessage());
        }
        finally {
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
