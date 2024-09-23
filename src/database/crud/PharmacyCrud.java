package database.crud;

import database.connection.ConnectionManager;
import entities.Address;
import entities.Pharmacy;
import entities.PharmacyPhoneNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacyCrud {

    public static ResultSet addNewPharmacy(int x) throws SQLException {
        // Executing the query
        return null;
    }

    public static Pharmacy getPharmacyDetail() {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        Pharmacy pharmacy = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT TOP 1 * FROM pharmacy");
            resultSet = preparedStatement.executeQuery();

            // we only have one pharmacy
            resultSet.next();
            int id = resultSet.getInt("pharmacyId");
            String phName = resultSet.getString("phName");
            String phCity = resultSet.getString("city");
            String phSubCity = resultSet.getString("subCity");
            int phKebele = resultSet.getInt("kebele");
            Address address = new Address(phKebele, phCity, phSubCity);

            List<PharmacyPhoneNumber> phoneNumbers = getPharmacyPhoneNumbers(id);
            pharmacy = new Pharmacy(phName, address, phoneNumbers);
            pharmacy.setPharmacyId(id);

            return pharmacy;
        } catch (SQLException e) {
            System.out.println("Error while trying to retrieve all registered pharmacies." + e);
        } finally {
           try {
               if (resultSet != null) {
                    resultSet.close();
             }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources");
            }
        }
        return pharmacy;
    }

    public static boolean insertPharmacy(Pharmacy pharmacy){
        Connection connection;
        PreparedStatement selectPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.getConnection();

            // Before inserting a Pharmacy make sure there is no pharmacy info registered. We only allow one pharmacy registration
            selectPreparedStatement = connection.prepareStatement("SELECT count(*) as count FROM pharmacy");
            resultSet = selectPreparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");
            if (count > 0) {
                return false;
            }

            // insert pharmacy
            insertPreparedStatement = connection.prepareStatement("INSERT INTO pharmacy (phName, city, subCity, kebele)  VALUES (?,?,?,?)");
            insertPreparedStatement.setString(1, pharmacy.getPharmacyName());
            insertPreparedStatement.setString(2, pharmacy.getAddress().getCity());
            insertPreparedStatement.setString(3, pharmacy.getAddress().getSubcity());
            insertPreparedStatement.setInt(4, pharmacy.getAddress().getKebele());
            int rowsInserted =insertPreparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error while trying to retrieve all registered pharmacies." + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (selectPreparedStatement != null) {
                    selectPreparedStatement.close();
                }
                if(insertPreparedStatement != null){
                    insertPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources");
            }
        }
        return false;
    }
    public static boolean updatePharmacy(Pharmacy pharmacy){
        PreparedStatement updatePreparedStatement = null;
        PreparedStatement deletePhoneNumberStatement = null;
        PreparedStatement insertPhoneNumberStatement = null;

        try {
            Connection connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("Update pharmacy SET phName = ?, city = ?, subCity = ?, kebele = ?");
            updatePreparedStatement.setString(1, pharmacy.getPharmacyName());
            updatePreparedStatement.setString(2, pharmacy.getAddress().getCity());
            updatePreparedStatement.setString(3,pharmacy.getAddress().getSubcity());
            updatePreparedStatement.setInt(4, pharmacy.getAddress().getKebele());
            int rowAffected =  updatePreparedStatement.executeUpdate();

            if (rowAffected  == 1) {
                // update also the phone number by deleting them all and re-inserting
                deletePhoneNumberStatement = connection.prepareStatement("DELETE FROM pharmacyPhoneNumber WHERE pharmacyId = ?");
                deletePhoneNumberStatement.setInt(1, pharmacy.getPharmacyId());
                deletePhoneNumberStatement.executeUpdate();

                if (pharmacy.getPhoneNumbers() != null && !pharmacy.getPhoneNumbers().isEmpty()) {
                    insertPhoneNumberStatement = connection.prepareStatement("INSERT INTO pharmacyPhoneNumber VALUES (?,?)");
                    for (PharmacyPhoneNumber pharmacyPhoneNumber :  pharmacy.getPhoneNumbers()) {
                        insertPhoneNumberStatement.setInt(1, pharmacyPhoneNumber.getPharmacyId());
                        insertPhoneNumberStatement.setString(2, pharmacyPhoneNumber.getPhoneNumber());
                        insertPhoneNumberStatement.addBatch();
                    }
                    int[] rowsAffected = insertPhoneNumberStatement.executeBatch();
                    return rowsAffected.length == pharmacy.getPhoneNumbers().size();
                }
            }
            return rowAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error while trying update a pharmacy " + e);
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

    public static List<PharmacyPhoneNumber> getPharmacyPhoneNumbers(int pharmacyId) {
        PreparedStatement phoneNumbersPrepareStmt = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            phoneNumbersPrepareStmt = connection.prepareStatement("SELECT * FROM pharmacyPhoneNumber WHERE pharmacyId = ? ");
            phoneNumbersPrepareStmt.setInt(1, pharmacyId);
            resultSet = phoneNumbersPrepareStmt.executeQuery();

            List<PharmacyPhoneNumber> phoneNumbers = new ArrayList<>();
            while (resultSet.next()) {
                phoneNumbers.add(new PharmacyPhoneNumber(pharmacyId, resultSet.getString("phoneNumber")));
            }
            return phoneNumbers;
        } catch (SQLException ex) {
            System.out.println("error while retrieving phrmscy phone numbers");
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(phoneNumbersPrepareStmt != null) {
                    phoneNumbersPrepareStmt.close();
                }
            } catch(SQLException  e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static boolean insertPharmacyPhoneNumbers(List<PharmacyPhoneNumber> pharmacyPhoneNumbers, int pharmacyId) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO pharmacyPhoneNumber VALUES (?,?)");
            for(PharmacyPhoneNumber phoneNumber : pharmacyPhoneNumbers){
                preparedStatement.setInt(1, pharmacyId);
                preparedStatement.setString(2, phoneNumber.getPhoneNumber());
                preparedStatement.addBatch();
            }
            int[] affectedRows = preparedStatement.executeBatch();
            return affectedRows.length == pharmacyPhoneNumbers.size();

        } catch (SQLException ex){
            System.out.println("Error while trying to insert phone numbers for a paharmcy");
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            }catch (SQLException ex) {

            }
        }
        return false;
    }
}
