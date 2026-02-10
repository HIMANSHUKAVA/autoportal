package com.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.entity.Cars;

public interface carrepository extends JpaRepository<Cars, Integer> {

    // 1) Filter by only brand
    List<Cars> findByBrand(String brand);

    // 2) Filter by only type
    List<Cars> findByType(String type);

    // 3) Filter by brand + type
    List<Cars> findByBrandAndType(String brand, String type);

    // 4) Filter by price range
    List<Cars> findByPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(int min, int max);

    // 5) Fetch single car with all its images
    @Query("SELECT c FROM Cars c LEFT JOIN FETCH c.images WHERE c.id = :id")
    Cars findCarWithImages(int id);

    // 6) Full filter: brand + type + price
    List<Cars> findByBrandAndTypeAndPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(
        String brand,
        String type,
        int min,
        int max
    );
    List<Cars>findByFeaturedTrue();
    
    
}
