package br.ufes.inf.nemo.marvin.core.application;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.AcademicType;
import br.ufes.inf.nemo.marvin.core.domain.Academic_;
import br.ufes.inf.nemo.util.TextUtils;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.identity.Role;



public class LoginService  implements LoginModule {

	
	private static final Logger logger = Logger.getLogger(LoginService.class.getCanonicalName());
	
	private Academic academic;
	
	
	private Subject subject;
	private CallbackHandler callbackHandler; 
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map sharedState; 
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
	private boolean loginOk;
	private Principal identity;
	private char[] credential;
	
	
	   
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		logger.log(Level.INFO, "LOGIN  INITIALIZE INIT");
		this.subject = subject;
	    this.callbackHandler = callbackHandler;
	    this.sharedState = sharedState;
	    this.options = options;	 
	    logger.log(Level.INFO, "LOGIN  INITIALIZE OK");
	}

	@Override
	public boolean login() throws LoginException {
		loginOk = false;
	    getUsernameAndPassword(); 
	    logger.log(Level.INFO, "LOGIN  LOGIN - GETUSERNAME E PASSWORD OK");
	    getUser();
	    logger.log(Level.INFO, "LOGIN  LOGIN - GETUSER OK");
	    validateUser(); 
	    logger.log(Level.INFO, "LOGIN  LOGIN - VALIDATE USER OK");
	    loginOk = true;
	    return true;
	}

	@Override
	public boolean commit() throws LoginException {
		logger.log(Level.INFO, "LOGIN  COMMIT - INIT");
		if( loginOk == false )return false;
		Group callerGroup ;
	    callerGroup = new SimpleGroup("CallerPrincipal");
	    callerGroup.addMember(identity);
		Set<Principal> principals = subject.getPrincipals();
	    principals.add(identity);  
	    principals.add(getRoleSets());
	    principals.add(callerGroup);
	    
	    logger.log(Level.INFO, "LOGIN  COMMIT - OK");
	    return true;
	}
	
	
	

	@Override
	public boolean abort() throws LoginException {
		logger.log(Level.INFO, "LOGIN  ABORT - OK");
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		logger.log(Level.INFO, "LOGIN  LOGOUT- INIT ");
	    Set<Principal> principals = subject.getPrincipals();
	    principals.remove(identity);
	    principals.clear();
	    if(principals.isEmpty())
	    	logger.log(Level.INFO, "MYLOGIN  LOGOUT OK");
	    return true;
	}
	
	
	
	protected void getUsernameAndPassword() throws LoginException{
	    if( callbackHandler == null ){ throw new LoginException();  } 
	    NameCallback nc = new NameCallback("username");
	    PasswordCallback pc = new PasswordCallback("password: ", false);
	    Callback[] callbacks = {nc, pc};
        try {callbackHandler.handle(callbacks);}
        catch (Exception e) {throw new LoginException();} 
	    identity = new SimplePrincipal(nc.getName());
	    logger.log(Level.INFO, "LOGIN  IDENTY = {0}",nc.getName());
	    char[] tmpPassword = pc.getPassword();
	    if( tmpPassword != null ){
	    	credential = new char[tmpPassword.length];
	    	System.arraycopy(tmpPassword, 0, credential, 0, tmpPassword.length);
	        pc.clearPassword();
	    }
	}

	
	
	
	
	protected void getAdmin() throws LoginException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("saeLogin");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Academic> cq = cb.createQuery(Academic.class);
		Root<Academic> root = cq.from(Academic.class);
		cq.where(  cb.equal(root.get(Academic_.email), identity.getName()));
		try{
			academic = em.createQuery(cq).getSingleResult();
			logger.log(Level.INFO, "USUARIO CADASTRADO COMO Academic)");
		}
		catch (Exception e) {
			academic =null;
			logger.log(Level.INFO, "USUARIO NAO CADASTRADO COMO Academic)");
		}
		
		tx.commit();
		em.close();
		emf.close();
		
	}
	
	
	
	
	
	
	protected void getUser() throws LoginException {
		logger.log(Level.INFO, "LOGIN SQL IDENTY = {0}",identity.getName());
		getAdmin();
	}

	
	
	protected Group getRoleSets() throws LoginException {
		logger.log(Level.INFO, "LOGIN  BUSCANDO ROLES");
		SimpleGroup group = new SimpleGroup("Roles");
		
		if(academic!=null){
			Iterator<AcademicType> lista = academic.getAcademicTypes().iterator();
			
			while(lista.hasNext()){
				group.addMember(new SimplePrincipal(lista.next().getLabel()));
			}
		}
        return group ;
	}
	
	
	
	
	protected void validateUser( ) throws LoginException {
		logger.log(Level.INFO, "LOGIN VALIDATE");
		try {
			String md5password = TextUtils.produceMd5Hash(new String(credential));
			
			if(academic!=null){
				if( md5password == null || !md5password.equals(academic.getPassword()) ){
		            throw new LoginException(); 
				}
				else{
					logger.log(Level.INFO, "ADMIN VALIDADO");
					return;
				}
			}
			
		} catch (NoSuchAlgorithmException e) {
			throw new LoginException();
		}
               
    }
	
	
}
