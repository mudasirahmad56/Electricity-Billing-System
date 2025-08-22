package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash(){
        super("Welcome to Electricity Billing System");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/1.jpeg.jpg"));
        Image imageOne=imageIcon.getImage().getScaledInstance(700,500,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(imageOne);
        JLabel imageLabel=new JLabel(imageIcon1);
        add(imageLabel);

    setSize(700,500);
    setLocation(400,160);
    setVisible(true);

    try {
        Thread.sleep(3000);
        new Login();
        setVisible(false);
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    public static void main(String[] args) {

        new Splash();
    }
}
