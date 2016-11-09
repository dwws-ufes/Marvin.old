package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Map;

import javax.ejb.Singleton;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.ResourceUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Singleton
public class Mailer implements Serializable {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	// FIXME: implement i18n for this. 
	/** The path (in the EJB module) for the e-mail templates. */
	private static final String TEMPLATE_FOLDER_PATH = "/META-INF/mailer/en_US/";

	/** The file extension used by templates. */
	private static final String TEMPLATE_FILE_EXTENSION = ".ftlh";

	/** TODO: document this field. */
	private Configuration freeMarkerCfg;

	/**
	 * TODO: document this method.
	 * 
	 * @throws MailerException
	 */
	@Inject
	private void init() throws MailerException {
		try {
			// Configures FreeMarker, according to its manual:
			// http://freemarker.org/docs/pgui_quickstart_createconfiguration.html.
			freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_25);
			freeMarkerCfg.setDirectoryForTemplateLoading(ResourceUtil.getResourceAsFile(TEMPLATE_FOLDER_PATH));
			freeMarkerCfg.setDefaultEncoding("UTF-8");
			freeMarkerCfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			freeMarkerCfg.setLogTemplateExceptions(false);
		}
		catch (Exception e) {
			throw new MailerException(e);
		}
	}

	/**
	 * TODO: document this method.
	 * 
	 * @param templateName
	 * @param dataModel
	 * @throws MailerException
	 */
	public void sendEmail(MailerTemplate mailerTemplate, Map<String, Object> dataModel) throws MailerException {
		try {
			// Loads the e-mail template file.
			Template template = freeMarkerCfg.getTemplate(mailerTemplate + TEMPLATE_FILE_EXTENSION);
			
			// Merges the template with the data model and writes the result to a string.
			String emailMsg = null;
			try (StringWriter writer = new StringWriter()) {
				template.process(dataModel, writer);
				emailMsg = writer.getBuffer().toString();
			}
			
			// FIXME: send the actual e-mail once we know that FreeMarker is working.
			System.out.println(emailMsg);
	
			//	logger.log(Level.INFO, "At {0}, sending confirmation code {1} to e-mail {2}.", new Object[] { emailConfirmation.getTimestamp(), emailConfirmation.getConfirmationCode(), emailAddress });
			//	Email email = new SimpleEmail();
			//	email.setHostName(config.getSmtpServerAddress());
			//	email.setSmtpPort(config.getSmtpServerPort());
			//	email.setAuthenticator(new DefaultAuthenticator(config.getSmtpUsername(), config.getSmtpPassword()));
			//	email.setSSLOnConnect(false);
			//	email.setFrom(config.getSmtpUsername());
			//	email.setSubject(I18n.getFormattedString("email.confirmationCode.subject", config.getOwner().getAcronym()));
			//	email.setMsg(I18n.getFormattedString("email.confirmationCode.text", emailConfirmation.getConfirmationCode(), config.getOwner().getAcronym()));
			//	email.addTo(emailAddress);
			//	email.send();
		}
		catch (Exception e) {
			throw new MailerException(e);
		}
	}
}
