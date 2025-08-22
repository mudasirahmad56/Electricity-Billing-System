package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NewCustumer extends JFrame implements ActionListener {
    JLabel heading, meterNumLabel, addressLabel, cityLabel, stateLabel, emailLabel, phoneLabel, nameLabel, meterNumberDisplay;
    JButton next, cancel;
    JTextField nameText, addressText, cityText, stateText, emailText, phoneText;

    NewCustumer() {
        super("New Customer");
        setSize(600, 500);
        setLocation(400, 130);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(209, 176, 83));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(140, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        nameLabel = new JLabel("Customer Name");
        nameLabel.setBounds(50, 80, 100, 20);
        panel.add(nameLabel);

        nameText = new JTextField();
        nameText.setBounds(180, 80, 150, 20);
        panel.add(nameText);

        meterNumLabel = new JLabel("Meter Number");
        meterNumLabel.setBounds(50, 120, 100, 20);
        panel.add(meterNumLabel);

        meterNumberDisplay = new JLabel();
        meterNumberDisplay.setBounds(180, 120, 150, 20);
        panel.add(meterNumberDisplay);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterNumberDisplay.setText("" + Math.abs(number));

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(50, 160, 100, 20);
        panel.add(addressLabel);

        addressText = new JTextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        cityLabel = new JLabel("City");
        cityLabel.setBounds(50, 200, 100, 20);
        panel.add(cityLabel);

        cityText = new JTextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        stateLabel = new JLabel("State");
        stateLabel.setBounds(50, 240, 100, 20);
        panel.add(stateLabel);

        stateText = new JTextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 280, 100, 20);
        panel.add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50, 320, 100, 20);
        panel.add(phoneLabel);

        phoneText = new JTextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230, 390, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, "West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String name = nameText.getText();
            String meter = meterNumberDisplay.getText();
            String address = addressText.getText();
            String city = cityText.getText();
            String state = stateText.getText();
            String email = emailText.getText();
            String phone = phoneText.getText();

            String query1 = "INSERT INTO customers VALUES('" + name + "','" + meter + "','" + address + "','" + city + "','" + state + "','" + email + "','" + phone + "')";
            String query2 = "INSERT INTO user1 VALUES('', '" + meter + "', '', '" + name + "', '')";

            try {
                DataBase db = new DataBase();
                db.statement.executeUpdate(query1);
                db.statement.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Customer details added successfully.");
                setVisible(false);
                new MeterInfo(meter);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustumer();
    }
}
