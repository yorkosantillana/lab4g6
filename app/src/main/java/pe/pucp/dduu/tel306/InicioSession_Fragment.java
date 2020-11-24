package pe.pucp.dduu.tel306;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InicioSession_Fragment extends Fragment {

    public InicioSession_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InicioSession_Fragment newInstance(String param1, String param2) {
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
        return inflater.inflate(R.layout.fragment_inicio_session_, container, false);
    }
}