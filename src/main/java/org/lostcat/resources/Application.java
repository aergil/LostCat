package org.lostcat.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.api.view.Viewable;

@Path(Paths.application)
@Produces(Constantes.Text_Html_UTF8)
public class Application {

	@GET
	@Path("/links")
	public Viewable links() {
		return new Viewable("/links");
	}

	@GET
	@Path("/index")
	public Viewable index() {
		return new Viewable("/index");
	}

	@GET
	@Path("/contact")
	public Viewable contact() {
		return new Viewable("/contact");
	}

}
