package model;

import java.io.Serializable;
import java.sql.Date;
/**
 *
 * @author dolong
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idGame;
    private static int idStatic=0;
    private Account player1;
    private Account player2;
    private String status;
    private Date date;
    private int[][] debai;

    public Game() {
        idStatic++;
        idGame = idStatic;
    }

    public Account getPlayer1() {
        return player1;
    }

    public int getId() {
        return idGame;
    }

    public void setId(int id) {
        this.idGame = id;
    }

    public void setPlayer1(Account player1) {
        this.player1 = player1;
    }

    public Account getPlayer2() {
        return player2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPlayer2(Account player2) {
        this.player2 = player2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int[][] getDebai() {
        return debai;           
    }

    public void setDebai(int m, int n) {
        this.debai = createMatrix(m, n);
    }

    @Override
    public boolean equals(Object obj) {
        Game game = (Game)obj;
        if (this.idGame == game.getId()) {
            return true;
        }
        return false;
    }
    public Object[] toObjects(int stt) {
        return new Object[]{stt, player1.getName(), player2.getName(), status,date};
    }
    public int[][] createMatrix(int m, int n) {
        int[][] arr = new int[m][n];
        int N = m * n;
        int[] b = new int[m * n];
        int[] c = new int[m * n];

        int i;
        for (i = 0; i < m * n; ++i) {
            b[i] = i + 1;
            if (b[i] > N / 2) {
                b[i] -= N / 2;
            }
            c[i] = (int) (Math.random() * 1000000.0D);
        }
        int j;
        for (i = 0; i < N - 1; ++i) {
            for (j = i + 1; j < N; ++j) {
                if (c[i] > c[j]) {
                    int tmp = b[i];
                    b[i] = b[j];
                    b[j] = tmp;
                    tmp = c[i];
                    c[i] = c[j];
                    c[j] = tmp;
                }
            }
        }
        N = 0;

        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                arr[i][j] = b[N++];
            }
        }
        return arr;
    }

}
