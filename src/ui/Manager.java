package ui;
import java.util.Calendar;
import java.util.Scanner;

import model.Controller;

public class Manager {
    private Controller readX;;

	/**
     * Constructs a Manager object and initializes the Controller.
     */
    public Manager(){
        this.readX = new Controller();
    }

	/**
     * Displays the main menu and handles user input.
     */
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

	/**
     * Displays the menu for a user and handles user input.
     *
     * @param id the id of the user
     */
	public void menuUser(String id){
		if(readX.existUser(id)){
			boolean status = true;
			Scanner l = new Scanner(System.in);
			while(status){
				System.out.println("Choose the option:\n1.Purchase book\n2.Subscribe to magazine\n3.Simulate reading session\n4.My Library\n5.Cancel a magazine subscription\n9.Back to main menu\n10.Exit");
				System.out.println(readX.showAdd(id));
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
					case 5:
						cancelSuscription(id);
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

	/**
     * Creates a new user by taking input from the user.
     */
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

	/**
     * Displays the menu for the ReadX manager and handles user input.
     */
	public void menuReadX(){
		boolean status = true;
		Scanner l = new Scanner(System.in);
		while(status){
			System.out.println("Choose the option:\n1.Add bibliographic product\n2.Update bibliographic product\n3.Remove bibliographic product\n4.Generate reports\n9.Back to main menu\n10.Exit");
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
				case 4: 
					generateReports();
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

	/**
	 * Creates or updates a bibliographic product based on the specified parameters.
	 * 
	 * <br>pre:<br> The type, func, and id parameters must be valid.
	 * 
	 * <br>post:<br> A new bibliographic product is created or an existing one is updated with the provided information.
	 * 
	 * @param type The type of bibliographic product (1 for book, 2 for magazine)
	 * @param func The function type (1 for create, 2 for update)
	 * @param id The ID of the bibliographic product
	 */
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
		datePublication.set(yearPublication, monthPublication-1, dayPublication);
		System.out.print("Value: ");
		double value = l.nextDouble();
		while((value<0&&func==1)||(value<-1&&func==2)){
			System.out.println("The value cannot be less than zero. Try again");
			value = l.nextInt();
		}
		System.out.print("Number of pages read: ");
		long pagesRead = l.nextLong();
		while((pagesRead<0&&func==1)||(pagesRead<-1&&func==2)){
			System.out.println("The number of pages read cannot be less than zero. Try again");
			pagesRead = l.nextLong();
		}
		l.nextLine();
		if(type==1){
			System.out.print("Review: ");
			String review = l.nextLine();
			while(review.length()>350){
				System.out.println("The review can contain a maximum of 350 characters. Try again");
				review = l.nextLine();
			}
			System.out.print("Number of copies sold: ");
			long copiesSold = l.nextLong();
			l.nextLine();
			while((copiesSold<0&&func==1)||(copiesSold<-1&&func==2)){
				System.out.println("The number of copies sold cannot be less than zero. Try again");
				copiesSold = l.nextLong();
			}
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
				while(!(genre >= 1 && genre <= 3) && genre != -1){
					System.out.println("Input invalid. Try again");
					genre = l.nextInt();
				}
				System.out.println(readX.updateBP(id, name, url, pages, datePublication, value, pagesRead, review, copiesSold, genre));
			}
		} else {
			System.out.print("Periodicity of emission: ");
			String periodicityEmission = l.nextLine();
			System.out.println("Number of active subscriptions: ");
			long activeSubscriptions = l.nextLong();
			while((activeSubscriptions<0&&func==1)||(activeSubscriptions<-1&&func==2)){
				System.out.println("The number of active subscriptions cannot be less than zero. Try again");
				activeSubscriptions = l.nextLong();
			}
			System.out.println("Category: ");
			for(int i=0; i<3; i++){
				System.out.println((i+1) + ". " + readX.showCategory(i));
			}
			l.nextLine();
			int category = l.nextInt();
			if(func==1){
				while(category<1&&category>3){
					System.out.println("Input invalid. Try again");
					category = l.nextInt();
				}
				System.out.println(readX.createBP(id, name, url, pages, datePublication, value, pagesRead, category, periodicityEmission, activeSubscriptions));
			} else {
				while(!(category >= 1 && category <= 3) && category != -1){
					System.out.println("Input invalid. Try again");
					category = l.nextInt();
				}
				System.out.println(readX.updateBP(id, name, url, pages, datePublication, value, pagesRead, category, periodicityEmission, activeSubscriptions));
			}
		}
	}
	
	/**
	 * Creates a new bibliographic product by gathering user input.
	 * 
	 * <br>post:<br> A new bibliographic product is created with the provided information.
	 */
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
		while(id.length()>3){
			System.out.println("Input invalid. Try again");
			id = l.nextLine();
		}
		createupdateBP(type, 1, id);
	}

	/**
	 * Updates an existing bibliographic product by gathering user input.
	 * 
	 * <br>post:<br> The existing bibliographic product is updated with the provided information.
	 */
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

	/**
	 * Removes a bibliographic product with the specified ID.
	 * 
	 * <br>post:<br> Show a message indicating the success or failure of the removal operation
	 */
	public void removeBP(){
		Scanner l = new Scanner(System.in);
		System.out.print("Id: ");
		String id = l.nextLine();
		System.out.println(readX.removeBP(id));
	}
	
	/**
	 * Buys a bibliographic product of the specified type for the given user.
	 * 
	 * <br>pre:<br> The type parameter must be valid.
	 * 
	 * <br>post:<br> The user purchases the bibliographic product of the specified type.
	 * 
	 * @param type The type of bibliographic product to buy (1 for book, 2 for magazine)
	 * @param idUser The ID of the user making the purchase
	 */
	public void buyBP(int type, String idUser){
		Scanner l = new Scanner(System.in);
		System.out.print(type==1?"Book ID: ":"Magazine ID: ");
		String idBP = l.nextLine();
		System.out.println(readX.buyBP(type, idUser, idBP));
	}

	/**
	 * Simulates a reading session for the specified bibliographic product and user.
	 * 
	 * <br>post:<br> The reading session is simulated for the specified bibliographic product and user.
	 * 
	 * @param idUser The ID of the user simulating the reading session
	 */
	public void simulateReadingSession(String idUser){
		Scanner l = new Scanner(System.in);
		System.out.print("Bibliographic Product ID: ");
		String idBP = l.nextLine();
		simulateReadingSession(1, idBP, idUser);
	}

	/**
	 * Simulates a reading session for the specified page of the bibliographic product and user.
	 * 
	 * <br>post:<br> The reading session is simulated for the specified page of the bibliographic product and user.
	 * 
	 * @param page The page number to start the reading session from
	 * @param idBP The ID of the bibliographic product
	 * @param idUser The ID of the user simulating the reading session
	 */
	public void simulateReadingSession(int page, String idBP, String idUser){
		Scanner l = new Scanner(System.in);
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

	/**
	 * Navigates to the user's library and allows interaction with the bibliographic products.
	 * 
	 * <br>post:<br> The user navigates to their library and can perform various actions.
	 * 
	 * @param idUser The ID of the user navigating the library
	 */
	public void goToMyLibrary(String idUser){
		Scanner l = new Scanner(System.in);
		int page = 0;
		String message = "\n\nType the x,y coordinate or the corresponding code of the bibliographic product to start a reading session\nType A to go to the previous page\nType S to go to the next page\nType E to exit";
		System.out.println(readX.goToMyLibrary(idUser, page) + message);
		String input = l.nextLine();
		while(!input.equalsIgnoreCase("E")){
			if(input.equalsIgnoreCase("A")){
				if(page>0){
					page--;
				}
			} else if (input.equalsIgnoreCase("S")){
				page++;
			} else {
				String idBp = readX.goToSimulation(idUser, input, page);
				simulateReadingSession(1, idBp, idUser);
			}
			System.out.println(readX.goToMyLibrary(idUser, page) + message);
			input = l.nextLine();
		}	
	}

	/**
	 * Cancels a subscription to a magazine for the specified user.
	 * 
	 * @param idUser The ID of the user canceling the subscription
	 */
	public void cancelSuscription(String idUser){
		Scanner l = new Scanner(System.in);
		System.out.println("Magazine ID: ");
		String idM = l.nextLine();
		System.out.println(readX.cancelSuscription(idUser, idM));
	}

	/**
	 * Generates reports containing various statistics and information.
	 * 
	 * <br>post:<br> Reports containing various statistics and information are generated.
	 */
	public void generateReports(){
		System.out.println(readX.totalPagesRead());
		System.out.println(readX.totalPagesReadGenreCategory());
		System.out.println(readX.top5(1));
		System.out.println(readX.top5(2));
		System.out.println(readX.bookCopiesSold());
		System.out.println(readX.magazineSuscriptionActive());
	}

	/**
	 * The main method of the Manager class. Executes the program.
	 * 
	 * @param args The command-line arguments
	 */
	public static void main(String[] args) {
		Manager objManager = new Manager();
		objManager.menu();
	}  
}