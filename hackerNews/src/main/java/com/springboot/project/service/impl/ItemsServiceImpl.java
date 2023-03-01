package com.springboot.project.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.project.exception.ResourceNotFoundException;
import com.springboot.project.model.Items;
import com.springboot.project.repository.ItemsRepository;
import com.springboot.project.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService{
	private ItemsRepository itemsRepository;

	public ItemsServiceImpl(ItemsRepository itemsRepository) {
		super();
		this.itemsRepository = itemsRepository;
	}

	@Override
	@Scheduled(fixedRate =  1*60*1000)
	public List<Items> getTop10Stories() {
		
		long currentTimestamp = Instant.now().toEpochMilli();
        //Date expiry = new Date( currentTimestamp * 1000 );
        
		long millis=TimeUnit.MINUTES.toMillis(15);
		long result=currentTimestamp-millis;
		return itemsRepository.getTop10Stories(result);
	}

	@Override
	public List<Items> getPastStories() {

		return null;
	}

	@Override
	@Cacheable(value="items",key="#id")
	public List<Items> getComments(int id) {
		List<Items> items=itemsRepository.getComments(id);
		for(Items item:items) {
				List<Integer> limited_items=new ArrayList<Integer>();
				for(int i=0;i<10;i++) {
					System.out.println(i);
					limited_items.add(item.getKids().get(i));
			}
				item.setKids(limited_items);
		}
		return items;
	}

	@Override
	public Items saveItems(Items items) {

		return itemsRepository.save(items);
	}

	@Override
	public List<Items> getAllItems() {

//		Instant instant=Instant.now();
//		Date date=Date.from(instant);
//		
		
		return itemsRepository.findAll();
	}

	@Override
	@Cacheable(value="items",key="#id")
	@Scheduled(fixedDelay = 1000)
	public Items getItemsById(int id) {
		
		return itemsRepository.findById(id).orElseThrow(()->
		                new ResourceNotFoundException("Items", "Id", id));
		
	}

	@Override
	public Items updateItems(Items items, int id) {
		Items existingItems=itemsRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException("Items", "Id", id));
		existingItems.setBy_user(items.getBy_user());
		existingItems.setDead(items.isDead());
		existingItems.setId(items.getId());
		itemsRepository.save(existingItems);
		return existingItems;
	}

	@Override
	public void deleteItems(int id) {
		itemsRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException("Items", "Id", id));
		itemsRepository.deleteById(id);
	}

	
	

}
