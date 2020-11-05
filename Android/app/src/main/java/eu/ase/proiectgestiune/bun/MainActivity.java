package eu.ase.proiectgestiune.bun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
import eu.ase.proiectgestiune.bun.util.Administrator;
import eu.ase.proiectgestiune.bun.util.AdministratorDao;
import eu.ase.proiectgestiune.bun.util.AdministratorRepository;
import eu.ase.proiectgestiune.bun.util.UserDatabase;

public class MainActivity extends AppCompatActivity {

    public EditText username;
    private EditText parola;
    private Button btn_signIn;
    private CheckBox rememberMe;
    UserDatabase database;
    public static final String PREFS_NAME = "loginPref";
    public static final String PREF_USERNAME = "key_username";
    private static final String PREF_PASSWORD = "key_password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        deleteallAdmin();
        insertAdmin();

    }

    private void  initComponents()
    {
        database = UserDatabase.getInstance(this).getInstance(getApplicationContext());
        username= (EditText) findViewById(R.id.log_name);
        parola=(EditText)findViewById(R.id.log_pass);
        btn_signIn=(Button) findViewById(R.id.btn_signIn);
        rememberMe=(CheckBox)findViewById(R.id.cb_rememberMe) ;

        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
         final SharedPreferences.Editor editor = pref.edit();

        if(pref.getString(PREF_USERNAME, null)!=null) {
            username.setText(pref.getString(PREF_USERNAME, null));
        }
        if(pref.getString(PREF_PASSWORD, null)!=null) {
            parola.setText(pref.getString(PREF_PASSWORD, null));
        }
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validate(username.getText().toString(), parola.getText().toString());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(rememberMe.isChecked())
                {
                    editor.putString(PREF_USERNAME, username.getText().toString());
                    editor.putString(PREF_PASSWORD, parola.getText().toString());
                    editor.commit();
                }
                else
                {   editor.clear();
                    editor.commit();
               }

            }
        });

    }

    private void validate(String username1, String password) throws ExecutionException, InterruptedException {
        if(username1.equals(getUsername("Danalache")) && password.equals(getParola("Danalache"))) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        else if (username1.equals(getUsername("Danalache")) && !password.equals(getParola("Danalache")))
        {
            parola.setError(getString(R.string.wrong_password));
        }
        else {
            username.setError(getString(R.string.wrong_username));
        }

    }

    public void insertAdmin(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AdministratorDao administratorDao= UserDatabase.getInstance(getApplicationContext()).getAdministratorDao();
                administratorDao.insertAdministrator(new Administrator("Danalache","Madalina","Danalache","1234"));
            }
        });
    }
    public String getUsername (String nume ) throws ExecutionException, InterruptedException {
        return new AdministratorRepository.GetUsername(database.getAdministratorDao()).execute(nume).get();
    }
    public String getParola (String nume ) throws ExecutionException, InterruptedException {
        return new AdministratorRepository.GetParola(database.getAdministratorDao()).execute(nume).get();
    }
    public void deleteallAdmin() {
        if(database.getAdministratorDao()!=null)
            new AdministratorRepository.DeleteAll(database.getAdministratorDao()).execute();

    }

}
