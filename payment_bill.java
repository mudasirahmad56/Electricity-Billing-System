package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class payment_bill extends JFrame implements ActionListener {

    String meter;
    JButton back, openBrowser;

    payment_bill(String meter) {
        this.meter = meter;

        JLabel label = new JLabel("Click below to pay your bill via Paytm:");
        label.setBounds(160, 100, 300, 30);
        add(label);

        openBrowser = new JButton("Open Paytm Payment Page");
        openBrowser.setBounds(160, 140, 250, 30);
        openBrowser.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://paytm.com/online-payments"));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open browser.");
            }
        });
        add(openBrowser);

        back = new JButton("Back");
        back.setBounds(540, 20, 80, 30);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(650, 450);
        setLocation(500, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new pay_bill(meter);  // pass actual meter value
    }

    public static void main(String[] args) {
        new payment_bill("");
    }
}




