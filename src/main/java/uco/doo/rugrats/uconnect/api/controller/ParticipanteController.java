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
import uco.doo.rugrats.uconnect.dto.EstadoDTO;
import uco.doo.rugrats.uconnect.dto.ParticipanteDTO;

@RestController
@RequestMapping("uconnect/api/v1/participante")
public final class ParticipanteController {
	
	
	public ParticipanteController() {
		super();
	}
	@GetMapping("/dummy")
	public ParticipanteDTO dummy() {
		return ParticipanteDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<ParticipanteDTO>> list(@RequestBody ParticipanteDTO dto) {
		List<ParticipanteDTO> list = new ArrayList<>();
		
		List<String> messages = new ArrayList<>();
		messages.add("Participantes consultados exitosamente");
		
		Response<ParticipanteDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public EstadoDTO listById(@PathVariable UUID id) {
		return EstadoDTO.create().setIdentificador(id);
	}
	
}
