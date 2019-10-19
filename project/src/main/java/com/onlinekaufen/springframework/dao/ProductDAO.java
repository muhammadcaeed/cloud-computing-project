package com.onlinekaufen.springframework.dao;

import com.onlinekaufen.springframework.dto.BuyingProductsDTO;
import com.onlinekaufen.springframework.dto.ProductsDTO;
import com.onlinekaufen.springframework.dto.PurchaseItemsDTO;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.Iterator;
import java.util.List;

@UseStringTemplate3StatementLocator
@RegisterMapperFactory(BeanMapperFactory.class)
public interface ProductDAO {

    @SqlQuery("SELECT p.*, c.category_name FROM products p INNER JOIN categories c on p.category_id = c.category_id  WHERE stock>0 AND id IN (<prodIdList>)")
    List<BuyingProductsDTO> getBuyingProducts(@BindIn("prodIdList") List<Integer> prodList);

    @SqlQuery("SELECT p.*, c.category_name FROM products p INNER JOIN categories c on p.category_id = c.category_id WHERE p.product_name LIKE concat('%',:query,'%') OR c.category_name LIKE concat('%',:query,'%') AND p.stock > 0")
    List<ProductsDTO> getProductsForAutoComplete(@Bind("query") String query);

    @SqlQuery("SELECT p.*, c.category_name FROM products p INNER JOIN categories c on p.category_id = c.category_id WHERE p.stock > 0")
    List<ProductsDTO> getProducts();

    @SqlQuery("SELECT p.*, c.category_name FROM products p INNER JOIN categories c on p.category_id = c.category_id WHERE p.seller_id = :sellerId")
    List<ProductsDTO> getProductsBySellerId(@Bind("sellerId") int sellerId);

    @SqlQuery("SELECT p.*, c.category_name FROM products p INNER JOIN categories c on p.category_id = c.category_id WHERE p.id = :productId")
    ProductsDTO getProductById(@Bind("productId") int productId);

    @SqlQuery("SELECT p.stock FROM products p INNER JOIN categories c on p.category_id = c.category_id WHERE p.id = :productId")
    Integer getProductQtyById(@Bind("productId") int productId);

    @SqlUpdate("INSERT INTO products(product_name, slug, description, price, category_id, type, height, length, depth, weight, prod_condition, seller_id, stock, publication_date, img_path) VALUES (:productName, :slug, :description, :price, :categoryId, :type, :height, :length, :depth, :weight, :prodCondition, :sellerId, :stock, NOW(), :imgPath)")
    @GetGeneratedKeys
    int productUpload(@BindBean ProductsDTO product);

    @SqlBatch("UPDATE products p set p.stock = (p.stock - :quantity) WHERE p.id = :productId")
    void buyProduct(@BindBean Iterator<PurchaseItemsDTO> purchaseItems);

    @SqlQuery("SELECT p.*, c.category_name FROM products p INNER JOIN categories c on p.category_id = c.category_id <query>")
    List<ProductsDTO> getFilteredProds(@Define("query") String query);
}