import java.util.Scanner;

import structure5.Association;

public class Question {
    /**
     * Uses scanner to create a MyVector that represents the information in a phonebook
     * @pre a phonebook.txt is attached to Question when it executes
     * @post returns MyVector that represents the phonebook info
     * @return MyVector that represents the information in a phonebook
     * @bigO
     */
    public static MyVector<Student> createList() {
        Scanner sc = new Scanner(System.in);
        MyVector<Student> returned = new MyVector<Student>(); 
        while(sc.hasNextLine()) {
            returned.add(new Student(
                sc.nextLine(),
                sc.nextLine(),
                sc.nextLong(),
                sc.nextInt(),
                sc.nextLong()
            ));
            sc.nextLine();
            sc.nextLine(); //skip dashes
        }
        sc.close();
        return returned;
    }
    /**
     * sorts students based on their name
     * @param list MyVector to be sorted
     * @pre list is not null
     * @post list is now sorted based on students' name
     */
    public static void sortByName(MyVector<Student> list) {
        assert list != null: "list is null";
        //using lamda expression
        list.sort((Student s1, Student s2) -> {
            return s1.getName().compareTo(s2.getName());
        }
        );
    }
    /**
     * sorts students based on their address
     * @param list MyVector to be sorted
     * @pre list is not null
     * @post list is now sorted based on students' address
     */
    public static void sortByAddress(MyVector<Student> list) {
        assert list != null: "list is null";
        //using lamda expression
        list.sort((Student s1, Student s2) -> {
            return s1.getAddress().compareTo(s2.getAddress());
        }
        );
    }
    /**
     * sorts students based on their PO box number
     * @param list MyVector to be sorted
     * @pre list is not null
     * @post list is now sorted based on students' PO box number
     */
    public static void sortByBoxNum(MyVector<Student> list) {
        assert list != null: "list is null";
        //using lamda expression
        list.sort((Student s1, Student s2) -> {
            return s1.getBoxNum() - s2.getBoxNum();
        }
        );
    }
    /**
     * sorts students based on the amount of vowels in their name
     * @param list MyVector to be sorted
     * @pre list is not null
     * @post list is now sorted based on the amount of vowels in students' name
     */
    public static void sortByNumOfVowels(MyVector<Student> list) {
        assert list != null: "list is null";
        //lamda
        list.sort((Student s1, Student s2) -> {
            int vowels1 = 0;
            for(int i = 0; i < s1.getName().length(); i++) {
                char examined = s1.getName().charAt(i);
                if(examined == 'a' || examined == 'o' || examined == 'i' || examined == 'u' || examined == 'e') {
                    vowels1++;
                }
            }
            int vowels2 = 0;
            for(int i = 0; i < s2.getName().length(); i++) {
                char examined = s2.getName().charAt(i);
                if(examined == 'a' || examined == 'o' || examined == 'i' || examined == 'u' || examined == 'e') {
                    vowels2++;
                }
            }
            return vowels1-vowels2;
        });

    }
    /**
     * Literally just the code to answer Question four, which is to print out the names of the people who live in
     * the most popular address
     * @param list MyVector of students to get info of addresses from
     * @pre list is not null
     * @post prints out the names of ppl who live in the most popular addy
     */
    public static void QuestionFour(MyVector<Student> list) {
        assert list != null: "list is null";
        sortByAddress(list);
        MyVector<Association<Integer, Integer>> sameAddys = new MyVector<Association<Integer, Integer>>();
        //first integer is index of where to start, second integer is how many students at that address
        sameAddys.add(new Association<Integer, Integer>(0,1)); //creating first association with first address
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i).getAddress().equals("UNKNOWN")) {
                //do nothing, just iterate over
            }
            else if(list.get(sameAddys.getLast().getKey()).getAddress().equals(list.get(i).getAddress())) {
                sameAddys.getLast().setValue(sameAddys.getLast().getValue()+1); //basically value++
            }
            else {
                sameAddys.add(new Association<Integer, Integer>(i,1));
            } 
        }
        //sort associations
        SortAssociations(sameAddys);
        System.out.println("These students live at the most occupied address, which is: " + list.get(sameAddys.getLast().getKey()).getAddress());
        for(int i = 0; i < sameAddys.getLast().getValue(); i++) {
            System.out.println("\t" + list.get(sameAddys.getLast().getKey() + i).getName());
        }
    }
    /**
     * sorts Associations based on their value
     * @param vector vector holding all of the associations
     * @pre vector is not null
     * @post vector is now sorted by association's values
     */
    public static void SortAssociations(MyVector<Association<Integer, Integer>> vector) {
        assert vector != null: "association vector is null";
        vector.sort((Association<Integer, Integer> a, Association<Integer, Integer> b) -> {
            return a.getValue() - b.getValue();
        });
    } 
    /**
     * 
     * @param list MyVector of students to get info of addresses from
     * @pre list is not null
     * @post prints out the names of ppl who live in the most popular addy
     */
    public static void QuestionFive(MyVector<Student> list) {
        assert list != null: "list is null";
        sortByCellNumber(list);
        MyVector<Association<Integer, Integer>> sameAreaCode = new MyVector<Association<Integer, Integer>>();
        //first integer is index of where to start, second integer is how many students at that address
        int startIndex = 0;
        while(list.get(startIndex).getCellNum() == -1) {
            startIndex++;
        }
        sameAreaCode.add(new Association<Integer, Integer>(startIndex, 1) );
        for(int i = startIndex + 1; i < list.size(); i++) {
            if(list.get(sameAreaCode.getLast().getKey()).getAreaCode() == list.get(i).getAreaCode()) {
                sameAreaCode.getLast().setValue(sameAreaCode.getLast().getValue()+1); //basically value++
            }
            else {
                sameAreaCode.add(new Association<Integer, Integer>(i,1));
            } 
        }
        //sort associations
        SortAssociations(sameAreaCode);
        System.out.println("Ten most common area codes: ");
        for(int i = sameAreaCode.size()-1; i > sameAreaCode.size()-11; i--) {
            System.out.println("\t" + list.get(sameAreaCode.get(i).getKey()).getAreaCode() + ": " + sameAreaCode.get(i).getValue());
        }
    }
    /**
     * sorts students based on their cellphone number
     * @param list MyVector to be sorted
     * @pre list is not null
     * @post list is now sorted based on their cellphone number
     */
    public static void sortByCellNumber(MyVector<Student> list) {
        list.sort((Student s1, Student s2) -> {
            if(s1.getCellNum() > s2.getCampusNum()) {
                return 1;
            }
            else if(s1.getCellNum() == s2.getCellNum()) {
                return 0;
            }
            else {
                return -1;
            }
        });
    }

    //java Question<file.txt pretends as if file is the keyboard so you can use scanner
    public static void main(String[] args) {
        MyVector<Student> students = createList();
        //question 1: idk whether to do the first one in the unsorted or the first in the sorted
        System.out.println("Question 1:");

        System.out.println("\t" + "The first student's name in the unsorted phonebook is: " + students.get(0).getName());
        sortByName(students);
        System.out.println("\t" + "The first student's name in the sorted phonebook is: " + students.get(0).getName());
        
        //question 2
        sortByBoxNum(students);

        System.out.println("Question 2:");
        System.out.println("\t" + students.get(0).getName() + " has the smallest SU box number.");
        System.out.println("\t" + students.get(students.size()-1).getName() + " has the largest SU box number.");
        
        //question 3
        sortByNumOfVowels(students);

        System.out.println("Question 3:");
        System.out.println("\t" + students.get(students.size()-1).getName() + " has the most vowels in their name.");

        //question 4
        System.out.println("Question 4:");
        QuestionFour(students);

        //question 5
        System.out.println("Question 5:");
        QuestionFive(students);

    }
}