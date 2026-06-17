import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import Tickets from "./pages/Tickets";
import Users from "./pages/Users";
import CreateUser from "./pages/CreateUser";
import CreateTicket from "./pages/CreateTicket";
import EditUser from "./pages/EditUser";
import EditTicket from "./pages/EditTicket";

function App() {
    return (
        <BrowserRouter>

            <nav className="navbar">

                <div className="logo">
                    Sistema de Gerenciamento de Chamados
                </div>

                <div className="menu">
                    <Link to="/">Dashboard</Link>

                    <Link to="/tickets">
                        Chamados
                    </Link>

                    <Link to="/users">
                        Usuários
                    </Link>
                </div>

            </nav>

            <hr />

            <Routes>
                <Route path="/" element={<Dashboard />} />

                <Route path="/tickets" element={<Tickets />} />
                <Route path="/tickets/create" element={<CreateTicket />} />
                <Route path="/tickets/edit/:id" element={<EditTicket />} />

                <Route path="/users" element={<Users />} />
                <Route path="/users/create" element={<CreateUser />} />
                <Route path="/users/edit/:id" element={<EditUser />} />
            </Routes>

        </BrowserRouter>
    );
}

export default App;