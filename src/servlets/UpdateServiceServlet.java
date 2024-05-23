package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Service;
import Infrastructure.DAOService;

@WebServlet("/UpdateService")
public class UpdateServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServiceServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the service ID from the request parameters
        int serviceId = Integer.parseInt(request.getParameter("id"));

        // Use DAOService to fetch the existing service
        DAOService daoService = new DAOService();
        Service existingService = daoService.findById(serviceId);

        // Set the retrieved service as an attribute in the request scope
        request.setAttribute("service", existingService);

        // Forward the request to EditService.jsp
        request.getRequestDispatcher("/WEB-INF/EditService.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));

        // Create a new Service object
        Service updatedService = new Service(name, price, duration, description);
        updatedService.setId(id);
        // Update the service in the database
        DAOService daoService = new DAOService();
        daoService.update(updatedService);

        // Redirect back to the services page
        response.sendRedirect("Services");
    }
}
