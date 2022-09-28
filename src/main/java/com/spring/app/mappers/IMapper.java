package com.spring.app.mappers;

public interface IMapper<T> {

 
    // PersonResponseDTO entityToDto(PersonEntity entity);
    T entityToResponseDto(T entity);

    // PersonEntity dtoToEntity(PersonDTO dto);
    T requestDtoToEntity(T requestDto);

    //PersonResponseDTOFull listPersonDTOs(List<PersonEntity> listPersonEntities);

}
