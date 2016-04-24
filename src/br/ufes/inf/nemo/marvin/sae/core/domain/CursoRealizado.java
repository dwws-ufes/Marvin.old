package br.ufes.inf.nemo.marvin.sae.core.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class CursoRealizado extends PersistentObjectSupport implements Comparable<CursoRealizado> {

	
	private static final long serialVersionUID = 1L;

	
	/** MATRICULA*/
	@NotNull 
	@Size(max = 10)
	private String matricula;
	
	
	@NotNull 
	private int anoInicio;
	
	@NotNull 
	private int anoTermino;
	
	@NotNull
	@ManyToOne
	private Curso curso;
	
	@NotNull
	@ManyToOne
	private Egresso egresso;
	 
	
	
	
	@Override
	public String toString() { return "Curso : " + curso + ", Egresso : " + egresso ; }

	
	@Override
	 public int compareTo(CursoRealizado o) {
		if (matricula == null)	return 1;
		if (o.matricula == null) return -1;
		int cmp = matricula.compareTo(o.matricula);
		if (cmp != 0 ) return cmp;
		return super.compareTo(o);	
	 }


	public String getMatricula() { return matricula;}
	public void setMatricula(String matricula) { this.matricula = matricula; }

	public int getAnoInicio() {	return anoInicio; }
	public void setAnoInicio(int anoInicio) {	this.anoInicio = anoInicio;	}

	public int getAnoTermino() {	return anoTermino;	}
	public void setAnoTermino(int anoTermino) { this.anoTermino = anoTermino;	}

	public Curso getCurso() { return curso;	}
	public void setCurso(Curso curso) {	this.curso = curso;	}

	public Egresso getEgresso() {	return egresso; }
	public void setEgresso(Egresso egresso) { this.egresso = egresso; }
	
	
 }




