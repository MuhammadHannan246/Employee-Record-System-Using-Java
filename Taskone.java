package Employee;
import java.util.ArrayList;
import java.util.Scanner;

public class Taskone {

	public static void main(String[] args) {


		LinkedList employeeList = new LinkedList();
		int selection = 1;
		System.out.print("\n Employee Record System");
		Scanner sc = new Scanner(System.in);
		while(selection != 0) {
			System.out.print("\n \t ** Enter Task To Perform **");
			System.out.print("\n 1. Add Employee");
			System.out.print("\n 2. Delete Employee");
			System.out.print("\n 3. Search Employee by Name or ID");
			System.out.print("\n 4. Show Employee List by Name or ID");
			System.out.print("\n 0. Exit");
			System.out.print("\n \n Selection = ");
			selection = sc.nextInt();
			workOnSelection(sc,selection,employeeList);
		}	
	}

	private static void workOnSelection(Scanner sc,int selection,LinkedList list) {
		Employee emp = new Employee();
		if(selection == 0){
			System.out.print("\n Program Terminated.");
		}else  if(selection == 1) {
			list.insert(list, emp.create());
		}else if(selection == 2) {
			System.out.print("Enter Index to Delete : ");
			list.deleteByKey(list, sc.next());
		}else if(selection == 3) {
			System.out.print("Enter Search Keyword : ");
			list.search(list, sc.next());
		}else if(selection == 4) {
			list.printList(list);
		}else {
			System.out.print("\n Select Appropriate Value.");
		}
	}

}

class Employee{
	String firstName;
	String lastName;
	String joiningDate;
	String positionTitle;
	int companyId;
	Gender gender;
	String dateOfBirth;
	String address;
	String phoneNumber;
	String emailAddress;
	EmployeeStatus employeeStatus;
	
	public String getName() {
	    return firstName + " " + lastName;
	}
	
	public Employee create() {
		Scanner sc = new Scanner(System.in);
		Employee newEmp = new Employee();
		System.out.print("\n Enter First Name: ");
		newEmp.firstName = sc.next();
		System.out.print("\n Enter Last Name: ");
		newEmp.lastName = sc.next();
		System.out.print("\n Enter Joining Date: ");
		newEmp.joiningDate = sc.next();
		System.out.print("\n Enter Position: ");
		newEmp.positionTitle = sc.next();
		System.out.print("\n Enter Company ID: ");
		newEmp.companyId = sc.nextInt();
		newEmp.gender = getGender(sc);
		System.out.print("\n Enter Date of Birth: ");
		newEmp.dateOfBirth = sc.next();
		System.out.print("\n Enter Address: ");
		newEmp.address = sc.next();
		System.out.print("\n Enter Mobile: ");
		newEmp.phoneNumber = sc.next();
		System.out.print("\n Enter Email: ");
		newEmp.emailAddress = sc.next();
		newEmp.employeeStatus = getEmpStatus(sc);
		return newEmp;
	}
	
	public Gender getGender(Scanner sc) {
		System.out.print("\n Enter Gender(M/F): ");
		char val = sc.next().toLowerCase().charAt(0);
		if(val == 'm') {
			return Gender.Male;
		}else if(val == 'f') {
			return Gender.Female;
		}else {
			System.out.print("\n Invalid Gender Enter Again");
			return getGender(sc);
		}
	}
	
	public EmployeeStatus getEmpStatus(Scanner sc) {
		System.out.print("\n Enter Employee Status(Contract/Permanant): ");
		String val = sc.next().toLowerCase();
		if(val.equals("permenant")) {
			return EmployeeStatus.Permanant;
		}else if(val.equals("contract")) {
			return EmployeeStatus.Contract;
		}else {
			System.out.print("\n Invalid Status Enter Again.");
			return getEmpStatus(sc);
		}
	}
}

enum Gender{
	Male,
	Female,	
}

enum EmployeeStatus{
	Permanant,
	Contract,	
}


class LinkedList{
	Node head; 

    static class Node {
    	Employee data;
		Node next;

        Node(Employee d){
			data = d;
			next = null;
		}
	}

	public static LinkedList insert(LinkedList list, Employee data){
		Node new_node = new Node(data);
		new_node.next = null;
		if (list.head == null) {
			list.head = new_node;
		}else {
			Node last = list.head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = new_node;
		}
		return list;
	}

	public static void printList(LinkedList list){
		Node currNode = list.head;
		System.out.println("\n ID \t Name \t\t\t Gender \t Email Address \t\t Status \t Position ");
		while (currNode != null) {
			Employee emp = currNode.data;
			System.out.println("\n "+emp.companyId+" \t "+emp.getName()+" \t\t "+emp.gender+" \t\t "+emp.emailAddress+" \t "+emp.employeeStatus+" \t "+ emp.positionTitle);
			currNode = currNode.next;
		}    			
		System.out.println();
	}

    public static LinkedList deleteByKey(LinkedList list, String key){
		Node currNode = list.head, prev = null;
		if (currNode != null && currNode.data.getName() == key) {
			list.head = currNode.next;
			System.out.println(key + " found and deleted");
			return list;
		}
		while (currNode != null && currNode.data.getName() == key) {
			prev = currNode;
			currNode = currNode.next;
		}
		if (currNode != null) {
			prev.next = currNode.next;
			System.out.println(key + " found and deleted");
		}

		if (currNode == null) {
			System.out.println(key + " not found");
		}
		return list;
	}

    public static void printSingle(Employee emp){
    	System.out.println("\n ID \t Name \t\t\t Gender \t Email Address \t\t Status \t Position ");
    	System.out.println("\n "+emp.companyId+" \t "+emp.getName()+" \t\t "+emp.gender+" \t\t "+emp.emailAddress+" \t "+emp.employeeStatus+" \t "+ emp.positionTitle);
		System.out.println();
	}

    public static LinkedList search(LinkedList list, String name){
		Node currNode = list.head, prev = null;
		if (currNode != null && (currNode.data.firstName == name || currNode.data.lastName == name)) {
			printSingle(currNode.data);
			return list;
		}
		while (currNode != null && (currNode.data.firstName == name || currNode.data.lastName == name)) {
			prev = currNode;
			currNode = currNode.next;
		}
		if (currNode != null) {
            printSingle(currNode.data);
		}
		if (currNode == null) {
			System.out.println(name + " not found");
		}
		return list;
	}
}