package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.busisness.facade.EstadoFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.EstadoFacadeImpl;
import uco.doo.rugrats.uconnect.dto.EstadoDTO;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

@RestController
@RequestMapping("uconnect/api/v1/estado")
public final class EstadoController {
	
	
	@GetMapping("/dummy")
	public EstadoDTO dummy() {
		return EstadoDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<EstadoDTO>> list(@RequestBody EstadoDTO dto) {
		EstadoFacade facade = new EstadoFacadeImpl();
		List<EstadoDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add(UconnectApiMessages.EstadoControllerMessages.READ_MESSAGE);
		
		Response<EstadoDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
}

