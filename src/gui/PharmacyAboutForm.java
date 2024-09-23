package gui;

import database.crud.PharmacyCrud;
import entities.Pharmacy;
import entities.PharmacyPhoneNumber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PharmacyAboutForm extends JFrame {
    public PharmacyAboutForm(){
        setTitle("Pharmacy Information");
        setVisible(true);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel infoJp = new JPanel();
        infoJp.setSize(300,200);
        infoJp.setLayout(new GridLayout(6,0,5,5));
        infoJp.setBorder(new EmptyBorder(20, 30, 10, 10));

        // get pharmacy detail from DB.
        Pharmacy pharmacy = PharmacyCrud.getPharmacyDetail();
        JLabel jlAbout = new JLabel("About");
        //jlAbout.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel jlpharmacyName = new JLabel("Pharmacy Name: " +pharmacy.getPharmacyName());
        JLabel jlpharmacyCity = new JLabel("City: " +pharmacy.getAddress().getCity());
        JLabel jlpharmacysubCity = new JLabel("Subcity: " +pharmacy.getAddress().getSubcity());
        JLabel jlpharmacyKebele = new JLabel("Kebele: " +pharmacy.getAddress().getKebele());

        StringBuilder sb = new StringBuilder();
        for(PharmacyPhoneNumber PharmacyphoneNumber: pharmacy.getPhoneNumbers()) {
            sb.append(PharmacyphoneNumber.getPhoneNumber()).append(",");
        }
        JLabel jlphoneNumber = new JLabel("Phone Numbers: " + sb.toString());

        infoJp.add(jlAbout);
        infoJp.add(jlpharmacyName);
        infoJp.add(jlpharmacyCity);
        infoJp.add(jlpharmacysubCity);
        infoJp.add(jlpharmacyKebele);
        infoJp.add(jlphoneNumber);
        add(infoJp,BorderLayout.CENTER);
    }
}
