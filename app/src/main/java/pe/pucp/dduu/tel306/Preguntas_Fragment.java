package pe.pucp.dduu.tel306;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Preguntas_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Preguntas_Fragment extends Fragment {

    Button buttonCerrarSesion;
    String questionText, questionDate;
    int id;

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

        return view;
    }

    public void getPreguntas(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/users/login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("questionText",questionText );
            jsonBody.put("questionDate", questionDate);
            jsonBody.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("pregunta", response);

                Gson gson =new Gson();
                PreguntasDto preguntasDto = gson.fromJson(response, PreguntasDto.class);
               // EditText editText = findby
                // PreguntasDto[] arrayPreguntas = gson.fromJson(response, PreguntasDto[].class);
                // Edit text para answer

            /*   for(PreguntasDto preguntasDto : arrayPreguntas){
                    Log.d("infoWS",preguntasDto.getId()+" / "+ preguntasDto.getQuestionDate()+" / "+ preguntasDto.getQuestionText());

                }
                ArrayAdapter<PreguntasDto> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,arrayPreguntas);
*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}