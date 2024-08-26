package br.com.lucefull.mapper.custom;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import br.com.lucefull.data.vo.v1.PersonVO;
import br.com.lucefull.data.vo.v2.PersonVOV2;
import br.com.lucefull.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "key", target = "id")
    Person personVoToPerson(PersonVO vo);

    @Mapping(source = "key", target = "id")
    List<Person> personVOToPerson(List<PersonVO> persons);

    @Mapping(source = "id", target = "key")
    PersonVO personToPersonVO(Person person);

    @Mapping(source = "id", target = "key")
    List<PersonVO> personToPersonVO(List<Person> persons);

    PersonVOV2 personToPersonVOV2(Person person);

    List<PersonVOV2> personToPersonVOV2(List<Person> persons);

    Person personVoToPerson(PersonVOV2 vo);
}
