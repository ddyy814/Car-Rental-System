package com.example.demo_car_rental.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "rent")
public class Rent implements Serializable {

    private static final long serialVersionUID = -1713505055304086201L;

    public Rent() {
        super();
    }

    @Id
    @Column(name = "rent_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;
    
    @Column(name = "pick_up", length = 120)
    private String pickUp;

	@Column(name = "drop_off", length = 120)
    private String dropOff;
	
    @Size(min = 2, max = 30)
    @Column(name = "driver_first_name", length = 30)
    private String driverFirstName;
    
    @Size(min = 2, max = 30)
    @Column(name = "driver_last_name", length = 30)
    private String driverLastName;
	
	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "driver_birth", length = 20)
	private String driverBirth;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

    public String getDropOff() {
		return dropOff;
	}

	public void setDropOff(String dropOff) {
		this.dropOff = dropOff;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDriverFirstName() {
		return driverFirstName;
	}

	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}

	public String getDriverLastName() {
		return driverLastName;
	}

	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}

	public String getDriverBirth() {
		return driverBirth;
	}

	public void setDriverBirth(String driverBirth) {
		this.driverBirth = driverBirth;
	}

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public boolean isValid() {
    	boolean valid = true;
    	if(driverFirstName == null)
    		valid = false;
    	else if(driverLastName == null)
    		valid = false;
    	else if(phone == null)
    		valid = false;
    	else if(driverBirth == null)
    		valid = false;
    	return valid;
    }
}
