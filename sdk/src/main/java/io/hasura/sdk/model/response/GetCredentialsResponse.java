package io.hasura.sdk.model.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class GetCredentialsResponse {
    @SerializedName("hasura_id")
    int hasuraId;

    @SerializedName("hasura_roles")
    String[] hasuraRoles;

    @SerializedName("info")
    JsonObject info;

    @SerializedName("auth_token")
    String auth_token;

    public int getHasuraId() {
        return hasuraId;
    }

    public String[] getHasuraRoles() {
        return hasuraRoles;
    }

    public JsonObject getInfo() {
        return info;
    }

    public String getSessionId() {
        return auth_token;
    }
}
