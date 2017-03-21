package com.wowtour.jersey.vo;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wowtour.jersey.comm.HTTPMethod;
import com.wowtour.jersey.comm.Roles;
import com.wowtour.jersey.utils.JerseyUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientSecretCredentail
 *
 * @author Lynch 2014-09-15
 */
public class ClientSecretCredential extends Credential {

    private static JerseyWebTarget CLIENT_TOKEN_TARGET = null;

    public ClientSecretCredential(String clientID, String clientSecret, String role) {
        super(clientID, clientSecret);

        if (role.equals(Roles.USER_ROLE_APPADMIN)) {
            CLIENT_TOKEN_TARGET = EndPoints.TOKEN_APP_TARGET
                    .resolveTemplate("org_name", "hd18701968643").resolveTemplate("app_name",
                            "vcbbs5");
        }
    }

    @Override
    protected GrantType getGrantType() {
        return GrantType.CLIENT_CREDENTIALS;
    }

    @Override
    protected JerseyWebTarget getTokenRequestTarget() {
        return CLIENT_TOKEN_TARGET;
    }

    @Override
    public Token getToken() {

        if (null == token || token.isExpired()) {
            try {
                ObjectNode objectNode = factory.objectNode();
                objectNode.put("grant_type", "client_credentials");
                objectNode.put("client_id", tokenKey1);
                objectNode.put("client_secret", tokenKey2);
                List<NameValuePair> headers = new ArrayList<NameValuePair>();
                headers.add(new BasicNameValuePair("Content-Type", "application/json"));

                ObjectNode tokenRequest = JerseyUtils.sendRequest(getTokenRequestTarget(), objectNode, null,
                        HTTPMethod.METHOD_POST, headers);

                if (null != tokenRequest.get("error")) {
                    return token;
                }

                String accessToken = tokenRequest.get("access_token").asText();

                Long expiredAt = System.currentTimeMillis() + tokenRequest.get("expires_in").asLong() * 1000;

                token = new Token(accessToken, expiredAt);
            } catch (Exception e) {
                throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
            }
        }

        return token;
    }
}
