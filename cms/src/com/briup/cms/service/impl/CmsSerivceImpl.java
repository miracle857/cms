package com.briup.cms.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.cms.bean.Article;
import com.briup.cms.bean.Category;
import com.briup.cms.dao.ICmsDao;
import com.briup.cms.service.ICmsService;

@Service
public class CmsSerivceImpl implements ICmsService{

	@Autowired
	ICmsDao dao;
	
	@Override
	public List<Category> listAllCategory() throws Exception {
		List<Category> list = dao.listAllCategory();
		return list;
	}

	@Override
	public void saveCategory(Category category) throws Exception {
		dao.saveCategory(category);
	}

	@Override
	public void saveArticle(Article article) throws Exception {
		article.setClickTimes(0);
		article.setPublisurDate(new Date());
		dao.saveArticle(article);
	}

	@Override
	public Category findCategoryByid(Long id) throws Exception {
		return dao.findCategoryByid(id);
	}

	@Override
	public List<Article> listAllArtcles() throws Exception {
		return dao.listAllArtcles();
	}

	@Override
	public Article findArticleById(Long id) throws Exception {
		
		return dao.findArticleById(id);
	}

	@Override
	public void updateArticleClickTimes(Long id) throws Exception {
		Article findArticle = dao.findArticleById(id);
		findArticle.setClickTimes(findArticle.getClickTimes().intValue()+1);
		dao.updateArticleClickTimes(findArticle);
		
	}

	@Override
	public void deleteCategoryById(Long id) throws Exception {
		Category category = new Category();
		category.setId(id);
		deleteArticle(category);
		dao.deleteCategoryById(id);
	}

	@Override
	public void deleteArticle(Category category) throws Exception {
		dao.deleteArticle(category);
	}

	@Override
	public List<Article> searchArticle(String key,String value,String page) throws Exception {
		long p = 0L;
		System.out.println(page);
		try{
			if("".equals(page)){
				p = 1L;
			}else{
				p = Long.parseLong(page);
			}	
		}catch(NumberFormatException e){
			p = 1L;
		}
		Article article = new Article();
		article.setId(3*p-2);
		//article.setClickTimes(3*Integer.parseInt(page));
		article.setClickTimes(new Long(3*p).intValue());
		if(!("".equals(value))){
			article.setContent("%"+value+"%");
		}
		
		if ("".equals(key)) {}
		if ("title".equals(key)) {
			article.setTitle(key);
		}
		if ("author".equals(key)) {
			article.setAuthor(key);
		}
		if ("category".equals(key)) {
			Category category = new Category();
			category.setName(key);
			article.setCategory(category);
		}
		System.out.println(":"+article);
		return dao.searchArticle(article);
	}

	@Override
	public void deleteBatchArticle(Long[] articleId) throws Exception {
		for (Long id : articleId) {
			dao.deleteArticleById(id);
		}
	}

}
