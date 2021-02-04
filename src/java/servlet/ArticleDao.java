/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ArticleDao {
     private String jdbcURL = "jdbc:mysql://localhost:3306/article?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_ARTICLE_SQL = "INSERT INTO article" + "(name) VALUES (?)";
	private static final String SELECT_ARTICLE_BY_ID = "select id,name from article where id =?";
	private static final String SELECT_ALL_ARTICLE = "select * from article";
	private static final String DELETE_ARTICLE_SQL = "delete from article where id = ?;";
	private static final String UPDATE_ARTICLE_SQL = "update article set name = ? where id = ?;";
        
        protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
//insert article
        public void insertArticle(Article article) throws SQLException {
		System.out.println(INSERT_ARTICLE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE_SQL)) {
			preparedStatement.setString(1, article.getName());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
        
//update article
        public boolean updateArticle(Article article) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE_SQL);) {
			        statement.setString(1, article.getName());
                                statement.setInt(2, article.getId());
			        rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
                
//select article by id
        public Article selectArticle(int id) {
		Article article = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
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
		try (Connection connection = getConnection();

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
        public boolean deleteArticle(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
