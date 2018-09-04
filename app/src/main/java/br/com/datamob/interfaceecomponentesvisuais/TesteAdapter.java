package br.com.datamob.interfaceecomponentesvisuais;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.com.datamob.interfaceecomponentesvisuais.data.Universidade;

public class TesteAdapter extends ArrayAdapter<Universidade>
{
    public TesteAdapter(@NonNull Context context, int resource)
    {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(getItem(position).toString());

        return convertView;
    }
}
