import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
    getTicketsPaged,
    deleteTicket,
    searchTicketsByTitle,
    getTicketsByStatus,
    getTicketsByPriority
} from "../services/ticket";

function Tickets() {

    const [tickets, setTickets] = useState<any[]>([]);
    const [search, setSearch] = useState("");
    const [status, setStatus] = useState("");
    const [priority, setPriority] = useState("");

    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const loadTickets = () => {

        getTicketsPaged(page, 5)
            .then(response => {

                setTickets(response.data.content);

                setTotalPages(
                    response.data.totalPages
                );
            });
    };

    useEffect(() => {
        loadTickets();
    }, [page]);

    const handleSearch = async () => {

        try {

            let data: any[] = [];

            if (status) {

                const response =
                    await getTicketsByStatus(status);

                data = response.data;

            } else if (priority) {

                const response =
                    await getTicketsByPriority(priority);

                data = response.data;

            } else if (search.trim()) {

                const response =
                    await searchTicketsByTitle(search);

                data = response.data;

            } else {

                loadTickets();
                return;
            }

            if (search.trim()) {

                data = data.filter((ticket: any) =>
                    ticket.title
                        .toLowerCase()
                        .includes(search.toLowerCase())
                );
            }

            if (priority && status) {

                data = data.filter((ticket: any) =>
                    ticket.priority === priority
                );
            }

            setTickets(data);

            setTotalPages(1);
            setPage(0);

        } catch (error) {

            console.error(error);

            alert("Erro ao filtrar chamados");
        }
    };

    const handleClear = () => {

        setSearch("");
        setStatus("");
        setPriority("");

        setPage(0);

        getTicketsPaged(0, 5)
            .then(response => {

                setTickets(response.data.content);

                setTotalPages(
                    response.data.totalPages
                );
            });
    };

    const handleDelete = async (id: number) => {

        const confirmed = window.confirm(
            "Deseja realmente excluir este chamado?"
        );

        if (!confirmed) {
            return;
        }

        try {

            await deleteTicket(id);

            loadTickets();

            alert("Chamado excluído com sucesso!");

        } catch (error) {

            console.error(error);

            alert("Erro ao excluir chamado");
        }
    };

    return (
        <>
            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    marginBottom: "20px",
                    flexWrap: "wrap",
                    gap: "10px"
                }}
            >
                <h1>Chamados</h1>

                <Link to="/tickets/create">
                    <button>
                        Novo Chamado
                    </button>
                </Link>
            </div>

            <div
                style={{
                    display: "flex",
                    gap: "10px",
                    marginBottom: "20px",
                    alignItems: "center",
                    flexWrap: "wrap"
                }}
            >

                <input
                    type="text"
                    placeholder="Pesquisar por título..."
                    value={search}
                    onChange={(e) =>
                        setSearch(e.target.value)
                    }
                />

                <select
                    value={status}
                    onChange={(e) =>
                        setStatus(e.target.value)
                    }
                >
                    <option value="">
                        Todos os status
                    </option>

                    <option value="ABERTO">
                        Aberto
                    </option>

                    <option value="EM_ANDAMENTO">
                        Em Andamento
                    </option>

                    <option value="FECHADO">
                        Fechado
                    </option>
                </select>

                <select
                    value={priority}
                    onChange={(e) =>
                        setPriority(e.target.value)
                    }
                >
                    <option value="">
                        Todas as prioridades
                    </option>

                    <option value="ALTA">
                        Alta
                    </option>

                    <option value="MEDIA">
                        Média
                    </option>

                    <option value="BAIXA">
                        Baixa
                    </option>
                </select>

                <button onClick={handleSearch}>
                    Buscar
                </button>

                <button onClick={handleClear}>
                    Limpar
                </button>

            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Status</th>
                        <th>Prioridade</th>
                        <th>Usuário</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
                    {tickets.map(ticket => (
                        <tr key={ticket.id}>
                            <td>{ticket.id}</td>

                            <td>{ticket.title}</td>

                            <td>
                                {ticket.status?.replaceAll("_", " ")}
                            </td>

                            <td>{ticket.priority}</td>

                            <td>
                                {ticket.user?.name ??
                                    "Sem usuário"}
                            </td>

                            <td>

                                <Link
                                    to={`/tickets/edit/${ticket.id}`}
                                >
                                    <button>
                                        Editar
                                    </button>
                                </Link>

                                <button
                                    onClick={() =>
                                        handleDelete(ticket.id)
                                    }
                                >
                                    Excluir
                                </button>

                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="pagination">

                <button
                    disabled={page === 0}
                    onClick={() =>
                        setPage(page - 1)
                    }
                >
                    Anterior
                </button>

                <span>
                    Página {page + 1} de {totalPages}
                </span>

                <button
                    disabled={page >= totalPages - 1}
                    onClick={() =>
                        setPage(page + 1)
                    }
                >
                    Próxima
                </button>

            </div>
        </>
    );
}

export default Tickets;