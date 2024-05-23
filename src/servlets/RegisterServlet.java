package servlets;

import Infrastructure.DAOUser;
import Beans.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOUser daoUser;

    public void init() {
        daoUser = new DAOUser();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to register page
    	response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Register.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        User newUser = new User(username, email, password, phoneNumber);
        daoUser.create(newUser);

        response.sendRedirect("login");
    }
}
