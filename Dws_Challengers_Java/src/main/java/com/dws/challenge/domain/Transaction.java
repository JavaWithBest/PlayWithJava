package com.dws.challenge.domain;


import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String fromAccountId;
	private String toAccountId;
	private BigDecimal amount;
	
	
	
	
}
