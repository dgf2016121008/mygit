package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DBUtils;
import entity.user;


/**
 * Servlet implementation class Dologin
 */
@WebServlet("/dologin")
public class Dologin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dologin() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		user user = new user();
		user.setuId(request.getParameter("uId"));
		user.setUpwd(request.getParameter("upwd"));
		String uId = request.getParameter("uId");
		request.setAttribute("uId",uId);		
		if(DBUtils.doLogin(user))
		{
			 request.getRequestDispatcher("me.jsp").forward(request, response); 		
		}else{
			 request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	

	}


