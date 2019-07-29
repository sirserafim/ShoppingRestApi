package shopping.controller;

import shopping.exception.ResourceNotFoundException;
import shopping.model.CommitedProduct;
import shopping.model.Employee;
import shopping.model.Shop;
import shopping.model.Stock;
import shopping.service.EmployeeService;
import shopping.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/st")
public class StockController {

	 @Autowired
	 private  StockService stockService;
	   
	/* @Autowired
	 private  EmployeeService employeeService;
	  */
	 
 // Get All items in stock
    @GetMapping("/allstock")
    public @ResponseBody ResponseEntity<Object> getAll() {
    List<Stock> entityList = stockService.getALLStocks();
    return new ResponseEntity<Object>(entityList, HttpStatus.OK);
    }
    @PostMapping("/stock")
    public @ResponseBody ResponseEntity<Object> createEmployee(@Valid @RequestBody Stock stock){
    stock = stockService.addStock(stock);
    return new ResponseEntity<Object>(stock, HttpStatus.OK);
    }
   // Get All items of a type in stock    
    @GetMapping("/stocks/{type}")
	public @ResponseBody ResponseEntity<Object> gestock(@PathVariable(value = "type") String stockType){	
	List<Stock> entityList = stockService.getStockType(stockType);
	return new ResponseEntity<Object>(entityList, HttpStatus.OK);
}
    
    
    /*
    @GetMapping("/stockType/{id}")
    // Getting the employee type
    public List<Stock> getType(@PathVariable(value = "id") int userid,
            @Valid @RequestBody Stock stockDetails) {
    	
    	String type = employeeService.getEmployeeType(userid);
    	//Employee employee = employeeService.getEmployee(userid).get();
        return stockService.getStockType(type);
    }*/

 // Create a new item in the Stock
    

 // Get a Single item in the Stock
    @GetMapping("/stock/{id}")
    public @ResponseBody ResponseEntity<Object>  getStockById(@PathVariable(value = "id") int stockId) {
        Stock stock = stockService.getStock(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));
        return new ResponseEntity<Object>(stock, HttpStatus.OK);
    }
    
   
   

       
 // Update an item in the Stock
		@PutMapping("/stock/{id}")
		public @ResponseBody ResponseEntity<Object> updateStock(@PathVariable(value = "id") int stockId,
                                        @Valid @RequestBody Stock stockDetails) {

    	Stock stock = stockService.getStock(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));

    	stock.setName(stockDetails.getName());
    	stock.setPrice(stockDetails.getPrice());
    	stock.setDescription(stockDetails.getDescription());
    	stock.setType(stockDetails.getType());
        stock.setQuantity(stockDetails.getQuantity());
        
        Stock updatedStock = stockService.updateStock(stockId,stock);
        return new ResponseEntity<Object>(updatedStock, HttpStatus.OK);    
    }
    
    
	//Ckeck if you can buy a product
		 @PutMapping("/stock/{userid}/{id}/{quantity}")
		    public   @ResponseBody ResponseEntity<?>  checkoutStock(@PathVariable(value = "id")int stockId ,
		    		@PathVariable(value = "quantity")  int Quantity,
		    @PathVariable(value = "userid")  int Userid) {
			 boolean allwas= stockService.sellStock(stockId, Quantity, Userid); 
			//  return new ResponseEntity<Object>(StringStock, HttpStatus.OK);
			 if (allwas== true) {
				 return ResponseEntity.ok().build();
			 }else {
				 return ResponseEntity.badRequest().build();
			 }
	
		 }
			 // return ResponseEntity.ok().build();
			// return new ResponseEntity<String>(StringStock);
		      //  return("All is fine");
			 //return new ResponseEntity<Object>(StringStock, HttpStatus.OK);
		   	
		/*
	 @GetMapping("/stock/{id}/{quantity}")
		    public @ResponseBody ResponseEntity<?>  saleStock(@PathVariable(value = "id")int stockId ,
		    		@PathVariable(value = "quantity")  int Quantity) {
			 ResponseEntity<String>	 StringStock= stockService.sellStock(stockId, Quantity);  
		        return new ResponseEntity<Object>(StringStock, HttpStatus.OK);
		    }
	@PutMapping("/stock/{id}/{quantity}")
    public void saleStock(@PathVariable(value = "id") int stockId, 
    		@PathVariable(value = "quantity")  int Quantity, @Valid @RequestBody Stock stockDetails) {
    	ResponseEntity<String>	 StringStock= stockService.sellStock(stockId, Quantity);   		
    
    }
	*/
		
		
 // Delete a Stock
    @DeleteMapping("/stock/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable(value = "id") int stockId) {
    	Stock stock = stockService.getStock(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));

    	stockService.deleteStock(stockId);

        return ResponseEntity.ok().build();
    }
    
   

	

}
