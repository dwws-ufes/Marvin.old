package br.ufes.inf.nemo.marvin.sae.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;




/**
 * CLASSE DE DOMMINIO QUE REPRESENTA OS SEMINARIOS.
 * 
 * IMPLEMENTADA COM BASE NO Documento de Análise de Requisitos VERSÃO 1.4
 * 
 * <i>ESTA CLASSE FAZ PARTE DO SISTEMA SAE.</i>
 * 
 * @author BRUNO MANZOLI (manzoli2122@gmail.com)
 */

@Entity
public class Seminario  extends PersistentObjectSupport implements Comparable<Seminario>{

	
	private static final long serialVersionUID = 1L;
	
	
	/** PALESTRANTE DO SEMINARIO */
	@Size(max = 60)
	private String palestrante;
	
	
	/** DATA DO SEMINARIO */
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_hora;
	
	
	/** LOCAL ONDE SERÁ REALIZADO O SEMINARIO */
	@Size(max = 60)
	private String local;

	
	/** TITULO DO SEMINARIO */
	@NotNull
	@Size(max = 60)
	private String titulo;
	
	
	/** ASSUNTO DO SEMINARIO*/
	@NotNull
	@ManyToOne
	private AssuntoInteresse assunto_interesse;
	
	
	@ManyToOne
	private Egresso egresso_Palestrante;
	
	
	
	
	
	
	
	

	@Override
	public int compareTo(Seminario  o) { return super.compareTo(o); }
	
	@Override
	public String toString() { return titulo; }

	
	
	/**  GETS AND SETS  */
	public String getPalestrante() { return palestrante; 	}
	public void setPalestrante(String palestrante) { this.palestrante = palestrante; }

	public Date getData_hora() { return data_hora; }
	public void setData_hora(Date data_hora) { this.data_hora = data_hora; }

	public AssuntoInteresse getAssunto_interesse() { return assunto_interesse; }
	public void setAssunto_interesse(AssuntoInteresse assunto_interesse) {this.assunto_interesse = assunto_interesse;}

	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }

	public String getLocal() { 	return local; }
	public void setLocal(String local) { this.local = local; }
	
	public Egresso getEgresso_Palestrante() { return egresso_Palestrante;  	}
	public void setEgresso_Palestrante(Egresso egresso_Palestrante) { 
		this.egresso_Palestrante = egresso_Palestrante;
		this.palestrante = egresso_Palestrante.getName();
	}

	
}
