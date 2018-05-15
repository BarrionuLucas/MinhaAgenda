package br.com.barrionuevo.minhaagenda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    ContatoAdapter contatoAdapter;
    DatabaseReference myRef;
    FirebaseDatabase database;
    List<Contato> lc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lc = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_contatos);

        carregaLista();

        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("idContato",lc.size());
                Intent addContact = new Intent(MainActivity.this, AddContactActivity.class);
                addContact.putExtras(bundle);
                startActivity(addContact);
            }
        });
    }

    private void carregaLista(){

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lc.clear();
        carregaContatosCloud();

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        carregaLista();

    }

    private void carregaContatosCloud() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lc.clear();
                Contato contato;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    contato = postSnapshot.getValue(Contato.class);

                    if(contato != null) {
                        lc.add(contato);
                        Log.d("Select Contatos", "Value is: " + contato.getNome());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        contatoAdapter = new ContatoAdapter(lc,MainActivity.this);

                    }
                }
                mRecyclerView.setAdapter(contatoAdapter);


            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Error Select", "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
