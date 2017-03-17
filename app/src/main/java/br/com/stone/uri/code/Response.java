package br.com.stone.uri.code;

import android.net.Uri;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

/**
 * Created by JGabrielFreitas on 06/03/17.
 */

public class Response {

  private Uri responseUri;

  public Response(Uri responseUri) {
    this.responseUri = responseUri;
  }

  public boolean wasApproved() {
    return parseBoolean(responseUri.getQueryParameter("WasApproved"));
  }

  public String getAcquirerTransactionKey() {
    return responseUri.getQueryParameter("paymentId");
  }

  public String getResponseReason() {
    return responseUri.getQueryParameter("ResponseReason");
  }

  public int getResponseCode() {
    return parseInt(responseUri.getQueryParameter("ResponseCode"));
  }


}
