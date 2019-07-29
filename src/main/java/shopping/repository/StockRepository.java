package shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopping.model.Employee;
import shopping.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> findByType(String type); 
}

