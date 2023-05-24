package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {
    // users 테이블 select
    public void select() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String str1 = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str1 + " " + str2 + " " + str3);
        }
    }

    // show databases 로 schema check
    public void check() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    // users table 에 insert into 하기
    public void add() throws ClassNotFoundException, SQLException {
        // db 연결
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        PreparedStatement pstmt = con.prepareStatement(
                "insert into users(name, password) values (?, ?)"
        );
        pstmt.setString(1, "dori");
        pstmt.setString(2, "4321");
        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectChecker cc = new ConnectChecker();
        cc.select();
    }
}
