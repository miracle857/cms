package com.briup.cms.service;

import java.util.List;

import com.briup.cms.bean.Article;
import com.briup.cms.bean.Category;

public interface ICmsService {

	/**
	 * 查询所有的栏目
	 * 
	 * @return List<Category>
	 */
	List<Category> listAllCategory() throws Exception;

	/**
	 * 保存栏目
	 * 
	 * @param category
	 * @throws Exception
	 */
	void saveCategory(Category category) throws Exception;

	/**
	 * 保存article信息
	 * 
	 * @param article
	 * @throws Exception
	 */
	void saveArticle(Article article) throws Exception;

	/**
	 * 根据ID，查询Category
	 * 
	 * @param id
	 * @return Category
	 * @throws Exception
	 */
	Category findCategoryByid(Long id) throws Exception;

	/**
	 * 查询所有的文章
	 * 
	 * @return List<Article>
	 * @throws Exception
	 */
	List<Article> listAllArtcles() throws Exception;

	/**
	 * 根据ID查文章
	 * 
	 * @return Article
	 * @throws Exception
	 */
	Article findArticleById(Long id) throws Exception;

	/**
	 * 跟新文章的点击次数
	 * 
	 * @param article
	 * @throws Exception
	 */
	void updateArticleClickTimes(Long id) throws Exception;

	/**
	 * 删除category
	 * 
	 * @param id
	 * @throws Exception
	 */
	void deleteCategoryById(Long id) throws Exception;

	/**
	 * 根据category删除article
	 * 
	 * @param category
	 * @throws Exception
	 */
	void deleteArticle(Category category) throws Exception;

	/**
	 * 搜索文章
	 * 
	 * @param article
	 * @return
	 * @throws Exception
	 */
	List<Article> searchArticle(String key, String keyword, String page) throws Exception;

	/**
	 * 批量删除文章
	 * 
	 * @param articleId
	 * @throws Exception
	 */
	void deleteBatchArticle(Long[] articleId) throws Exception;
}
