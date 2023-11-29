package com.springboot.springboot.repository;

import java.util.List;

//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.springboot.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    
     // Only categories
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) )GROUP BY m.id LIMIT 9 OFFSET 0", nativeQuery = true)
    List<Mesa> findCategoriesOnMesa(@Param("categories") String[] categories);
   
    // BigestTables
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE ORDER BY m.capacity DESC LIMIT :limit", nativeQuery = true)
    List<Mesa> findBigTables(@Param("limit") Integer limit);
   
}
// public interface MesaRepository extends JpaRepository<Mesa, Long> {

//         // Only active
//         @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.name_mesa LIKE :name_mesa ", nativeQuery = true)
//         List<Mesa> findActive(@Param("name_mesa") String name_mesa);

//         // Only categories
//         @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) ) AND m.name_mesa LIKE :name_mesa GROUP BY m.id LIMIT 9 OFFSET 0", nativeQuery = true)
//         List<Mesa> findCategoriesOnMesa(@Param("categories") String[] categories, @Param("name_mesa") String name_mesa);
// }