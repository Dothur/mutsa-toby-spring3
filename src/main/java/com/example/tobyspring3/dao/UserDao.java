package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;
    public UserDao() {
        this.connectionMaker = new DConnectionMaker();
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close(); // 다 쓰고 닫아줘야함
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("8");
        user.setName("kyeongrok8");
        user.setPassword("12345232");
        userDao.add(user);

        User selectedUser = userDao.get("8");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
