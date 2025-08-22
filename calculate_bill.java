//package electricity.billing.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.sql.ResultSet;
//
//public class calculate_bill extends JFrame implements ActionListener {
//    Choice meternumchoice,monthchoice;
//    JLabel nametext,addresstext;
//    JTextField unittext;
//    JButton submit,cancel;
//    calculate_bill(){
//
//        JPanel panel=new JPanel();
//        panel.setLayout(null);
//        panel.setBackground(new Color(192, 209, 192));
//        add(panel);
//
//        JLabel heading= new JLabel("Calculate Electricity Bill");
//        heading.setBounds(50,25,200,20);
//        heading.setFont(new Font("Tahoma", Font.BOLD, 17));
//        panel.add(heading);
//
//        JLabel meternumber =new JLabel("Meter Number");
//        meternumber.setBounds(20,80,140,20);
//        panel.add(meternumber);
//
//        meternumchoice=new Choice();
//        try {
//            DataBase db=new DataBase();
//            ResultSet resultSet= db.statement.executeQuery("select*from customers");
//            while (resultSet.next()){
//                meternumchoice.add(resultSet.getString("Meter"));
//            }
//        }catch (Exception E){
//            E.printStackTrace();
//        }
//        meternumchoice.setBounds(160,80,100,20);
//        panel.add(meternumchoice);
//
//        JLabel name=new JLabel("Name");
//        name.setBounds(20,110,140,20);
//        panel.add(name);
//
//        nametext=new JLabel("");
//        nametext.setBounds(160,110,140,20);
//        panel.add(nametext);
//
//        JLabel address=new JLabel("Address");
//        address.setBounds(20,140,140,20);
//        panel.add(address);
//
//        addresstext=new JLabel("");
//        addresstext.setBounds(160,140,140,20);
//        panel.add(addresstext);
//
//        try{
//            DataBase db=new DataBase();
//            ResultSet resultSet=db.statement.executeQuery("select*from customers where Meter='"+meternumchoice.getSelectedItem()+"'");
//            while (resultSet.next()){
//                nametext.setText(resultSet.getString("name"));
//                addresstext.setText(resultSet.getString("address"));
//            }
//        }catch (Exception E){
//            E.printStackTrace();
//        }
//
//        meternumchoice.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                try{
//                    DataBase db=new DataBase();
//                    ResultSet resultSet=db.statement.executeQuery("select*from customers where Meter='"+meternumchoice.getSelectedItem()+"'");
//                    while (resultSet.next()){
//                        nametext.setText(resultSet.getString("name"));
//                        addresstext.setText(resultSet.getString("address"));
//                    }
//                }catch (Exception E){
//                    E.printStackTrace();
//                }
//
//            }
//        });
//
//        JLabel unitconsumed=new JLabel("Unit Consumed");
//        unitconsumed.setBounds(20,170,140,20);
//        panel.add(unitconsumed);
//
//        unittext=new JTextField();
//        unittext.setBounds(160,170,140,20);
//        panel.add(unittext);
//
//        JLabel month = new JLabel("Month");
//        month.setBounds(20,200,140,20);
//        panel.add(month);
//
//        monthchoice=new Choice();
//        monthchoice.add("Janurary");
//        monthchoice.add("Februry");
//        monthchoice.add("March");
//        monthchoice.add("April");
//        monthchoice.add("May");
//        monthchoice.add("June");
//        monthchoice.add("July");
//        monthchoice.add("August");
//        monthchoice.add("September");
//        monthchoice.add("October");
//        monthchoice.add("November");
//        monthchoice.add("December");
//        monthchoice.setBounds(160,200,140,20);
//        panel.add(monthchoice);
//
//        submit=new JButton("Submit");
//        submit.setBounds(85,250,80,25);
//        submit.setBackground(new Color(9, 38, 161));
//        submit.setForeground(new Color(255, 255, 255));
//        submit.addActionListener(this);
//        panel.add(submit);
//
//        cancel=new JButton("Cancel");
//        cancel.setBounds(190,250,80,25);
//        cancel.setBackground(new Color(9, 38, 161));
//        cancel.setForeground(new Color(255, 255, 255));
//        cancel.addActionListener(this);
//        panel.add(cancel);
//
//        setLayout(new BorderLayout());
//        add(panel, "Center");
//
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
//        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel imgLabel = new JLabel(i3);
//        add(imgLabel, "East");
//
//
//        setSize(600,350);
//        setLocation(500,130);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource()==submit){
//            String smeterno=meternumchoice.getSelectedItem();
//            String sunit=unittext.getText();
//            String smonth=monthchoice.getSelectedItem();
//
//            int total_bill=0;
//            int units=Integer.parseInt(sunit);
//            String query_tax="select * from Tax";
//            try {
//                DataBase db=new DataBase();
//                ResultSet resultSet=db.statement.executeQuery(query_tax);
//                while (resultSet.next()){
//                    total_bill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
//                    total_bill += Integer.parseInt(resultSet.getString("meter_rent"));
//                    Integer.parseInt(resultSet.getString("service_charge"));
//                    Integer.parseInt(resultSet.getString("General_Sales_Tax"));
//                    Integer.parseInt(resultSet.getString("fixed_tax"));
//                }
//            }catch (Exception E){
//                E.printStackTrace();
//            }
//            String query_total_tax="insert into Bill values('"+smeterno+"','"+smonth+"','"+sunit+"','"+total_bill+"','Not Paid')";
//            try {
//                DataBase db=new DataBase();
//                db.statement.executeUpdate(query_total_tax);
//                JOptionPane.showMessageDialog(null,"Custumer Bill Updated Succesfuly");
//                setVisible(false);
//            }catch (Exception E){
//                E.printStackTrace();
//            }
//        }
//
//    else {
//        setVisible(false);
//        }
//    }
//
//    public static void main(String[] args) {
//        new calculate_bill();
//    }
//}


package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {

    Choice meternumchoice, monthchoice;
    JLabel nametext, addresstext;
    JTextField unittext;
    JButton submit, cancel;

    calculate_bill() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(192, 209, 192));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(50, 25, 250, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(heading);

        JLabel meternumber = new JLabel("Meter Number");
        meternumber.setBounds(20, 80, 140, 20);
        panel.add(meternumber);

        meternumchoice = new Choice();
        try {
            DataBase db = new DataBase();
            ResultSet resultSet = db.statement.executeQuery("SELECT * FROM customers");
            while (resultSet.next()) {
                meternumchoice.add(resultSet.getString("Meter"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        meternumchoice.setBounds(160, 80, 100, 20);
        panel.add(meternumchoice);

        JLabel name = new JLabel("Name");
        name.setBounds(20, 110, 140, 20);
        panel.add(name);

        nametext = new JLabel("");
        nametext.setBounds(160, 110, 140, 20);
        panel.add(nametext);

        JLabel address = new JLabel("Address");
        address.setBounds(20, 140, 140, 20);
        panel.add(address);

        addresstext = new JLabel("");
        addresstext.setBounds(160, 140, 140, 20);
        panel.add(addresstext);

        // Load name/address for the first meter by default
        try {
            DataBase db = new DataBase();
            ResultSet resultSet = db.statement.executeQuery("SELECT * FROM customers WHERE Meter='" + meternumchoice.getSelectedItem() + "'");
            if (resultSet.next()) {
                nametext.setText(resultSet.getString("name"));
                addresstext.setText(resultSet.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        meternumchoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    DataBase db = new DataBase();
                    ResultSet resultSet = db.statement.executeQuery("SELECT * FROM customers WHERE Meter='" + meternumchoice.getSelectedItem() + "'");
                    if (resultSet.next()) {
                        nametext.setText(resultSet.getString("name"));
                        addresstext.setText(resultSet.getString("address"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(20, 170, 140, 20);
        panel.add(unitconsumed);

        unittext = new JTextField();
        unittext.setBounds(160, 170, 140, 20);
        panel.add(unittext);

        JLabel month = new JLabel("Month");
        month.setBounds(20, 200, 140, 20);
        panel.add(month);

        monthchoice = new Choice();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        for (String m : months) {
            monthchoice.add(m);
        }
        monthchoice.setBounds(160, 200, 140, 20);
        panel.add(monthchoice);

        submit = new JButton("Submit");
        submit.setBounds(85, 250, 80, 25);
        submit.setBackground(new Color(9, 38, 161));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(190, 250, 80, 25);
        cancel.setBackground(new Color(9, 38, 161));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, BorderLayout.EAST);

        setSize(600, 350);
        setLocation(500, 130);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterno = meternumchoice.getSelectedItem();
            String sunit = unittext.getText();
            String smonth = monthchoice.getSelectedItem();

            if (sunit.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter units consumed");
                return;
            }

            int total_bill = 0;
            int units;

            try {
                units = Integer.parseInt(sunit);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid unit value");
                return;
            }

            try {
                DataBase db = new DataBase();
                ResultSet resultSet = db.statement.executeQuery("SELECT * FROM Tax");
                if (resultSet.next()) {
                    total_bill += units * resultSet.getInt("cost_per_unit");
                    total_bill += resultSet.getInt("meter_rent");
                    total_bill += resultSet.getInt("service_charge");
                    total_bill += resultSet.getInt("General_Sales_Tax");
                    total_bill += resultSet.getInt("fixed_tax");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String query_total_tax = "INSERT INTO Bill (meter_no, month, units, total_amount, status) " +
                    "VALUES('" + smeterno + "', '" + smonth + "', '" + units + "', '" + total_bill + "', 'Not Paid')";

            try {
                DataBase db = new DataBase();
                db.statement.executeUpdate(query_total_tax);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}
