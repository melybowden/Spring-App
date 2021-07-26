package com.cygnus.books;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String author;
    private Long year;

    public BookClass() {
        this.title = null;
        this.author = null;
        this.year = null;
    }

    public BookClass(String title, String author, Long year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

	public BookClass(BookClass other, Long id) {
		this.title = other.getTitle();
		this.author = other.getAuthor();
		this.year = other.getYear();
		this.id = id;
	}

	public Long getID() {
		return this.id;
	}

	// public void setID(Long id) {
	// 	this.id = id;
	// }

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getYear() {
		return this.year;
	}

	public void setYear(Long year) {
		this.year = year;
	}
}