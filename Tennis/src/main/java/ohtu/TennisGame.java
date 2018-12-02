package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    private String winText = "Win for ";
    private String advantageText = "Advantage ";
    private String[] scoreTexts = {"Love", "Fifteen", "Thirty", "Forty"};
    private String deuceText = "Deuce";
    private int deuceScore = 4;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == this.player1Name) {
            m_score1 += 1;
        } else if (playerName == this.player2Name) {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String scoreText = "";
        if ((m_score1>=deuceScore || m_score2>=deuceScore) && (m_score1 != m_score2)){
            scoreText = winOrAdvantage();
        } else {
            scoreText = getScoreText();
        }
        return scoreText;
    }

    public String winOrAdvantage() {
        String playerWithAdvantage = player1Name;
        if (m_score2 > m_score1) {
            playerWithAdvantage = player2Name;
        }
        String status = winText;
        int minusResult = Math.abs(m_score1-m_score2);
        if (minusResult==1) {
            status = advantageText;
        } 
        return status + playerWithAdvantage;
    }

    public String getScoreText() {
        if (m_score1 == deuceScore && m_score2 == deuceScore) {
            return deuceText;
        }
        String scoreText = scoreTexts[m_score1] + "-";
        if (m_score1 == m_score2) {
            return scoreText + "All";
        }
        return scoreText + scoreTexts[m_score2];
    }
}