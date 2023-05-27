package model;
import java.util.ArrayList;
import java.util.Calendar;

public class Controller {
    private ArrayList<BibliographicProduct> products;
    private ArrayList<User> users;
    private ArrayList<Payment> payments;

    public Controller(){
        this.products = new ArrayList<BibliographicProduct>();
        this.users = new ArrayList<User>();
        this.payments = new ArrayList<Payment>();
    }

    // -------- Automatically generate objects --------
    public String generateObjects(){
        // Bibliographic Products
        Calendar publication1 = Calendar.getInstance();
        publication1.set(2020, Calendar.AUGUST, 15);
        Calendar publication2 = Calendar.getInstance();
        publication2.set(2005, Calendar.NOVEMBER, 11);
        Calendar publication3 = Calendar.getInstance();
        publication3.set(2018, Calendar.MARCH, 22);
        // Book
        createBP("2A9","The Great Gatsby", "https://example.com/the-great-gatsby", 300, publication1, 19.99, 200, "A captivating story set in the 1920s.", 1000, 3);    
        createBP("B0E", "1984", "https://example.com/1984", 350, publication2, 14.99, 150, "A dystopian masterpiece.", 2000, 1);
        createBP("7C1", "Pride and Prejudice", "https://example.com/pride-and-prejudice", 250, publication3, 9.99, 200, "A classic love story with witty dialogue.", 3000, 2);
        // Magazine
        createBP("M01", "Entertainment Weekly", "https://www.entertainmentweekly.com", 70, publication1, 8.99, 50, 1, "Weekly", 2000);
        createBP("M02", "Architectural Digest", "https://www.architecturaldigest.com", 90, publication2, 10.99, 20, 2, "Monthly", 800);
        createBP("M03", "Science Today", "https://www.sciencetoday.com", 60, publication3, 7.99, 40, 3, "Bi-monthly", 500);
        // Regular user
        createUser(1, "Alice", "123001");
        createUser(1, "Bob", "456002");
        // Premium user
        createUser(2,"Carol", "789003");
        createUser(2,"David", "258004");
        return toString();
    }

    // ------------ Bibliographic Products ------------
    public BibliographicProduct searchBP(String id){
        BibliographicProduct product = null;
        boolean search = true;
        for(int i=0; i<products.size()&&search; i++){
            if(products.get(i).getId().equalsIgnoreCase(id)){
                product = products.get(i);
                search = false;
            }
        }
        return product;
    }

    public String checkBP(String id){
        String message = "";
        BibliographicProduct product = searchBP(id);
        if(product!=null){
            if(product instanceof Book){
                message = "Book";
            } else if(product instanceof Magazine){
                message = "Magazine";
            }
        } else {
            message = "Non-existent bibliographic product.";
        }
        return message;
    }

    public String createBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,String review, int copiesSold, int genre){
        String message = "";
        if(searchBP(id)==null){
            Book newB = new Book(id, name, url, pages, publication, value, pagesRead, review, copiesSold, genre);
            products.add(newB);
            message = "\n----Book created successfully----\n" + newB.toString() + "\n---------------------------------";
        }
        else {
            message = "There is already a bibliographic product with the entered ID.";
        }
        return message;
    }

    public String createBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        String message = "";
        if(searchBP(id)==null){
            Magazine newM = new Magazine(id, name, url, pages, publication, value, pagesRead, category, periodicityEmission, activeSubscriptions);
            products.add(newM);
            message = "\n----Magazine created successfully----\n" + newM.toString() + "\n---------------------------------";
        }
        else {
            message = "There is already a bibliographic product with the entered ID.";
        }
        return message;
    }

    public String updateBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,String review, int copiesSold, int genre){
        Book product = (Book) searchBP(id);
        String message = "-----Initial data-----\n" + product.toString() + "\n------------------------\n";
        product.updateBook(name, url, pages, publication, value, pagesRead, review, copiesSold, genre);
        message += "-----Updated data-----\n" + product.toString() + "\n------------------------\n";
        return message;
    }

    public String updateBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        Magazine product = (Magazine) searchBP(id);
        String message = "-----Initial data-----\n" + product.toString();
        product.updateMagazine(name, url, pages, publication, value, pagesRead, category, periodicityEmission, activeSubscriptions);
        message += "\n-----Updated data-----\n" + product.toString() + "\n------------------------\n";   
        return message;
    }

    public String removeBP(String id){
        String message = "";
        if(searchBP(id)!=null){
            products.remove(searchBP(id));
            message = "---Bibliographic product removed successfully---";
        }
        else {
            message = "Non-existent bibliographic product.";
        }
        return message;
    }

    public String toString(){
        String message = "---------------------------------";
        for(int i=0; i<products.size(); i++){
            message +=  "\n" + products.get(i).toString() + "\n---------------------------------";
        }
        for(int i=0; i<users.size(); i++){
            message += "\n" + users.get(i).toString() + "\n---------------------------------";
        }
        return message;
    }

    public String showGenre(int i){
        return Genre.values()[i].toString();
    }

    public String showCategory(int i){
        return Category.values()[i].toString();
    }

    // --------------------- Users ---------------------
    public User searchUser(String id){
        User user = null;
        boolean search = true;
        for(int i=0; i<users.size()&&search; i++){
            if(users.get(i).getId().equalsIgnoreCase(id)){
                user = users.get(i);
                search = false;
            }
        }
        return user;
    }

    public boolean existUser(String id){
        return searchUser(id)!=null?true:false;
    }

    public String createUser(int type, String name, String id){
        String message = "";
        User newU = searchUser(id);
        if(newU==null){
            if(type==1){
                newU = new Regular(name, id);
            } else {
                newU = new Premium(name, id);
            }
            users.add(newU);
            message = "----User created successfully----\n" + newU.toString() + "\n---------------------------------";
        }
        else {
            message = "There is already a user with the entered ID.";
        }
        return message;
    }

    // -- Purchase of books or magazine subscriptions --
    public String buyBP(int type, String idUser, String idBP){
        User user = searchUser(idUser);
        String message = "";
        boolean buy = true;
        if(user instanceof Regular){
            Regular userR = (Regular) user;
            buy = userR.canBuy(type);
        }
        BibliographicProduct bp = searchBP(idBP);
        if(buy&&bp!=null&&((type==1&&bp instanceof Book)||(type==2&&bp instanceof Magazine))){
            bp.sellBP();
            Payment newP = new Payment(bp.getValue());
            payments.add(newP);
            message = user.buyBP(newP, bp);
        } else if (!buy) {
            message = type==1?"Maximum number of books purchased":"Maximum number of active subscriptions";
        } else {
            message = type==1?"Book not found":"Magazine Product not found";
        }
        return message;
    }

    // ----------- Simulate reading session ------------
    public String simulateReadingSession(int page, String idBP, String idUser){
        String message = "";
        User user = searchUser(idUser);
        BibliographicProduct bp = searchBP(idBP);
        boolean adds = user instanceof Regular?true:false;
        if(user!=null&&user.searchBP(idBP)!=null&&page<=bp.getPages()){
            if(page<1){page=1;}
            message = "Reading session in progress:\n"+ bp.simulateReadingSession(page) +"\n\nType A to go to the previous page.\nType S to go to the next page\nType B to return to the Library.";
        } else if(user!=null&&user.searchBP(idBP)!=null&&page>bp.getPages()){
            message = "ALERT!!! Maximum number of pages: " + bp.getPages() + "\n\nReading session in progress:\n"+ bp.simulateReadingSession(page) +"\n\nType A to go to the previous page.\nType S to go to the next page\nType B to return to the Library.";
        } else if(user!=null&&user.searchBP(idBP)==null) {
            message = "This bibliographic product was not found in the library. Type B to return to the Library.";
        }
        return message;
    } 
    
    // -------------- Library presentation -------------
    public String goToMyLibrary(String idUser, int page){
        User user = searchUser(idUser); 
        String message = "   |  0  |  1  |  2  |  3  |  4 ";
        String[][][] library = user.generateLibrary();
        if(page>=library.length){
            page = library.length-1;
        }
        for(int i=0; i<library[page].length; i++){
            message += "\n " + i ;
            for(int j=0; j<library[page][i].length; j++){
                message += " | " + library[page][i][j]; 
            }
        }
        return message;
    }

    public String goToSimulation(String idUser, String input, int page){
        User user = searchUser(idUser); 
		String[] idBP = input.split(",");
		String bp = idBP[0];
		if(idBP.length==2){
            String x = idBP[0];
            String y = idBP[1];
            if(x.matches("-?\\d+")&&y.matches("-?\\d+")){
                bp = user.searchBP(Integer.parseInt(idBP[0]), Integer.parseInt(idBP[1]), page);
            }
		}
		return bp;
	}

    // -------------- Cancel suscription ---------------
    public String cancelSuscription(String idUser, String idM){
        String message = "";
        User user = searchUser(idUser);
        Magazine magazine = user.searchBP(idM)!=null&&user.searchBP(idM) instanceof Magazine?(Magazine) user.searchBP(idM):null;
        if(user!=null&&magazine!=null){
            magazine.setActiveSubscriptions(magazine.getActiveSubscriptions()-1);
            message = "The subscription has been canceled successfully\n" + user.cancelSuscription(idM);
        } else {
            message = "ERROR! Check the magazine ID.";
        }
        return message;
    }

    // -------------- Generate reports -----------------
    public String totalPagesRead(){
        int contB = 0;
        int contM = 0;
        for(int i=0; i<products.size(); i++){
            int pagesRead = products.get(i).getPagesRead();
            if(products.get(i) instanceof Book){
                contB+=pagesRead;
            } else if(products.get(i) instanceof Magazine){
                contM+=pagesRead;
            }
        }
        return "---- Cumulative number of pages read ---\nBook: " + contB + "\nMagazine: " + contM + "\nTotal: " + (contM+contB); 
    }

    public String totalPagesReadGenreCategory(){
        int[] genre = new int[3];
        int[] category = new int[3];
        for(int i=0; i<products.size(); i++){
            int pagesRead = products.get(i).getPagesRead();
            if(products.get(i) instanceof Book){
                Book product = (Book) products.get(i);
                if(product.getGenre()==Genre.SCIENCE_FICTION){
                    genre[0]+=pagesRead;
                } else if(product.getGenre()==Genre.FANTASY){
                    genre[1]+=pagesRead;
                } else if(product.getGenre()==Genre.HISTORICAL_NOVEL){
                    genre[2]+=pagesRead;
                }
            } else if(products.get(i) instanceof Magazine){
                Magazine product = (Magazine) products.get(i);
                if(product.getCategory()==Category.VARIETIES){
                    category[0]+=pagesRead;
                } else if(product.getCategory()==Category.DESIGN){
                    category[1]+=pagesRead;
                } else if(product.getCategory()==Category.SCIENTIFY){
                    category[2]+=pagesRead;
                }
            }
        }

        int maxB = genre[0]; 
        String genreMax = Genre.values()[0].toString();
        int maxM = category[0]; 
        String categoryMax = Genre.values()[0].toString();
        for (int i = 1; i < genre.length; i++) {
            if (genre[i] > maxB) {
                maxB = genre[i];
                genreMax = Genre.values()[i].toString();
            }
            if (category[i] > maxM) {
                maxM = genre[i];
                categoryMax = Category.values()[i].toString();
            }
        }
        return "BOOK     | Most read category: " + categoryMax + " - " +  maxB + " pages read.\n"+ 
               "MAGAZINE | Most read genre:    " + genreMax + " - " +  maxM + " pages read.\n"; 
    }
}