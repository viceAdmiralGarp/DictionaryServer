package com.mmdev.dictionaryy.entity.admins;

import com.mmdev.dictionaryy.entity.school.School;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "admin", catalog = "dictionary", schema = "public")
public class Admin {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "admin", fetch = LAZY)
	private List<School> schools;

}
