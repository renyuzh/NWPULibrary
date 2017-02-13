package com.npu.library.web.controller.student;

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

import java.util.List;

@Controller
@RequestMapping("/student/book")
public class StuBookController {

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
        return "student/book";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Book> list(ModelMap model, Book book) {
    	String sql = "from com.npu.library.dao.domain.Book";
        /*String[] para =  { book.getBno() };*/
        List<Book> books = bookDao.find(sql);
        PageBean<Book> data = new PageBean<Book>();
        data.setData(books);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Book findById(ModelMap model, Book book) {
       List<Book> books = bookDao.find("from com.npu.library.dao.domain.Book where id ="+book.getId());
       if(books == null || books.size() ==0 )
    	   return null;
       return books.get(0);
    }
}