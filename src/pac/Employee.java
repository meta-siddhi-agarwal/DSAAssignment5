package pac;

import java.util.Scanner;

public class Employee {
	double salary;
	int age;
	String name;

	Employee empObject;
    Employee next;
    static Employee head;

	static Scanner scannerObj = new Scanner(System.in);

	Employee(String name, int age, double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;

	}
	public Employee(Employee empObject) {
        this.empObject = empObject;
        this.next = null;
    }

	Employee() {

	}

	public static void main(String[] args) {
		Employee empObj = new Employee();
		int noOfEmp = empObj.getNumberOfEmployees();
		empObj.getEployees(noOfEmp);
		empObj.sortList();
		empObj.printList(head);
	}

	public void printList(Employee headNode){
		Employee temp=headNode;
		while(temp!=null){
			// System.out.println(temp+" ");
			Employee empObj=temp.empObject;
			System.out.println("Name: "+empObj.name+" "+"Age: "+empObj.age+" "+"Salary: "+empObj.salary);
			temp=temp.next;
		}
	}
	public void getEployees(int noOfEmp) {
		Scanner scannerObj = new Scanner(System.in);
		// Scannner scannerObjFor
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

								Employee empObject=new Employee(name,age,salary);
								
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

	public void sortList(){
		Employee temp=head.next;
		while(temp!=null){
			Employee tempSubNode=head;
			while(tempSubNode!=temp){
				Employee empObject=tempSubNode.empObject;
				Employee empObject1=temp.empObject;
				if(empObject.salary<empObject1.salary){
					Employee tempObj=tempSubNode.empObject;
					tempSubNode.empObject=temp.empObject;
					temp.empObject=tempObj;
					// double tempSalary=empObject.salary;
					// empObject.salary=empObject1.salary;
					// empObject1.salary=tempSalary;

				}
				else if(empObject.salary==empObject1.salary){
					if(empObject.age>empObject1.age){
						Employee tempObj=tempSubNode.empObject;
					tempSubNode.empObject=temp.empObject;
					temp.empObject=tempObj;
					// 	System.out.println("hello");
					// 	Employee tempObj=empObject;
					// empObject=empObject1;
					// empObject1=tempObj;
					}

				}

				else{}
				tempSubNode=tempSubNode.next;
			}
			temp=temp.next;
		}
		

	}

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
		}

		else {
			System.out.println("Enter valid no.");
			System.out.println();
			scannerObj.nextLine();
			return getNumberOfEmployees();
		}
	}


	public void  createLinkedList(int nodesNumber, Employee empObject, int empNumber) {
        Employee node=null;
        Employee tempNode;
        if(empNumber==1){
            node=new Employee(empObject);
            head=node;
        }
        else{
            tempNode=head;
            for(int i=1;i<empNumber-1;i++){
                tempNode=tempNode.next;
            }
			node=new Employee(empObject);
            tempNode.next=node;



        }
	}





    

}
