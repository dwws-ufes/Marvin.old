package br.ufes.inf.nemo.marvin;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import br.ufes.inf.nemo.marvin.core.domain.AcademicType;

@FacesConverter("enumTypeConverter")
public class EnumTypeConverter  implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return AcademicType.valueOf(value);
		}
		return null;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof AcademicType) {
			return ((AcademicType) value).getLabel();
		}
		return null;
	}

}
