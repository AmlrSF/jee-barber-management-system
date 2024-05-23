package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Appointment;
import Beans.Barber;
import Beans.Service;
import Beans.User;
import Infrastructure.DAOAppointment;

@WebServlet("/ReserveService")
public class ReserveServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReserveServiceServlet() {
        super();
    
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Home.jsp" ).forward( request, response );

	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        int barberId = Integer.parseInt(request.getParameter("barberId"));
        LocalDateTime appointmentTime = LocalDateTime.now(); 
        
        User user = new User();
        user.setId(userId);
        
        
        Barber barber = new Barber();
        barber.setId(barberId);
        
        Service service = new Service();
        service.setId(serviceId);
        
        System.out.print(userId + " "+ serviceId +" "+ barberId);

        Appointment appointment = new Appointment(user, barber, service, appointmentTime);

        DAOAppointment daoAppointment = new DAOAppointment();
        daoAppointment.create(appointment);

        response.sendRedirect("Appointments");
    }
}
