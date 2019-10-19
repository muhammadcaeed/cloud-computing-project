package com.onlinekaufen.springframework.service;

import com.onlinekaufen.springframework.dto.CategoriesDTO;
import com.onlinekaufen.springframework.dto.CategoryCountDTO;

import java.util.List;

public interface CategoryService {

    CategoriesDTO addCategories(CategoriesDTO categoriesDTO);

    List<CategoriesDTO> getAllCategories();

    CategoriesDTO getCategoryById(int categoryId);

    List<CategoryCountDTO> getCategoryCounts();
}
