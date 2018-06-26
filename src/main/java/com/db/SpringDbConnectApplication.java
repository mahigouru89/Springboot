package com.db;

import java.util.List;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


import com.db.dao.CustomRepo;
import com.db.model.Customer;
import static java.lang.System.exit;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class SpringDbConnectApplication implements CommandLineRunner{
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomRepo customRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDbConnectApplication.class, args);
	}

	@Transactional(readOnly = true)
	@Override
	public void run(String... args) throws Exception {
		
        System.out.println("DATASOURCE = " + dataSource);

/*        System.out.println("Display All Customers....");
		
        List<Customer> list = customRepo.findAll();
        list.forEach(x -> System.out.println(x));*/
        
        System.out.println("\n1.findAll()...");
        
        for(Customer customer: customRepo.findAll()) {
        	System.out.println(customer.getName() + ',' + customer.getEmail() + ',' + customer.getDate());
        }
        
        System.out.println("\n1.findByEmail()...");
        
        for(Customer customer: customRepo.findByEmail("222@yahoo.com")) {
        	System.out.println(customer);
        }
        System.out.println("\n1.findByDate()...");
        
        for(Customer customer: customRepo.findByDate(sdf.parse("2017-02-12"))) {
        	System.out.println(customer);
        }
        // For Stream, need @Transactional
        System.out.println("\n4.findByEmailReturnStream(@Param(\"email\") String email)...");
        try (Stream<Customer> stream = customRepo.findByEmailReturnStream("333@yahoo.com")) {
            stream.forEach(x -> System.out.println(x));
        }

        
        System.out.println("Done..!");
        
        exit(0);
	}
}
