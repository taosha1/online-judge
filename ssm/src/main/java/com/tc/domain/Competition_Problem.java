package com.tc.domain;

import java.io.Serializable;

public class Competition_Problem implements Serializable{
    private Integer id;
    private Problem problem;//1 n
    private Competition competition;//1 1

    @Override
    public String toString() {
        return "Competition_Problem{" +
                "id=" + id +
                ", problem=" + problem +
                ", competition=" + competition +
                '}';
    }

    public Competition_Problem(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
