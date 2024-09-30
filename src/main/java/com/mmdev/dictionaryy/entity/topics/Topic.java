package com.mmdev.dictionaryy.entity.topics;

import com.mmdev.dictionaryy.entity.school.School;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topic", catalog = "dictionary", schema = "public")
public class Topic {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "school_id")
	private School school;

}
