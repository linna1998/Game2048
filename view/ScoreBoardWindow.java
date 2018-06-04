package com.game.view;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class ScoreBoardWindow extends JFrame {

    private JLabel ltitle;
    private JButton lexit;
    private ScoreBoard scoreBoard;

    public ScoreBoardWindow() {
        this.setLayout(null);
    }

    public void initView() throws IOException {

        ltitle = new JLabel("Score Board", JLabel.CENTER);
        ltitle.setFont(new Font("", Font.BOLD, 25));
        ltitle.setForeground(new Color(0x776e65));
        ltitle.setBounds(0, 10, 200, 60);
        ltitle.setVisible(true);

        lexit = new JButton("Exit");
        lexit.setFont(new Font("", Font.BOLD, 22));
        lexit.setForeground(new Color(0x776e65));
        lexit.setBounds(260, 20, 120, 45);

        //排行榜面板组件
        scoreBoard = new ScoreBoard();
        scoreBoard.setPreferredSize(new Dimension(400, 400));
        scoreBoard.setBackground(new Color(0xeee4da));
        scoreBoard.setBounds(10, 100, 400, 400);
        scoreBoard.setFocusable(true);

        this.add(ltitle);
        this.add(lexit);
        this.add(scoreBoard);
        this.setVisible(true);
    }

    public class ScoreBoard extends JPanel {
        private int userNum;
        private String userName[];
        private String userScore[];
        private JLabel[][] lboard;

        public ScoreBoard() throws IOException {
            setLayout(new GridLayout(11, 2));

            readScores();

            lboard = new JLabel[11][3];

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 3; j++) {
                    lboard[i][j] = new JLabel("", JLabel.CENTER);
                }
            }
            lboard[0][0].setText("Rank");
            lboard[0][1].setText("User Name");
            lboard[0][2].setText("Score");
            for (int i = 1; i <= userNum; i++) {
                lboard[i][0].setText(String.valueOf(i));
                lboard[i][1].setText(userName[i - 1]);
                lboard[i][2].setText(userScore[i - 1]);
            }
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 3; j++) {
                    lboard[i][j].setFont(new Font("", Font.BOLD, 20));
                    lboard[i][j].setForeground(new Color(0x776e65));
                    lboard[i][j].setVisible(true);
                    add(lboard[i][j]);
                }
            }
        }

        class User {
            private String name;
            private int score;

            User() {
            }

            User(String inName, int inScore) {
                name = inName;
                score = inScore;
            }

        }

        /*  Read in the scores in Scores.txt
         *  select top 10 of the scores
         *  show the top 10 scores on the screen
         *  write back the top 10 scores
         */
        private void readScores() throws IOException {
            userNum = 0;
            Vector<User> readUser;

            String charset = "GB2312";
            String fileName = "Scores.txt";

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileName), charset));
            String line;

            readUser = new Vector<User> ();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0) continue;
                int idx = line.indexOf("\t");
                String name = line.substring(0, idx);
                int score = Integer.parseInt(line.substring(idx + 1));
                User tempUser = new User(name, score);
               readUser.add(tempUser);
            }

            // TODO
            // 对readUser排序，选出前十个（若干个）放到userName, userScore数组里面
            //  设置userNum的值
            // 把前十个（若干个）写回到Scores.txt里面去
            userNum = 0;
            userName = new String[10];
            userScore = new String[10];

            reader.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ScoreBoardWindow test = new ScoreBoardWindow();
        test.initView();
        test.setTitle("2048 Score Board");
        test.getContentPane().setPreferredSize(new Dimension(400, 500));
        //JFrame直接调用setBackground设置背景色不生效
        test.getContentPane().setBackground(new Color(0xfaf8ef));
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setResizable(false); //去掉最大化按钮
        test.pack();    //获得最佳大小
        test.setVisible(true);
    }
}
