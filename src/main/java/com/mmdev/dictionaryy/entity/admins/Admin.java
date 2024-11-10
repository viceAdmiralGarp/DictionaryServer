package com.mmdev.dictionaryy.entity.admins;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.model.admin.AdminDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"school"})
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "admin", catalog = "dictionary", schema = "public")
public class Admin {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	@OneToOne(mappedBy = "admin",
			cascade = ALL,
			fetch = LAZY,//TODO by default eager
			optional = false)
	private School school;

	private String email;

	private String password;

	public AdminDto toDto(){
		return AdminDto.builder()
				.id(id)
				.name(name)
				.email(email)
				.password(password)
				.build();
	}

}
