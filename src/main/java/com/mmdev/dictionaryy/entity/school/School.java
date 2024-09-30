package com.mmdev.dictionaryy.entity.school;

import com.mmdev.dictionaryy.entity.topics.Topic;
import com.mmdev.dictionaryy.entity.admins.Admin;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "school", catalog = "dictionary", schema = "public")
public class School {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = LAZY)
	private Admin admin;

	@OneToMany(mappedBy = "school")
	private List<Topic> topics;

}
