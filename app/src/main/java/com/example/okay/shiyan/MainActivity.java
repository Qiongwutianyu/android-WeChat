package com.example.okay.shiyan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.okay.shiyan.Thead.LoginHttpThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button bt2 = (Button) findViewById(R.id.bt2);
        //ʵ��ע��ҳ����ת
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        //�����¼��ת����ҳ,��ת��ҳ���뽫�û�������
        Button login=(Button)findViewById(R.id.bt1);
        final EditText name = (EditText) findViewById(R.id.ed1);
        final EditText password= (EditText) findViewById(R.id.ed2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //�����¼��Ϣ��дȱʧ,��������ִ��
        if(!checkLogin()){return;}
            //��Ϣ��д��ȫ��������¼Http�߳�
            LoginHttpThread loginHttpThread=new LoginHttpThread(name.getText().toString(),password.getText().toString());
            loginHttpThread.start();
            try {
                loginHttpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //�жϵ�¼��Ϣ�Ƿ���ȷ
            if( loginHttpThread.isOk()){
                Bundle bundle=new Bundle();
                Intent intent2=new Intent(MainActivity.this,SecondActivity.class);;
                intent2.putExtras(bundle);
                startActivity(intent2);
            }else{
                Toast.makeText(MainActivity.this, "�û������������", Toast.LENGTH_SHORT).show();
            }

        }});
    }



    private boolean checkLogin() {
        EditText ed1=(EditText)findViewById(R.id.ed1);
        EditText ed2=(EditText)findViewById(R.id.ed2);
        if(ed1.getText().toString().equals("")){
            Toast.makeText(MainActivity.this,"�������û���",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(ed2.getText().toString().equals("")){
                Toast.makeText(MainActivity.this,"����������",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
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
