package Infrastructure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Beans.Barber;

public class DAOBarber implements IDAOcrud<Barber> {

    private Connection connection;
    
    public DAOBarber() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void create(Barber barber) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO barber (name, password, email, phoneNumber, specialization,image) "
            		+ "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, barber.getName());
            statement.setString(2, barber.getPassword());
            statement.setString(3, barber.getEmail());
            statement.setString(4, barber.getPhoneNumber());
            statement.setString(5, barber.getSpecialization());
            statement.setString(6, barber.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Barber findById(int id) {
        Barber barber = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM barber WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                barber = new Barber();
                barber.setId(resultSet.getInt("id"));
                barber.setName(resultSet.getString("name"));
                barber.setPassword(resultSet.getString("password"));
                barber.setEmail(resultSet.getString("email"));
                barber.setPhoneNumber(resultSet.getString("phoneNumber"));
                barber.setSpecialization(resultSet.getString("specialization"));
                barber.setImage(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barber;
    }

    @Override
    public List<Barber> findAll() {
        List<Barber> barbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM barber");
            while (resultSet.next()) {
                Barber barber = new Barber();
                barber.setId(resultSet.getInt("id"));
                barber.setName(resultSet.getString("name"));
                barber.setPassword(resultSet.getString("password"));
                barber.setEmail(resultSet.getString("email"));
                barber.setPhoneNumber(resultSet.getString("phoneNumber"));
                barber.setSpecialization(resultSet.getString("specialization"));
                barber.setImage(resultSet.getString("image"));
                barbers.add(barber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barbers;
    }

    @Override
    public void update(Barber barber) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE barber SET name = ?, password = ?, email = ?, phoneNumber = ?, specialization = ?, image = ? WHERE id = ?");
            statement.setString(1, barber.getName());
            statement.setString(2, barber.getPassword());
            statement.setString(3, barber.getEmail());
            statement.setString(4, barber.getPhoneNumber());
            statement.setString(5, barber.getSpecialization());
            statement.setString(6, barber.getImage());
            statement.setLong(7, barber.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Barber findByEmail(String email) {
        Barber barber = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM barber WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                barber = new Barber();
                barber.setId(resultSet.getInt("id"));
                barber.setName(resultSet.getString("name"));
                barber.setPassword(resultSet.getString("password"));
                barber.setEmail(resultSet.getString("email"));
                barber.setPhoneNumber(resultSet.getString("phoneNumber"));
                barber.setSpecialization(resultSet.getString("specialization"));
                barber.setImage(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barber;
    }

    @Override
    public void delete(Barber barber) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM barber WHERE id = ?");
            statement.setLong(1, barber.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
