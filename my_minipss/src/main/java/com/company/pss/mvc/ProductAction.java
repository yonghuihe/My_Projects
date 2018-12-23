package com.company.pss.mvc;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.Product;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IBrandService;
import com.company.pss.service.IProductService;
import com.company.pss.util.FileUploadUtil;
import com.company.pss.util.RequiredPermission;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private IBrandService brandService;
	private QueryObject queryObject = new QueryObject();
	private Product product = new Product();
	/**
	 * 上传的文件对象
	 */
	private File pic;
	/**
	 * 上传的文件名称
	 */
	private String picFileName;

	@RequiredPermission("Product列表")
	public String execute() {
		this.addContext(PAGERESULT, this.productService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑Product")
	public String input() {
		this.addContext("brands", this.brandService.list());
		if (product.getId() != null) {
			product = this.productService.get(product.getId());
		}
		return INPUT;
	}

	public void prepareSave() {
		if (product.getId() != null) {
			//会把查询出来的对象放到一级缓存中
			product = this.productService.get(product.getId());
			// 打破关联关系
			product.setBrand(null);
		}
	}

	@RequiredPermission("修改Product")
	public String save() throws Exception {
		// 检查文件pic是否为空
		if (pic != null) {
			//是否是编辑，如果是编辑，删除之前的照片
			if(product.getId()!=null){
				//找到之前的图片，判断之前的图片是否为空，如果不为空，删除掉
				Product p = productService.get(product.getId());
				String oldImagePath = p.getImagePath();
				// 如果之前没有上传图片，就执行删除操作
				if(oldImagePath != null){
					FileUploadUtil.deleteFile(oldImagePath);
				}
			}
			//把上传的文件拷贝到web项目的根目录中的upload文件夹中
			String imagePath = FileUploadUtil.uploadFile(pic, picFileName);
			product.setImagePath(imagePath);
		}
		if (product.getId() != null) {
			this.productService.update(product);
			this.addActionMessage("修改成功");
		} else {
			this.productService.save(product);
			this.addActionMessage("保存成功");
		}
		return SUCCESS;
	}

	@RequiredPermission("删除Product")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (product.getId() != null) {
				//获取图片地址，如果有图片，删除图片
				product = productService.get(product.getId());
				if(product.getImagePath()!=null){
					FileUploadUtil.deleteFile(product.getImagePath());
				}
				this.productService.delete(product);
			}
			response.getWriter().write("删除成功！");
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员！" + e.getMessage());
		}
		return NONE;
	}
	
	public String view() {
		this.addContext(PAGERESULT, this.productService.query(queryObject));
		return "view";
	}

	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

}
