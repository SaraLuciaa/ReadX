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
			System.out.println("Who you are?\n1.User\n2.New user\n3.Manager ReadX\n4.Generate objects\n9.Exit");
			int opt = l.nextInt();
			l.nextLine();
			switch(opt){
				case 1:
					System.out.print("Enter the id of the user: ");
					String idUser = l.nextLine();
					menuUser(idUser);
					break;
				case 2:
					createUser();
					break;
				case 3: 
					menuReadX();
					break;
				case 4: 
					System.out.println(readX.generateObjects());
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

	public void menuUser(String id){
		if(readX.existUser(id)){
			boolean status = true;
			Scanner l = new Scanner(System.in);
			while(status){
				System.out.println("Choose the option:\n1.Purchase book\n2.Subscribe to magazine\n3.Simulate reading session\n4.My Library\n10.Exit");
				int opt = l.nextInt();	
				switch(opt){
					case 1:
						buyBP(1, id);
						break;
					case 2:
						buyBP(2, id);
						break;
					case 3:
						simulateReadingSession(id);
						break;
					case 4: 
						goToMyLibrary(id);
						break;
					case 9: 
						status = false;
						break;
					case 10:
						System.exit(0);
						break;
					default:
						System.out.println("Input invalid. Try again.");
						break;
				}
			}
		} else {
			System.out.println("User not found");
		}
	}

	public void createUser(){
		Scanner l = new Scanner(System.in);
		System.out.println("Type of user: 1.REGULAR 2.PREMIUM");
		int type = l.nextInt();
		while(type!=1&&type!=2){
			System.out.println("Input invalid. Try again");
			type = l.nextInt();
		}
		l.nextLine();
		System.out.print("Name: ");
		String name = l.nextLine();
		System.out.print("Id: ");
		String id = l.nextLine();
		System.out.println(readX.createUser(type, name, id));
	}

	public void menuReadX(){
		boolean status = true;
		Scanner l = new Scanner(System.in);
		while(status){
			System.out.println("Choose the option:\n1.Add bibliographic product\n2.Update bibliographic product\n3.Remove bibliographic product\n9.Back to main menu\n10.Exit");
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
				case 10:
					System.exit(0);
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
	
	public void buyBP(int type, String idUser){
		Scanner l = new Scanner(System.in);
		System.out.print(type==1?"Book ID: ":"Magazine ID: ");
		String idBP = l.nextLine();
		System.out.println(readX.buyBP(type, idUser, idBP));
	}

	public void simulateReadingSession(String idUser){
		Scanner l = new Scanner(System.in);
		System.out.print("Bibliographic Product ID: ");
		String idBP = l.nextLine();
		System.out.print("Page: ");
		int page = l.nextInt();
		l.nextLine();
		System.out.println(readX.simulateReadingSession(page, idBP, idUser));
		char opt = l.nextLine().toUpperCase().charAt(0);
		while(opt!='B'){
			if(opt=='A'){
				page--;
				System.out.println(readX.simulateReadingSession(page, idBP, idUser));
			} else if(opt=='S'){
				page++;
				System.out.println(readX.simulateReadingSession(page, idBP, idUser));
			} else {
				System.out.println("Input not valid. Try again.");
			}
			opt = l.nextLine().toUpperCase().charAt(0);
		}
	}

	public void goToMyLibrary(String idUser){
		Scanner l = new Scanner(System.in);
		int page = 0;
		String message = "\n\nType the x,y coordinate or the corresponding code of the bibliographic product to start a reading session\nType A to go to the previous page\nType S to go to the next page\nType E to exit";
		System.out.println(readX.goToMyLibrary(idUser, page) + message);
		char input = l.nextLine().toUpperCase().charAt(0);
		while(input!='E'){
			if(input=='A'){
				if(page>0){
					page--;
				}
				System.out.println(readX.goToMyLibrary(idUser, page) + message);
				input = l.nextLine().toUpperCase().charAt(0);
			} else if (input=='S'){
				page++;
				System.out.println(readX.goToMyLibrary(idUser, page) + message);
				input = l.nextLine().toUpperCase().charAt(0);
			}
		}
	}

	public static void main(String[] args) {
		Manager objManager = new Manager();
		objManager.menu();
	}  
}