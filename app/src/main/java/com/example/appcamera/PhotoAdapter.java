package com.example.appcamera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhotoAdapter extends BaseAdapter {
    int layout;
    Context context;
    List<Camera> list;

    public PhotoAdapter(int layout, Context context, List<Camera> list) {
        this.layout = layout;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        ImageView img = view.findViewById(R.id.imgcustom);
        TextView text1 = view.findViewById(R.id.textName);
        TextView text2 = view.findViewById(R.id.textpopulation);
        Camera get = list.get(i);
        img.setImageURI(get.getImageSoucre());
        text2.setText(get.getContent());
        text1.setText(get.getDescription());
        return view;
    }
}
