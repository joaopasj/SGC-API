package SGC_API.dto;

public record TicketResponseDTO(
        Long id,
        String title,
        String status,
        String priority
) {
}