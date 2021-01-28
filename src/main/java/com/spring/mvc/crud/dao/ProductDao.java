package com.spring.mvc.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.crud.bean.ProductBean;

@Component // spring can automatically create object of ProductDao
public class ProductDao {

	@Autowired // spring can inject created object(spring-servlet.xml) in this valriable
	private HibernateTemplate hibernateTemplate;

	/* InsertORUpdate Product in database */
	@Transactional // Enable Transactional and it is used for turn on writing mode
	public void insertOrUpdateProduct(ProductBean productBean) {
//		this.hibernateTemplate.save(productBean); // Only for insert data

		/*
		 * saveOrUpdate : If id is available than it will perform Update operation and
		 * if id is not available than perform Save(insert) operation
		 */
		this.hibernateTemplate.saveOrUpdate(productBean);
	}

	/* Get all products from database */
	public List<ProductBean> getProducts() {
		List<ProductBean> getProductList = this.hibernateTemplate.loadAll(ProductBean.class);
		return getProductList;
	}

	/* Delete the single product based on productID */
	@Transactional // Enable Transactional and it is used for turn on writing mode
	public void deleteSingleProduct(int pid) {
		ProductBean getProductBean = this.hibernateTemplate.load(ProductBean.class, pid);
		this.hibernateTemplate.delete(getProductBean);
	}

	/* Get the single product based on productID */
	public ProductBean getSingleProduct(int pid) {
		ProductBean getProductBean = this.hibernateTemplate.get(ProductBean.class, pid);
		return getProductBean;
	}

}
