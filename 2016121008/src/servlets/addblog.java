package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DBUtils;

import entity.user;
import entity.Blogtext;
/**
 * Servlet implementation class addblog
 */
@WebServlet("/addblog.do")
public class addblog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addblog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		HttpSession session = request.getSession();
		
		
		String bid = request.getParameter("bid");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String uId = request.getParameter("uId");
		String content = request.getParameter("container");
		if(DBUtils.doAddblog( bid, name, type, uId, content))
		{
			request.getRequestDispatcher("me.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("me.jsp").forward(request, response);
		}
	}

}
