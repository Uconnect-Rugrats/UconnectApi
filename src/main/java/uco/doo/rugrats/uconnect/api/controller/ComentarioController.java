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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.api.validator.comentario.CambiarEstadoComentarioValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.ComentarComentarioValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.EliminarComentarioValidation;
import uco.doo.rugrats.uconnect.busisness.facade.ComentarioFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.ComentarioFacadeImpl;
import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectException;
import uco.doo.rugrats.uconnect.dto.ComentarioDTO;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

@RestController
@RequestMapping("uconnect/api/v1/comentario")
public class ComentarioController {
	
	private ComentarioFacade facade;
	private Logger log = LoggerFactory.getLogger(ComentarioController.class);
	
	@GetMapping("/dummy")
	public ComentarioDTO dummy() {
		return ComentarioDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<ComentarioDTO>> list() {
		var dto = ComentarioDTO.create();
		facade = new ComentarioFacadeImpl();

		List<ComentarioDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add(UconnectApiMessages.ComentarioControllerMessages.LIST_MESSAGE);
		
		Response<ComentarioDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ComentarioDTO listById(@PathVariable UUID id) {
		return ComentarioDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public ResponseEntity<Response<ComentarioDTO>> create(@RequestBody ComentarioDTO dto) {
		facade = new ComentarioFacadeImpl();

		var statusCode = HttpStatus.OK;
		Response<ComentarioDTO> response = new Response<>();
		
		try {
			var result = ComentarComentarioValidation.validate(dto);
			if(result.getMessages().isEmpty()) {
				facade.comentar(dto);
				response.getMessages().add(UconnectApiMessages.ComentarioControllerMessages.CREATE_MESSAGE);
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
			response.getMessages().add(UconnectApiMessages.UNEXPECTED_USER_ERROR_MESSAGE);
			log.error(UconnectApiMessages.UNEXPECTED_TECHNYCAL_ERROR_MESSAGE);
		}
		
		return new ResponseEntity<>(response,statusCode);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response<ComentarioDTO>> update(@RequestParam UUID id,@RequestBody ComentarioDTO dto) {
		facade = new ComentarioFacadeImpl();

		var statusCode = HttpStatus.OK;
		var response = new Response<ComentarioDTO>();
		
		try {
			dto.setIdentificador(id);
			var result = CambiarEstadoComentarioValidation.validate(dto);
			if(result.getMessages().isEmpty()) {
				facade.cambiarEstado(dto);
				response.getMessages().add(UconnectApiMessages.ComentarioControllerMessages.MODIFY_MESSAGE);
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
			response.getMessages().add(UconnectApiMessages.UNEXPECTED_USER_ERROR_MESSAGE);
			log.error(UconnectApiMessages.UNEXPECTED_TECHNYCAL_ERROR_MESSAGE);
		}
		
		return new ResponseEntity<>(response,statusCode);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<ComentarioDTO>> drop(@PathVariable UUID id) {
		facade = new ComentarioFacadeImpl();

		var statusCode = HttpStatus.OK;
		var response = new Response<ComentarioDTO>();
		
		try {
			var result = EliminarComentarioValidation.validate(id);
			if(result.getMessages().isEmpty()) {
				facade.eliminar(id);
				response.getMessages().add(UconnectApiMessages.ComentarioControllerMessages.DELETE_MESSAGE);
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
			response.getMessages().add(UconnectApiMessages.UNEXPECTED_USER_ERROR_MESSAGE);
			log.error(UconnectApiMessages.UNEXPECTED_TECHNYCAL_ERROR_MESSAGE);

		}
		
		return new ResponseEntity<>(response,statusCode);
	}
	
}
