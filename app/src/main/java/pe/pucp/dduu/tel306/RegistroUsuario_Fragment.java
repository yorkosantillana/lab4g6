package pe.pucp.dduu.tel306;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistroUsuario_Fragment extends Fragment {
    EditText usuarioRegister, password, email ;
    Button  butonRegistro ,buttonRegresarInicioSession;
    String user , pwd ,ecorreo ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
        buttonRegresarInicioSession = view.findViewById(R.id.buttonIniciarSesionRegistro);

        butonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        user = usuarioRegister.getText().toString();
                        ecorreo  = email.getText().toString();
                        pwd  =  password.getText().toString();

                        editor.putString("user", user);
                        editor.putString("ecorreo",ecorreo);
                        editor.putString("pwd", pwd);
                        editor.apply();
                Toast.makeText(getContext(), "registrado", Toast.LENGTH_SHORT).show();
                        // Se resgitra

            }
        });

        buttonRegresarInicioSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InicioSession_Fragment inicioSession_fragment = InicioSession_Fragment.newInstance();
                FragmentManager supportFragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentInicioRegistroContainer,inicioSession_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}