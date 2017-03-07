package br.com.stone.uri.database;

import br.com.stone.uri.code.Response;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by JGabrielFreitas on 06/03/17.
 */

@Table(name = "Transactions") public class Transaction extends Model {

  @Column(name = "wasApproved")            private boolean wasApproved;
  @Column(name = "responseCode")           private int     responseCode;
  @Column(name = "responseReason")         private String  responseReason;
  @Column(name = "acquirerTransactionKey") private String  acquirerTransactionKey;

  public Transaction() {
  }

  public Transaction(Response response) {
    this.wasApproved = response.wasApproved();
    this.responseCode = response.getResponseCode();
    this.responseReason = response.getResponseReason();
    this.acquirerTransactionKey = response.getAcquirerTransactionKey();
  }

  public boolean isWasApproved() {
    return wasApproved;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public String getResponseReason() {
    return responseReason;
  }

  public String getAcquirerTransactionKey() {
    return acquirerTransactionKey;
  }
}
