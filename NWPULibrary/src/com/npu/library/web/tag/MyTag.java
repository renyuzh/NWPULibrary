package com.npu.library.web.tag;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.npu.library.constant.Constant;

public class MyTag extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException {
		HttpSession session = (HttpSession) ((PageContext) this
				.getJspContext()).getSession();
		JspWriter out = getJspContext().getOut();
		out.print(session.getAttribute(Constant.user_name));
	}
}
