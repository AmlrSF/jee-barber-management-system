package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import Beans.Service;
import Infrastructure.DAOService;
import Beans.Barber;

/**
 * Servlet implementation class ServicesServlet
 */
@WebServlet("/Services")
public class ServicesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the logged-in barber from session
        Barber barber = (Barber) request.getSession().getAttribute("barber");

        // Check if barber is logged in
        if (barber != null) {
            // Retrieve all services associated with the barber's ID
            List<Service> services = new DAOService().findByBarberId(barber.getId());

            // Set the services attribute in the request
            request.setAttribute("services", services);

            // Forward the request to the Services.jsp page
            request.getRequestDispatcher("/WEB-INF/Services.jsp").forward(request, response);
        } else {
            // If barber is not logged in, redirect to home page
            response.sendRedirect("home");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
