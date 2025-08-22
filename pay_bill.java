package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {
    String meter;
    JButton pay, back;
    Choice monthchoice;
    JLabel unittext, totalbilltext, staustext;

    pay_bill(String meter) {
        this.meter = meter;
        setSize(800, 500);
        setLocation(300, 130);
        setLayout(null);

        JLabel heading = new JLabel("Pay_Bill");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 80, 100, 20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(150, 80, 100, 20);
        add(meterText);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 130, 100, 20);
        add(name);

        JLabel nametext = new JLabel("");
        nametext.setBounds(150, 130, 200, 20);
        add(nametext);

        JLabel searchmonth = new JLabel("Month");
        searchmonth.setBounds(30, 180, 120, 20);
        add(searchmonth);

        monthchoice = new Choice();
        monthchoice.add("January");
        monthchoice.add("February");
        monthchoice.add("March");
        monthchoice.add("April");
        monthchoice.add("May");
        monthchoice.add("June");
        monthchoice.add("July");
        monthchoice.add("August");
        monthchoice.add("September");
        monthchoice.add("October");
        monthchoice.add("November");
        monthchoice.add("December");
        monthchoice.setBounds(150, 180, 130, 20);
        add(monthchoice);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(30, 230, 140, 20);
        add(unit);

        unittext = new JLabel();
        unittext.setBounds(150, 230, 140, 20);
        add(unittext);

        JLabel totalbill = new JLabel("Total Bill");
        totalbill.setBounds(30, 280, 140, 20);
        add(totalbill);

        totalbilltext = new JLabel();
        totalbilltext.setBounds(150, 280, 140, 20);
        add(totalbilltext);

        JLabel status = new JLabel("Status");
        status.setBounds(30, 330, 140, 20);
        add(status);

        staustext = new JLabel("");
        staustext.setBounds(150, 330, 140, 20);
        add(staustext);

        try {
            DataBase db = new DataBase();
            ResultSet resultSet = db.statement.executeQuery("SELECT * FROM customers WHERE Meter='" + meter + "'");
            while (resultSet.next()) {
                meterText.setText(meter);
                nametext.setText(resultSet.getString("Name"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        monthchoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateBillDetails();
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(100, 400, 80, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(200, 400, 80, 25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    private void updateBillDetails() {
        try {
            DataBase db = new DataBase();
            String selectedMonth = monthchoice.getSelectedItem();
            ResultSet resultSet = db.statement.executeQuery("SELECT * FROM Bill WHERE meter_no='" + meter + "' AND month='" + selectedMonth + "'");
            while (resultSet.next()) {
                unittext.setText(resultSet.getString("unit"));
                totalbilltext.setText(resultSet.getString("total_bill"));
                staustext.setText(resultSet.getString("status"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            try {
                String selectedMonth = monthchoice.getSelectedItem();
                DataBase db = new DataBase();
                db.statement.executeUpdate("UPDATE Bill SET status='Paid' WHERE meter_no ='" + meter + "' AND month = '" + selectedMonth + "'");
                staustext.setText("Paid"); // update label
                JOptionPane.showMessageDialog(null, "Bill Paid Successfully");
            } catch (Exception E) {
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("meter");
    }
}
