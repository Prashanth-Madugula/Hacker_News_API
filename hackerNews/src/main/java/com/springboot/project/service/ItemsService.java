package com.springboot.project.service;

import java.util.List;

import com.springboot.project.model.Items;

public interface ItemsService {
	Items saveItems(Items items);
	List<Items> getAllItems();
	Items getItemsById(int id);
	Items updateItems(Items items,int id);
	void deleteItems(int id);
	
	List<Items> getTop10Stories();
	List<Items> getPastStories();
	List<Items> getComments(int id);

}
