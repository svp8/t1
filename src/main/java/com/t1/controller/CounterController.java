package com.t1.controller;

import com.t1.utils.OccurrenceCounterUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@Tag(name = "Счетчик символов", description = "Считает количество каждого символа в строке")
public class CounterController {
    @GetMapping("/count")
    @Operation(
            summary = "Endpoint для подсчета симолов"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Строка обработана",
                    content ={ @Content(mediaType = "application/json",schema = @Schema(type = "Map",defaultValue = "{'a':2,'b':1}"))}
            ),
            @ApiResponse(responseCode = "400", description = "Строка пуста", content = @Content)
    })
    public ResponseEntity<?> count(@Parameter(description = "Строка для обработки") @RequestParam String text) {
        if (text.isBlank()) {
            return ResponseEntity.badRequest().body("String is blank");
        }
        return ResponseEntity.ok().body(OccurrenceCounterUtil.count(text));
    }
}
