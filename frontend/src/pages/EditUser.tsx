import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
    getUserById,
    updateUser
} from "../services/user";

function EditUser() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    useEffect(() => {

        if (!id) return;

        getUserById(Number(id))
            .then(response => {

                setName(response.data.name);
                setEmail(response.data.email);

            })
            .catch(error => {
                console.error(error);
            });

    }, [id]);

    const handleSubmit = async (
        e: React.FormEvent
    ) => {

        e.preventDefault();

        try {

            await updateUser(
                Number(id),
                {
                    name,
                    email
                }
            );

            alert("Usuário atualizado com sucesso!");

            navigate("/users");

        } catch (error) {

            console.error(error);

            alert("Erro ao atualizar usuário");
        }
    };

    return (
        <div>

            <h1>Editar Usuário</h1>

            <form onSubmit={handleSubmit}>

                <div>
                    <label>Nome</label>
                    <br />

                    <input
                        type="text"
                        value={name}
                        onChange={(e) =>
                            setName(e.target.value)
                        }
                    />
                </div>

                <br />

                <div>
                    <label>Email</label>
                    <br />

                    <input
                        type="email"
                        value={email}
                        onChange={(e) =>
                            setEmail(e.target.value)
                        }
                    />
                </div>

                <br />

                <button type="submit">
                    Salvar
                </button>

            </form>

        </div>
    );
}

export default EditUser;