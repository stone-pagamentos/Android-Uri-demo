package br.com.stone.uri.adapter;

import android.view.View;
import android.widget.TextView;
import br.com.stone.uri.R;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by JGabrielFreitas on 21/02/17.
 */

@LayoutId(R.layout.option_view_holder_row)
public class OptionsViewHolder extends ItemViewHolder<String> {

  @ViewId(R.id.optionTextView) TextView optionTextView;

  public OptionsViewHolder(View view) {
    super(view);
  }

  public OptionsViewHolder(View view, String item) {
    super(view, item);
  }

  @Override public void onSetValues(String item, PositionInfo positionInfo) {
    optionTextView.setText(item);
  }
}
