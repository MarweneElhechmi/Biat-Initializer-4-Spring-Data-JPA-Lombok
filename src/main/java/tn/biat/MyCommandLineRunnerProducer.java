package tn.biat;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tn.biat.entities.Compte;
import tn.biat.repository.ICompteRepository;

@Configuration
public class MyCommandLineRunnerProducer {

	@Autowired
	private ICompteRepository repo;
	@Autowired
	private ApplicationContext ctxt;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private EntityManager entityManager;	

	@Bean
	CommandLineRunner myRunner() {
		return (args)->{
			System.out.println("***************************************************************************");
			Compte resultat = entityManager.find(Compte.class, "A100");
			System.out.println(resultat);
			System.out.println("***************************************************************************");
			System.out.println("La dataSource configurée pour tous beans est: "+dataSource.getClass().getSimpleName());
			System.out.println("***************************************************************************");

			System.out.println("Liste de tous les beans du Context Spring");
			for (String bean : ctxt.getBeanDefinitionNames()) {
				System.out.println("=====>"+" "+bean);
			}
			
			System.out.println("***************************************************************************");
			System.out.println("Il y avait pour tous : "+" "+ctxt.getBeanDefinitionCount()+" beans !");
			repo.save( new Compte("A100","Marwene",new BigDecimal("100")));
			repo.save( new Compte("A100","Marwene",new BigDecimal("100")));
			repo.save( new Compte("A100","Marwene",new BigDecimal("100")));
					
					System.out.println("******************* FIN CREATION COMPTE ***********************");
					
					repo.findAll().stream().forEach(System.out::println);
					System.out.println("***************************************************************************");

		};
		
	}
	
}
