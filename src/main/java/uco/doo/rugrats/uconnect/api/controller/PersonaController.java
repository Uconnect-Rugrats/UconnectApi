package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.dto.PersonaDTO;

@RestController
@RequestMapping("uconnect/api/v1/persona")
public final class PersonaController {
	
	
	public PersonaController() {
		super();
	}
	@GetMapping("/dummy")
	public PersonaDTO dummy() {
		return PersonaDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<PersonaDTO>> list(@RequestBody PersonaDTO dto) {
		List<PersonaDTO> list = new ArrayList<>();
		
		List<String> messages = new ArrayList<>();
		messages.add("Personas consultadas exitosamente");
		
		Response<PersonaDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public PersonaDTO listById(@PathVariable UUID id) {
		return PersonaDTO.create().setIdentificador(id);
	}
	
}
