package com.epam.edu.jmp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.CurrencyDao;
import com.epam.edu.jmp.model.Currency;

@Named
@FacesConverter(forClass = Currency.class)
public class CurrencyConverter implements Converter {

	@Inject
	private CurrencyDao currencyDao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Currency found = currencyDao.find(Long.valueOf(arg2));
		return found;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = String.valueOf(((Currency) arg2).getCurrencyId());
		return str;
	}

}
