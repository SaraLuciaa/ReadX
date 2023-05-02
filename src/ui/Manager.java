package ui;
import java.util.Calendar;
import java.util.Scanner;
import model.Controller;

public class Manager {
    private Controller readX;;

    public Manager(){
        this.readX = new Controller();
    }

    public void menu(){
		Scanner l = new Scanner(System.in);
        boolean status = true;	
		while(status){
			System.out.println("Choose the option:\n2.Manage bibliographic products\n9.Exit");
			int opt = l.nextInt();
			switch(opt){
				case 2:
					menuBP();
					break;
				case 9: 
					System.out.println(readX.toString());					
					status = false;
					break;
				default:
					System.out.println("Input invalid. Try again.");
					break;
			}
		}
    }

	public void menuBP(){
		boolean status = true;
		Scanner l = new Scanner(System.in);
		while(status){
			System.out.println("Choose the option:\n1.Add bibliographic product\n2.Update bibliographic product\n3.Remove bibliographic product\n9.Back to main menu");
			int opt = l.nextInt();	
			switch(opt){
				case 1:
					createBP();
					break;
				case 2: 
					updateBP();	
					break;
				case 3:
					removeBP();
					break;
				case 9: 
					status = false;
					break;
				default:
					System.out.println("Input invalid. Try again.");
					break;
			}
		}
	}

	public void createupdateBP(int type, int func, String id){
		Scanner l = new Scanner(System.in);
		System.out.print("Name: ");
		String name = l.nextLine();
		System.out.print("URL: ");
		String url = l.nextLine();
		System.out.print("Pages: ");
		int pages = l.nextInt();
		l.nextLine();
		System.out.print("Date of publication:\n	Day: ");
		int dayPublication = l.nextInt();
		System.out.print("	Month: ");
		int monthPublication = l.nextInt();
		System.out.print("	Year: ");
		int yearPublication = l.nextInt();
		Calendar datePublication = Calendar.getInstance();
		datePublication.set(dayPublication, monthPublication-1, yearPublication);
		System.out.print("Value: ");
		double value = l.nextDouble();
		System.out.print("Number of pages read: ");
		int pagesRead = l.nextInt();
		l.nextLine();
		if(type==1){
			System.out.print("Review: ");
			String review = l.nextLine();
			System.out.print("Number of copies sold: ");
			int copiesSold = l.nextInt();
			l.nextLine();
			System.out.print("Genre:\n");
			for(int i=0; i<3; i++){
				System.out.println((i+1)+ ". " + readX.showGenre(i));
			}
			int genre = l.nextInt();
			if(func==1){
				while(genre<1&&genre>3){
					System.out.println("Input invalid. Try again");
					genre = l.nextInt();
				}
				System.out.println(readX.createBP(id, name, url, pages, datePublication, value, pagesRead, review, copiesSold, genre));
			} else {
				while((genre<1&&genre>3)||genre!=-1){
					System.out.println("Input invalid. Try again");
					genre = l.nextInt();
				}
				System.out.println(readX.updateBP(id, name, url, pages, datePublication, value, pagesRead, review, copiesSold, genre));
			}
		} else {
			System.out.print("Category: ");
			for(int i=0; i<3; i++){
				System.out.println((i+1) + readX.showCategory(i));
			}
			int category = l.nextInt();
			while(category<1|category>3){
				System.out.println("Input invalid. Try again");
				category = l.nextInt();
			}
			l.nextLine();
			System.out.print("Periodicity of emission: ");
			String periodicityEmission = l.nextLine();
			System.out.println("Number of active subscriptions: ");
			int activeSubscriptions = l.nextInt();
			if(func==1){
				System.out.println(readX.createBP(id, name, url, pages, datePublication, value, pagesRead, category, periodicityEmission, activeSubscriptions));
			} else {
				System.out.println(readX.updateBP(id, name, url, pages, datePublication, value, pagesRead, category, periodicityEmission, activeSubscriptions));
			}
		}
	}
	
	public void createBP(){
		Scanner l = new Scanner(System.in);
		System.out.println("Enter the number of the type of bibliographic product:    1.Book    2.Magazine");
		int type = l.nextInt();
		while(type!=1&&type!=2){
		System.out.println("Input invalid. Try again");
			type = l.nextInt();
		}
		l.nextLine();
		System.out.print("Id: ");
		String id = l.nextLine();
		createupdateBP(type, 1, id);
	}

	public void updateBP(){
		Scanner l = new Scanner(System.in);
		System.out.print("Id: ");
		String id = l.nextLine();
		int type = 0;
		String message = readX.checkBP(id);
		if(message.equals("Book")|message.equals("Magazine")){
			System.out.println("Enter the new info: ");
			System.out.println("***If you do not want to modify the information of some field enter -1***\n***Please enter the correct publication date, whether you want to modify it or not***");
			if(message.equals("Book")){
				type = 1;
			} else {
				type = 2;
			}
			createupdateBP(type, 2, id);
		} else {
			System.out.println(message);
		}
	}

	public void removeBP(){
		Scanner l = new Scanner(System.in);
		System.out.print("Id: ");
		String id = l.nextLine();
		System.out.println(readX.removeBP(id));
	}
	
	public static void main(String[] args) {
		Manager objManager = new Manager();
		objManager.menu();
	}  
}