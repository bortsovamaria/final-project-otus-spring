package ru.otus.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AttachmentFullResponseDto {
    private long id;

    private String name;

    private String type;

    private byte[] data;

}
