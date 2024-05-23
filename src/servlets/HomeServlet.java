package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession(false);
        
        //if (session == null || session.getAttribute("user") == null) {
          //  response.sendRedirect("login");
            //return;
        //}

     // Forward to login page
        response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Home.jsp" ).forward( request, response );
    }
}
