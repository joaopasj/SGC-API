import { api } from "./api";

export const getTickets = () =>
    api.get("/tickets");

export const getStats = () =>
    api.get("/tickets/stats");

export const createTicket = (ticket: any) =>
    api.post("/tickets", ticket);

export const deleteTicket = (id: number) =>
    api.delete(`/tickets/${id}`);

export const getTicketById = (id: number) =>
    api.get(`/tickets/${id}`);

export const updateTicket = (
    id: number,
    ticket: any
) =>
    api.put(`/tickets/${id}`, ticket);

export const searchTicketsByTitle = (
    title: string
) =>
    api.get(
        `/tickets/searchByTitle?title=${title}`
    );

export const getTicketsByStatus = (
    status: string
) =>
    api.get(
        `/tickets/status/${status}`
    );

export const getTicketsByPriority = (
    priority: string
) =>
    api.get(
        `/tickets/priority/${priority}`
    );

export const getTicketsPaged = (
    page: number,
    size: number
) =>
    api.get(
        `/tickets/paged?page=${page}&size=${size}`
    );  