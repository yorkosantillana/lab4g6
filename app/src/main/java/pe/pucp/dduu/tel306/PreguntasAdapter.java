package pe.pucp.dduu.tel306;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.preguntasHolder>{

    private  PreguntasDto[] preguntas;
    private Context contexto;

    public PreguntasAdapter(PreguntasDto[] preguntas, Context contexto) {
        this.preguntas = preguntas;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public preguntasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View itemView = LayoutInflater.from(contexto).inflate(R.layout.item_rv,parent, false);  // para crear elementos de la vista dentro del recycleView
preguntasHolder preguntasHolder = new preguntasHolder(itemView);
    return  preguntasHolder; // al final lo retorna
    }

    @Override
    public void onBindViewHolder(preguntasHolder holder, int position) {
        //String
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static  class preguntasHolder extends  RecyclerView.ViewHolder{

        public TextView textView;
        public  preguntasHolder(View itemview){
            super(itemview);
            this.textView = itemview.findViewById(R.id.textViewPreguntas);

        }
    }
}
