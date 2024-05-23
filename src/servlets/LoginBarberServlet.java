package servlets;

import Infrastructure.DAOBarber;
import Beans.Barber;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginBarber")
public class LoginBarberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOBarber daoBarber;

    public void init() {
        daoBarber = new DAOBarber();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/LoginBarber.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Barber barber = daoBarber.findByEmail(email);

        if (barber != null && barber.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("barber", barber);
            response.sendRedirect("home");
        } 
    }
}
