package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.POI;
import play.data.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import play.libs.Json;
import play.mvc.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import static play.data.Form.*;


public class CPOI extends Controller {

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

        if (queryType ==0) {
            POI poi = POI.findById(id);

            List<POI> poiList = new ArrayList<>();
            poiList.add(poi);
            return ok(Json.toJson(poiList));
        } if (queryType ==1) {
            java.util.Collection<POI> poiList = new ArrayList<>();

            poiList = POI.findByCity(queryInfo);

            //poiList.add(poi);
            return ok(Json.toJson(poiList));
        } else {
            return ok("Type is: " + queryType);
        }

    }

}
