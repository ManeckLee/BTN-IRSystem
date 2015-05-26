package ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DbConntion;
import mutiple.AbsInfo;
import mutiple.AbsInfoComparator;
import mutiple.Answerer;
import process.Spliter;
import recommend.RecomendInfo;
import recommend.Recommender;

/**
 * Servlet implementation class expert_coservice_ser
 */
@WebServlet("/expert_coservice_ser")
public class expert_coservice_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public expert_coservice_ser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Content-Type", "text/xml");
		out.println("<response>");
		
		String status="";
		status=request.getParameter("status").toString().trim();
		
		if(status.equals("conservice"))
		{

			Answerer answer = new Answerer();
			
			Map<String,List<AbsInfo>> ans = answer.answer(request.getParameter("userproblem").trim());
			
			Iterator<String> ite = ans.keySet().iterator();
			
			String problemanswerliterature="";
			String problemanswerpatent="";
			String problemanswerintelligence="";
			
			int numN1=0;
			int numN2=0;
			int numN3=0;
			while(ite.hasNext())
			{
				String flag = ite.next();
				if(flag.equals("literature"))
				{
					List<AbsInfo> infos = ans.get(flag);
					infos.sort(new AbsInfoComparator());
					
					//连接数据库
					Connection con = null;
					PreparedStatement pre = null;
					ResultSet rs = null;
					DbConntion conns = new DbConntion();
					con = conns.getConnection();
					
					for(int literi=0;literi<infos.size()&&literi<3;literi++)
					{
						numN1++;
						int literatureID= infos.get(literi).getID();
						try {
							 	pre = con
									.prepareStatement("select * from ExpLiteratureTable where NO=?");
							 	pre.setInt(1, literatureID);
							 	rs=pre.executeQuery();
							 	while (rs.next()) {
							 		problemanswerliterature+=rs.getString("abs");
							 		problemanswerliterature+="&lt;br/&gt;&lt;br/&gt;";
							 	}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}

					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				else if(flag.equals("patent"))
				{
					List<AbsInfo> infos = ans.get(flag);
					infos.sort(new AbsInfoComparator());
					
					//连接数据库
					Connection con = null;
					PreparedStatement pre = null;
					ResultSet rs = null;
					DbConntion conns = new DbConntion();
					con = conns.getConnection();
					
					for(int literi=0;literi<infos.size()&&literi<3;literi++)
					{
						numN2++;
						int patentID= infos.get(literi).getID();
						try {
							 	pre = con
									.prepareStatement("select * from ExpPatentTable where NO=?");
							 	pre.setInt(1, patentID);
							 	rs=pre.executeQuery();
							 	while (rs.next()) {
							 		problemanswerpatent+=rs.getString("abs");
							 		problemanswerpatent+="&lt;br/&gt;&lt;br/&gt;";
							 	}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}

					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				}
				else if(flag.equals("intelligence"))
				{
					List<AbsInfo> infos = ans.get(flag);
					infos.sort(new AbsInfoComparator());
					
					//连接数据库
					Connection con = null;
					PreparedStatement pre = null;
					ResultSet rs = null;
					DbConntion conns = new DbConntion();
					con = conns.getConnection();
					
					for(int literi=0;literi<infos.size()&&literi<3;literi++)
					{
						numN3++;
						int intelligenceID= infos.get(literi).getID();
						try {
							 	pre = con
									.prepareStatement("select * from ExpIntelligenceTable where NO=?");
							 	pre.setInt(1, intelligenceID);
							 	rs=pre.executeQuery();
							 	while (rs.next()) {
							 		problemanswerintelligence+=rs.getString("abs");
							 		problemanswerintelligence+="&lt;br/&gt;&lt;br/&gt;";
							 	}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}

					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				
				}
				
			}
			out.println("<lengthliterature>"+numN1+"</lengthliterature>");
			out.println("<lengthpatent>"+numN2+"</lengthpatent>");
			out.println("<lengthintelligence>"+numN3+"</lengthintelligence>");
			
			out.println("<answerliterature>"+problemanswerliterature+"</answerliterature>");
			out.println("<answerpatent>"+problemanswerpatent+"</answerpatent>");
			out.println("<answerintelligence>"+problemanswerintelligence+"</answerintelligence>");
			
			out.println("</response>");

			out.close();
		}
		
		
		
		
		
		
	
	}

}
