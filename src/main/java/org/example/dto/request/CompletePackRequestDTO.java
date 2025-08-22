package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletePackRequestDTO {

    private List<Item> answers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private UUID exerciseId;
        private String answer;
    }

}


