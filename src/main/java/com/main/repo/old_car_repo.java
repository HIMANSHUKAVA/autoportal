package com.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.entity.Cars;
import com.main.entity.Old_car;

public interface old_car_repo extends JpaRepository<Old_car, Integer> {

    // ✔ brand
    List<Old_car> findByBrand(String brand);

    // ✔ type
    List<Old_car> findByType(String type);

    // ✔ brand + type
    List<Old_car> findByBrandAndType(String brand, String type);

    // ✔ price range (SIRF price)
    List<Old_car> findByPriceGreaterThanEqualAndPriceLessThanEqual(
        int min,
        int max
    );

    // ✔ single car with images
    @Query("SELECT c FROM Old_car c LEFT JOIN FETCH c.images WHERE c.id = :id")
    Old_car findCarWithImages(int id);

    // ✔ brand + type + price
    List<Old_car> findByBrandAndTypeAndPriceGreaterThanEqualAndPriceLessThanEqual(
        String brand,
        String type,
        int min,
        int max
    );
    
    List<Old_car>findByFeaturedTrue();
}
