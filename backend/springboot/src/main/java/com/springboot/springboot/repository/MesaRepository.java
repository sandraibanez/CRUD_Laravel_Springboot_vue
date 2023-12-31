package com.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.springboot.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    
     // Only categories
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) ) GROUP BY m.id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findCategoriesOnMesa(@Param("categories") String[] categories, 
            @Param("limit") Integer limit, @Param("offset") Integer offset);
//      // Only active
//      @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.name_mesa LIKE :name_mesa LIMIT :limit OFFSET :offset", nativeQuery = true)
//      List<Mesa> findActive(@Param("name_mesa") String name_mesa, @Param("limit") Integer limit,
//              @Param("offset") Integer offset);
 // Only active
     @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE LIMIT :limit OFFSET :offset", nativeQuery = true)
     List<Mesa> findActive( @Param("limit") Integer limit,
             @Param("offset") Integer offset);
 
    // Only capacity
    @Query(value = "SELECT * FROM mesas m WHERE m.capacity = :capacity AND m.is_active IS TRUE LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findByCapacity(@Param("capacity") Number capacity,
            @Param("limit") Integer limit, @Param("offset") Integer offset);

    // Capacity and categories
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) ) AND m.capacity = :capacity GROUP BY m.id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findByCapacityAndCategories(@Param("capacity") Number capacity,
            @Param("categories") String[] categories,
            @Param("limit") Integer limit, @Param("offset") Integer offset);

    // Only order ASC
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.name_mesa LIKE :name_mesa ORDER BY m.capacity ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findOrderedASC(@Param("name_mesa") String name_mesa, @Param("limit") Integer limit,
            @Param("offset") Integer offset);

    // Only order DESC
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.name_mesa LIKE :name_mesa ORDER BY m.capacity DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findOrderedDESC(@Param("name_mesa") String name_mesa, @Param("limit") Integer limit,
            @Param("offset") Integer offset);

    // Categories ASC
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) )  GROUP BY m.id ORDER BY m.capacity ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findCategoriesOnMesaASC(@Param("categories") String[] categories,
            @Param("limit") Integer limit, @Param("offset") Integer offset);

    // Categories DESC
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) ) GROUP BY m.id ORDER BY m.capacity DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Mesa> findCategoriesOnMesaDESC(@Param("categories") String[] categories,
            @Param("limit") Integer limit, @Param("offset") Integer offset);

 
    // BigestTables
    @Query(value = "SELECT * FROM mesas m WHERE m.is_active IS TRUE ORDER BY m.capacity DESC LIMIT :limit", nativeQuery = true)
    List<Mesa> findBigTables(@Param("limit") Integer limit);

     /// PAGINATE///

    // Only active
    @Query(value = "SELECT COUNT(*) FROM mesas m WHERE m.is_active IS TRUE AND m.name_mesa LIKE :name_mesa", nativeQuery = true)
    Integer findActivePaginate(@Param("name_mesa") String name_mesa);

    // Only categories
    @Query(value = "SELECT COUNT(*) FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) ) AND m.name_mesa LIKE :name_mesa", nativeQuery = true)
    Integer findCategoriesOnMesaPaginate(@Param("categories") String[] categories,
            @Param("name_mesa") String name_mesa);

    // Only capacity
    @Query(value = "SELECT COUNT(*) FROM mesas m WHERE m.capacity = :capacity AND m.is_active IS TRUE", nativeQuery = true)
    Integer findByCapacityPaginate(@Param("capacity") Number capacity);

    // Capacity and categories
    @Query(value = "SELECT COUNT(*) FROM mesas m WHERE m.is_active IS TRUE AND m.id IN( SELECT a.mesa_id FROM ( SELECT mc.mesa_id, COUNT(mc.category_id) as count_cat_id FROM mesas_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE c.name_category IN :categories GROUP BY mc.mesa_id) AS a WHERE a.count_cat_id >= ALL ( SELECT b.count_cat FROM ( SELECT COUNT(*) as count_cat FROM categories cc WHERE cc.name_category IN :categories ) b ) ) AND m.capacity = :capacity", nativeQuery = true)
    Integer findByCapacityAndCategoriesPaginate(@Param("capacity") Number capacity,
            @Param("categories") String[] categories);

    // List mesas of 1 user
    @Query(value = "SELECT DISTINCT m.* FROM mesas m INNER JOIN reservations r WHERE m.id = r.mesa_id AND r.user_id = :user_id", nativeQuery = true)
        List<Mesa> showUserReservation(@Param("user_id") Long user_id);
   
}