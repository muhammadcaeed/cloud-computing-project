package com.onlinekaufen.springframework.dao;

import com.onlinekaufen.springframework.dto.PurchaseDTO;
import com.onlinekaufen.springframework.dto.PurchaseItemsDTO;
import com.onlinekaufen.springframework.dto.SoldItemsDTO;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.Iterator;
import java.util.List;

@UseStringTemplate3StatementLocator
@RegisterMapperFactory(BeanMapperFactory.class)
public interface PurchaseDAO {

    @SqlBatch("INSERT INTO purchase_items (purchase_id, product_id, quantity, subtotal) VALUES (:purchaseId, :productId, :quantity, :subtotal)")
    void insertPurchaseItems(@BindBean Iterator<PurchaseItemsDTO> purchaseItems);

    @SqlUpdate("INSERT INTO purchases(buyer_id, total, purchase_date) VALUES (:buyerId, :total, NOW())")
    @GetGeneratedKeys
    int insertPurchase(@BindBean PurchaseDTO purchaseDTO);

    @SqlQuery("SELECT p.product_name, p.price, pu.purchase_date, pi.* FROM purchase_items pi INNER JOIN purchases pu on pi.purchase_id = pu.id INNER JOIN products p on pi.product_id = p.id WHERE pu.buyer_id = :buyerId ORDER BY pu.purchase_date DESC")
    List<SoldItemsDTO> getPurchasesByBuyerId(@Bind("buyerId") int buyerId);

    @SqlQuery("SELECT p.product_name, p.price, pu.purchase_date, pi.* FROM purchase_items pi INNER JOIN purchases pu on pi.purchase_id = pu.id INNER JOIN products p on pi.product_id = p.id WHERE p.seller_id = :sellerId ORDER BY pu.purchase_date DESC")
    List<SoldItemsDTO> getPurchasesBySellerId(@Bind("sellerId") int sellerId);
}
