package familyTree;

import java.util.ArrayList;

public class Person {

    private String name;
    private String gender="";
    private ArrayList<Person> children;
    private ArrayList<Person> parents;

    public Person(String name) {
        this.name = name;
        children = new ArrayList<Person>();
        parents = new ArrayList<Person>();
    }

    public Person(){
        children = new ArrayList<Person>();
        parents = new ArrayList<Person>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public ArrayList<Person> getParents() {
        return parents;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", children=" + children +
                ", parents=" + parents +
                '}';
    }
}
