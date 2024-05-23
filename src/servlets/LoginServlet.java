package servlets;

import Infrastructure.DAOUser;
import Beans.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOUser daoUser;

    public void init() {
        daoUser = new DAOUser();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("home");
            return;
        }
        // Forward to login page
        response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.print(email);
        System.out.print(password);
        User user = daoUser.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home");
        } 
    }
}
