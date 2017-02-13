package com.npu.library.web.controller.admin;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Book;
import com.npu.library.dao.domain.BookType;
import com.npu.library.web.bean.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/book")
public class AdmBookController {

    @Resource
    private BaseDAO<Book> bookDao;

    @Resource
    private BaseDAO<BookType> bookTypeDao;
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        List<BookType> bookTypes = bookTypeDao.find("from com.npu.library.dao.domain.BookType");
        model.addAttribute("username", username);
        model.addAttribute("bookTypes", bookTypes);
        return "admin/book";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Book> list(ModelMap model, Book book) {
        List<Book> books = bookDao.find("from com.npu.library.dao.domain.Book");
        PageBean<Book> data = new PageBean<Book>();
        data.setData(books);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Book findById(ModelMap model, Book book) {
         List<Book> books = bookDao.find("from com.npu.library.dao.domain.Book where id = "+book.getId());
        if(books == null || books.size() ==0)
        	return null;
        return books.get(0);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, Book book) throws ParseException {
    	String sql = "from com.npu.library.dao.domain.Book where bno = ?";
        String[] para =  { book.getBno() };
        List<Book> books = bookDao.find(sql, para);
        if (books != null && books.size() > 0) {
            return -1;
        }
        String sql2 = "from com.npu.library.dao.domain.BookType where id = "+book.getTid();
    	List<BookType> bookTypes = bookTypeDao.find(sql2);
    	String typeName = bookTypes.get(0).getTname();
    	book.setTname(typeName);
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date cdate = d.parse(d.format(new Date()));
        book.setCdate(cdate);
        bookDao.save(book);
        return  1;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean update(ModelMap model, Book book) throws ParseException {
    	String sql = "from com.npu.library.dao.domain.Book where bno = ?";
    	String sql2 = "from com.npu.library.dao.domain.BookType where id = "+book.getTid();
    	List<BookType> bookTypes = bookTypeDao.find(sql2);
    	String typeName = bookTypes.get(0).getTname();
        String[] para =  { book.getBno() };
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date udate = d.parse(d.format(new Date()));
        Book b = bookDao.find(sql,para).get(0);
        b.setTid(book.getTid());
        b.setTname(typeName);
        b.setBname(book.getBname());
        b.setAuthor(book.getAuthor());
        b.setBrief(book.getBrief());
        b.setPrice(book.getPrice());
        b.setTotal(b.getTotal() + (book.getRemain() - b.getRemain()));
        b.setUdate(udate);
        bookDao.update(b);
       
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, Book book) {
        bookDao.delete(book);
        return true;
    }
}