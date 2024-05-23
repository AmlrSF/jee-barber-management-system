<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Beans.User, Beans.Barber, Beans.Service, Infrastructure.DAOService" %>
<%
    User user = (User) session.getAttribute("user");
    Barber barber = (Barber) session.getAttribute("barber");
    DAOService daoService = new DAOService();

    List<Service> services = daoService.findAll();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
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
    
       <div class="container mt-3">
        <form class="form-inline" method="GET" action="home">
            <div class="form-group mx-sm-3 mb-2">
                <label for="serviceNameFilter" class="sr-only">Service Name</label>
                <input type="text" class="form-control" id="serviceNameFilter" name="serviceNameFilter" placeholder="Enter Service Name">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Filter</button>
        </form>
    </div>

    <!-- Main content -->
    <div class="container mt-5">
        <div class="jumbotron">
            <h1 class="display-4">Welcome to the Barber Appointment System!</h1>
            <p class="lead">Easily book appointments with your favorite barbers.</p>
            <hr class="my-4">
            <h2>Available Services</h2>
            <div class="row">
                <% if (services != null) { %>
                <% for (Service service : services) { %>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title"><%= service.getName() %></h5>
                            <p class="card-text"><%= service.getDescription() %></p>
                            <p class="card-text"><strong>Price:</strong> $<%= service.getPrice() %></p>
                            <% if (user != null) { %>
                            <form action="ReserveService" method="post">
                                <input type="hidden" name="serviceId" value="<%= service.getId() %>">
                                <input type="hidden" name="barberId" value="<%= service.getBarberId().getId() %>">
                                <input type="hidden" name="userId" value="<%= user.getId() %>">
                                <button type="submit" class="btn btn-primary">Reserve</button>
                            </form>
                            <% } %>
                        </div>
                    </div>
                </div>
                <% } %>
                <% } else { %>
                <p>No services found.</p>
                <% } %>
            </div>
            <% if (user == null && barber == null) { %>
            <p>Click on Login or Register to get started.</p>
            <a class="btn btn-primary btn-lg" href="Onboarding" role="button">Get Started</a>
            <% } %>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
