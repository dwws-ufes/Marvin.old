package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.application.InstallEvent;

@Local
public interface InstallQualisService extends Serializable {
	public void installQualis(InstallEvent event);
}
