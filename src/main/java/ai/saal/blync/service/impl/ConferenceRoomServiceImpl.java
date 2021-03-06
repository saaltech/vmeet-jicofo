package ai.saal.blync.service.impl;

import ai.saal.blync.dto.ConferenceRes;
import ai.saal.blync.util.BlyncUrl;
import ai.saal.blync.dto.ConferenceStatus;
import ai.saal.blync.service.ConferenceRoomService;
import ai.saal.blync.util.RestClient;
import org.jitsi.utils.logging.Logger;

public class ConferenceRoomServiceImpl implements ConferenceRoomService {


    String  url =  BlyncUrl.getUrl()+"/conferences/{confId}/status";
    private final static Logger logger
            = Logger.getLogger(ConferenceRoomServiceImpl.class);
    @Override
    public Boolean updateRoomState(String conferenceId, String status) {
        conferenceId = conferenceId.split("@")[0];
        logger.info("invoking status update api for Room name "+conferenceId);
        String hostUrl = url.replace("{confId}",conferenceId);
        ConferenceStatus conferenceStatus = new ConferenceStatus();
        conferenceStatus.setStatus(status);
        RestClient.updateConferenceState(hostUrl, conferenceStatus);
        return false;
    }

    @Override
    public Boolean isDirectCall(String conferenceId) {
        conferenceId = conferenceId.split("@")[0];
        logger.info("invoking get details api for conference id "+conferenceId);
        String conferenceUrl = BlyncUrl.getUrl() + "/conferences/" + conferenceId;
        ConferenceRes conferenceDetails = RestClient.getConferenceDetails(conferenceUrl);
        return conferenceDetails.getIsDirectCall();
    }


}


