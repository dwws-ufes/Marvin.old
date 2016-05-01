package br.ufes.inf.nemo.marvin.core.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.AcademicType;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;




@Named
@ApplicationScoped
public class CoreController implements Serializable{


	private static final long serialVersionUID = 1L;

	
	/** The DAO for Academic objects. */
	@EJB    	
	private AcademicDAO academicDAO;
	
	
	/** JSF Converter for Administrador objects. */
	private PersistentObjectConverterFromId<Academic> academicConverter;
	
	
	/** Getter for AdministradorConverter */
	public Converter getAcademicConverter() {
		if (academicConverter == null) {
			academicConverter = new PersistentObjectConverterFromId<Academic>(academicDAO);
		}
		return academicConverter;
	}
	
	
	

	
	
	
	public List<Academic> getTeachers(){
		List<Academic> lista = academicDAO.retrieveAll();
		List<Academic> teacher = new ArrayList<Academic>();
		for(Academic academic : lista){
			Iterator<AcademicType> it = academic.getAcademicTypes().iterator();
			while(it.hasNext()){
				if(it.next().equals(AcademicType.Teacher)){
					teacher.add(academic);
				}
			}
		}
		return teacher;
	}
	
	
	
	
	
	
	public String login(){
		return "/login/index.xhtml?faces-redirect=true" ;
	}
	
	
	
	
	public AcademicType[] getAcademicType() {
		return AcademicType.values();
	}
	
	
	
}
