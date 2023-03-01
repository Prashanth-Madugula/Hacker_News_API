package com.springboot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.project.model.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer>{
	
	@Query("SELECT i FROM Items i where i.time>:result order by i.score desc limit 10")
	public List<Items> getTop10Stories(@Param("result") long result);
	
	@Query("select i from Items i where i.id = :id")
	public List<Items> getComments(@Param("id") int id);
}
