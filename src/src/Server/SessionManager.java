package Server;

import java.util.HashMap;
import java.util.UUID;


public class SessionManager {
    private static final Long Session_Timeout = (long) (10*60*1000);
    private static HashMap<String,Long> session = new HashMap<>();

    public static String createSession(String username) {//生成唯一令牌sessionTOKEN，并记录会话开始时间，并返回唯一令牌
        String sessionId= UUID.randomUUID().toString();//sessionTOKEN
        long expiryTime = System.currentTimeMillis() + Session_Timeout;
        session.put(sessionId, expiryTime);
        System.out.println("Session created for user " + username + " , token:" + sessionId);
        return sessionId;
    }
    public static boolean isSessionvalid(String sessionId) {
        Long startTime = session.get(sessionId);
        if (startTime == null) {
            return false;
        }
        Long endTime = System.currentTimeMillis() - startTime;
        if (endTime < startTime) {
            session.remove(sessionId);
            return false;
        }
        return true;

    }
    public static void endSession(String sessionToken) {
        session.remove(sessionToken);
        System.out.println("Session " + sessionToken + " has ended.");
    }
}