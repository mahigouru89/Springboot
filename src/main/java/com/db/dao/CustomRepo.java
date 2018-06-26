package com.db.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.db.model.Customer;

@Repository
public interface CustomRepo extends CrudRepository<Customer, Long> {
	
	List<Customer> findByEmail(String email);
	
	List<Customer> findByDate(Date date);
	
	List<Customer> findAll();
	
	@Query("select c from Customer c where c.email = :email")
	Stream<Customer> findByEmailReturnStream(@Param("email") String email);

/*	@Autowired
	private JdbcTemplate jdbcTemplate;*/
	
/*	public List<Customer> findAll(){
		List<Customer> result = jdbcTemplate.query(
                "SELECT id, name, email, created_date FROM customer",
                (rs, rowNum) -> new Customer(rs.getInt("id"),
                        rs.getString("name"), rs.getString("email"), rs.getDate("created_date"))
        );
		System.out.println(result);
		return result;
	}*/
}
