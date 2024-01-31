package dev.lpa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};

        for(Integer i : others){
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo=%d%n", five, (val == 0 ? "<" : ">"), i, val);
        }

        String banana = "banana";
        String[] fruit = {"apple", "banana", "pear", "BANANA"};

        for(String s : fruit){
            int val = banana.compareTo(s);
            System.out.printf("%s %s %s: compareTo=%d%n", banana, (val == 0 ? "==" : (val < 0) ? "<" : ">"), s, val);
        }

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));

        System.out.println("A:" + (int)'A' + " " + "a:"+(int)'a');
        System.out.println("B:"+(int)'B'+ " " + "b:"+(int)'b');
        System.out.println("P:"+(int)'P'+" "+"p:"+(int)'p');

        //When we use the compareTo method on Strings, we're really comparing the integer values of the characters in
        //the strings.

        //The method will compare the first characters, and if they're the same, it next compares the second, and so
        //on, returning the difference between the character's underlying integer values.

        Student tim = new Student("Tim");
        Student [] students = {new Student("Zach"), new Student("Tim"), new Student("Ann")};

        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        System.out.println("result = " + tim.compareTo(new Student("TIM")));

        Comparator<Student> gpaSorter = new StudentGPAComparator();
        Arrays.sort(students, gpaSorter.reversed());
        System.out.println(Arrays.toString(students));
    }

}

class StudentGPAComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2){
        return (o1.gpa + o1.name).compareTo(o2.gpa + o2.name);
    }
}
class Student implements Comparable<Student> {
    String name;
    private int id;
    protected double gpa;
    private static int LAST_ID = 1000;
    private static Random random = new Random();

    public Student(String name) {
        this.name = name;
        //Because LAST_ID is static and there's only 1 in memory, any instance increments that one copy.
        id = LAST_ID++;
        gpa = random.nextDouble(1.0, 4.0);
    }

    @Override
    public String toString() {
        return "%d - %s (%.2f)".formatted(id, name, gpa);
    }

//    @Override
//    public int compareTo(Object o) {
//        Student other = (Student) o;
//        return name.compareTo(other.name);
//    }

    @Override
    public int compareTo(Student o) {
        return Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
    }

    //Sorting and comparing objects, meaning instances of your own classes, is something often done.

    //You use Comparable when something has a natural order, as we saw with student names.

    //Natural order means that your object's compareTo method will return a zero if one object is considered equal to
    //another, or the equals method returns true when the compareTo method returns 0.

}

//Comparable is an interface we have discussed previously.

//For an array, we can call Arrays.sort, and pass it an array, but as it has been stated previously, the elements in
//the array need to implement Comparable.

//Types like String, or primitive wrapper class like Integer or Character are sortable, and this is because they
//implement this interface.

//Below is the interface declaration in Java:

//public interface Comparable<T> {int compareTo(T o);}

//It's a generic type, meaning it's parameterized.

//Any class that implements this interface needs to implement the compareTo method.

//The method above takes one object as an argument, shown above as the letter o, and compares it to the current instance,
//shown below as this.

//Below shows what the results of the compareTo method should mean when implemented:

//  resulting Value             Meaning
//      zero                    0 == this
//      negative value          this < 0
//      positive value          this > 0

//THE COMPARATOR INTERFACE

//The comparator interface is similar to the Comparable interface, and the two can often be confused with each other.

//Its declaration and primary abstract method are shown below, in comparison to Comparable.

//Note that the method names are different: compare vs. compareTo.

//  Comparator                              Comparable

//  public interface Comparator<T> {        public interface Comparable<T> {
//      int compare(T o1, T o2);                int compareTo(T o);
//  }                                       }

//The compare method takes two arguments vs. one for compareTo, meaning it will compare the two arguments to one another,
//and not one object to the instance itself.

//It's common practice to include a Comparator as a nested class.

//Differences between Comparable and Comparator:

//Comparable                                                            Comparator
//int compareTo(T o);                                                   int compare(T o1, T o2);

//Compares the argument with the current instance.                      Compares two arguments of the same type with
//                                                                      each other.
//Called from the instance of the class that implements Comparable.     Called from an instance of Comparator.
//                                                                      Does not require the class itself to implement
//                                                                      Comparator, though you could also implement it
//                                                                      this way.

//Best practice is to have this.compareTo(o)==0 result in this.
//equals(o) being true.

//Arrays.sort(T[] elements) requires T to implement Comparable.         Array.sort(T[] elements, Comparator<T>) does not
//                                                                      require T to implement Comparable.
