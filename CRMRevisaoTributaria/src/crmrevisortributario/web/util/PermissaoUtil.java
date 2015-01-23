
package crmrevisortributario.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class PermissaoUtil {
	
	public static final String PERSMISSAO_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
	public static final String PERSMISSAO_USUARIO = "ROLE_USUARIO";
	
	public static boolean possuiPermissao(String permissao) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) external.getRequest();

		if (request.isUserInRole(permissao))
		{
			return true;
		}else{
			return false;
		}
	
	}
}
