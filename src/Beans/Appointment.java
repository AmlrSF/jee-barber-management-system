package Beans;

import java.time.LocalDateTime;

public class Appointment {
	 
	    private int id;

	
	    private User user;

	    
	    private Barber barber;

	 
	    private Service service;

	    private LocalDateTime appointmentTime;

	    // Constructors
	    public Appointment() {}

	    public Appointment(User user, Barber barber, Service service, LocalDateTime appointmentTime) {
	        this.user = user;
	        this.barber = barber;
	        this.service = service;
	        this.appointmentTime = appointmentTime;
	    }

	    // Getters and setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public Barber getBarber() {
	        return barber;
	    }

	    public void setBarber(Barber barber) {
	        this.barber = barber;
	    }

	    public Service getService() {
	        return service;
	    }

	    public void setService(Service service) {
	        this.service = service;
	    }

	    public LocalDateTime getAppointmentTime() {
	        return appointmentTime;
	    }

	    public void setAppointmentTime(LocalDateTime appointmentTime) {
	        this.appointmentTime = appointmentTime;
	    }
}
