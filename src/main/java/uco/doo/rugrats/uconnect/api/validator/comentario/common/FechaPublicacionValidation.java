package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import java.time.LocalDateTime;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.utils.UtilDate;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class FechaPublicacionValidation implements Validation<LocalDateTime>{
	private FechaPublicacionValidation() {
		super();
	}
	public static final Result validate(final LocalDateTime data) {
		return new FechaPublicacionValidation().execute(data);
	}
	@Override
	public Result execute(LocalDateTime data) {
		var result = Result.create();
		
		if(UtilDate.isNull(data)) {
			result.addMessage("No es posible continuar con la fecha de la publicación del comentario vacía");
		}
		else if(UtilObject.isDefault(data, UtilDate.getDefaultValue())) {
			result.addMessage("No es posible continuar con la fecha de la publicación del comentario con sus valores por defecto");
		}
		return result;
	}
}
