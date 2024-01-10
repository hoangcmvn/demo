/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Book;
import modal.Category;
import modal.Language;
import modal.Publisher;

/**
 *
 * @author pv
 */
public class BookDBContext extends DBContext{
    public void insert(Book b){
        try {
            String sql = "INSERT INTO [Book]\n" +
                        "           ([name]\n" +
                        "           ,[publication_year]\n" +
                        "           ,[number_pages]\n" +
                        "           ,[img]\n" +
                        "           ,[Description]\n" +
                        "           ,[author]\n" +
                        "           ,[publisher_id]\n" +
                        "           ,[language_id]\n" +
                        "           ,[location]\n" +
                        "           ,[category_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?)"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, b.getName());
            stm.setInt(2, b.getPublicationYear());
            stm.setInt(3, b.getNumberPages());
            stm.setString(4, b.getImg());
            stm.setString(5, b.getDescription());
            stm.setString(6, b.getAuthor());
            stm.setInt(7, b.getPublisher().getId());
            stm.setInt(8, b.getLanguage().getId());
            stm.setString(9, b.getLocation());
            stm.setInt(10, b.getCategory().getId());
            stm.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    public void update(Book b){
        try {
            String sql = "UPDATE [Book]\n" +
                    "   SET [name] = ?\n" +
                    "      ,[publication_year] = ?\n" +
                    "      ,[number_pages] = ?\n" +
                    "      ,[img] = ?\n" +
                    "      ,[Description] = ?\n" +
                    "      ,[author] = ?\n" +
                    "      ,[publisher_id] = ?\n" +
                    "      ,[language_id] = ?\n" +
                    "      ,[category_id] = ?\n" +
                    "      ,[location] = ?\n" +
                    " WHERE book_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql); 
            stm.setString(1, b.getName());
            stm.setInt(2, b.getPublicationYear());
            stm.setInt(3,b.getNumberPages());
            stm.setString(4, b.getImg());
            stm.setString(5,b.getDescription());
            stm.setString(6, b.getAuthor());
            stm.setInt(7,b.getPublisher().getId());
            stm.setInt(8,b.getLanguage().getId()); 
            stm.setInt(9, b.getCategory().getId());
            stm.setString(10, b.getLocation());
            stm.setInt(11, b.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(int bid){
        try {
            String sql = "DELETE FROM [Book]\n" +
                    "WHERE book_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public boolean checkExistBook(String bname, int publicationYear, String author, int cid, int pid, int lid){
        try {
            String sql = "select * from Book where [name] like ? and publication_year = ? and author like ?\n" +
                    " and publisher_id = ? and language_id = ? and category_id = ? "; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+ bname +"%");
            stm.setInt(2, publicationYear);
            stm.setString(3, "%"+ author +"%");
            stm.setInt(4, pid);
            stm.setInt(5, lid);
            stm.setInt(6, cid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
      } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;  
    }
    public ArrayList<Book> getTop10books(){
        ArrayList<Book> top10books = new ArrayList<>(); 
        try {
            String sql = "select top(10) book_id, b.[name] as bname, publication_year, number_pages, img, [Description], author, \n" +
                        "                 p.publisher_id, p.[name] as pname, l.language_id, l.name_language as lname, c.category_id, c.[name] as cname, [location] \n" +
                        "from Book as b inner join Publisher as p on b.publisher_id = p.publisher_id \n" +
                        "               inner join Categories as c on b.category_id = c.category_id\n" +
                        "		inner join [Language] as l on b.language_id = l.language_id\n" +
                        "order by b.book_id desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Book book = new Book(); 
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("bname"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setNumberPages(rs.getInt("number_pages"));
                book.setImg(rs.getString("img"));
                book.setDescription(rs.getString("Description"));
                book.setAuthor(rs.getString("author"));
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("pname"));
                book.setPublisher(p);
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("cname"));
                book.setCategory(c);
                Language l = new Language(); 
                l.setId(rs.getInt("language_id"));
                l.setName(rs.getString("lname"));
                book.setLanguage(l);
                book.setLocation(rs.getString("location"));
                top10books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return top10books;
    }
    public ArrayList<Book> getTop5publicYear(){
        ArrayList<Book> top10books = new ArrayList<>(); 
        try {
            String sql = "select top(5) book_id, b.[name] as bname, publication_year, number_pages, img, [Description], author, \n" +
                        "                 p.publisher_id, p.[name] as pname, l.language_id, l.name_language as lname, c.category_id, c.[name] as cname, [location] \n" +
                        "from Book as b inner join Publisher as p on b.publisher_id = p.publisher_id \n" +
                        "               inner join Categories as c on b.category_id = c.category_id\n" +
                        "		inner join [Language] as l on b.language_id = l.language_id\n" +
                        "order by b.number_pages desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Book book = new Book(); 
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("bname"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setNumberPages(rs.getInt("number_pages"));
                book.setImg(rs.getString("img"));
                book.setDescription(rs.getString("Description"));
                book.setAuthor(rs.getString("author"));
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("pname"));
                book.setPublisher(p);
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("cname"));
                book.setCategory(c);
                Language l = new Language(); 
                l.setId(rs.getInt("language_id"));
                l.setName(rs.getString("lname"));
                book.setLanguage(l);
                book.setLocation(rs.getString("location"));
                top10books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return top10books;
    }
    public ArrayList<Book> getAllBooks(int pageIndex, int pageSize){
        ArrayList<Book> books = new ArrayList<>(); 
        try {
            String sql = "select * from " +
            "( select ROW_NUMBER() OVER (order by b.book_id desc) as row_index, \n" +
            "  book_id, b.[name] as bname, publication_year, number_pages, img, [Description], author, \n" +
            "  p.publisher_id, p.[name] as pname, l.language_id, l.name_language as lname, c.category_id, c.[name] as cname, [location] \n" +
            "  from Book as b inner join Publisher as p on b.publisher_id = p.publisher_id \n" +
            "  inner join Categories as c on b.category_id = c.category_id\n" +
            "  inner join [Language] as l on b.language_id = l.language_id ) as tbl" +
            "  where row_index >= (? - 1) * ? + 1 and row_index <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageIndex);
            ResultSet rs =  stm.executeQuery();
            while(rs.next()){
                Book book = new Book(); 
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("bname"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setNumberPages(rs.getInt("number_pages"));
                book.setImg(rs.getString("img"));
                book.setDescription(rs.getString("Description"));
                book.setAuthor(rs.getString("author"));
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("pname"));
                book.setPublisher(p);
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("cname"));
                book.setCategory(c);
                Language l = new Language(); 
                l.setId(rs.getInt("language_id"));
                l.setName(rs.getString("lname"));
                book.setLanguage(l);
                book.setLocation(rs.getString("location"));
                books.add(book); 
            }
        } catch (SQLException ex) { 
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }
    public int count(){
        try {
            String sql = "Select Count(*) as Total from Book";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs =  stm.executeQuery();
            while(rs.next()){
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public ArrayList<Book> advancedSearch(int cid, int pid, int from, int to, String bname, String author, int pageIndex, int pageSize){
        ArrayList<Book> books = new ArrayList<>(); 
        try {
            String sql = "select * from ( select ROW_NUMBER() OVER(order by b.book_id desc) as row_index, \n" +
                    " book_id, b.[name] as bname, publication_year, number_pages, img, [Description], author, \n" +
                    " p.publisher_id, p.[name] as pname, l.language_id, l.name_language as lname, c.category_id, c.[name] as cname, [location] \n" +
                    " from Book as b inner join Publisher as p on b.publisher_id = p.publisher_id \n" +
                    " inner join Categories as c on b.category_id = c.category_id\n" +
                    " inner join [Language] as l on b.language_id = l.language_id \n" +
                    " And (1=1) ";
            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if(cid != -1){
                sql += "And b.category_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = cid;
                parameters.put(paramIndex, param);
            }
            if(pid != -1){
                sql += "And b.publisher_id =  ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = pid;
                parameters.put(paramIndex, param);
            }
            if(from != -1){
                sql += "And b.publication_year >= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = from;
                parameters.put(paramIndex, param);
            }
            if(to != -1){
                sql += "And b.publication_year <= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = to;
                parameters.put(paramIndex, param);
            }
            if(bname != null){
                sql += "And b.[name] like '%' + ? + '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = bname;
                parameters.put(paramIndex, param);
            }
            if(author != null){
                sql += "And b.author like '%' + ? + '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = author;
                parameters.put(paramIndex, param);
            }
            sql += ") as tbl where row_index >= ( ? - 1 ) * ? + 1 and row_index <= ? * ?";
            // dấu hỏi số 1 của where row_index >= ....
            paramIndex++;
            Object[] param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageIndex; 
            parameters.put(paramIndex, param);
            // dấu hỏi số 2 của where row_index >= ....
            paramIndex++;
            param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageSize; 
            parameters.put(paramIndex, param);
            // dấu hỏi số 3 của where row_index >= ....
            paramIndex++;
            param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageSize; 
            parameters.put(paramIndex, param);
            // dấu hỏi số 4 của where row_index >= ....
            paramIndex++;
            param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageIndex; 
            parameters.put(paramIndex, param);
            PreparedStatement stm = connection.prepareStatement(sql); 
            //parameters
            for (Map.Entry<Integer, Object[]> entry : parameters.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if(type.equals(Integer.class.getName()))
                {
                    stm.setInt(index, Integer.parseInt(value[1].toString()));
                }
                else if (type.equals(String.class.getName()))
                {
                    stm.setString(index, value[1].toString());
                }
            }
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
                Book book = new Book(); 
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("bname"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setNumberPages(rs.getInt("number_pages"));
                book.setImg(rs.getString("img"));
                book.setDescription(rs.getString("Description"));
                book.setAuthor(rs.getString("author"));
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("pname"));
                book.setPublisher(p);
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("cname"));
                book.setCategory(c);
                Language l = new Language(); 
                l.setId(rs.getInt("language_id"));
                l.setName(rs.getString("lname"));
                book.setLanguage(l);
                book.setLocation(rs.getString("location"));
                books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books; 
    }
    public int countAdvanceSearch(int cid, int pid, int from, int to, String bname, String author){
        try {
            String sql = "select Count(*) as Total from ( select ROW_NUMBER() OVER(order by b.book_id desc) as row_index, \n" +
                            "     book_id, b.[name] as bname, publication_year, number_pages, img, [Description], author,\n" +
                            "     p.publisher_id, p.[name] as pname, l.language_id, l.name_language as lname, c.category_id, c.[name] as cname, [location]\n" +
                            "     from Book as b inner join Publisher as p on b.publisher_id = p.publisher_id\n" +
                            "     inner join Categories as c on b.category_id = c.category_id\n" +
                            "     inner join [Language] as l on b.language_id = l.language_id where\n" +
                            "     (1=1) ";
            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if(cid != -1){
                sql += "And b.category_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = cid;
                parameters.put(paramIndex, param);
            }
            if(pid != -1){
                sql += "And b.publisher_id =  ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = pid;
                parameters.put(paramIndex, param);
            }
            if(from != -1){
                sql += "And b.publication_year >= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = from;
                parameters.put(paramIndex, param);
            }
            if(to != -1){
                sql += "And b.publication_year <= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getTypeName();
                param[1] = to;
                parameters.put(paramIndex, param);
            }
            if(bname != null){
                sql += "And b.[name] like '%' + ? + '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = bname;
                parameters.put(paramIndex, param);
            }
            if(author != null){
                sql += "And b.author like '%' + ? + '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = author;
                parameters.put(paramIndex, param);
            }
            sql += ") as t"; 
            PreparedStatement stm = connection.prepareStatement(sql); 
            //parameters
            for (Map.Entry<Integer, Object[]> entry : parameters.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if(type.equals(Integer.class.getName()))
                {
                    stm.setInt(index, Integer.parseInt(value[1].toString()));
                }
                else if (type.equals(String.class.getName()))
                {
                    stm.setString(index, value[1].toString());
                }
            }
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
                return rs.getInt("Total");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public ArrayList<Integer> getPublicationYear(){
        ArrayList<Integer> publicationYears = new ArrayList<>(); 
        try {
            String sql = "select distinct publication_year from book Order by publication_year desc"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int temp = rs.getInt("publication_year"); 
                publicationYears.add(temp); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicationYears;         
    }
    public Book getBook(int bid){
        try {
            String sql = "select b.book_id, b.[name] as bname, b.publication_year, b.number_pages, b.img, b.[Description], \n" +
                    "       b.author, b.publisher_id, p.[name] as pname, b.language_id, l.name_language as lname, b.category_id, c.[name] as cname , b.[location]   \n" +
                    "       from Book as b INNER JOIN Categories as c on b.category_id = c.category_id\n" +
                    "	                   INNER JOIN Publisher as p on b.publisher_id = p.publisher_id\n" +
                    "			   INNER JOIN [Language] as l on b.language_id = l.language_id\n" +
                    "	    where b.book_id = ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bid);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
                Book book = new Book(); 
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("bname"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setNumberPages(rs.getInt("number_pages"));
                book.setImg(rs.getString("img"));
                book.setDescription(rs.getString("Description"));
                book.setAuthor(rs.getString("author"));
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("pname"));
                book.setPublisher(p);
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("cname"));
                book.setCategory(c);
                Language l = new Language(); 
                l.setId(rs.getInt("language_id"));
                l.setName(rs.getString("lname"));
                book.setLanguage(l);
                book.setLocation(rs.getString("location")); 
                return book;
           }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
