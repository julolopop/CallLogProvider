package com.example.usuario.calllogprovider;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity implements CallLogContract.View{


    CallLogContract.Presenter presenter ;
    CallLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CallLogPresenter(this);

        adapter = new CallLogAdapter(this);
        this.setListAdapter(adapter);
        presenter.getCallLogs();

    }

    @Override
    public void setCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public Context getContext() {
        Context context = this;
        return context;
    }
}
