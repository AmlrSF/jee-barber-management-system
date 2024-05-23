package Beans;

public class Service {
	 private int id;

	    private String name;
	    private double price;
	    private int duration; // Duration in minutes
	    private String description;
	    private Barber barberId;
	    // Constructors
	    public Service() {}

	    public Service(String name, double price, int duration,String description, Barber barberId) {
	        this.name = name;
	        this.price = price;
	        this.duration = duration;
	        this.description = description;
	        this.barberId = barberId;
	    }

	    public Service(String name, double price, int duration,String description) {
	        this.name = name;
	        this.price = price;
	        this.duration = duration;
	        this.description = description;
	        
	    }

	    
	    // Getters and setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }


	    public Barber getBarberId() {
	        return barberId;
	    }

	    public void setBarberId(Barber id) {
	        this.barberId = id;
	    }

	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public int getDuration() {
	        return duration;
	    }

	    public void setDuration(int duration) {
	        this.duration = duration;
	    }
}
