package br.ufes.inf.nemo.marvin.core.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.core.domain.AcademicType;

@Named
@ApplicationScoped
public class CoreController implements Serializable{


	private static final long serialVersionUID = 1L;

	public String login(){
		return "/login/index.xhtml?faces-redirect=true" ;
	}
	
	public AcademicType[] getAcademicType() {
		return AcademicType.values();
	}
	
	
	
}
