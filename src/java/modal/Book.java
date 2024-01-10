/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

/**
 *
 * @author pv
 */
public class Book {
    private int id; 
    private String name; 
    private String img;
    private String author; 
    private String description;
    private int publicationYear;
    private int numberPages; 
    private String location; 
    private Publisher publisher; 
    private Category category;
    private Language language; 

    public Book() {
    }

    public Book(int id, String name, String img, String author, String description, int publicationYear, int numberPages, String location, Publisher publisher, Category category, Language language) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.author = author;
        this.description = description;
        this.publicationYear = publicationYear;
        this.numberPages = numberPages;
        this.location = location;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
    
}
