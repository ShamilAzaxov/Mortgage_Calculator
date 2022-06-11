package main.sql;

import main.MortgageCalculator;
import main.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

 public class Implementation extends Connector {
     MortgageCalculator calculator;
    private List<String> tables = new ArrayList<>();

     public Implementation(MortgageCalculator calculator) {
         this.calculator = calculator;
     }
    public List<String> showTables(){
        PreparedStatement stmt;
        tables = new ArrayList<>();
        try(Connection c = connect()) {
            stmt = c.prepareStatement("create table if not exists payment_schedule_1(months int primary key auto_increment, remaining_balance varchar(20));");
            stmt.execute();
            stmt = c.prepareStatement("show table status;");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                String table = rs.getString("Name");
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    public void createTable()  {
        String sql=null;
        try(Connection c = connect()) {
            for(int i=0; i<showTables().size();) {
                if(("payment_schedule_" + (i+1)).equals(tables.get(i)))
                    i++;
                sql = "create table if not exists payment_schedule_" + (i+1)
                        + "(months int primary key auto_increment, remaining_balance varchar(20))";
            }
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTable(){
        createTable();
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into payment_schedule_" + tables.size() + "(remaining_balance) values(?)");
            for (int i=1;i<=calculator.getNumberOfPayments();i++) {
                stmt.setString(1, NumberFormat.getCurrencyInstance().format(calculator.getRemainingBalance()[i-1]));
                stmt.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}