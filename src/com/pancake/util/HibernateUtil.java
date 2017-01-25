package com.pancake.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**   
* For getting session and session factory.   
* ProjectName：HinberateOne2ManyDemo   
* ClassName：HibernateUtil   
* ClassDescription：   
* Author：Pancake   
* CreateTime：2017年1月25日 下午3:58:53   
* @version        
*/
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;

	static {
		// 创建配置对象,读取hibernate.cfg.xml文件，完成初始化。
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		StandardServiceRegistry ssr = ssrb.build();
		// 创建回话工厂对象
		sessionFactory = config.buildSessionFactory(ssr);
	}

	/**
	 * @return A sessionFactory.
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @return A session.
	 */
	public static Session getSession() {
		session = sessionFactory.openSession();
		return session;
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
		}

	}
}
