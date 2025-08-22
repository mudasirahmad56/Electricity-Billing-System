package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Custumer_detail extends JFrame implements ActionListener {
    Choice searchmeterchoice, namechoice;
    JTable table;
    JButton search,print,close;
    Custumer_detail(){
        super("Custumer Detail");
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
            ResultSet resultSet=db.statement.executeQuery("select*from customers");
            while (resultSet.next()){
                searchmeterchoice.add(resultSet.getString("Meter"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchname =new JLabel("Search by Name");
        searchname.setBounds(430,26,120,20);
        add(searchname);

        namechoice=new Choice();
        namechoice.setBounds(550,26,150,20);
        add(namechoice);

        try {
            DataBase db=new DataBase();
            ResultSet resultSet=db.statement.executeQuery("select*from customers");
            while (resultSet.next()){
                namechoice.add(resultSet.getString("Name"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        table=new JTable();
        try {
            DataBase db=new DataBase();
            ResultSet resultSet=db.statement.executeQuery("select*from customers");
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
            String search_query="select*from customers where Meter='"+searchmeterchoice.getSelectedItem()+"' and Name='"+namechoice.getSelectedItem()+"'";

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
        new Custumer_detail();
    }
}
