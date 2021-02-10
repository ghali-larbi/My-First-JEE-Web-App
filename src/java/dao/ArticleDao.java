/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connexion.connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Article;

/**
 *
 * @author DELL
 */
public class ArticleDao implements ArticleInterface{
         connexion conx=new connexion();
    private static final String INSERT_ARTICLE_SQL = "insert into article(name) VALUES (?)";
	private static final String SELECT_ARTICLE_BY_ID = "select id,name from article where id =?";
	private static final String SELECT_ALL_ARTICLE = "select * from article";
	private static final String DELETE_ARTICLE_SQL = "delete from article where id = ?;";
	private static final String UPDATE_ARTICLE_SQL = "update article set name = ? where id = ?;";
        
         
         public void insertArticle(Article article)  {
		
		try (Connection connection = conx.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE_SQL)) {
			preparedStatement.setString(1, article.getName());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
        
//update article
         
        public boolean updateArticle(Article article) {
		boolean rowUpdated = false;
		try (Connection connection = conx.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE_SQL);) {
			        statement.setString(1, article.getName());
                                statement.setInt(2, article.getId());
			        rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException ex) {
                 Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
             }
		return rowUpdated;
	}
                
//select article by id
        public Article selectArticle(int id) {
		Article article = null;
		// Step 1: Establishing a Connection
		try (Connection connection = conx.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				article = new Article(id, name);
			}
		}catch (SQLException e) {
			printSQLException(e);
		}
		return article;
	}
        public List<Article> selectAllArticles() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Article> articles = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = conx.getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLE);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				articles.add(new Article(id, name));
			}
		} catch (SQLException e) {
			 printSQLException(e);
		}
		return articles;
	}
        public boolean deleteArticle(int id) {
		boolean rowDeleted = false;
		try (Connection connection = conx.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException ex) {
                 Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
             }
		return rowDeleted;
	}

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
