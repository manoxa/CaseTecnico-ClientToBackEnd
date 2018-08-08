package br.com.development.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.development.client.domain.Comentario;
import br.com.development.client.domain.Livro;

public class LivrosClient {
	
	private RestTemplate restTemplate;
	
	private String URI_BASE;
	
	private String URN_BASE = "/livros/";
	
	private String BASE_COMENTARIO = "/comentarios";
		
	
	public LivrosClient(String url) {
		restTemplate = new RestTemplate();
		URI_BASE = url.concat(URN_BASE);
		
	}
	
	public List<Livro> listar() {
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(URI_BASE)).build();
		
		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro) {		
		RequestEntity<Livro> request = RequestEntity
				.post(URI.create(URI_BASE)).body(livro);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscar(String uri) {
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(uri)).build();
		
		ResponseEntity<Livro> response = restTemplate.exchange(request, Livro.class);
		
		return response.getBody();
	}

	public String salvarComentario(Long id, Comentario comentario) {
	
		List<Livro> livros = listar();
		Livro livro = null;
		
		if(livros.size()>0) {
			livro = livros.get(0);			
		}else {
			livro = livros.get(id.intValue());	
		}
		
		String codigoLivro = livro.getId().toString();
		
		comentario.setLivro(livro);
		RequestEntity<Comentario> request = RequestEntity
				.post(URI.create(URI_BASE + codigoLivro + BASE_COMENTARIO)).body(comentario);
	
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
		
	}
	

 }