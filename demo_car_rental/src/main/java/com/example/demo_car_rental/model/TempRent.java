package com.example.demo_car_rental.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TempRent{
	
    private String startDate;
    private String endDate;
    private String pickUp;
    private String dropOff;
    private long rentDays;
    private long beforeTax;
	private double tax;
	private double totalPrice;

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
	
	private void calRentDays() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = format.parse(startDate);
			Date date2 = format.parse(endDate);
			rentDays = TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
		}catch (ParseException e) {
			rentDays = -1;
		}
	}
	
	public long getRentDays() {
		return rentDays;
	}
	
	public void setRentDays(long rentDays) {
		this.rentDays = rentDays;
	}

	public void setPrice(int price) {
		calRentDays();
		beforeTax = price * rentDays;
		tax = (double) Math.round(0.075 * beforeTax * 100) / 100;
		setTotalPrice(tax + beforeTax);
	}
	
    public long getBeforeTax() {
		return beforeTax;
	}

	public void setBeforeTax(long beforeTax) {
		this.beforeTax = beforeTax;
	}
	
	public double getTax() {
		return tax;
	}
	
    public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}