package com.onlinekaufen.springframework.dao;

import com.onlinekaufen.springframework.dto.CategoriesDTO;
import com.onlinekaufen.springframework.dto.CategoryCountDTO;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface CategoriesDAO {

    @SqlUpdate("INSERT INTO categories(category_name, parent_id) VALUES (:categoryName, :parentId);")
    int addCategories(CategoriesDTO categoriesDTO);

    @SqlQuery("SELECT * FROM categories")
    List<CategoriesDTO> getAllCategories();

    @SqlQuery("SELECT c.category_id, c.category_name, COUNT(p.id) AS category_count FROM products p INNER JOIN categories c on p.category_id = c.category_id WHERE p.stock>0 GROUP BY c.category_id")
    List<CategoryCountDTO> getCategoryCounts();

    @SqlQuery("SELECT * FROM categories WHERE category_id=:categoryId")
    CategoriesDTO getCategoryById(int categoryId);
}
