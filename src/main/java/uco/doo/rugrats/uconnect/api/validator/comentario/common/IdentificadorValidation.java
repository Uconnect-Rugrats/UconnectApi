package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import java.util.UUID;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.utils.UtilObject;
import uco.doo.rugrats.uconnect.utils.UtilUUID;

public class IdentificadorValidation implements Validation<UUID>{
	private IdentificadorValidation() {
		super();
	}
	public static final Result validate(final UUID data) {
		return new IdentificadorValidation().execute(data);
	}
	@Override
	public Result execute(UUID data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible continuar con el identificador del comentario vacío");
			
		}else if(UtilUUID.isDefault(data)) {
			result.addMessage("No es posible tener el identificador por defecto del comentario");
		}
		return result;
	}
}
