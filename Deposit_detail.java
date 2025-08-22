package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Deposit_detail extends JFrame implements ActionListener {
    Choice searchmeterchoice, monthchoice;
    JTable table;
    JButton search,print,close;
    Deposit_detail(){
        super("Deposit Detail");
        getContentPane().setBackground(new Color(191, 170, 142));
        setSize(750,500);
        setLocation(320,160);
        setLayout(null);

        JLabel searchmeter =new JLabel("Search by Meter Number");
        searchmeter.setBounds(22,26,150,20);
        add(searchmeter);

        searchmeterchoice=new Choice();
        searchmeterchoice.setBounds(170,26,150,20);
        add(searchmeterchoice);

        try {
            DataBase db=new DataBase();
            ResultSet resultSet=db.statement.executeQuery("select*from Bill");
            while (resultSet.next()){
                searchmeterchoice.add(resultSet.getString("meter_no"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchmonth =new JLabel("Search by Month");
        searchmonth.setBounds(430,26,120,20);
        add(searchmonth);

        monthchoice=new Choice();
        monthchoice.add("Janurary");
        monthchoice.add("Februry");
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
        monthchoice.setBounds(550,26,150,20);
        add(monthchoice);



        table=new JTable();
        try {
            DataBase db=new DataBase();
            ResultSet resultSet=db.statement.executeQuery("select*from Bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,750,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        search =new JButton("Search");
        search.setBounds(22,66,80,22);
        search.setBackground(Color.white);
        search.addActionListener(this);
        add(search);

        print =new JButton("Print");
        print.setBounds(140,66,80,22);
        print.setBackground(Color.white);
        print.addActionListener(this);
        add(print);

        close =new JButton("Close");
        close.setBounds(650,66,80,22);
        close.setBackground(Color.white);
        close.addActionListener(this);
        add(close);



        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            String search_query="select*from Bill where meter_no='"+searchmeterchoice.getSelectedItem()+"' and month='"+monthchoice.getSelectedItem()+"'";

            try {
                DataBase db=new DataBase();
                ResultSet resultSet=db.statement.executeQuery(search_query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            }catch (Exception E){
                E.printStackTrace();
            }
        }else if (e.getSource()==print){
            try {
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }

    }


    public static void main(String[] args) {
      new  Deposit_detail();
    }
}
