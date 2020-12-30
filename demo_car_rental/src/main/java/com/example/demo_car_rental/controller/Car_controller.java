package com.example.demo_car_rental.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo_car_rental.model.Car;
import com.example.demo_car_rental.model.Customer;
import com.example.demo_car_rental.model.Rent;
import com.example.demo_car_rental.model.TempRent;
import com.example.demo_car_rental.service.CarService;
import com.example.demo_car_rental.service.CustomerService;
import com.example.demo_car_rental.service.RentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class Car_controller {
    private CarService carService;
    private RentService rentService;
    private CustomerService customerService;

    @Autowired
    public Car_controller(CarService carService, RentService rentService, CustomerService customerService){
        this.carService = carService;
        this.rentService = rentService;
        this.customerService = customerService;
    }
    
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	List<Car> cars = (List<Car>) carService.findAll();
    	modelAndView.addObject("isLogged", isLogged());
    	modelAndView.addObject("cars", cars);
    	modelAndView.addObject("name", auth.getName());
    	modelAndView.setViewName("home_page");
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("tempRent", new TempRent());
    	modelAndView.addObject("isLogged", isLogged());
    	modelAndView.setViewName("welcome_page");
        return modelAndView;
    }
    
    @RequestMapping(value="/tempForm", method = RequestMethod.POST)
    public String getTempRent(@ModelAttribute("tempRent") TempRent tempRent, HttpServletRequest request) {
    	request.getSession().setAttribute("tempRent", tempRent);
    	return "redirect:/home";
    }
    
    @GetMapping("/booking")
    @ResponseBody
    public ModelAndView booking(@RequestParam Long id, HttpSession session) {
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	TempRent tempRent = (TempRent)session.getAttribute("tempRent");
    	Customer customer = customerService.findByUsername(auth.getName());
        Car car = carService.findById(id);

        tempRent.setPrice(car.getPrice());
        
    	modelAndView.addObject("tempRent", tempRent);
    	modelAndView.addObject("rent", new Rent());
    	modelAndView.addObject("customer", customer);
        modelAndView.addObject("car", car);
    	modelAndView.setViewName("booking_page");
        return modelAndView;
    }
    
    @RequestMapping(value="/booking", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView getRent(@RequestParam Long id, @ModelAttribute("rent") Rent rent, HttpSession session) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("redirect:/welcome");
    	TempRent tempRent = (TempRent)session.getAttribute("tempRent");
    	modelAndView.addObject("rent", rent);
        if(rent.isValid()) {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	Customer customer = customerService.findByUsername(auth.getName());
            Car car = carService.findById(id);
            if(car != null)
            	rent.setCar(car);
            if(customer != null)
            	rent.setCustomer(customer);
        	rent.setStartDate(tempRent.getStartDate());
        	rent.setEndDate(tempRent.getEndDate());
        	rent.setPickUp(tempRent.getPickUp());
        	rent.setDropOff(tempRent.getDropOff());
        	Rent result = rentService.save(rent);
        	modelAndView.setViewName("redirect:/receipt?id=" + result.getId());
        }
    	return modelAndView;
    }
    
    @RequestMapping(value="/receipt", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView receipt(@RequestParam Integer id) {
    	ModelAndView modelAndView = new ModelAndView();
    	Rent rent = rentService.findByRentId(id);
    	Customer customer = rent.getCustomer();
    	modelAndView.addObject("rent", rent);
    	modelAndView.addObject("customer", customer);
    	modelAndView.setViewName("receipt_page");
    	return modelAndView;
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login_page");
        return modelAndView;
    }
    
    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("signup_page");
        return modelAndView;
    }
    
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        Customer customerExists = customerService.findByUsername(customer.getUsername());
        if (customerExists == null) {
        	customerService.save(customer);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("customer", new Customer());
            modelAndView.setViewName("signup_page");
        }
        return modelAndView;
    }
    
    public static boolean isLogged(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
}
