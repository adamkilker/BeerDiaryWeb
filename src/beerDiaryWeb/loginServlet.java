package beerDiaryWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if(user.isEmpty() || pass.isEmpty())
		{
			out.println("Username or Password is empty");
		}
		else{
		
			try{
				Class.forName("com.mysql.jdbc.Driver");
		Connection connection = JDBCMySQLConnection.getConnection();
		PreparedStatement stmt = connection.prepareStatement ("select * from users where userName =?");
		stmt.setString(1, user);
		
		ResultSet rs = stmt.executeQuery();
		
		String securePass = User.get_SHA_1_SecurePassword(pass);
		
		if(rs.getString(3)== securePass)
		{
			response.sendRedirect("interface.html");
		}
		else{
			out.print("Incorrect Password");
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}//end else

}
}
