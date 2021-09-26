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
		String category,due_date;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Create item Section\n"
				+ "enter the title\n");
		
		title = sc.next();
		if (list.isDuplicate(title)){
			System.out.printf("title can't be duplicate\n");
			return;
		}
		sc.nextLine();
		System.out.println("enter the description");
		desc = sc.nextLine().trim();
		
		System.out.println("enter the category");
		category=sc.next();
		
		System.out.println("enter the deadline (20xx/mm/dd)");
		due_date=sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		t.setCategory(category);
		t.setDue_date(due_date);		
		
		
		list.addItem(t);
		System.out.println("item added");
	}
	public static void find(TodoList l,String key) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if (item.toSaveString().contains(key)) {
				System.out.println(count+1+". ["+item.getCategory()+"] " + item.getTitle()+" - " + item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
				count++;
			}
			
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");
	}
	public static void find_cate(TodoList l,String key) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if (item.getCategory().contains(key)) {
				System.out.println(count+1+". ["+item.getCategory()+"] " + item.getTitle()+" - " + item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
				count++;
			}
			
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");
	}

	public static void deleteItem(TodoList l) { //없는 번호일때 처리하는 함수 넣기 
		
		Scanner sc = new Scanner(System.in);
		//String title = sc.next();
		
		System.out.println("\n"
				+ "========== Delete Item Section\n"
				+ "enter the number of item to remove\n"
				+ "\n");
		
		int n=sc.nextInt();
		l.at(n);
		
		/*
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("item delted");
				break;
			}
		}*/
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Edit Item Section\n"
				+ "enter the number of the item you want to update\n"
				+ "\n");
		int n=sc.nextInt();
		l.at(n);  //지우는 함수 
		
		System.out.println("enter the new title of the item");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("enter the new description ");
		String new_description = sc.nextLine().trim();
		
		
		System.out.println("enter the new category");
		String new_category=sc.next();
		
		System.out.println("enter the new deadline (20xx/mm/dd)");
		String new_due_date=sc.next();
		
		TodoItem t = new TodoItem(new_title, new_description);
		t.setCategory(new_category);
		t.setDue_date(new_due_date);
		
		l.addItem(t);
		System.out.println("item updated");
		
		
		
		/*
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}

		
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("item updated");
			}
		}*/

	}

	public static void listAll(TodoList l) {
		
		
		System.out.println("Number of Items  "+l.size1());
		int count=1;
		
		for (TodoItem item : l.getList()) {
			System.out.println(count+". ["+item.getCategory()+"] " + item.getTitle()+" - " + item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			count++;
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
				a.setCategory(tokens[3]);
				a.setDue_date(tokens[4]);
				l.addItem(a);
	            //System.out.println(str);
	        }
			System.out.println("파일을 읽었습니다.");
			
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
			//System.out.println(str);
			writer.write(str);
		}
		writer.close();
		
		
		
	}
}
