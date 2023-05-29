package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.utils.UtilText;

public class ContenidoValidation implements Validation<String>{

	private static final int MINIMUN_LENGHT = 1;
	private static final int MAXIMUN_LENGHT = 250;
	private ContenidoValidation() {
		super();
	}
	public static final Result validate(final String data) {
		return new ContenidoValidation().execute(data);
	}
	@Override
	public Result execute(String data) {
		var result = Result.create();
		
		if(UtilText.isEmpty(data)) {
			result.addMessage("No es posible continuar con el contenido del comentario vacío");
			
		}else {
			if(UtilText.getUtilText().textHasLenghtAllowed(data, MINIMUN_LENGHT, MAXIMUN_LENGHT)) { 
				result.addMessage("El contenido del comentario no puede ser mayor a 250 caracteres");
			}
		
		}
		return result;
	}

}
