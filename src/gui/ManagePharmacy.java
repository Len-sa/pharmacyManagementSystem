package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagePharmacy extends JFrame {
    public ManagePharmacy() {
        setTitle("OUR PHARMACY");
        setVisible(true);
        setLayout(new GridLayout(3,2,10,10));
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setSize(400,250);
        jPanel.setLayout(new GridLayout(1,2,10,10));

        JButton infoBtn = new JButton("About");
        infoBtn.setSize(40,40);
        infoBtn.setOpaque(true);

        JButton addPharmacy = new JButton("Add New");
        addPharmacy.setSize(40,40);
        addPharmacy.setOpaque(true);

        JButton updatePharmacy = new JButton("UPDATE");
        updatePharmacy.setSize(40,40);
        addPharmacy.setOpaque(true);

        //adding buttons to panel
        jPanel.add(addPharmacy);
        jPanel.add(updatePharmacy);
        jPanel.add(infoBtn);


        add(jPanel,BorderLayout.CENTER);

        addPharmacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PharmacyForm();
            }
        });

        updatePharmacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new PharmacyUpdateForm();
            }
        });
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PharmacyAboutForm();
            }
        });
        pack();
    }
}
