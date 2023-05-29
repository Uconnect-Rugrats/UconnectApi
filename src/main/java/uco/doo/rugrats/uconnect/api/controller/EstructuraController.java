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
import uco.doo.rugrats.uconnect.busisness.facade.EstructuraFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeImpl.EstructuraFacadeImpl;
import uco.doo.rugrats.uconnect.dto.EstructuraDTO;

@RestController
@RequestMapping("uconnect/api/v1/estructura")
public final class EstructuraController {
	
	
	private EstructuraFacade facade;
	@GetMapping("/dummy")
	public EstructuraDTO dummy() {
		return EstructuraDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<EstructuraDTO>> list(@RequestBody EstructuraDTO dto) {
		facade = new EstructuraFacadeImpl();
		List<EstructuraDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Estructuras consultadas exitosamente");
		
		Response<EstructuraDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public EstructuraDTO listById(@PathVariable UUID id) {
		return EstructuraDTO.create().setIdentificador(id);
	}
	
}
