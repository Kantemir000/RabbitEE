package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Laptop;
import domain.Product;
import domain.Pc;
import domain.Printer;
import java.sql.PreparedStatement;

@WebServlet(name = "editProductServlet", value = "/editProductServlet")
public class editProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
            HttpServletResponse response)
     */
	ConnectionProperty prop;
	String select_products = "SELECT * FROM product";
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<Product> productEdit = new ArrayList<Product>();
	String insert_products = "INSERT INTO product (model, maker, type) VALUES (?, ?, ?)";
	String select_product_ById = "SELECT * FROM product WHERE model = ?";
	String edit_product = "UPDATE product SET model = ?, maker=?, type=? WHERE model = ?";
	String userPath;

    
	 private Product FindByModel(String model, ArrayList<Product> products) {
		 if(products != null) {
			 for(Product r: products) {
				 if((r.getModel()).equals(model)) {
					 return r;
				 }
			 }
		 } else {return null;}
		 return null;
	 }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			EmpConnBuilder builder = new EmpConnBuilder();
			try (Connection conn = builder.getConnection()) {
				String strId = request.getParameter("id");
//				Long id = null; 
//				if(strId != null) {
//					id = Long.parseLong(strId);
//				}
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(select_products);
				if(rs != null) {
					products.clear();
					while (rs.next()) {
						products.add(new Product(rs.getString("model"), rs.getString("maker"), rs.getString("type")
								));
						System.out.println(rs.getString("model"));
					}
					rs.close();
					request.setAttribute("products", products);
				}
				try (PreparedStatement preparedStatement = conn.prepareStatement(select_product_ById)) {
					preparedStatement.setString(1, strId);
					stmt = conn.createStatement();
					rs = preparedStatement.executeQuery();
					if(rs != null) {
							productEdit.clear();
						while (rs.next()) {
							productEdit.add(new Product(rs.getString("model"), rs.getString("maker"), rs.getString("type")));
					
						} 
						rs.close();
						request.setAttribute("productEdit", productEdit);
					}
					else {
						System.out.println("Ошибка загрузки ");
					}
					} catch (Exception e) {
						System.out.println(e);
					}

			} catch (Exception e) {
				System.out.println(e);
			}
			userPath = request.getServletPath();
			if("/editProductServlet".equals(userPath)){
				request.getRequestDispatcher("/view/editProduct.jsp").forward(request, response);
			}
//			if("/homeServlet".equals(userPath)){
//				request.getRequestDispatcher("/index.jsp")
//						.forward(request, response);
//			}
		}
    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        doGet(request, response);
//    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

					System.out.println("dopost");
			    	EmpConnBuilder builder = new EmpConnBuilder();
			    	try (Connection conn = builder.getConnection()){
			    		
			    		String maker = request.getParameter("maker");
			    		String type = request.getParameter("type");
			    		String model = request.getParameter("model");
						System.out.println(maker);
			    		try (PreparedStatement preparedStatement = conn.prepareStatement(edit_product)){
							preparedStatement.setString(1, model);
							preparedStatement.setString(2, maker);
							preparedStatement.setString(3, type);
							preparedStatement.setString(4, model);
							int result = preparedStatement.executeUpdate();
			    			System.out.println(result);
			    		} catch (Exception e) {
			    			System.out.println(e);
			    		}
			    		
					} catch (Exception e) {
						System.out.println(e);
						getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
					}
				doGet(request, response);
				}
	 
}
