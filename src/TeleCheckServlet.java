

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceRequester.QryTelnumOpCodeService;

import com.google.gson.Gson;

/**
 * Servlet implementation class TelematicsServlet
 */
public class TeleCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeleCheckServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
//		response.setContentType("text/plain");
		OutputStream out = response.getOutputStream();
		
		String number = request.getParameter("number");
		
		// 取得QryTelnumOpCodeService實體
		QryTelnumOpCodeService qryTelnumOpCodeService = 
			new QryTelnumOpCodeService("isvAccount", "isvKey");

		// 取得門號所屬業者查詢結果
		String result = qryTelnumOpCodeService.getQryResult(number);
//		out.write((result  + "<br/>\n").getBytes());
		
		String status = result.substring(result.indexOf("<Status>") + 8, result.indexOf("</Status>"));
		String msg = result.substring(result.indexOf("<Msg>") + 5, result.indexOf("</Msg>"));
		String telnum = result.substring(result.indexOf("<TelNum>") + 8, result.indexOf("</TelNum>"));
		String opcode = result.substring(result.indexOf("<OpCode>") + 8, result.indexOf("</OpCode>"));
		
		Msg m = new Msg();
		m.msg = msg;
		m.number = telnum;
		m.status = status;
		m.opCode = opcode;
		
		
		Gson gson = new Gson();
		out.write(gson.toJson(m).getBytes());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
