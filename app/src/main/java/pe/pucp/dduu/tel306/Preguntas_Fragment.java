package pe.pucp.dduu.tel306;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Preguntas_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Preguntas_Fragment extends Fragment {

    Button buttonCerrarSesion;
    RecyclerView recyclerView;
    ListView listView;
    String questionText, questionDate;
    int id;
    PreguntasAdapter preguntasAdapter;
    //PreguntaDTO pregDTO;

    public Preguntas_Fragment() {
        // Required empty public constructor

    }


    public static Preguntas_Fragment newInstance() {
        Preguntas_Fragment fragment = new Preguntas_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preguntas_, container, false);

        buttonCerrarSesion = view.findViewById(R.id.buttonCerrarSesion);
        recyclerView = view.findViewById(R.id.recyclerView);
        listView = view.findViewById(R.id.listViewListaPreguntas);

            buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).eliminarArchivoLogin();

                    InicioSession_Fragment inicioSession_fragment = InicioSession_Fragment.newInstance();
                    FragmentManager supportFragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentInicioRegistroContainer, inicioSession_fragment);
                    fragmentTransaction.commit();

                }
            });


            getPreguntas();

        return view;
    }

    public void getPreguntas() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/questions";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null)
                        {
                            try {
                                response=new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        Log.d("preguntas", response);
                        Gson g = new Gson();
                        Pregunta[] arregloPreg = g.fromJson(response, Pregunta[].class);
                        List<Pregunta> listaPreg = Arrays.asList(arregloPreg);

                        Log.d("preguntas", arregloPreg[0].getQuestionText());

                        PreguntasAdapter preguntasAdapter = new PreguntasAdapter(arregloPreg,getActivity());

                        recyclerView.setAdapter(preguntasAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                        /*
                        List<String> listPreguntas = null;
                        for (Pregunta p : arregloPreg){
                            listPreguntas.add(p.getQuestionText());
                        }

                        ArrayAdapter<String>fileList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

                        listView.setAdapter(fileList);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Log.d("pregunta", String.valueOf(position));
                            }
                        });*/


                        //resultTextView.setText("Response is: "+ response);
                        //resultTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("preguntas", "ERROR");
                //resultTextView.setText("That didn't work!");
                //Aquí debería ir set Preguntas
            }
        });

        requestQueue.add(stringRequest);


    }



}