import { useState } from "react";
import { createUser } from "../services/user";

function CreateUser() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const handleSubmit = async (
        e: React.FormEvent
    ) => {

        e.preventDefault();

        try {

            await createUser({
                name,
                email
            });

            alert("Usuário cadastrado com sucesso!");

            setName("");
            setEmail("");

        } catch (error) {

            alert("Erro ao cadastrar usuário");

            console.error(error);
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
                    Novo Usuário
                </h1>

                <form onSubmit={handleSubmit}>

                    <div>
                        <label>Nome</label>

                        <input
                            type="text"
                            value={name}
                            onChange={(e) =>
                                setName(e.target.value)
                            }
                        />
                    </div>

                    <div>
                        <label>Email</label>

                        <input
                            type="email"
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)
                            }
                        />
                    </div>

                    <button
                        type="submit"
                        style={{
                            width: "100%",
                            marginTop: "10px"
                        }}
                    >
                        Cadastrar
                    </button>

                </form>
            </div>
        </div>
    );
}

export default CreateUser;