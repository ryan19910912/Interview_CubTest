package com.example.cubtest.entity.db;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "currency")
public class CurrencyVO implements Serializable {

	private static final long serialVersionUID = -3932103423689874854L;

	@Id
	private String code;
	
	private String name;
	
	
}
