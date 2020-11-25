package pe.pucp.dduu.tel306;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Preguntas_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Preguntas_Fragment extends Fragment {

    Button buttonCerrarSesion;

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
}