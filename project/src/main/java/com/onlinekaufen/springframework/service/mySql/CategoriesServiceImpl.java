package com.onlinekaufen.springframework.service.mySql;

import com.onlinekaufen.springframework.dao.CategoriesDAO;
import com.onlinekaufen.springframework.dto.CategoriesDTO;
import com.onlinekaufen.springframework.dto.CategoryCountDTO;
import com.onlinekaufen.springframework.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoryService {

    private final
    DbService dbService;

    @Autowired
    public CategoriesServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public CategoriesDTO addCategories(CategoriesDTO categoriesDTO) {
        CategoriesDAO categoriesDAO = dbService.getDao(CategoriesDAO.class);
        int id = categoriesDAO.addCategories(categoriesDTO);
        categoriesDTO.setCategoryId(id);
        return categoriesDTO;
    }

    @Override
    public List<CategoriesDTO> getAllCategories() {
        CategoriesDAO categoriesDAO = dbService.getDao(CategoriesDAO.class);
        return categoriesDAO.getAllCategories();
    }

    @Override
    public CategoriesDTO getCategoryById(int categoryId) {
        CategoriesDAO categoriesDAO = dbService.getDao(CategoriesDAO.class);
        return categoriesDAO.getCategoryById(categoryId);
    }

    @Override
    public List<CategoryCountDTO> getCategoryCounts() {
        CategoriesDAO categoriesDAO = dbService.getDao(CategoriesDAO.class);
        return categoriesDAO.getCategoryCounts();
    }
}
