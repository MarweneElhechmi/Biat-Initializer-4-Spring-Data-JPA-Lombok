package tn.biat.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="Comptes")
@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
	@Id
	private String numero;
	private String proprietaire;
	private BigDecimal solde;

}
