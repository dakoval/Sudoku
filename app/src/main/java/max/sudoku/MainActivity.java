package max.sudoku;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    static int dif,i,j,loops;
    static Button s1play, s1solve,xback_s2, d1,d2,d3,d4,d5;
    static Button x1,x2,x3,x4,x5,x6,x7,x8,x9, xhint, xsolve,xdel,xcheck,xclear,xquit,b=null;
    static Button[][] grid = new Button[9][9];
    static int[][] gridVal = new int[9][9];
    static boolean solved = false;
    DatastoreFactory.DatastoreFactoryDbHelper mDbHelper = new DatastoreFactory.DatastoreFactoryDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s1Listener();
        databaseCheck(); // check if TESS.db exists. If not, save the values for easier lookup.
    }

    void databaseCheck(){
        File database=getApplicationContext().getDatabasePath("TESS.db");
        if (!database.exists()) {
            database_save("0","000000000000000000000000000000000000000000000000000000000000000000000000000000000"); // Initialize save state
            database_save("1","010020300004005060070000008006900070000100002030048000500006040000800106008000000"); // level 1 data
            database_save("2","000427000060090080000000000900000008120030045500000007000000000040060030000715000"); // level 2 data
            database_save("3","000398000050010060000000000800000009120030045700000008000000000040020010000769000"); // level 3 data
            database_save("4","102004070000902800009003004000240006000107000400068000200800700007501000080400109"); // level 4 data
            database_save("5","002008050000040070480072000008000031600080005570000600000960048090020000030800900"); // level 5 data
            Log.i("Database", "Not Found: adding board data now. ");
        } else {
            Log.i("Database", "Found, yay!");
        }
    }
    void database_save(String level, String board){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatastoreFactory.FeedEntry.COLUMN_NAME_LEVEL, level);
        values.put(DatastoreFactory.FeedEntry.COLUMN_NAME_BOARD, board);

        long newRowId = db.insert(DatastoreFactory.FeedEntry.TABLE_NAME, null, values);
        Log.w("Database","-SAVING-");

    }
    void database_update(String level, String board){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DatastoreFactory.FeedEntry.COLUMN_NAME_BOARD, board);

        // Which row to update, based on the title
        String selection = DatastoreFactory.FeedEntry.COLUMN_NAME_LEVEL + " LIKE ?";
        String[] selectionArgs = { level };

        int count = db.update(
                DatastoreFactory.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        Log.w("Database","-UPDATING-");
    }
    private String database_retrieve(String level){

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DatastoreFactory.FeedEntry._ID,
                DatastoreFactory.FeedEntry.COLUMN_NAME_LEVEL,
                DatastoreFactory.FeedEntry.COLUMN_NAME_BOARD
        };

        String selection = DatastoreFactory.FeedEntry.COLUMN_NAME_LEVEL + " = ?";
        String[] selectionArgs = { level };

        String sortOrder =
                DatastoreFactory.FeedEntry.COLUMN_NAME_BOARD + " DESC";

        Cursor cursor = db.query(
                DatastoreFactory.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        Log.w("test",DatabaseUtils.dumpCursorToString(cursor));
        String data = "";
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                data = cursor.getString(cursor.getColumnIndex("board"));
               // Log.w("r", data);
                cursor.moveToNext();
            }
        }
        cursor.close();
        //TODO: randomly select board values (rand (0, cursor.size))
        return data;
    }

    void s1Listener(){
        setContentView(R.layout.s1);
        s1play = (Button) findViewById(R.id.s1play);
        s1solve = (Button) findViewById(R.id.s1solve);
        s1play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.s2);


                difficultyListener();
                backToS1Listener();
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

    void difficultyListener(){
        d1 = (Button) findViewById(R.id.d1);
        d2 = (Button) findViewById(R.id.d2);
        d3 = (Button) findViewById(R.id.d3);
        d4 = (Button) findViewById(R.id.d4);
        d5 = (Button) findViewById(R.id.d5);
        d1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=1;
                retrievePuzzle(1); // load level 1 Sudoku game
            }
        });
        d2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=2;
                retrievePuzzle(2); // load level 2 Sudoku game
            }
        });
        d3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=3;
                retrievePuzzle(3); // load level 3 Sudoku game
            }
        });
        d4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=4;
                retrievePuzzle(4); // load level 4 Sudoku game
            }
        });
        d5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dif=5;
                retrievePuzzle(5); // load level 5 Sudoku game
            }
        });
    }

    void backToS1Listener(){
        xback_s2 = (Button) findViewById(R.id.xback_s2);
        xback_s2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                s1play.setOnClickListener(null);
                s1solve.setOnClickListener(null);
                d1.setOnClickListener(null);
                d2.setOnClickListener(null);
                d3.setOnClickListener(null);
                d4.setOnClickListener(null);
                d5.setOnClickListener(null);
                s1Listener();
            }
        });
    }

    void retrievePuzzle(int n){
        //TODO: randomly pull up puzzles from the database
        String board = null;
        switch(n){
            case 1:
                board = database_retrieve("1");

                break;
            case 2:
                board = database_retrieve("2");
                break;
            case 3:
                board = database_retrieve("3");
                break;
            case 4:
                board = database_retrieve("4");
                break;
            case 5:
                board = database_retrieve("5");
                break;
            default:
                break;
        }
        //String s = "000000000000000000000000000000000000000000000000000000000000000000000000000000001";
       // String s = "010020300004005060070000008006900070000100002030048000500006040000800106008000000";
        //String s = "002008050000040070480072000008000031600080005570000600000960048090020000030800900";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            gridVal[j][i]=Integer.parseInt(board.charAt(i*9+j)+"");
        }
        setContentView(R.layout.s3);
        boardListener();
        writeToScreen();
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
        xclear = (Button) findViewById(R.id.xclear);
        xquit = (Button) findViewById(R.id.xquit);
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
        for(i=0;i<9;i++)for(j=0;j<9;j++) {
            final Button btn = grid[i][j];
            btn.setOnClickListener(null);
        }
    }

    void saveCurrentState(){
        Log.w("Saved State: ", database_retrieve("0"));
        getValues();
        String temp = "";
        for(int i=0;i<9;i++)for(int j=0; j<9; j++){
            int p = gridVal[i][j];

            temp = temp + p +"";
            //Log.w("Grid Val: ", p +"");
        }
        Log.w("StringVal:", temp);
        if(!solved){
            database_update("0",temp);
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
        xquit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                s1Listener();
                saveCurrentState();
                clearScreen();

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
                //Log.d("Starting","--------------------------------------------------------------");
                loops=0;
                solveHorizontal();
                solveVertical();
                solveBox();
                if(solveRecur(0,0)){
                    writeToScreen();
                }else{
                    clearScreen();
                    String s = "Given Sudoku Puzzle Could Not Be Solved.";
                    int at =0;
                    outerLoop:
                    for(int i=0;i<9;i++)for(int j=0;j<9;j++){
                        final Button btn = grid[j][i];
                        if(s.length()-1<at)break outerLoop;
                        else if(s.charAt(at)==' ')j=9;
                        else btn.setText(s.charAt(at)+"");
                        at++;
                    }
                }
                solved = true;
                xsolve.setBackgroundResource(R.drawable.back);
                startListenerOptions();
                startListener();
            }
        });
        xhint.setBackgroundColor(Color.GRAY);//TODO Remove once hint is done
        xhint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            //TODO hint button
            }
        });
        xclear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clearScreen();
            }
        });
    }
    void stopListenerOptions(){
        xcheck.setOnClickListener(null);
        xsolve.setOnClickListener(null);
        xhint.setOnClickListener(null);
        xclear.setOnClickListener(null);
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

    void solveHorizontal(){
        for(int i=0;i<9;i++){
            int[] arr = new int[10];
            int t=0;
            for(int j=0;j<9;j++){
                if(gridVal[j][i] != 0 && arr[gridVal[j][i]]==0)t++;
                ++arr[gridVal[j][i]];
            }
            if(t==8){
                for(int k=1;k<10;k++){
                    if(arr[k]==0){
                        for(int j=0;j<9;j++){
                            if(gridVal[j][i]==0){
                                gridVal[j][i]=k;
                            }
                        }
                    }
                }
            }

        }
    }
    void solveVertical(){
        for(int i=0;i<9;i++){
            int[] arr = new int[10];
            int t=0;
            for(int j=0;j<9;j++){
                if(gridVal[i][j] !=0 && arr[gridVal[i][j]]==0)t++;
                ++arr[gridVal[i][j]];
            }
            if(t==8){
                for(int k=1;k<10;k++){
                    if(arr[k]==0){
                        for(int j=0;j<9;j++){
                            if(gridVal[i][j]==0){
                                gridVal[j][i]=k;
                            }
                        }
                    }
                }
            }

        }
    }
    void solveBox(){
        for(int a =0;a<3;a++)for(int b=0;b<3;b++){
            int[] arr = new int[10];
            int t=0;
            for(int i=a*3;i<a*3+3;i++)for(int j=b*3;j<b*3+3;j++){
                if(gridVal[i][j] != 0 && arr[gridVal[i][j]]==0)t++;
                ++arr[gridVal[i][j]];
            }
            if(t==8){
                for(int k=1;k<10;k++){
                    if(arr[k]==0){
                        for(int i=a*3;i<a*3+3;i++)for(int j=b*3;j<b*3+3;j++){
                            if(gridVal[i][j]==0){
                                gridVal[i][j]=k;
                            }
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
        loops++;
        if(loops>2500000)return false;
       //Log.d("Recur","In the loop  "+loops);
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

    void writeToScreen(){
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            final Button btn = grid[i][j];
            if(gridVal[i][j]!=0)btn.setText(gridVal[i][j]+"");
            else btn.setText("");
        }
    }

    void clearScreen(){
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            final Button btn = grid[i][j];
            btn.setText("");
            gridVal[i][j]=0;
        }
    }
}
