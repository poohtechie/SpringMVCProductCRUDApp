package com.spring.mvc.crud.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.mvc.crud.bean.ProductBean;
import com.spring.mvc.crud.dao.ProductDao;

@Controller
public class ProductController {

	@Autowired // Spring create object of productDao and inject it in this variable
	private ProductDao productDao;

	/* Show Home Page */
	@RequestMapping("/")
	public String homePage(Model model) {
		/* Get all products from database */
		List<ProductBean> getProductList = productDao.getProducts();
		/* Send productList to the index.jsp page */
		model.addAttribute("productListKey", getProductList);

		return "index";
	}

	/* Show Add Product Form */
	@RequestMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("titleKey", "Add Product");
		return "add_product_form";
	}

	/* Handle Add/Update Product form on click of Add/Update Button */
	@RequestMapping(value = "/processProduct", method = RequestMethod.POST)
	public RedirectView processAddUpdateProduct(@ModelAttribute ProductBean productBean, HttpServletRequest request) {
		System.out.println(productBean);

		/* Insert product in database */
		productDao.insertOrUpdateProduct(productBean);

		RedirectView redirectView = new RedirectView();
		/*
		 * Redirect on Home Page using request.getContextPath() because if we use only
		 * "/" than our projectName will be removed from url and give error
		 */
		redirectView.setUrl(request.getContextPath() + "/");

		return redirectView;
	}

	/* Show Update Product Form */
	@RequestMapping("/updateProduct/{productId}")
	public String updateProduct(@PathVariable("productId") int productId, Model model) {
		/* Get Product data and send it to the view */
		ProductBean getProductBean = this.productDao.getSingleProduct(productId);
		model.addAttribute("productBeanKey", getProductBean);
		model.addAttribute("titleKey", "Edit Product");

		return "update_product_form";
	}

	/* Delete Product */
	@RequestMapping("/deleteProduct/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		/* Delete product from database based on productID */
		this.productDao.deleteSingleProduct(productId);

		RedirectView redirectView = new RedirectView();
		/*
		 * Redirect on Home Page using request.getContextPath() because if we use only
		 * "/" than our projectName will be removed from url and give error
		 */
		redirectView.setUrl(request.getContextPath() + "/");

		return redirectView;
	}

}
