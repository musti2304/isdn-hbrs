package model;

import java.util.List;

import org.hibernate.SessionFactory;

public interface IOrtsDAO {
	public void setSessionFactory(SessionFactory sessionFactory);
	public void saveOrt(IAbstractOrt abstractOrt);
	public List<IAbstractOrt> getOrte ();
}
