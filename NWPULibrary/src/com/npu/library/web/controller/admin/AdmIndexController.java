package com.npu.library.web.controller.admin;

import java.util.List;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Book;
import com.npu.library.dao.domain.BookType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdmIndexController {
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
        return "admin/index";
    }

}