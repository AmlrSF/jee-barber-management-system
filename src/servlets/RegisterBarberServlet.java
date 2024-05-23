package servlets;

import Infrastructure.DAOBarber;
import Beans.Barber;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/RegisterBarber")
@MultipartConfig
public class RegisterBarberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOBarber daoBarber;

    public void init() {
        daoBarber = new DAOBarber();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/RegisterBarber.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String specialization = request.getParameter("specialization");

        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        filePart.write(uploadPath + File.separator + fileName);

        String imagePath = "uploads" + File.separator + fileName;
        Barber newBarber = new Barber(name, password, email, phoneNumber, specialization, imagePath);
        daoBarber.create(newBarber);

        response.sendRedirect("LoginBarber");
    }
}
