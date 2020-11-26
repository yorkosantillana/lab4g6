package pe.pucp.dduu.tel306;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Base64;

public class TestStorage extends AppCompatActivity {


    private Button getApiBtn, postApiBtnNew,postApiBtnLogin;
    private TextView resultTextView;

    //private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);


        //###########################
        //PARA CONSULTAR EL API
        String name = "";
        String email= "mgsotelo@pucp.pe";
        String password = "1234567890";

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        getApiBtn = (Button) findViewById(R.id.getApiBtn);
        postApiBtnNew = (Button)findViewById(R.id.postApiBtnNew);
        postApiBtnLogin = (Button)findViewById(R.id.postApiBtnLogin);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        getApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionid = "8";
                String userid = "156";


                getConteoStatsPreguntas(questionid);
            }
        });

        postApiBtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataNEW(name,email,password);
            }
        });
        postApiBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataLOGIN(email,password);

            }
        });
        //###########################









    }




    public void postDataLOGIN(String email, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("email", email);
            object.put("password", password);
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
                        Usuario usu = g.fromJson(jsonString, Usuario.class);

                        resultTextView.setText(usu.getName());

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



    public void getQuestions(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            String url = "http://34.236.191.118:3000/api/v1/questions";
            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("infoapp",response.toString());
                    resultTextView.setText("Resposne : " + response.toString());

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public void getQuestions1() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/questions";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        resultTextView.setText("Response is: "+ response);
                        //resultTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("That didn't work!");
            }
        });

        requestQueue.add(stringRequest);


    }
























    public void getQuestionByID(String id) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/questions/"+id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        resultTextView.setText(response);
                        //resultTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("That didn't work!");
            }
        });

        requestQueue.add(stringRequest);


    }






    public void postSubirRespuestaUsuario(String id,String iduser,String idanswer) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "http://34.236.191.118:3000/api/v1/questions/"+id+"/answer";

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("iduser", iduser);
            jsonBody.put("idanswer", idanswer);

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


    public void getSiUsuarioRespondioPregunta(String questionid,String userid) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/answers/detail?questionid="+questionid+"&userid="+userid;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        resultTextView.setText(response);
                        //resultTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("That didn't work!");
            }
        });

        requestQueue.add(stringRequest);


    }



    public void getConteoStatsPreguntas(String questionid) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());  //getApplicationContext()
        String URL = "http://34.236.191.118:3000/api/v1/answers/stats?questionid="+questionid;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        resultTextView.setText(response);
                        //resultTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("That didn't work!");
            }
        });

        requestQueue.add(stringRequest);


    }





















}