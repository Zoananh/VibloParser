import parser.parser;
import entity.infoEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.infoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class main {
	
	public static void main(String[] args) throws IOException{
		List<String> keywords = new ArrayList<String>();
		keywords.add("java");
		parser pr = new parser();
		List<infoEntity> entList = pr.parse(keywords);
		
		final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate_cfg.xml")
                        .build();
		SessionFactory sessionFactory = null;
		try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);        
            System.exit(1);
        }
		
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
        	for(infoEntity ent:entList) {
    	    	session.save(ent);
    	    }
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();            
        }
        finally {
            session.close();
        }
	    
	    session.close();
    
    }
}


