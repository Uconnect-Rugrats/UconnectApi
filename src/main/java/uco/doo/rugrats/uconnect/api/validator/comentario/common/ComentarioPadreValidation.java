package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.dto.ComentarioDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class ComentarioPadreValidation implements Validation<ComentarioDTO>{
	private ComentarioPadreValidation() {
		super();
	}
	public static final Result validate(final ComentarioDTO data) {
		return new ComentarioPadreValidation().execute(data);
	}
	@Override
	public Result execute(ComentarioDTO data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible continuar con el comentario vacio");
		}
		else if(UtilObject.isDefault(data, ComentarioDTO.create())) {
			result.addMessage("No es posible continuar con los datos del comentario con sus valores por defecto");
		}
		return result;
	}
}
