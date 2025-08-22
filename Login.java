package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {
    JTextField usertext,userpassword;
    Choice LoginChoice;
    JButton loginButton,cancelButton,signupButton;
    Login(){
        super("LOG IN ");
        getContentPane().setBackground(Color.white);
        JLabel Username=new JLabel("user name");
        Username.setBounds(300,60,100,20);
        add(Username);

        usertext=new JTextField();
        usertext.setBounds(400,60,120,20);
        add(usertext);

        JLabel password=new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        userpassword =new JTextField();
        userpassword.setBounds(400,100,120,20);
        add(userpassword);

        JLabel login= new JLabel("Log in");
        login.setBounds(300,140,100,20);
        add(login);

        LoginChoice=new Choice();
        LoginChoice.setBounds(400,140,120,20);
        add(LoginChoice);
        LoginChoice.add("Admin");
        LoginChoice.add("Customer");

        loginButton=new JButton("Login");
        loginButton.setBounds(340,180,75,23);
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton=new JButton("Cancel");
        cancelButton.setBounds(450,180,75,23);
        cancelButton.setBackground(Color.BLUE);
        cancelButton.setForeground(Color.white);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton=new JButton("Sign Up");
        signupButton.setBounds(400,220,80,23);
        signupButton.setBackground(Color.BLUE);
        signupButton.setForeground(Color.white);
        signupButton.addActionListener(this);
        add(signupButton);


        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image imageOne=imageIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(imageOne);
        JLabel imageLabel=new JLabel(imageIcon1);
        imageLabel.setBounds(2,2,250,250);
        add(imageLabel);


        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent E) {
        if (E.getSource()==loginButton){
        String user1text=usertext.getText();
        String user1pasword=userpassword.getText();
        String choice1=LoginChoice.getSelectedItem();
        try {
            DataBase db=new DataBase();
            String query="select * from user1 where username='"+user1text+"' and password='"+user1pasword+"' and loginas='"+choice1+"'";
            ResultSet  resultSet =db.statement.executeQuery(query);
            if (resultSet.next()){
                String meter=resultSet.getString("meterno");
                setVisible(false);
                new main_calass(choice1,meter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        }
        else if (E.getSource() == cancelButton) {
            setVisible(false);
        } else if (E.getSource() == signupButton) {
        setVisible(false);
        new SignUp();
        }
    }
        public static void main(String[]args){
        new Login();
    }
}
