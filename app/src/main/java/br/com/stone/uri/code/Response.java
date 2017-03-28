package br.com.stone.uri.code;

import android.net.Uri;

import static java.lang.Boolean.parseBoolean;

/**
 * Created by JGabrielFreitas on 06/03/17.
 */

public class Response {

    private Uri responseUri;

    public Response(Uri responseUri) {
        this.responseUri = responseUri;
    }

    public boolean wasApproved() {
        String wasApproved = responseUri.getQueryParameter("WasApproved");
        return wasApproved != null && parseBoolean(wasApproved);
    }

    public String getAcquirerTransactionKey() {
        return responseUri.getQueryParameter("paymentId");
    }

    public String getReason() {
        return responseUri.getQueryParameter("reason");
    }

    public Integer getResponseCode() {
        String responseCode = responseUri.getQueryParameter("ResponseCode");
        return (responseCode != null) ? Integer.parseInt(responseCode) : null;
    }


}
