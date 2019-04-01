package familytree;

import java.util.ArrayList;
import java.util.Arrays;

public class Family {

    private ArrayList<Person> family;
    private static final String male = "male";
    private static final String female = "female";

    public Family() {
       family = new ArrayList<>();
    }

    public boolean male(String name){
        if(personExists(name)){
            if(!find(name).getGender().isEmpty()){
                return false;
            }
            if(isAParent(name)){
                find(name).setGender(male);
                inferGenderOfOtherParent(name);
            }
            find(name).setGender(male);
        }
        else{
            Person person = new Person();
            person.setName(name);
            person.setGender(male);
            family.add(person);
        }
        return true;
    }

    public boolean female(String name){
        if(personExists(name)){
            if(!find(name).getGender().isEmpty()){
                return false;
            }
            if(isAParent(name)){
                find(name).setGender(female);
                inferGenderOfOtherParent(name);
            }
           find(name).setGender(female);
        }
        else{
            Person person = new Person();
            person.setName(name);
            person.setGender(female);
            family.add(person);
        }
        return true;
    }

    public boolean isMale(String name){
        if(personExists(name)){
            if(find(name).getGender().equals(male)){
                return true;
            }
        }
        else {
            family.add(new Person(name));
        }
        return false;
    }

    public boolean isFemale(String name){
        if(personExists(name)){
            if(find(name).getGender().equals(female)){
                return true;
            }
        }
        else {
            family.add(new Person(name));
        }
        return false;
    }

    public boolean setParent(String childName, String parentName){
        Person child = personExists(childName) ? find(childName) : new Person(childName);
        Person parent = personExists(parentName) ? find(parentName) : new Person(parentName);

        if(child.getParents().size()>=2){ return false; }
        if (childName.equals(parentName)){ return false; }
        for(Person childOf : child.getChildren()) {
            if (childOf.getName().equals(parentName)) return false;
        }

        for(Person existingParent : child.getParents()){
            if (isMale(parent.getName()) && isMale(existingParent.getName())) { return false; }
            if (isFemale(parent.getName()) && isFemale(existingParent.getName())) { return false; }
            if (existingParent.getGender().isEmpty() && parent.getGender().equals(male)){
                existingParent.setGender(female); }
            if (existingParent.getGender().isEmpty() && parent.getGender().equals(female)){
                existingParent.setGender(male); }
        }

      if (!personExists(childName)){
          child.getParents().add(parent);
          family.add(child);
      }
      else{ find(childName).getParents().add(parent); }

      if (!personExists(parent.getName())){
          parent.getChildren().add(child);
          family.add(parent);
      }
      else{ find(parent.getName()).getChildren().add(child); }
        return true;
    }

    public ArrayList<Person> getParents(String name){
        if(!personExists(name)){
            return new ArrayList<>();
        }
        return find(name).getParents();
    }

    public ArrayList<Person> getChildren(String name){
        if(!personExists(name)){
            return new ArrayList<>();
        }
        return find(name).getChildren();
    }

    public String getChildrenOf(String name){
        String [] children = new String[find(name).getChildren().size()];
        int i = 0;
        for (Person child : find(name).getChildren()){
            children[i++] =  child.getName();
        }
        Arrays.sort(children);
        return Arrays.toString(children);
    }

    public String getParentsOf(String name){
        String [] parents = new String[find(name).getParents().size()];
        int i = 0;
        for (Person parent : find(name).getParents()){
            parents[i++] =  parent.getName();
        }
        Arrays.sort(parents);
        return Arrays.toString(parents);
    }

    public boolean personExists(String name){
        for (Person p : family){
            if (p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Person find(String name){
        for (Person p : family){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Person> getFamily(){
        return family;
    }

    public boolean isAParent(String name){
        for (Person person : family){
            for(Person parent : person.getParents()){
                if(parent.getName().equals(name)){
                    return true;
                }

            }
        }
        return false;
    }

    public void inferGenderOfOtherParent(String parentName){
        ArrayList<Person> children = find(parentName).getChildren();
        for(Person parent: children.get(0).getParents()){
            if(!parent.getName().equals(parentName)) {
                if (find(parentName).getGender().equals(female)) {
                    find(parent.getName()).setGender(male);
                }
                if (find(parentName).getGender().equals(male)) {
                    find(parent.getName()).setGender(female);
                }
            }
        }

    }

}
