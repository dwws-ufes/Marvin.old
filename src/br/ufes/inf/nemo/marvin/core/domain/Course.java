package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;


/**
 * CLASSE DE DOMMINIO QUE REPRESENTA OS CURSOS.
 * 
 * IMPLEMENTADA COM BASE NO Documento de Análise de Requisitos VERSÃO 1.4
 * 
 * <i>ESTA CLASSE FAZ PARTE DO SISTEMA SAE.</i>
 * 
 * @author BRUNO MANZOLI (manzoli2122@gmail.com)
 */

@Entity
public class Course  extends  PersistentObjectSupport implements Comparable<Course> {
	
	private static final long serialVersionUID = 1L;
	
	/** name DO CURSO */
	@NotNull
	@Size(max = 60)
	@Column(unique=true)
	private String name;
	
	
	/** code DO CURSO */
	@NotNull
	@Size(max = 8)
	@Column(unique=true)
	private String code;
	

	
	
	/** COORDENADOR DO CURSO */
	@NotNull
	@ManyToOne
	private Academic coordinator;
	
	public Academic getCoordinator() { 
		return coordinator; 
	}
	
	public void setCoordinator(Academic coordinator) { 
		if(	coordinator.getAcademicTypes().contains(AcademicType.Admin) || 
			coordinator.getAcademicTypes().contains(AcademicType.Teacher)	) {
			this.coordinator = coordinator; 
		}
	}

	
	
	
	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Course o) { 	
		if (name == null)	return 1;
		if (o.name == null) return -1;
		
		if (code == null)	return 1;
		if (o.code == null) return -1;
		
		int cmp = name.compareTo(o.name);
		if (cmp != 0 ) return cmp;
		
		int cmpcpf = code.compareTo(o.code);
		if (cmpcpf != 0) return cmpcpf;
		
		return super.compareTo(o); 
		
	}
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport#toString() */
	@Override
	public String toString() { return name; }

	
	
	/**  GETS AND SETS  */
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	
	public String getName() {return name; }
	public void setName(String name) { this.name = name; }	
	
}
