package shopping.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.model.CommitedProduct;
import shopping.model.Stock;



@Transactional
public interface CommitedProductRepository extends  JpaRepository <CommitedProduct , Long>  {

	//Optional<CommitedProduct> getCommitedProduct(Long commitedproductId);
	List<CommitedProduct> findByUserid(long userid); 
	void deleteListById(List<CommitedProduct> commitedProductlist); 
	//List<CommitedProduct> findByTypeAndById(String type,long userid); 
}
