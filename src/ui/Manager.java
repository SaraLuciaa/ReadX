package ui;
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
			System.out.println("Choose the option:\n1.Manage bibliographic products\n9.Exit");
			int opt = l.nextInt();
			l.nextLine();
			switch(opt){
				case 2:
					menuBP();
					break;
				default:
					System.out.println("Input invalid. Try again.");
					break;
			}
		}
		l.close();
    }

	public void menuBP(){
		Scanner l = new Scanner(System.in);
		System.out.println("Choose the option:\n1.Add book\n2.Add magazine\n9.Exit");
		int opt = l.nextInt();
		l.nextLine();
		switch(opt){
			case 1:
				createBibliographicProduct();
				break;
			default:
				System.out.println("Input invalid. Try again.");
				break;
		}
		l.close();
	};

	public void createBibliographicProduct(){
		Scanner l = new Scanner(System.in);
		System.out.print("Id: ");
		String id = l.nextLine();
		System.out.print("Name: ");
		String name = l.nextLine();
		System.out.print("URL: ");
		String url = l.nextLine();
		System.out.print("Pages: ");
		int page = l.nextInt();
		System.out.print("Date of publication: ");
		String publication = l.nextLine();
		System.out.print("Value: ");
		String value = l.nextLine();
		System.out.print("Number of pages read: ");
		String pagesRead = l.nextLine();
		l.close();
	}

	public static void main(String[] args) {
		
	}
}