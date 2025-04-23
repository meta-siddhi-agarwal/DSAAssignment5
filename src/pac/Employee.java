package pac;

import java.util.Scanner;

public class Employee {

    double salary;
    int age;
    String name;

    Employee empObject;
    Employee next;
    static Employee head;

    /**
     * constructor for initializing variables of the class
     * @param name->denotes name of the employee
     * @param age->denotes age of the employee
     * @param salary->denotes salary of the employee
     */
    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    /**
     * used for creating objects for linked list
     * @param empObject->node of the linked list
     */
    public Employee(Employee empObject) {
        this.empObject = empObject;
        this.next = null;
    }

    /**
     * for calling non static methods
     */
    Employee() {
    }

    public static void main(String[] args) {
        Employee empObj = new Employee();
        int noOfEmp = empObj.getNumberOfEmployees();
        empObj.getEployees(noOfEmp);
        empObj.sortList();
        empObj.printList(head);
    }

    /**
     * will print the sorted list
     * @param headNode->denotes head node
     */
    public void printList(Employee headNode) {
        Employee tempNode = headNode;
        while (tempNode != null) {
            Employee empObj = tempNode.empObject;
            System.out.println("Name: " + empObj.name + " " + "Age: " + empObj.age + " " + "Salary: " + empObj.salary);
            tempNode = tempNode.next;
        }
    }

    /**
     * will get employees from user
     * @param noOfEmp->no. of employees 
     */
    public void getEployees(int noOfEmp) {
        Scanner scannerObj = new Scanner(System.in);
        Scanner scannerObjForString = new Scanner(System.in);
        int countOfEmp = 1;

        while (noOfEmp > 0) {
            System.out.println("Enter name of employee " + countOfEmp);
            String name = scannerObjForString.nextLine();

            if (name.length() == 0) {
                System.out.println("Please enter name of the employee ");
                noOfEmp++;
                countOfEmp--;
            } else {
                System.out.println("Enter age of employee " + countOfEmp);
                if (scannerObj.hasNextInt()) {
                    int age = scannerObj.nextInt();
                    if (age <= 0) {
                        System.out.println("Please enter valid age");
                        noOfEmp++;
                        countOfEmp--;

                    } else {
                        System.out.println("Enter salary of employee " + countOfEmp);
                        if (scannerObj.hasNextDouble()) {
                            double salary = scannerObj.nextDouble();

                            if (salary <= 0) {
                                System.out.println("Please enter valid salary");
                                noOfEmp++;
                                countOfEmp--;

                            } else {
                                // create linked list
                                Employee empObject = new Employee(name, age, salary);

                                empObject.createLinkedList(noOfEmp, empObject, countOfEmp);
                            }

                        } else {
                            System.out.println("Enter valid input");
                            System.out.println();
                            scannerObj.nextLine();
                            noOfEmp++;
                            countOfEmp--;
                        }
                    }
                } else {
                    System.out.println("Please enter valid age");
                    System.out.println();
                    scannerObj.nextLine();
                    noOfEmp++;
                    countOfEmp--;
                }
            }
            noOfEmp--;
            countOfEmp++;
        }
    }

    /**
     * will perform insertion sort
     */
    public void sortList() {
        Employee temp = head.next;

        while (temp != null) {
            Employee tempSubNode = head;

            while (tempSubNode != temp) {
                Employee empObject = tempSubNode.empObject;
                Employee empObject1 = temp.empObject;
                if (empObject.salary < empObject1.salary) {
                    Employee tempObj = tempSubNode.empObject;
                    tempSubNode.empObject = temp.empObject;
                    temp.empObject = tempObj;
                } else if (empObject.salary == empObject1.salary) {
                    if (empObject.age > empObject1.age) {
                        Employee tempObj = tempSubNode.empObject;
                        tempSubNode.empObject = temp.empObject;
                        temp.empObject = tempObj;
                    }
                } else {
                }
                tempSubNode = tempSubNode.next;
            }
            temp = temp.next;
        }
    }

    /**
     * will get no. employees from user 
     * @return no. employees 
     */
    public int getNumberOfEmployees() {
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("Enter no. of employees");
        if (scannerObj.hasNextInt()) {
            int nodesNumber = scannerObj.nextInt();
            if (nodesNumber <= 0) {
                System.out.println("Enter valid no. of employees");
                System.out.println();
                return getNumberOfEmployees();
            } else {
                return nodesNumber;
            }
        } else {
            System.out.println("Enter valid no.");
            System.out.println();
            scannerObj.nextLine();
            return getNumberOfEmployees();
        }
    }

    /**
     * will create a linked list
     * @param nodesNumber->no. of employees
     * @param empObject->denote obj which is inserted in node
     * @param empNumber->no. of employees
     */
    public void createLinkedList(int nodesNumber, Employee empObject, int empNumber) {
        Employee node = null;
        Employee tempNode;
        if (empNumber == 1) {
            node = new Employee(empObject);
            head = node;

        } else {
            tempNode = head;
            for (int i = 1; i < empNumber - 1; i++) {
                tempNode = tempNode.next;
            }
            node = new Employee(empObject);
            tempNode.next = node;
        }
    }
}
