package br.ufes.inf.nemo.marvin.sae.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

/**
 * CLASSE DE DOMMINIO QUE REPRESENTA O ASSUNTOS RELACIONADOS AO DEPARTAMENTO DE INFORMÁTICA.
 * 
 * IMPLEMENTADA COM BASE NO Documento de Análise de Requisitos VERSÃO 1.4
 * 
 * <i>ESTA CLASSE FAZ PARTE DO SISTEMA SAE.</i>
 * 
 * @author BRUNO MANZOLI (manzoli2122@gmail.com)
 */
@Entity
public class AssuntoInteresse extends PersistentObjectSupport implements Comparable<AssuntoInteresse> {

	
	private static final long serialVersionUID = 1L;
	
	
	/** NOME DO ASSUNTO DE INTERESSE */
	@NotNull
	@Size(max = 60)
	@Column(unique=true)
	private String nome;
	
	

	
	@Override
	public int compareTo(AssuntoInteresse o) { 
		if (nome == null)	return 1;
		if (o.nome == null) return -1;
		
		int cmp = nome.compareTo(o.nome);
		if (cmp != 0) return cmp;
		
		return super.compareTo(o); 
	}
	
	
	@Override
	public String toString() { return nome; }

	
	
	
	
	/**  GETS AND SETS  */
	public String getNome() {return nome; }
	public void setNome(String nome) { 	this.nome = nome; }
	
	
	
}
