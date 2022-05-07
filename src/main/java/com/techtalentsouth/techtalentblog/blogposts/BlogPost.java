package com.techtalentsouth.techtalentblog.blogposts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
/*3. @Entity puts the attributes inside of a database*/
public class BlogPost {
//1. There will be 4 attributes: id(primary key), Title, Author, and Blog Entry
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//3.1. We need to store the primary key in the database too
	private Long id;
	private String title;
	private String author;
	private String blogEntry;
	
	/*2. Once you put in your attributes you need to be stored into a database, which we will use the annotation called entity ontop of the blogpost*/
	public BlogPost() {
	}
	
	
	/*4. Now we create a constructor*/
	public BlogPost(String title, String author, String blogEntry) {
		this.title = title;
		this.author = author;
		this.blogEntry = blogEntry;
}


	/*5. Generate Getters and setters to get everything auto*/
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getBlogEntry() {
		return blogEntry;
	}


	public void setBlogEntry(String blogEntry) {
		this.blogEntry = blogEntry;
	}


	public Long getId() {
		return id;
	}


	@Override
	/*6. Generate toString is a method that is called when you are trying to print the value or convert it back to a string.*/
	public String toString() {
		return "BlogPost [id=" + id + ", title=" + title + ", author=" + author + ", blogEntry=" + blogEntry + "]";
	}
	
	
}
