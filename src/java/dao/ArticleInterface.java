/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Article;

/**
 *
 * @author DELL
 */
public interface ArticleInterface {
   void insertArticle(Article article);
   boolean updateArticle(Article article);
   Article selectArticle(int id);
   List<Article> selectAllArticles();
   boolean deleteArticle(int id);
    
}
