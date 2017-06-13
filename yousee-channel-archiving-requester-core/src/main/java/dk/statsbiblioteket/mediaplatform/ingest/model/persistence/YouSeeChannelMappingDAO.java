package dk.statsbiblioteket.mediaplatform.ingest.model.persistence;

import dk.statsbiblioteket.mediaplatform.ingest.model.YouSeeChannelMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class YouSeeChannelMappingDAO extends GenericHibernateDAO<YouSeeChannelMapping, Long> implements YouSeeChannelMappingDAOIF {

    /**
     * Constructor for this DAO class.
     */
    public YouSeeChannelMappingDAO(HibernateUtilIF util) {
        super(YouSeeChannelMapping.class, util);
    }

    @Override
    public List<YouSeeChannelMapping> getMappingsFromYouSeeChannelId(String youSeeChannelId, Date date) {
        Session sess = null;
        try {
            sess = getSession();
            final Query query = sess.createQuery("FROM YouSeeChannelMapping WHERE youSeeChannelId = :id AND " +
                    "fromDate <= :date AND toDate >= :date");
            return query.setParameter("id", youSeeChannelId).setDate("date", date).list();
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
    }


    @Override
    public List<YouSeeChannelMapping> getMappingsFromSbChannelId(String sBChannelId, Date date) {
        Session sess = null;
        try {
            sess = getSession();
            final Query query = sess.createQuery("FROM YouSeeChannelMapping WHERE sbChannelId = :id AND " +
                    "fromDate <= :date AND toDate >= :date");
            return query.setParameter("id", sBChannelId).setDate("date", date).list();
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
    }

    @Override
    public List<YouSeeChannelMapping> getAllMappings() {
        Session sess = null;
        try {
            sess = getSession();
            return sess.createQuery("from YouSeeChannelMapping ORDER BY toDate desc").list();
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
    }

    @Override
    public YouSeeChannelMapping getMappingByID(Long id) {
        Session sess = null;
        try {
            sess = getSession();
            Query query = sess.createQuery("FROM YouSeeChannelMapping WHERE id = :id").setLong("id", id);
            if (query.list().size() == 1)
                return (YouSeeChannelMapping) query.list().get(0);
            else
                return null;
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
    }

}