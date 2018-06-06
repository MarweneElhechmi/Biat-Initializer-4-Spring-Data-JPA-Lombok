package tn.biat;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

import tn.biat.entities.Compte;
import tn.biat.repository.ICompteRepository;

@SpringBootApplication
public class Application {
	
	@Autowired
	private ICompteRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	
	@Bean
	CommandLineRunner myRunner() {
		return args -> {
			// Step1_Default_Crud();
			
			// Step2_DSL();
			
			// Step3_JPA_QL();
			
			// Step 4 : Paging
			System.out.println(" Pagination --- ");
			repo.findAll(PageRequest.of(1,2)).forEach(System.out::println);
			
		};
	}

	private void Step3_JPA_QL() {
		// Step 3 : JPA QL
		System.out.println(" Via une Query Tous les comptes inférieurs à 200");
		repo.findBySoldeInferieurA(new BigDecimal("200")).forEach(System.out::println);

		System.out.println(" Tous les comptes ayant nom comportant w");
		repo.findByProprietaireComme("%"+"w"+"%").forEach(System.out::println);
	}

	private void Step2_DSL() {
		// Step 2 : DSL
		System.out.println(" Tous les comptes inférieurs à 200");
		repo.findBySoldeIsLessThanEqual(new BigDecimal("200")).forEach(System.out::println);

		System.out.println(" Tous les comptes ayant nom comportant w");
		repo.findByProprietaireIsLike("%w%").forEach(System.out::println);
	}

	private void Step1_Default_Crud() {
		// Step 1 : Utilisation des CRUD par défauts de JPA repositories
		Optional<Compte> resultat = repo.findById("A100");
		System.out.println(resultat);
		System.out.println(resultat.isPresent()?resultat.get():"Inexistant");
		for (Compte compte: repo.findAll(Example.of(new Compte(null,"Marwene",null)))) {
			System.out.println(" ** Un Compte de Marwene ** "+ compte);

		}
	}
	
}
