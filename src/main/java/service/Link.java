package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.User;

public class Link {

  
	
	public static String findUser(String username,String userpwd) throws ClassNotFoundException, SQLException {
		String DBUrl = "jdbc:mysql://localhost:3306/diskuser?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true";
	    String DBUser = "root";
	    String DBPass = "1836022119";
	    Connection con = null;


	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(DBUrl, DBUser, DBPass);
	        String sql2 ="select * from userinfo where username= ? and userpwd= ?";
	        String sql1 = " select username from userinfo where username=? ";
	        PreparedStatement PS = con.prepareStatement(sql1);
	        PS.setString(1,username);
	        ResultSet res = PS.executeQuery();
	        if(res.next()){
	        	PreparedStatement PS1 = con.prepareStatement(sql2);
	        	PS1.setString(1,username);
	        	PS1.setString(2,userpwd);
	        	ResultSet res1 = PS1.executeQuery();     
	        if (res1.next()){
                System.out.println("登录成功！");
            } else {
                System.out.println();
                System.out.println(res1.next());
                System.out.println("密码错误");
            }
        }else {
            System.out.println("此账号还未注册，请先注册再登录！");
        }
        con.close();
        PS.close();
        res.close();
	    return username;
	  }
	

	
	
	public static int addUser(User user) throws ClassNotFoundException, SQLException {
		String DBUrl = "jdbc:mysql://localhost:3306/diskuser?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true";
	    String DBUser = "root";
	    String DBPass = "1836022119";
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    try {
	        
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(DBUrl, DBUser, DBPass);
	        String sql="insert into userinfo(username,userpwd) values(?,?)";
	        pstmt=con.prepareStatement(sql);

	        String username=user.getUsername();

	        String userpwd=user.getUserpwd(); 




			pstmt.setString(1, username); 

			pstmt.setString(2, userpwd); 

            pstmt.executeUpdate();
  	        pstmt.close();
  	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
	        // 关闭资源
	        try{
	            if(pstmt!=null) pstmt.close();
	        }catch(SQLException e){
	        }
	        try{
	            if(con!=null) con.close();
	        }catch(SQLException e){
	            e.printStackTrace();
	        }
	    }
		return 0;
	    
	  }
	
	

	
	
	
	

}
