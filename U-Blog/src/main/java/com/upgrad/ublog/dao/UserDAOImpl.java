package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO: 6.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 6.6. findByEmail() method should take email id as an input parameter and
 *  return the user details corresponding to the email id from the UBLOG_USERS table defined
 *  in the database. (Hint: You should get the connection using the DatabaseConnection class)
 * TODO: 6.7. create() method should take user details as input and insert these details
 *  into the UBLOG_USERS table. Return the same UserDTO object which was passed as an input
 *  argument. (Hint: You should get the connection using the DatabaseConnection class)
 */

public class UserDAOImpl implements UserDAO {

    private static final UserDAOImpl userDAO = new UserDAOImpl();
    private UserDAOImpl() {}

    public static UserDAOImpl getInstance() {
        return userDAO;
    }

    @Override
    public UserDTO create(UserDTO userDTO) throws SQLException {
        System.out.println(userDTO);
        String sql = "INSERT INTO UBLOG_USERS (email_id, password) VALUES ('"+userDTO.getEmailId()+"', '"+ userDTO.getPassword() + "')";
        System.out.println("Step 1");
        Connection connection = DatabaseConnection.getInstance();
        System.out.println("Step 2");
        Statement statement = connection.createStatement();
        System.out.println("Step 3");
        if (statement.execute(sql)) {
            return userDTO;
        }
        return null;
    }

    @Override
    public UserDTO findByEmail(String emailId) throws SQLException {
        Connection connection = DatabaseConnection.getInstance();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM UBLOG_USERS WHERE email_id = '" + emailId + "'");
        if (resultSet.next())
            return new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        return null;
    }
    public static void main(String[] args) {
        try {
//			UserDAO userDAO = UserDAOImpl.getInstance();
            UserDTO temp = new UserDTO();
            temp.setUserId(1);
            temp.setEmailId("temp@temp.temp");
            temp.setPassword("temp");
            System.out.println(userDAO);
//			userDAO.create(temp);
            System.out.println(userDAO.findByEmail("temp@temp.temp"));
        } catch (Exception e) {
            System.out.println("FAILED");
        }

        // Following should be the desired output of the main method.
        // UserDTO{userId=11, emailId='temp@temp.temp', password='temp'}
    }
}
