package br.com.agenda.aplicacao;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {
	public static void main(String[] args) {
		Contato contato = new Contato();
		
		contato.setNome("Gabriel de Souza");
		contato.setIdade(21);
		contato.setDataCadastro("05/05/2011");
		ContatoDAO contatoDao = new ContatoDAO();
		//contatoDao.save(contato);
		
		//visualizacao dos registro do banco de dados Todos
		/*
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato " + c.getId());
			System.out.println("Contato " + c.getNome());
			System.out.println("Contato " + c.getIdade());
			System.out.println("Contato " + c.getDataCadastro());
			System.out.println("________________________________");
		}
	
			Contato contatoUpdate = new Contato();
			contatoUpdate.setNome("farinha");
			contatoDao.update(contatoUpdate, 4);
		
		
			contatoDao.delete(3);
			*/
		Contato contatoSelect = contatoDao.selectById(2);
		System.out.println(contatoSelect.getId() + ", "+ contatoSelect.getNome() + ", " + contato.getIdade());
	}
	
}
