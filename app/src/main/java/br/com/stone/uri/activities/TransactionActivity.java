package br.com.stone.uri.activities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.jgabrielfreitas.core.activity.BaseActivity;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import br.com.stone.uri.R;
import br.com.stone.uri.code.Response;
import br.com.stone.uri.database.Transaction;
import butterknife.Bind;
import butterknife.OnClick;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;
import static android.content.Intent.ACTION_VIEW;
import static br.com.stone.uri.R.array.installment;
import static java.lang.String.valueOf;
import static java.util.UUID.randomUUID;

@InjectLayout(layout = R.layout.activity_transaction)
public class TransactionActivity extends BaseActivity {

    private static final String TAG = "TransactionActivity";

    @Bind(R.id.editTextValue)
    EditText editTextValue;
    @Bind(R.id.radioDebit)
    RadioButton debitRadioButton;
    @Bind(R.id.spinnerInstallments)
    Spinner installmentSpinner;
    int TRANSACTION_RESULT = 10;

    @Override
    protected void onStart() {
        super.onStart();
        configureSpinner();
    }

    @OnClick(R.id.buttonSendTransaction)
    public void sendTransaction() {
        Uri.Builder transactionUri = new Uri.Builder();
        transactionUri.scheme("stone");
        transactionUri.authority("payment");
        transactionUri.appendQueryParameter("acquirerId", "846873720");
        transactionUri.appendQueryParameter("taxes", "true");
//        transactionUri.appendQueryParameter("acquirerId", "234556789");
        transactionUri.appendQueryParameter("transactionId", randomUUID().toString());
        transactionUri.appendQueryParameter("paymentId", randomUUID().toString());
        transactionUri.appendQueryParameter("paymentType", (debitRadioButton.isChecked()) ? "DEBIT" : "CREDIT");
        transactionUri.appendQueryParameter("amount", editTextValue.getText().toString());
        transactionUri.appendQueryParameter("scheme", "demoUri");
        transactionUri.appendQueryParameter("installments", valueOf(installmentSpinner.getSelectedItemPosition() + 1));
        transactionUri.appendQueryParameter("autoConfirm", "true"); // true = automatically | false = user needs to confirm

        Intent intent = new Intent(ACTION_VIEW);
        intent.setDataAndType(transactionUri.build(), "text/plain");
        startActivityForResult(intent, TRANSACTION_RESULT);
    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, installment, simple_spinner_item);
        adapter.setDropDownViewResource(simple_spinner_dropdown_item);
        installmentSpinner.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            Log.d(TAG, data.toUri(0));
            Response response = new Response(data.getData());
            Toast.makeText(this, response.getReason(), Toast.LENGTH_SHORT).show();
            new Transaction(response).save();
            killThisActivity();
        }
    }
}
