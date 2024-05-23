package Infrastructure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Beans.User;

public class DAOUser implements IDAOcrud<User> {

    private Connection connection;
    
    public DAOUser() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (username, email, password, phoneNumber) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        User user = null;
        try {
        	PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber")
                );
                user.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findById(int id) {
       User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET username = ?, email = ?, password = ?, phoneNumber = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNumber());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
}
