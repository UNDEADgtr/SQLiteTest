package by.undead.sqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTextView).setOnClickListener(this);
        findViewById(R.id.btnList).setOnClickListener(this);
        findViewById(R.id.btnListContent).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnTextView:{
                intent = new Intent(MainActivity.this, TextViewActivity.class);
                break;
            }
            case R.id.btnList:{
                intent = new Intent(MainActivity.this, ListViewActivity.class);
                break;
            }
            case R.id.btnListContent:{
                intent = new Intent(MainActivity.this, ListViewContentActivity.class);
            }
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
