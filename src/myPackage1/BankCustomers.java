package myPackage1;
import java.sql.*;
import java.util.*;

public class BankCustomers {
    public static Connection getConnection(){
        Connection con = null;
        try{
            String url = "jdbc:mysql://localhost:3306/bankcustomers";
            String user = "root";
            String pass = "Suh@ibkh0n!";
            con = DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
    public static void insertCustomers(String accNumber,String cusName,double Balance,String cusAdd){
        String query = "INSERT INTO Customers (accNumber,cusName,Balance,cusAdd) VALUES (?, ?, ?, ?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            con.setAutoCommit(false);
            ps.setString(1,accNumber);
            ps.setString(2,cusName);
            ps.setDouble(3,Balance);
            ps.setString(4,cusAdd);
            ps.addBatch();
            int[] rows = ps.executeBatch();
            con.commit();
            System.out.println(rows.length+": records inserted Successfully.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void fetchCustomers(){
        String query = "SELECT *FROM Customers";
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            System.out.println("-------------Customers Records------------");
            System.out.println("------------------------------------------");
            while (rs.next()){
                String accNumber = rs.getString("accNumber");
                String cusName   = rs.getString("cusName");
                double balance   = rs.getDouble("Balance");
                String cusAdd    = rs.getString("cusAdd");
                System.out.println(accNumber+" | "+cusName+" | "+balance+" | "+cusAdd);
            }
            System.out.println("------------------------------------------");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void fetchSpecificCustomer(String accountNumber){
        String query = "SELECT * FROM Customers WHERE accNumber = ?";
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,accountNumber);
            ResultSet rs   = ps.executeQuery();
            System.out.println("-------------Customers Records------------");
            System.out.println("------------------------------------------");
            boolean found = false;
            while (rs.next()){
                found = true;
                String accNumber = rs.getString("accNumber");
                String cusName   = rs.getString("cusName");
                double balance   = rs.getDouble("Balance");
                String cusAdd    = rs.getString("cusAdd");
                System.out.println(accNumber+" | "+cusName+" | "+balance+" | "+cusAdd);
            }if(!found){
                System.out.println("No record found for account number : "+accountNumber);
            }
            System.out.println("------------------------------------------");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void updateCustomers(String accNumber,double newBalance){
        String query = "UPDATE Customers SET Balance = ? WHERE accNumber = ?";
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1,newBalance);
            ps.setString(2,accNumber);
            int rows = ps.executeUpdate();
            System.out.println(rows+": record updated Successfully.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteCustomers(String accNumber){
        String query = "DELETE FROM Customers WHERE accNumber = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,accNumber);
            int rows = ps.executeUpdate();
            System.out.println(rows+": record deleted Successfully.");
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    public static void createBankAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account number  : ");
        String accN =sc.nextLine();
        System.out.print("Enter customer name   : ");
        String cusN =sc.nextLine();
        System.out.print("Enter account balance : ");
        double accB =sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter customer address : ");
        String cusAd =sc.nextLine();
        insertCustomers(accN,cusN,accB,cusAd);
    }
    public static void main(String[] args){
        fetchSpecificCustomer("ACC12345678");
    }
}
