package it.epicode.simpleMVC.dataAccess;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import it.epicode.simpleMVC.model.Fornitore;

public interface AbstractFornitoreDAO {
	
	List<Fornitore> listaFornitori() throws SQLException;
	
	Fornitore creaFornitore(Fornitore f) throws SQLException;
	
	Optional<Fornitore> findFornitore(int codiceFornitore) throws SQLException;
}
