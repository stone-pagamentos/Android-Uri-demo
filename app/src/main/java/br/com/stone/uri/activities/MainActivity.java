package br.com.stone.uri.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import br.com.stone.uri.R;
import br.com.stone.uri.adapter.OptionsViewHolder;
import br.com.stone.uri.listeners.OptionsListener;
import butterknife.Bind;
import com.jgabrielfreitas.core.activity.BaseActivity;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;
import java.util.ArrayList;
import java.util.List;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

@InjectLayout(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements OptionsListener{

  @Bind(R.id.optionsRecyclerView) RecyclerView optionsRecyclerView;
  List<String> options = new ArrayList<>();

  @Override protected void onStart() {
    super.onStart();
    killAfterIntent = false;

    options.add("Nova transação");
    options.add("Lista de transações");

    optionsRecyclerView.setHasFixedSize(true);
    optionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    optionsRecyclerView.setAdapter(new EasyRecyclerAdapter<>(this, OptionsViewHolder.class, options, this));
  }

  @Override public void optionClicked(String clicked) {

    switch (clicked) {

      case "Nova transação":
        doIntent(TransactionActivity.class);
        break;

      case "Lista de transações":
        doIntent(ListTransactionsActivity.class);
        break;

    }

  }
}
