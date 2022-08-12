package com.gestion.hotel.Repositorio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseRepositorio {

	public Session session;
	@Autowired
	public SessionFactory sessionFactory;
	boolean flagConnectionClose;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void checkSession(){
		flagConnectionClose=false;
		if(this.session!=null){
			if(!this.session.isOpen()){
				flagConnectionClose=true;
			}
		}else{
			flagConnectionClose=true;
		}
		
		if (flagConnectionClose){
			this.session= this.sessionFactory.openSession();
			//this.session = this.sessionFactory.getCurrentSession();
		}
	}
	
	public void closeSession() {
//		this.session.flush();
//		if (this.session.isOpen()) {
//			this.session.close();
//		}
//		this.session=null;
		//this.sessionFactory.close();
		
	}
}
