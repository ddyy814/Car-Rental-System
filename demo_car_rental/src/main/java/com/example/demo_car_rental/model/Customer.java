package com.example.demo_car_rental.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = -5499172417961772372L;

    public Customer(){
        super();
    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Size(min = 5, max = 60)
    @Column(name = "password", length = 60)
    private String password;

    @Size(min = 2, max = 30)
    @Column(name = "first_name", length = 30)
    private String firstName;
    
    @Size(min = 2, max = 30)
    @Column(name = "last_name", length = 30)
    private String lastName;
    
    @Size(min = 5, max = 50)
    @Column(name = "email", length = 50)
    private String email;

	@Column(name = "rent_car")
    private int rentCars;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Rent> rents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }
}
