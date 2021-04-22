package io.hari.demo.constant;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
public enum QuestionStatus {
    pass(100),
    partial_pass(50),
    time_out(25),
    fail(0);

    int score;
    QuestionStatus(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
