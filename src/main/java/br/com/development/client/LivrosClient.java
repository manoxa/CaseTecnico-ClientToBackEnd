package br.com.development.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.development.client.domain.Livro;

public class LivrosClient {
	
	private RestTemplate restTemplate;
	
	private String URI_BASE;
	
	private String URN_BASE = "/livros";
	
	
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
 }