package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.dto.PublicacionDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class PublicacionValidation implements Validation<PublicacionDTO>{
	private PublicacionValidation() {
		super();
	}
	public static final Result validate(final PublicacionDTO data) {
		return new PublicacionValidation().execute(data);
	}
	@Override
	public Result execute(PublicacionDTO data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible continuar con la publicación vacía");	
		}
		else if(UtilObject.isDefault(data, PublicacionDTO.create())) {
			result.addMessage("No es posible continuar con la publicación con sus valores por defecto");
		}
		return result;
	}
}