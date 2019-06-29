package com.app.server;

import com.app.config.AppConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

/**
 * Created by vmusil on 29-Jun-2019.
 *
 * Inspired from: https://github.com/fernandospr/spring-jetty-example
 */
public class EmbeddedServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedServer.class);

    private static final int PORT = 8080;

    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION_PACKAGE = "com.app.config";
    private static final String MAPPING_URL = "/";
    private static final String WEBAPP_DIRECTORY = "webapp";


    public static void startJetty() throws Exception {
        LOGGER.debug("Starting server at port {}", PORT);
        Server server = new Server(PORT);

        server.setHandler(getServletContextHandler());

        addRuntimeShutdownHook(server);

        server.start();
        LOGGER.info("Server started at port {}", PORT);
        server.join();
    }

    private static ServletContextHandler getServletContextHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS); // SESSIONS needed for JSP
        contextHandler.setErrorHandler(null);

//        contextHandler.setResourceBase(new ClassPathResource(WEBAPP_DIRECTORY).getURI().toString());
        contextHandler.setContextPath(CONTEXT_PATH);

//        // JSP
//        contextHandler.setClassLoader(Thread.currentThread().getContextClassLoader()); // due to the JspServlet
//        contextHandler.addServlet(JspServlet.class, "*.jsp");

        // Spring
        WebApplicationContext webAppContext = getWebApplicationContext();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);

        ServletHolder springServletHolder = new ServletHolder("mvc-dispatcher", dispatcherServlet);
        contextHandler.addServlet(springServletHolder, MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(webAppContext));

        return contextHandler;
    }

    private static WebApplicationContext getWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION_PACKAGE);
        return context;
    }

//    private static AnnotationConfigApplicationContext registerSpringAppContext() {
//        // register beans scanning
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class);
//        ctx.refresh();
//
//        return ctx;
//    }

    private static void addRuntimeShutdownHook(final Server server) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (server.isStarted()) {
                server.setStopAtShutdown(true);

                try {
                    server.stop();
                } catch (Exception e) {
                    System.out.println("Error while stopping jetty server: " + e.getMessage());
                    LOGGER.error("Error while stopping jetty server: " + e.getMessage(), e);
                }
            }
        }));
    }
}
