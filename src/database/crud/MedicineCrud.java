package database.crud;

import database.connection.ConnectionManager;
import entities.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineCrud {
    public static boolean addMedicine(Medicine medicine) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            String query = "Insert into medicine (medName, brand, manufacturedDate, expiryDate, sellingPrice, stockQuantity) values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setString(2, medicine.getMedicineBrand());
            preparedStatement.setString(3, medicine.getManufacturedDate());
            preparedStatement.setString(4, medicine.getExpiredDate());
            preparedStatement.setDouble(5, medicine.getSellingPrice());
            preparedStatement.setInt(6, medicine.getStockQuantity());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error while Registering Medicine" + ex.getMessage());
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

    public static Medicine getMedicineById(int medId) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("Select * From medicine where medId = ?");
            preparedstatement.setInt(1, medId);
            resultSet = preparedstatement.executeQuery();

            resultSet.next(); // there is only 1 medicine with a specific ID.
            int id = resultSet.getInt("medId");
            String medicineName = resultSet.getString("medName");
            String brand = resultSet.getString("brand");
            String manufacturedDate = resultSet.getString("manufacturedDate");
            String expiryDate = resultSet.getString("expiryDate");
            double sellingPrice = Double.parseDouble(resultSet.getString("sellingPrice"));
            int stockQuantity = resultSet.getInt("stockQuantity");

            Medicine medicine = new Medicine(medicineName, brand, manufacturedDate, expiryDate, sellingPrice, stockQuantity);
            medicine.setMedicineId(id);
            return medicine;

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

    public static List<String> searchByName(String searchText) {
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<String> result = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("Select * From medicine where medName like ?");
            preparedstatement.setString(1, searchText + "%");
            resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                int medId = resultSet.getInt("medId");
                String medName = resultSet.getString("medName");
                String expiryDate = resultSet.getString("expiryDate");
                result.add(medName + " EXP:" + expiryDate + " ID#" + medId);
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

    public static boolean updateMedicine(Medicine medicine) {
        PreparedStatement updatePreparedStatement = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("Update medicine SET medName = ?, brand = ?, manufacturedDate = ?, expiryDate = ?, sellingPrice = ?, stockQuantity = ? where medId = ?");
            updatePreparedStatement.setString(1, medicine.getMedicineName());
            updatePreparedStatement.setString(2, medicine.getMedicineBrand());
            updatePreparedStatement.setString(3, medicine.getManufacturedDate());
            updatePreparedStatement.setString(4, medicine.getExpiredDate());
            updatePreparedStatement.setDouble(5, medicine.getSellingPrice());
            updatePreparedStatement.setInt(6, medicine.getStockQuantity());
            updatePreparedStatement.setInt(7, medicine.getMedicineId());

            int rowAffected = updatePreparedStatement.executeUpdate();
            return rowAffected == 1;

        } catch (SQLException e) {
            System.out.println("Error while trying update a Medicine " + e);
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

    public static boolean updateMedicineQuantity(int medId, int quantityToReduce) {
        PreparedStatement updatePreparedStatement = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("UPDATE medicine SET stockQuantity = stockQuantity - ? WHERE medId = ?");
            updatePreparedStatement.setInt(1, quantityToReduce);
            updatePreparedStatement.setInt(2, medId);

            int rowAffected = updatePreparedStatement.executeUpdate();
            return rowAffected == 1;

        } catch (SQLException e) {
            System.out.println("Error while trying update a Medicine quantity" + e.getMessage());
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


    public static boolean deleteMedicine(int medId) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedstatement = connection.prepareStatement("DELETE FROM medicine WHERE medId = ?");
            preparedstatement.setInt(1, medId);
            int rowsAffected = preparedstatement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error while deleting medicine: " + e.getMessage());
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
}

