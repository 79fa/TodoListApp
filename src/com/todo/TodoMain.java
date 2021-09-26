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
			System.out.println("");
			String choice = sc.next();
			
			
			switch (choice) {
			case "ls_cate"://떠오른 생각은 한 문자열에 더가면서 거기 안에 있나 확인 
				
			case "find":
				String keyword=sc.next();
				TodoUtil.find(l,keyword);
				//System.out.println(keyword);
				break;
			case "find_cate":
				String keyword2=sc.next();
				TodoUtil.find_cate(l,keyword2);
				//System.out.println(keyword);
				break;
				
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
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList=true;
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
