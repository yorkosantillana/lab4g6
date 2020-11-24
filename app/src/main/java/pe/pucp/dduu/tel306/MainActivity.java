package pe.pucp.dduu.tel306;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  Regreso {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MostrarFragmento();
    }

    public void MostrarFragmento (){

        InicioSession_Fragment inicioSession_fragment = InicioSession_Fragment.newInstance();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentInicioRegistroContainer,inicioSession_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


    @Override
    public void changeFragment() {

    }
}