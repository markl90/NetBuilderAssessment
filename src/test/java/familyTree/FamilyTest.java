package familyTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    private Family family;

    @BeforeEach
    void setUp() {
        family = new Family();
    }

    @Test
    public void addMaleTest(){
        family.male("Bob");
        assertEquals( 1, family.family.size());
        assertTrue(family.family.get(0).getName().equals("Bob"));
        assertTrue(family.family.get(0).getGender().equals("male"));
        assertTrue(family.isMale("Bob"));
    }

    @Test
    public void addFemaleTest(){
        family.female("Amy");
        assertEquals( 1, family.family.size());
        assertTrue(family.family.get(0).getName().equals("Amy"));
        assertTrue(family.family.get(0).getGender().equals("female"));
        assertTrue(family.isFemale("Amy"));
    }

    @Test
    public void checkMaleWhenPersonDoesNotExist(){
        assertFalse(family.isMale("John"));
    }

    @Test
    public void checkFemaleWhenPersonDoesNotExist(){
        assertFalse(family.isFemale("Amy"));
    }

    @Test
    public void addParent(){
        family.male("Bob");
        family.female("Amy");
        family.setParent("Bob", "Amy");
        Person bob = family.family.get(0);
        assertTrue(bob.getParents().get(0).getName().equals("Amy"));
    }

    @Test
    public void cannotAddTwoFathersTest(){
        family.male("Bob");
        family.male("Dad");
        family.male("Dad2");
        family.setParent("Bob", "Dad");
        assertFalse(family.setParent("Bob", "Dad2"));
        Person bob = family.family.get(0);
        assertTrue(bob.getParents().get(0).getName().equals("Dad"));
        assertTrue(bob.getParents().size()==1);
    }

    @Test
    public void cannotAddMoreThanTwoParentsTest(){
        family.male("Bob");
        family.male("Dad");
        family.female("Mum");
        family.female("Mum2");
        family.setParent("Bob", "Dad");
        family.setParent("Bob", "Mum");
        assertFalse(family.setParent("Bob", "Mum2"));
        Person bob = family.family.get(0);
        assertTrue(bob.getParents().get(0).getName().equals("Dad"));
        assertTrue(bob.getParents().get(1).getName().equals("Mum"));
        assertTrue(bob.getParents().size()==2);
    }

    @Test
    public void firstMentionofNamesInSetParentsMethodAddsPeopleToFamilyTest(){
        family.setParent("Bob", "Dad");
        family.setParent("Bob", "Mum");
        assertTrue(family.family.size()==3);
        assertTrue(family.family.get(0).getName().equals("Bob"));
        assertTrue(family.family.get(1).getName().equals("Dad"));
        assertTrue(family.family.get(2).getName().equals("Mum"));
    }

    @Test
    public void ifGenderIsSetItCannotBeChangedTest(){
        family.male("Bob");
        family.female("Bob");
        assertTrue(family.find("Bob").getGender().equals("male"));
    }

    @Test
    public void nameIsUniqueIdentifierNoTwoAllowedTheSameTest(){
        family.male("Bob");
        family.male("Bob");
        assertFalse(family.male("Bob"));
        assertTrue(family.family.size()==1);
    }

    @Test
    public void firstMentionOfNameInIsMaleMethodAddsPersonToFamilyTest(){
        family.isMale("Barry");
        assertTrue(family.family.size()==1);
        assertTrue(family.family.get(0).getName().equals("Barry"));
    }

    @Test
    public void firstMentionOfNameInIsFemaleMethodAddsPersonToFamilyTest(){
        family.isFemale("Betty");
        assertTrue(family.family.size()==1);
        assertTrue(family.family.get(0).getName().equals("Betty"));
    }

    @Test
    public void ifGenderHasNotBeenSetItCanBeSetTest(){
        family.isFemale("Betty");
        assertTrue(family.find("Betty").getGender().isEmpty());
        family.female("Betty");
        assertTrue(family.find("Betty").getGender().equals("female"));
    }

    @Test
    public void unsetGenderOfaParentIsInferredFromNextParentWithMaleGender(){
        family.male("Bob");
        family.setParent("Bob", "Amy");
        assertTrue(family.find("Amy").getGender().isEmpty());
        family.male("Barry");
        family.setParent("Bob", "Barry");
        assertTrue(family.find("Amy").getGender().equals("female"));
    }

    @Test
    public void unsetGenderOfaParentIsInferredFromNextParentWithFemaleGender(){
        family.male("Bob");
        family.setParent("Bob", "Dad");
        assertTrue(family.find("Dad").getGender().isEmpty());
        family.female("Mum");
        family.setParent("Bob", "Mum");
        assertTrue(family.find("Dad").getGender().equals("male"));
    }

    @Test
    public void callsOfUnsetGenderMethodsWillAlwaysBeFalseTest(){
        assertFalse(family.isFemale("Amy"));
        assertFalse(family.isMale("Amy"));
        assertFalse(family.isFemale("Amy"));
    }

    @Test
    public void unsetChildrenAlwaysReturnsAnEmptyArrayTest(){
        family.male("John");
        family.setParent("John", "Ben");
        family.setParent("John", "Sarah");
        assertTrue(family.getChildren("John").isEmpty());
    }

    @Test
    public void getParentsReturnsParentRelationsTest(){
        family.male("John");
        family.setParent("John", "Ben");
        family.setParent("John", "Sarah");
        assertTrue(family.getParents("John").get(0).getName().equals("Ben"));
        assertTrue(family.getParents("John").get(1).getName().equals("Sarah"));
    }

    @Test
    public void queryParentOrChildRelationDoesNotAddRecordToFamily(){
        family.getChildren("Harry");
        family.getParents("Oscar");
        assertTrue(family.family.isEmpty());
    }

    @Test
    public void cannotBeTheParentOfYourParent(){
        family.male("John");
        family.setParent("John", "Ben");
        assertFalse(family.setParent("Ben", "John"));
        assertTrue(family.family.size()==2);

    }

    @Test
    public void inferGenderOfParentFemale(){
        family.setParent("Frank", "Morgan");
        family.setParent("Frank", "Dylan");
        family.male("Dylan");
        assertTrue(family.find("Morgan").getGender().equals("female"));
    }

    @Test
    public void inferGenderOfParentMale(){
        family.setParent("theChild", "aWoman");
        family.setParent("theChild", "aMan");
        family.female("aWoman");
        assertTrue(family.find("aWoman").getGender().equals("female"));
    }

    @Test
    public void returnParentsStringTest(){
        family.setParent("theChild", "Father Bill");
        family.setParent("theChild", "Mother Milly");
        assertEquals("[Father Bill, Mother Milly]", family.getParentsOf("theChild"));
    }

    @Test
    public void returnChildrenStringTest(){
        family.setParent("Vin", "Harry");
        family.setParent("Dwayne", "Harry");
        family.setParent("Matt", "Harry");
        assertEquals("[Vin, Dwayne, Matt]", family.getChildrenOf("Harry"));
    }

}