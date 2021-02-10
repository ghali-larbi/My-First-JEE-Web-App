/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import dao.ArticleDao;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;

/**
 *
 * @author DELL
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     public static ArticleDao articledao=new ArticleDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String action = request.getServletPath();

		try {
			switch (action) {
			case "/listeArticle":
				listArticle(request, response);
				break;
                        case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertArticle(request, response);
				break;
			case "/delete":
				deleteArticle(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateArticle(request, response);
				break;
                        }
			
		} catch (Exception ex) {
                   printStackTrace();
		}
    }
  private void listArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Article> listArticle = articledao.selectAllArticles();
		request.setAttribute("listArticle", listArticle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listeArticle.jsp");
		dispatcher.forward(request, response);
               
	}
   private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("PageFormArticle.jsp");
		dispatcher.forward(request, response);
	}
    private void insertArticle(HttpServletRequest request, HttpServletResponse response) 
		throws SQLException, IOException {
		String name = request.getParameter("name");
		Article newArticle= new Article(name);
                System.out.println(newArticle.getName());
	        articledao.insertArticle(newArticle);
		response.sendRedirect("listeArticle");
	}
 private void deleteArticle(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		articledao.deleteArticle(id);
		response.sendRedirect("listeArticle");

        }
 private void updateArticle(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Article book = new Article(id, name);
		articledao.updateArticle(book);
		response.sendRedirect("listeArticle");
	}
 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
         	throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Article existingUser = articledao.selectArticle(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PageFormArticle.jsp");
		request.setAttribute("article", existingUser);
		dispatcher.forward(request, response);
	}
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     this.doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
