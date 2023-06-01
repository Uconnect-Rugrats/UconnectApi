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
import uco.doo.rugrats.uconnect.busisness.facade.EstadoFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.EstadoFacadeImpl;
import uco.doo.rugrats.uconnect.dto.EstadoDTO;

@RestController
@RequestMapping("uconnect/api/v1/estado")
public final class EstadoController {
	private EstadoFacade facade;
	
	@GetMapping("/dummy")
	public EstadoDTO dummy() {
		return EstadoDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<EstadoDTO>> list(@RequestBody EstadoDTO dto) {
		facade = new EstadoFacadeImpl();
		List<EstadoDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Estados consultados exitosamente");
		
		Response<EstadoDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public EstadoDTO listById(@PathVariable UUID id) {
		return EstadoDTO.create().setIdentificador(id);
	}
	
}

