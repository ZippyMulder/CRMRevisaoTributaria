
package crmrevisortributario.cliente;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class ClienteDAOHibernate implements ClienteDAO {

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Cliente cliente) {
		this.session.save(cliente);
		this.session.flush();
		this.session.clear();
	}

	public void atualizar(Cliente cliente) {
		this.session.update(cliente);
		this.session.flush();
		this.session.clear();
	}

	public void excluir(Cliente cliente) {
		this.session.delete(cliente);
	}

	public Cliente carregar(Integer codigo) {
		return (Cliente) this.session.get(Cliente.class, codigo);
	}

	public Cliente buscarPorCnpj(String cnpj) {
		String hql = "select c from Cliente c where c.cnpj = :cnpj";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("cnpj", cnpj);

		//TODO mostrar primeiramente com o list e depois apresentar o uniqueResult
		return (Cliente) consulta.uniqueResult();
	}
	
	public List<Cliente> buscarPorRazaoSocial(String razaoSocial, Boolean buscarQualquerPosicao) {
		
		String hql = "select c from Cliente c where c.razaoSocial LIKE :razaoSocial";
		Query query = this.session.createQuery(hql);
		if (buscarQualquerPosicao == true){
			query.setString("razaoSocial", "%" + razaoSocial + "%");
		}else{
			query.setString("razaoSocial",razaoSocial + "%");
		}

		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		return this.session.createCriteria(Cliente.class).list();
	}
}
