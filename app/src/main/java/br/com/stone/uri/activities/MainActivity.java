package br.com.stone.uri.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.jgabrielfreitas.core.activity.BaseActivity;
import com.jgabrielfreitas.core.fragment.BaseFragment;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import br.com.stone.uri.R;
import br.com.stone.uri.fragments.HistoryFragment;
import br.com.stone.uri.fragments.SettingsFragment;
import br.com.stone.uri.fragments.TransactionFragment;

@InjectLayout(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private BaseFragment fragment = new SettingsFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;
            case R.id.navigation_transaction:
                fragment = new TransactionFragment();
                break;
            case R.id.navigation_history:
                fragment = new HistoryFragment();
                break;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
        return true;
    }
}
