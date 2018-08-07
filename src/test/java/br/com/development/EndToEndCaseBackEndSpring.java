package br.com.development;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.development.client.AutoresClient;
import br.com.development.client.LivrosClient;
import br.com.development.client.domain.Autor;
import br.com.development.client.domain.Comentario;
import br.com.development.client.domain.Livro;


public class EndToEndCaseBackEndSpring {
	
	LivrosClient livrosClient = new LivrosClient("http://localhost:8080");
	AutoresClient autoresClient = new AutoresClient("http://localhost:8080");
	
	@Test
	public void deveSalvarUmAutor() {
		List<Livro> livros = livrosClient.listar();
		
		Autor autor = new Autor();
		autor.setNome("Solange Borges de Almeida");
		autor.setNacionalidade("Brasileira");
		autor.setNascimento(new Date());
		autor.setLivros(livros);
		
		autoresClient.salvar(autor);
		
	}
	
	@Test
	public void deveSalvarUmLivro() {
		
		List<Autor> autores = autoresClient.listar();
		Autor autor = null;

		for(Autor autorSelecionado: autores) {
			autor = autorSelecionado;
		}
		
		List<Comentario> comentarios = new ArrayList<Comentario>();
		Livro livro = new Livro();
		
		livro.setNome("Angular Cli");
		livro.setEditora("Marques Books");
		livro.setPublicacao(new Date());
		livro.setResumo("Modernizando o frontend.");
		livro.setComentarios(comentarios);
		livro.setAutor(autor);
		
		livrosClient.salvar(livro);
	}

}
