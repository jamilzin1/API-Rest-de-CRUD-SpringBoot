package apirest.aiko.services;

import apirest.aiko.dtos.EquipmentDTO;
import apirest.aiko.mappers.EquipmentMapper;
import apirest.aiko.models.Equipment;
import apirest.aiko.repositories.EquipmentRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class EquipmentService {
    final EquipmentRepository equipmentRepository;
    final EquipmentMapper mapper;

    public List<EquipmentDTO> findAll() {
        List<Equipment> list = equipmentRepository.findAll();
        return mapper.mapEntityListToDtoList(list);
    }

//    @Transactional
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "OK - Criado com sucesso", content = {@Content(mediaType = "application/json")}),
//            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json")}),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json")})
//    })
//    public Equipment save(EquipmentDTO equipment) {
//        if (equipment == null) {
//            throw new IllegalArgumentException("Valor nulo");
//        }
//        return equipmentRepository.saveEquipmentFromDTO(equipment);
//    }

    @Transactional
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json")})
    })
//    public Optional<EquipmentDTO> findById(UUID id) {
//        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
//        log.info("service: ->"+ equipmentOptional);
//        if (equipmentOptional.isPresent()) {
//            var equipment = equipmentOptional.get();
//            log.info(Optional.of(mapper.mapEntityToDto(equipment)));
//            return Optional.of(mapper.mapEntityToDto(equipment));
//        }
//
//        return Optional.empty();
//    }

    public Optional<Equipment> findById(UUID id) {
        return equipmentRepository.findById(id);
    }
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - deletado com sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json")})
    })
    public void delete(EquipmentDTO equipment) {
        equipmentRepository.delete(mapper.mapDtoToEntity(equipment));
    }
}
