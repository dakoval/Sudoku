package max.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTest {
    MainActivity m=new MainActivity();
    @Test
    public void solveHorizontalCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000000000000000000000000000000000000000000000000000000000000000000000000023456789";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        m.solveHorizontal();
        assertEquals(1,m.gridVal[0][8]);
    }

    @Test
    public void solveHorizontalNotCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000000000000000000000000000000000000000000000000000000000000000000000000033456789";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(false,m.solveHorizontal());
    }

    @Test
    public void solveVerticalCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000000000200000000300000000400000000500000000600000000700000000800000000900000000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        m.solveVertical();
        assertEquals(1,m.gridVal[0][0]);
    }

    @Test
    public void solveVerticalNotCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "200000000200000000300000000400000000500000000600000000700000000800000000900000000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(false,m.solveVertical());
    }

    @Test
    public void solveBoxCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "123000000456000000780000000000000000000000000000000000000000000000000000000000000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        m.solveBox();
        assertEquals(9,m.gridVal[2][2]);
    }

    @Test
    public void solveBoxNotCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "123000000456000000781000000000000000000000000000000000000000000000000000000000000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(false,m.solveBox());
    }

    @Test
    public void solveRecurCorrect0() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solveRecur(0,0));
    }

    @Test
    public void solveRecurCorrect1() throws Exception {
        m.gridVal=new int[9][9];
        String s = "010020300004005060070000008006900070000100002030048000500006040000800106008000000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solveRecur(0,0));
    }

    @Test
    public void solveRecurCorrect2() throws Exception {
        m.gridVal=new int[9][9];
        String s = "002008050000040070480072000008000031600080005570000600000960048090020000030800900";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solveRecur(0,0));
    }

    @Test
    public void solveRecurCorrect3() throws Exception {
        m.gridVal=new int[9][9];
        String s = "102004070000902800009003004000240006000107000400068000200800700007501000080400109";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solveRecur(0,0));
    }

    @Test
    public void solveRecurCorrect4() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000398000050010060000000000800000009120030045700000008000000000040020010000769000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solveRecur(0,0));
    }

    @Test
    public void solveRecurCorrect5() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000427000060090080000000000900000008120030045500000007000000000040060030000715004";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solveRecur(0,0));
    }

    @Test
    public void solveRecurNotCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "400427000060090080000000000900000008120030045500000007000000000040060030000715000";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(false,m.solveRecur(0,0));
    }

    @Test
    public void whiteBoxTestCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "000427000060090080000000000900000008120030045500000007000000000040060030000715004";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(true,m.solve());
    }

    @Test
    public void whiteBoxTestNotCorrect() throws Exception {
        m.gridVal=new int[9][9];
        String s = "102014070000902800009003004000240006000107000400068000200800700007501000080400109";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        assertEquals(false,m.solve());
    }

    @Test
    public void performanceTest() throws Exception{
        m.gridVal=new int[9][9];
       String s =    "800000000003600000070090200050007000000045700000100030001000069008500010090000400";
        for(int i=0;i<9;i++)for(int j=0;j<9;j++){
            m.gridVal[j][i]=Integer.parseInt(s.charAt(i*9+j)+"");
        }
        long startTime = System.currentTimeMillis();
        boolean t = m.solveRecur(0,0);
        long endTime = System.currentTimeMillis();
        assertEquals(true, t&&(endTime-startTime)/1000<5);
    }
}
