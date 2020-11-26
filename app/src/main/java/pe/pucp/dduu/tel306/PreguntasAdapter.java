package pe.pucp.dduu.tel306;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreguntasAdapter extends RecyclerView.Adapter <PreguntasAdapter.preguntasHolder>{

    private  Pregunta[] preguntas;
    private Context contexto;
    private  int preguntaId;

    public PreguntasAdapter(Pregunta[] preguntas, Context contexto) {
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
        Pregunta pregunta = preguntas[position];
        holder.pregunta = pregunta;
        holder.contextVh= contexto;
        holder.preguntaIdVh= preguntaId;
        holder.llenarVIewHolder();

    }

    @Override
    public int getItemCount() {
        return preguntas.length;
    }

    public static  class preguntasHolder extends  RecyclerView.ViewHolder{

        public TextView textView;
        public Pregunta pregunta;
        public Context contextVh;
        public int preguntaIdVh;

        public  preguntasHolder(View itemview){
            super(itemview);
            this.textView = itemview.findViewById(R.id.textViewPreguntas);

        }
        public  void  llenarVIewHolder(){
            String data= pregunta.getQuestionText();
            textView.setText(data);
            if (pregunta.getId()==preguntaIdVh){
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            } else {
                textView.setTypeface(null,  Typeface.NORMAL);
            }
        }
    }
}
