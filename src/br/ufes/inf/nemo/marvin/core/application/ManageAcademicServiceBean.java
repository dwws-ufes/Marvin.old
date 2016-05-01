package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageAcademicServiceBean extends CrudServiceBean<Academic> implements ManageAcademicService{

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The DAO for Academic objects. */
	@EJB
	private AcademicDAO academicDAO;
	
	
	@Override
	public BaseDAO<Academic> getDAO() {
		return academicDAO;
	}

	@Override
	protected Academic createNewEntity() {
		return new Academic();
	}
	
	
	@Override
	public void validateUpdate(Academic entity) throws CrudException {
		Date now = new Date(System.currentTimeMillis());
		entity.setLastUpdateDate(now);
	}
	
	@Override
	public void validateCreate(Academic entity) throws CrudException {
		Date now = new Date(System.currentTimeMillis());
		entity.setLastUpdateDate(now);
	}

}
