package com.example.okay.shiyan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okay.shiyan.Thead.RegisterHttpThread;


public class SecondActivity extends AppCompatActivity {
    //获取名字
    EditText nameView=null;
    //密码
    EditText pwdView=null;
    //获取性别
    RadioGroup sexs=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button sub=(Button)findViewById(R.id.bt3);
/**
 * 点击注册按钮，通过注册HTTP线程返回注册结果
 */
        sub.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //如果信息填写完整
               if (checkAll()) {
                   String sex_reg = null;
                   for (int i = 0; i < sexs.getChildCount(); i++) {
                       RadioButton radioButton = (RadioButton) sexs.getChildAt(i);
                       if (radioButton.isChecked()) {
                           sex_reg = radioButton.getText().toString();
                       }
                   }
                   //注册线程，传参（name,password,sex）
                   RegisterHttpThread registerHttpThread = new RegisterHttpThread(nameView.getText().toString(), pwdView.getText().toString(), sex_reg);
                   registerHttpThread.start();
                   try {
                       registerHttpThread.join();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   if (registerHttpThread.isOk()) {
                       Toast.makeText(SecondActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                       startActivity(intent);
                   }


               }
           }
       });


/**
 * 返回按钮,点击跳转回登录页面
 */
        TextView back=(TextView)findViewById(R.id.bt4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }});

    }


    /**
     * 将所有判断条件一起判断
     * @return
     */
    public boolean checkAll(){
        return checkName()&&checkPwd()&&checkSex()?true:false;
    }


    public boolean checkName(){
        nameView=(EditText)findViewById(R.id.red1);
        String name=nameView.getText().toString();
        if(name.equals("")){
            Toast.makeText(SecondActivity.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getSex(){
        for(int i=0;i<sexs.getChildCount();i++){
            RadioButton sex=(RadioButton) sexs.getChildAt(i);
            if(sex.isChecked()){
                return sex.getText().toString();
            }
        }
        return "";
    }
    public boolean checkPwd(){
        pwdView=(EditText)findViewById(R.id.red2);
        String  pwd=pwdView.getText().toString();
        EditText pwdSureView=(EditText)findViewById(R.id.red3);
        String pwdsure=pwdSureView.getText().toString();
        if(pwd.equals("")){
            Toast.makeText(SecondActivity.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pwd.equals(pwdsure)){
            Toast.makeText(SecondActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkSex(){
        sexs=(RadioGroup)findViewById(R.id.rg_sex);
        for(int i=0;i<sexs.getChildCount();i++){
            RadioButton sex=(RadioButton) sexs.getChildAt(i);
            if(sex.isChecked()){
                return true;
            }
        }
        Toast.makeText(SecondActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
        return false;
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
