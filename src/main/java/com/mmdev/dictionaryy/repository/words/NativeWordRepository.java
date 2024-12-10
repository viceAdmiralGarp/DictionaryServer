package com.mmdev.dictionaryy.repository.words;

import com.mmdev.dictionaryy.entity.words.NativeWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NativeWordRepository extends JpaRepository<NativeWord, Long> {
}
