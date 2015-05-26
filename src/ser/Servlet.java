package ser;

import impl.ExpertBrowseImpl;
import impl.ExpertSearchImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expert.Expert;
import expert.ExpertIntell;
import expert.ExpertLit;
import expert.ExpertPatent;
import expertNet.Cooperation;
import expertNet.CoreExpertInfo;
import expertNet.ExpertRelationNet;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String status = request.getParameter("status");
		if (status.equals("browse"))
		{
			String expert_name = request.getParameter("expert_name");
			ExpertBrowseImpl expertBrowseImpl = new ExpertBrowseImpl();
			// 专家详情
			Expert expert = expertBrowseImpl.browse_detail(expert_name);
			// 专家文献信息
			ArrayList<ExpertLit> expertLits = expertBrowseImpl
					.browseLit(expert_name);
			// 专家情报信息
			ArrayList<ExpertIntell> expertIntells = expertBrowseImpl
					.browseIntell(expert_name);
			// 专家专利信息
			ArrayList<ExpertPatent> expertPatents = expertBrowseImpl
					.browsePatent(expert_name);

			request.setAttribute("expert", expert);
			request.setAttribute("expertLits", expertLits);
			request.setAttribute("expertIntells", expertIntells);
			request.setAttribute("expertPatents", expertPatents);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/expert_detail.jsp");
			dispatcher.forward(request, response);
		} else if (status.equals("namesearch"))
		{
			String input = request.getParameter("input");
			ExpertSearchImpl expertSearchImpl = new ExpertSearchImpl();
			ArrayList<Expert> experts = expertSearchImpl.NameSearch(input);
			
			request.setAttribute("experts", experts);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/search_result.jsp");
			dispatcher.forward(request, response);
		}else if (status.equals("orgsearch"))
		{
			String input = request.getParameter("input");
			ExpertSearchImpl expertSearchImpl = new ExpertSearchImpl();
			ArrayList<Expert> experts = expertSearchImpl.OrgSearch(input);
			
			request.setAttribute("experts", experts);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/search_result.jsp");
			dispatcher.forward(request, response);
		}else if (status.equals("keysearch"))
		{
			String input = request.getParameter("input");
			
			ExpertRelationNet net = new ExpertRelationNet();
			Cooperation coop = new Cooperation();
			net.buildRelationNet(input, coop);
			net.computeCoreExpert();
			List<CoreExpertInfo> cinfos = net.getCoreExperts();
			ArrayList<Expert> experts = new ArrayList<Expert>();
			ExpertSearchImpl expertSearchImpl = new ExpertSearchImpl();
			for(int i = 0; i < cinfos.size(); ++i)
			{
				Expert expert = expertSearchImpl.KeySearch(cinfos.get(i).getExpert());
				experts.add(expert);
			}
			
			request.setAttribute("experts", experts);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/search_key_result.jsp");
			dispatcher.forward(request, response);
		}
	}

}
