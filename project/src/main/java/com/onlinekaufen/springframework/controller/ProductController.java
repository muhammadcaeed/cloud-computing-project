package com.onlinekaufen.springframework.controller;

import com.onlinekaufen.springframework.dto.ProductsDTO;
import com.onlinekaufen.springframework.dto.Response;
import com.onlinekaufen.springframework.dto.UserDTO;
import com.onlinekaufen.springframework.service.CategoryService;
import com.onlinekaufen.springframework.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final
    ProductService productService;

    private final
    CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String productDetail(Model model, @PathVariable("id") int prodId) {
        model.addAttribute("product", productService.getProductById(prodId));
        return "prodDetail";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchProduct(@RequestParam("search-item") String query, Model model) {
        Response<List<ProductsDTO>> response = productService.getProductsForAutoComplete(query);
        if (response.getStatus() == 200) {
            model.addAttribute("searchProducts", response.getData());
        } else {
            model.addAttribute("searchProducts", new ArrayList<>());
        }
        return "search";
    }

    @RequestMapping(value = "/myProducts", method = RequestMethod.GET)
    public String myProducts(Model model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof UserDTO) {
            model.addAttribute("products", productService.getProductBySellerId(((UserDTO) user).getId()));
        } else {
            model.addAttribute("products", new ArrayList<>());
        }
        return "myProducts";
    }

    @RequestMapping(value = "/productUpload", method = RequestMethod.GET)
    public String productUpload(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productUpload";
    }

    @RequestMapping(value = "/productUpload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public
    @ResponseBody
    Response productUpload(HttpServletRequest request, @RequestParam("product_image") MultipartFile multipartFile) {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDTO) {
            String productName = request.getParameter("product_name");
            LOGGER.info("Product Name is :" + productName);
            String slug = request.getParameter("product_slug");
            String productDescription = request.getParameter("product_description");
            int productCategory = Integer.valueOf(request.getParameter("product_category"));
            int qty = Integer.parseInt(request.getParameter("available_quantity"));
            Float productPrice = Float.parseFloat(request.getParameter("product_price"));
            int productType = Integer.valueOf(request.getParameter("product_type"));
            int productCondition = Integer.valueOf(request.getParameter("product_condition"));
            ProductsDTO product = new ProductsDTO();
            product.setProductName(productName);
            product.setSlug(slug);
            product.setDescription(productDescription);
            product.setCategoryId(productCategory);
            product.setType(productType);
            product.setProdCondition(productCondition);
            product.setStock(qty);
            product.setPrice(productPrice);
            product.setItemStatus(1);
            product.setSellerId(((UserDTO) object).getId());
            return productService.productUpload(product, multipartFile);
        } else {
            return Response.fail(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }
}
