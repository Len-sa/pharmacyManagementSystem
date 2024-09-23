package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportGenerator extends JFrame {

    public ReportGenerator() {
        setTitle("Report Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // You can add buttons and tables to display the report
        JLabel reportLabel = new JLabel("Report Section Coming Soon!", JLabel.CENTER);
        getContentPane().add(reportLabel, BorderLayout.CENTER);

        setVisible(true);
    }
}

