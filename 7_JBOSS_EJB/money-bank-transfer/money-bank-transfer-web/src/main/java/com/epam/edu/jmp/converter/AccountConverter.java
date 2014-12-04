package com.epam.edu.jmp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.AccountDao;
import com.epam.edu.jmp.model.Account;

@Named
@FacesConverter(forClass=Account.class)
public class AccountConverter implements Converter {

  @Inject
  private AccountDao accountDao;

  @Override
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
	Account found = accountDao.find(Long.valueOf(arg2));
    return found;
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	String str = String.valueOf(((Account) arg2).getId());
    return str;
  }

}
