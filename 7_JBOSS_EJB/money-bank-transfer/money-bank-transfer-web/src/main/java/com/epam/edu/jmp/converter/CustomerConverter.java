package com.epam.edu.jmp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.CustomerDao;
import com.epam.edu.jmp.model.Customer;

@Named
@FacesConverter(forClass=Customer.class)
public class CustomerConverter implements Converter {

  @Inject
  private CustomerDao customerDao;

  @Override
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    Customer found = customerDao.find(Long.valueOf(arg2));
    return found;
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    String str = String.valueOf(((Customer) arg2).getCustomerId());
    return str;
  }

}
