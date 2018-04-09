package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.POI;
import play.data.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import play.libs.Json;
import play.mvc.*;
import play.Logger;

import java.util.*;

import Util.*;

import static play.data.Form.*;


public class CPOI extends ServerController {

    //private static final Logger logger = Logger.of(CPOI.class);

    /**
     * This result directly redirect to application home.
     */
    public Result GO_HOME = redirect(
            routes.Application.list(0, "name", "asc", "")
    );

    /*
    0 1,2,3,4 corresponding to id city, state, county, continent
     */
    @Transactional(readOnly=true)
    public Result query(Long id, String queryInfo , Integer queryType) {

        Logger.info("started");

        if (queryType ==0) {
            POI poi = POI.findById(id);

            List<POI> poiList = new ArrayList<>();
            poiList.add(poi);
            return ok(Json.toJson(poiList));
        } if (queryType == 1) {
            java.util.Collection<POI> poiList = new ArrayList<>();

            poiList = POI.findByCity(queryInfo);

            return ok(Json.toJson(poiList));
        } if (queryType == 2) {
            java.util.Collection<POI> poiList = new ArrayList<>();

            poiList = POI.findByState(queryInfo);

            return ok(Json.toJson(poiList));
        } if (queryType == 3) {
            java.util.Collection<POI> poiList = new ArrayList<>();

            try {
                poiList = POI.findByCountry(queryInfo);
                Logger.info("success");
                return ok(Json.toJson(poiList));
            } catch (Exception e) {
                Logger.error(e.getMessage(),e);
                return internalServerError();
            }

        } if (queryType == 4) {
            java.util.Collection<POI> poiList = new ArrayList<>();

            poiList = POI.findByContient(queryInfo);

            return ok(Json.toJson(poiList));
        } else {
            return ok("Type is: " + queryType);
        }

    }

    /*
    Create a POI
     */
    @Transactional
    public Result create() {

        final Map<String, String> params = ServerHttpRequest.getParamSimMap();
        if (params == null) {
            throw new RuntimeException("INVALID_PARAM");
        }

        String name = params.get(Param.NAME);

        POI poi = new POI();

        if (name!= null) {
            poi.setName(name);
        } else {
            //throw new ServerRuntimeException(Constants.MISSING_PARAM, "param", Param.NAME);
            throw new RuntimeException();
        }

        poi.save();

        Map<String, Object> ret = new HashMap<String, Object>();
        //ret.put("id", poi.getId());
        ret.put("name", name);
        return ServerUtil.toJsonRes(ret);

    }

}
