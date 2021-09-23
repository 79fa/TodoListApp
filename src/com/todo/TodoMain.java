package com.todo;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		
		
		TodoUtil.loadList(l,"todolist.txt");
		do {
			
			isList = false;
			//System.out.print("Enter your choice >");
			Menu.prompt();
			String choice = sc.next();
			
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			case "help":
				Menu.displaymenu();
				break;
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("please enter correct command(need help -help)");
				break;
			}
			
			
			
			if(isList) l.listAll();
		} while (!quit);
		
		TodoUtil.saveList(l,"todolist.txt");
	}
}
