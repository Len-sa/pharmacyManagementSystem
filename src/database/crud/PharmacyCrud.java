package database.crud;

import database.connection.ConnectionManager;

import java.sql.*;

public class PharmacyCrud {

    public static ResultSet addNewPharmacy( ) throws SQLException {
        // Executing the query
        return null;
    }

    public static void getAllPharmacy(int stdId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM pharmacy");
            resultSet = preparedStatement.executeQuery();

            System.out.println("-------------------------------------------");
            System.out.println("pharmacyId\t\tphName\t\tphCity\t\tphSubCity\t\tphKebele");
            System.out.println("--------------------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("pharmacyId");
                String phName = resultSet.getString("phName");
                String phCity = resultSet.getString("city");
                String phSubCity = resultSet.getString("subCity");
                String phKebele = resultSet.getString("kebele");

                System.out.println(id + "\t\t" + phName + "\t\t" + phCity + "\t\t" + phSubCity + "\t" + phKebele);
            }

        } catch(SQLException ex) {
            System.out.println("Error while trying to retrieve all registered pharmacies." + ex);
        } finally {
                try {
                    if(resultSet != null) {
                        resultSet.close();
                    }
                    if(preparedStatement != null) {
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error while closing resources");
                }
        }
    }
}
