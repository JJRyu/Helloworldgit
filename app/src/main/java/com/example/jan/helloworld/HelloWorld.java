package com.example.jan.helloworld;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HelloWorld extends Activity implements View.OnClickListener{


   public ProgressBar bar;
    public Button btn;
    public Button seite2btn;
  //  final Context context=this;
    public int i=1;
    ImageView Bild;
    Button Bild_laden;

    Button safe;
    Button load;
    TextView anzeige;
    EditText eingabe;
    SharedPreferences speicher;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        bar=(ProgressBar)findViewById(R.id.progressBar);
        bar.setMax(100);

        btn=(Button)findViewById(R.id.button);
      //  btn.setOnClickListener(new View.OnClickListener(){
        btn.setOnClickListener(this);

        seite2btn=(Button)findViewById(R.id.page2button);
        seite2btn.setOnClickListener(this);
        Bild=(ImageView)findViewById(R.id.imageView1);
        Bild_laden=(Button)findViewById(R.id.bild_button);
        Bild_laden.setOnClickListener(this);

        safe=(Button)findViewById(R.id.safebutton);
        safe.setOnClickListener(this);

        load=(Button)findViewById(R.id.loadbutton);
        load.setOnClickListener(this);

        anzeige=(TextView)findViewById(R.id.textView4);
        eingabe=(EditText)findViewById(R.id.inputtext);

        speicher = getApplicationContext().getSharedPreferences("Daten",0);
        editor =speicher.edit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_world, menu);
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

    @Override
    public void onClick(View e) {
        if (e == btn) {
            i += 4;
            if (i >= 100) {
                // AlertDialog.Builder ad =new AlertDialog.Builder(this).create();
                AlertDialog ad = new AlertDialog.Builder(this).create();
                ad.setMessage("Leiste ist voll");
                ad.show();
                i = 0;
            }
            bar.setProgress(i);
        }
        if (e == seite2btn) {
            Intent intent = new Intent(this, seite2.class);
            EditText et1 = (EditText) findViewById(R.id.eingabe);
            String input = et1.getText().toString();
            intent.putExtra("key", input);
            startActivity(intent);
        }
        if (e == Bild_laden) {
            //Toast.makeText(this, getResources().getDisplayMetrics().toString(), Toast.LENGTH_LONG).show();
            try {
                Bild.setImageResource(R.drawable.th);
            } catch (Exception d) {
                Toast.makeText(this, d.toString(), Toast.LENGTH_LONG).show();
            }
        }

        if (e == safe) {
            Textspeichern(eingabe.getText().toString());
        }

        if (e == load) {
            Textladen();
        }
    }




    public void Textladen(){
        if(speicher.getString("Data1",null)!= null){
            anzeige.setText(speicher.getString("Data1", null));
        }
    }

    public void Textspeichern(String inhalt){
        if(inhalt !=null){
            editor.putString("Data1", inhalt);
            editor.commit();
            Toast.makeText(this, "neuer Inhalt hinzugefügt", Toast.LENGTH_LONG).show();

        }
    }



}
