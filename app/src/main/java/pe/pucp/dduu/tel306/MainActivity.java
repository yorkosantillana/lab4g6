package pe.pucp.dduu.tel306;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements  Regreso {
    Fragment fragmento ;

    private Button getApiBtn, postApiBtnNew,postApiBtnLogin;
    private TextView resultTextView;
    private EditText editTextUsuarioRegistro;
    private EditText editTextMailRegistro;
    private EditText editTextPasswordRegistro;
    private EditText editTextUsuarioSesion;
    private EditText editTextPassword;

    UsuarioDTO usuDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            String[] arregloArchivos = fileList();

            if(arregloArchivos.length == 0){
                MostrarFragmento();
            }else{
                
            }

            for(String archivo : arregloArchivos){
                Log.d("infoapp",archivo);

            }








        /*
        //###########################
        //PARA CONSULTAR EL API

        editTextUsuarioRegistro = findViewById(R.id.editTextUsuarioRegistro);
        editTextMailRegistro = findViewById(R.id.editTextMailRegistro);
        editTextPasswordRegistro = findViewById(R.id.editTextPasswordRegistro);
        postApiBtnNew = (Button)findViewById(R.id.buttonRegistrarUsuario);



        editTextUsuarioSesion = findViewById(R.id.editTextUsuarioSesion);
        editTextPassword = findViewById(R.id.editTextPassword);
        postApiBtnLogin = (Button)findViewById(R.id.buttonIniciarSesion);


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        postApiBtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextUsuarioRegistro.getText().toString();

                String email = editTextMailRegistro.getText().toString();

                String password = editTextPasswordRegistro.getText().toString();


                postDataNEW(name,email,password);
            }
        });
        postApiBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailIniciar = editTextUsuarioSesion.getText().toString();

                String passwordIniciar = editTextPassword.getText().toString();


                postDataLOGIN(emailIniciar, passwordIniciar);

                Usuario[] arregloUsuario = usuDTO.getUsuario();
                Gson g = new Gson();
                String usuarioGuardar = g.toJson(arregloUsuario);
                String fileNameJson = "sesionusuario";


                try {
                    FileOutputStream fileOutputStream = MainActivity.this.openFileOutput(fileNameJson, Context.MODE_PRIVATE);
                    FileWriter fileWriter = new FileWriter(fileOutputStream.getFD());
                    fileWriter.write(usuarioGuardar);

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });

        //###########################


         */














    }








    public void MostrarFragmento (){

       // InicioSession_Fragment inicioSession_fragment = InicioSession_Fragment.newInstance();
    InicioSession_Fragment fragment = new InicioSession_Fragment();
    fragment.setRegreso(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentInicioRegistroContainer,fragment);
        fragmentTransaction.commit();


    }


    public void cambiarFragmento(){
       // RegistroUsuario_Fragment   registroUsuario_fragment = RegistroUsuario_Fragment.newInstance();
        RegistroUsuario_Fragment registroUsuario_fragment = new RegistroUsuario_Fragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentInicioRegistroContainer, registroUsuario_fragment);
        fragmentTransaction.commit();


    }


    @Override
    public void changeFragment() {
        cambiarFragmento();
    }











    public void postDataLOGIN(String emailIniciar, String passwordIniciar) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("email", emailIniciar);
            object.put("password", passwordIniciar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = "http://34.236.191.118:3000/api/v1/users/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String jsonString = response.toString();
                        Gson g = new Gson();
                        usuDTO = g.fromJson(jsonString, UsuarioDTO.class);

                        //resultTextView.setText(usu.getName());


                        /*
                        for (Answer ans: usu.getAnswers()){

                            resultTextView.setText(ans.getAnswerText());
                        }
                        */



                        //resultTextView.setText(response.toString());

                        //resultTextView.setText(usu.getPassword());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("ERROR");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }




    public void postDataNEW(String name,String email, String password) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "http://34.236.191.118:3000/api/v1/users/new";

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", name);
            jsonBody.put("email", email);
            jsonBody.put("password", password);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    resultTextView.setText(response); //AQUI ENTREGA EL true o false
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





    public void eliminarArchivoLogin (){
        deleteFile("sesionusuario.json");

    }













}
