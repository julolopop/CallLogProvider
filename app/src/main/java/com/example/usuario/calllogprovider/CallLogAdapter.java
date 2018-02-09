package com.example.usuario.calllogprovider;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogAdapter extends CursorAdapter{




    public CallLogAdapter(Context context) {
        super(context, null, FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_call,parent,false);

        CallLogHolder holder = new CallLogHolder();

        holder.txv_nombre = view.findViewById(R.id.textView);
        holder.txv_date = view.findViewById(R.id.textView2);
        holder.txv_duracion = view.findViewById(R.id.textView3);
        holder.txv_type = view.findViewById(R.id.textView4);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(cursor.getLong(1));

        CallLogHolder holder = (CallLogHolder) view.getTag();
        holder.txv_nombre.setText(cursor.getString(0));
        holder.txv_date.setText(a.get(Calendar.DAY_OF_MONTH)+"/"+a.get(Calendar.MONTH));
        holder.txv_duracion.setText(cursor.getString(2));
        holder.txv_type.setText(cursor.getString(3));

    }

    public class  CallLogHolder{

        private TextView txv_nombre;
        private TextView txv_date;
        private TextView txv_duracion;
        private TextView txv_type;

    }

}
