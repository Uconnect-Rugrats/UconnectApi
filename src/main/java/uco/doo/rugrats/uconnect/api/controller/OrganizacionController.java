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
import uco.doo.rugrats.uconnect.dto.OrganizacionDTO;

@RestController
@RequestMapping("uconnect/api/v1/organizacion")
public final class OrganizacionController {
	
	
	public OrganizacionController() {
		super();
	}
	@GetMapping("/dummy")
	public OrganizacionDTO dummy() {
		return OrganizacionDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<OrganizacionDTO>> list(@RequestBody OrganizacionDTO dto) {
		List<OrganizacionDTO> list = new ArrayList<>();
		
		List<String> messages = new ArrayList<>();
		messages.add("Organizaciones consultados exitosamente");
		
		Response<OrganizacionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public OrganizacionDTO listById(@PathVariable UUID id) {
		return OrganizacionDTO.create().setIdentificador(id);
	}
	
}
