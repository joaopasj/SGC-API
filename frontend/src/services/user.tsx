import { api } from "./api";

export const getUsers = () =>
    api.get("/users");

export const createUser = (user: any) =>
    api.post("/users", user);

export const deleteUser = (id: number) =>
    api.delete(`/users/${id}`);

export const getUserById = (id: number) =>
    api.get(`/users/${id}`);

export const updateUser = (
    id: number,
    user: any
) => api.put(`/users/${id}`, user);