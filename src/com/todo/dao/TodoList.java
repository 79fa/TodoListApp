package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;
	
	public void at(int n) {
		list.remove(n-1);
	}

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		
		System.out.println("Number of Items  "+list.size());
		int count1=1;
		for (TodoItem myitem : list) {
			//System.out.println(myitem.getTitle() +"    "+ myitem.getDesc()+);
			System.out.println(count1+". ["+myitem.getCategory()+"] " + myitem.getTitle()+" - " + myitem.getDesc()+" - "+myitem.getDue_date()+" - "+myitem.getCurrent_date());
			count1++;
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}
	
	public int size1() {
		return list.size();
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
