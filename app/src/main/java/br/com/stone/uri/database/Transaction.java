package br.com.stone.uri.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import br.com.stone.uri.code.Response;

/**
 * Created by JGabrielFreitas on 06/03/17.
 */

@Table(name = "Transactions")
public class Transaction extends Model {

    @Column(name = "paymentId") private String paymentId;
    @Column(name = "acquirerTid") private String acquirerTid;
    @Column(name = "responseCode") private Integer responseCode;
    @Column(name = "responseReason") private String responseReason;
    @Column(name = "acquirerAuthorizationCode") private String acquirerAuthorizationCode;

    public Transaction() {
    }

    public Transaction(Response response) {
        this.paymentId = response.getPaymentId();
        this.responseReason = response.getReason();
        this.acquirerTid = response.getAcquirerTid();
        this.responseCode = response.getResponseCode();
        this.acquirerAuthorizationCode = response.getAcquirerAuthorizationCode();
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseReason() {
        return responseReason;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getAcquirerTid() {
        return acquirerTid;
    }

    public String getAcquirerAuthorizationCode() {
        return acquirerAuthorizationCode;
    }

    public void updateStatus(int responseCode) {
        this.responseCode = responseCode;
        save();
    }

}
