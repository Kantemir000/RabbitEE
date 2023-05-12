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
import domain.Product;

@WebServlet(name = "editLaptopServlet", value = "/editLaptopServlet")
public class editLaptopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editLaptopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	ConnectionProperty prop;
	String select_products = "SELECT * FROM product";
	ArrayList<Product> products = new ArrayList<Product>();
	String select_laptops = "SELECT * FROM laptop";
	ArrayList<Laptop> laptops = new ArrayList<Laptop>();
	ArrayList<Laptop> laptopEdit = new ArrayList<Laptop>();
	String insert_laptop = "INSERT INTO laptop (speed, ram, hd, screen, price, count, product) VALUES (?, ?, ?, ?, ?, ?, ?)";
	String select_laptop_ById = "SELECT * FROM laptop WHERE id = ?";
	String edit_laptop = "UPDATE laptop SET speed = ?, ram=?, hd=?, screen=?, price=?, count=?, product=? WHERE id = ?";
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
	
    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
            HttpServletResponse response)
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("view/laptop.jsp").forward(request, response);
//    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()) {
			String strId = request.getParameter("id");
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
				laptops.clear();
				while (rs.next()) {
					laptops.add(new Laptop(rs.getLong("id"), FindByModel(rs.getString("product"), products),
							rs.getString("speed"), rs.getString("ram"), rs.getString("hd"),rs.getString("screen"),rs.getString("price"),rs.getString("count")));
				}
				rs.close();
				request.setAttribute("laptopAll", laptops);
			}
			try (PreparedStatement preparedStatement = conn.prepareStatement(select_laptop_ById)) {
				Long id = null; 
				if(strId != null) {
					id = Long.parseLong(strId);
				}
				preparedStatement.setLong(1, id);
				stmt = conn.createStatement();
				rs = preparedStatement.executeQuery();
				if(rs != null) {
						laptopEdit.clear();
					while (rs.next()) {
						laptopEdit.add(new Laptop(rs.getLong("id"), FindByModel(rs.getString("product"), products),
								rs.getString("speed"), rs.getString("ram"), rs.getString("hd"),rs.getString("screen"),rs.getString("price"),rs.getString("count")));
				
					} 
					rs.close();
					request.setAttribute("laptopEdit", laptopEdit);
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
		if("/editLaptopServlet".equals(userPath)){
			request.getRequestDispatcher("view/laptopEdit.jsp")
					.forward(request, response);
		}
	}
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        doGet(request, response);
//    }
	
//	String insert_laptop = "INSERT INTO pcs (speed, ram, hd, screen, price, count, product) VALUES (?, ?, ?, ?, ?, ?, ?)";
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			System.out.println("dopost");
	    	EmpConnBuilder builder = new EmpConnBuilder();
	    	try (Connection conn = builder.getConnection()){
	    		String product = request.getParameter("model");
	    		String speed = request.getParameter("speed");
	    		String ram = request.getParameter("ram");
	    		String hd = request.getParameter("hd");
	    		String screen = request.getParameter("screen");
	    		String price = request.getParameter("price");
	    		String count = request.getParameter("count");
	    		

	    		try (PreparedStatement preparedStatement = conn.prepareStatement(edit_laptop)){
		    		String model = request.getParameter("model");
		    		String strId = request.getParameter("id");
		    		Long id = null; 
					if(strId != null) {
						id = Long.parseLong(strId);
					}
		    		int startIndex = model.indexOf("model") + "model = ".length(); 
		    		int endIndex = model.indexOf(",", startIndex);
		    		String modele = model.substring(startIndex, endIndex);
	    			System.out.println(modele);		
					preparedStatement.setString(1, speed);
					preparedStatement.setString(2, ram);
					preparedStatement.setString(3, hd);
					preparedStatement.setString(4, screen);
					preparedStatement.setString(5, price);
					preparedStatement.setString(6, count);
					preparedStatement.setString(7, modele);
					preparedStatement.setLong(8, id);
					int result = preparedStatement.executeUpdate();

	    		} catch (Exception e) {
	    			System.out.println(e);
	    		}
	    		
			} catch (Exception e) {
				System.out.println(e);
				getServletContext().getRequestDispatcher("/view/laptop.jsp").forward(request, response);
			}
		doGet(request, response);
		}
}
