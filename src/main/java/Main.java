import familytree.Family;
import stringreplacer.StringReplacer;

public class Main {

    public static void main(String[] args) {

        //Assessment Task 1
        //Tested in "StringReplacerTest" but have included a main method.
        StringReplacer replacer = new StringReplacer();
        System.out.println(replacer.replaceLetterWithPosition("This NETbuilder assessment is way too easy."));

        //Assessment Task 2
        //All rules set in the assessment are tested in "FamilyTest".
        //Have included the code example that is in the assessment in a main method here to show that the behaviour matches.
        Family family = new Family();
        System.out.println(family.setParent("Frank", "Morgan"));
        System.out.println(family.setParent("Frank", "Dylan"));
        System.out.println(family.male("Dylan"));
        System.out.println(family.setParent("Joy", "Frank"));
        System.out.println(family.male("Frank"));
        System.out.println(family.male("Morgan"));
        System.out.println(family.setParent("July", "Morgan"));
        System.out.println(family.isMale("Joy") || family.isFemale("Joy"));
        System.out.println(family.getChildrenOf("Morgan"));
        System.out.println(family.setParent("Jennifer", "Morgan"));
        System.out.println(family.getChildrenOf("Morgan"));
        System.out.println(family.getChildrenOf("Dylan"));
        System.out.println(family.getParentsOf("Frank"));
        System.out.println(family.setParent("Morgan", "Frank"));

    }
}
