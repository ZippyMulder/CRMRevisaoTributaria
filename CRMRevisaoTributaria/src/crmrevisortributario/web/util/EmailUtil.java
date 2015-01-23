package crmrevisortributario.web.util;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import crmrevisortributario.util.UtilException;

public class EmailUtil {

	public void enviarEmail(String de, String para, String assunto, String mensagem) throws UtilException {
		
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:comp/env");
			Session session = (Session) envContext.lookup("mail/Session");
			
			SimpleEmail email = new SimpleEmail();
			email.setMailSession(session);
			if (de != null) {
				email.setFrom(de);
			} else {
				Properties p = session.getProperties();
				email.setFrom(p.getProperty("mail.smtp.user"));
			}
			email.addTo(para);
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.setSentDate(new Date());
			email.send();
		} catch (EmailException e) {
			throw new UtilException(e);
		} catch (NamingException e) {
			throw new UtilException(e);
		}
	}
	
	public Boolean validarEmail(String email)
	{
		
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern p = Pattern.compile(regex);
  
		//Compara a string com o padrão;  
		Matcher m = p.matcher(email);  
     
		boolean matchFound = m.matches();  
  
		if (matchFound){  
    	  	return true;
		}else{ 
			return false;
		}
	}
}