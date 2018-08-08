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
	public void deveSalvarUmAutorUmLivroEumComentario() {
		
		List<Livro> livros = livrosClient.listar();
		
		Autor autor1 = new Autor();
		autor1.setNome("Robert Rodriguez");
		autor1.setNacionalidade("Espanol");
		autor1.setNascimento(new Date());
		autor1.setLivros(livros);
		
		autoresClient.salvar(autor1);
		
		List<Autor> autores = autoresClient.listar();
		Autor autor = null;

		for(Autor autorSelecionado: autores) {
			autor = autorSelecionado;
		}
		
		List<Comentario> comentarios = new ArrayList<Comentario>();
		Livro livro = new Livro();
		
		livro.setNome("Microservices com SpringBoot");
		livro.setEditora("Marques Books");
		livro.setPublicacao(new Date());
		livro.setResumo("Livro voltado para Desenvolvedores de Software");
		livro.setComentarios(comentarios);
		livro.setAutor(autor);
		
		livrosClient.salvar(livro);
				
		List<Livro> todosLivros = livrosClient.listar();
		Livro queroUmLivro = null;
		
		for(Livro livroSelecionado: todosLivros) {
			queroUmLivro = livroSelecionado;
		}
		
		Comentario comentario = new Comentario();
		comentario.setUsuario("amrodrigues");
		comentario.setData(new Date());
		comentario.setTexto("Particularmente esse livro é o melhor referênte ao assunto.");
		
		livrosClient.salvarComentario(queroUmLivro.getId(), comentario);
	}
	
}
