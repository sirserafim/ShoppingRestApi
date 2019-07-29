package shopping.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shopping.exception.ResourceNotFoundException;
import shopping.model.CommitedProduct;
import shopping.model.Employee;
import shopping.model.Stock;
import shopping.repository.CommitedProductRepository;
import shopping.repository.StockRepository;




@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository; 
	
	@Autowired
	private CommitedProductRepository commitedProductRepository; 
	
	
	public List <Stock> getALLStocks(){
		List <Stock> stocks = new ArrayList<>();
		stockRepository.findAll()
		.forEach(stocks::add);
		return stocks;
	}
	
	public Optional<Stock> getStock(long id) 
	{

		return	stockRepository.findById(id);
	}
	
	public List<Stock> getStockType(String type) 
	{
		List <Stock> stocks = new ArrayList<>();
		stockRepository.findByType(type)
		.forEach(stocks::add);
		return stocks;
		
	}
	
	public Stock addStock (Stock stock) {
	return	stockRepository.save(stock);
	}
	
	public Stock updateStock(long id, Stock stock) {
	return	stockRepository.save(stock);
	}

	public void  deleteStock(long id) {
		stockRepository.deleteById(id);
	}
	
	
	// used to add items to the shopping basket
	public boolean sellStock(long stockId, int Quantity ,long userid) 
	{	
			boolean allwas;
			Stock stock = stockRepository.findById(stockId).get();
			Optional<CommitedProduct> commitedProduct = commitedProductRepository.findById(stockId);
			
			int	stocked = stock.getQuantity();
			int	commitedStock = 0;
			int  sumCommitedStock = 0 ;
			int	reserve = 0;
			int	result= stocked - Quantity ;
		if (result >= 0) 
    	{
			reserve= stocked -  Quantity ;
            stock.setQuantity(reserve);
           
            if(commitedProduct.isPresent()){
            	 commitedStock = commitedProduct.get().getQuantity();
            	 sumCommitedStock = commitedStock + Quantity;
                 commitedProduct.get().setQuantity(sumCommitedStock);
                 commitedProductRepository.save(commitedProduct.get());
            }else 
            {	CommitedProduct	 newcommitedproduct = new CommitedProduct() ;
            	sumCommitedStock =  Quantity;
            	newcommitedproduct.setName(stock.getName());
            	newcommitedproduct.setPrice(stock.getPrice());
            	newcommitedproduct.setDescription(stock.getDescription());
            	newcommitedproduct.setType(stock.getType());
               	newcommitedproduct.setUserid(userid);
            	newcommitedproduct.setQuantity(sumCommitedStock);
            	commitedProductRepository.save(newcommitedproduct);
            	//commitedProductRepository.save(stock);
            	//na dimiourgisw ena antikeimeno
            }
           
            stockRepository.save(stock);
            allwas=true;
           return allwas;
           

    	}
    	else {
    		 allwas=false;
    		 return allwas;
            
    	}
    	
	}
	
}
