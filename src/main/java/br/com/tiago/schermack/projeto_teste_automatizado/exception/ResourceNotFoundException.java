package br.com.tiago.schermack.projeto_teste_automatizado.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message){
    	super(message);
    }
}