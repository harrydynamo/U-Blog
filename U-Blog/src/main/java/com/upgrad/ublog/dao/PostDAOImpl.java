package com.upgrad.ublog.dao;

/**
 * TODO: 6.19. Implement the PostsDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  PostDAOImpl and a public static getInstance() method which returns the instance attribute.)
 *   Note: getPostDAO() method of the DAOFactory should return the PostDAOImpl object using
 *   getInstance() method of the PostDAOImpl class
 * TODO: 6.20. Define the following methods and return null for each of them. You will provide a
 *  proper implementation for each of these methods, later in this project.
 *  a. findByEmail()
 *  b. findByTag()
 *  c. findAllTags()
 *  d. findById()
 *  e. deleteById() (return false for this method for now)
 * TODO: 6.21. create() method should take post details as input and insert these details into
 *  the UBLOG_POSTS table. Return the same PostDTO object which was passed as an input argument.
 *  (Hint: You should get the connection using the DatabaseConnection class)
 */

/**
 * TODO: 7.1. Implement findByEmail() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the UBLOG_POSTS table defined
 *  in the database.
 */

/**
 * TODO: 7.13. Implement the deleteById() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false.
 * TODO: 7.14. Implement the findById() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return an PostDTO object
 *  without setting any of its attributes.
 */

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.PostDTO;
import com.upgrad.ublog.utils.DateTimeFormatter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 7.22. Implement findAllTags() method which returns a list of all unique tags in the UBLOG_POSTS
 *  table.
 * TODO: 7.23. Implement findByTag() method which takes "tag" as an input argument and returns all the
 *  posts corresponding to the input "tag" from the UBLOG_POSTS table defined in the database.
 */

public class PostDAOImpl implements PostDAO {

    private static final PostDAOImpl postDAOImpl = new PostDAOImpl();
    private PostDAOImpl() {}

    public static PostDAOImpl getInstance() {
        return postDAOImpl;
    }

    @Override
    public PostDTO create(PostDTO postDTO) throws SQLException {
        Connection connection = DatabaseConnection.getInstance();
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO UBLOG_POSTS (email_id, title, description, tag, timestamp) VALUES('"+postDTO.getEmailId()+"', '"+postDTO.getTitle()+"', '"+postDTO.getDescription()+"', '"+postDTO.getTag()+"', '"+postDTO.getTimestamp()+"')";
        if (stmt.execute(sql))
            return postDTO;
        return null;
    }

    @Override
    public List<PostDTO> findByEmail(String emailId) throws SQLException {
        String sql = "SELECT * FROM UBLOG_POSTS WHERE email_id = '"+emailId+"' ";
        Connection connection = DatabaseConnection.getInstance();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<PostDTO> posts = new ArrayList<>();
        while (resultSet.next()) {
            PostDTO post = new PostDTO();
            post.setPostId(resultSet.getInt(1));
            post.setEmailId(resultSet.getString(2));
            post.setTitle(resultSet.getString(3));
            post.setDescription(resultSet.getString(4));
            post.setTag(resultSet.getString(5));
            post.setTimestamp(LocalDateTime.parse(resultSet.getString(6)));
            posts.add(post);
        }
        return posts;
    }

    @Override
    public List<PostDTO> findByTag(String tag) throws SQLException {
        Connection connection = DatabaseConnection.getInstance();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM UBLOG_POSTS WHERE TAG = '"+tag+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        List<PostDTO> posts = new ArrayList<>();
        while (resultSet.next()) {
            PostDTO post = new PostDTO();
            post.setPostId(resultSet.getInt(1));
            post.setEmailId(resultSet.getString(2));
            post.setTitle(resultSet.getString(3));
            post.setDescription(resultSet.getString(4));
            post.setTag(resultSet.getString(5));
            post.setTimestamp(LocalDateTime.parse(resultSet.getString(6)));
            posts.add(post);
        }
        return posts;
    }

    @Override
    public PostDTO findById(int id) throws SQLException {
        String sql = "SELECT * FROM UBLOG_POSTS WHERE ID = " + id;
        Connection connection = DatabaseConnection.getInstance();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        PostDTO postDTO = new PostDTO();
        if (resultSet.next()) {
            postDTO.setPostId(resultSet.getInt(1));
            postDTO.setEmailId(resultSet.getString(2));
            postDTO.setTitle(resultSet.getString(3));
            postDTO.setDescription(resultSet.getString(4));
            postDTO.setTag(resultSet.getString(5));
            postDTO.setTimestamp(LocalDateTime.parse(resultSet.getString(6)));
        }
        return postDTO;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        Connection connection = DatabaseConnection.getInstance();
        Statement statement = connection.createStatement();
        String sql = "SELECT DISTINCT tag FROM UBLOG_POSTS ORDER BY tag ASC";
        ResultSet resultSet = statement.executeQuery(sql);
        List<String> tags = new ArrayList<>();
        while (resultSet.next()) {
            tags.add(resultSet.getString(1));
        }
        return tags;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM UBLOG_POSTS WHERE ID = " + id;
        Connection connection = DatabaseConnection.getInstance();
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);
        if (rows > 0)
            return true;
        return false;
    }
}

