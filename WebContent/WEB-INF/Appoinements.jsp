<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Beans.User, Beans.Barber, Beans.Appointment" %>
<%
    User user = (User) session.getAttribute("user");
    Barber barber = (Barber) session.getAttribute("barber");

    List<Appointment> appointments = (List<Appointment>) request.getAttribute("appoinements");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointments</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Barber Appointment System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="home">Home</a>
                </li>
                <% if (barber != null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="NewService">Add Service</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Services">List Services</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Appointments">List Appointments</a>
                </li>
                <% } %>
                <% if (user != null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="Appointments">List Appointments</a>
                </li>
                <% } %>
            </ul>
            <ul class="navbar-nav ml-auto">
                <% if (user == null && barber == null) { %>
                <a class="btn btn-primary btn-lg ml-2" href="Onboarding" role="button">Get Started</a>
                <% } else { %>
                <li class="nav-item">
                    <% if (user != null) { %>
                    <span class="navbar-text">Welcome, <%= user.getUsername() %></span>
                    <% } else if (barber != null) { %>
                    <span class="navbar-text">Welcome, <%= barber.getName() %></span>
                    <% } %>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="display-4">Appointments</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User</th>
                    <th>Barber</th>
                    <th>Service</th>
                    <th>Appointment Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% if (appointments != null) { %>
                <% for (Appointment appointment : appointments) { %>
                <tr>
                    <td><%= appointment.getId() %></td>
                    <td><%= appointment.getUser().getUsername() %> (<%= appointment.getUser().getEmail() %>)</td>
                    <td><%= appointment.getBarber().getName() %> (<%= appointment.getBarber().getEmail() %>)</td>
                    <td><%= appointment.getService().getName() %> - $<%= appointment.getService().getPrice() %></td>
                    
                    <td><%= appointment.getAppointmentTime() %></td>
                	<td>
		            <form action="DeleteAppointment" method="post">
		                <input type="hidden" name="appointmentId" value="<%= appointment.getId() %>">
		                <button type="submit" class="btn btn-danger">Delete</button>
		            </form>
		        </td>
                </tr>
                <% } %>
                <% } else { %>
                <tr>
                    <td colspan="5">No appointments found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
