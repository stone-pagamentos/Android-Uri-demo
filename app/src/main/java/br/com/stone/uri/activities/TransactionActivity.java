package br.com.stone.uri.activities;

import android.content.Intent;
import android.net.Uri;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import br.com.stone.uri.R;
import butterknife.Bind;
import butterknife.OnClick;
import com.jgabrielfreitas.core.activity.BaseActivity;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;
import static android.content.Intent.ACTION_VIEW;
import static br.com.stone.uri.R.array.installment;
import static java.util.UUID.randomUUID;

@InjectLayout(layout = R.layout.activity_transaction)
public class TransactionActivity extends BaseActivity {

    @Bind(R.id.editTextValue)         EditText    editTextValue;
    @Bind(R.id.radioDebit)            RadioButton debitRadioButton;
    @Bind(R.id.spinnerInstallments)   Spinner     installmentSpinner;
    int TRANSACTION_RESULT = 10;

    @Override protected void onStart() {
        super.onStart();

        configureSpinner();
    }

    @OnClick(R.id.buttonSendTransaction)
    public void sendTransaction() {

        Uri.Builder transactionUri = new Uri.Builder();
        transactionUri.scheme("stone");
        transactionUri.authority("pay");
        transactionUri.appendQueryParameter("transactionId", randomUUID().toString());
        transactionUri.appendQueryParameter("paymentType", (debitRadioButton.isChecked()) ? "DEBIT" : "CREDIT");
        transactionUri.appendQueryParameter("amount", editTextValue.getText().toString());
        transactionUri.appendQueryParameter("installments", String.valueOf(installmentSpinner.getSelectedItemPosition() + 1));

        Intent intent = new Intent(ACTION_VIEW);
        intent.setDataAndType(transactionUri.build(), "text/plain");
        startActivityForResult(intent, TRANSACTION_RESULT);
    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, installment, simple_spinner_item);
        adapter.setDropDownViewResource(simple_spinner_dropdown_item);
        installmentSpinner.setAdapter(adapter);
    }
}
