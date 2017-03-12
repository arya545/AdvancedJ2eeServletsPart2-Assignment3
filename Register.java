package com.inf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.inf.Dao.RegisterDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static boolean checkEmpty(String value)
    {
    	if (!(value == null))
    	{
    		return value.isEmpty();
    	}
    	return true;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String userName=request.getParameter("userName");
		String password =request.getParameter("password");
		String email=request.getParameter("email");
		String country=request.getParameter("userCountry");
		
		/*try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "arya", "arya");
			
			PreparedStatement ps= con.prepareStatement("insert into REGISTERUSER values (?,?,?,?)");
			
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, country);*/
			
		if ((checkEmpty(userName))  ||  (checkEmpty(password))  || (checkEmpty(email)) || checkEmpty(country))
		{
			
				out.println("All fields are mandatory");
				RequestDispatcher rd=request.getRequestDispatcher("/register.html");
				rd.include(request, response);
		}
		else
		{
		
			
			int i= RegisterDao.register(userName, password, email, country)	;		
			if (i>0)
			{
				//out.println("Successfully registered!");
				RequestDispatcher rd=request.getRequestDispatcher("welcomeRegistration");
				rd.forward(request, response);
			}
			else
			{
				out.println("Not successfully registered!!");
			}
		/*}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}*/
		out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
