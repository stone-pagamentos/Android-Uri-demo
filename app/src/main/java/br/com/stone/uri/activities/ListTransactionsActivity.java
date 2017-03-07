package br.com.stone.uri.activities;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.stone.uri.R;
import br.com.stone.uri.database.Transaction;
import butterknife.Bind;
import butterknife.OnItemClick;
import com.activeandroid.query.Select;
import com.jgabrielfreitas.core.activity.BaseActivity;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;

@InjectLayout(layout = R.layout.activity_list_transcations) public class ListTransactionsActivity extends BaseActivity {

  @Bind(R.id.transactionsListView) ListView transactionsListView;

  @Override protected void onResume() {
    super.onResume();

    List<Transaction> transactions = new Select().from(Transaction.class).execute();
    ArrayList<String> transactionsAsString = new ArrayList<>();

    for (Transaction transaction : transactions)
      transactionsAsString.add(format("ATK %s\nAprovada? %s", transaction.getAcquirerTransactionKey(), valueOf(transaction.isWasApproved())));

    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, transactionsAsString);
    transactionsListView.setAdapter(adapter);
  }

  @OnItemClick(R.id.transactionsListView) public void onTrasanctionCLicked() {
    Toast.makeText(this, "blah", Toast.LENGTH_SHORT).show();
  }
}
