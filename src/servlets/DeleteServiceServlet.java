package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Infrastructure.DAOService;

@WebServlet("/DeleteService")
public class DeleteServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServiceServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the service ID from the request parameters
        int serviceId = Integer.parseInt(request.getParameter("id"));

        // Use DAOService to delete the service by ID
        DAOService daoService = new DAOService();
        daoService.deleteService(serviceId);

        // Redirect back to the services page
        response.sendRedirect("Services");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to doGet method
        doGet(request, response);
    }
}
