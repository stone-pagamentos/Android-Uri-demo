package br.com.stone.uri.code;

import android.net.Uri;

/**
 * Created by JGabrielFreitas on 06/03/17.
 */

public class Response {

    private Uri responseUri;

    public Response(Uri responseUri) {
        this.responseUri = responseUri;
    }

    public String getPaymentId() {
        return responseUri.getQueryParameter("paymentId");
    }

    public String getAcquirerTid() {
        return responseUri.getQueryParameter("acquirerTid");
    }

    public String getAcquirerAuthorizationCode() {
        return responseUri.getQueryParameter("acquirerAuthorizationCode");
    }

    public String getReason() {
        return responseUri.getQueryParameter("reason");
    }

    public Integer getResponseCode() {
        String responseCode = responseUri.getQueryParameter("responsecode");
        return (responseCode != null) ? Integer.parseInt(responseCode) : null;
    }


}
