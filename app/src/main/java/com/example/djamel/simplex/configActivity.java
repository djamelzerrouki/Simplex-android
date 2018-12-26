package com.example.djamel.simplex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class configActivity extends AppCompatActivity {
private static   LinearLayout.LayoutParams layoutParams;
public static int N =2;//nombre de variable horBAse
public static int M =2;//nombre des contrent
public static boolean MaxMin=true;
    ArrayList<EditText> edittext_list;
    ArrayList<Button> button_list;

    TextView textAfichag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        LinearLayout layout =new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        Button next = (Button) findViewById(R.id.simplex);
          textAfichag = (TextView) findViewById(R.id.Affichage);

        edittext_list = new ArrayList<EditText>();
        button_list=new ArrayList<Button>();
        next.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                // TODO Auto-generated method stub

                for(int i=0; i < edittext_list.size(); i++){
                    edittext_list.get(i).getText().toString();
                }
                 double[] objectiveFunc =  new double[N];// fonction objectif
               double[] constraintRightSide = new double[M];// constraintRightSide

                for(int i=0; i <N; i++){
                     objectiveFunc[i] =Double.parseDouble(edittext_list.get(i).getText().toString());
                }

                 for(int i=0; i <M; i++){
                  constraintRightSide[i] =Double.parseDouble(edittext_list.get((N*(i+1))+(N)+(i)).getText().toString());
                }

                double[][] constraintLeftSide =  new double[M][N];

                int index=0;

                         for(int i=0; i <M; i++) {
                             index=((i+1)*N)+i;

                             for (int j = 0; j < N ; j++) {
                                 constraintLeftSide[i][j] = Double.parseDouble(edittext_list.get(j+index).getText().toString());
                             }
                }


                Simplex.Constraint[] constraintOperator= new Simplex.Constraint[M];
                for(int i=0; i <button_list.size(); i++){

                    switch (button_list.get(i).getText().toString()) {
                        case "<=":
                            makeText(v.getContext(),i+"  : "+button_list.get(i).getText().toString(),Toast.LENGTH_LONG).show();

                            constraintOperator[i] =Simplex.Constraint.lessThan;
                            break;
                        case  ">=":
                            constraintOperator[i] =Simplex.Constraint.greatherThan;

                            break;
                     }
                }

                //  double[] objectiveFunc = { 1200,1000};// fonction objectif
                // double[][] constraintLeftSide = { {3,4}, { 6,3} }; // les contret

                //Simplex.Constraint[] constraintOperator = { Simplex.Constraint.lessThan,Simplex.Constraint.lessThan,Simplex.Constraint.lessThan,Simplex.Constraint.lessThan  };

//              double[] constraintRightSide = { 1200,1000 };

                Simplex.Modeler model = new Simplex.Modeler(constraintLeftSide, constraintRightSide,
                        constraintOperator, objectiveFunc);

                Simplex simplex = new Simplex(model.getTableaux(),
                        model.getNumberOfConstraint(),
                        model.getNumberOfOriginalVariable(), MaxMin);
                double[] x = simplex.primal();
                StringBuffer buffer=new StringBuffer();
                for (int i = 0; i < x.length; i++){
                    buffer.append("x[" + i + "] = " + x[i]+"\n");
                    System.out.println("x[" + i + "] = " + x[i]);
                }

buffer.append("Solution: " + simplex.value());
                System.out.println("Solution: " + simplex.value());
                textAfichag.setText(buffer.toString());
                makeText(v.getContext() ,buffer.toString(),Toast.LENGTH_LONG).show();

            }

         });

         LinearLayout mainlayout = (LinearLayout) findViewById(R.id.config);
//fonctioObjectif
        LinearLayout layoutfonctioObjectif = (LinearLayout) findViewById(R.id.fonctioObjectif);
        layoutfonctioObjectif.addView(getLinearLayoutlayoutfonctioObjectif(1,N));

      layoutParams = new  LinearLayout.LayoutParams(120, ViewGroup.LayoutParams.WRAP_CONTENT);
   layoutParams.setMargins(5, 3, 0, 0); // left, top, right, bottom



for (int j =1;j<=M;j++){
    mainlayout.addView(getLinearLayout(j,N,getButton(j)));
 }

    }

    public LinearLayout getLinearLayout(int id,int N, Button b){
        LinearLayout l= new LinearLayout(this);
        l.setId(id);
        l.setGravity(Gravity.CENTER);
        l.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 1;i<=N-1;i++){
            l.addView(getEditText(i));

            l.addView(getTextView(i,"X"+i+"+"));
        }
        l.addView(getEditText(N));
        l.addView(getTextView(N,"X"+N));

        l.addView(b);
        l.addView(getEditText(N+1));



        return   l;
    }

    public LinearLayout getLinearLayoutlayoutfonctioObjectif(int id,int N){
        LinearLayout l= new LinearLayout(this);
        l.setId(id);
        l.setGravity(Gravity.CENTER);
        l.setOrientation(LinearLayout.HORIZONTAL);
        final Button b= new Button(this);
        b.setId(id);
        b.setText("Max");
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
               if (!MaxMin){ b.setText("Max");  MaxMin=true;}
               else { b.setText("Min");  MaxMin=false;}

            }
        });
l.addView(b);
        l.addView(getTextView(0,"Z ="));
        for (int i = 1;i<=N-1;i++){
            l.addView(getEditText(i));
            l.addView(getTextView(i,"X"+i+"+"));
        }
        l.addView(getEditText(N));
        l.addView(getTextView(N,"X"+(N)));

        return   l;
    }

    public Button getButton(int id){
        final Button b= new Button(this);
        b.setId(id);
b.setText("<=");
b.setLayoutParams(layoutParams);
            final boolean[] LessGreather = {true}; //  lessThan, equal, greatherThan

b.setOnClickListener(new View.OnClickListener() {

    public void onClick(View v) {
        /* TODO Auto-generated method stub */

        if (!LessGreather[0]){ b.setText("<=");  LessGreather[0] =true;}
        else { b.setText(">=");  LessGreather[0] =false;}

    }

});
        button_list.add(b);

return   b;
    }
    public TextView getTextView(int id){
        TextView t= new TextView(this);
        t.setId(id);
        t.setText("+");
        return   t;
    }
    public TextView getTextView(int id,String text){
        TextView t= new TextView(this);
        t.setId(id);
        t.setText(text);
        return   t;
    }
    public EditText getEditText(int id){
        EditText e= new EditText(this);
        e.setId(id);
        e.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams layoutParams = new  LinearLayout.LayoutParams(70, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 3, 0, 0);
        e.setLayoutParams(layoutParams);
        edittext_list.add(e);
         return   e;
    }
}
