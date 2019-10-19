package com.onlinekaufen.springframework.service;

import com.onlinekaufen.springframework.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<BuyingProductsDTO> getBuyingProducts(HashMap<Integer, CartSessionDTO> cartProducts);

    Response<List<ProductsDTO>> getProductsForAutoComplete(String query);

    List<ProductsDTO> getProducts();

    List<ProductsDTO> getFilteredProducts(Optional<String> sortBy, Optional<String> order, Optional<Integer> category);

    ProductsDTO getProductById(int productId);

    List<ProductsDTO> getProductBySellerId(int sellerId);

    Integer getProductQtyById(int productId);

    Response productUpload(ProductsDTO product, MultipartFile file);

    Response buyProducts(List<BuyingProductsDTO> buyingProductDTOS);

    List<SoldItemsDTO> getPurchasesByBuyerId(int buyerId);

    List<SoldItemsDTO> getPurchasesBySellerId(int sellerId);
}
