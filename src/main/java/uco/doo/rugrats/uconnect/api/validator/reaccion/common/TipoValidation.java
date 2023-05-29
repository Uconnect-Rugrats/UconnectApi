package uco.doo.rugrats.uconnect.api.validator.reaccion.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.dto.TipoReaccionDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class TipoValidation implements Validation<TipoReaccionDTO> {
	private TipoValidation() {
		super();
	}
	public static final Result validate(final TipoReaccionDTO data) {
		return new TipoValidation().execute(data);
	}
	@Override
	public Result execute(TipoReaccionDTO data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible continuar con el Tipo de reacción vacío");	
		}
		else if(UtilObject.isDefault(data, TipoReaccionDTO.create())) {
			result.addMessage("No es posible continuar con el Tipo de reacción con sus valores por defecto");
		}
		return result;
	}

}
