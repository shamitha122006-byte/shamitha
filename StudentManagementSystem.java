import java.util.*;

class Student {
    private int id;
    private String name;
    private int age;
    private String department;
    private double marks;

    public Student(int id, String name, int age, String department, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "Fail";
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nName: " + name +
                "\nAge: " + age +
                "\nDepartment: " + department +
                "\nMarks: " + marks +
                "\nGrade: " + getGrade();
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        System.out.println("======================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("======================================");

        do {
            displayMenu();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    sortStudents();
                    break;

                case 7:
                    showStatistics();
                    break;

                case 8:
                    System.out.println("Exiting Student Management System...");
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please try again.");
            }

        } while (choice != 8);
    }

    public static void displayMenu() {

        System.out.println("\n----------- MENU -----------");

        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Sort Students by Marks");
        System.out.println("7. Show Statistics");
        System.out.println("8. Exit");

        System.out.println("----------------------------");
    }

    public static void addStudent() {

        System.out.println("\n--- ADD STUDENT ---");

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();

        Student student = new Student(
                id,
                name,
                age,
                department,
                marks
        );

        students.add(student);

        System.out.println("Student Added Successfully!");
    }

    public static void viewStudents() {

        System.out.println("\n--- STUDENT LIST ---");

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {

            System.out.println("--------------------------");

            System.out.println(student);

            System.out.println("--------------------------");
        }
    }

    public static void searchStudent() {

        System.out.println("\n--- SEARCH STUDENT ---");

        System.out.print("Enter Student ID: ");

        int id = scanner.nextInt();

        boolean found = false;

        for (Student student : students) {

            if (student.getId() == id) {

                System.out.println("\nStudent Found!");

                System.out.println(student);

                found = true;

                break;
            }
        }

        if (!found) {

            System.out.println("Student Not Found!");
        }
    }

    public static void updateStudent() {

        System.out.println("\n--- UPDATE STUDENT ---");

        System.out.print("Enter Student ID: ");

        int id = scanner.nextInt();

        scanner.nextLine();

        boolean found = false;

        for (Student student : students) {

            if (student.getId() == id) {

                System.out.print("Enter New Name: ");

                String name = scanner.nextLine();

                System.out.print("Enter New Age: ");

                int age = scanner.nextInt();

                scanner.nextLine();

                System.out.print("Enter New Department: ");

                String department = scanner.nextLine();

                System.out.print("Enter New Marks: ");

                double marks = scanner.nextDouble();

                student.setName(name);

                student.setAge(age);

                student.setDepartment(department);

                student.setMarks(marks);

                System.out.println("Student Updated Successfully!");

                found = true;

                break;
            }
        }

        if (!found) {

            System.out.println("Student Not Found!");
        }
    }

    public static void deleteStudent() {

        System.out.println("\n--- DELETE STUDENT ---");

        System.out.print("Enter Student ID: ");

        int id = scanner.nextInt();

        Iterator<Student> iterator = students.iterator();

        boolean found = false;

        while (iterator.hasNext()) {

            Student student = iterator.next();

            if (student.getId() == id) {

                iterator.remove();

                System.out.println("Student Deleted Successfully!");

                found = true;

                break;
            }
        }

        if (!found) {

            System.out.println("Student Not Found!");
        }
    }

    public static void sortStudents() {

        System.out.println("\n--- SORT STUDENTS BY MARKS ---");

        if (students.isEmpty()) {

            System.out.println("No students available.");

            return;
        }

        Collections.sort(
                students,
                new Comparator<Student>() {

                    public int compare(Student s1, Student s2) {

                        return Double.compare(
                                s2.getMarks(),
                                s1.getMarks()
                        );
                    }
                }
        );

        System.out.println("Students Sorted Successfully!");

        viewStudents();
    }

    public static void showStatistics() {

        System.out.println("\n--- STUDENT STATISTICS ---");

        if (students.isEmpty()) {

            System.out.println("No students available.");

            return;
        }

        double totalMarks = 0;

        double highestMarks = students.get(0).getMarks();

        double lowestMarks = students.get(0).getMarks();

        Student topper = students.get(0);

        for (Student student : students) {

            totalMarks += student.getMarks();

            if (student.getMarks() > highestMarks) {

                highestMarks = student.getMarks();

                topper = student;
            }

            if (student.getMarks() < lowestMarks) {

                lowestMarks = student.getMarks();
            }
        }

        double average = totalMarks / students.size();

        System.out.println("Total Students: " + students.size());

        System.out.println("Average Marks: " + average);

        System.out.println("Highest Marks: " + highestMarks);

        System.out.println("Lowest Marks: " + lowestMarks);

        System.out.println("Topper: " + topper.getName());

        System.out.println("Topper Marks: " + topper.getMarks());
    }
}
