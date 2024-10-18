package com.mmdev.dictionaryy.validator;

public interface EntityValidator<T,D> {

	T entityValidator(Long id,D dto);
}
