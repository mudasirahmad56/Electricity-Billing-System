package electricity.billing.system;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {
    Choice loginChoice;
    JTextField meterText, employeeText, usernameText, nameText,passwordText;
    JButton createButton, backButton;
    JLabel meterLabel, employeeLabel;

    SignUp() {
        super("Sign Up");
        getContentPane().setBackground(new Color(15, 170, 163));
        setLayout(null);

        JLabel createLabel = new JLabel("Create Account As");
        createLabel.setBounds(20, 30, 130, 20);
        add(createLabel);

        loginChoice = new Choice();
        loginChoice.setBounds(160, 30, 120, 20);
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        add(loginChoice);

        meterLabel = new JLabel("Meter No");
        meterLabel.setBounds(20, 85, 120, 20);
        meterLabel.setVisible(false);
        add(meterLabel);

        meterText = new JTextField();
        meterText.setBounds(160, 85, 120, 20);
        meterText.setVisible(false);
        add(meterText);

        employeeLabel = new JLabel("Employee ID");
        employeeLabel.setBounds(20, 85, 120, 20);
        add(employeeLabel);

        employeeText = new JTextField();
        employeeText.setBounds(160, 85, 120, 20);
        add(employeeText);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(20, 120, 120, 20);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(160, 120, 120, 20);
        add(usernameText);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 155, 120, 20);
        add(nameLabel);

        nameText = new JTextField("");
        nameText.setBounds(160, 155, 120, 20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    DataBase db= new DataBase();
                    ResultSet resultSet=db.statement.executeQuery("select*from user1 where meterno='"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }
                }catch(Exception E){
                    E.printStackTrace();

                }
            }
        });

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 195, 120, 20);
        add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setBounds(160, 195, 120, 20);
        add(passwordText);

        loginChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String  user=loginChoice.getSelectedItem();
                if (user.equals("Customer")) {
                    employeeLabel.setVisible(false);
                    nameText.setEditable(false);
                    meterLabel.setVisible(true);
                    meterText.setVisible(true);


                }
                else if (user.equals("Admin")){
                    employeeLabel.setVisible(true);
                    employeeText.setVisible(true);
                    meterLabel.setVisible(false);
                    meterLabel.setVisible(false);
                }
            }
        });


        createButton = new JButton("Create");
        createButton.setBounds(30, 260, 75, 30);
        createButton.setBackground(new Color(12, 73, 230));
        createButton.setForeground(Color.white);
        createButton.addActionListener(this);
        add(createButton);

        backButton = new JButton("Back");
        backButton.setBounds(175, 260, 75, 30);
        backButton.setBackground(new Color(12, 73, 230));
        backButton.addActionListener(this);
        backButton.setForeground(Color.white);
        add(backButton);


            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
            Image img = icon.getImage().getScaledInstance(210, 210, Image.SCALE_DEFAULT);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(310, 60, 210, 210);
            add(imgLabel);


        setSize(600, 380);
        setLocation(500, 200);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent E) {
        if (E.getSource()==createButton){
        String schoice=loginChoice.getSelectedItem();
        String susername=usernameText.getText();
        String sname=nameText.getText();
        String spassword=passwordText.getText();
        String smeterno=meterText.getText();
        try {
        DataBase db= new DataBase();
        String query=null;
            if (schoice.equals("Admin")) {
                query = "INSERT INTO user1 (loginas, meterno, username, name, password) " +
                        "VALUES('" + schoice + "','" + smeterno + "','" + susername + "','" + sname + "','" + spassword + "')";
            } else {
                // Check if this meter number exists already
                ResultSet rs = db.statement.executeQuery("SELECT * FROM user1 WHERE meterno='" + smeterno + "'");
                if (rs.next()) {
                    // Update if exists
                    query = "UPDATE user1 SET username='" + susername + "', password='" + spassword + "', loginas='" + schoice + "' WHERE meterno='" + smeterno + "'";
                } else {
                    // Insert if not exists
                    query = "INSERT INTO user1 (loginas, meterno, username, name, password) " +
                            "VALUES('" + schoice + "','" + smeterno + "','" + susername + "','" + sname + "','" + spassword + "')";
                }
            }
        db.statement.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Are Successfully Created ");
        setVisible(false);
        new Login();
        }catch (Exception a){
        a.printStackTrace();
        }

        }
        else if (E.getSource()==backButton){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
