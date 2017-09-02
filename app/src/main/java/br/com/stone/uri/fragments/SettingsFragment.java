package br.com.stone.uri.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.widget.Toast;

import com.jgabrielfreitas.core.fragment.BaseFragment;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import br.com.stone.uri.R;
import br.com.stone.uri.code.Response;
import br.com.stone.uri.support.SharedPreferencesManager;
import butterknife.Bind;
import butterknife.OnClick;

import static android.content.Intent.ACTION_VIEW;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

@InjectLayout(layout = R.layout.fragment_settings)
public class SettingsFragment extends BaseFragment {
    private static final int CONFIG_RESULT = 1;
    private static final String TAG = "SettingsFragment";

    @Bind(R.id.stoneCodeEditText) TextInputEditText stoneCodeEditText;
    SharedPreferencesManager sharedPreferencesManager;
    String stoneCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesManager = SharedPreferencesManager.newInstance(getContext());
    }

    @Override
    protected void modifyViews() {
        String stoneCode = sharedPreferencesManager.getStoneCode();
        stoneCodeEditText.setText(stoneCode);
    }

    @OnClick(R.id.saveButton)
    public void save() {

        stoneCode = stoneCodeEditText.getText().toString();

        Uri.Builder transactionUri = new Uri.Builder();
        transactionUri.scheme("stone");
        transactionUri.authority("configuration");
        transactionUri.appendQueryParameter("acquirerId", stoneCode);
        transactionUri.appendQueryParameter("scheme", "demoUri");
        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(transactionUri.build());
        startActivityForResult(intent, CONFIG_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "]");
        if (data != null && data.getData() != null) {
            Log.d(TAG, "data = [" + data.getData().toString() + "]");
            Response response = new Response(data.getData());
            makeText(getContext(), response.getReason(), LENGTH_SHORT).show();
            if (response.getResponseCode() == 0) {
                sharedPreferencesManager.setStoneCode(stoneCode);
            }
        } else { makeText(getContext(), "no data content", LENGTH_SHORT).show();}
    }
}