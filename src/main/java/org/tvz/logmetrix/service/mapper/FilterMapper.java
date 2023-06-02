package org.tvz.logmetrix.service.mapper;

import org.mapstruct.Mapper;
import org.tvz.logmetrix.dto.FilterDTO;
import org.tvz.logmetrix.entity.Filter;

@Mapper(componentModel = "spring")
public interface FilterMapper {
	FilterDTO toDTO(Filter filter);
	Filter toEntity(FilterDTO filterDTO);
}
