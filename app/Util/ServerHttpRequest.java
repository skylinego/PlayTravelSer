package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.libs.Json;
import play.mvc.Http;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Http.Context;
import play.mvc.Http.Request;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by dliu15 on 3/1/18.
 */
public class ServerHttpRequest {

    public static Map<String, String> getParamSimMap() {
        return getParamSimMap(Controller.ctx());
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getParamSimMap(Http.Context ctx) {
        if (ctx.args.get(ServerHttpContext.PARAM_SIMMAP_KEY) == null) {
            Map<String, String> simMap = getParamSimMapImpl(ctx);
            ctx.args.put(ServerHttpContext.PARAM_SIMMAP_KEY, simMap);
        }

        return (Map<String, String>) ctx.args.get(ServerHttpContext.PARAM_SIMMAP_KEY);
    }

    public static Map<String, String> getParamSimMapImpl(Context ctx) {
        Request request = ctx.request();
        // combine parameters passed in from query string & post body
        List<Map<String, String[]> > maps = new ArrayList<Map<String, String[]> >();
        maps.add(request.queryString());
        maps.add(getFormParamMap(ctx));
        if(multipartForm(ctx.request())){
            maps.add(ctx.request().body().asMultipartFormData().asFormUrlEncoded());
        }
        Map<String, String> resMap = new HashMap<String, String>();
        for (Map<String, String[]> m : maps) {
            if (m == null)
                continue;
            for (String key : m.keySet()) {
                String[] val = m.get(key);
                if (val != null && val.length > 0)
                    resMap.put(key, val[0]);
            }
        }
        return resMap;
    }

    public static boolean multipartForm(Request request) {
        String contentType = request.getHeader("Content-Type");
        return contentType != null && contentType.toLowerCase().startsWith("multipart/form-data");
    }

    public static Map<String, String[]> getFormParamMap(Context ctx) {
        Request request = ctx.request();
        Map<String, String[]> map = null;
        if (multipartForm(request)) {
            map = request.body().asMultipartFormData().asFormUrlEncoded();
        } else {
            JsonNode bodyJson = getRequestJson(ctx);

            if (bodyJson == null)
                map = request.body().asFormUrlEncoded();
        }
        return map;
    }


    public static JsonNode getRequestJson(Http.Context ctx) {

        if (!ctx.args.containsKey(ServerHttpContext.REQUEST_JSON)) {
            String contentType = ctx.request().getHeader("Content-Type");
            if("POST".equalsIgnoreCase(ctx.request().method()) || "PUT".equalsIgnoreCase(ctx.request().method())){
                if(contentType!=null && contentType.toUpperCase().indexOf("JSON")>=0){
                    ctx.args.put(ServerHttpContext.REQUEST_JSON, ctx.request().body().asJson());
                }
                else{
                    RequestBody body = Controller.ctx().request().body();
                    String content = null;
                    try{
                        content = new String(body.asRaw().asBytes());
                    } catch (Exception e) {
                        // don't process
                    }
                    if(content != null && !content.trim().isEmpty()){
                        try{
                            ctx.args.put(ServerHttpContext.REQUEST_JSON, Json.parse(content));
                        }catch(Exception ex){
                        }
                    }
                }
            }
        }
        return (JsonNode) ctx.args.get(ServerHttpContext.REQUEST_JSON);
    }

    public static void putObject(Http.Context ctx, String key, Object obj) {
        ctx.args.put(key, obj);
    }
}
