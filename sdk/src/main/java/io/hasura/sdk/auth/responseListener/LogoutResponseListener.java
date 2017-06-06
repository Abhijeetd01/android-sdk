package io.hasura.sdk.auth.responseListener;

import io.hasura.sdk.core.HasuraException;

/**
 * Created by jaison on 31/05/17.
 */

public interface LogoutResponseListener {
    void onSuccess();
    void onFailure(HasuraException e);
}
