package org.lostcat.resources;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.container.ContainerException;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.spi.resource.Singleton;
import com.sun.jersey.spi.template.ViewProcessor;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Provider
@Singleton
public class FreemarkerViewProcessor implements ViewProcessor<String> {

	public final static String FREEMARKER_TEMPLATES_BASE_PATH = "com.sun.jersey.freemarker.templateBasePath";
	private final Configuration configuration;
	@Context
	private UriInfo uriInfo;

	public FreemarkerViewProcessor(@Context ResourceConfig resourceConfig) {
		try {
			configuration = new Configuration();
			configuration.setLocale(java.util.Locale.FRANCE);
			configuration.setNumberFormat("0.####");
			configuration.setEncoding(java.util.Locale.FRANCE, "utf-8");
			configuration.setOutputEncoding("utf-8");
			configuration.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
			String basePath = (String) resourceConfig.getProperties().get(FREEMARKER_TEMPLATES_BASE_PATH);
			configuration.setDirectoryForTemplateLoading(new File(basePath));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String resolve(String path) {
		if (!path.endsWith(".ftl"))
			path = path + ".ftl";

		return path;
	}

	@Override
	public void writeTo(String resolvedPath, Viewable viewable, OutputStream out) throws IOException {
		try {
			out.flush();
			final Template template = configuration.getTemplate(resolvedPath);
			template.process(viewable.getModel(), new OutputStreamWriter(out));
		} catch (TemplateException te) {
			throw new ContainerException(te);
		}
	}

}