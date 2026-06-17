package SGC_API.dto;

public record TicketStatsDTO(

        long abertos,
        long emAndamento,
        long fechados,

        long abertoAlta,
        long abertoMedia,
        long abertoBaixa,

        long andamentoAlta,
        long andamentoMedia,
        long andamentoBaixa,

        long fechadoAlta,
        long fechadoMedia,
        long fechadoBaixa

) {
}