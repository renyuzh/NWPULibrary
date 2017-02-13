package com.npu.library.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npu.library.dao.BaseDAO;

@Repository("baseDAO")
@SuppressWarnings("all")
@Transactional
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	public Serializable save(T o) {
   
		return this.getCurrentSession().save(o);

	}

	public void delete(T o) {

		Session session = this.getCurrentSession();
		// Transaction tran =session.beginTransaction();
		session.delete(o);
		// session.flush();
		// tran.commit();
	}

	public void update(T o) {
		/*Session session = this.getCurrentSession();
		Transaction tran =session.beginTransaction();*/
		this.getCurrentSession().update(o);
/*		 session.flush();
		tran.commit();*/

	}

	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
			/*if(param[i] == null)
				continue*/;
				q.setParameter(i, param[i]);
				System.out.println(param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Transactional
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}
}
