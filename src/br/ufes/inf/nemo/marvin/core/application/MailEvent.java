package br.ufes.inf.nemo.marvin.core.application;

import java.util.Map;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
public class MailEvent {
	/** TODO: document this field. */
	private String emailAddress;

	/** TODO: document this field. */
	private String subjectKey;

	/** TODO: document this field. */
	private MailerTemplate mailerTemplate;

	/** TODO: document this field. */
	private Map<String, Object> dataModel;

	/** Constructor. */
	public MailEvent(String emailAddress, String subjectKey, MailerTemplate mailerTemplate, Map<String, Object> dataModel) {
		this.emailAddress = emailAddress;
		this.subjectKey = subjectKey;
		this.mailerTemplate = mailerTemplate;
		this.dataModel = dataModel;
	}

	/** Getter for emailAddress. */
	public String getEmailAddress() {
		return emailAddress;
	}

	/** Getter for subjectKey. */
	public String getSubjectKey() {
		return subjectKey;
	}

	/** Getter for mailerTemplate. */
	public MailerTemplate getMailerTemplate() {
		return mailerTemplate;
	}

	/** Getter for dataModel. */
	public Map<String, Object> getDataModel() {
		return dataModel;
	}
}
