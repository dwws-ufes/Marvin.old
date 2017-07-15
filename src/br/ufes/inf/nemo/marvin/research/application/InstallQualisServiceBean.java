package br.ufes.inf.nemo.marvin.research.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import br.ufes.inf.nemo.marvin.core.application.InstallEvent;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.persistence.QualisDAO;

@Stateless
public class InstallQualisServiceBean implements InstallQualisService {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@EJB
		private QualisDAO qualisDAO;
		
		public void installQualis(@Observes InstallEvent event) {
			
			Qualis a1 = new Qualis("A1");
			Qualis a2 = new Qualis("A2");
			Qualis b1 = new Qualis("B1");
			Qualis b2 = new Qualis("B2");
			Qualis b3 = new Qualis("B3");
			Qualis b4 = new Qualis("B4");
			Qualis b5 = new Qualis("B5");
			Qualis c = new Qualis("C");
			
			qualisDAO.save(a1);
			qualisDAO.save(a2);
			qualisDAO.save(b1);
			qualisDAO.save(b2);
			qualisDAO.save(b3);
			qualisDAO.save(b4);
			qualisDAO.save(b5);
			qualisDAO.save(c);
			
		}
}
