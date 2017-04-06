package br.com.stone.uri.activities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.jgabrielfreitas.core.fragment.BaseFragment;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import br.com.stone.uri.R;
import br.com.stone.uri.code.Response;
import br.com.stone.uri.database.Transaction;
import br.com.stone.uri.support.SharedPreferencesManager;
import butterknife.Bind;
import butterknife.OnClick;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;
import static android.content.Intent.ACTION_VIEW;
import static br.com.stone.uri.R.array.installment;
import static java.lang.String.valueOf;
import static java.util.UUID.randomUUID;

@InjectLayout(layout = R.layout.fragment_transaction)
public class TransactionFragment extends BaseFragment {

    private static final String TAG = "TransactionFragment";

    @Bind(R.id.editTextValue)
    EditText editTextValue;
    @Bind(R.id.radioDebit)
    RadioButton debitRadioButton;
    @Bind(R.id.interestSwitch)
    Switch interestSwitch;
    @Bind(R.id.spinnerInstallments)
    Spinner installmentSpinner;

    int TRANSACTION_RESULT = 10;

    @Override
    protected void modifyViews() {
        super.modifyViews();
        configureSpinner();
    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), installment, simple_spinner_item);
        adapter.setDropDownViewResource(simple_spinner_dropdown_item);
        installmentSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.buttonSendTransaction)
    public void sendTransaction() {
        Uri.Builder transactionUri = new Uri.Builder()
                .scheme("stone")
                .authority("payment")
                .appendQueryParameter("acquirerId", SharedPreferencesManager.newInstance(getContext()).getStoneCode())
                .appendQueryParameter("taxes", Boolean.toString(interestSwitch.isChecked()))
                .appendQueryParameter("transactionId", randomUUID().toString())
                .appendQueryParameter("paymentId", randomUUID().toString())
                .appendQueryParameter("paymentType", debitRadioButton.isChecked() ? "DEBIT" : "CREDIT")
                .appendQueryParameter("amount", editTextValue.getText().toString())
                .appendQueryParameter("scheme", "demoUri")
                .appendQueryParameter("installments", valueOf(installmentSpinner.getSelectedItemPosition() + 1))
                .appendQueryParameter("autoConfirm", "true"); // true = automatically | false = user needs to confirm

        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(transactionUri.build());
        startActivityForResult(intent, TRANSACTION_RESULT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            Log.d(TAG, data.toUri(0));
            Response response = new Response(data.getData());
            Toast.makeText(getContext(), response.getReason(), Toast.LENGTH_SHORT).show();
            new Transaction(response).save();
        }
    }
}
