package br.ufes.inf.nemo.marvin.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Path("/changePassword")
public class ChangePasswordRestService {
	/** TODO: document this field. */
	@Context
  private HttpServletRequest request;
  
	/** TODO: document this field. */
	@Context 
  private HttpServletResponse response;
	
	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/core/changePassword/";

	@GET @Path("{passwordCode}")
	public Response begin(@PathParam("passwordCode") String passwordCode) throws IOException {
		// FIXME: send the password code to the JSF controller.
		String url = VIEW_PATH + "index.faces";
		response.sendRedirect(request.getContextPath() + url);
		return Response.status(Status.ACCEPTED).build();
	}
}
