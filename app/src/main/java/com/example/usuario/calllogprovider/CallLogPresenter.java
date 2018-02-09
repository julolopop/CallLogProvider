package com.example.usuario.calllogprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogPresenter implements LoaderManager.LoaderCallbacks<Cursor>, CallLogContract.Presenter {


    CallLogContract.View view;
    public static final int CALLLOG = 0;

    public CallLogPresenter(CallLogContract.View view) {
        this.view = view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = null;
        switch (id) {
            case CALLLOG:
                Uri uri =Uri.parse(CallLog.CONTENT_URI + "/calls");

                String strOrder = CallLog.Calls.DATE + " DESC";
                String[] projection = {CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE,CallLog.Calls._ID};

                cursorLoader = new CursorLoader(view.getContext(), uri, projection, null, null, strOrder);
                break;

        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursor(null);
    }

    @Override
    public void getCallLogs() {
        ((Activity)view.getContext()).getLoaderManager().restartLoader(CALLLOG, null, this);
    }
}
