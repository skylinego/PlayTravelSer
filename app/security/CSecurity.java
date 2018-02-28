package security;

import Util.EncryptUtil;
import Util.TimeUtil;
import models.Client;
import models.POI;
import play.data.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import play.libs.Json;
import play.mvc.*;
import play.libs.F;

import java.sql.Timestamp;

import static play.data.Form.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;

public class CSecurity extends Controller {

    @Transactional
    public F.Promise<Result> authenticateClient() {
        JsonNode json = request().body().asJson();
        String clientId = json.findPath("clientId").textValue();
        String clientSecret = json.findPath("clientSecret").textValue();

        Client client = Client.findById(Long.valueOf(clientId));

        if (client.getSecret().compareTo(clientSecret) !=0) {

            ObjectNode result = Json.newObject();
            result.setAll(ImmutableMap.of(
                    "clientId", result.textNode(clientId),
                    "Error", result.textNode("Invalid Secret")));

            return F.Promise.pure(ok(result));
        }
        Timestamp exp = client.getExpiration();

        String token;
        if (TimeUtil.isExpired(exp)) {
             token = EncryptUtil.encrypt(clientSecret);
              client.setExpiration(new Timestamp(System.currentTimeMillis() + 10*86400*1000));
              client.setToken(token);
              //client.save(); //no need.
        } else {
            token = client.getToken();
        }

        ObjectNode result = Json.newObject();
        result.setAll(ImmutableMap.of(
                "accessToken", result.textNode(token),
                "clientId", result.textNode(clientId),
                "expiration", result.numberNode(client.getExpiration().getTime())));
        return F.Promise.pure(ok(result));
    }

    @Transactional
    public F.Promise<Result> authenticateUser() {
        JsonNode json = request().body().asJson();
        String username = json.findPath("username").textValue();
        String password = json.findPath("password").textValue();

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Request clientAuthenticationRequest =
                ((OAuth2Authentication) authentication).getOAuth2Request();
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put("username", username);
        requestParameters.put("password", password);
        TokenRequest tokenRequest = new TokenRequest(requestParameters,
                clientAuthenticationRequest.getClientId(), clientAuthenticationRequest.getScope(),
                "password");
        OAuth2AccessToken token = tokenGranter.grant("password", tokenRequest);
        */
        ObjectNode result = Json.newObject();
        result.setAll(ImmutableMap.of(
                "accessToken", result.textNode(""),
                "username", result.textNode(username),
                "expiration", result.numberNode(10),
                "refreshToken", result.textNode("")));
        return F.Promise.pure(ok(result));
    }

  /*
    public Promise<Result> refreshUserAccessToken() {
        JsonNode body = request().body().asJson();
        String refreshToken = body.findPath("refreshToken").textValue();

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Request clientAuthenticationRequest =
                ((OAuth2Authentication) authentication).getOAuth2Request();
        TokenRequest tokenRequest =
                new TokenRequest(Collections.emptyMap(), clientAuthenticationRequest.getClientId(),
                        clientAuthenticationRequest.getScope(), "refresh");
        OAuth2AccessToken token = tokenServices.refreshAccessToken(refreshToken, tokenRequest);

        ObjectNode result = Json.newObject();
        result.setAll(ImmutableMap.of(
                "accessToken", result.textNode(token.getValue()),
                "expiration", result.numberNode(token.getExpiration().getTime()),
                "refreshToken", result.textNode(token.getRefreshToken().getValue())));
        return Promise.pure(ok(result));
    }
  */
}
