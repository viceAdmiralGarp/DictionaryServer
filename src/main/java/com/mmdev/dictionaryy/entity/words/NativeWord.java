package com.mmdev.dictionaryy.entity.words;

import com.mmdev.dictionaryy.entity.student.Student;
import com.mmdev.dictionaryy.entity.topics.SubTopic;
import com.mmdev.dictionaryy.model.words.NativeWordDto;
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
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "all_native_words", catalog = "dictionary", schema = "public")
public class NativeWord {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	@Builder.Default
	@ManyToMany(mappedBy = "nativeWords")
	private List<SubTopic> subTopics = new ArrayList<>();

	@Builder.Default
	@ManyToMany(mappedBy = "nativeWords")
	private List<Student> students = new ArrayList<>();

	public NativeWordDto toDto(){
		return NativeWordDto.builder()
				.id(id)
				.name(name)
				.build();
	}
}
