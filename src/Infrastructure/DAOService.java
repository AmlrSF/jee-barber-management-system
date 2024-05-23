package Infrastructure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Beans.Service;

public class DAOService implements IDAOcrud<Service> {

    private Connection connection;
    
    public DAOService() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void create(Service service) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO service (name, price, duration,barberId ,description) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getPrice());
            statement.setInt(3, service.getDuration());
            statement.setInt(4, service.getBarberId().getId());
            statement.setString(5, service.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Service> findByBarberId(int barberId) {
        List<Service> services = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM service WHERE barberId  = ?");
            statement.setInt(1, barberId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getInt("id"));
                service.setName(resultSet.getString("name"));
                service.setDescription(resultSet.getString("description"));
                service.setPrice(resultSet.getDouble("price"));
                service.setDuration(resultSet.getInt("duration"));
                // Set other attributes as needed
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public Service findById(int id) {
        Service service = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM service WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                service = new Service();
                service.setId(resultSet.getInt("id"));
                service.setName(resultSet.getString("name"));
                service.setPrice(resultSet.getDouble("price"));
                service.setBarberId(new DAOBarber().findById(resultSet.getInt("barberId")));
                service.setDescription(resultSet.getString("description"));            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM service");
            while (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getInt("id"));
                service.setName(resultSet.getString("name"));
                service.setPrice(resultSet.getDouble("price"));
                service.setDuration(resultSet.getInt("duration"));
                service.setBarberId(new DAOBarber().findById(resultSet.getInt("barberId")));
                service.setDescription(resultSet.getString("description"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public void update(Service service) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE service SET name = ?, price = ?, duration = ?, description = ? WHERE id = ?");
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getPrice());
            statement.setInt(3, service.getDuration());
            statement.setString(4, service.getDescription());
            statement.setLong(5, service.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Service service) {

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM service WHERE id = ?");
            statement.setLong(1, service.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteService(int id) {

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM service WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
