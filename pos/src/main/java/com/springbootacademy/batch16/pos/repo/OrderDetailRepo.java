package com.springbootacademy.batch16.pos.repo;

import com.springbootacademy.batch16.pos.entity.Order;
import com.springbootacademy.batch16.pos.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories

public interface OrderDetailRepo extends JpaRepository<OrderDetails,Integer> {
}
