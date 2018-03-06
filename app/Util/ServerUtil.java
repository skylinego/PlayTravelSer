package Util;

import controllers.ServerController;

import java.util.Map;
import play.mvc.*;
import play.mvc.Result;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by dliu15 on 3/2/18.
 */
public class ServerUtil {

    public static Result outputMimeStr(String str, String mimeType) {
        ServerHttpRequest.putObject(Controller.ctx(), ServerHttpContext.RESPONSE_BODY_KEY, str);
        // set content Type to current context
        Controller.ctx().response().setContentType(mimeType);
        return ServerController.ok(str).as(mimeType);
    }

    public static Result toJsonRes(Object obj) {
        return toJsonRes(obj, null);
    }

    public static Result toJsonRes(Object obj, Map<String, Object> extra) {
        return outputJsonStr(toJsonStr(obj, extra));
    }

    public static Result outputJsonStr(String str) {
        return outputMimeStr(str, "application/json");
    }

    // Output related utilities
    public static String toJsonStr(Object obj) {
        return toJsonStr(obj, null);
    }

    public static String toJsonStr(Object obj, Map<String, Object> extra) {
        if (obj != null) {
            JsonNode jsnode = toJsonIgnoreNull(obj);

            if (extra != null && jsnode.isObject()) {
                ObjectNode objNode = (ObjectNode) jsnode;
                for (String key : extra.keySet()) {
                    Object val = extra.get(key);
                    JsonNode valNode = toJsonIgnoreNull(val);
                    objNode.put(key, valNode);
                }
            }
            return Json.stringify(jsnode);
        } else {
            return "";
        }
    }

    /**
     * Convert an object to JsonNode and ignore null value.
     *
     * @param data Value to convert in Json.
     */
    public static JsonNode toJsonIgnoreNull(final Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

            return mapper.valueToTree(data);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
