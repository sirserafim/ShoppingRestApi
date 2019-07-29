package shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopping.model.Employee;
import shopping.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
	


}

