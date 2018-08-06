package br.com.development.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.development.client.domain.Autor;

public class AutoresClient {
	
	private RestTemplate restTemplate;
	
	private String URI_BASE;
	
	private String URN_BASE = "/autores";
	
	
	public AutoresClient(String url) {
		restTemplate = new RestTemplate();
		URI_BASE = url.concat(URN_BASE);
		
	}
	
	public List<Autor> listar() {
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(URI_BASE)).build();
		
		ResponseEntity<Autor[]> response = restTemplate.exchange(request, Autor[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Autor autor) {		
		RequestEntity<Autor> request = RequestEntity
				.post(URI.create(URI_BASE)).body(autor);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
	public Autor buscar(String uri) {
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(uri)).build();
		
		ResponseEntity<Autor> response = restTemplate.exchange(request, Autor.class);
		
		return response.getBody();
	}
 }