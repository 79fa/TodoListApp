package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. Add a new item ( add )");
        System.out.println("2. Delete an existing item ( del )");
        System.out.println("3. Update an item  ( edit )");
        System.out.println("4. List all items ( ls )");
        System.out.println("5. sort the list by name ( ls_name_asc )");
        System.out.println("6. sort the list by name ( ls_name_desc )");
        System.out.println("7. sort the list by date ( ls_date )");
        System.out.println("7. sort the list by date ( ls_date_desc )");
        System.out.println("9. find word ( find <keyword> )");
        System.out.println("10. find word in category ( find_cate <keyword>)");
        System.out.println("11. list all categories ( ls_cate )");
        System.out.println("12. exit (Or press escape key to exit)");
        //System.out.println("Enter your choice >");
    }
    public static void prompt() {
    	System.out.print("Enter your choice >");
    }
}
