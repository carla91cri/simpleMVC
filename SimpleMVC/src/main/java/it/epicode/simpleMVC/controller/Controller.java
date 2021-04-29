package it.epicode.simpleMVC.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.epicode.simpleMVC.dataAccess.AbstractFornitoreDAO;
import it.epicode.simpleMVC.dataAccess.FornitoreJdbcDAO;
import it.epicode.simpleMVC.model.Fornitore;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AbstractFornitoreDAO fornitoreDAO = new FornitoreJdbcDAO();
	public static final String CHIAVE_LISTA_FORNITORI = "CHIAVE_LISTA_FORNITORI";
	public static final String CHIAVE_RICERCA_FORNITORE = "CHIAVE_RICERCA_FORNITORE";
	public static final String LISTA_FORNITORI_DESTINATION = "jsp/listaFornitori.jsp";
	public static final String CHIAVE_RICERCA_FORNITORE_DESTINATION = "jsp/ricercaFornitore.jsp";
	public static final String PAGINA_DI_ERRORE_DESTINATION = "jsp/paginaDiErrore.jsp";


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getServletPath();
		int pos = path.lastIndexOf('/');
		String action = path.substring(pos+1, path.length()-3);
		try{
			switch (action) {
			case "listaFornitori": listaFornitori(request, response);
			break;
			case "aggiungiFornitore": aggiungiFornitori(request, response);
			break;
			case "ricercaFornitorePerCodice": ricercaFornitorePerCodice(request, response);
			default: mostraPaginaDiErrore(request, response);
		
			}
		} catch(SQLException e) {
			e.printStackTrace();
			mostraPaginaDiErrore(request, response);
		}
		
	}
	
	private void listaFornitori(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		// La Servlet interroga il Model per avere la lista dei fornitori
		List<Fornitore> result = fornitoreDAO.listaFornitori();
		// La Servlet inserisce il risultato nello scope request in modo che sia disponibile alla jsp a cui faremo la forward
		request.setAttribute(CHIAVE_LISTA_FORNITORI, result); 
		// Creiamo un Dispatcher alla pagina prescelta
		RequestDispatcher disp = request.getRequestDispatcher(LISTA_FORNITORI_DESTINATION); 
		disp.forward(request, response);
	}

	private void aggiungiFornitori(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	private void mostraPaginaDiErrore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher disp = request.getRequestDispatcher(PAGINA_DI_ERRORE_DESTINATION); 
		disp.forward(request, response);
	}
	
	private void ricercaFornitorePerCodice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String parametroCodiceFornitore = request.getParameter("codiceFornitore");
		int codiceFornitore = Integer.parseInt(parametroCodiceFornitore);
		Optional<Fornitore> fornitore = fornitoreDAO.findFornitore(codiceFornitore);
		request.setAttribute(CHIAVE_RICERCA_FORNITORE, fornitore);
		RequestDispatcher disp = request.getRequestDispatcher(CHIAVE_RICERCA_FORNITORE_DESTINATION); 
		disp.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}




