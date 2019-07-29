package shopping.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.CommitedProduct;
import shopping.model.Employee;
import shopping.model.Stock;
import shopping.repository.CommitedProductRepository;





@Service
public class CommitedProductService {

	@Autowired
	private CommitedProductRepository commitedProductRepository; 
	
	
	
	public List <CommitedProduct> getALLCommitedProduct(){
		List <CommitedProduct> commitedProducts = new ArrayList<>();
		commitedProductRepository.findAll()
		.forEach(commitedProducts::add);
		return commitedProducts;
	}
	
	/*public List<CommitedProduct> getcommitedProducts(String commitedProductType, int userid) 
	{
		List <CommitedProduct> commitedProducts = new ArrayList<>();
		commitedProductRepository.findByTypeAndById(commitedProductType, userid)
		.forEach(commitedProducts::add);
		return commitedProducts;
		
	}*/
	
	public List<CommitedProduct> getAllUserProducts(long userid) 
	{
		List <CommitedProduct> commitedProducts = new ArrayList<>();
		commitedProducts= commitedProductRepository.findByUserid(userid);
		for (CommitedProduct commitedProduct : commitedProducts) {
		    //System.out.println(commitedProduct);
			commitedProductRepository.deleteById(commitedProduct.getId());	
		}
		//commitedProductRepository.deleteById(commitedProducts);
		return commitedProducts;
		
	}
	
	public void deleteCommitedProductList(List<CommitedProduct> commitedProductlist) 
	{
	//return	employees.stream().filter(emp -> emp.getId().equals(id)).findFirst().get();	
		commitedProductRepository.deleteListById(commitedProductlist);
	}
	
	public Optional<CommitedProduct> getCommitedProduct(long id) 
	{
	//return	employees.stream().filter(emp -> emp.getId().equals(id)).findFirst().get();	
		return	commitedProductRepository.findById(id);
	}
	
	
	public CommitedProduct addCommitedProduct(CommitedProduct commitedProduct) {
	return	commitedProductRepository.save(commitedProduct);
	}
	
	public CommitedProduct updateCommitedProduct(int id, CommitedProduct commitedProduct) {
	return	commitedProductRepository.save(commitedProduct);
	}

	public void  deleteCommitedProduct(long id) {
		commitedProductRepository.deleteById(id);
	}
	
	
	
	
}
