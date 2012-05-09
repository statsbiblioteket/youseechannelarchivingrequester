package dk.statsbiblioteket.mediaplatform.ingest.model.service;

import dk.statsbiblioteket.mediaplatform.ingest.channelarchivingrequester.ChannelArchivingRequesterException;
import dk.statsbiblioteket.mediaplatform.ingest.model.YouSeeChannelMapping;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.YouSeeChannelMappingDAO;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.YouSeeChannelMappingDAOIF;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class YouSeeChannelMappingService implements YouSeeChannelMappingServiceIF {
    @Override
    public YouSeeChannelMapping getUniqueMappingFromYouSeeChannelId(String youSeeChannelId, Date date) throws ChannelArchivingRequesterException {
        YouSeeChannelMappingDAOIF dao = new YouSeeChannelMappingDAO();
        List<YouSeeChannelMapping> mappings = dao.getMappingsFromYouSeeChannelId(youSeeChannelId, date);
        if (mappings.size() == 1) {
            return mappings.get(0);
        } else {
            throw new ChannelArchivingRequesterException("Expected a unique mapping for '" + youSeeChannelId + " at "
                    + date + " but found " + mappings.size());
        }
    }

    @Override
    public YouSeeChannelMapping getUniqueMappingFromSbChannelId(String sBChannelId, Date date) throws ChannelArchivingRequesterException {
        YouSeeChannelMappingDAOIF dao = new YouSeeChannelMappingDAO();
        List<YouSeeChannelMapping> mappings = dao.getMappingsFromSbChannelId(sBChannelId, date);
        if (mappings.size() == 1) {
            return mappings.get(0);
        } else {
            throw new ChannelArchivingRequesterException("Expected a unique mapping for '" + sBChannelId  + " at "
                    + date + " but found " + mappings.size());
        }
    }
}
