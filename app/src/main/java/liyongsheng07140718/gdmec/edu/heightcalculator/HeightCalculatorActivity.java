package liyongsheng07140718.gdmec.edu.heightcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**--------------------07140718--------------------------
 * @author 李永生
 * 项目名称：个人标身高体重计算器。
 * 项目目标：
 * （1)开发输入界面。
 * （2）进行事件处理。
 * （3）处理计算结果。
 * （4）发布到手机。
 */

public class HeightCalculatorActivity extends Activity {
    private Button calculatorButton;//计算按钮
    private EditText weightEditText;//体重输入框
    private CheckBox manCheckBox;//男性单选按钮
    private CheckBox womanCheckBox;//女性单选按钮
    private TextView resultTextView;//显示结果
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_calculator);
        setTitle("HeightCalculator");
        calculatorButton = (Button)findViewById(R.id.calculator);
        weightEditText = (EditText)findViewById(R.id.weight);
        manCheckBox = (CheckBox)findViewById(R.id.man);
        womanCheckBox = (CheckBox)findViewById(R.id.woman);
        resultTextView = (TextView)findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvevt();
    }

    /**
     * 注册事件
     */
    protected void registerEvevt() {
        //注册按钮事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已填写体重数据
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //判断是否已选择性别
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果------\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高：");
                            double result = evalueteHeight(weight,"男");
                            sb.append((int)result+"厘米\n");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女性标准身高：");
                            double result = evalueteHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        //输出显示结果
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");
                    }
                } else {
                    showMessage("请输入体重！");
                }
            }
        });
    }

    /**
     * 计算处理执行代码事件
     * @param weight
     * @param sex
     * @return
     */
    private double evalueteHeight(double weight,String sex) {
        double height;
        if(sex=="男") {
            height = 170-(62-weight)/0.6;
        } else {
            height = 158-(52-weight)/0.5;
        }
        return height;
    }

    /**
     * 消息提示框
     * @param message
     */
    private void showMessage(String message) {
        //提示框
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {

            }
        });
        alert.show();
    }

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_height_calculator, menu);
        menu.add(Menu.NONE, 1, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == 1) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
