
import database.connection.ConnectionManager;
import database.crud.PharmacyCrud;
import gui.ManageCustomer;

import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {

            System.out.println("Welcome to Pharmacy Management System");
            System.out.println("======================================\n");

            boolean continueLoop = true;
            while(continueLoop) {
                displayMenuOptions();
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();

                switch(choice){
                    case 0:
                        continueLoop = false;
                        break;
                    case 1:
                        PharmacyCrud.getPharmacyDetail();
                    case 2:
                        new ManageCustomer();
                }
            }
            // close connection only once at the end
            ConnectionManager.closeConnection();
        }

        private static void displayMenuOptions(){
            System.out.println("please select menu option");
            System.out.println("0: to exit");
            System.out.println("1: to display all registered pharmacies");
            System.out.println("2: to register a new Customer");
        }
    }

