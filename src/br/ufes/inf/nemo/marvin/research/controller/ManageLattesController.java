package br.ufes.inf.nemo.marvin.research.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.SessionInformation;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.Publication;

@Named
@SessionScoped
public class ManageLattesController extends JSFController {
	private static final long serialVersionUID = 1L;

	@EJB
	private SessionInformation sessionInformation;

	private List<Publication> publications = new ArrayList<Publication>();

	private Part file;

	/***
	 * Read the uploaded XML file, and store the bibliography into the database
	 * 
	 * @param currentUser
	 *          - The user logged in the system
	 */
	public String readXML(Academic currentUser) {
		try {

			InputStream input = file.getInputStream();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();

			// Fetch all elements under the PRODUCAO-BIBLIOGRAFICA tag
			NodeList nList = doc.getElementsByTagName("PRODUCAO-BIBLIOGRAFICA");

//			for (int temp = 0; temp < nList.getLength(); temp++) {
//
//				NodeList productions = nList.item(0).getChildNodes();
//
//				for (int temp_1 = 0; temp_1 < productions.getLength(); temp_1++) {
//					NodeList works = productions.item(temp_1).getChildNodes();
//					String typeOfProduction = productions.item(temp_1).getNodeName();
//
//					for (int temp_2 = 0; temp_2 < works.getLength(); temp_2++) {
//
//						Node node = works.item(temp_2);
//						if (node.getNodeType() == Node.ELEMENT_NODE) {
//							Element eElement = (Element) node;
//
//							// BibTex @inproceedings
//							if (typeOfProduction == "TRABALHOS-EM-EVENTOS") {
//								Element dados = (Element) eElement.getElementsByTagName("DADOS-BASICOS-DO-TRABALHO").item(0);
//								NodeList authors = eElement.getElementsByTagName("AUTORES");
//
//								Element detalhes = (Element) eElement.getElementsByTagName("DETALHAMENTO-DO-TRABALHO").item(0);
//
//								if (dados.getAttribute("DOI") == "" || proceedingDAO.retrieveByDOI(dados.getAttribute("DOI")) == null) {
//									if (proceedingDAO.retrieveByTitle(dados.getAttribute("TITULO-DO-TRABALHO")) == null) {
//										// Instantiate a new proceeding object
//										InProceedings proceeding = new InProceedings();
//
//										proceeding.setTitle(dados.getAttribute("TITULO-DO-TRABALHO"));
//										proceeding.setYear(dados.getAttribute("ANO-DO-TRABALHO"));
//										proceeding.setBookTitle(detalhes.getAttribute("TITULO-DOS-ANAIS-OU-PROCEEDINGS"));
//										proceeding.setPages(detalhes.getAttribute("PAGINA-INICIAL") + "-" + detalhes.getAttribute("PAGINA-FINAL"));
//										proceeding.setDoi(dados.getAttribute("DOI"));
//										proceeding.setOwner(currentUser);
//
//										proceedingDAO.save(proceeding);
//
//										for (int temp_4 = 0; temp_4 < authors.getLength(); temp_4++) {
//											AuthorPublication author_publication = new AuthorPublication();
//											String author_name = ((Element) authors.item(temp_4)).getAttribute("NOME-COMPLETO-DO-AUTOR");
//											Author author_db = authorDAO.retrieveByName(author_name);
//
//											if (author_db != null) {
//												author_publication.setPublication(proceeding);
//												author_publication.setAuthor(author_db);
//												authorPublicationDAO.save(author_publication);
//											}
//											else {
//												Author author_dblp = new Author();
//												author_dblp.setName(author_name);
//												authorDAO.save(author_dblp);
//
//												author_publication.setPublication(proceeding);
//												author_publication.setAuthor(author_dblp);
//												authorPublicationDAO.save(author_publication);
//											}
//										}
//									}
//								}
//								else {
//									System.out.println("Proceeding with this DOI already exists");
//								}
//							}
//
//							// BibTex @article
//							if (typeOfProduction == "ARTIGOS-PUBLICADOS") {
//								Element dados = (Element) eElement.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO").item(0);
//								NodeList authors = eElement.getElementsByTagName("AUTORES");
//								Element detalhes = (Element) eElement.getElementsByTagName("DETALHAMENTO-DO-ARTIGO").item(0);
//
//								// There is no article with this title
//								if (dados.getAttribute("DOI") == "" || articleDAO.getArticleByDOI(dados.getAttribute("DOI")) == null) {
//									// There is no article with this doi
//									if (articleDAO.getArticleByTitle(dados.getAttribute("TITULO-DO-ARTIGO")) == null) {
//
//										// This article doesn't exists in our database, so, create a new one
//										Article article = new Article();
//
//										article.setTitle(dados.getAttribute("TITULO-DO-ARTIGO"));
//										article.setJournal(detalhes.getAttribute("TITULO-DO-PERIODICO-OU-REVISTA"));
//										article.setVolume(detalhes.getAttribute("VOLUME"));
//										article.setPages(detalhes.getAttribute("PAGINA-INICIAL") + "-" + detalhes.getAttribute("PAGINA-FINAL"));
//										article.setDoi(dados.getAttribute("DOI"));
//										article.setIssn(detalhes.getAttribute("ISSN"));
//										article.setYear(dados.getAttribute("ANO-DO-ARTIGO"));
//										article.setOwner(currentUser);
//										articleDAO.save(article);
//
//										for (int temp_3 = 0; temp_3 < authors.getLength(); temp_3++) {
//											AuthorPublication author_publication = new AuthorPublication();
//											String author_name = ((Element) authors.item(temp_3)).getAttribute("NOME-COMPLETO-DO-AUTOR");
//											Author author_db = authorDAO.retrieveByName(author_name);
//
//											if (author_db != null) {
//												author_publication.setPublication(article);
//												author_publication.setAuthor(author_db);
//												authorPublicationDAO.save(author_publication);
//											}
//											else {
//												Author author_dblp = new Author();
//												author_dblp.setName(author_name);
//												authorDAO.save(author_dblp);
//
//												author_publication.setPublication(article);
//												author_publication.setAuthor(author_dblp);
//												authorPublicationDAO.save(author_publication);
//											}
//										}
//									}
//									else {
//										// Here we should check what've changed
//										System.out.println("DOI already exists");
//									}
//								}
//								else {
//									// Here we should check what've changed
//									System.out.println("Title already exists");
//								}
//							}
//
//							if (typeOfProduction == "LIVROS-E-CAPITULOS") {
//								// BibTex @book
//								if (eElement.getNodeName() == "LIVROS-PUBLICADOS-OU-ORGANIZADOS") {
//									NodeList books = eElement.getChildNodes();
//									System.out.println("LIVROS PUBLICADOS OU ORGANIADOS");
//									for (int temp_5 = 0; temp_5 < books.getLength(); temp_5++) {
//										Node book = books.item(temp_5);
//
//										if (book.getNodeType() == Node.ELEMENT_NODE) {
//											Element book_e = (Element) book;
//											Element dados = (Element) book_e.getElementsByTagName("DADOS-BASICOS-DO-LIVRO").item(0);
//											Element details = (Element) book_e.getElementsByTagName("DETALHAMENTO-DO-LIVRO").item(0);
//											NodeList authors = book_e.getElementsByTagName("AUTORES");
//
//											if (bookDAO.retrieveByTitle(dados.getAttribute("TITULO-DO-LIVRO")) == null) {
//												// Instantiate a new book
//												Book book_published = new Book();
//
//												book_published.setTitle(dados.getAttribute("TITULO-DO-LIVRO"));
//												book_published.setEdition(details.getAttribute("NUMERO-DA-EDICAO-REVISAO"));
//												book_published.setYear(dados.getAttribute("ANO"));
//												book_published.setOwner(currentUser);
//												bookDAO.save(book_published);
//
//												for (int temp_3 = 0; temp_3 < authors.getLength(); temp_3++) {
//
//													AuthorPublication author_publication = new AuthorPublication();
//													String author_name = ((Element) authors.item(temp_3)).getAttribute("NOME-COMPLETO-DO-AUTOR");
//													Author author_db = authorDAO.retrieveByName(author_name);
//
//													if (author_db != null) {
//														author_publication.setPublication(book_published);
//														author_publication.setAuthor(author_db);
//														authorPublicationDAO.save(author_publication);
//													}
//													else {
//														Author author_dblp = new Author();
//														author_dblp.setName(author_name);
//														authorDAO.save(author_dblp);
//
//														author_publication.setPublication(book_published);
//														author_publication.setAuthor(author_dblp);
//														authorPublicationDAO.save(author_publication);
//													}
//												}
//											}
//											else {
//												System.out.println("There is a book with this title");
//											}
//										}
//									}
//								}
//
//								// BibTex @incollection ou @inbook
//								if (eElement.getNodeName() == "CAPITULOS-DE-LIVROS-PUBLICADOS") {
//									NodeList chapters = eElement.getChildNodes();
//									System.out.println("CAPITULOS-DE-LIVROS-PUBLICADOS");
//									for (int temp_6 = 0; temp_6 < chapters.getLength(); temp_6++) {
//										Node chapter = chapters.item(temp_6);
//
//										if (chapter.getNodeType() == Node.ELEMENT_NODE) {
//											Element chapter_e = (Element) chapter;
//											Element dados = (Element) chapter_e.getElementsByTagName("DADOS-BASICOS-DO-CAPITULO").item(0);
//											Element details = (Element) chapter_e.getElementsByTagName("DETALHAMENTO-DO-CAPITULO").item(0);
//											NodeList authors = chapter_e.getElementsByTagName("AUTORES");
//
//											if (collectionDAO.retrieveByTitle(dados.getAttribute("TITULO-DO-CAPITULO-DO-LIVRO")) == null) {
//												// Instantiate a new chapter
//												InCollection collection = new InCollection();
//
//												collection.setTitle(dados.getAttribute("TITULO-DO-CAPITULO-DO-LIVRO"));
//												collection.setYear(dados.getAttribute("ANO"));
//												collection.setBookTitle(details.getAttribute("TITULO-DO-LIVRO"));
//												collection.setPages(details.getAttribute("PAGINA-INICIAL") + "-" + details.getAttribute("PAGINA-FINAL"));
//												collection.setPublisher(details.getAttribute("NOME-DA-EDITORA"));
//												collection.setOwner(currentUser);
//
//												collectionDAO.save(collection);
//
//												for (int temp_3 = 0; temp_3 < authors.getLength(); temp_3++) {
//													AuthorPublication author_publication = new AuthorPublication();
//													String author_name = ((Element) authors.item(temp_3)).getAttribute("NOME-COMPLETO-DO-AUTOR");
//													Author author_db = authorDAO.retrieveByName(author_name);
//
//													if (author_db != null) {
//														author_publication.setPublication(collection);
//														author_publication.setAuthor(author_db);
//														authorPublicationDAO.save(author_publication);
//													}
//													else {
//														Author author_dblp = new Author();
//														author_dblp.setName(author_name);
//														authorDAO.save(author_dblp);
//
//														author_publication.setPublication(collection);
//														author_publication.setAuthor(author_dblp);
//														authorPublicationDAO.save(author_publication);
//													}
//												}
//											}
//											else {
//												System.out.println("There is a chapter with this title");
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//
//			}
			return "curriculo.xhtml?faces-redirect=true";
		}
		catch (

		Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
}
