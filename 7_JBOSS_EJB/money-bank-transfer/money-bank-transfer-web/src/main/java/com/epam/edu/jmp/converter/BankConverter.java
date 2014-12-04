package com.epam.edu.jmp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.BankDao;
import com.epam.edu.jmp.model.Bank;

@Named
@FacesConverter(forClass=Bank.class)
public class BankConverter implements Converter {

  @Inject
  private BankDao bankDao;

  @Override
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    Bank found = bankDao.find(Long.valueOf(arg2));
    return found;
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	  String str = String.valueOf(((Bank) arg2).getBankId());
    return str;
  }

}
