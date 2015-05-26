package ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.Spliter;
import recommend.RecomendInfo;
import recommend.Recommender;

/**
 * Servlet implementation class expert_recommend_ser
 */
@WebServlet("/expert_recommend_ser")
public class expert_recommend_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public expert_recommend_ser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		
		if(status.equals("recommend"))
		{
			//System.out.println("serverlt“—æ≠÷¥––");
			Spliter ph = new Spliter();
			ph.split(request.getParameter("userproblem").trim());
			Set<String> set = ph.getSpliteResult();
			
			Recommender recommender = new Recommender();
			List<RecomendInfo> res = recommender.recommend(set);
			Iterator<RecomendInfo> ite = res.iterator();
			//System.out.println(res.size());
			out.println("<length>"+res.size()+"</length>");
			int i=0;
			while(ite.hasNext())
			{
				RecomendInfo info = ite.next();
				String name = info.getExpertName();
				String[] comtxt = name.split("_|\\.");
				
				List<String> keyWords = info.getKeyWords();
				
				out.println("<expertname>"+comtxt[0]+"</expertname>");
				out.println("<expert_company>"+comtxt[1]+"</expert_company>");
				out.println("<basis>"+keyWords+"</basis>");
				
			} 
			out.println("</response>");

			out.close();
		}
		
		
		
		
		
		
	}

}
