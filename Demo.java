import java.util.*;
public class Demo{
	private static Scanner input = new Scanner(System.in);
	private static String [] user = {"danujav","1234"};
	private static String [][] suppliers = new String [0][2];
	private static String [] categories = new String [0];
	private static String [][] stocks = new String [0][7];
	
	private final static void clearConsole() {
		final String os = System.getProperty("os.name");
		try {
			if (os.equals("Linux")) {
				System.out.print("\033\143");
			} else if (os.equals("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception e) {
		//handle the exception
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String args[]){
		loginPage();
	}
	
	public static void printTopic(String topic){
		clearConsole();
		int sp = (80-topic.length())/2;
		System.out.printf("+--------------------------------------------------------------------------------+%n");
		System.out.printf("%c%"+sp +"s%S%"+-sp+"s%c%n",'|',"",topic,"",'|');
		System.out.printf("+--------------------------------------------------------------------------------+%n%n");
	}
	
	public static void loginPage(){
		printTopic("login page");
		l1: while(true){
			System.out.print("User Name : ");
			String Uname = input.next();
			if(Uname.equals(user[0])){
				l2:	while (true){
					System.out.print("\npassword : ");
					String pw = input.next();
					if(pw.equals(user[1])){
						homePage();
						return;
					}else{
						System.out.println("password is incorrect. please try again !");
						continue l2;
					}
				}
			}else{
				System.out.println("user name is invalid. please try again !\n");
				continue l1;
			}
		}
	}
	
	public static void homePage(){
		printTopic("welcome to ijse stock management system ");
		System.out.printf("%-50s%-30s%n%-50s%-30s%n%-50s%n","[1] Change the Credentials","[2] Supplier Manage","[3] Stock Manage","[4] Log Out ","[5] Exit the system");
		
		l1: while(true){
			System.out.print("\nEnter an option to continue > ");
			int op = input.nextInt();
			
			if(op == 1){
				credintialManage();
			}else if(op == 2){
				supplierManage();
			}else if(op==3){
				stockManage();
			}else if (op ==4){
				loginPage();
			}else if(op == 5){
				clearConsole();
				return;
			}else{
				System.out.println("Invalid option.try again!");
				continue l1;
			}
			return;
		}
	}
	
	public static void credintialManage(){
		printTopic("credential manage ");
		
		l1: while(true){
			System.out.print("Please enter the user name to verify it's you : ");
			String name = input.next();
			if(name.equals(user[0])){
				System.out.print("Hey " + user[0]);
				l2:	while(true){
					System.out.print(" \nEnter your current password : ");
					String pw = input.next();
					if(pw.equals(user[1])){
						System.out.print("Enter your new password : ");
						user [1] = input.next();
						System.out.println();
						System.out.print("Password changed successfully ! Do you want to go home page (Y/N) : ");
						char ans = input.next().charAt(0);
						if((ans == 'Y') ||(ans =='y')){
							homePage();
							return;
						}else{
							return;
						}
					}else{
						System.out.println("Incorrect password.try again!");
						continue l2;
					}
				}
			}else{
				System.out.println("Invalid user name.try again!\n");
				continue l1;
			}
		}
	}
	
	public static void supplierManage(){
		printTopic("supplier manage ");
		System.out.printf("%-50s%-30s%n%-50s%-30s%n%-50s%-30s%n","[1] Add Supplier","[2] Update Supplier","[3] Delete Supplier","[4] View Supplier","[5] Search Supplier","[6].Home Page");
		
		l1: while(true){
			System.out.print("\nEnter an option to continue > ");
			int op = input.nextInt();
		
			if(op == 1){
				addSupplier();
			}else if(op == 2){
				updateSupplier();
			}else if(op == 3){
				deleteSupplier();
			}else if(op == 4){
				viewSupplier();
			}else if(op == 5){
				searchSupplier();
			}else if(op == 6){
				homePage();
			}else{
				System.out.println("Invalid Option.Try Again !");
				continue l1;
			}
			return;
		}
	}
	
	public static void addSupplier(){
		l1:	while(true){
			printTopic("add supplier");
			
			l2: while(true){
				System.out.print("Supplier ID : ");
				String id = input.next();
				for(int i=0;i<suppliers.length;i++){
					if(id.equals(suppliers[i][0])){
						System.out.println("already exists. try another supplier id !\n");
						continue l2;
					}
				}
				
				System.out.print("Supplier Name : ");
				String name = input.next();
				String [][] temp = new String [suppliers.length+1][2];
				temp[temp.length-1][0] = id;
				temp[temp.length-1][1] = name;
				
				for(int i =0;i<suppliers.length;i++){
					for(int j =0;j<suppliers[i].length;j++){
						temp[i][j] = suppliers[i][j];
					}
				}
				suppliers= temp;
				System.out.print("Added successfully !  Do you want to add another supplier (Y/N) ? ");
				char ans = input.next().charAt(0);
				if((ans == 'Y')||(ans == 'y')){
					continue l1;
				}
				supplierManage();
				return;
			}
		}
	}
	
	public static void updateSupplier(){
		l1:	while(true){
			printTopic("update supplier ");
			
			l2: while (true){
				System.out.print("Supplier ID : ");
				String id = input.next();
		
				for(int i=0;i<suppliers.length;i++){
					if(id.equals(suppliers[i][0])){
						System.out.println("Supplier Name : " +  suppliers[i][1]);
						System.out.print("\nEnter the new supplier name : ");
						suppliers[i][1] = input.next();
					
						System.out.print("Updated Successfully ! Do you want to update another supplier ? (Y/N) ");
						char ans = input.next().charAt(0);
						if((ans == 'Y') ||( ans == 'y')){
							continue l1;
						}
						supplierManage();
						return;
					}else{
						if(i == (suppliers.length-1)){
							System.out.println("Can't find supplier id.try again !\n");
							continue l2;
						}
					}
				}
			}
		}
	}
	
	public static void deleteSupplier(){
		l1:	while(true){
			printTopic("delete supplier ");
			
			l2: while(true){
				System.out.print("Supplier ID : ");
				String id = input.next();
			
				for(int i=0;i<suppliers.length;i++){
					if(id.equals(suppliers[i][0])){
					String [][] temp = new String [suppliers.length-1][2];
					l3: for(int j=0,k=0;j<temp.length;k++){
						if(id.equals(suppliers[k][0])){
							continue l3;
						}
					
						temp[j][0] = suppliers[k][0];
						temp[j][1] = suppliers[k][1];
						j++;
					}
						suppliers = temp;
					
						System.out.print("deleted successfully ! Do you want to delete another ? (Y/N) ");
						char ans = input.next().charAt(0);
						if((ans == 'Y') ||( ans == 'y')){
							continue l1;
						}else{
							supplierManage();
							return;
						}
					}else{
						if(i == suppliers.length-1){
							System.out.println("can't find supplier id.try again !\n");
							continue l2;
						}
					}
				}
			}
		}
	}
	
	public static void viewSupplier(){
		printTopic("view supplier ");
		
		System.out.printf("+-------------------------------------------------+%n");
		System.out.printf("|%18S%6s|%18s%6s|%n","SUPPLIER ID"," ","SUPPLIER NAME"," ");
		System.out.printf("+-------------------------------------------------+%n");

		for(int i =0;i<suppliers.length;i++){
			System.out.printf("|%14S%10s|%8s%-16s|%n",suppliers[i][0]," ","",suppliers[i][1]);
		}
		System.out.printf("+-------------------------------------------------+%n");
		
		System.out.print("Do you want to go Supplier manager page (Y/N) ? ");
		char ans = input.next().charAt(0);
		if((ans == 'Y') ||( ans == 'y')){
			supplierManage();
			return;
		}
		return;
	}
	
	public static void searchSupplier(){
		l1:	while(true){
			printTopic(" search supplier ");
			
			l2: while(true){
				System.out.print("Supplier ID : ");
				String id = input.next();
				for(int i=0;i<suppliers.length;i++){
					if(id.equals(suppliers[i][0])){
						System.out.print("Supplier Name : " + suppliers[i][1]);
						System.out.print("\nSearch successfully !Do you want to add another find (Y/N) ?");
						char ans = input.next().charAt(0);
			
						if((ans == 'Y') ||( ans == 'y')){
							continue l1;
						}
						supplierManage();
						return;
					}
					if((i == suppliers.length-1)||(i == 0)){
						System.out.println("can't find supplier id.try again !\n");
						continue l2;
					}
				}
			return;
			}
		}
	}
	
	public static void stockManage(){
		printTopic("stock management  ");
		System.out.printf("%-50s%-30s%n%-50s%-30s%n%-50s%-30s%n","[1] Manage Item Categories","[2] Add Item","[3] Get Items Supplier Wise","[4] View Items","[5] Rank Items Per Unit Price","[6] Home Page");
		l1: while(true){
			System.out.print("\nEnter an option to continue >");
			int op = input.nextInt();
		
			if(op == 1){
				manageItemCategories();
			}else if(op ==2){
				addItem();
			}else if(op==3){
				getItemSupplierWise();
			}else if(op ==4){
				viewItem();
			}else if(op ==5){
				rankItemsPerUnitPrice();
			}else if(op ==6){
				homePage();
			}else{
				System.out.println("Invalid Option.Try Again !");
				continue l1;
			}
		return;
		}
	}
	
	public static void manageItemCategories(){
		printTopic(" manage item category ");
		System.out.printf("%-50s%-30s%n%-50s%-30s%n","[1] Add New Item Category","[2] Delete Item Category","[3] Update Item Category","[4] Stock Management");
		
		l1: while(true){
			System.out.print("\nEnter an option to continue >");
			int op = input.nextInt();
		
			if(op == 1){
				addNewItemCategory();
			}else if(op ==2){
				deleteItemCategory();
			}else if(op==3){
				updateItemCategory();
			}else if(op==4){
				stockManage();
			}else{
				System.out.println("Invalid Option.Try Again!");
				continue l1;
			}
			return;
		}
	}
	
	public static void addNewItemCategory(){
		l1:	while(true){
			printTopic("add item category ");
			
			System.out.print("Enter the new item category :");
			String cat = input.next();
			String []temp = new String [categories.length+1]; 
			
			for(int i=0;i<categories.length;i++){
				temp[i] = categories[i];
			}
			temp[temp.length-1]= cat;
			categories = temp;
			
			System.out.print("added successfully! Do you want to add another category(Y/N)?");
			char ans = input.next().charAt(0);
			if((ans == 'Y') ||( ans == 'y')){
				System.out.println();
				continue l1;
			}
			manageItemCategories();
			return;
		}
	}
	
	public static void deleteItemCategory(){
		l1: while(true){
			printTopic(" delete item category ");
			
			l2: while(true){
				System.out.print("Enter Item  Category : ");
				String item = input.next();
				
				String [] temp = new String [categories.length -1];
				for(int i =0;i<categories.length;i++){
					if(item.equals(categories[i])){
						l3: for(int j=0,k=0;j<categories.length;j++){
							if(item.equals(categories[j])){
								continue l3;
							}
							temp[k] = categories[j];
							k++;
						}
						categories = temp;
						System.out.print("Category deleted successfully ! Do you want delete another category ? (Y/N)");
						char ans = input.next().charAt(0);
						if((ans == 'Y') ||( ans == 'y')){
							continue l1;
						}
						manageItemCategories();
						return;
					}else{
						if(i == categories.length-1){
							System.out.println("Can't find Item Category.Please Try again !\n");
							continue l2;
						}
					}
				}
			}
		}
	}
	
	public static void updateItemCategory(){
		l1:	while(true){
			printTopic(" update item category ");
			
			l2:while(true){
				System.out.print("Enter Item Category : ");
				String item = input.next();
		
				for(int i =0;i<categories.length;i++){
					if(item.equals(categories[i])){
					
						System.out.print("Enter New Item Category : ");
						categories[i] = input.next();
						System.out.print("Item Category Updated Successfully ! Do you want update another category ? (Y/N)");
						char ans = input.next().charAt(0);
				
						if((ans == 'Y') ||( ans == 'y')){
							continue l1;
						}else{
							manageItemCategories();
							return;
						}
					}else{
						if(i == categories.length-1){
							System.out.println("Can't find That Item Category.Please Try again !\n");
							continue l2;
						}
					}
				}
			}
		}
	}
	
	public static void addItem(){
		l1:	while(true){
			printTopic(" add item ");
			
			if(categories.length==0){
				System.out.print("OOPS ! It seems that you don't have any item categories in the system.");
				System.out.print("\nDo you want to add a new item category ?(Y/N)");
				char ans = input.next().charAt(0);
				System.out.println();
					if((ans == 'Y') ||( ans == 'y')){
						addNewItemCategory();
						return;
					}else{
						stockManage();
						return;
					}
			}else if (suppliers.length ==0){
				System.out.print("OOPS ! It seems that you don't have any suppliers in the system.");
				System.out.print("\nDo you want to add a new supplier  ?(Y/N)");
				char ans1 = input.next().charAt(0);
				
				if((ans1 == 'Y') ||( ans1 == 'y')){
					addSupplier();
					return;
				}else{
					stockManage();
					return;
				}
			}
			l3: while(true){
				System.out.print("Item Code : ");
				String code = input.next();
				
				for(int g=0;g < stocks.length;g++){
					if(code.equals(stocks[g][0])){
						System.out.println("already added .try another item code !\n");
						continue l3;
					}
				}
				
				String [][] temp = new String [stocks.length+1][7];
					
				for(int m=0;m<stocks.length;m++){
					for(int n=0;n<stocks[m].length;n++){
						temp[m][n] = stocks [m][n];
					}
				}
				temp[temp.length-1][0] = code;
				
				System.out.println();
				System.out.println("Suppliers list :");
				System.out.printf("+-------------------------------------------------------+%n");
				System.out.printf("|%5s %-5s|%15s %-5s|%15s %-5s|%n","#" ," ","SUPPLIER ID"," ","SUPPLIER NAME"," ");
				System.out.printf("+-------------------------------------------------------+%n");
			
				for(int j =0;j<suppliers.length;j++){
					System.out.printf("|%5d %-5s|%12s %-8s|%4s %-16s|%n",(j+1)," ",suppliers[j][0]," " ," ",suppliers[j][1]);
				}	
				System.out.printf("+-------------------------------------------------------+%n");
				System.out.print("\nEnter The Supplier number > ");
				int num = input.nextInt();
				temp[temp.length-1][1] = suppliers[num-1][0];
				temp[temp.length-1][2] = suppliers[num-1][1];
				
				System.out.println();
				System.out.println("Item Categories :");
				System.out.printf("+-----------------------------------+%n");
				System.out.printf("|%5s  %-4s| %2s %-18s |%n","#" ," "," ","CATEGORY NAME");
				System.out.printf("+-----------------------------------+%n");
			
				for(int k =0;k<categories.length;k++){
					System.out.printf("|%5d %-4s | %5s %-15s |%n",(k+1)," "," ",categories[k]);
				}
				System.out.printf("+-----------------------------------+%n");
				System.out.print("\nEnter the category number > ");
				int no = input.nextInt();
				temp[temp.length-1][3] = categories[no-1];
					
					
				System.out.print("Description : ");
				temp[temp.length-1][4] = input.next();
				System.out.print("Unit Price  : ");
				temp[temp.length-1][5] = input.next();
				System.out.print("Qty on Hand  : ");
				temp[temp.length-1][6] = input.next();
					
				stocks = temp;
					
				System.out.print("Added successfully! Do you want to add another item (Y/N) ? ");
				char an = input.next().charAt(0);
				if((an == 'Y') ||( an == 'y')){
					continue l1;
				}else{
					stockManage();
					return;
				}
			}
		}
	}
	
	public static void getItemSupplierWise(){
		l1: while(true){
			printTopic(" search items supplier wise ");
			
			l2: while(true){
				System.out.print("Enter Supplier Id : ");
				String id = input.next();
				l3: for(int i =0;i<suppliers.length;i++){
					if(id.equals(suppliers[i][0])){
					
						System.out.print("Supplier Name : " + suppliers[i][1]);
						System.out.println("\n");
					
						System.out.printf("+---------------------------------------------------------------------+%n");
						System.out.printf("| %10s | %-10s | %-10s | %-10s | %-10s | %n","ITEM CODE ","DESCRIPTION","UNIT PRICE "," QTY ON HAND "," CATEGORY");
						System.out.printf("+---------------------------------------------------------------------+%n");
			
						for(int j =0;j<stocks.length;j++){
							if(id.equals(stocks[j][1])){
								System.out.printf("| %6s %-3s | %7s %-3s | %7s %-3s | %7s %-5s | %7s %-2s | %n",stocks[j][0]," " ,stocks[j][4]," ",stocks[j][5]," ",stocks[j][6]," ",stocks[j][3]," ");
							}
						}
						System.out.printf("+---------------------------------------------------------------------+%n");
						System.out.print("Search successfully! Do you want to another search ? (Y/N)");
						char ans = input.next().charAt(0);
				
						if((ans == 'Y') ||( ans == 'y')){
							continue l1;
						}else{
							stockManage();
							return;
						}
					}else{
						if(i == suppliers.length-1){
							System.out.println("can't find supplier id.try again !\n");
							continue l2;
						}else{
							continue l3;
						}
					}
				}
			}
		}
	}
	
	public static void viewItem(){
		printTopic(" view items ");
		
		for(int i =0;i<categories.length;i++){
			System.out.println();
			System.out.println( categories[i] + " :");
			System.out.printf("+----------------------------------------------------------+%n");
			System.out.printf("|%6s %-3s|%6s %-3s|%6s %-5s| %8s | %10s | %n","SID "," ","CODE"," ","DESC"," "," PRICE "," QTY ");
			System.out.printf("+----------------------------------------------------------+%n");
			for(int j= 0;j<stocks.length;j++){
				if(categories[i].equals(stocks[j][3])){
					System.out.printf("|%6s %-3s|%6s %-3s|%s %-10s| %8s | %10s | %n",stocks[j][1]," ",stocks[j][0]," "," ",stocks[j][4],stocks[j][5],stocks[j][6]);
				}
			}
			System.out.printf("+----------------------------------------------------------+%n");
		}
		System.out.print("Do you want to go stock manage page ? (Y/N)");
		char ans = input.next().charAt(0);
		System.out.println();
		if((ans == 'Y') ||( ans == 'y')){
			stockManage();
			return;
		}else{
			return;
		}
	}
	
	public static void rankItemsPerUnitPrice(){
		printTopic("ranked unit price ");
		System.out.println();
			
		int [] price = new int [stocks.length];
		for(int i=0;i<price.length;i++){
			price[i] =  Integer.parseInt(stocks[i][5]);
		}
		for(int i = 0;i<price.length-1;i++){
			for(int j=0;j<price.length-1;j++){
				if(price[j]>price[j+1]){
					int temp = price[j];
					price[j] = price[j+1];
					price[j+1] = temp;
				}
			}
		}
		String [][] temp = new String [stocks.length][stocks[0].length];
		for(int i =0;i<price.length;i++){
			l2:for(int j=0;j<stocks.length;j++){
				if(price[i] == Integer.parseInt(stocks[j] [5])){
					for(int k=0;k<stocks[i].length;k++){
						temp[i][k] = stocks[j][k];
					}
				}
			}
		}
		stocks = temp;
		System.out.printf("+-------------------------------------------------------------------------------+%n");
		System.out.printf("|%7s %3s|%7s %3s|%10s %5s|%7s %3s|%7s %3s|%9s %4s| %n","SID"," ","CODE"," ","DESC"," ","PRICE"," ","QTY"," ","CAT","");
		System.out.printf("+-------------------------------------------------------------------------------+%n");
			
		for(int j =0;j<stocks.length;j++){
			System.out.printf("|%7s %3s|%7s %3s|%5s %-10s|%7s %3s|%7s %3s|%-4s%-10s| %n",stocks[j][1]," ",stocks[j][0]," "," ",stocks[j][4],stocks[j][5]," ",stocks[j][6]," ","",stocks[j][3]);
		}	
		System.out.printf("+-------------------------------------------------------------------------------+%n");
		System.out.print("Do you want to go stock manage page ? (Y/N)");
		char ans = input.next().charAt(0);
		if((ans == 'Y') ||( ans == 'y')){
			stockManage();
			return;
		}else{
			return;
		}
	}
}
