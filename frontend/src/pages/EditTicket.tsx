import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import {
    getTicketById,
    updateTicket
} from "../services/ticket";

import { getUsers } from "../services/user";

function EditTicket() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("ABERTO");
    const [priority, setPriority] = useState("MEDIA");
    const [userId, setUserId] = useState("");

    const [users, setUsers] = useState<any[]>([]);

    useEffect(() => {

        getUsers().then(response => {
            setUsers(response.data);
        });

        if (!id) return;

        getTicketById(Number(id))
            .then(response => {

                const ticket = response.data;

                setTitle(ticket.title);
                setDescription(ticket.description);
                setStatus(ticket.status);
                setPriority(ticket.priority);
                setUserId(ticket.user?.id.toString());

            });

    }, [id]);

    const handleSubmit = async (
        e: React.FormEvent
    ) => {

        e.preventDefault();

        try {

            await updateTicket(
                Number(id),
                {
                    title,
                    description,
                    status,
                    priority,
                    user: {
                        id: Number(userId)
                    }
                }
            );

            alert("Chamado atualizado com sucesso!");

            navigate("/tickets");

        } catch (error) {

            console.error(error);

            alert("Erro ao atualizar chamado");
        }
    };

    return (
        <div>

            <h1>Editar Chamado</h1>

            <form onSubmit={handleSubmit}>

                <div>
                    <label>Título</label>
                    <br />

                    <input
                        value={title}
                        onChange={(e) =>
                            setTitle(e.target.value)
                        }
                    />
                </div>

                <br />

                <div>
                    <label>Descrição</label>
                    <br />

                    <textarea
                        value={description}
                        onChange={(e) =>
                            setDescription(e.target.value)
                        }
                    />
                </div>

                <br />

                <div>
                    <label>Status</label>
                    <br />

                    <select
                        value={status}
                        onChange={(e) =>
                            setStatus(e.target.value)
                        }
                    >
                        <option value="ABERTO">
                            ABERTO
                        </option>

                        <option value="EM_ANDAMENTO">
                            EM_ANDAMENTO
                        </option>

                        <option value="FECHADO">
                            FECHADO
                        </option>
                    </select>
                </div>

                <br />

                <div>
                    <label>Prioridade</label>
                    <br />

                    <select
                        value={priority}
                        onChange={(e) =>
                            setPriority(e.target.value)
                        }
                    >
                        <option value="ALTA">
                            ALTA
                        </option>

                        <option value="MEDIA">
                            MEDIA
                        </option>

                        <option value="BAIXA">
                            BAIXA
                        </option>
                    </select>
                </div>

                <br />

                <div>
                    <label>Usuário</label>
                    <br />

                    <select
                        value={userId}
                        onChange={(e) =>
                            setUserId(e.target.value)
                        }
                    >
                        {users.map(user => (
                            <option
                                key={user.id}
                                value={user.id}
                            >
                                {user.name}
                            </option>
                        ))}
                    </select>
                </div>

                <br />

                <button type="submit">
                    Salvar
                </button>

            </form>

        </div>
    );
}

export default EditTicket;