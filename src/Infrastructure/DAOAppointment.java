package Infrastructure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Beans.Appointment;
import Beans.Barber;
import Beans.Service;
import Beans.User;

public class DAOAppointment implements IDAOcrud<Appointment> {

    private Connection connection;
    
    public DAOAppointment() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void create(Appointment appointment) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO appointment (userId, barberId, serviceId, appointmentTime) VALUES (?, ?, ?, ?)");
            statement.setLong(1, appointment.getUser().getId());
            statement.setLong(2, appointment.getBarber().getId());
            statement.setLong(3, appointment.getService().getId());
            statement.setTimestamp(4, Timestamp.valueOf(appointment.getAppointmentTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Appointment findById(int id) {
        Appointment appointment = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointment WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setUser(new DAOUser().findById(resultSet.getInt("userId")));
                appointment.setBarber(new DAOBarber().findById(resultSet.getInt("barberId")));
                appointment.setService(new DAOService().findById(resultSet.getInt("serviceId")));
                appointment.setAppointmentTime(resultSet.getTimestamp("appointmentTime").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    
    
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT a.id, a.appointmentTime, " +
                       "u.id AS userId, u.username, u.email, " +
                       "b.id AS barberId, b.name AS barberName, b.email AS barberEmail, " +
                       "s.id AS serviceId, s.name AS serviceName, s.price, s.description " +
                       "FROM appointment a " +
                       "JOIN user u ON a.userId = u.id " +
                       "JOIN barber b ON a.barberId = b.id " +
                       "JOIN service s ON a.serviceId = s.id;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setAppointmentTime(resultSet.getTimestamp("appointmentTime").toLocalDateTime());

                User user = new User();
                user.setId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                appointment.setUser(user);

                Barber barber = new Barber();
                barber.setId(resultSet.getInt("barberId"));
                barber.setName(resultSet.getString("barberName"));
                barber.setEmail(resultSet.getString("barberEmail"));
                appointment.setBarber(barber);

                Service service = new Service();
                service.setId(resultSet.getInt("serviceId"));
                service.setName(resultSet.getString("serviceName"));
                service.setPrice(resultSet.getDouble("price"));
                service.setDescription(resultSet.getString("description"));
                appointment.setService(service);

                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    @Override
    public void update(Appointment appointment) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE appointment SET userId = ?, barberId = ?, serviceId = ?, appointmentTime = ? WHERE id = ?");
            statement.setLong(1, appointment.getUser().getId());
            statement.setLong(2, appointment.getBarber().getId());
            statement.setLong(3, appointment.getService().getId());
            statement.setTimestamp(4, Timestamp.valueOf(appointment.getAppointmentTime()));
            statement.setLong(5, appointment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Appointment appointment) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM appointment WHERE id = ?");
            statement.setLong(1, appointment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
