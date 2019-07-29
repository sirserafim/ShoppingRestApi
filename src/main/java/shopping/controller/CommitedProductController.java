package shopping.controller;

import shopping.exception.ResourceNotFoundException;

import shopping.model.CommitedProduct;
import shopping.model.Employee;
import shopping.model.Stock;
import shopping.repository.CommitedProductRepository;
import shopping.service.CommitedProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@CrossOrigin()
@RestController
@RequestMapping("/comp")
public class CommitedProductController {

    @Autowired
    CommitedProductService commitedProductService;

 // Get All /commitedproducts
  /*  @GetMapping("/commitedproducts")
    public List< CommitedProduct>  CommitedProducts() {
        return commitedproductRepository.findAll();
    }*/
    //Get All /commitedproducts
    @GetMapping("/commitedproducts")
    public @ResponseBody ResponseEntity<Object> CommitedProducts() {
    List<CommitedProduct> entityList = commitedProductService.getALLCommitedProduct();
    return new ResponseEntity<Object>(entityList, HttpStatus.OK);
    }

    //buy All items of userid in stock  
    @DeleteMapping("/commiteduserproducts/{id}")
   	public @ResponseBody  ResponseEntity<Object> buyStock(@PathVariable(value = "id") long userid){	
    List<CommitedProduct> entityList = 	commitedProductService.getAllUserProducts(userid);
    //return ResponseEntity.ok().build();  
    //commitedProductService.deleteCommitedProductList(entityList);
  //  return new ResponseEntity<Object>(entityList, HttpStatus.OK);
    return ResponseEntity.ok().build();  
   }
    
 // Create a new commitedproduct
    @PostMapping("/commitedproduct")
    public @ResponseBody ResponseEntity<Object> createCommitedProduct(@Valid @RequestBody CommitedProduct commitedProduct){
    commitedProduct = commitedProductService.addCommitedProduct(commitedProduct);
    return new ResponseEntity<Object>(commitedProduct, HttpStatus.OK);
    }
 // Get a Single commitedproduct
    @GetMapping("/commitedproduct/{id}")
    public @ResponseBody ResponseEntity<Object> getCommitedProductById(@PathVariable(value = "id") int commitedproductId) {
    	CommitedProduct commitedProduct = commitedProductService.getCommitedProduct(commitedproductId)
                .orElseThrow(() -> new ResourceNotFoundException("CommitedProduct", "id", commitedproductId));
    	 return new ResponseEntity<Object>(commitedProduct, HttpStatus.OK);
    }
    
 
  
 // Update a commitedproduct
    @PutMapping("/commitedproduct/{id}")
    public @ResponseBody ResponseEntity<Object>  updateCommitedProduct(@PathVariable(value = "id") int commitedproductId,								
                                            @Valid @RequestBody CommitedProduct commitedproductDetails) {

    	CommitedProduct commitedproduct = commitedProductService.getCommitedProduct(commitedproductId)
                .orElseThrow(() -> new ResourceNotFoundException("CommitedProduct", "id", commitedproductId));

    	commitedproduct.setName(commitedproductDetails.getName());
    	commitedproduct.setPrice(commitedproductDetails.getPrice());
    	commitedproduct.setDescription(commitedproductDetails.getDescription());
    	commitedproduct.setType(commitedproductDetails.getType());
    	commitedproduct.setQuantity(commitedproductDetails.getQuantity());
    	commitedproduct.setUserid(commitedproductDetails.getUserid());
        
        CommitedProduct updatedCommitedProduct = commitedProductService.updateCommitedProduct(commitedproductId,commitedproduct);
        return new ResponseEntity<Object>(updatedCommitedProduct, HttpStatus.OK);
    }
    
  


 // Delete a commitedproduct
    @DeleteMapping("/commitedproduct/{id}")
    public ResponseEntity<?> deleteCommitedProduct(@PathVariable(value = "id") int commitedproductId) {
    	CommitedProduct commitedproduct = commitedProductService.getCommitedProduct(commitedproductId)
                .orElseThrow(() -> new ResourceNotFoundException("CommitedProduct", "id", commitedproductId));

    	commitedProductService.deleteCommitedProduct(commitedproductId);

        return ResponseEntity.ok().build();
    }
    
 
}
