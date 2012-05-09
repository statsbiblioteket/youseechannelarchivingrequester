package dk.statsbiblioteket.mediaplatform.ingest.model.persistence;

import dk.statsbiblioteket.generic.utils.GenericHibernateDAO;
import dk.statsbiblioteket.mediaplatform.ingest.channelarchivingrequester.ChannelArchivingRequesterException;
import dk.statsbiblioteket.mediaplatform.ingest.model.YouSeeChannelMapping;
import org.hibernate.Query;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class YouSeeChannelMappingDAO extends GenericHibernateDAO<YouSeeChannelMapping, Long> implements YouSeeChannelMappingDAOIF {

    /**
     * Constructor for this DAO class.
     */
    public YouSeeChannelMappingDAO() {
        super(YouSeeChannelMapping.class);
    }




    @Override
    public List<YouSeeChannelMapping> getMappingsFromYouSeeChannelId(String youSeeChannelId, Date date) {
        final Query query = getSession().createQuery("FROM YouSeeChannelMapping WHERE youSeeChannelId = :id AND " +
                "fromDate <= :date AND toDate >= :date");
        return query.setParameter("id", youSeeChannelId).setDate("date", date).list();
    }


    @Override
    public List<YouSeeChannelMapping> getMappingsFromSbChannelId(String sBChannelId, Date date) {
        final Query query = getSession().createQuery("FROM YouSeeChannelMapping WHERE sbChannelId = :id AND " +
                "fromDate <= :date AND toDate >= :date");
         return query.setParameter("id", sBChannelId).setDate("date", date).list();
    }



}
