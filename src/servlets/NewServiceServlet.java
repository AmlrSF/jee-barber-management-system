package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Barber;
import Beans.Service;
import Infrastructure.DAOService;

/**
 * Servlet implementation class NewServiceServlet
 */
@WebServlet("/NewService")
public class NewServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/NewService.jsp" ).forward( request, response );

	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int barberId = Integer.parseInt(request.getParameter("barberId")); // Assuming barberId is provided as a hidden field
        Barber barber = new Barber();
        barber.setId(barberId);
        
        // Create a new Service object
        Service newService = new Service(name, price, duration, description, barber);
        
        // Save the new service to the database
        DAOService daoService = new DAOService();
        daoService.create(newService);
        
        // Redirect back to the services page
        response.sendRedirect("Services");
    }

}
