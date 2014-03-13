package br.ufc.quixada.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.quixada.websocket.WSocket;

/**
 * Servlet implementation class EmpresaServlet
 */

@WebServlet("/simulador")
public class SimuladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimuladorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //Simular recebimento de msg
    //http://localhost:8080/Radar-Livre/simulador?action=simular&lat=-10.239424&lon=-50.186502&grau=180&hex=TESTE&status=ADD
    private void simular (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lon");
		String grau = request.getParameter("grau");
		String hex = request.getParameter("hex");
		String status = request.getParameter("status");
		WSocket.broadcast(lat+","+lon+","+grau+","+hex+","+status);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("simular")) this.simular(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
