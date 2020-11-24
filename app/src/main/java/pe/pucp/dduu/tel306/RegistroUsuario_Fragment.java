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
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class RegistroUsuario_Fragment extends Fragment {
    EditText usuarioRegister, password, email ;
    Button  butonRegistro;
    String name , contra ,correo ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    String booleanStatus;

    @Override
    public void onAttach(@NonNull Context context) {
         sharedPreferences = context.getSharedPreferences("userFIle", Context.MODE_PRIVATE );
         editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    public RegistroUsuario_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistroUsuario_Fragment newInstance() {
        RegistroUsuario_Fragment fragment = new RegistroUsuario_Fragment();

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
        View view = inflater.inflate(R.layout.fragment_registro_usuario_,container,false);
        usuarioRegister = view.findViewById(R.id.editTextUsuarioRegistro);
        password= view.findViewById(R.id.editTextPasswordRegistro);
        email = view.findViewById(R.id.editTextMailRegistro);
        butonRegistro= view.findViewById(R.id.buttonRegistrarUsuario);

        butonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = usuarioRegister.getText().toString();

                correo = email.getText().toString();

                contra = password.getText().toString();


                postDataNEW(name,correo,contra);

                //Validar el boolean



            }
        });









        return view;
    }





    public void postDataNEW(String name,String email, String password) {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());  //getApplicationContext()
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
                    booleanStatus = response;
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