package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.Product;
import com.xmg.api.domain.ProductCatalogPropertyValue;
import com.xmg.api.domain.ProductDesc;
import com.xmg.api.domain.ProductImage;
import com.xmg.api.domain.ProductSku;
import com.xmg.api.domain.ProductSkuProperty;
import com.xmg.api.qury.PageResult;
import com.xmg.api.qury.ProductQueryObject;
import com.xmg.api.service.IProductService;
import com.xmg.api.vo.ProductVo;
import com.xmg.server.mapper.ProductCatalogPropertyValueMapper;
import com.xmg.server.mapper.ProductDescMapper;
import com.xmg.server.mapper.ProductImageMapper;
import com.xmg.server.mapper.ProductMapper;
import com.xmg.server.mapper.ProductSkuMapper;
import com.xmg.server.mapper.ProductSkuPropertyMapper;

@Service("productService")
public class ProductServiceImpl implements IProductService{

	@Autowired
	ProductMapper productDao;
	
	@Autowired
	ProductDescMapper productDescDao;
	
	@Autowired
	ProductImageMapper productImageDao;
	
	@Autowired
	ProductCatalogPropertyValueMapper productCatalogPropertyValueDao;
	
	@Autowired
	ProductSkuMapper productSkuDao;
	
	@Autowired
	ProductSkuPropertyMapper productSkuPropertyMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Product record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return productDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(Product record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int queryCount(ProductQueryObject qo) {
		// TODO Auto-generated method stub
		return productDao.queryCount(qo);
	}

	@Override
	public PageResult query(ProductQueryObject qo) {
		// TODO Auto-generated method stub
		int count = this.queryCount(qo);
		if(count == 0){
			return PageResult.empty(qo.getPageSize());
		}
		List<Product> list = productDao.query(qo);
		return new PageResult(list,count,qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public Product save(ProductVo vo) {
		// TODO Auto-generated method stub
		Product product = vo.getProduct();
		ProductDesc productDesc = vo.getProductDesc();
		productDao.insert(product);
		productDesc.setProductId(product.getId());
		productDescDao.insert(productDesc);
		
		//保存商品相册
		List<ProductImage> productImages = vo.getProductImages();
		for (ProductImage productImage : productImages) {
			productImage.setProductId(product.getId());
			//因为商品图片前台是写死了8张图片的，所以这里判断一下如果图片的路径为null则不插入数据库
			if(productImage.getImagePath() != null && !productImage.getImagePath().equals("")){
				
				productImageDao.insert(productImage);
			}
		}
		
		//保存商品属性
		List<ProductCatalogPropertyValue> propertys = vo.getPropertys();
		for (ProductCatalogPropertyValue productCatalogPropertyValue : propertys) {
			productCatalogPropertyValue.setProductId(product.getId());
			productCatalogPropertyValueDao.insert(productCatalogPropertyValue);
		}
		
		//保存商品sku
		List<ProductSku> productSkuList = vo.getProductSkuList();
		for (ProductSku productSku : productSkuList) { 
			productSku.setMods(1L);
			productSku.setProduct(product);
			productSkuDao.insert(productSku);
			
			//获取sku中的属性列表
			List<ProductSkuProperty> productSkuPropertyList = productSku.getProductSkuPropertyList();
			for (ProductSkuProperty productSkuProperty : productSkuPropertyList) {
				productSkuProperty.setProductSkuId(productSku.getId());
				//保存sku中的属性列表
				productSkuPropertyMapper.insert(productSkuProperty);
			}
		}
		return product;
	}

}













