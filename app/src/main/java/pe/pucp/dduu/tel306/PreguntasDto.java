package pe.pucp.dduu.tel306;

public class PreguntasDto {
private int id;
private String questionText;
private String questionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    private  Answer[] answers;



}
