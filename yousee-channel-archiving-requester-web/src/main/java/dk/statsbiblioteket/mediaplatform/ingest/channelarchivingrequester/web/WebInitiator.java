package dk.statsbiblioteket.mediaplatform.ingest.channelarchivingrequester.web;

import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.ChannelArchivingRequesterHibernateUtil;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.HibernateUtilIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 *
 */
public class WebInitiator implements ServletContextListener {

    private static Logger log = LoggerFactory.getLogger(WebInitiator.class);

    /**
     * Place any initialisations or configuration sanity-checks here.
     * @param sce the context in which this class is intiated.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initializing CAR service v{}", getClass().getPackage().getImplementationVersion());
        try {
            InitialContext ctx = new InitialContext();
            String cfgPath = (String) ctx.lookup("java:/comp/env/hibernate_cfg");
            //String cfgPath= sce.getServletContext().getInitParameter("hibernate_cfg");
            final File cfgFile = new File(cfgPath);
            log.info("Reading hibernate configuration from " + cfgFile.getAbsolutePath());
            HibernateUtilIF util = ChannelArchivingRequesterHibernateUtil.initialiseFactory(cfgFile);
            util.getSession();
        } catch (NamingException e) {
            log.error("Failed to lookup hibernate config", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("CAR service destroyed");
    }
}
