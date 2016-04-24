package br.ufes.inf.nemo.marvin.sae.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.sae.core.domain.Seminario;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
@DeclareRoles({"Admin", "egresso", "guest"})
@RolesAllowed({ "Admin" })
public interface ManageSeminarioService  extends CrudService<Seminario> {

}
