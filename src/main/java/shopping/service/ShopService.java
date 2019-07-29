package shopping.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.Employee;
import shopping.model.Shop;
import shopping.repository.ShopRepository;





@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository; 
	
	
	
	public List <Shop> getALLShops(){
		List <Shop> shops = new ArrayList<>();
		shopRepository.findAll()
		.forEach(shops::add);
		return shops;
	}
	
	public Optional<Shop> getShop(long id) 
	{
	
		return	shopRepository.findById(id);
	}
	
	
	
	public Shop addShop (Shop shop) {
	return	shopRepository.save(shop);
	}

	public Shop updateShop(long id, Shop shop) {
		return	shopRepository.save(shop);
		}
	public void  deleteShop(long id) {
		shopRepository.deleteById(id);
	}
	
}
