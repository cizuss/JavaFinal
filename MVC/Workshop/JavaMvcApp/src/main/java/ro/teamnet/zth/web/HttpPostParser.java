package ro.teamnet.zth.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cizuss94 on 7/17/2016.
 */
public class HttpPostParser {
    public static Map<String, String> getParameterMap(String requestBody) {
        Map<String, String> paramMap = new HashMap<String, String>();
        String[] tokens = requestBody.split("&");
        for (String token: tokens) {
            String[] keyVal = token.split("=");
            paramMap.put(keyVal[0], keyVal[1]);
        }
        return paramMap;
    }
}
