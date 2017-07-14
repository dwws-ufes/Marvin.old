package br.ufes.inf.nemo.marvin.research.application;

import java.util.Set;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.JournalPaper;
import br.ufes.inf.nemo.marvin.research.domain.Book;
import br.ufes.inf.nemo.marvin.research.domain.BookChapter;
import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
public interface PublicationInfo {
	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	Long getLattesId();

	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	Academic getResearcher();

	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	long getPreviousQuantity();

	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	Set<Book> getBooks();

	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	Set<BookChapter> getBookChapters();

	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	Set<ConferencePaper> getConferencePapers();

	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	Set<JournalPaper> getJournalPapers();
}
