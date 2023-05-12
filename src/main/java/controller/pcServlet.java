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
import domain.Pc;
import domain.Product;

@WebServlet(name = "pcServlet", value = "/pcServlet")
public class pcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pcServlet() {
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
	String select_pc = "SELECT * FROM pcs";
	ArrayList<Pc> pcAll = new ArrayList<Pc>();
	String userPath;
	String insert_pc = "INSERT INTO pcs (speed, ram, hd, cd, price, count, product) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
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
//        request.getRequestDispatcher("view/pc.jsp").forward(request, response);
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
				rs = stmt.executeQuery(select_pc);
				if(rs != null) {
					pcAll.clear();
					while (rs.next()) {
						pcAll.add(new Pc(rs.getLong("id"), FindByModel(rs.getString("product"), products),
								rs.getString("speed"), rs.getString("ram"), rs.getString("hd"),rs.getString("cd"),rs.getString("price"),rs.getString("count")));
					}
					rs.close();
					request.setAttribute("pcAll", pcAll);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			userPath = request.getServletPath();
			if("/pcServlet".equals(userPath)){
				request.getRequestDispatcher("view/pc.jsp")
						.forward(request, response);
			}
		}
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			System.out.println("dopost");
	    	EmpConnBuilder builder = new EmpConnBuilder();
	    	try (Connection conn = builder.getConnection()){
	    		String product = request.getParameter("model");
	    		String speed = request.getParameter("speed");
	    		String ram = request.getParameter("ram");
	    		String hd = request.getParameter("hd");
	    		String sd = request.getParameter("sd");
	    		String price = request.getParameter("price");
	    		String count = request.getParameter("count");
	    		

	    		try (PreparedStatement preparedStatement = conn.prepareStatement(insert_pc)){
		    		String model = request.getParameter("model");
		    		
		    		int startIndex = model.indexOf("model") + "model = ".length(); 
		    		int endIndex = model.indexOf(",", startIndex);
		    		String modele = model.substring(startIndex, endIndex);
	    			System.out.println(modele);		
					preparedStatement.setString(1, speed);
					preparedStatement.setString(2, ram);
					preparedStatement.setString(3, hd);
					preparedStatement.setString(4, sd);
					preparedStatement.setString(5, price);
					preparedStatement.setString(6, count);
					preparedStatement.setString(7, modele);
					int result = preparedStatement.executeUpdate();

	    		} catch (Exception e) {
	    			System.out.println(e);
	    		}
	    		
			} catch (Exception e) {
				System.out.println(e);
				getServletContext().getRequestDispatcher("/view/pc.jsp").forward(request, response);
			}
		doGet(request, response);
		}

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        doGet(request, response);
//    }
}
