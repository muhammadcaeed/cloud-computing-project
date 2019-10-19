package com.onlinekaufen.springframework.dao;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ProductImagesDAO {

    @SqlUpdate("INSERT INTO product_images (product_id, file_name) VALUES (:prodId, :fileName)")
    int addProductImages(int prodId, String fileName);
}
