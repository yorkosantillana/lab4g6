package pe.pucp.dduu.tel306;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  Regreso {
Fragment fragmento ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        MostrarFragmento();
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
}