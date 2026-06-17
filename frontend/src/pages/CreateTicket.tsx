import { useEffect, useState } from "react";
import { createTicket } from "../services/ticket";
import { getUsers } from "../services/user";

function CreateTicket() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("ABERTO");
    const [priority, setPriority] = useState("ALTA");
    const [userId, setUserId] = useState("");

    const [users, setUsers] = useState<any[]>([]);

    useEffect(() => {
        getUsers().then(response => {
            setUsers(response.data);
        });
    }, []);

    const handleSubmit = async (
        e: React.FormEvent
    ) => {
        e.preventDefault();

        try {
            await createTicket({
                title,
                description,
                status,
                priority,
                userId: userId ? Number(userId) : null
            });

            alert("Chamado criado com sucesso!");

            setTitle("");
            setDescription("");
            setStatus("ABERTO");
            setPriority("ALTA");
            setUserId("");

        } catch (error) {
            console.error(error);
            alert("Erro ao criar chamado");
        }
    };

    return (
        <div
            style={{
                display: "flex",
                justifyContent: "center",
                marginTop: "30px"
            }}
        >
            <div
                style={{
                    width: "100%",
                    maxWidth: "700px"
                }}
            >
                <h1
                    style={{
                        textAlign: "center",
                        marginBottom: "25px"
                    }}
                >
                    Novo Chamado
                </h1>

                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Título</label>

                        <input
                            type="text"
                            value={title}
                            onChange={(e) =>
                                setTitle(e.target.value)
                            }
                        />
                    </div>

                    <div>
                        <label>Descrição</label>

                        <textarea
                            rows={5}
                            value={description}
                            onChange={(e) =>
                                setDescription(e.target.value)
                            }
                        />
                    </div>

                    <div>
                        <label>Status</label>

                        <select
                            value={status}
                            onChange={(e) =>
                                setStatus(e.target.value)
                            }
                        >
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
                    </div>

                    <div>
                        <label>Prioridade</label>

                        <select
                            value={priority}
                            onChange={(e) =>
                                setPriority(e.target.value)
                            }
                        >
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
                    </div>

                    <div>
                        <label>Usuário</label>

                        <select
                            value={userId}
                            onChange={(e) =>
                                setUserId(e.target.value)
                            }
                        >
                            <option value="">
                                Selecione um usuário
                            </option>

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

                    <button
                        type="submit"
                        style={{
                            width: "100%",
                            marginTop: "10px"
                        }}
                    >
                        Criar Chamado
                    </button>
                </form>
            </div>
        </div>
    );
}

export default CreateTicket;