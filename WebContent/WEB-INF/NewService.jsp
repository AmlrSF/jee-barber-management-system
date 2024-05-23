<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Beans.User, Beans.Barber, Beans.Service, Infrastructure.DAOService" %>
<%
    User user = (User) session.getAttribute("user");
    Barber barber = (Barber) session.getAttribute("barber");
    DAOService daoService = new DAOService();
    
    List<Service> services;
    if (barber != null) {
        services = daoService.findByBarberId(barber.getId());
    } else {
        services = daoService.findAll();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Services</title>
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

    <!-- Main content -->
    <div class="container mt-5">
    <h1 class="display-4">Add New Service</h1>
    <form action="NewService" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price" required>
        </div>
        <div class="form-group">
            <label for="duration">Duration (minutes):</label>
            <input type="number" class="form-control" id="duration" name="duration" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>
        <input type="hidden" id="barberId" name="barberId" value="<%= barber.getId() %>">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
