package com.xmg.mgrsite.web;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xmg.api.domain.Brand;
import com.xmg.api.domain.Catalog;
import com.xmg.api.domain.CatalogProperty;
import com.xmg.api.domain.CatalogPropertyValue;
import com.xmg.api.domain.Product;
import com.xmg.api.domain.ProductCatalogPropertyValue;
import com.xmg.api.domain.ProductDesc;
import com.xmg.api.domain.ProductImage;
import com.xmg.api.domain.ProductSku;
import com.xmg.api.domain.ProductSkuProperty;
import com.xmg.api.domain.SkuProperty;
import com.xmg.api.domain.SkuPropertyValue;
import com.xmg.api.qury.PageResult;
import com.xmg.api.qury.ProductQueryObject;
import com.xmg.api.service.IBrandService;
import com.xmg.api.service.ICatalogPropertyService;
import com.xmg.api.service.ICatalogService;
import com.xmg.api.service.IProductCatalogPropertyValueService;
import com.xmg.api.service.IProductDescService;
import com.xmg.api.service.IProductImageService;
import com.xmg.api.service.IProductService;
import com.xmg.api.service.IProductSkuService;
import com.xmg.api.service.ISkuPropertyService;
import com.xmg.api.service.ISkuService;
import com.xmg.api.vo.JSONResult;
import com.xmg.api.vo.ProductCatalogPropertyVo;
import com.xmg.api.vo.ProductVo;
import com.xmg.api.vo.SkuGenerateFormVo;
import com.xmg.mgrsite.util.UploadUtil;

@Controller
public class ProductController {

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private ICatalogService catalogService;
	
	@Autowired
	private ICatalogPropertyService catalogPropertyService;
	
	@Autowired
	private ISkuPropertyService skuPropertyService;
	
	@Autowired
	private IProductImageService productImageService;
	
	@Autowired
	private ISkuService skuService;
	
	@Autowired
	private IProductDescService productDescService;
	
	@Autowired
	private IProductCatalogPropertyValueService productCatalogPropertyValueService;
	
	@Autowired
	private IProductSkuService productSkuService;
	
	@RequestMapping("/productList.do")
	public String productList(ProductQueryObject qo,Model model){
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
	
	@RequestMapping("/productSave.do")
	@ResponseBody
	public JSONResult productSave(ProductVo vo){
		JSONResult jsonResult = new JSONResult();
		try {
			productService.save(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
	}
	
	@RequestMapping("/productImgUpload.do")
	@ResponseBody
	public String productImgUpload(MultipartFile file) {
		// 先得到basepath
		String basePath = servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		return "/upload/" + fileName;
	}
	
	/**
	 * 通过商品选择的分类，获取分类属性和对应的分类属性值，返回商品属性面板
	 * @return
	 */
	@RequestMapping("/getCatalogPropertyPanel.do")
	public String getCatalogProperty(Model model,Long catalogId){
		
		List<ProductCatalogPropertyVo> productCatalogPropertyList = new ArrayList<ProductCatalogPropertyVo>();
		
		List<CatalogProperty> catalogPropertyList = catalogPropertyService.getCatalogProperty(catalogId);
		
		for (CatalogProperty catalogProperty : catalogPropertyList) {
			
			ProductCatalogPropertyVo productCatalogPropertyVo = new ProductCatalogPropertyVo();
			
			productCatalogPropertyVo.setCatalogPropertyName(catalogProperty.getName());
			
			productCatalogPropertyVo.setType(catalogProperty.getType());
			
			List<CatalogPropertyValue> catalogPropertyValue = catalogPropertyService.getCatalogPropertyValue(catalogProperty.getId());
			
			productCatalogPropertyVo.setPropertyValueList(catalogPropertyValue);
			
			productCatalogPropertyList.add(productCatalogPropertyVo);
		}
		model.addAttribute("productCatalogPropertyList",productCatalogPropertyList);
		return "product/catalog_property_panel";
	}
	/**
	 * 获取sku主面板
	 * @param model
	 * @param catalogId
	 * @return
	 */
	@RequestMapping("/getSkuPropertyPanel.do")
	public String getSkuProperty(Model model,Long catalogId){
		List<SkuProperty> skuPropertyList = skuPropertyService.getSkuProperty(catalogId);
		model.addAttribute("skuPropertyList", skuPropertyList);
		return "product/sku_property_panel";
	}
	
	/**
	 * 获取sku属性值表格
	 * @param model
	 * @param propertyId
	 * @return
	 */
	@RequestMapping("/getSkuPropertyValueTable.do")
	public String getSkuPropertyValueTable(Model model,Long skuPropertyId){
		SkuProperty skuProperty = skuPropertyService.selectByPrimaryKey(skuPropertyId);
		List<SkuPropertyValue> skuPropertyValue = skuPropertyService.getSkuPropertyValue(skuPropertyId);
		model.addAttribute("skuPropertyValue", skuPropertyValue);
		model.addAttribute("skuProperty", skuProperty);
		return "product/sku_property_value_table";
		
	}
	
	/**
	 * 生成sku列表
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/generateSkuList.do")
	public String generateSkuList(Model model,@RequestBody SkuGenerateFormVo vo){
		List<Map<String,Object>> skuList = skuService.generateSku(vo);
		model.addAttribute("skuList", skuList);
		model.addAttribute("skuPropertieList", vo.getSkuPropertieList());
		return "product/sku_table";
	}
	
	/**
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/showProduct.do")
	public String showProduct(Long productId,Model model){
		
		//商品对象
		Product product = productService.selectByPrimaryKey(productId);
		model.addAttribute("product",product);
		
		//所有品牌
		List<Brand> brandList = brandService.selectAll();
		model.addAttribute("brands", brandList);
		
		//所有分类
		List<Catalog> catalogList = catalogService.selectAll();
		model.addAttribute("catalogs", catalogList);
		
		//商品详细介绍
		ProductDesc productDesc = productDescService.getDescByProductId(product.getId());
		model.addAttribute("productDesc", productDesc);
		
		//商品相册
		List<ProductImage> productImageList = productImageService.getImageByProductId(product.getId());
		model.addAttribute("productImageList", productImageList);
		
		//商品属性
		List<ProductCatalogPropertyValue> propertyList = productCatalogPropertyValueService.getPropertyByProductId(productId);
		model.addAttribute("propertyList", propertyList);
		
		//sku列表
		List<ProductSku> productSkuList = productSkuService.getSkuByProductId(productId);
		model.addAttribute("productSkuList", productSkuList);
		
		//suk列表里的属性,LinkedHashSet是有序并不重复的
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		for (ProductSku productSku : productSkuList) {
			for (ProductSkuProperty productSkuProperty : productSku.getProductSkuPropertyList()) {
				
				set.add(productSkuProperty.getSkuProperty().getName());
			}
		}
		model.addAttribute("skuPropertyList", set);
		
		return "product/show_product";
	}
}















