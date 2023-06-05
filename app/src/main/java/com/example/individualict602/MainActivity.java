package com.example.individualict602;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etKwh,etRebate;
    Button btnSubmit, btnClear;
    TextView kwhOutput,rebateOutput, totalOutput;
    String  numKwh,numRebate;
    double kwh,rebate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etKwh = (EditText) findViewById(R.id.editTextNumberDecimal2);
        etRebate = (EditText) findViewById(R.id.editTextNumberDecimal);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
        kwhOutput = (TextView) findViewById(R.id.kwhOutput);
        rebateOutput = (TextView) findViewById(R.id.rebateOutput);
        totalOutput = (TextView) findViewById(R.id.totalOutput);

        btnSubmit.setOnClickListener(this);
        btnClear.setOnClickListener(this);


    }


    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){



            case R.id.about:

                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {



        if(v==btnSubmit){

            numKwh=etKwh.getText().toString();
            numRebate=etRebate.getText().toString();

            try{
                kwh=Double.parseDouble(numKwh);


                if(kwh >1000000 || kwh<0){
                    throw new NumberLimitException();
                }



            }catch(NumberFormatException nfe){

                Toast.makeText(this,  "Please enter valid number for kwh", Toast.LENGTH_SHORT).show();


            }
            catch (NumberLimitException nle){
                Toast.makeText(this, "Please enter number between 0-100000 for kwh and 0-5 for rebate", Toast.LENGTH_SHORT).show();


            }

            try{
                rebate=Double.parseDouble(numRebate);

                if(rebate >5 || rebate<0){
                    throw new NumberLimitException();
                }
            }
            catch(NumberFormatException nfe){

                Toast.makeText(this,  "Please enter valid number for rebate", Toast.LENGTH_SHORT).show();


            }
            catch (NumberLimitException nle){
                Toast.makeText(this, "Please enter number between 0-5 for rebate", Toast.LENGTH_SHORT).show();


            }

            if(kwh<= 200 && rebate<= 5) {
                double totalpay = kwh * 0.218;
                double totalrebate = totalpay * (rebate/100);
                double totalall = totalpay - totalrebate;
                kwhOutput.setText("Total price :RM"+ totalpay);
                rebateOutput.setText("Get Rebate :RM"+ totalrebate);
                totalOutput.setText("Total price bills :RM"+ totalall);

            }
            else if(kwh<=300 && rebate<= 5){
                double totalpay = 200 * 0.218 + (kwh - 200) * 0.334;
                double totalrebate = totalpay * (rebate/100);
                double totalall = totalpay - totalrebate;
                kwhOutput.setText("Total price :RM"+ totalpay);
                rebateOutput.setText("Get Rebate :RM"+ totalrebate);
                totalOutput.setText("Total price bills :RM"+ totalall);
            }
            else if(kwh<=600 && rebate<= 5){
                double totalpay = 200 * 0.218 + 100 * 0.334 + (kwh - 300) * 0.516;
                double totalrebate = totalpay * (rebate/100);
                double totalall = totalpay - totalrebate;
                kwhOutput.setText("Total price :RM"+ totalpay);
                rebateOutput.setText("Get Rebate :RM"+ totalrebate);
                totalOutput.setText("Total price bills :RM"+ totalall);
            }
            else {
                double totalpay = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (kwh - 600) * 54.6;
                double totalrebate = totalpay * (rebate/100);
                double totalall = totalpay - totalrebate;
                kwhOutput.setText("Total price :RM"+ totalpay);
                rebateOutput.setText("Get Rebate :RM"+ totalrebate);
                totalOutput.setText("Total price bills :RM"+ totalall);
            }



        }
        else if(v==btnClear){
            //Toast.makeText(this, "button clear clicked", Toast.LENGTH_SHORT).show();
            etKwh.setText("");
            etRebate.setText("");
            kwhOutput.setText("");
            rebateOutput.setText("");
            totalOutput.setText("");
        }

    }


}
