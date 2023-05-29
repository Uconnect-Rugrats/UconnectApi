package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.api.validator.reaccion.EliminarReaccionValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.ReaccionarReaccionValidation;
import uco.doo.rugrats.uconnect.busisness.facade.ReaccionFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeImpl.ReaccionFacadeImpl;
import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectException;
import uco.doo.rugrats.uconnect.dto.ReaccionDTO;

@RestController
@RequestMapping("uconnect/api/v1/reaccion")
public class ReaccionController {
	private Logger log = LoggerFactory.getLogger(ReaccionController.class);

	private ReaccionFacade facade;
	
	@GetMapping("/dummy")
	public ReaccionDTO dummy() {
		return ReaccionDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<ReaccionDTO>> list(@RequestParam ReaccionDTO dto) {
		facade = new ReaccionFacadeImpl();
		List<ReaccionDTO> list = new ArrayList<>();
		
		List<String> messages = new ArrayList<>();
		messages.add("Calificaciones consultadas exitosamente");
		
		Response<ReaccionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ReaccionDTO listById(@PathVariable UUID id) {
		return ReaccionDTO.create().setIdentificador(id);
	}
	@PostMapping
	public ResponseEntity<Response<ReaccionDTO>> create(@RequestParam ReaccionDTO dto) {
		facade = new ReaccionFacadeImpl();
		var statusCode = HttpStatus.OK;
		Response<ReaccionDTO> response = new Response<>();
		
		try {
			var result = ReaccionarReaccionValidation.validate(dto);
			if(result.getMessages().isEmpty()) {
				facade.reaccionar(dto);
				response.getMessages().add("La reacción fue registrada de forma satisfactoria");
			}else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}
		}catch (UconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat("-").concat(exception.getTechnicalMessage()),exception);

			
		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("Se ha presentado un problema inesperado. Por favor contacte con el administrador del sistema");
			log.error("Se ha presentado un problema inesperado. Por favor, validar la consola");

		}
		
		return new ResponseEntity<>(response,statusCode);
	}
	
	@DeleteMapping
	public ResponseEntity<Response<ReaccionDTO>> drop(@PathVariable UUID id) {
		facade = new ReaccionFacadeImpl();
		var statusCode = HttpStatus.OK;
		var response = new Response<ReaccionDTO>();
		
		try {
			var result = EliminarReaccionValidation.validate(id);
			if(result.getMessages().isEmpty()) {
				facade.eliminar(id);
				response.getMessages().add("La reacción fue eliminada de forma satisfactoria");
			}else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}
		}catch (UconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat("-").concat(exception.getTechnicalMessage()),exception);

			
		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("Se ha presentado un problema inesperado. Por favor contacte con el administrador del sistema");
			log.error("Se ha presentado un problema inesperado. Por favor, validar la consola");

		}
		
		return new ResponseEntity<>(response,statusCode);
	}
}
