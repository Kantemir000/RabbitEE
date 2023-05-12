package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Laptop;
import domain.Printer;
import domain.Product;

@WebServlet(name = "printerServlet", value = "/printerServlet")
public class printerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public printerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	ConnectionProperty prop;
	String select_products = "SELECT * FROM product";
	ArrayList<Product> products = new ArrayList<Product>();
	String select_laptops = "SELECT * FROM printer";
	ArrayList<Printer> printerAll = new ArrayList<Printer>();
	String insert_products = "INSERT INTO printer (product, color, type, price, count) VALUES (?, ?, ?, ?, ?)";
	String userPath;
	
    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
            HttpServletResponse response)
     */
	
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
	 
	 
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("view/printer.jsp").forward(request, response);
//    }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			EmpConnBuilder builder = new EmpConnBuilder();
			try (Connection conn = builder.getConnection()) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(select_products);
				if(rs != null) {
					products.clear();
					while (rs.next()) {
						products.add(new Product(
								rs.getString("model"), rs.getString("maker"), rs.getString("type")
								));
						System.out.println(rs.getString("model"));
					}
					rs.close();
					request.setAttribute("products", products);
				}

				stmt = conn.createStatement();
				rs = stmt.executeQuery(select_laptops);
				if(rs != null) {
					printerAll.clear();
					while (rs.next()) {
						printerAll.add(new Printer(rs.getLong("id"), FindByModel(rs.getString("product"), products),
								rs.getString("color"), rs.getString("type"), rs.getString("price"),rs.getString("count")));
					}
					rs.close();
					request.setAttribute("printerAll", printerAll);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			userPath = request.getServletPath();
			if("/printerServlet".equals(userPath)){
				request.getRequestDispatcher("view/printer.jsp")
						.forward(request, response);
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			System.out.println("dopost");
	    	EmpConnBuilder builder = new EmpConnBuilder();
	    	try (Connection conn = builder.getConnection()){
	    		String product = request.getParameter("model");
	    		String color = request.getParameter("color");
	    		String type = request.getParameter("type");
	    		String price = request.getParameter("price");
	    		String count = request.getParameter("count");
				System.out.println(product);
	    		try (PreparedStatement preparedStatement = conn.prepareStatement(insert_products)){
		    		String model = request.getParameter("model");
		    		int startIndex = model.indexOf("model") + "model = ".length(); 
		    		int endIndex = model.indexOf(",", startIndex);
		    		String modele = model.substring(startIndex, endIndex);
	    			System.out.println(modele);		
					preparedStatement.setString(1, modele);
					preparedStatement.setString(2, color);
					preparedStatement.setString(3, type);
					preparedStatement.setString(4, price);
					preparedStatement.setString(5, count);
					int result = preparedStatement.executeUpdate();

	    		} catch (Exception e) {
	    			System.out.println(e);
	    		}
	    		
			} catch (Exception e) {
				System.out.println(e);
				getServletContext().getRequestDispatcher("/view/printer.jsp").forward(request, response);
			}
		doGet(request, response);
		}

}
