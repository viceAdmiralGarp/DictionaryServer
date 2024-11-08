package com.mmdev.dictionaryy.entity.words;

import com.mmdev.dictionaryy.entity.student.Student;
import com.mmdev.dictionaryy.entity.topics.SubTopic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "subTopics")
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "all_eng_words", catalog = "dictionary", schema = "public")
public class EngWord {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	@Builder.Default
	@ManyToMany(mappedBy = "engWords")
	private List<SubTopic> subTopics = new ArrayList<>();

	@Builder.Default
	@ManyToMany(mappedBy = "engWords")
	private List<Student> students = new ArrayList<>();
}
