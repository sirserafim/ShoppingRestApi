package shopping.controller;

import shopping.exception.ResourceNotFoundException;
import shopping.model.Employee;
import shopping.model.Shop;
import shopping.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/sh")
public class ShopController {

	
	 @Autowired
	 private  ShopService shopService;
	    
   
 // Get All /shops
	 @GetMapping("/allshops")
	    public @ResponseBody ResponseEntity<Object> getallShops() {
	    List<Shop> entityList = shopService.getALLShops();
	    return new ResponseEntity<Object>(entityList, HttpStatus.OK);
	    } 
	 

 // Create a new shop  
    @PostMapping("/shop")
    public @ResponseBody ResponseEntity<Object> createShop(@Valid @RequestBody Shop shop){
    	shop = shopService.addShop(shop);
    return new ResponseEntity<Object>(shop, HttpStatus.OK);
    }

 // Get a Single Shop
    @GetMapping("/shop/{id}")
    public @ResponseBody ResponseEntity<Object> getShopId(@PathVariable(value = "id") int shopId) {
    	Shop shop = shopService.getShop(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));
    	 return new ResponseEntity<Object>(shop, HttpStatus.OK);
    }
    

 // Update a Shop
    @PutMapping("/shop/{id}")
    public @ResponseBody ResponseEntity<Object> updateShop(@PathVariable(value = "id") int shopId,
                                            @Valid @RequestBody Shop shopDetails) {

        Shop shop = shopService.getShop(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        shop.setName(shopDetails.getName());
        shop.setCountry(shopDetails.getCountry());
        shop.setCounty(shopDetails.getCounty());
        shop.setCountry(shopDetails.getCountry());		
        shop.setPhone(shopDetails.getPhone());
        shop.setAddress(shopDetails.getAddress());
  
        
        Shop updatedShop = shopService.updateShop(shopId,shop);
        return new ResponseEntity<Object>(updatedShop, HttpStatus.OK);
    }

 // Delete a Shop
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable(value = "id") int shopId) {
    	Shop shop = shopService.getShop(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

    	shopService.deleteShop(shopId);

        return ResponseEntity.ok().build();
    }
    
   

}
