import { useEffect, useState } from "react";
import { getStats } from "../services/ticket";

function Dashboard() {

    const [stats, setStats] = useState<any>(null);

    useEffect(() => {
        getStats().then(response => {
            setStats(response.data);
        });
    }, []);

    if (!stats) {
        return <h1>Carregando...</h1>;
    }

    return (
        <>
            <h1>Dashboard</h1>

            <div className="matrix-grid">

                <div className="card">
                    <h2>Abertos</h2>

                    <p>
                        <span className="alta">Alta</span>
                        <span>{stats.abertoAlta}</span>
                    </p>

                    <p>
                        <span className="media">Média</span>
                        <span>{stats.abertoMedia}</span>
                    </p>

                    <p>
                        <span className="baixa">Baixa</span>
                        <span>{stats.abertoBaixa}</span>
                    </p>
                </div>

                <div className="card">
                    <h2>Em Andamento</h2>

                    <p>
                        <span className="alta">Alta</span>
                        <span>{stats.andamentoAlta}</span>
                    </p>

                    <p>
                        <span className="media">Média</span>
                        <span>{stats.andamentoMedia}</span>
                    </p>

                    <p>
                        <span className="baixa">Baixa</span>
                        <span>{stats.andamentoBaixa}</span>
                    </p>
                </div>

                <div className="card">
                    <h2>Fechados</h2>

                    <p>
                        <span className="alta">Alta</span>
                        <span>{stats.fechadoAlta}</span>
                    </p>

                    <p>
                        <span className="media">Média</span>
                        <span>{stats.fechadoMedia}</span>
                    </p>

                    <p>
                        <span className="baixa">Baixa</span>
                        <span>{stats.fechadoBaixa}</span>
                    </p>
                </div>

            </div>
        </>
    );
}

export default Dashboard;