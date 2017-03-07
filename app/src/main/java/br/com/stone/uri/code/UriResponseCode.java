package br.com.stone.uri.code;

/**
 * Created by JGabrielFreitas on 06/03/17.
 */

public class UriResponseCode {

  // everything OK
  // request ok and response ok,
  // but the transaction can be denied or approved
  public static final int OK = 0;

  // cancel button pressed
  public static final int CANCELED = 1;

  // back button pressed
  public static final int ABORTED = 3;

}
