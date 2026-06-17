import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getUsers, deleteUser } from "../services/user";

function Users() {

    const [users, setUsers] = useState<any[]>([]);

    const loadUsers = () => {
        getUsers().then(response => {
            setUsers(response.data);
        });
    };

    useEffect(() => {
        loadUsers();
    }, []);

    const handleDelete = async (id: number) => {

        const confirmed = window.confirm(
            "Deseja realmente excluir este usuário?"
        );

        if (!confirmed) {
            return;
        }

        try {

            await deleteUser(id);

            setUsers(
                users.filter(user => user.id !== id)
            );

            alert("Usuário excluído com sucesso!");

        } catch (error) {

            console.error(error);

            alert("Erro ao excluir usuário");
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
                <h1>Usuários</h1>

                <Link to="/users/create">
                    <button>
                        Novo Usuário
                    </button>
                </Link>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.name}</td>
                            <td>{user.email}</td>

                            <td>

                                <Link
                                    to={`/users/edit/${user.id}`}
                                >
                                    <button>
                                        Editar
                                    </button>
                                </Link>

                                <button
                                    onClick={() =>
                                        handleDelete(user.id)
                                    }
                                >
                                    Excluir
                                </button>

                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default Users;