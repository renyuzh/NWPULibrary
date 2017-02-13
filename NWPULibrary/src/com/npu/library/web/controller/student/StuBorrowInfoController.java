package com.npu.library.web.controller.student;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Book;
import com.npu.library.dao.domain.BookStudent;
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
@RequestMapping("/student/borrowInfo")
public class StuBorrowInfoController {

    @Resource
    private BaseDAO<BookStudent> bookStudentDao;

    @Resource
    private BaseDAO<Book> bookDao;


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "student/borrow-info";
    }



    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<BookStudent> list(HttpServletRequest request, ModelMap model, BookStudent bookStudent) {
        Integer sid = (Integer) request.getSession().getAttribute(Constant.user_id);
        bookStudent.setSid(sid);
        List<BookStudent> bookStudents = bookStudentDao.find("from com.npu.library.dao.domain.BookStudent where sid = "+sid);
        PageBean<BookStudent> data = new PageBean<BookStudent>();
        data.setData(bookStudents);
        return data;
    }


    @ResponseBody
    @RequestMapping(value = "/findBook", method = {RequestMethod.GET, RequestMethod.POST})
    public Book findBook(Book book){
    	String sql = "from com.npu.library.dao.domain.Book where bno = ?";
        String[] para =  { book.getBno() };
    	List<Book> books =  bookDao.find(sql,para);
    	if(books == null || books.size() == 0)
    		return null;
    	else
    		return books.get(0);
    				
    }
}