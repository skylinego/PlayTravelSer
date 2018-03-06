package controllers;

import play.mvc.*;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.Status;
import play.mvc.Action;
import play.mvc.Controller;
import play.Logger;
import play.libs.F;
/**
 * Created by dliu15 on 3/2/18.
 */

public class ServerController extends Controller {

    public static class ServerAction extends Action.Simple {

        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            startRequest(ctx);

            return delegate.call(ctx);
        }
    }

    public static void startRequest(Http.Context ctx) {
        Logger.info(ctx.request().method() + "" + ctx.request().uri());

    }

    public static Result finishRequest(Http.Context ctx, Result result) {

        final Http.Context _ctx = ctx;

        /*try {
            //PerfLogger.stopAction(_ctx, result);
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }*/
        return result;

    }

    public static Status ok(String content) {
        // store response for cache
        ctx().args.put("response", content);

        return play.mvc.Results.ok(content);
    }

}
