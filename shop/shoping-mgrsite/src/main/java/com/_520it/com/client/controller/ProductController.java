package com._520it.com.client.controller;

import com._520it.com.base.domain.*;
import com._520it.com.base.mapper.BrandMapper;
import com._520it.com.base.query.PageResult;
import com._520it.com.base.query.ProductQueryObject;
import com._520it.com.base.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductDescService productDescService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IProductCatalogPropertyValueService productCatalogPropertyValueService;
    @Autowired
    private IProductSkuService productSkuService;
    @Autowired
    private BrandMapper brandService;
    @Autowired
    private ICatalogService catalogService;


    /*
        商品列表的首页
     */
    @RequestMapping("productList")
    public String list(@ModelAttribute("qo") ProductQueryObject qo, Model model){
        //商品列表的展示
        PageResult pageResult = productService.query(qo);

        model.addAttribute("pageResult", pageResult);
        return "product/product_list";
    }

    @RequestMapping("/addProduct.do")
    public String addProduct(Model model){
        List<Brand> brandList = brandService.selectAll();
        List<Catalog> catalogList = catalogService.selectAll();
        model.addAttribute("brands", brandList);
        model.addAttribute("catalogs", catalogList);
        return "product/product_input";
    }

    /*
        根据id查询商品的详细信息
     */
    @RequestMapping("showProduct")
    public String showProduct(Long productId, Model model){
        //商品对象
        Product product = productService.selectByPrimaryKey(productId);
        model.addAttribute("product",product);

        //商品详细信息
        ProductDesc productDesc = productDescService.getDescByProductId(product.getId());
        model.addAttribute("productDesc",productDesc);

        //商品相册
        List<ProductImage> productImageList = productImageService.getImageByProductId(product.getId());
        model.addAttribute("productImageList",productImageList);

        //商品属性
        List<ProductCatalogPropertyValue> productCatalogPropertyValues = productCatalogPropertyValueService.getPropertyByProductId(product.getId());
        model.addAttribute("propertyList",productCatalogPropertyValues);

        //sku列表
        List<ProductSku> productSkuList = productSkuService.getSkuByProductId(product.getId());
        model.addAttribute("productSkuList",productSkuList);

        //sku列表中的属性,LinkHashSet是有序的不重复的
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (ProductSku productSku : productSkuList) {
            for (ProductSkuProperty productSkuProperty : productSku.getProductSkuPropertyList()) {
                set.add(productSkuProperty.getSkuProperty().getName());
            }
        }
        model.addAttribute("skuPropertyList",set);
        return "product/show_product";
    }


}
