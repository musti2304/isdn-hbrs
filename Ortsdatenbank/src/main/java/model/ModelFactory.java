package model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ModelFactory {
	private ApplicationContext context;
	
	public ModelFactory(){
		context = new FileSystemXmlApplicationContext("springhibernate.xml");
	}
	
	public ModelFactory(ApplicationContext applicationContext){
		this.context = applicationContext;
	}
	
	public IOrtsListe getOrtsListe(){
		return (IOrtsListe)context.getBean("OrtsListe");
	}

	public IAbstractOrt getAbstractOrt(){
		return (IAbstractOrt)context.getBean("AbstractOrt");
	}
	
	public IOrt getOrt(){
		return (IOrt)context.getBean("Ort");
	}
	
	public IOrtMitBesuchsdatum getOrtMitBesuchsDatum(){
		return (IOrtMitBesuchsdatum)context.getBean("OrtMitBesuchsdatum");
	}
	
	public IOrtsDAO getOrtsDAO() {
		return (IOrtsDAO)context.getBean("OrtsDAO");
	}
}