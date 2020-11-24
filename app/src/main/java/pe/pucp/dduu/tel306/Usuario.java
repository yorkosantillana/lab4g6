package pe.pucp.dduu.tel306;

import java.util.List;

public class Usuario {

    private int id;
    private String name;
    private String email;
    private String password;
    private String token;
    private String createdAt;
    private String updatedAt;
    private String questions;
    private List<Answer> answers;
    //private String answers;


    public void Usuario (int id,String name,String email,String password,String token,String createdAt,String updatedAt,String questions, List<Answer> answers){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setToken(token);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.setQuestions(questions);
        this.setAnswers(answers);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
