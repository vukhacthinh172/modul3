package org.example;


import model.Acounts;

import java.sql.*;
import java.util.Scanner;

public class Main {
    final static String driver = "com.mysql.cj.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/bank";
    final static String username = "root";
    final static String password = "vukhacthinh172";
    static Connection connection = getConection();

    public static void main(String[] args) {
        while (true) {
            System.out.println("--Menu--");
            System.out.println("1.Read");
            System.out.println("2.Create");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("0.Exit");
            System.out.print("Nhap Lua Chon : ");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice) {
                case 1:
                    Read();
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("nhap sai vui long nhap lai ");
            }
        }

    }

    public static void Read() {

        // Connection conn = getConection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select *from accounts");
            while (rs.next()) {
                Acounts acounts = new Acounts();
                acounts.setId(rs.getInt("id"));
                acounts.setAccounts_holder_id(rs.getInt("account_holder_id"));
                acounts.setBalance(rs.getDouble("balance"));
                System.out.println(acounts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        // Connection connection = getConection();
        System.out.println("nhap id can update");
        int id = new Scanner(System.in).nextInt();
        try {
            CallableStatement call = connection.prepareCall("update  accounts set account_holder_id = ?,balance = ? where id = ?");
            if (checkId(id)) {
                System.out.println("Nhập account_holder_id: ");
                int accountHolderId = new Scanner(System.in).nextInt();

                System.out.println("Nhập balance: ");
                double balance = new Scanner(System.in).nextDouble();

                call.setInt(1, accountHolderId);
                call.setDouble(2, balance);
                call.setInt(3, id);
                int count = call.executeUpdate();
                if (count > 0) {
                    System.out.println("update thanh cong");
                } else {
                    System.out.println("ko tim thay ban ghi ");
                }

            } else {
                System.out.println("khong tim thay  id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete() {
        try {
            System.out.println("nhap id muon xoa ");
            int id = new Scanner(System.in).nextInt();
            if (checkId(id)) {
                CallableStatement callableStatement = connection.prepareCall("delete from accounts where id = ?");
                callableStatement.setInt(1, id);
                int count = callableStatement.executeUpdate();
                if (count > 0) {
                    System.out.println("delete thanh cong ");
                }
            } else {
                System.out.println("khong tim thay id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create() {
        //Connection connection = getConection();
        try {
            CallableStatement callableStatement = connection.prepareCall("insert into accounts(id,account_holder_id,balance) values (?,?,?)");
            System.out.println("Nhap id");
            callableStatement.setInt(1, new Scanner(System.in).nextInt());
            System.out.println("Nhap holder_id");
            callableStatement.setInt(2, new Scanner(System.in).nextInt());
            System.out.println("Nhap balance");
            callableStatement.setInt(3, new Scanner(System.in).nextInt());
            int count = callableStatement.executeUpdate();
            if (count > 0) {
                System.out.println("Create thanh cong");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException e) {
            System.err.println("ko tim thay doi tuong tuong thich");
        } catch (SQLException e) {
            System.err.println("TU CHOI TRUY CAP ");
        }
        return connection;
    }

    public static boolean checkId(int checkId) {
        try {
            CallableStatement callableStatement = connection.prepareCall("select * from accounts where id= ?");
            callableStatement.setInt(1, checkId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}