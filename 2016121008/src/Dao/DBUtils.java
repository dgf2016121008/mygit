package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Blogtext;

import entity.user;

public class DBUtils {
	private static String driverName;
	private static String url;
	private static String pwd;
	private static String user;
	static{
		driverName ="com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/blog?characterEncoding=UTF-8";
		user= "root";
		pwd = "1996dgf1018";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			Connection connection =DriverManager.getConnection(url,user,pwd);
			//System.out.println("链接成功");
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//----------------------------------------判断连接是否成功-----------------------------------------------------------------------------------
	

	
	public static void check(Connection connection) {
		try {
			
			if(connection!=null){
				System.out.println("连接成功！");
			}else{
				System.out.println("连接失败！");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//----------------------------------------关闭资源------------------------------------------------------------------------------------
	
	
	
	public static void close(Connection connection,Statement statement,ResultSet rs){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
//----------------------------------------用户注册添加------------------------------------------------------------------------------------
	
	
	
	public static boolean doRegiser(user user) {
		Connection  connection  = null;
		connection = DBUtils.getConnection();
		PreparedStatement prstatement = null;
		PreparedStatement prstatement2 = null;
		PreparedStatement prstatement3 = null;
		PreparedStatement prstatement4 = null;
		ResultSet  resultSet = null;
		try {
			prstatement = connection.prepareStatement("SELECT * FROM user where uId=?");
			prstatement.setString(1, user.getuId());
			resultSet = prstatement.executeQuery();
			if(resultSet.next()){
				System.out.println("您输入的ID已被占用");
				System.out.println(user.getuId());
				return false;
			}else{
				prstatement2 = connection.prepareStatement("INSERT INTO user(uId,uname,usex,upwd,email,uaddr) VALUES(?,?,?,?,?,?)");
				prstatement2.setString(1,user.getuId() );
				prstatement2.setString(2, user.getUname());
				prstatement2.setString(3, user.getUsex());
				prstatement2.setString(4,user.getUpwd());
				prstatement2.setString(5,user.getUaddr());
				prstatement2.setString(6,user.getEmail());
				prstatement2.executeUpdate();
				
				
				
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtils.close(connection, prstatement2, resultSet);			
		}
		return false;
	}
	
//----------------------------------------用户登录检验------------------------------------------------------------------------------------
	public static boolean doLogin(user user) {
		Connection  connection  = null;
		connection = DBUtils.getConnection();
		PreparedStatement prstatement = null;
		
		ResultSet  resultSet = null;
		try {
			prstatement = connection.prepareStatement("SELECT upwd FROM user where uId=?");
			prstatement.setString(1,user.getuId());
			resultSet = prstatement.executeQuery();
			if(resultSet.next()){
				if(resultSet.getString("upwd").equals(user.getUpwd())){
				System.out.println(user.getUpwd());
				System.out.println(resultSet.getString("upwd"));
				System.out.println("账号和密码匹配");
				return true;
				}
			}else{
				
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtils.close(connection, prstatement, resultSet);
			
		}
		return false;
	}

	
	
	
//----------------------------------------用户个人信息展示------------------------------------------------------------------------------------
			public static user doQP(String uId) {
				Connection  connection  = null;
				connection = DBUtils.getConnection();
				PreparedStatement prstatement = null;
				System.out.println(uId);
				ResultSet  resultSet = null;
				try {
					prstatement = connection.prepareStatement("select user.uId,user.uname,users.usex,user.uaddr,user.email from user WHERE user.uId=?  ");
					prstatement.setString(1, uId);
					prstatement.setString(2, uId);
					prstatement.setString(3, uId);
					resultSet = prstatement.executeQuery();
					if(resultSet!=null){
						while(resultSet.next()){
							user users = new user();
							users.setUname(resultSet.getString("uname"));
							users.setuId(resultSet.getString("uId"));
							users.setUsex(resultSet.getString("usex"));
							users.setUaddr(resultSet.getString("uaddr"));
							users.setEmail(resultSet.getString("email"));
							
							return users;
						}
					}else{
						return null;
						}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, prstatement, resultSet);
				}
				return null;
			
			}

			//----------------------------------------用户信息更新------------------------------------------------------------------------------------
			
			public static boolean doUupdate(String uId,String uname,String uaddr,String email,String usex,String upwd) {
				Connection  connection  = null;
				PreparedStatement statement= null;
				ResultSet  resultSet = null;
				System.out.println(uId);
				try {
					connection = DBUtils.getConnection();
					statement = connection.prepareStatement("UPDATE user SET user.uname=?,user.upwd=?,user.usex=?,user.uaddr=?,WHERE users.uId=? ");
					statement.setString(1, uname);
					statement.setString(2, upwd);
					statement.setString(3, usex);
					statement.setString(4,uaddr);
					statement.setString(5, email);
					
					statement.executeUpdate();
					return true;
											
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, statement, resultSet);
				}
				return false;
			
			}

			//----------------------------------------博文信息查询------------------------------------------------------------------------------------		
			
			public static List<Blogtext> doBQuery() {
				Connection  connection  = null;
				PreparedStatement statement = null;
				ResultSet  resultSet = null;
				List<Blogtext> list = new ArrayList<>();
				try {
					connection = DBUtils.getConnection();
					statement = connection.prepareStatement("select * from Blogtext");
					resultSet = statement.executeQuery();
					if(resultSet!=null){
						while(resultSet.next()){
							Blogtext blog = new  Blogtext();
							blog.setBid(resultSet.getString("bid"));
							blog.setName(resultSet.getString("name"));
							blog.setuId(resultSet.getString("uId"));
							blog.setType(resultSet.getString("type"));
							blog.setContent(resultSet.getString("content"));
							list.add(blog);
						}
						return list;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, statement, resultSet);
				}
				return null;
			
			}
			
			//----------------------------------------新增博文信息------------------------------------------------------------------------------------
			
			public static boolean doAddblog(String bid,String name,String type,String uId,String content) {
				Connection  connection  = null;
				PreparedStatement statement = null;
				ResultSet  resultSet = null;
				
				try {
					connection = DBUtils.getConnection();
					statement = connection.prepareStatement("INSERT into Blogtext( bid,name,type,uId,content) VALUES(?,?,?,?,?)");
					statement.setString(1, bid);
					statement.setString(2, name);
					statement.setString(3, type);
					statement.setString(4, uId);
					statement.setString(5,content);
					statement.executeUpdate();
						return true;
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, statement, resultSet);
				}
				return false;
			
			}
			
			//----------------------------------------博文信息删除------------------------------------------------------------------------------------
			
			public static boolean doBDelete(String bid) {
				Connection  connection  = null;
				PreparedStatement statement = null;
				ResultSet  resultSet = null;
				
				try {
					connection = DBUtils.getConnection();
					statement = connection.prepareStatement("delete from commodity WHERE bid = ?");
					statement.setString(1, bid);
					statement.executeUpdate();
					return true;
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, statement, resultSet);
				}
				return false;
			
			}	
		
			//----------------------------------------博文信息更新------------------------------------------------------------------------------------
			public static boolean doBupdate(String bid,String name,String type,String uId,String content) {
				Connection  connection  = null;
				PreparedStatement statement= null;
				ResultSet  resultSet = null;
				System.out.println(bid);
				System.out.println("bid:::::::::::"+bid);
				try {
					connection = DBUtils.getConnection();
					statement = connection.prepareStatement("UPDATE Blogtext SET name=?,type=? ,content=?,uId=? WHERE bid=?");
					statement.setString(1, name);
					statement.setString(2, type);
					statement.setString(3, content);
					statement.setString(4,uId);
					statement.setString(5, bid);
					statement.executeUpdate();
					return true;
						
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, statement, resultSet);
				}
				return false;
			
			}

			//----------------------------------------个人博文具体一览------------------------------------------------------------------------------------
			
			public static List<user> doQO(String uId) {
				Connection  connection  = null;
				PreparedStatement prstatement= null;
				ResultSet  resultSet = null;
				PreparedStatement prstatement2= null;
				ResultSet  resultSet2 = null;
				
				System.out.println(uId);
				try {
					connection = DBUtils.getConnection();
					List<user> userlist = new ArrayList<>();
					prstatement = connection.prepareStatement("SELECT *,count(*) AS 'num' FROM Blogtext WHERE uId=? GROUP BY bid");
					prstatement.setString(1,uId);
					resultSet = prstatement.executeQuery();
					prstatement2 = connection.prepareStatement("SELECT * FROM Blogtext WHERE bid in(SELECT bid FROM Blogtext WHERE uId=?) ");
					prstatement2.setString(1,uId);
					resultSet2 = prstatement2.executeQuery();
					if(resultSet!=null){
						while(resultSet.next()&&resultSet2.next()){
							user user = new user();
							user.setuId(uId);
							user.setBid(resultSet.getString("bid"));
							user.setNum(resultSet.getInt("num"));
							int num = resultSet.getInt("num");
							System.out.println(num);
							user.setName(resultSet2.getString("name"));
							user.setType(resultSet2.getString("type"));
							user.setContent(resultSet2.getString("content"));
							
							userlist.add(user);
							
						}
						return userlist;
					}else{
						return null;
						}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, prstatement, resultSet);
					DBUtils.close(connection, prstatement2, resultSet2);
					
				}
				return null;
			
			}	
			//----------------------------------------具体博文-----------------------------------------------------------------------------------
			
			public static Blogtext doUQ(String bid) {
				Connection  connection  = null;
				PreparedStatement statement= null;
				ResultSet  resultSet = null;
				System.out.println(bid);
				try {
					connection = DBUtils.getConnection();
					statement = connection.prepareStatement("select Blogtext.bid,Blogtext.name,Blogtext.type,Blogtext.uId,Blogtext.content from Blogtext WHERE Blogtext.bid=? ");
					statement.setString(1, bid);
					statement.setString(2, bid);
					statement.setString(3, bid);
					resultSet = statement.executeQuery();
					if(resultSet!=null){
						while(resultSet.next()){
							Blogtext blogtext = new Blogtext();
							blogtext.setName(resultSet.getString("name"));
							blogtext.setuId(resultSet.getString("uId"));
							blogtext.setBid(resultSet.getString("bid"));
							blogtext.setType(resultSet.getString("type"));
							blogtext.setContent(resultSet.getString("content"));
							
							
							return blogtext;
						}
					}else{
						return null;
						}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					DBUtils.close(connection, statement, resultSet);
				}
				return null;
			
			}
			
						

}


