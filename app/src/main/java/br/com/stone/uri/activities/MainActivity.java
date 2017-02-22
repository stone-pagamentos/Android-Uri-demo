package br.com.stone.uri.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import br.com.stone.uri.R;
import br.com.stone.uri.adapter.OptionsViewHolder;
import butterknife.Bind;
import com.jgabrielfreitas.core.activity.BaseActivity;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;
import java.util.ArrayList;
import java.util.List;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

@InjectLayout(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity {

  @Bind(R.id.optionsRecyclerView) RecyclerView optionsRecyclerView;
  final String[] options = new String[]{"Nova transação", "Lista de transações"};

  @Override protected void onStart() {
    super.onStart();

    List<String> strings = new ArrayList<>();
    strings.add("Nova transação");
    strings.add("Lista de transações");

    optionsRecyclerView.setHasFixedSize(true);
    optionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    optionsRecyclerView.setAdapter(new EasyRecyclerAdapter<>(this, OptionsViewHolder.class, strings));
  }
}
