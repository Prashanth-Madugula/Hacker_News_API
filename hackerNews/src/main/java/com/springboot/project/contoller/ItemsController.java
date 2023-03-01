package com.springboot.project.contoller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.model.Items;
import com.springboot.project.service.ItemsService;

@RestController
public class ItemsController {
	
	private ItemsService itemsService;

	public ItemsController(ItemsService itemsService) {
		super();
		this.itemsService = itemsService;
	}
	@PostMapping("/items/add")
	public ResponseEntity<Items> saveItems(@RequestBody Items items){
		return new ResponseEntity<Items>(itemsService.saveItems(items),HttpStatus.CREATED);
	}
	
	@GetMapping("/items")
	public List<Items> getAllItems(){
		return itemsService.getAllItems();
	}
	
	@GetMapping("/items/get/{id}")
	public ResponseEntity<Items> getItemsById(@PathVariable("id") int id){
		return new ResponseEntity<Items>(itemsService.getItemsById(id),HttpStatus.OK);
	}
	
	@PutMapping("/items/update/{id}")
	public ResponseEntity<Items> updateItems(@PathVariable("id") int id,@RequestBody Items items){
		return new ResponseEntity<Items>(itemsService.updateItems(items,id),HttpStatus.OK);
	}
	
	@DeleteMapping("/items/delete/{id}")
	public ResponseEntity<String> deleteItems(@PathVariable("id") int id){
		itemsService.deleteItems(id);
		return new ResponseEntity<String>("Item deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/top-stories")
	@Cacheable("items")
	public List<Items> getTop10Stories() {
		return itemsService.getTop10Stories();
	}
    
	@GetMapping("/past-stories")
    public List<Items> getPastStories(){
		
		return itemsService.getTop10Stories();
	}
	
	@GetMapping("/comments/{id}")
	public List<Items> getComments(@PathVariable("id") int id){
		return itemsService.getComments(id);
	}
    
}
