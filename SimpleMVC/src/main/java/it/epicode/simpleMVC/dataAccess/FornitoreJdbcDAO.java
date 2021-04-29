package it.epicode.simpleMVC.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.epicode.simpleMVC.model.Fornitore;

public class FornitoreJdbcDAO implements AbstractFornitoreDAO {

	public static final String LISTA_FORNITORI = "SELECT codice_fornitore, nome, indirizzo, citta FROM negozio.fornitore";
	
	@Override
	public List<Fornitore> listaFornitori() throws SQLException {
		
		List<Fornitore> listaFornitori = new ArrayList<>();
		try(Connection connect = ConnectionUtils.createConnection();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(LISTA_FORNITORI)){
			
			while(rs.next()) {
				
				listaFornitori.add(new Fornitore(rs.getInt("codice_fornitore"), rs.getString("nome"), 
												 rs.getString("indirizzo"), rs.getString("citta")));
			}	
		}
		return listaFornitori;
	}

	
	
	@Override
	public Fornitore creaFornitore(Fornitore f) {

		return null;
	}

	public static final String FIND_FORNITORE ="SELECT codice_fornitore, nome, indirizzo, citta FROM negozio.fornitore WHERE codice_fornitore=?";
	
	@Override
	public Optional<Fornitore> findFornitore(int codiceFornitore) throws SQLException {
		
		try(Connection connect = ConnectionUtils.createConnection();
			PreparedStatement ps = connect.prepareStatement(FIND_FORNITORE)){
			
			
			ps.setInt(1, codiceFornitore);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					Fornitore f = new Fornitore(rs.getInt("codice_fornitore"),rs.getString("nome"), 
												rs.getString("indirizzo"), rs.getString("citta"));
					return Optional.of(f);
				}
				return Optional.empty();
			}
		}
	}

	
	
}












