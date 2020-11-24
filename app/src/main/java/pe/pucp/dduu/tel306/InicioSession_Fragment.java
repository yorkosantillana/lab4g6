package pe.pucp.dduu.tel306;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InicioSession_Fragment extends Fragment {
EditText usuarioSesion, password ;
Button buttonSesion, butonRegistro;
Regreso regreso;
String user , pwd ;



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
}