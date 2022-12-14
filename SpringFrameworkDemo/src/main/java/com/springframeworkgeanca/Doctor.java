package com.springframeworkgeanca;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(scopeName = "prototype")
public class Doctor implements Staff , BeanNameAware {

    private String qualification;

    public String getQualification() {
        return qualification;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "qualification='" + qualification + '\'' +
                '}';
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void assist(){
        System.out.println("doctor assisting");
    }

    @Override
    public void setBeanName(String name){
        System.out.println("Set Bean name method is called");
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println("Post construct method is called");
    }

}
