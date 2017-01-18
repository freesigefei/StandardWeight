package com.test.standardweight;
/**
@author:freesigefei
该程序的功能是根据用户选择的性别、输入的身高和体重，根据国际计算标准计算用户的体重是否标准，并反馈给用户
**/
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private Button countButton;
    private EditText heightText,weightText;
    private RadioButton manBtn,womenBtn;
    String sex = "";
    double height,actaulweight,standweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用创建视图的函数
        creadView();
        //调用性别选择函数
        sexChoose();
        //调用Button注册监听器函数
        setListenner();
    }

    //定义响应Button时间的函数
    private void setListenner(){
        countButton.setOnClickListener(countListner);
    }
    private View.OnClickListener countListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (sexChoose().equals(getString(R.string.sex_ladybody))) {
                midToast();
            }
            else if (heightText.getText().toString().equals("")){
                Toast.makeText(MainActivity.this,"点毛线啊，身高没输入呢~",Toast.LENGTH_LONG).show();
            }
            else if (weightText.getText().toString().equals("")){
                Toast.makeText(MainActivity.this,"信不信我打你啊，体重都不输入嘞~",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(MainActivity.this, "不负责任的计算结果:" + getWeight(sexChoose()), Toast.LENGTH_LONG).show();
            }
        }
    };

    //定义性别选择函数
    public String sexChoose(){
        if (manBtn.isChecked()){
            sex=getString(R.string.sex_man);
        }
        else if (womenBtn.isChecked()){
            sex=getString(R.string.sex_women);
        }
        else {
            sex=getString(R.string.sex_ladybody);
        }
        return sex;
    }

    //定义创建视图的函数
    public void creadView(){
        countButton=(Button)findViewById(R.id.buttonCount);
        heightText=(EditText)findViewById(R.id.editTextHeight);
        weightText=(EditText)findViewById(R.id.editTextWeight);
        manBtn=(RadioButton)findViewById(R.id.radioButtonMan);
        womenBtn=(RadioButton)findViewById(R.id.radioButtonWomen);
    }

    //定义计算得到标准体重的函数
    private String getWeight(String sex){
        height=Double.parseDouble(heightText.getText().toString());
        actaulweight=Double.parseDouble(weightText.getText().toString());
        String weightresult;
        if (sex.equals(getString(R.string.sex_man))){
            standweight=(height-150)*0.6+50;
            BigDecimal bdactaulweight = new BigDecimal(actaulweight);
            BigDecimal bdstandweightL = new BigDecimal(standweight*0.9);
            BigDecimal bdstandweightR = new BigDecimal(standweight*1.1);
            if ((bdactaulweight.compareTo(bdstandweightL)>=0)&&(bdactaulweight.compareTo(bdstandweightR)<=0)){
                weightresult="相当标致的帅哥啊(绝对符合国际标准~)";
            }
            else if (bdactaulweight.compareTo(bdstandweightL)<0){
                weightresult="瘦子一枚(多吃几个鸡腿去呗~)";
            }
            else {
                weightresult="胖子一坨(吃饱了还能再吃2碗~)";
            }
        }
        else {
            standweight = (height - 150) * 0.6+48;
            BigDecimal bdactaulweight = new BigDecimal(actaulweight);
            BigDecimal bdstandweightL = new BigDecimal(standweight * 0.9);
            BigDecimal bdstandweightR = new BigDecimal(standweight * 1.1);
            if ((bdactaulweight.compareTo(bdstandweightL) >= 0) && (bdactaulweight.compareTo(bdstandweightR) <= 0)) {
                weightresult = "好生标致靓女啊(绝对符合国际标准~)";
            } else if (bdactaulweight.compareTo(bdstandweightL) < 0) {
                weightresult = "瘦妞一枚(多吃几个鸡腿去呗~)";
            } else {
                weightresult = "胖妞一坨(吃饱了还能再吃2碗~)";
            }
        }
        return weightresult;
    }

    //定义人妖时弹出图片提示函数
    private void midToast(){
        Toast toast=new Toast(MainActivity.this);
        toast.setGravity(Gravity.BOTTOM,0,200);
        LinearLayout layout=new LinearLayout(MainActivity.this);
        ImageView image=new ImageView(this);
        image.setImageResource(R.drawable.ladybody);
        layout.addView(image);
        TextView textview = new TextView(MainActivity.this);
        textview.setText(R.string.ladybody_tips);
        textview.setTextSize(30);
        textview.setTextColor(Color.MAGENTA);
        layout.addView(textview);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
