package com.solv.inventory.dao;

import com.solv.inventory.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Page<Item> findByNameContaining(String title, Pageable pageable);
    List<Item> findByPriceBetween(double minPrice,double maxPrice);

    Page<Item> findByCategory(String category,Pageable pageable);


    @Query("SELECT i from Item i WHERE (i.category LIKE CONCAT('%',:category,'%') OR Coalesce(:category,'') = '') and i.price>=:minPrice and i.price<=:maxPrice")
    List<Item> findByCategoryAndPriceBetween(@Param("category") String category,@Param("minPrice") double minPrice,@Param("maxPrice") double maxPrice);

    @Query("Select i from Item i WHERE i.name LIKE CONCAT('%',:query,'%')"+
    "OR i.category LIKE CONCAT('%',:query,'%')")
    List<Item> findItemBasedOnQuery(@Param("query") String query);

    @Query("SELECT i from Item i WHERE i.category LIKE CONCAT ('%'=:category,'%') and i.price>=:minPrice and i.price<=:maxPrice")
    List<Item> findItemsBasedOnParameters(@Param("category")String category,@Param("minPrice")double minPrice,@Param("maxPrice")double maxPrice);
}
