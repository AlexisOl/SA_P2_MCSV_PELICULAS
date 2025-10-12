package com.movies.microservice.horario.infrastructure.inputadapters.rest;

import com.movies.microservice.horario.application.inputports.*;
import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.infrastructure.inputadapters.rest.dto.CrearActualizarHorarioRequest;
import com.movies.microservice.horario.infrastructure.inputadapters.rest.dto.HorarioResponse;
import com.movies.microservice.horario.infrastructure.inputadapters.rest.mapper.HorarioRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
public class HorarioControllerAdapter {

    private final CrearHorarioInputPort crearUC;
    private final ActualizarHorarioInputPort actualizarUC;
    private final DesactivarHorarioInputPort desactivarUC;
    private final ListarHorariosInputPort listarUC;

    @PostMapping
    public ResponseEntity<HorarioResponse> crear(@RequestBody CrearActualizarHorarioRequest req) {
        Horario creado = crearUC.crear(HorarioRestMapper.toDomain(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(HorarioRestMapper.toResponse(creado));
    }

    @PutMapping("/{horarioId}")
    public HorarioResponse actualizar(@PathVariable UUID horarioId,
            @RequestBody CrearActualizarHorarioRequest req) {
        Horario actualizado = actualizarUC.actualizar(horarioId, HorarioRestMapper.toDomain(req));
        return HorarioRestMapper.toResponse(actualizado);
    }

    @PostMapping("/{horarioId}/desactivar")
    public HorarioResponse desactivar(@PathVariable UUID horarioId) {
        Horario h = desactivarUC.desactivar(horarioId);
        return HorarioRestMapper.toResponse(h);
    }

    @GetMapping
    public List<HorarioResponse> listar(
            @RequestParam(required = false) UUID peliculaId,
            @RequestParam(required = false) UUID cinemaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            @RequestParam(required = false, defaultValue = "true") Boolean soloActivos
    ) {
        return listarUC.listar(peliculaId, cinemaId, desde, hasta, soloActivos)
                .stream().map(HorarioRestMapper::toResponse).toList();
    }
}
