package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.User;
import Beans.Barber;
import Beans.Appointment;
import Infrastructure.DAOAppointment;

@WebServlet("/Appointments")
public class Appoinements extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Appoinements() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session attributes
        User user = (User) request.getSession().getAttribute("user");
        Barber barber = (Barber) request.getSession().getAttribute("barber");

        // Create an instance of the DAO for appointments
        DAOAppointment daoAppointment = new DAOAppointment();

        // Retrieve all appointments
        List<Appointment> allAppointments = daoAppointment.findAll();

        // Initialize lists for filtered appointments
        List<Appointment> userAppointments = new ArrayList<>();
        List<Appointment> barberAppointments = new ArrayList<>();
        List<Appointment> filteredAppointments = new ArrayList<>();

        // Filter appointments based on the session
        if (user != null) {
            System.out.println("User ID: " + user.getId());
            allAppointments.forEach(appointment -> {
                if (appointment.getUser().getId() == user.getId()) {
                    userAppointments.add(appointment);
                }
            });
            filteredAppointments = userAppointments;
        } else if (barber != null) {
            System.out.println("Barber ID: " + barber.getId());
            allAppointments.forEach(appointment -> {
                if (appointment.getBarber().getId() == barber.getId()) {
                    barberAppointments.add(appointment);
                }
            });
            filteredAppointments = barberAppointments;
        } else {
            // Handle case when neither user nor barber is logged in
            System.out.println("No user or barber logged in.");
            filteredAppointments = allAppointments; // Return all appointments
        }

        // Set appointments attribute to be accessed in JSP
        request.setAttribute("appoinements", filteredAppointments);

        // Forward to the JSP page to display appointments
        request.getRequestDispatcher("/WEB-INF/Appoinements.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
