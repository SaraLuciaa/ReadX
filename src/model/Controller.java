package model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Controller {
    private ArrayList<BibliographicProduct> products;
    private ArrayList<User> users;
    private ArrayList<Payment> payments;

    public Controller(){
        this.products = new ArrayList<BibliographicProduct>();
        this.users = new ArrayList<User>();
        this.payments = new ArrayList<Payment>();
    }

    /**
     * Generates bibliographic objects and users.
     *
     * <br>pre:<br> None.
     * <br>post:<br> Bibliographic objects and users are generated.
     *
     * @return A string representation of the generated objects.
     */
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

    /**
     * Search for a bibliographic product by its ID.
     *
     * <br>pre:<br> None.
     * <br>post:<br> The bibliographic product corresponding to the ID is returned, if any.
     *
     * @param id The product ID to search for.
     * @return The bibliographic product corresponding to the ID, or null if not found.
     */
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

    /**
     * Verify the type of bibliographic product by its ID.
     *
     * <br>pre:<br> None.
     * <br>post:<br> A message is returned indicating the bibliographic product type corresponding to the ID, if any.
     *
     * @param id The product ID to verify.
     * @return A message indicating the type of bibliographic product, or a message of non-existence if not found.
     */
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

    /**
     * Create a book with the data provided.
     *
     * <br>pre:<br> There must be no bibliographic product with the ID provided.
     * <br>post:<br> A new workbook is created and added to the product list.
     *
     * @param id The book ID.
     * @param name The name of the book.
     * @param url The URL of the book.
     * @param pages The number of pages in the book.
     * @param publication The publication date of the book.
     * @param value The value of the book.
     * @param pagesRead The number of pages read from the book.
     * @param review The book review.
     * @param copiesSold The number of copies sold of the book.
     * @param genre The genre of the book.
     * @return A message indicating if the book was successfully created or if a product already exists with the ID provided.
     */
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

    /**
     * Create a magazine with the data provided.
     *
     * <br>pre:<br> There must be no bibliographic product with the ID provided.
     * <br>post:<br> A new magazine is created and added to the product list.
     *
     * @param id The magazine ID.
     * @param name The name of the magazine.
     * @param url The URL of the magazine.
     * @param pages The number of pages of the magazine.
     * @param publication The publication date of the magazine.
     * @param value The value of the magazine.
     * @param pagesRead The number of pages read from the magazine.
     * @param category The magazine category.
     * @param periodicityEmission The periodicity of issue of the magazine.
     * @param activeSubscriptions The number of active magazine subscriptions.
     * @return A message indicating whether the magazine was successfully created or whether a product already exists with the ID provided.
     */
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

    /**
     * Updates the data in a book.
     *
     * <br>pre:<br> There must be a book with the ID provided.
     * <br>post:<br> The data of the book corresponding to the ID is updated.
     *
     * @param id The book ID to update.
     * @param name The new name of the book.
     * @param url The new URL of the book.
     * @param pages The new number of pages in the book.
     * @param publication The new publication date of the book.
     * @param value The new value of the book.
     * @param pagesRead The new number of pages read from the book.
     * @param review The new book review.
     * @param copiesSold The new number of copies sold of the book.
     * @param genre The new genre of the book.
     * @return A message indicating the initial and updated data of the book.
     */
    public String updateBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,String review, int copiesSold, int genre){
        Book product = (Book) searchBP(id);
        String message = "-----Initial data-----\n" + product.toString() + "\n------------------------\n";
        product.updateBP(name, url, pages, publication, value, pagesRead, review, copiesSold, genre);
        message += "-----Updated data-----\n" + product.toString() + "\n------------------------\n";
        return message;
    }

    /**
     * Updates the data of a magazine.
     *
     * <br>pre:<br> There must be a magazine with the ID provided.
     * <br>post:<br> The data of the magazine corresponding to the ID is updated.
     *
     * @param id The magazine ID to update.
     * @param name The new name of the magazine.
     * @param url The new URL of the magazine.
     * @param pages The new number of pages of the magazine.
     * @param publication The magazine's new publication date.
     * @param value The new value of the magazine.
     * @param pagesRead The new number of pages read from the magazine.
     * @param category The new category of the magazine.
     * @param periodicityEmission The new issue periodicity of the magazine.
     * @param activeSubscriptions The new number of active magazine subscriptions.
     * @return A message indicating the initial and updated data of the magazine.
     */
    public String updateBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        Magazine product = (Magazine) searchBP(id);
        String message = "-----Initial data-----\n" + product.toString();
        product.updateBP(name, url, pages, publication, value, pagesRead, category, periodicityEmission, activeSubscriptions);
        message += "\n-----Updated data-----\n" + product.toString() + "\n------------------------\n";   
        return message;
    }

    /**
     * Delete a bibliographic product by its ID.
     *
     * <br>pre:<br> There must be a bibliographic product with the ID provided.
     * <br>post:<br> The bibliographic product corresponding to the ID is deleted and the associated subscriptions are cancelled.
     *
     * @param id The product ID to remove.
     * @return A message indicating if the product was successfully removed or if no product was found with the ID provided.
     */
    public String removeBP(String id){
        String message = "";
        if(searchBP(id)!=null){
            products.remove(searchBP(id));
            for(int i=0; i<users.size(); i++){
                if(searchBP(id) instanceof Magazine){
                    cancelSuscription(users.get(i).getId(), id);
                }
            }
            message = "---Bibliographic product removed successfully---";
        }
        else {
            message = "-------Non-existent bibliographic product.------";
        }
        return message;
    }

    /**
     * Returns a string representation of the objects in the system.
     *
     * <br>pre:<br> None.
     * <br>post:<br> Returns a string representation of the bibliographic products and users in the system.
     *
     * @return A string representation of the objects in the system.
     */
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

    /**
     * Returns the string representation of the genre at the given index.
     *
     * <br>pre:<br> None.
     * <br>post:<br> Returns the string representation of the genre at the given index.
     *
     * @param i The index of the genre.
     * @return The string representation of the genre at the given index.
     */
    public String showGenre(int i){
        return Genre.values()[i].toString();
    }

    /**
     * Returns the string representation of the category at the given index.
     *
     * <br>pre:<br> None.
     * <br>post:<br> Returns the string representation of the category at the given index.
     *
     * @param i The index of the category.
     * @return The string representation of the category at the given index.
     */
    public String showCategory(int i){
        return Category.values()[i].toString();
    }

    // --------------------- Users ---------------------
    /**
     * Searches for a user by their ID.
     *
     * <br>pre:<br> None.
     * <br>post:<br> Returns the user with the matching ID, if found.
     *
     * @param id The ID of the user to search for.
     * @return The user with the matching ID, or null if not found.
     */
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

    /**
     * Checks if a user with the given ID exists.
     *
     * <br>pre:<br> None.
     * <br>post:<br> Returns true if a user with the given ID exists, false otherwise.
     *
     * @param id The ID of the user to check.
     * @return True if a user with the given ID exists, false otherwise.
     */
    public boolean existUser(String id){
        return searchUser(id)!=null?true:false;
    }

    /**
     * Creates a new user with the given type, name, and ID.
     *
     * <br>pre:<br> There should not be an existing user with the provided ID.
     * <br>post:<br> Creates a new user and adds it to the user list.
     *
     * @param type The type of the user (1 for Regular, 2 for Premium).
     * @param name The name of the user.
     * @param id The ID of the user.
     * @return A message indicating if the user was created successfully or if a user with the entered ID already exists.
     */
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
    /**
     * Performs the purchase of books or magazine subscriptions.
     *
     * <br>pre:<br> The user exists.
     * <br>post:<br> The purchase is made, and the corresponding messages are returned.
     *
     * @param type The type of the purchase (1 for book, 2 for magazine).
     * @param idUser The ID of the user making the purchase.
     * @param idBP The ID of the bibliographic product being purchased.
     * @return The message indicating the status of the purchase.
     */
    public String buyBP(int type, String idUser, String idBP){
        User user = searchUser(idUser);
        String message = "";
        boolean buy = true;
        if(user instanceof Regular){
            Regular userR = (Regular) user;
            buy = userR.canBuy(type);
        }
        BibliographicProduct bp = searchBP(idBP);
        String subtype = "";
        if(bp instanceof Book) {
            subtype = ((Book) bp).getGenre().toString();
        } else if(bp instanceof Magazine) {
            subtype = ((Magazine) bp).getCategory().toString();
        }
        if(buy&&bp!=null&&((type==1&&bp instanceof Book)||(type==2&&bp instanceof Magazine))){
            bp.sellBP();
            Payment newP = new Payment(bp.getClass().getSimpleName(), subtype, bp.getValue());
            payments.add(newP);
            message = user.buyBP(newP, bp);
        } else if (!buy) {
            message = type==1?"Maximum number of books purchased":"Maximum number of active subscriptions";
        } else {
            message = type==1?"Book not found":"Magazine not found";
        }
        return message;
    }

    // ----------- Simulate reading session ------------
    /**
     * Simulates a reading session.
     *
     * <br>pre:<br>  The user and bibliographic product exist, and the page is valid.
     * <br>post:<br>  The reading session is simulated, and the corresponding message is returned.
     *
     * @param page The page number to start the reading session.
     * @param idBP The ID of the bibliographic product being read.
     * @param idUser The ID of the user performing the reading session.
     * @return The message indicating the progress of the reading session.
     */
    public String simulateReadingSession(int page, String idBP, String idUser){
        String message = "";
        User user = searchUser(idUser);
        BibliographicProduct bp = searchBP(idBP);
        if(user!=null&&user.searchBP(idBP)!=null&&page<=bp.getPages()){
            if(page<1){page=1;}
            message = "Reading session in progress:\n"+ bp.simulateReadingSession(page) +"\n\nType A to go to the previous page.\nType S to go to the next page\nType B to return to the Library.";
        } else if(user!=null&&user.searchBP(idBP)!=null&&page>bp.getPages()){
            message = "ALERT!!! Maximum number of pages: " + bp.getPages() + "\n\nReading session in progress:\n"+ bp.simulateReadingSession(page) +"\n\nType A to go to the previous page.\nType S to go to the next page\nType B to return to the Library.";
        } else if(user!=null&&user.searchBP(idBP)==null) {
            message = "This bibliographic product was not found in the library. Type B to return to the Library.";
        }
        message += "\n" + (((page%20==0||page==1)&&bp instanceof Book)||((page%5==0||page==1)&&bp instanceof Magazine)?showAdd(idUser):"");
        return message;
    } 
    
    // -------------- Library presentation -------------
    /**
     * Displays the user's library.
     *
     * <br>pre:<br> The user exists.
     * <br>post:<br>  The user's library is displayed.
     *
     * @param idUser The ID of the user.
     * @param page The page number of the library to display.
     * @return The message displaying the user's library.
     */
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

    /**
     * Goes to the simulation based on user input.
     *
     * <br>pre:<br> The user exists, and the input and page number are valid.
     * <br>post:<br> The simulation is displayed.
     *
     * @param idUser The ID of the user.
     * @param input The input containing the bibliographic product ID.
     * @param page The page number to display in the simulation.
     * @return The simulation message.
     */
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
    /**
     * Cancels a subscription.
     *
     * <br>pre:<br> The user and magazine exist.
     * <br>post:<br>  The subscription is canceled, and the corresponding message is returned.
     *
     * @param idUser The ID of the user.
     * @param idM The ID of the magazine subscription to cancel.
     * @return The cancellation message.
     */
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
    /**
     * Calculates the total number of pages read.
     *
     * <br>pre:<br> None.
     * <br>post:<br> The total number of pages read is returned.
     *
     * @return The message displaying the total number of pages read.
     */
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
        return "--------- Cumulative number of pages read --------\nBook: " + contB + "\nMagazine: " + contM + "\nTotal: " + (contM+contB) + "\n--------------------------------------------------";
    }

    /**
     * Calculates the total number of pages read for each genre and category of products.
     * 
     * <br>pre: The 'products' list is not null.
     * <br>post: Returns a formatted string displaying the most read category for books and the most read genre for magazines.
     * 
     * @return A string containing the most read category for books and the most read genre for magazines.
     */
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
        return "--------------------------------------------------\n" +
               "BOOK     | Most read category: " + categoryMax + " - " +  maxB + " pages read.\n"+ 
               "MAGAZINE | Most read genre:    " + genreMax + " - " +  maxM + " pages read.\n" +
               "--------------------------------------------------"; 
    }

    /**
     * Retrieves the top 5 most read books or magazines based on the specified type.
     * 
     * <br>pre: The 'products' list is not null.
     * <br>post: Returns a string listing the top 5 most read books or magazines.
     * 
     * @param typeBP An integer representing the type of bibliographic product (1 for books, 2 for magazines).
     * @return A string listing the top 5 most read books or magazines.
     */
    public String top5(int typeBP){
        String message = typeBP==1?"------------ Top 5 most read books ---------------":"------------ Top 5 most read magazines -----------";
        ArrayList<BibliographicProduct> bp = new ArrayList<BibliographicProduct>();
        for(BibliographicProduct product : products){
            if(typeBP==1&&product instanceof Book){
                bp.add(product);
            } else if(typeBP==2&&product instanceof Magazine){
                bp.add(product);
            }
        }
        ArrayList<BibliographicProduct> top5 = new ArrayList<BibliographicProduct>();
        for(BibliographicProduct prod : bp){
            if (top5.isEmpty()) {
                top5.add(prod);
            } else {
                int index = -1;
                for (int i = 0; i < top5.size(); i++) {
                    if (prod.getPagesRead() > top5.get(i).getPagesRead()) {
                        index = i;
                        break;
                    }
                }
    
                if (index != -1) {
                    top5.add(index, prod);
                    if (top5.size() > 5) {
                        top5.remove(5);
                    }
                } else if (top5.size() < 5) {
                    top5.add(prod);
                }
            }
        }
        for(int i=0; i<top5.size(); i++){
            BibliographicProduct prod = top5.get(i);
            message += "\n" +(i+1) + ". " + prod.getName() + " - " + prod.getPagesRead() + " pages read.";
        }
        message += "\n--------------------------------------------------";
        return message;
    }

    /**
     * Calculates the total sales or subscriptions based on the specified option.
     * 
     * <br>pre: The 'users' list is not null.
     * <br>post: Returns an array of doubles representing the total sales or subscriptions.
     * 
     * @param opt An integer representing the option to calculate total sales (1) or subscriptions (2).
     * @return An array of doubles representing the total sales or subscriptions.
     */
    public double[] totalSalesSuscription(int opt){
        double[] totalSales = new double[3]; 
        double[] totalSuscr = new double[3];
        for(int i=0; i<users.size(); i++){
            User user = users.get(i);
            ArrayList<Payment> pay = user.getPayments();
            for(int j=0; j<pay.size(); j++){
                Payment p = pay.get(j);
                if(p.getTypeBP().equals("Book")){
                    if(p.getSubtype().equals(Genre.SCIENCE_FICTION.toString())){
                        totalSales[0]+=p.getPay();
                    } else if(p.getSubtype().equals(Genre.FANTASY.toString())){
                        totalSales[1]+=p.getPay();
                    } else if(p.getSubtype().equals(Genre.HISTORICAL_NOVEL.toString())){
                        totalSales[2]+=p.getPay();
                    }
                } else if(p.getTypeBP().equals("Magazine")){
                    if(p.getSubtype().equals(Category.VARIETIES.toString())){
                        totalSuscr[0]+=p.getPay();
                    } else if(p.getSubtype().equals(Category.DESIGN.toString())){
                        totalSuscr[1]+=p.getPay();
                    } else if(p.getSubtype().equals(Category.SCIENTIFY.toString())){
                        totalSuscr[2]+=p.getPay();
                    }
                }
            }
        }
        double[] totalS = new double[3];
        if(opt==1){
            totalS = totalSales;
        } else {
            totalS = totalSuscr;
        }
        return totalS;
    }

    /**
     * Generates a sales report for books, displaying the number of copies sold for each genre.
     * 
     * <br>pre: The 'products' list is not null.
     * <br>post: Returns a formatted string displaying the sales report by genre for books.
     * 
     * @return A string containing the sales report by genre for books.
     */
    public String bookCopiesSold(){
        String message = "-------------- Sales report by genre -------------";
        int[] genre = new int[3];
        for(int i=0; i<products.size(); i++){
            if(products.get(i) instanceof Book){
                Book product = (Book) products.get(i);
                if(product.getGenre()==Genre.SCIENCE_FICTION){
                    genre[0]+=product.getCopiesSold();
                } else if(product.getGenre()==Genre.FANTASY){
                    genre[1]+=product.getCopiesSold();
                } else if(product.getGenre()==Genre.HISTORICAL_NOVEL){
                    genre[2]+=product.getCopiesSold();
                }
            }
        }
        for(int i=0; i<genre.length; i++){
            message += "\n" + Genre.values()[i].toString() + "\nCopies sold: " + genre[i] + "  |  Total sales: " + totalSalesSuscription(1)[i] + "\n--------------------------------------------------";
        }
        return message;
    }

    /**
     * Generates a subscriptions report for magazines, displaying the number of active subscriptions for each category.
     * 
     * <br>pre: The 'products' list is not null.
     * <br>post: Returns a formatted string displaying the subscriptions report by category for magazines.
     * 
     * @return A string containing the subscriptions report by category for magazines.
     */
    public String magazineSuscriptionActive(){
        String message = "-------- Subscriptions report by category --------";
        int[] category = new int[3];
        for(int i=0; i<products.size(); i++){
            if(products.get(i) instanceof Magazine){
                Magazine product = (Magazine) products.get(i);
                if(product.getCategory()==Category.VARIETIES){
                    category[0]+=product.getActiveSubscriptions();
                } else if(product.getCategory()==Category.DESIGN){
                    category[1]+=product.getActiveSubscriptions();
                } else if(product.getCategory()==Category.SCIENTIFY){
                    category[2]+=product.getActiveSubscriptions();
                }
            }
        }
        for(int i=0; i<category.length; i++){
            message += "\n" + Category.values()[i].toString() + "\nActive suscriptions: " + category[i] + "  |  Total paid: " + totalSalesSuscription(2)[i] + "\n--------------------------------------------------";
        }
        return message;
    }

    /**
     * Returns a random promotional message.
     * 
     * @return A randomly selected promotional message.
     */
    public String showAdd(){
        String[] message = {"****** Subscribe to Combo Plus and get Disney+ and Star+ at an incredible price! **************", "****** Now your pets have a favorite app: Laika. The best products for your furry one. ********", "****** It's our anniversary! Visit your nearest Exito and be surprised with the best offers. **"};
        Random random = new Random();
        int position = random.nextInt(3);
        return message[position];
    }

    /**
     * Returns a promotional message if the user is a regular user.
     * 
     * @param idUser The ID of the user.
     * @return A promotional message if the user is a regular user; otherwise, an empty string.
     */
    public String showAdd(String idUser){
        return ((searchUser(idUser)!=null)&&(searchUser(idUser) instanceof Regular))?showAdd():"";
    }
}