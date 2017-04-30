package com.briup.cms.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.cms.bean.Article;
import com.briup.cms.bean.Category;
import com.briup.cms.service.ICmsService;

@Controller
public class CmsController {

	@Autowired
	ICmsService cmsServiceImpl;
	
	/**
	 * 修改article信息 
	 */
	@RequestMapping("updateArticleInfo.do")
	public String updateArticleInfo(Model model,Long articleId){
		Article article = null;
		List<Category> categories = null;
		try {
			article = cmsServiceImpl.findArticleById(articleId);
			categories = cmsServiceImpl.listAllCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("article", article);
		model.addAttribute("categories", categories);
		return "manager/updateArticleInfo";
	}
	
	@RequestMapping("list.html")
	public String listArticles(int id,HttpSession session,Model model){
		List<Category> categories = (List<Category>) session.getAttribute("categories");
		for (Category c : categories) {
			if(c.getId() == id){
				model.addAttribute("category", c);
				System.out.println(c.getArticles());
				break;
			}
		}
		return "list";
	}
	/**
	 * 批量删除文章
	 */
	@RequestMapping("deleteMoreArticle.action")
	public void deleteMoreArticle(String checked,HttpServletResponse response){
		String[] s = checked.split("[,]");
		System.out.println(s.length);
		Long[] articles = new Long[s.length];
		for (int i = 0; i < s.length; i++) {
			articles[i] = Long.parseLong(s[i]);
		}
		try {
			cmsServiceImpl.deleteBatchArticle(articles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.print("");
		writer.flush();
		writer.close();;
		return;
	}
	/**
	 * 首页 
	 */
	@RequestMapping("index")
	public String index(HttpSession session) {
		List<Category> categories = listCategory();
		List<Article> listAllArtcles = null;
		try {
			listAllArtcles = cmsServiceImpl.listAllArtcles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Category> list = dealCategoryAndArticle(categories, listAllArtcles);
		//model.addAttribute("categories", list);
		session.setAttribute("categories", list);
		return "index";
	}

	/**
	 * 增加category 
	 */
	@RequestMapping("manager/addCategory.do")
	public void addCategory(Category category) {
		System.out.println(category);
		try {
			cmsServiceImpl.saveCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 列出所有category信息 
	 */
	@RequestMapping("categoryList.do")
	public String listCategory(Model model) {
		model.addAttribute("categoryList", listCategory());
		return "manager/categoryList";
	}

	/**
	 * 列出所有category信息 
	 */
	@RequestMapping("articlePublisur.action")
	public String a1(Model model) {
		model.addAttribute("categoryList", listCategory());
		return "manager/articlePublisur";
	}

	/**
	 * 发表article 
	 */
	@RequestMapping("manager/articlePublisur.do")
	public void articlePublisur(Article article, Long categoryId) {
		try {
			article.setCategory(cmsServiceImpl.findCategoryByid(categoryId));
			cmsServiceImpl.saveArticle(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据关键字查询article
	 */
	@RequestMapping("articleListContent.do")
	public String articleListContentNew(Model model, String key, String value,String page){
		List<Article> list = null;
		System.out.println(""+page);
		try {
			list = cmsServiceImpl.searchArticle(key,value,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("articles", list);
		return "manager/articleListContent";
	}
	
	/**
	 * 点击文章后增加点击次数
	 */
	@RequestMapping("toContent.action")
	public String toContent(Long id, Model model) {
		Article article = null;
		try {
			//更新文章点击数
			//其实更新方法有返回值更好...
			cmsServiceImpl.updateArticleClickTimes(id);
			article = cmsServiceImpl.findArticleById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("article", article);
		return "content";
	}
	
	/**
	 * 删除栏目（需级联删除文章） 
	 */
	@RequestMapping("manager/deleteCategory.action")
	public void deleteCategory(Long categoryId,HttpServletResponse response){
		PrintWriter writer = null;
		try {
			cmsServiceImpl.deleteCategoryById(categoryId);
			writer = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//要随便写点东西，不然可能不能正常调用  回调函数
		writer.print("123");
		writer.flush();
		writer.close();
	}
	
	// 私有方法
	private List<Category> listCategory() {
		List<Category> list = null;
		try {
			list = cmsServiceImpl.listAllCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Category> dealCategoryAndArticle(List<Category> categories, List<Article> articles) {
		for (Category c : categories) {
			for (Article a : articles) {
				if (a.getCategory().getName().equals(c.getName())) {
					c.getArticles().add(a);
				}
			}
		}
		return categories;
	}

}
