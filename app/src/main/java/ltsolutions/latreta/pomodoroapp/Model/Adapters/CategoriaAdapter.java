package ltsolutions.latreta.pomodoroapp.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.R;

/**
 * Created by LaTreTz on 09/05/2016.
 */
public class CategoriaAdapter extends ArrayAdapter<Categoria>{
    private Context context;
    private ArrayList<Categoria> listaCategorias;


    public CategoriaAdapter(Context context, ArrayList<Categoria> categorias){
        super(context,0,categorias);
        this.listaCategorias = categorias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Categoria categoria = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listcategoria,parent,false);
        }
        TextView nome = (TextView)convertView.findViewById(R.id.nomeCategoria);
        nome.setText(categoria.getNome());
        return convertView;
    }
}
