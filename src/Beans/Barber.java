package Beans;

public class Barber {
	 private int id;

	    private String name;
	    private String password;
	    private String email;
	    private String phoneNumber;
	    private String specialization;
	    private String image;

	    // Constructors
	    public Barber() {}

	    public Barber(String name, String password, String email, String phoneNumber, String specialization,String image) {
	        this.name = name;
	        this.password = password;
	        this.email = email;
	        this.phoneNumber = phoneNumber;
	        this.specialization = specialization;
	        this.image = image;
	    }

	    // Getters and setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getImage() {
	        return image;
	    }

	    public void setImage(String image) {
	        this.image = image;
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public String getSpecialization() {
	        return specialization;
	    }

	    public void setSpecialization(String specialization) {
	        this.specialization = specialization;
	    }
}
