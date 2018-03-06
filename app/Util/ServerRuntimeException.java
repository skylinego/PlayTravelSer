package Util;

import java.util.Map;

/**
 * Created by dliu15 on 3/1/18.
 */

public class ServerRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -7387875471451264809L;
    private String messageID;
    private Map<String, Object> replaceMap;

    private long errorCode = 200;

    private boolean isGoOnThrowing = false;
    private boolean isMuted = false;

    public ServerRuntimeException(String messageID) {
        this(messageID, (Map<String, Object>) null);
    }

    public ServerRuntimeException(long errorCode, String messageID){
        this(messageID, (Map<String, Object>) null);
        this.errorCode = errorCode;
    }

    public ServerRuntimeException(String messageID, Object... list) {
        this.messageID = messageID;
        //this.replaceMap = PlayUtil.objAryToMap(list);
    }
}
