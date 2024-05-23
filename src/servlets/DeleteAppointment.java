package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Beans.Appointment;
import Infrastructure.DAOAppointment;

/**
 * Servlet implementation class DeleteAppointment
 */
@WebServlet("/DeleteAppointment")
public class DeleteAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get the appointment ID to delete
	    int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

	    // Call DAO to delete the appointment
	    DAOAppointment daoAppointment = new DAOAppointment();
	    Appointment  app = new Appointment();
	    app.setId(appointmentId);
	    daoAppointment.delete(app);

	    // Redirect back to the appointments page
	    response.sendRedirect(request.getContextPath() + "/Appointments");
	}


}
