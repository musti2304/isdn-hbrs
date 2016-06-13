package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OrtsDAO implements IOrtsDAO {
	Session session;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}

	@Override
	public void saveOrt(IAbstractOrt abstractOrt) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(abstractOrt);
		transaction.commit();
	}

	@Override
	public List<IAbstractOrt> getOrte() {
		Query queryObject = session.createQuery("from Ort");
		return queryObject.list();
	}
}