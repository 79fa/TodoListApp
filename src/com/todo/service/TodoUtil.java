package com.todo.service;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		String current_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Create item Section\n"
				+ "enter the title\n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("enter the description");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("item added");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n"
				+ "========== Delete Item Section\n"
				+ "enter the title of item to remove\n"
				+ "\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("item delted");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Edit Item Section\n"
				+ "enter the title of the item you want to update\n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}

		System.out.println("enter the new title of the item");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("enter the new description ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("item updated");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
		}
	}
	public static void loadList(TodoList l,String filename) {
		//FileReader reader=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/Users/hasyhasy2001/Desktop/"+filename));
			String str;
			
			while((str = reader.readLine()) != null){
				String []tokens=str.split("##");
				TodoItem a = new TodoItem(tokens[0],tokens[1]);
				a.setCurrent_date(tokens[2]);
				l.addItem(a);
	            //System.out.println(str);
	        }
			
			reader.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("파일을 읽을 수 없습니다.");
		}
		
		
	}
	public static void saveList(TodoList l,String filename) throws IOException {
		FileWriter writer=new FileWriter("/Users/hasyhasy2001/Desktop/"+filename);
		
		for (TodoItem item : l.getList()) {
			String str=item.toSaveString();
			System.out.println(str);
			writer.write(str);
		}
		writer.close();
		
		
		
	}
}
