package com.mmdev.dictionaryy.entity.admins;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "super_admin", catalog = "dictionary", schema = "public")
public class SuperAdmin {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;

}
