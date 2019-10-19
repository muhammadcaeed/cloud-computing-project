package com.onlinekaufen.springframework.service.mySql;

import com.google.common.base.Throwables;
import com.onlinekaufen.springframework.dao.ProductDAO;
import com.onlinekaufen.springframework.dao.PurchaseDAO;
import com.onlinekaufen.springframework.dto.*;
import com.onlinekaufen.springframework.service.ProductService;
import com.onlinekaufen.springframework.utility.FileHandingFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@PropertySource("classpath:application.properties")
public class ProductServiceImpl implements ProductService {

    public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${file.path}")
    private String filePath;

    @Value("${image.path}")
    private String imagePath;

    @Value("${amazon.bucket.url}")
    private String amazonBucketURL;

    private final
    DbService dbService;

    @Autowired
    public ProductServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<BuyingProductsDTO> getBuyingProducts(HashMap<Integer, CartSessionDTO> cartProducts) {
        List<Integer> prodIds = new ArrayList<>();
        for (Map.Entry<Integer, CartSessionDTO> integerCartSessionEntry : cartProducts.entrySet()) {
            CartSessionDTO sessObj = (CartSessionDTO) ((Map.Entry) integerCartSessionEntry).getValue();
            prodIds.add(sessObj.getItemId());
        }
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<BuyingProductsDTO> products = productDAO.getBuyingProducts(prodIds);
        for (Map.Entry<Integer, CartSessionDTO> integerCartSessionEntry : cartProducts.entrySet()) {
            CartSessionDTO sessObj = (CartSessionDTO) ((Map.Entry) integerCartSessionEntry).getValue();
            for (BuyingProductsDTO buyingProductsDTO : products) {
                buyingProductsDTO.setImgPath(amazonBucketURL + File.separator + buyingProductsDTO.getImgPath());
                if (sessObj.getItemId() == buyingProductsDTO.getId()) {
                    buyingProductsDTO.setQty(sessObj.getItemQty());
                }
            }
        }
        return products;
    }

    @Override
    public Response<List<ProductsDTO>> getProductsForAutoComplete(String query) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<ProductsDTO> products = productDAO.getProductsForAutoComplete(query);
        if (products.isEmpty()) {
            return Response.ok(new ArrayList<>(), HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            for (ProductsDTO productsDTO : products) {
                productsDTO.setImgPath(amazonBucketURL + File.separator + productsDTO.getImgPath());
            }
            return Response.ok(products, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        }
    }

    @Override
    public List<ProductsDTO> getProducts() {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<ProductsDTO> products = productDAO.getProducts();
        for (ProductsDTO productsDTO : products) {
            productsDTO.setImgPath(amazonBucketURL + File.separator + productsDTO.getImgPath());
        }
        return products;
    }

    @Override
    public List<ProductsDTO> getFilteredProducts(Optional<String> sortBy, Optional<String> orderBy, Optional<Integer> category) {
        String query = " WHERE p.stock > 0";
        String sortQuery = "";

        if (sortBy.isPresent()) {
            if (sortBy.get().equalsIgnoreCase("Price")) {
                sortQuery = " ORDER BY p.price ";
            } else {
                sortQuery = " ORDER BY p.product_name ";
            }
        }
        if (orderBy.isPresent()) {
            sortQuery += orderBy.get();
        }
        if (category.isPresent()) {
            query += " AND p.category_id=" + category.get();
        }
        query = query + sortQuery;
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        logger.info("The query created is " + query);
        List<ProductsDTO> filteredProducts = productDAO.getFilteredProds(query);
        for (ProductsDTO product : filteredProducts) {
            product.setImgPath(amazonBucketURL + File.separator + product.getImgPath());
        }
        return filteredProducts;
    }

    @Override
    public ProductsDTO getProductById(int productId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        ProductsDTO productsDTO = productDAO.getProductById(productId);
        productsDTO.setImgPath(amazonBucketURL + File.separator + productsDTO.getImgPath());
        return productsDTO;
    }

    @Override
    public List<ProductsDTO> getProductBySellerId(int sellerId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<ProductsDTO> products = productDAO.getProductsBySellerId(sellerId);
        for (ProductsDTO productsDTO : products) {
            productsDTO.setImgPath(amazonBucketURL + File.separator + productsDTO.getImgPath());
        }
        return products;
    }

    @Override
    public Integer getProductQtyById(int productId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        return productDAO.getProductQtyById(productId);
    }

    @Override
    public Response<String> productUpload(ProductsDTO product, MultipartFile file) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        try {

            String path = FileHandingFunctions.createFiles(file, filePath).getName();
            product.setImgPath(path);
            productDAO.productUpload(product);
            return Response.ok("Product uploaded successfully", HttpStatus.OK.value(), "Product uploaded successfully.");
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public Response buyProducts(List<BuyingProductsDTO> buyingProductDTOS) {
        try {
            UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            purchaseDTO.setBuyerId(userDTO.getId());
            PurchaseDAO purchaseDAO = dbService.getDao(PurchaseDAO.class);
            ProductDAO productDAO = dbService.getDao(ProductDAO.class);
            int purchaseId = purchaseDAO.insertPurchase(purchaseDTO);
            //logger.info("The purchase id is : " + purchaseId);
            List<PurchaseItemsDTO> purchaseItemsDTOList = new ArrayList<>();
            float total = 0F;
            for (BuyingProductsDTO buyingProduct : buyingProductDTOS) {
                PurchaseItemsDTO purchaseItemsDTO = new PurchaseItemsDTO();
                purchaseItemsDTO.setPurchaseId(purchaseId);
                purchaseItemsDTO.setProductId(buyingProduct.getId());
                purchaseItemsDTO.setQuantity(buyingProduct.getQty());
                purchaseItemsDTO.setSubtotal(buyingProduct.getQty() * buyingProduct.getPrice());
                total += (buyingProduct.getQty() * buyingProduct.getPrice());
                purchaseItemsDTOList.add(purchaseItemsDTO);
            }
            purchaseDAO.insertPurchaseItems(purchaseItemsDTOList.iterator());
            productDAO.buyProduct(purchaseItemsDTOList.iterator());
            return Response.ok("Successfully checked out.", HttpStatus.OK.value(), "Successfully bought the products.");
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error buying the product. Please try again.");
        }
    }

    @Override
    public List<SoldItemsDTO> getPurchasesByBuyerId(int buyerId) {
        PurchaseDAO purchaseDAO = dbService.getDao(PurchaseDAO.class);
        return purchaseDAO.getPurchasesByBuyerId(buyerId);
    }

    @Override
    public List<SoldItemsDTO> getPurchasesBySellerId(int sellerId) {
        PurchaseDAO purchaseDAO = dbService.getDao(PurchaseDAO.class);
        return purchaseDAO.getPurchasesBySellerId(sellerId);
    }
}
