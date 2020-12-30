package com.example.demo_car_rental.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "car")
public class Car implements Serializable {
    private static final long serialVersionUID = 7034352443015914334L;


    public Car(){
        super();
    }

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "car_name", length = 100)
    private String name;

   //@Digits(integer = 5, fraction = 2)
    @Column(name = "price")
    private int price;
    
    @Column(name = "url", length = 150)
    private String url;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Rent> rentDates = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getUrl() {
    	return url;
    }
    
    public void setUrl(String url) {
    	this.url = url;
    }

    public List<Rent> getRentDates() {
        return rentDates;
    }

    public void setRentDates(List<Rent> rentDates) {
        this.rentDates = rentDates;
    }
}
