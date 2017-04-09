package max.sudoku;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    static int dif,i,j;
    static Button s1play, s1solve, d1,d2,d3,d4,d5;
    static Button x1,x2,x3,x4,x5,x6,x7,x8,x9, xhint, xsolve,xdel,xcheck,b=null;
    //static Button x00,x01,x02,x03,x04,x05,x06,x07,x08,x10,x11,x12,x13,x14,x15,x16,x17,x18,x20,x21,x22,x23,x24,x25,x26,x27,x28,x30,x31,x32,x33,x34,x35,x36,x37,x38,x40,x41,x42,x43,x44,x45,x46,x47,x48,x50,x51,x52,x53,x54,x55,x56,x57,x58,x60,x61,x62,x63,x64,x65,x66,x67,x68,x70,x71,x72,x73,x74,x75,x76,x77,x78,x80,x81,x82,x83,x84,x85,x86,x87,x88;
    static Button[][] grid = new Button[9][9];
    static int[][] gridVal = new int[9][9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s1);

        s1play = (Button) findViewById(R.id.s1play);
        s1solve = (Button) findViewById(R.id.s1solve);


        s1play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.s2);
                d1 = (Button) findViewById(R.id.d1);
                d2 = (Button) findViewById(R.id.d2);
                d3 = (Button) findViewById(R.id.d3);
                d4 = (Button) findViewById(R.id.d4);
                d5 = (Button) findViewById(R.id.d5);
                difficultyListener();
        }
        });
        s1solve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.s3);
                boardListener();
            }
        });
    }
    static void difficultyListener(){
        d1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=1;
            }
        });
        d2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=2;
            }
        });
        d3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=3;
            }
        });
        d4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=4;
            }
        });
        d5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=5;
            }
        });
    }
    void boardListener(){

        grid[0][0] = (Button) findViewById(R.id.x00);
        grid[0][1] = (Button) findViewById(R.id.x01);
        grid[0][2] = (Button) findViewById(R.id.x02);
        grid[0][3] = (Button) findViewById(R.id.x03);
        grid[0][4] = (Button) findViewById(R.id.x04);
        grid[0][5] = (Button) findViewById(R.id.x05);
        grid[0][6] = (Button) findViewById(R.id.x06);
        grid[0][7] = (Button) findViewById(R.id.x07);
        grid[0][8] = (Button) findViewById(R.id.x08);
        grid[1][0] = (Button) findViewById(R.id.x10);
        grid[1][1] = (Button) findViewById(R.id.x11);
        grid[1][2] = (Button) findViewById(R.id.x12);
        grid[1][3] = (Button) findViewById(R.id.x13);
        grid[1][4] = (Button) findViewById(R.id.x14);
        grid[1][5] = (Button) findViewById(R.id.x15);
        grid[1][6] = (Button) findViewById(R.id.x16);
        grid[1][7] = (Button) findViewById(R.id.x17);
        grid[1][8] = (Button) findViewById(R.id.x18);
        grid[2][0] = (Button) findViewById(R.id.x20);
        grid[2][1] = (Button) findViewById(R.id.x21);
        grid[2][2] = (Button) findViewById(R.id.x22);
        grid[2][3] = (Button) findViewById(R.id.x23);
        grid[2][4] = (Button) findViewById(R.id.x24);
        grid[2][5] = (Button) findViewById(R.id.x25);
        grid[2][6] = (Button) findViewById(R.id.x26);
        grid[2][7] = (Button) findViewById(R.id.x27);
        grid[2][8] = (Button) findViewById(R.id.x28);

        grid[3][0] = (Button) findViewById(R.id.x30);
        grid[3][1] = (Button) findViewById(R.id.x31);
        grid[3][2] = (Button) findViewById(R.id.x32);
        grid[3][3] = (Button) findViewById(R.id.x33);
        grid[3][4] = (Button) findViewById(R.id.x34);
        grid[3][5] = (Button) findViewById(R.id.x35);
        grid[3][6] = (Button) findViewById(R.id.x36);
        grid[3][7] = (Button) findViewById(R.id.x37);
        grid[3][8] = (Button) findViewById(R.id.x38);
        grid[4][0] = (Button) findViewById(R.id.x40);
        grid[4][1] = (Button) findViewById(R.id.x41);
        grid[4][2] = (Button) findViewById(R.id.x42);
        grid[4][3] = (Button) findViewById(R.id.x43);
        grid[4][4] = (Button) findViewById(R.id.x44);
        grid[4][5] = (Button) findViewById(R.id.x45);
        grid[4][6] = (Button) findViewById(R.id.x46);
        grid[4][7] = (Button) findViewById(R.id.x47);
        grid[4][8] = (Button) findViewById(R.id.x48);
        grid[5][0] = (Button) findViewById(R.id.x50);
        grid[5][1] = (Button) findViewById(R.id.x51);
        grid[5][2] = (Button) findViewById(R.id.x52);
        grid[5][3] = (Button) findViewById(R.id.x53);
        grid[5][4] = (Button) findViewById(R.id.x54);
        grid[5][5] = (Button) findViewById(R.id.x55);
        grid[5][6] = (Button) findViewById(R.id.x56);
        grid[5][7] = (Button) findViewById(R.id.x57);
        grid[5][8] = (Button) findViewById(R.id.x58);

        grid[6][0] = (Button) findViewById(R.id.x60);
        grid[6][1] = (Button) findViewById(R.id.x61);
        grid[6][2] = (Button) findViewById(R.id.x62);
        grid[6][3] = (Button) findViewById(R.id.x63);
        grid[6][4] = (Button) findViewById(R.id.x64);
        grid[6][5] = (Button) findViewById(R.id.x65);
        grid[6][6] = (Button) findViewById(R.id.x66);
        grid[6][7] = (Button) findViewById(R.id.x67);
        grid[6][8] = (Button) findViewById(R.id.x68);
        grid[7][0] = (Button) findViewById(R.id.x70);
        grid[7][1] = (Button) findViewById(R.id.x71);
        grid[7][2] = (Button) findViewById(R.id.x72);
        grid[7][3] = (Button) findViewById(R.id.x73);
        grid[7][4] = (Button) findViewById(R.id.x74);
        grid[7][5] = (Button) findViewById(R.id.x75);
        grid[7][6] = (Button) findViewById(R.id.x76);
        grid[7][7] = (Button) findViewById(R.id.x77);
        grid[7][8] = (Button) findViewById(R.id.x78);
        grid[8][0] = (Button) findViewById(R.id.x80);
        grid[8][1] = (Button) findViewById(R.id.x81);
        grid[8][2] = (Button) findViewById(R.id.x82);
        grid[8][3] = (Button) findViewById(R.id.x83);
        grid[8][4] = (Button) findViewById(R.id.x84);
        grid[8][5] = (Button) findViewById(R.id.x85);
        grid[8][6] = (Button) findViewById(R.id.x86);
        grid[8][7] = (Button) findViewById(R.id.x87);
        grid[8][8] = (Button) findViewById(R.id.x88);

        xcheck = (Button) findViewById(R.id.xcheck);
        xsolve = (Button) findViewById(R.id.xsolve);
        xhint = (Button) findViewById(R.id.xhint);

        x1 = (Button) findViewById(R.id.x1);
        x2 = (Button) findViewById(R.id.x2);
        x3 = (Button) findViewById(R.id.x3);
        x4 = (Button) findViewById(R.id.x4);
        x5 = (Button) findViewById(R.id.x5);
        x6 = (Button) findViewById(R.id.x6);
        x7 = (Button) findViewById(R.id.x7);
        x8 = (Button) findViewById(R.id.x8);
        x9 = (Button) findViewById(R.id.x9);
        xdel = (Button) findViewById(R.id.xdel);


        startListener();
        startListenerOptions();

    }


    void numberListener(){

        stopListenerOptions();
        stopListener();

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setBackgroundResource(R.drawable.back);
                startListener();
            }
        });
        x1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("1");
                startListener();
            }
        });
        x2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("2");
                startListener();
            }
        });
        x3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("3");
                startListener();
            }
        });
        x4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("4");
                startListener();
            }
        });
        x5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("5");
                startListener();
            }
        });
        x6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("6");
                startListener();
            }
        });
        x7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("7");
                startListener();
            }
        });
        x8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("8");
                startListener();
            }
        });
        x9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("9");
                startListener();
            }
        });
        xdel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b.setText("");
                startListener();
            }
        });

    }

    void startListener(){
        x1.setOnClickListener(null);
        x2.setOnClickListener(null);
        x3.setOnClickListener(null);
        x4.setOnClickListener(null);
        x5.setOnClickListener(null);
        x6.setOnClickListener(null);
        x7.setOnClickListener(null);
        x8.setOnClickListener(null);
        x9.setOnClickListener(null);
        xdel.setOnClickListener(null);

        if(b!=null)b.setBackgroundResource(R.drawable.back);

        startListenerOptions();

        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                grid[i][j].setOnClickListener(new View.OnClickListener(){
                    final Button btn = grid[i][j];
                    @Override
                    public void onClick(View v){
                        btn.setBackgroundColor(Color.BLUE);
                        b=btn;
                        numberListener();
                    }
                });
            }
        }

    }

    void stopListener(){
        for(i=0;i<9;i++)for(j=0;j<9;j++){
            final Button btn = grid[i][j];
            btn.setOnClickListener(null);
        }
    }


    void startListenerOptions(){
        xcheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b=null;
                stopListenerOptions();
                stopListener();
                getValues();
                checkHorizontal();
                checkVertical();
                checkBox();
                startListenerOptions();
                startListener();
            }
        });

        xsolve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b=null;
                stopListenerOptions();
                stopListener();
                getValues();
                xsolve.setBackgroundColor(Color.BLUE);
                Log.d("Starting","--------------------------------------------------------------");
                if(solveRecur(0,0)){
                    for(int i=0;i<9;i++)for(int j=0;j<9;j++){
                        final Button btn = grid[i][j];
                        if(gridVal[i][j]!=0)btn.setText(gridVal[i][j]+"");
                    }
                    xsolve.setBackgroundColor(Color.BLACK);
                }else{//TODO print unsolvable
                    for(int i=0;i<9;i++)for(int j=0;j<9;j++){
                        final Button btn = grid[i][j];
                        if(gridVal[i][j]!=0)btn.setText(gridVal[i][j]+"");
                    }
                    xsolve.setBackgroundColor(Color.RED);
                }
                startListenerOptions();
                startListener();
            }
        });
        xhint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            //TODO hint button
            }
        });
    }
    void stopListenerOptions(){
        xcheck.setOnClickListener(null);
        xsolve.setOnClickListener(null);
        xhint.setOnClickListener(null);
    }

    void getValues(){
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            final Button btn = grid[i][j];
            String s = btn.getText().toString();
            //Log.d("Grid","x = "+i+", y = "+j+"   == "+s);
            if(!s.equals(""))gridVal[i][j]=Integer.parseInt(s);
        }
    }
    void checkHorizontal(){
        for(int i=0;i<9;i++){
            int[] arr = new int[10];
            for(int j=0;j<9;j++){
                ++arr[gridVal[j][i]];
            }
            for(int k=1;k<10;k++){
                if(arr[k]>1){
                    for(int j=0;j<9;j++){
                        if(gridVal[j][i]==k){
                            final Button btn = grid[j][i];
                            btn.setBackgroundColor(Color.RED);
                        }
                    }
                }
            }
        }
    }
    void checkVertical(){
        for(int i=0;i<9;i++){
            int[] arr = new int[10];
            for(int j=0;j<9;j++){
                ++arr[gridVal[i][j]];
            }
            for(int k=1;k<10;k++){
                if(arr[k]>1){
                    for(int j=0;j<9;j++){
                        if(gridVal[i][j]==k){
                            final Button btn = grid[i][j];
                            btn.setBackgroundColor(Color.RED);
                        }
                    }
                }
            }
        }
    }
    void checkBox(){
        for(int a =0;a<3;a++)for(int b=0;b<3;b++){
            int[] arr = new int[10];
            for(int i=a*3;i<a*3+3;i++)for(int j=b*3;j<b*3+3;j++){
                ++arr[gridVal[i][j]];
            }
            for(int k=1;k<10;k++){
                if(arr[k]>1){
                    for(int i=a*3;i<a*3+3;i++)for(int j=b*3;j<b*3+3;j++){
                        if(gridVal[i][j]==k){
                            final Button btn = grid[i][j];
                            btn.setBackgroundColor(Color.RED);
                        }
                    }
                }
            }
        }
    }

    boolean solveRecurCheck(int x,int y,int v){
        //horizontal
        for(int i=0;i<9;i++){
            if(x!=i && v==gridVal[i][y]){
                return false;
            }
        }
        //vertical
        for(int i=0;i<9;i++){
            if(y!=i && v==gridVal[x][i]){
                return false;
            }
        }
        //box
        for(int i=(x/3)*3;i<(x/3)*3+3;i++)for(int j=(y/3)*3;j<(y/3)*3+3;j++){//Log.d("Box","For x:"+i+", y:"+j+",  "+v);
            if(!(i==x && j==y) && v==gridVal[i][j]){//Log.d("check","b false");
                return false;
            }
        }
        return true;
    }

    boolean solveRecur(int x, int y){
        Log.d("Recur","In the loop");
        int v = gridVal[x][y],nx=x+1,ny=y;
        if(nx>8){
            ny++;
            nx=0;
        }
        if(v!=0){
            if(solveRecurCheck(x,y,v)){
                if(x==8 && y==8)return true;
                return solveRecur(nx,ny);
            }else return false;
        }
        //Log.d("Recur","try random");
        for(int i=1;i<10;i++){
            //Log.d("Try","For x:"+x+", y:"+y+",  "+i);
            gridVal[x][y]=i;
            if(solveRecurCheck(x,y,i)){
                //Log.d("Use","For x:"+x+", y:"+y+",  i");
                if(x==8 && y==8)return true;
                if(solveRecur(nx,ny))return true;
            }
        }
        gridVal[x][y]=0;
        return false;
    }


    //Todo: retrieve from database
    static void getPuzzlesByDifficulty(int d){
        String link = "http://localhost.org/phpmyadmin/db_structure.php?server=1&db=sudoku";
    }


}
