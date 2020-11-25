package pe.pucp.dduu.tel306;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

public class InicioSession_Fragment extends Fragment {
EditText usuarioSesion, password ;
Button buttonSesion, butonRegistro;
Regreso regreso;
String user , pwd ;

String stringStatus;
Usuario usuDTO;



    @Override
    public void onAttach(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userFIle", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    public InicioSession_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InicioSession_Fragment newInstance() {
        InicioSession_Fragment fragment = new InicioSession_Fragment();

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
        View view = inflater.inflate(R.layout.fragment_inicio_session_,container,false);
        usuarioSesion = view.findViewById(R.id.editTextUsuarioSesion);
        password= view.findViewById(R.id.editTextPassword);
        butonRegistro = view.findViewById(R.id.buttonRegistro);
        buttonSesion= view.findViewById(R.id.buttonIniciarSesion);

        buttonSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user = usuarioSesion.getText().toString();
                pwd = password.getText().toString();

                postDataLOGIN(user, pwd);





                //Usuario arregloUsuario = usuDTO;
                //Log.d("status",usuDTO.getName()); //este es el puto problema
                //Gson g = new Gson();

                /*
                String usuarioGuardar = g.toJson(usuDTO);
                String fileNameJson = "sesionusuario";
                Log.d("status","Estamos a punto de guardar el JSON.");


                try (FileOutputStream fileOutputStream = getActivity().openFileOutput(fileNameJson, Context.MODE_PRIVATE);
                     FileWriter fileWriter = new FileWriter(fileOutputStream.getFD());){
                    fileWriter.write(usuarioGuardar);
                    Log.d("status1","Se guardó el JSON.");

                }
                catch (IOException e) {
                    e.printStackTrace();

                }*/




            }
        });

        butonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (regreso != null ){
                        regreso.changeFragment();
                    }
            }
        });









        return view;
    }

    public  void  setRegreso (Regreso regreso){

        this.regreso= regreso;

    }




    public void postDataLOGIN(String emailIniciar, String passwordIniciar) {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/users/login";


        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", emailIniciar);
            jsonBody.put("password", passwordIniciar);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    Log.d("VOLLEY", response);
                    Gson g = new Gson();
                    usuDTO = g.fromJson(response,Usuario.class);
                    Log.d("status",usuDTO.getName());

                    String usuarioGuardar = g.toJson(usuDTO);
                    String fileNameJson = "sesionusuario.json";
                    Log.d("status","Estamos a punto de guardar el JSON.");


                    try (FileOutputStream fileOutputStream = getActivity().openFileOutput(fileNameJson, Context.MODE_PRIVATE);
                         FileWriter fileWriter = new FileWriter(fileOutputStream.getFD());){
                        fileWriter.write(usuarioGuardar);
                        Log.d("status1","Se guardó el JSON.");

                    }
                    catch (IOException e) {
                        e.printStackTrace();

                    }



                    //AQUI ENTREGA EL true o false
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        String s = new String(response.data);
                        responseString = String.valueOf(s);


                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }









}