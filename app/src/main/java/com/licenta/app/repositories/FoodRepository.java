package com.licenta.app.repositories;

import com.licenta.app.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "SELECT * FROM foods WHERE name=:name",nativeQuery = true)
    Food findByName(String name);
}